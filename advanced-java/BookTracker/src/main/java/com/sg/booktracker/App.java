package com.sg.booktracker;

import com.sg.booktracker.controller.BookController;
import com.sg.booktracker.dao.BookDao;
import com.sg.booktracker.dao.BookDaoMemoryImpl;
import com.sg.booktracker.service.BookService;
import com.sg.booktracker.ui.BookView;
import com.sg.booktracker.ui.UserIO;
import com.sg.booktracker.ui.UserIOConsoleImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Kyle David Rudy
 */
public class App {
    public static void main(String[] args) {

//        UserIO io = new UserIOConsoleImpl();
//        BookView view = new BookView(io);
//
//        BookDao dao = new BookDaoMemoryImpl();
//        BookService service = new BookService(dao);
//
//        BookController controller = new BookController(service, view);

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("com.sg.booktracker");
        applicationContext.refresh();

        BookController controller = applicationContext.getBean("bookController", BookController.class);

        controller.run();
    }
}
