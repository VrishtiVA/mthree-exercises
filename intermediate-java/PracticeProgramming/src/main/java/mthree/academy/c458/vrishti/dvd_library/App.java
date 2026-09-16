package mthree.academy.c458.vrishti.dvd_library;

import mthree.academy.c458.vrishti.dvd_library.controller.DVDLibraryController;
import mthree.academy.c458.vrishti.dvd_library.dao.DVDLibraryDao;
import mthree.academy.c458.vrishti.dvd_library.dao.DVDLibraryDaoFileImpl;
import mthree.academy.c458.vrishti.dvd_library.service.DVDLibraryService;
import mthree.academy.c458.vrishti.dvd_library.service.DVDLibraryServiceImpl;
import mthree.academy.c458.vrishti.dvd_library.ui.DVDLibraryView;
import mthree.academy.c458.vrishti.dvd_library.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {

        //Instantiate components
        DVDLibraryView view = new DVDLibraryView(new UserIOConsoleImpl());
        DVDLibraryDao dao = new DVDLibraryDaoFileImpl("dvd-library.txt");
        DVDLibraryService service = new DVDLibraryServiceImpl(dao);
        DVDLibraryController controller = new DVDLibraryController(view, service);

        //Call run method from controller to start application
        controller.run();
    }
}
