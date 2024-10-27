package Pertemuan6_Tugas.Listener;

import Pertemuan6_Tugas.GUI.FormPanel;
import Pertemuan6_Tugas.Interface.MListSectionListener;

import javax.swing.event.ListSelectionEvent;

public class ListSelectionHandler implements MListSectionListener {
    private FormPanel formPanel;

    public ListSelectionHandler(FormPanel formPanel) {
        this.formPanel = formPanel;
    }

    @Override
    public void handleListSelection(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {
            String selectedTabungan = formPanel.listJenisTabungan.getSelectedValue();
            System.out.println("Tabungan Dipilih : " + selectedTabungan);
        }
    }
}
