package com.mthree.academy.c458.vrishti.jco.exercises.address_book;

import com.mthree.academy.c458.vrishti.jco.exercises.address_book.controller.AddressBookController;
import com.mthree.academy.c458.vrishti.jco.exercises.address_book.dao.AddressBookDao;
import com.mthree.academy.c458.vrishti.jco.exercises.address_book.dao.AddressBookDaoFileImpl;
import com.mthree.academy.c458.vrishti.jco.exercises.address_book.ui.AddressBookView;
import com.mthree.academy.c458.vrishti.jco.exercises.address_book.ui.UserIOConsoleImpl;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        //Wire up
        AddressBookView view = new AddressBookView(new UserIOConsoleImpl());
        AddressBookDao dao = new AddressBookDaoFileImpl("addressBook.txt");
        AddressBookController controller = new AddressBookController(dao, view);

        //Run application through controller
        controller.run();
    }
}
