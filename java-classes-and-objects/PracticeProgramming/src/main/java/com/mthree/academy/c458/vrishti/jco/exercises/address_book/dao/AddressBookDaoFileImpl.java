package com.mthree.academy.c458.vrishti.jco.exercises.address_book.dao;

import com.mthree.academy.c458.vrishti.jco.exercises.address_book.dto.Address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookDaoFileImpl implements AddressBookDao {

    private String addressBookFile;

    private Map<Long, Address> addressMap = new HashMap<>();

    public AddressBookDaoFileImpl(String addressBookFile) {
        this.addressBookFile = addressBookFile;

        //Load up on application start.
        //...
    }

    @Override
    public boolean addAddress(Address address) {
        addressMap.put(address.getId(), address);
        return addressMap.get(address.getId()).equals(address);
    }

    @Override
    public boolean removeAddress(long id) {
        Address removedAddress = addressMap.remove(id);
        return removedAddress.getId() == id;
    }

    @Override
    public int countAddresses() {
        return addressMap.size();
    }

    @Override
    public List<Address> getAllAddresses() {
        return new ArrayList<>(addressMap.values());
    }

    @Override
    public List<Address> getAllAddressesByLastName(String lastName) {

        //Desired output
        List<Address> matchingAddresses = new ArrayList<>();

        //Find matching addresses
        for (Address address : addressMap.values()) {
            if (address.getLastName().equals(lastName))
                matchingAddresses.add(address);
        }

        //Return matching addresses
        return matchingAddresses;
    }

}
