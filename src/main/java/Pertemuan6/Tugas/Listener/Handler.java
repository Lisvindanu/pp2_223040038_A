package main.java.Pertemuan6.Tugas.Listener;

import main.java.Pertemuan6.Tugas.GUI.FormPanel;
import main.java.Pertemuan6.Tugas.GUI.MainFrame;
import main.java.Pertemuan6.Tugas.Interface.MActionListener;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.StringTokenizer;


public class Handler implements MActionListener {
    private JPanel mainPanel;
    private JMenuItem loaditem;
    private JMenuItem saveitem;
    private JMenuItem exititem;
    private JMenuItem formItem;
    private FormPanel formPanel;
//    TableServices tableServices;
    private DefaultTableModel tableModel;
    private MainFrame mainFrame;
//    private JPanel curPanel;

    public Handler(JPanel mainPanel, JMenuItem loaditem, JMenuItem saveitem,
                   JMenuItem exititem, JMenuItem formItem, DefaultTableModel tableModel, FormPanel formPanel, MainFrame mainFrame) {
        this.mainPanel = mainPanel;
        this.loaditem = loaditem;
        this.saveitem = saveitem;
        this.exititem = exititem;
        this.formItem = formItem;
        this.tableModel = tableModel;
        this.formPanel = formPanel;
        this.mainFrame = mainFrame;

//        this.tableServices = tableServices;


        this.mainPanel.setLayout(new MigLayout("wrap 1", "[grow]", "[grow]"));
        this.mainPanel.add(new JPanel(), "grow");
    }

    @Override
    public void handleAction(ActionEvent e) {
//        mainPanel.removeAll();
//        mainPanel.setLayout(new MigLayout("wrap 1", "[grow]", "[grow]"));

        if (e.getSource() == loaditem) {
           loadDataFromFile();
        } else if (e.getSource() == saveitem) {
          SwingUtilities.invokeLater(()-> {
              System.out.println("Attempting to save data. Current row count: " + tableModel.getRowCount());
              System.out.println("Model identity during save: " + System.identityHashCode(tableModel));

              if(tableModel.getRowCount()> 0) {
                  saveDataToFile();
                  JOptionPane.showMessageDialog(mainPanel, "Data has been saved");
              }else {
                  JOptionPane.showMessageDialog(mainPanel, "Please enter data first.");
              }
          });
        } else if (e.getSource() == exititem) {
            System.exit(0);
        } else if (e.getSource() == formItem) {
            mainPanel.removeAll();
            mainPanel.add(formPanel, "grow");
            mainFrame.showFormPanel();
            mainPanel.revalidate();
            mainPanel.repaint();
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public void saveDataToFile() {
        if(tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(mainPanel, "Please enter data first.");
            return;
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                StringBuilder row = new StringBuilder();
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    row.append(tableModel.getValueAt(i, j).toString());
                    if (j < tableModel.getColumnCount() - 1) {
                        row.append(", ");
                    }
                }
                writer.write(row.toString().replaceAll(", $", ""));
                writer.newLine();
            }
        }catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan." + e.getMessage());
        }
    }

    public void loadDataFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih file untuk di load");
        int userSelection = fileChooser.showOpenDialog(mainPanel);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                tableModel.setRowCount(0);
                while ((line = reader.readLine()) != null) {
                    StringTokenizer tokenizer = new StringTokenizer(line, ",");
                    Object[] row = new Object[tableModel.getColumnCount()];
                    int index = 0;
                    while (tokenizer.hasMoreTokens()) {
                        row[index] = tokenizer.nextToken().trim();
                        System.out.println(row[index]);
                        index++;
                    }
                    tableModel.addRow(row);
                }
            }catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Load." + e.getMessage());
            }
        }
        }
}