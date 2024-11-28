package main.java.pertemuan7.Tugas.dao;

import main.java.pertemuan7.Tugas.model.ContactInfo;

import java.sql.SQLException;
import java.util.List;

public interface ContactInfoDaoInterface {
    int addContactInfo(ContactInfo ContactInfo) throws SQLException;
    List<ContactInfo> getAllContactInfo()  throws SQLException;
    ContactInfo getContactInfoById(int id) throws SQLException;
    int updateContactInfo(ContactInfo ContactInfo) throws SQLException;
    int deleteContactInfo(int id) throws SQLException;
}
