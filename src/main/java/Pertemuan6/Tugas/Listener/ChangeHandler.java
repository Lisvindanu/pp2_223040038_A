package main.java.Pertemuan6.Tugas.Listener;

import main.java.Pertemuan6.Tugas.GUI.FormPanel;
import main.java.Pertemuan6.Tugas.Interface.MChangeListener;

import javax.swing.event.ChangeEvent;

public class ChangeHandler implements MChangeListener {
    private FormPanel formPanel;

    public ChangeHandler(FormPanel formPanel) {
        this.formPanel = formPanel;
    }

    @Override
    public void handleChange(ChangeEvent e) {
        int sliderValue = formPanel.slider.getValue();
        System.out.println("Nilai Dipilih : " + sliderValue + " Juta");
    }
}
