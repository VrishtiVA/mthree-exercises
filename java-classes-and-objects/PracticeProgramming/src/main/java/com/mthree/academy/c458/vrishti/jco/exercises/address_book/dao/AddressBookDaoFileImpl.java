package com.mthree.academy.c458.vrishti.jco.exercises.address_book.dao;

import com.mthree.academy.c458.vrishti.jco.exercises.address_book.dto.Address;

import java.io.*;
import java.util.*;

public class AddressBookDaoFileImpl implements AddressBookDao {

    private String DELIMITER = "::";

    private String addressBookFile;
    private Map<Long, Address> addressMap = new HashMap<>();

    public AddressBookDaoFileImpl(String addressBookFile) throws IOException {
        this.addressBookFile = addressBookFile;

        //Load up on application start.
        loadAddressBook();
    }

    @Override
    public boolean addAddress(Address address) {

        //Add address
        addressMap.put(address.getId(), address);
        //Persist update
        writeAddressBook();
        //Return if was added or not
        return addressMap.get(address.getId()).equals(address);
    }

    @Override
    public boolean removeAddress(long id) {

        //Remove address
        Address removedAddress = addressMap.remove(id);
        //Persist update
        writeAddressBook();
        //Return if was updated or not
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
            if (address.getLastName().equalsIgnoreCase(lastName))
                matchingAddresses.add(address);
        }

        //Return matching addresses
        return matchingAddresses;
    }

    @Override
    public Address getAddressById(long addressId) {
        return addressMap.get(addressId);
    }

    @Override
    public boolean updateFirstName(long addressId, String updateWith) {

        String previousValue = getAddressById(addressId).getFirstName();
        try {
            //Update
            getAddressById(addressId).setFirstName(updateWith);

            //Persist update
            writeAddressBook();

            return true;

        } catch (Exception e) {
            //Rollback
            getAddressById(addressId).setFirstName(previousValue);
            return false;
        }
    }

    @Override
    public boolean updateLastName(long addressId, String updateWith) {

        String previousValue = getAddressById(addressId).getLastName();
        try {
            //Update
            getAddressById(addressId).setLastName(updateWith);

            //Persist update
            writeAddressBook();

            return true;

        } catch (Exception e) {
            //Rollback
            getAddressById(addressId).setLastName(previousValue);
            return false;
        }
    }

    @Override
    public boolean updateAddressStreet(long addressId, String updateWith) {

        String previousValue = getAddressById(addressId).getStreet();
        try {
            //Update
            getAddressById(addressId).setStreet(updateWith);

            //Persist update
            writeAddressBook();

            return true;

        } catch (Exception e) {
            //Rollback
            getAddressById(addressId).setStreet(previousValue);
            return false;
        }
    }

    @Override
    public boolean updateAddressCity(long addressId, String updateWith) {

        String previousValue = getAddressById(addressId).getCity();
        try {
            //Update
            getAddressById(addressId).setCity(updateWith);

            //Persist update
            writeAddressBook();

            return true;

        } catch (Exception e) {
            //Rollback
            getAddressById(addressId).setCity(previousValue);
            return false;
        }
    }

    @Override
    public boolean updateAddressState(long addressId, String updateWith) {

        String previousValue = getAddressById(addressId).getState();
        try {
            //Update
            getAddressById(addressId).setState(updateWith);

            //Persist update
            writeAddressBook();

            return true;

        } catch (Exception e) {
            //Rollback
            getAddressById(addressId).setState(previousValue);
            return false;
        }
    }

    @Override
    public boolean updateAddressCountry(long addressId, String updateWith) {

        String previousValue = getAddressById(addressId).getCountry();
        try {
            //Update
            getAddressById(addressId).setCountry(updateWith);

            //Persist update
            writeAddressBook();

            return true;

        } catch (Exception e) {
            //Rollback
            getAddressById(addressId).setCountry(previousValue);
            return false;
        }
    }

    @Override
    public boolean updateAddressPostCode(long addressId, String updateWith) {

        String previousValue = getAddressById(addressId).getPostCode();
        try {
            //Update
            getAddressById(addressId).setPostCode(updateWith);

            //Persist update
            writeAddressBook();

            return true;

        } catch (Exception e) {
            //Rollback
            getAddressById(addressId).setPostCode(previousValue);
            return false;
        }
    }

    private String marshallAddress(Address address) {
        return address.getId() +
            DELIMITER + address.getFirstName() +
            DELIMITER + address.getLastName() +
            DELIMITER + address.getStreet() +
            DELIMITER + address.getCity() +
            DELIMITER + address.getState() +
            DELIMITER + address.getCountry() +
            DELIMITER + address.getPostCode();
    }

    private Address unmarshallAddress(String addressString) {

        //Split by delimiter
        String[] addressContent = addressString.split(DELIMITER);

        //Build address object from line
        Address address = new Address(Long.parseLong(addressContent[0]));
        address.setFirstName(addressContent[1]);
        address.setLastName(addressContent[2]);
        address.setStreet(addressContent[3]);
        address.setCity(addressContent[4]);
        address.setState(addressContent[5]);
        address.setCountry(addressContent[6]);
        address.setPostCode(addressContent[7]);

        //Return address object
        return address;
    }

    private void loadAddressBook() throws AddressBookPersistenceException {

        //Scanner for writing to file.
        Scanner fileReader;
        try {
            fileReader = new Scanner(new BufferedReader(new FileReader(addressBookFile)));
        } catch (FileNotFoundException e) {
            throw new AddressBookPersistenceException("Could not load address book into memory");
        }

        //Utility variables
        String currentAddressLine;
        Address currentAddress;

        //Read file line by line and populate map.
        while (fileReader.hasNextLine()) {
            //Get address line
            currentAddressLine = fileReader.nextLine();
            //Build address object
            currentAddress = unmarshallAddress(currentAddressLine);
            //Populate map
            addressMap.put(currentAddress.getId(), currentAddress);
        }
    }

    private void writeAddressBook() throws AddressBookPersistenceException {

        PrintWriter fileWriter;
        try {
            //New file writer, so its actions will override.
            fileWriter = new PrintWriter(new FileWriter(addressBookFile));
        } catch (IOException e) {
            throw new AddressBookPersistenceException("Could not save address book", e);
        }

        //Write in the whole address book
        String addressLine;
        for (Address address : getAllAddresses()) {
            //Convert address to string
            addressLine = marshallAddress(address);
            //Write the line
            fileWriter.println(addressLine);
            //Flush the line into file now.
            fileWriter.flush();
        }

        //Clean up
        fileWriter.close();
    }

}
