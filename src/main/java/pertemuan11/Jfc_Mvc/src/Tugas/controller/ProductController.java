package main.java.pertemuan11.Jfc_Mvc.src.Tugas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.pertemuan11.Jfc_Mvc.src.Tugas.model.MyBatisUtil;
import main.java.pertemuan11.Jfc_Mvc.src.Tugas.model.Product;
import main.java.pertemuan11.Jfc_Mvc.src.Tugas.model.ProductMapper;
import main.java.pertemuan11.Jfc_Mvc.src.Tugas.view.ProductView;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.rmi.server.LogStream.log;

public class ProductController {
    private final ProductView view;
    private final ProductMapper mapper;
    private final SqlSession session;
    private static final String BASE_URL = "http://virtual-realm-b8a13cc57b6c.herokuapp.com/api";
    private static final String API_KEY = "secret";
    private static final String BOUNDARY = UUID.randomUUID().toString();
    private List<Long> productIds = new ArrayList<>();

    public ProductController(ProductView view, ProductMapper mapper) {
        this.view = view;
        this.session = MyBatisUtil.getSqlSession();
        this.mapper = this.session.getMapper(ProductMapper.class);


        initializeCategoryAndGenre();


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
                    Integer productId = view.getProductIdAtTableRow(selectedRow);
                    displayProductDetails(productId);
                }
            }
        });

    }
    private void log(String message) {
        System.out.println("[ProductController] " + message);
    }


    private JSONArray parseJsonResponse(String response) throws Exception {
        if (response == null || response.trim().isEmpty()) {
            throw new Exception("Empty response from server");
        }


        if (response.trim().startsWith("[")) {
            return new JSONArray(response);
        }


        if (response.trim().startsWith("{")) {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.has("data")) {
                return jsonObject.getJSONArray("data");
            }
        }

        throw new Exception("Invalid JSON response format: " + response);
    }

    private void initializeCategoryAndGenre() {
        try {
            // Fetch and parse categories
            String categoriesResponse = fetchFromApi("/categories");
            System.out.println("Categories Response: " + categoriesResponse);

            JSONArray categories = parseJsonResponse(categoriesResponse);
            String[] categoryNames = new String[categories.length()];
            for (int i = 0; i < categories.length(); i++) {
                JSONObject category = categories.getJSONObject(i);
                categoryNames[i] = category.getString("name");
            }
            view.setCategories(categoryNames);

            // Fetch and parse genres
            String genresResponse = fetchFromApi("/genres");
            System.out.println("Genres Response: " + genresResponse);

            JSONArray genres = parseJsonResponse(genresResponse);
            String[] genreNames = new String[genres.length() + 1];
            genreNames[0] = ""; // Empty option for no genre
            for (int i = 0; i < genres.length(); i++) {
                JSONObject genre = genres.getJSONObject(i);
                genreNames[i + 1] = genre.getString("name");
            }
            view.setGenres(genreNames);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view,
                    "Error loading categories and genres: " + e.getMessage() +
                            "\nCheck console for detailed error information");
        }
    }

    private String fetchFromApi(String endpoint) throws Exception {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-Api-Key", API_KEY);
        conn.setRequestProperty("Accept", "application/json");

        StringBuilder response = new StringBuilder();
        int responseCode = conn.getResponseCode();
        System.out.println("Response Code for " + endpoint + ": " + responseCode);

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(responseCode == HttpURLConnection.HTTP_OK ?
                        conn.getInputStream() : conn.getErrorStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        String responseStr = response.toString();
        System.out.println("Response from " + endpoint + ": " + responseStr);

        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("API request failed: " + responseStr);
        }

        return responseStr;
    }

    private void displayProductDetails(int selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex < productIds.size()) {
            try {
                Long productId = productIds.get(selectedIndex);
                String response = fetchFromApi("/products/" + productId);
                log("Product Details Response: " + response);

                JSONObject jsonResponse = new JSONObject(response);

                if (jsonResponse.getInt("code") != 200) {
                    JOptionPane.showMessageDialog(view,
                            "Error fetching product details: " + jsonResponse.optString("data", "Unknown error"));
                    return;
                }

                JSONObject productData = jsonResponse.getJSONObject("data");

                view.setNameField(productData.getString("name"));
                view.setPriceField(String.valueOf(productData.getLong("price")));
                view.setQuantityField(String.valueOf(productData.getInt("quantity")));
                view.setCategorySelection(productData.getString("categoryName"));

                if (!productData.isNull("genreName")) {
                    view.setGenreSelection(productData.getString("genreName"));
                } else {
                    view.setGenreSelection("");
                }

                if (!productData.isNull("imageUrl")) {
                    view.displayImage(productData.getString("imageUrl"));
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(view,
                        "Error loading product details: " + e.getMessage() +
                                "\nCheck console for detailed error information");
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String response = fetchFromApi("/products");
                JSONArray products = parseJsonResponse(response);
                productIds.clear();


                String[] productList = new String[products.length()];


                Object[][] tableData = new Object[products.length()][7]; // Kolom ke-7 untuk gambar

                for (int i = 0; i < products.length(); i++) {
                    JSONObject product = products.getJSONObject(i);
                    Long id = product.getLong("id");
                    String name = product.getString("name");
                    Long price = product.getLong("price");
                    Integer quantity = product.getInt("quantity");
                    String category = product.getString("categoryName");
                    String genre = product.optString("genreName", "");
                    String imageUrl = product.optString("imageUrl", null);

                    productIds.add(id);

                    // Data untuk JList
                    productList[i] = String.format("[ID: %d] %s - $%d (Qty: %d)", id, name, price, quantity);

                    // Data untuk JTable
                    tableData[i][0] = id;
                    tableData[i][1] = name;
                    tableData[i][2] = price;
                    tableData[i][3] = quantity;
                    tableData[i][4] = category;
                    tableData[i][5] = genre;


                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        try {
                            // Unduh gambar dan skalakan
                            ImageIcon icon = new ImageIcon(new URL("https://virtual-realm.my.id" + imageUrl));
                            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                            tableData[i][6] = new ImageIcon(scaledImage);
                        } catch (Exception ex) {
                            tableData[i][6] = new JLabel("No Image");
                        }
                    } else {
                        tableData[i][6] = new JLabel("No Image");
                    }
                }

                view.setProductList(productList);
                view.setProductTableData(tableData);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Error refreshing product list: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }




    class AddProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                log("Starting to add a new product...");
                URL url = new URL(BASE_URL + "/products");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("X-Api-Key", API_KEY);
                conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
                conn.setDoOutput(true);

                try (OutputStream os = conn.getOutputStream();
                     PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8), true)) {

                    // Write JSON body part
                    writer.append("--").append(BOUNDARY).append("\r\n");
                    writer.append("Content-Disposition: form-data; name=\"body\"\r\n");
                    writer.append("Content-Type: application/json\r\n\r\n");

                    // Create JSON request body
                    JSONObject json = new JSONObject();
                    json.put("name", view.getNameInput());
                    json.put("price", Long.parseLong(view.getPriceInput()));
                    json.put("quantity", Integer.parseInt(view.getQuantityInput()));
                    json.put("categoryId", getCategoryId(view.getSelectedCategory()));

                    String selectedGenre = view.getSelectedGenre();
                    if (!selectedGenre.isEmpty()) {
                        json.put("genreId", getGenreId(selectedGenre));
                    }

                    log("Preparing JSON data: " + json.toString());
                    writer.append(json.toString());
                    writer.append("\r\n");

                    // Add file part if image is selected
                    if (view.getSelectedImageFile() != null) {
                        String formattedFileName = formatFileName(view.getSelectedImageFile().getName());
                        log("Adding image file: " + formattedFileName);
                        writer.append("--").append(BOUNDARY).append("\r\n");
                        writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"")
                                .append(formattedFileName).append("\"\r\n");
                        writer.append("Content-Type: ").append(getContentType(view.getSelectedImageFile())).append("\r\n\r\n");
                        writer.flush();

                        FileInputStream fileInputStream = new FileInputStream(view.getSelectedImageFile());
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                            os.write(buffer, 0, bytesRead);
                        }
                        os.flush();
                        fileInputStream.close();
                        writer.append("\r\n");
                    }

                    writer.append("--").append(BOUNDARY).append("--").append("\r\n");
                }

                int responseCode = conn.getResponseCode();
                log("Server response code: " + responseCode);

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    log("Product added successfully.");
                    JOptionPane.showMessageDialog(view, "Product added successfully!");
                    view.clearInputFields();
                    new RefreshListener().actionPerformed(null);
                } else {
                    logErrorFromConnection(conn);
                }
            } catch (Exception ex) {
                log("Error adding product: " + ex.getMessage());
                JOptionPane.showMessageDialog(view, "Error adding product: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

//
//    class UpdateProductListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            int selectedIndex = view.getSelectedProductIndex();
//            if (selectedIndex == -1 || selectedIndex >= productIds.size()) {
//                log("No product selected for update");
//                JOptionPane.showMessageDialog(view, "Please select a product to update!");
//                return;
//            }
//
//            try {
//                Long productId = productIds.get(selectedIndex);
//                log("Starting to update product with ID: " + productId);
//                URL url = new URL(BASE_URL + "/products/" + productId);
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("PUT");
//                conn.setRequestProperty("X-Api-Key", API_KEY);
//                conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
//                conn.setDoOutput(true);
//
//                try (OutputStream os = conn.getOutputStream();
//                     PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8), true)) {
//
//                    // Write JSON body part
//                    writer.append("--").append(BOUNDARY).append("\r\n");
//                    writer.append("Content-Disposition: form-data; name=\"body\"\r\n");
//                    writer.append("Content-Type: application/json\r\n\r\n");
//
//                    // Create JSON request body
//                    JSONObject json = new JSONObject();
//                    json.put("name", view.getNameInput());
//                    json.put("price", Long.parseLong(view.getPriceInput()));
//                    json.put("quantity", Integer.parseInt(view.getQuantityInput()));
//                    json.put("categoryId", getCategoryId(view.getSelectedCategory()));
//
//                    String selectedGenre = view.getSelectedGenre();
//                    if (!selectedGenre.isEmpty()) {
//                        json.put("genreId", getGenreId(selectedGenre));
//                    }
//
//                    log("Preparing update JSON data: " + json.toString());
//                    writer.append(json.toString());
//                    writer.append("\r\n");
//
//                    // Add file part if image is selected
//                    if (view.getSelectedImageFile() != null) {
//                        String formattedFileName = formatFileName(view.getSelectedImageFile().getName());
//                        log("Adding image file: " + formattedFileName);
//                        writer.append("--").append(BOUNDARY).append("\r\n");
//                        writer.append("Content-Disposition: form-data; name=\"file\"; filename=\"")
//                                .append(formattedFileName).append("\"\r\n");
//                        writer.append("Content-Type: ").append(getContentType(view.getSelectedImageFile())).append("\r\n\r\n");
//                        writer.flush();
//
//                        FileInputStream fileInputStream = new FileInputStream(view.getSelectedImageFile());
//                        byte[] buffer = new byte[4096];
//                        int bytesRead;
//                        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
//                            os.write(buffer, 0, bytesRead);
//                        }
//                        os.flush();
//                        fileInputStream.close();
//                        writer.append("\r\n");
//                    }
//
//                    writer.append("--").append(BOUNDARY).append("--").append("\r\n");
//                }
//
//                int responseCode = conn.getResponseCode();
//                log("Server response code for update: " + responseCode);
//
//                if (responseCode == HttpURLConnection.HTTP_OK) {
//                    log("Product updated successfully.");
//                    JOptionPane.showMessageDialog(view, "Product updated successfully!");
//                    view.clearInputFields();
//                    new RefreshListener().actionPerformed(null);
//                } else {
//                    logErrorFromConnection(conn);
//                }
//            } catch (Exception ex) {
//                log("Error updating product: " + ex.getMessage());
//                JOptionPane.showMessageDialog(view, "Error updating product: " + ex.getMessage());
//                ex.printStackTrace();
//            }
//        }
//    }
//


    class UpdateProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer productId = null;

            // Prioritaskan seleksi dari JTable
            int selectedRow = view.getSelectedTableRow();
            if (selectedRow != -1) {
                productId = view.getProductIdAtTableRow(selectedRow);
            } else {
                // Gunakan JList jika JTable tidak dipilih
                int selectedIndex = view.getSelectedProductIndex();
                if (selectedIndex != -1 && selectedIndex < productIds.size()) {
                    productId = productIds.get(selectedIndex).intValue();
                }
            }

            if (productId == null) {
                JOptionPane.showMessageDialog(view, "Please select a product to update!");
                return;
            }

            try {
                log("Starting to update product with ID: " + productId);
                URL url = new URL(BASE_URL + "/products/" + productId);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("PUT");
                conn.setRequestProperty("X-Api-Key", API_KEY);
                conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
                conn.setDoOutput(true);

                // JSON body and file upload logic as before
                // ...

                int responseCode = conn.getResponseCode();
                log("Server response code for update: " + responseCode);

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    log("Product updated successfully.");
                    JOptionPane.showMessageDialog(view, "Product updated successfully!");
                    view.clearInputFields();
                    new RefreshListener().actionPerformed(null);
                } else {
                    logErrorFromConnection(conn);
                }
            } catch (Exception ex) {
                log("Error updating product: " + ex.getMessage());
                JOptionPane.showMessageDialog(view, "Error updating product: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }


//    class DeleteProductListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            int selectedIndex = view.getSelectedProductIndex();
//            if (selectedIndex == -1 || selectedIndex >= productIds.size()) {
//                JOptionPane.showMessageDialog(view, "Please select a product to delete!");
//                return;
//            }
//
//            try {
//                Long productId = productIds.get(selectedIndex);
//                log("Starting to delete product with ID: " + productId);
//                URL url = new URL(BASE_URL + "/products/" + productId);
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("DELETE");
//                conn.setRequestProperty("X-Api-Key", API_KEY);
//
//                int responseCode = conn.getResponseCode();
//                if (responseCode == HttpURLConnection.HTTP_OK) {
//                    log("Product deleted successfully.");
//                    JOptionPane.showMessageDialog(view, "Product deleted successfully!");
//                    view.clearInputFields();
//                    new RefreshListener().actionPerformed(null);
//                } else {
//                    logErrorFromConnection(conn);
//                }
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(view, "Error deleting product: " + ex.getMessage());
//                ex.printStackTrace();
//            }
//        }
//
//        private void logErrorFromConnection(HttpURLConnection conn) {
//        }
//    }

    class DeleteProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Integer productId = null;

            // Prioritaskan seleksi dari JTable
            int selectedRow = view.getSelectedTableRow();
            if (selectedRow != -1) {
                productId = view.getProductIdAtTableRow(selectedRow);
            } else {
                // Gunakan JList jika JTable tidak dipilih
                int selectedIndex = view.getSelectedProductIndex();
                if (selectedIndex != -1 && selectedIndex < productIds.size()) {
                    productId = productIds.get(selectedIndex).intValue();
                }
            }

            if (productId == null) {
                JOptionPane.showMessageDialog(view, "Please select a product to delete!");
                return;
            }

            int confirmation = JOptionPane.showConfirmDialog(view,
                    "Are you sure you want to delete this product?",
                    "Delete Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                try {
                    log("Starting to delete product with ID: " + productId);
                    URL url = new URL(BASE_URL + "/products/" + productId);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("DELETE");
                    conn.setRequestProperty("X-Api-Key", API_KEY);

                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        log("Product deleted successfully.");
                        JOptionPane.showMessageDialog(view, "Product deleted successfully!");
                        view.clearInputFields();
                        new RefreshListener().actionPerformed(null);
                    } else {
                        logErrorFromConnection(conn);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Error deleting product: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }


    private Long getCategoryId(String categoryName) throws Exception {
        String response = fetchFromApi("/categories");
        JSONArray categories = parseJsonResponse(response);

        for (int i = 0; i < categories.length(); i++) {
            JSONObject category = categories.getJSONObject(i);
            if (category.getString("name").equals(categoryName)) {
                return category.getLong("id");
            }
        }
        throw new Exception("Category not found: " + categoryName);
    }

    private Long getGenreId(String genreName) throws Exception {
        if (genreName == null || genreName.trim().isEmpty()) {
            return null;
        }

        String response = fetchFromApi("/genres");
        JSONArray genres = parseJsonResponse(response);

        for (int i = 0; i < genres.length(); i++) {
            JSONObject genre = genres.getJSONObject(i);
            if (genre.getString("name").equals(genreName)) {
                return genre.getLong("id");
            }
        }
        throw new Exception("Genre not found: " + genreName);
    }

    private void logErrorFromConnection(HttpURLConnection conn) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        log("API Error Response: " + response);
        throw new Exception("Server returned code: " + conn.getResponseCode() + "\nResponse: " + response.toString());
    }

    private String getContentType(File file) {
        String name = file.getName().toLowerCase();
        if (name.endsWith(".jpg") || name.endsWith(".jpeg")) return "image/jpeg";
        if (name.endsWith(".png")) return "image/png";
        if (name.endsWith(".svg")) return "image/svg+xml";
        return "application/octet-stream";
    }

    private String formatFileName(String originalFileName) {
        String cleanName = originalFileName.replaceAll("[^a-zA-Z0-9.]", "");

        String extension = "";
        int i = cleanName.lastIndexOf('.');
        if (i > 0) {
            extension = cleanName.substring(i);
            cleanName = cleanName.substring(0, i);
        }

        if (cleanName.length() > 20) {
            cleanName = cleanName.substring(0, 20);
        }

        String timestamp = String.valueOf(System.currentTimeMillis());
        String shortTimestamp = timestamp.substring(timestamp.length() - 4);

        return "Berkas_" + cleanName + "_" + shortTimestamp + extension;
    }



}