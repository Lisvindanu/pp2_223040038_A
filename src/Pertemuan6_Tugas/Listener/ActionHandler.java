package Pertemuan6_Tugas.Listener;

import Pertemuan6_Tugas.GUI.FormPanel;
import Pertemuan6_Tugas.Interface.MActionListener;
import Pertemuan6_Tugas.Service.TableServices;

import java.awt.event.ActionEvent;


public class ActionHandler implements MActionListener {
    private FormPanel formPanel;
    private TableServices tableServices;

    public ActionHandler(FormPanel formPanel, TableServices tableServices) {
        this.formPanel = formPanel;
        this.tableServices = tableServices;
    }


    @Override
    public void handleAction(ActionEvent e) {
        if (e.getSource() == formPanel.addButton) {
            tableServices.tambahDataKeTabel();
            formPanel.getSlider().setValue(0);
        } else if (e.getSource() == formPanel.removeButton) {
            tableServices.hapusDataDariTabel();
        }
    }
}
