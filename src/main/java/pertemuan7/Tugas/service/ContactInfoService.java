package main.java.pertemuan7.Tugas.service;

import main.java.pertemuan7.Tugas.dao.ContactInfoDaoInterface;
import main.java.pertemuan7.Tugas.model.ContactInfo;

import java.sql.SQLException;
import java.util.List;

public class ContactInfoService {
    private ContactInfoDaoInterface contactInfoDao;

    public ContactInfoService(ContactInfoDaoInterface contactInfoDao) {
        this.contactInfoDao = contactInfoDao;
    }

    public int addContactInfo(ContactInfo contactInfo) throws SQLException {
        return contactInfoDao.addContactInfo(contactInfo);
    }

    public List<ContactInfo> getAllContactInfo() throws SQLException {
        return contactInfoDao.getAllContactInfo();
    }

    public ContactInfo getContactInfoById(int id) throws SQLException {
        return contactInfoDao.getContactInfoById(id);
    }

    public int updateContactInfo(ContactInfo contactInfo) throws SQLException {
        return contactInfoDao.updateContactInfo(contactInfo);
    }

    public int deleteContactInfo(int id) throws SQLException {
        return contactInfoDao.deleteContactInfo(id);
    }
}
