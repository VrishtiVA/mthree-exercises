package com.mthree.academy.c458.vrishti.jco.exercises.address_book.controller;

import com.mthree.academy.c458.vrishti.jco.exercises.address_book.dao.AddressBookDao;
import com.mthree.academy.c458.vrishti.jco.exercises.address_book.dto.Address;
import com.mthree.academy.c458.vrishti.jco.exercises.address_book.ui.AddressBookView;

import java.util.List;

public class AddressBookController {

    private AddressBookDao dao;
    private AddressBookView view;

    public AddressBookController(AddressBookDao dao, AddressBookView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        view.printTitle();

        boolean keepGoing = true;
        do {
            switch (view.printAndGetMenuSelection()) {
                case 1:
                    addAddress();
                    break;
                case 2:
                    deleteAddress();
                    break;
                case 3:
                    findAddressesByLastName();
                    break;
                case 4:
                    findAddressCount();
                    break;
                case 5:
                    findAllAddresses();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    break;
            }

            //To proceed
            view.enterToProceed();

        } while (keepGoing);

        view.printEnding();
    }

    private void addAddress() {
        view.printHeader("Add Address");

        //Get new address items
        Address address = view.getNewAddress();
        //Add new address
        boolean success = dao.addAddress(address);
        //Feedback to user
        if (success) view.printSuccessMessage("Added address to address book.");
        else view.printWarnMessage("Could not add address to address book.");
    }

    private void deleteAddress() {
        view.printHeader("Delete Address");

        //Get id of address to remove
        long addressId = view.getAddressId();
        //Remove the address
        boolean success = dao.removeAddress(addressId);
        //Feedback to user
        if (success) view.printSuccessMessage("Removed address from address book.");
        else view.printWarnMessage("Could not remove address from address book.");
    }

    private void findAddressesByLastName() {
        view.printHeader("Find Address");

        //Get last name to search by
        String lastName = view.getLastName();
        //Find matching addresses
        List<Address> addresses = dao.getAllAddressesByLastName(lastName);
        //Display addresses
        view.displayAddresses("Matching Addresses", addresses);
    }

    private void findAddressCount() {
        view.printHeader("Address Count");

        //Get address count
        int addressCount = dao.countAddresses();
        //Display address count
        view.displayAddressCount(addressCount);
    }

    private void findAllAddresses() {
        view.printHeader("Full Address Book");

        //Get all addresses
        List<Address> addresses = dao.getAllAddresses();
        //Display all addresses
        view.displayAddresses("Address Book", addresses);
    }

}
