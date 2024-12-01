package main.java.pertemuan8.Latihan.view.member;

import main.java.pertemuan8.Latihan.model.Member;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MemberTableModel extends AbstractTableModel {
    private String[] columnNames = {"nama", "jenis member"};
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
        String value = "";
        switch (col)
        {
            case 0:
                value = rowItem.getNama();
                break;
            case 1 :
                value = rowItem.getJenisMember().getNama();
        }
        return value;
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(Member value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void remove(int rowIndex) {
        data.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }


    public void update(Member value, int rowIndex) {
        data.set(rowIndex, value);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
    public Member getMemberAt(int rowIndex) {
        return data.get(rowIndex);
    }
}
