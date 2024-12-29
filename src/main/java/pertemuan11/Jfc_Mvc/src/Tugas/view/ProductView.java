package main.java.pertemuan11.Jfc_Mvc.src.Tugas.view;

import main.java.pertemuan11.Jfc_Mvc.src.Tugas.util.SSLUtils;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

public class ProductView extends JFrame {
    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> genreComboBox;
    private JLabel imageLabel;
    private JButton selectImageButton;
    private JLabel imageFileLabel;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JList<String> productList;
    private DefaultListModel<String> listModel;
    private File selectedImageFile;

    private JTable productTable;
    private DefaultTableModel tableModel;

    private static final int IMAGE_WIDTH = 200;
    private static final int IMAGE_HEIGHT = 200;

    // Colors for Nintendo Switch theme
    private final Color PRIMARY_COLOR = new Color(0, 161, 230);
    private final Color SECONDARY_COLOR = new Color(51, 51, 51);
    private final Color BACKGROUND_COLOR = new Color(242, 242, 242);

    public ProductView() {
        initializeFrame();
        initializeComponents();
        layoutComponents();
        setupEventHandlers();
    }

    private void initializeFrame() {

        setTitle("RetroGame eShop Product Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1200, 800));
        getContentPane().setBackground(BACKGROUND_COLOR);

        try {
            Image icon = ImageIO.read(Objects.requireNonNull(getClass().getResource("../resources/iconku.png")));

            setIconImage(icon);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Gagal dapetin icon");
            System.out.println(getClass().getResource("iconku.png"));

        }
    }

    private void initializeComponents() {
        // Initialize text fields
        nameField = createStyledTextField();
        priceField = createStyledTextField();
        quantityField = createStyledTextField();

        // Initialize combo boxes
        categoryComboBox = createStyledComboBox();
        genreComboBox = createStyledComboBox();

        // Initialize image components
        imageLabel = new JLabel("No Image", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
        imageLabel.setBorder(BorderFactory.createLineBorder(SECONDARY_COLOR));

        selectImageButton = createStyledButton("Select Image", SECONDARY_COLOR);
        imageFileLabel = new JLabel("No file selected");

        // Initialize list
        listModel = new DefaultListModel<>();
        productList = new JList<>(listModel);
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productList.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        // Initialize buttons
        addButton = createStyledButton("Add Product", PRIMARY_COLOR);
        updateButton = createStyledButton("Update", PRIMARY_COLOR);
        deleteButton = createStyledButton("Delete", PRIMARY_COLOR);
        refreshButton = createStyledButton("Refresh", SECONDARY_COLOR);

        // Initialize table
        String[] columnNames = {"ID", "Name", "Price", "Quantity", "Category", "Genre", "Image"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Non-editable
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 6) { // Kolom ke-7 adalah gambar
                    return ImageIcon.class;
                }
                return Object.class;
            }
        };
        productTable = new JTable(tableModel);
        productTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        productTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        productTable.setRowHeight(100); // Atur tinggi baris agar muat gambar
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productTable.setAutoCreateRowSorter(true);

        // Set column widths
        int[] columnWidths = {50, 200, 100, 80, 75, 75, 150};
        for (int i = 0; i < columnWidths.length; i++) {
            productTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }
    }

    private void layoutComponents() {
        // Main panel using MigLayout
        JPanel mainPanel = new JPanel(new MigLayout("fill, insets 20", "[45%][55%]", "[grow]"));
        mainPanel.setBackground(BACKGROUND_COLOR);

        // Left panel - Form
        JPanel leftPanel = createFormPanel();

        // Right panel - List and Table
        JPanel rightPanel = createListAndTablePanel();

        // Add panels to main panel
        mainPanel.add(leftPanel, "grow");
        mainPanel.add(rightPanel, "grow");

        // Add main panel to frame
        setContentPane(mainPanel);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new MigLayout("fillx, insets 20", "[][grow]", "[]10[]"));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY_COLOR),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Add form components
        panel.add(createLabel("Name:"), "right");
        panel.add(nameField, "growx, wrap");

        panel.add(createLabel("Price:"), "right");
        panel.add(priceField, "growx, wrap");

        panel.add(createLabel("Quantity:"), "right");
        panel.add(quantityField, "growx, wrap");

        panel.add(createLabel("Category:"), "right");
        panel.add(categoryComboBox, "growx, wrap");

        panel.add(createLabel("Genre:"), "right");
        panel.add(genreComboBox, "growx, wrap");

        // Image section
        panel.add(createLabel("Image:"), "right");
        JPanel imagePanel = new JPanel(new MigLayout("fillx", "[]rel[]", "[]10[]"));
        imagePanel.setBackground(Color.WHITE);
        imagePanel.add(selectImageButton, "split 2");
        imagePanel.add(imageFileLabel, "growx, wrap");
        imagePanel.add(imageLabel, "span 2, alignx center, width 200!, height 200!");
        panel.add(imagePanel, "span, growx, wrap");

        // Buttons
        JPanel buttonPanel = new JPanel(new MigLayout("insets 0", "[grow][grow][grow]", "[]"));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(addButton, "width 100!");
        buttonPanel.add(updateButton, "width 100!");
        buttonPanel.add(deleteButton, "width 100!");
        panel.add(buttonPanel, "span, growx, gaptop 10");

        return panel;
    }

    private JPanel createListAndTablePanel() {
        JPanel panel = new JPanel(new MigLayout("fill, insets 20", "[grow]", "[40%][60%][]"));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY_COLOR),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Add list with scroll pane
        JScrollPane listScrollPane = new JScrollPane(productList);
        listScrollPane.getViewport().setBackground(Color.WHITE);
        panel.add(listScrollPane, "grow, wrap");

        // Add table with scroll pane
        JScrollPane tableScrollPane = new JScrollPane(productTable);
        tableScrollPane.getViewport().setBackground(Color.WHITE);
        panel.add(tableScrollPane, "grow, wrap");

        // Add refresh button
        panel.add(refreshButton, "right, width 100!");

        return panel;
    }

    // Helper methods for styling components
    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY_COLOR),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return field;
    }

    private JComboBox<String> createStyledComboBox() {
        JComboBox<String> box = new JComboBox<>();
        box.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        box.setBackground(Color.WHITE);
        return box;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return button;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(SECONDARY_COLOR);
        return label;
    }

    private void setupEventHandlers() {
        selectImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                public boolean accept(File f) {
                    if (f.isDirectory()) return true;
                    String name = f.getName().toLowerCase();
                    return name.endsWith(".jpg") || name.endsWith(".jpeg")
                            || name.endsWith(".png") || name.endsWith(".svg");
                }
                public String getDescription() {
                    return "Image files (*.jpg, *.jpeg, *.png, *.svg)";
                }
            });

            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedImageFile = fileChooser.getSelectedFile();
                imageFileLabel.setText(selectedImageFile.getName());
                try {
                    BufferedImage img = ImageIO.read(selectedImageFile);
                    if (img != null) {
                        Image scaledImage = img.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
                        imageLabel.setIcon(new ImageIcon(scaledImage));
                        imageLabel.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // Interface methods required by the controller
    public String getNameInput() { return nameField.getText(); }
    public String getPriceInput() { return priceField.getText(); }
    public String getQuantityInput() { return quantityField.getText(); }
    public String getSelectedCategory() { return (String) categoryComboBox.getSelectedItem(); }
    public String getSelectedGenre() { return (String) genreComboBox.getSelectedItem(); }
    public File getSelectedImageFile() { return selectedImageFile; }
    public int getSelectedProductIndex() { return productList.getSelectedIndex(); }

    public void setNameField(String name) { nameField.setText(name); }
    public void setPriceField(String price) { priceField.setText(price); }
    public void setQuantityField(String quantity) { quantityField.setText(quantity); }
    public void setCategorySelection(String category) { categoryComboBox.setSelectedItem(category); }
    public void setGenreSelection(String genre) { genreComboBox.setSelectedItem(genre); }

    public void setCategories(String[] categories) {
        categoryComboBox.removeAllItems();
        for (String category : categories) {
            categoryComboBox.addItem(category);
        }
    }

    public void setGenres(String[] genres) {
        genreComboBox.removeAllItems();
        for (String genre : genres) {
            genreComboBox.addItem(genre);
        }
    }

    public void displayImage(String imageUrl) {
        try {
            if (imageUrl == null || imageUrl.trim().isEmpty()) {
                throw new Exception("Image URL is null or empty");
            }

            // Pastikan path tidak menambahkan domain redundan
            if (!imageUrl.startsWith("http")) {
                if (imageUrl.startsWith("/")) {
                    imageUrl = "https://virtual-realm.my.id" + imageUrl; // Path absolut
                } else {
                    imageUrl = "https://virtual-realm.my.id/" + imageUrl; // Path relatif
                }
            }

            BufferedImage originalImage = SSLUtils.loadImageSecurely(imageUrl);
            if (originalImage != null) {
                Image scaledImage = originalImage.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
                imageLabel.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            imageLabel.setIcon(null);
            imageLabel.setText("No Image Available");
        }
    }


    public void setProductList(String[] products) {
        listModel.clear();
        for (String product : products) {
            listModel.addElement(product);
        }
    }

    public void clearInputFields() {
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
        selectedImageFile = null;
        imageFileLabel.setText("No file selected");
        imageLabel.setIcon(null);
        imageLabel.setText("No Image Available");
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
    }

    // Listener methods that match the controller's expectations
    public void addAddProductListener(ActionListener listener) { addButton.addActionListener(listener); }
    public void addUpdateProductListener(ActionListener listener) { updateButton.addActionListener(listener); }
    public void addDeleteProductListener(ActionListener listener) { deleteButton.addActionListener(listener); }
    public void addRefreshListener(ActionListener listener) { refreshButton.addActionListener(listener); }
    public void addListSelectionListener(ListSelectionListener listener) {
        productList.addListSelectionListener(listener);
    }

    public void setProductTableData(Object[][] data) {
        tableModel.setRowCount(0);
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    public void addTableSelectionListener(ListSelectionListener listener) {
        productTable.getSelectionModel().addListSelectionListener(listener);
    }

    public int getSelectedTableRow() {
        return productTable.getSelectedRow();
    }

    public Integer getProductIdAtTableRow(int row) {
        if (row >= 0 && row < tableModel.getRowCount()) {
            Object value = tableModel.getValueAt(row, 0); // Ambil ID dari kolom pertama
            if (value instanceof Integer) {
                return (Integer) value;
            } else if (value instanceof Long) {
                return ((Long) value).intValue(); // Konversi Long ke int jika diperlukan
            }
        }
        return null; // Jika baris tidak valid
    }


    public void clearTableSelection() {
        productTable.clearSelection();
    }

    public void clearListSelection() {
        productList.clearSelection();
    }

}