package com.mthree.academy.c458.vrishti.jco.exercises.address_book.dao;

import com.mthree.academy.c458.vrishti.jco.exercises.address_book.dto.Address;

import java.util.List;

/**
 * Declaring what operations the DAO must support
 * This is like the Repository Layer it seems.
 */
public interface AddressBookDao {

    /**
     * Add an address to the address book
     * @param address The address to add.
     * @return true if the address was added, otherwise false.
     */
    public boolean addAddress(Address address);

    /**
     * Remove an address from the address book.
     * Remove by ID, since last name should not be able to uniquely identify an address to delete.
     * @param id The ID of the address to remove.
     * @return true if the address was removed, otherwise false.
     */
    public boolean removeAddress(long id);

    /**
     * @return The number of addresses in the address book.
     */
    public int countAddresses();

    /**
     * @return A list of the addresses in the address book.
     */
    public List<Address> getAllAddresses();

    /**
     * @param lastName The last name to search by
     * @return A list of addresses in the address book with a matching last name.
     */
    public List<Address> getAllAddressesByLastName(String lastName);

}
