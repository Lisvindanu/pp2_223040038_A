package main.java.Pertemuan3.Latihan5.GUI;

import main.java.Pertemuan3.Latihan5.Services.FormHandler;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

    public class MFrame extends JFrame implements ActionListener, ItemListener, ChangeListener {
        private JPanel controlPanel = new JPanel();
        private JPanel panel = new JPanel();
        private GridBagLayout layout = new GridBagLayout();
        private GridBagConstraints gbc = new GridBagConstraints();

        private JTextArea areaBiodata;
        private JButton tombolSimpan;
        private JMenuBar menuBar;
        private FormHandler formHandler;
        private FormPanel formPanel;

        public MFrame() {
            this.setTitle("Pertemuan 3");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(600, 800);
            this.setLayout(layout);

            formHandler = new FormHandler();
            formPanel = new FormPanel();

            //gbc untuk layout
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(10,10,10,10);
            gbc.weightx = 1;
            gbc.weighty = 1;

            //form panel
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 2;
            add(formPanel, gbc);

            formPanel.getCheckBox().addItemListener(this);

            // JTextArea field menampung nilai

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridwidth = 0;
            gbc.gridheight = 1;
            gbc.weightx = 0.5;
            gbc.weighty = 0.1;
            gbc.insets = new Insets(320,10,0,200);
            gbc.fill = GridBagConstraints.BOTH;
            areaBiodata = new JTextArea();
            areaBiodata.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(areaBiodata);
            scrollPane.setPreferredSize(new Dimension(400, 100));
            add(scrollPane, gbc);

            // Button simpan
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.insets = new Insets(0,10,200,200);
            gbc.fill = GridBagConstraints.NONE;
            tombolSimpan = new JButton("Simpan");
            add(tombolSimpan, gbc);

            //menu
            menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("File");
            JMenuItem exitMenuItem = new JMenuItem("Exit");
            JMenuItem resetMenu = new JMenuItem("Reset");

            menuBar.add(fileMenu);
            fileMenu.add(resetMenu);
            fileMenu.add(exitMenuItem);
            this.setJMenuBar(menuBar);

            exitMenuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    formHandler.handleExitMenuAction(e);
                }
            });

            resetMenu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    formHandler.handleResetMenuAction(e,
                            formPanel.getKotakNama(),
                            formPanel.getKotakNoHp(),
                            formPanel.getRadio1(),
                            formPanel.getRadio2(),
                            formPanel.getCheckBox(),
                            formPanel.getListJenisTabungan(),
                            areaBiodata);
                }
            });

            tombolSimpan.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    formHandler.handlerButtonSaveAction(e,
                            formPanel.getKotakNama(),
                            formPanel.getKotakNoHp(),
                            formPanel.getRadio1(),
                            formPanel.getRadio2(),
                            formPanel.getCheckBox(),
                            formPanel.getListJenisTabungan(),
                            formPanel.getSlider(),
                            areaBiodata,
                            formPanel.getSpinner());
                }
            });

//            add(headerLabel);

            setVisible(true);

        }

        @Override
        public void stateChanged(ChangeEvent e) {
            formHandler.handleSliderChange(e);
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            if(e.getSource() == formPanel.getCheckBox()){
                formHandler.handleItemStateChange(e);
            }

        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


