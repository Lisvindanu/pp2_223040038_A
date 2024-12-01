package main.java.Pertemuan6.Tugas.Listener;

import main.java.Pertemuan6.Tugas.GUI.FormPanel;
import main.java.Pertemuan6.Tugas.Interface.MItemListener;

import java.awt.event.ItemEvent;

public class itemHandler implements MItemListener {

    private FormPanel formPanel;
    public itemHandler(FormPanel formPanel) {
        this.formPanel = formPanel;
    }

    @Override
    public void handleItemChange(ItemEvent e) {
            if (e.getSource() == formPanel.radioPria || e.getSource() == formPanel.radioWanita) {
                System.out.println("Gender Dipilih : " + (formPanel.radioPria.isSelected() ? "Pria" : "Wanita"));
            } else if (e.getSource() == formPanel.checkBox) {
                System.out.println("Warga Negara Asing : " + (formPanel.checkBox.isSelected() ? "Ya" : "Tidak"));

            }
    }
}
