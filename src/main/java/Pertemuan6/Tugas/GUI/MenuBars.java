package main.java.Pertemuan6.Tugas.GUI;

import main.java.Pertemuan6.Tugas.Listener.Handler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;

public class MenuBars {


    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu helpMenu;
    private JMenuItem loaditem;
    private JMenuItem saveitem;
    private JMenuItem exititem;
    private JMenuItem formItem;
    private JMenuItem backToMainMenuItem;
    private JPanel mainPanel;
    private Handler handler;
//    private TableServices tableServices;
    private MainFrame mainFrame;

    public MenuBars(MainFrame mainFrame, JPanel mainPanel, FormPanel formPanel, DefaultTableModel tableModel) {
        this.mainPanel = mainPanel;
        this.mainFrame = mainFrame;
//        this.tableServices = tableServices;

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        helpMenu = new JMenu("Help");
        loaditem = new JMenuItem("Load");
        saveitem = new JMenuItem("Save");
        exititem = new JMenuItem("Exit");
        formItem = new JMenuItem("Open Form");
        backToMainMenuItem = new JMenuItem("Back to Main Menu");


        fileMenu.setMnemonic(KeyEvent.VK_F); // ALT + F for file
        editMenu.setMnemonic(KeyEvent.VK_E); // ALT + E for edit
        helpMenu.setMnemonic(KeyEvent.VK_H); // ALT + H for help
        loaditem.setMnemonic(KeyEvent.VK_L); // l for load
        saveitem.setMnemonic(KeyEvent.VK_S); // s for save
        exititem.setMnemonic(KeyEvent.VK_E); // e for load

        System.out.println("Model identity in MenuBar: " + System.identityHashCode(tableModel));


        handler = new Handler(mainPanel, loaditem, saveitem, exititem, formItem, tableModel, formPanel, mainFrame);

        loaditem.addActionListener(handler::handleAction);
        saveitem.addActionListener(handler::handleAction);
        exititem.addActionListener(handler::handleAction);
        formItem.addActionListener(handler::handleAction);
        backToMainMenuItem.addActionListener(e -> {
                mainPanel.removeAll();
                mainPanel.add(mainFrame.createLoadPanel(), "grow");
                mainPanel.revalidate();
                mainPanel.repaint();
                mainFrame.showMainMenu();
        });

        fileMenu.add(loaditem);
        fileMenu.add(saveitem);
        fileMenu.addSeparator();

        fileMenu.add(exititem);
        fileMenu.addSeparator();
        fileMenu.add(formItem);
        fileMenu.addSeparator();
        fileMenu.add(backToMainMenuItem);


        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
    }


    public JMenuBar dapetinMenuBar() {
        return menuBar;
    }
    public JMenuItem getLoadItem() {
        return loaditem;
    }

    public JMenuItem getSaveItem() {
        return saveitem;
    }

    public JMenuItem getExitItem() {
        return exititem;
    }

    public JMenuItem getFormItem() {
        return formItem;
    }

    public JMenuItem getBackToMainMenuItem() {
        return backToMainMenuItem; // Tambahkan getter untuk item baru
    }

    public void setSaveLoadVisible(boolean visible) {
        saveitem.setVisible(visible);
        loaditem.setVisible(visible);
        System.out.println("Save item visible: " + saveitem.isVisible());
        System.out.println("Load item visible: " + loaditem.isVisible());
    }
    public void setVisibility(boolean visible) {
        backToMainMenuItem.setVisible(visible);
    }
}