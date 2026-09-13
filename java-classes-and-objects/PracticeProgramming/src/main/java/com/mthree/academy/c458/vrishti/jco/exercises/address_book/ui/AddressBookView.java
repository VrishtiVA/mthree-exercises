package com.mthree.academy.c458.vrishti.jco.exercises.address_book.ui;

import com.mthree.academy.c458.vrishti.jco.exercises.address_book.dto.Address;

import java.util.List;

public class AddressBookView {

    private UserIO userIO;

    public AddressBookView(UserIO userIO) {
        this.userIO = userIO;
    }

    /**
     * Method to print Address Book main menu, and
     * obtain user number selection from the options available.
     * @return The number representing the selected option.
     */
    public int printAndGetMenuSelection() {
        userIO.print(
            "\nPlease select the operation you wish to perform:" +
            "\n1. Add Address" +
            "\n2. Delete Address" +
            "\n3. Find Addresses By Last Name" +
            "\n4. Find Address Count" +
            "\n5. List All Addresses" +
            "\n6. Quit Program"
        );
        return userIO.readInt("Your selection : ", 1, 6);
    }

    public void printTitle() {
        userIO.print("\n=-=-= Address Book =-=-=");
    }

    public void printEnding() {
        userIO.print("\n=-=-= Good Bye =-=-=");
    }

    public void printHeader(String header) {
        userIO.print("\n=---= " + header + " =---=");
    }

    public void printSuccessMessage(String message) {
        userIO.print("Success: " + message);
    }

    public void printWarnMessage(String message) {
        userIO.print("Warning: " + message);
    }

    public void enterToProceed() {
        userIO.readString("\nPress enter to proceed...");
    }

    public Address getNewAddress() {

        //Collect address details
        long id = userIO.readLong("Enter Address Id : ");
        String firstName = userIO.readString("Enter First Name : ");
        String lastName = userIO.readString("Enter Last Name : ");
        String street = userIO.readString("Enter Street Address : ");
        String city = userIO.readString("Enter City : ");
        String state = userIO.readString("Enter State : ");
        String country = userIO.readString("Enter Country : ");
        String postCode = userIO.readString("Enter Post Code : ");

        //Build new address object
        Address address = new Address(id);
        address.setFirstName(firstName);
        address.setLastName(lastName);
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setPostCode(postCode);

        //Return address object
        return address;
    }

    public long getAddressId() {
        return userIO.readLong("Enter Address Id : ");
    }

    public String getLastName() {
        return userIO.readString("Enter Last Name : ");
    }

    public void displayAddresses(String header, List<Address> addresses) {

        //Consider like 204
        if (addresses.isEmpty()) {
            userIO.print("There are no addresses to see.");
            return;
        }

        //Go for 200
        for (Address address : addresses) {
            userIO.print(
                "\nAddress ID " + address.getId() + ":" +
                "\n\t" + address.getFirstName() + " " + address.getLastName() +
                "\n\t" + address.getStreet() +
                "\n\t" + address.getCity() + ", " + address.getState() + ", " + address.getCountry() +
                "\n\t" + address.getPostCode()
            );
        }
    }

    public void displayAddressCount(int addressCount) {
        userIO.print("There are " + addressCount + " addresses in the address book.");
    }
}
