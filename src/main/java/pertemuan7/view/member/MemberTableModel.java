package main.java.pertemuan7.view.member;

import main.java.pertemuan7.model.Member;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MemberTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama", "Jenis Member"};
    private List<Member> data;

    public MemberTableModel(List<Member> data) {
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
        Member rowItem = data.get(row);
        String val = "";
        switch (col) {
            case 0:
                val = rowItem.getNama();
                break;
                case 1:
                    val = rowItem.getJenisMember().getNama();
                    break;
        }
        return val;
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(Member val) {
        data.add(val);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

}
