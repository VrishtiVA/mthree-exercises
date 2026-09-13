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
            "\n6. Edit Address" +
            "\n7. Quit Program"
        );
        return userIO.readInt("Your selection : ", 1, 7);
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
            printAddress(address);
        }
    }

    public void printAddress(Address address) {
        if (address == null) return;

        userIO.print(
            "\nAddress ID " + address.getId() + ":" +
            "\n\t" + address.getFirstName() + " " + address.getLastName() +
            "\n\t" + address.getStreet() +
            "\n\t" + address.getCity() + ", " + address.getState() + ", " + address.getCountry() +
            "\n\t" + address.getPostCode()
        );
    }

    public void displayAddressCount(int addressCount) {
        userIO.print("There " + (addressCount == 1 ? "is 1 address" :  "are " + addressCount + " addresses") + " in the address book.");
    }

    public boolean confirmRemoveAddress(Address address) {

        //Display address
        printAddress(address);

        //Confirm delete
        String userInput = userIO.readString("\nAre you sure you wish to remove this address? (y/n) : ").trim();
        if (!userInput.isBlank())
            return Character.toUpperCase(userInput.charAt(0)) == 'Y';
        else
            return false;
    }

    public void printCancelledOperation() {
        userIO.print("Cancelled Operation.");
    }

    public String getField(String field) {
        return userIO.readString("Enter New " + field + " : ");
    }

    public int printAndGetEditAddressMenuSelection() {
        userIO.print(
            "\nPlease select the field you wish to update:" +
                "\n1. First Name" +
                "\n2. Last Name" +
                "\n3. Street Address" +
                "\n4. City" +
                "\n5. State" +
                "\n6. Country" +
                "\n7. Post Code" +
                "\n8. Go Back"
        );
        return userIO.readInt("Your selection : ", 1, 8);
    }
}
