package main.java.pertemuan8.view.jenismember;

import main.java.pertemuan8.model.JenisMember;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class JenisMemberTableModel extends AbstractTableModel {
    private String[] colName = {"Nama"};
    private List<JenisMember> data;

    public JenisMemberTableModel(List<JenisMember> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
       return colName.length;
    }

    public String getColumnName(int col) {
        return colName[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        JenisMember rowItem = data.get(rowIndex);
        String val = "";
        switch (columnIndex) {
            case 0:
                val = rowItem.getNama();
                break;
        }
        return val;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void add(JenisMember value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}
