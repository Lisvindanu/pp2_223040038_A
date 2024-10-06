package Pertemuan3.Latihan5.Interface;

import javax.swing.*;
import java.awt.event.ActionEvent;

public interface MActionListener {
    void handlerButtonSaveAction(ActionEvent e,
                                 JTextField kotakNama,
                                 JTextField kotakNoHp,
                                 JRadioButton radio1,
                                 JRadioButton radio2,
                                 JCheckBox checkBox,
                                 JList<String> listJenisTabungan,
                                 JSlider slider,
                                 JTextArea areaBiodata,
                                 JSpinner spinner);

    void handleResetMenuAction(ActionEvent e,
                               JTextField kotakNama,
                               JTextField kotakNoHp,
                               JRadioButton radio1,
                               JRadioButton radio2,
                               JCheckBox checkBox,
                               JList<String> listJenisTabungan,
                               JTextArea areaBiodata);

    void handleExitMenuAction(ActionEvent e);
}
