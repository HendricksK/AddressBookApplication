package com.s212037943.kurvinhendricks.addressbookapplication.repository;

import com.s212037943.kurvinhendricks.addressbookapplication.domain.Contact;

import java.util.List;

/**
 * Created by Kurvin Hendricks on 18/08/2014.
 */
public interface DataSourceDAO {

    public void createContact(Contact contact);
    public void updateContact(Contact contact);
    public Contact findContactByID(int id);
    public void deleteContact(Contact contact);
    public Contact getContact();
    public List<Contact> getContactList();
    public int getCursor();

}
