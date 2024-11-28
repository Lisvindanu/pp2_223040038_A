package main.java.pertemuan7.view.jenismember;

import main.java.pertemuan7.model.JenisMember;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class JenisMemberTableModel extends AbstractTableModel {
    private String[] columnNames = {"nama"};
    private List<JenisMember> data;

    public JenisMemberTableModel(List<JenisMember> data) {
        this.data = data;
    }

    public int getColumnCount() {
        return columnNames.length;
    }
    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        JenisMember rowItem = data.get(row);
        String val = "";
        switch (col) {
            case 0:
                val = rowItem.getNama();
                break;
        }
        return val;
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(JenisMember val) {
        data.add(val);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}
