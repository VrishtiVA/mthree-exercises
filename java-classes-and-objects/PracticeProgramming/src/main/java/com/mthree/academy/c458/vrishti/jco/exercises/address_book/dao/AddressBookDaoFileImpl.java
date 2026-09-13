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

//    @Override
//    public boolean editAddress(long addressId, String firstName, String lastName, String street, String city, String state, String country, String postCode) {
//
//        //Cannot update non-existing address
//        if (!addressMap.containsKey(addressId)) return false;
//
//        //Get address to update
//        Address addressToUpdate = addressMap.get(addressId);
//
//        //Apply updates
//        if (firstName != null) addressToUpdate.setFirstName(firstName);
//        if (lastName != null) addressToUpdate.setLastName(lastName);
//        if (street != null) addressToUpdate.setStreet(street);
//        if (city != null) addressToUpdate.setCity(city);
//        if (state != null) addressToUpdate.setState(state);
//        if (country != null) addressToUpdate.setCountry(country);
//        if (postCode != null) addressToUpdate.setPostCode(postCode);
//
//        //Return true to note updates completed
//        return true;
//    }

    @Override
    public Address getAddressById(long addressId) {
        return addressMap.get(addressId);
    }

    @Override
    public boolean updateAddressFirstName(long addressId, String updateWith) {
        try {
            getAddressById(addressId).setFirstName(updateWith);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateAddressLastName(long addressId, String updateWith) {
        try {
            getAddressById(addressId).setLastName(updateWith);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateAddressStreet(long addressId, String updateWith) {
        try {
            getAddressById(addressId).setStreet(updateWith);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateAddressCity(long addressId, String updateWith) {
        try {
            getAddressById(addressId).setCity(updateWith);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateAddressState(long addressId, String updateWith) {
        try {
            getAddressById(addressId).setState(updateWith);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateAddressCountry(long addressId, String updateWith) {
        try {
            getAddressById(addressId).setCountry(updateWith);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateAddressPostCode(long addressId, String updateWith) {
        try {
            getAddressById(addressId).setPostCode(updateWith);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
