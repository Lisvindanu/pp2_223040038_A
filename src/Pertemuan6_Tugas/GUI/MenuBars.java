package Pertemuan6_Tugas.GUI;

import Pertemuan6_Tugas.Listener.Handler;
import Pertemuan6_Tugas.Service.TableServices;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPanel mainPanel;
    private Handler handler;
    private TableServices tableServices;

        public MenuBars(JPanel mainPanel, FormPanel formPanel, TableServices tableServices) {
            this.mainPanel = mainPanel;
            this.tableServices = tableServices;

            menuBar = new JMenuBar();
            fileMenu = new JMenu("File");
            editMenu = new JMenu("Edit");
            helpMenu = new JMenu("Help");
            loaditem = new JMenuItem("Load");
            saveitem = new JMenuItem("Save");
            exititem = new JMenuItem("Exit");
            formItem = new JMenuItem("Open Form");


            fileMenu.setMnemonic(KeyEvent.VK_F); // ALT + F for file
            editMenu.setMnemonic(KeyEvent.VK_E); // ALT + E for edit
            helpMenu.setMnemonic(KeyEvent.VK_H); // ALT + H for help
            loaditem.setMnemonic(KeyEvent.VK_L); // l for load
            saveitem.setMnemonic(KeyEvent.VK_S); // s for save
            exititem.setMnemonic(KeyEvent.VK_E); // e for load



            handler = new Handler(mainPanel, loaditem, saveitem, exititem, formItem, tableServices);

            loaditem.addActionListener(e -> handler.handleAction(e));
            saveitem.addActionListener(e -> {
                handler.handleAction(e);
            });
            exititem.addActionListener(e -> handler.handleAction(e));
            formItem.addActionListener(e -> handler.handleAction(e));

            fileMenu.add(loaditem);
            fileMenu.add(saveitem);
            fileMenu.add(exititem);
            fileMenu.addSeparator();
            fileMenu.add(formItem);


            menuBar.add(fileMenu);
            menuBar.add(editMenu);
            menuBar.add(helpMenu);


        }

    public JMenuBar dapetinMenuBar() {
        return menuBar;
    }


    }





