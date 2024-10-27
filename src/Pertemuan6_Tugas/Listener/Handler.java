package Pertemuan6_Tugas.Listener;

import Pertemuan6_Tugas.GUI.FormPanel;
import Pertemuan6_Tugas.Interface.MActionListener;
import Pertemuan6_Tugas.Service.TableServices;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class Handler implements MActionListener {
    private JPanel mainPanel;
    private JMenuItem loaditem;
    private JMenuItem saveitem;
    private JMenuItem exititem;
    private JMenuItem formItem;
    TableServices tableServices;
    private JPanel curPanel;

    public Handler(JPanel mainPanel, JMenuItem loaditem, JMenuItem saveitem, JMenuItem exititem, JMenuItem formItem, TableServices tableServices) {
        this.mainPanel = mainPanel;
        this.loaditem = loaditem;
        this.saveitem = saveitem;
        this.exititem = exititem;
        this.formItem = formItem;
        this.tableServices = tableServices;


        this.mainPanel.setLayout(new MigLayout("wrap 1", "[grow]", "[grow]"));
        this.curPanel = new JPanel();
        mainPanel.add(curPanel, "grow");
    }

    @Override
    public void handleAction(ActionEvent e) {
        mainPanel.removeAll();

        if (e.getSource() == loaditem) {
            curPanel = new JPanel();
            curPanel.add(new JLabel("Load Page"));
        } else if (e.getSource() == saveitem) {
            if(tableServices.getTableModel().getRowCount() > 0) {
                tableServices.saveDataToFile();
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Isi Data Terlebih dahulu");
            }
        } else if (e.getSource() == exititem) {
            System.exit(0);
        } else if (e.getSource() == formItem) {
           curPanel = new FormPanel();
        }

        mainPanel.add(curPanel, "grow");
        mainPanel.revalidate();
        mainPanel.repaint();
    }




}
