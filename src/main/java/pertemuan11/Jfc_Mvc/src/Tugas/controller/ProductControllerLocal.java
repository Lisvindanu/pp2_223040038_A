package main.java.pertemuan11.Jfc_Mvc.src.Tugas.controller;

import main.java.pertemuan11.Jfc_Mvc.src.Tugas.model.MyBatisUtil;
import main.java.pertemuan11.Jfc_Mvc.src.Tugas.model.Product;
import main.java.pertemuan11.Jfc_Mvc.src.Tugas.model.ProductMapperLocal;
import main.java.pertemuan11.Jfc_Mvc.src.Tugas.util.SSLUtils;
import main.java.pertemuan11.Jfc_Mvc.src.Tugas.view.ProductView;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductControllerLocal {
    private final ProductView view;
    private final ProductMapperLocal mapper;
    private final SqlSession session;
    private final List<Long> productIds = new ArrayList<>();

    public ProductControllerLocal(ProductView view) {
        this.view = view;


        this.session = MyBatisUtil.getSqlSession();
        this.mapper = this.session.getMapper(ProductMapperLocal.class);

        initializeCategoryAndGenre();

        //listener untuk setiap operasi
        this.view.addAddProductListener(new AddProductListener());
        this.view.addUpdateProductListener(new UpdateProductListener());
        this.view.addDeleteProductListener(new DeleteProductListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                view.clearTableSelection();
                displayProductDetails(view.getSelectedProductIndex());
            }
        });
        this.view.addTableSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = view.getSelectedTableRow();
                if (selectedRow != -1) {
                    view.clearListSelection();
                    Long productId = Long.valueOf(view.getProductIdAtTableRow(selectedRow));
                    displayProductDetailsById(productId);
                }
            }
        });
    }

    private void initializeCategoryAndGenre() {
        try {
            // Ambil data kategori dan genre dari mapper
            List<Product> categories = mapper.getCategories();
            view.setCategories(categories.stream().map(Product::getCategoryName).toArray(String[]::new));

            List<Product> genres = mapper.getGenres();
            String[] genreNames = new String[genres.size() + 1];
            genreNames[0] = ""; // Pilihan kosong
            for (int i = 0; i < genres.size(); i++) {
                genreNames[i + 1] = genres.get(i).getGenreName();
            }
            view.setGenres(genreNames);
        } catch (Exception e) {
            showError("Error loading categories and genres: " + e.getMessage());
        }
    }

    private void displayProductDetails(int selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex < productIds.size()) {
            displayProductDetailsById(productIds.get(selectedIndex));
        }
    }

    private void displayProductDetailsById(Long productId) {
        try {
            Product product = mapper.getById(productId);
            if (product != null) {
                view.setNameField(product.getName());
                view.setPriceField(String.valueOf(product.getPrice()));
                view.setQuantityField(String.valueOf(product.getQuantity()));
                view.setCategorySelection(product.getCategoryName());
                view.setGenreSelection(product.getGenreName());
                if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
                    view.displayImage(product.getImageUrl());
                }
            }
        } catch (Exception e) {
            showError("Error loading product details: " + e.getMessage());
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<Product> products = mapper.getAll();
                productIds.clear();

                String[] productList = new String[products.size()];
                Object[][] tableData = new Object[products.size()][7]; // Kolom ke-7 untuk gambar

                for (int i = 0; i < products.size(); i++) {
                    Product product = products.get(i);
                    productIds.add(product.getId());

                    productList[i] = String.format("[ID: %d] %s - $%d (Qty: %d)",
                            product.getId(), product.getName(), product.getPrice(), product.getQuantity());

                    tableData[i][0] = product.getId();
                    tableData[i][1] = product.getName();
                    tableData[i][2] = product.getPrice();
                    tableData[i][3] = product.getQuantity();
                    tableData[i][4] = product.getCategoryName();
                    tableData[i][5] = product.getGenreName();

                    // Ambil dan tampilkan gambar
                    String imageUrl = product.getImageUrl();
                    tableData[i][6] = createImageIcon(imageUrl);
                }

                view.setProductList(productList);
                view.setProductTableData(tableData);
            } catch (Exception ex) {
                showError("Error fetching data: " + ex.getMessage());
            }
        }

        private ImageIcon createImageIcon(String imageUrl) {
            try {

                if (imageUrl.startsWith("/")) {
                    imageUrl = "https://virtual-realm.my.id" + imageUrl;
                } else {
                    imageUrl = "https://virtual-realm.my.id/" + imageUrl;
                }

                // Use the same secure loading method as the form
                BufferedImage originalImage = SSLUtils.loadImageSecurely(imageUrl);
                Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            } catch (Exception e) {
                e.printStackTrace(); // Add this to see errors
                return new ImageIcon();
            }
        }
    }

    private String uploadImageToServer(File imageFile) {
        String uploadUrl = "http://virtual-realm-b8a13cc57b6c.herokuapp.com/sftp/upload-file";
        String boundary = "---Boundary";

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(uploadUrl).openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            conn.setRequestProperty("x-api-key", "secret");

            try (OutputStream os = conn.getOutputStream();
                 PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8), true)) {

                // Tambahkan parameter form-data


                writeFormField(writer, boundary, "server", "YOUR_SERVER");
                writeFormField(writer, boundary, "port", "YOUR_PORT");
                writeFormField(writer, boundary, "username", "YOUR_USERNAME");
                writeFormField(writer, boundary, "password", "YOUR_PASSWORD");

                // Tambahkan file gambar
                writeFile(writer, os, boundary, "file", imageFile);

                writer.append("--").append(boundary).append("--").append("\r\n");
                writer.flush();
            }

            // Periksa respons server
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return parseResponse(conn.getInputStream());
            } else {
                logError(conn);
            }
        } catch (Exception e) {
            showError("Error uploading file: " + e.getMessage());
        }
        return null;
    }

    private void writeFormField(PrintWriter writer, String boundary, String name, String value) {
        writer.append("--").append(boundary).append("\r\n")
                .append("Content-Disposition: form-data; name=\"").append(name).append("\"\r\n\r\n")
                .append(value).append("\r\n");
        writer.flush();
    }

    private void writeFile(PrintWriter writer, OutputStream os, String boundary, String name, File file) throws IOException {
        writer.append("--").append(boundary).append("\r\n")
                .append("Content-Disposition: form-data; name=\"").append(name)
                .append("\"; filename=\"").append(file.getName()).append("\"\r\n")
                .append("Content-Type: ").append(getContentType(file)).append("\r\n\r\n");
        writer.flush();
        Files.copy(file.toPath(), os);
        os.flush();
        writer.append("\r\n");
        writer.flush();
    }

    private String parseResponse(InputStream inputStream) throws IOException {
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            String response = scanner.useDelimiter("\\A").next();
            System.out.println("Server Response: " + response); // Log respons server

            int startIndex = response.indexOf("\"filePath\":\"") + 12;
            int endIndex = response.indexOf("\"", startIndex);
            String filePath = response.substring(startIndex, endIndex);

            System.out.println("Extracted filePath: " + filePath); // Log filePath yang diekstrak

            if (filePath.startsWith("/uploads/images/")) {
                return filePath; // Jika sudah benar, kembalikan apa adanya
            }

            // Jika belum lengkap, tambahkan prefix
            return filePath;
        }
    }

    private void logError(HttpURLConnection conn) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
            reader.lines().forEach(System.err::println);
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(view, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private String getContentType(File file) {
        String name = file.getName().toLowerCase();
        if (name.endsWith(".jpg") || name.endsWith(".jpeg")) return "image/jpeg";
        if (name.endsWith(".png")) return "image/png";
        return "application/octet-stream";
    }

    class AddProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Product product = new Product();
                product.setName(view.getNameInput());
                product.setPrice(Long.parseLong(view.getPriceInput()));
                product.setQuantity(Integer.parseInt(view.getQuantityInput()));
                product.setCategoryId(mapper.getCategoryId(view.getSelectedCategory()));
                product.setGenreId(mapper.getGenreId(view.getSelectedGenre()));

                // Unggah gambar ke server dan dapatkan URL-nya
                File selectedImageFile = view.getSelectedImageFile();
                if (selectedImageFile != null) {
                    String imageUrl = uploadImageToServer(selectedImageFile);
                    if (imageUrl != null) {
                        product.setImageUrl(imageUrl);
                    } else {
                        JOptionPane.showMessageDialog(view, "Failed to upload image.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                // Simpan produk ke database lokal
                mapper.insert(product);
                session.commit();

                JOptionPane.showMessageDialog(view, "Product added successfully!");
                new RefreshListener().actionPerformed(null);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error adding product: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class UpdateProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int selectedRow = view.getSelectedTableRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(view, "Please select a product to update.",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Long productId = Long.valueOf(view.getProductIdAtTableRow(selectedRow));
                Product product = mapper.getById(productId);
                if (product != null) {
                    product.setName(view.getNameInput());
                    product.setPrice(Long.parseLong(view.getPriceInput()));
                    product.setQuantity(Integer.parseInt(view.getQuantityInput()));
                    product.setCategoryId(mapper.getCategoryId(view.getSelectedCategory()));
                    product.setGenreId(mapper.getGenreId(view.getSelectedGenre()));

                    // Upload image if a new file is selected
                    File selectedImageFile = view.getSelectedImageFile();
                    if (selectedImageFile != null) {
                        String imageUrl = uploadImageToServer(selectedImageFile);
                        if (imageUrl != null) {
                            product.setImageUrl(imageUrl);
                        } else {
                            JOptionPane.showMessageDialog(view, "Failed to upload image.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }

                    // Update the product in the database
                    mapper.update(product);
                    session.commit();

                    JOptionPane.showMessageDialog(view, "Product updated successfully!");
                    new RefreshListener().actionPerformed(null);
                } else {
                    JOptionPane.showMessageDialog(view, "Selected product does not exist.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error updating product: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    class DeleteProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int selectedRow = view.getSelectedTableRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(view, "Please select a product to delete!");
                    return;
                }

                Long productId = Long.valueOf(view.getProductIdAtTableRow(selectedRow));
                mapper.delete(productId);
                session.commit();

                JOptionPane.showMessageDialog(view, "Product deleted successfully!");
                new RefreshListener().actionPerformed(null);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error deleting product: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
