package mthree.academy.c458.vrishti.dvd_library.controller;

import mthree.academy.c458.vrishti.dvd_library.dao.DVDLibraryDao;
import mthree.academy.c458.vrishti.dvd_library.dto.DVD;
import mthree.academy.c458.vrishti.dvd_library.ui.DVDLibraryView;

import java.util.List;

public class DVDLibraryController {

    private DVDLibraryView view;
    private DVDLibraryDao dao;

    public DVDLibraryController(DVDLibraryView view, DVDLibraryDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void run() {

        boolean keepGoing = true;
        do {
            switch (view.printAndGetMenuSelection()) {
                case 1:
                    addDVD();
                    break;
                case 2:
                    removeDVD();
                    break;
                case 3:
                    editDVD();
                    break;
                case 4:
                    listDVDs();
                    break;
                case 5:
                    viewDVD();
                    break;
                case 6:
                    searchForDVDs();
                    break;
                case 7:
                    keepGoing = false;
                    break;
                default:
                    view.printUnknownOption();
            }

            //Display enter to proceed
            view.printEnterToProceed();

        } while (keepGoing);

        //End program
        view.printGoodBye();
    }

    private void addDVD() {
        view.displayAddDvdBanner();

        //Collect DVD details
        DVD newDVD = view.getNewDvdInfo();
        //Add DVD
        dao.addDVD(newDVD);

        //Display operation completed
        view.displayAddDvdCompleted();
    }

    private void removeDVD() {
        view.displayRemoveDvdBanner();

        //Get DVD to remove
        long id = view.getDVDId();
        DVD dvd = dao.getDVD(id);

        //Ensure DVD exists
        if (dvd == null) {
            view.displayNoSuchIdWarning(id);
            return;
        }

        //Remove DVD on confirmation
        if (view.confirmRemoveDVD(dvd)) {
            dao.removeDVD(id);
            view.displayRemoveDvdCompleted();
        } else {
            view.displayOperationAbandoned();
        }
    }

    private void editDVD() {
        view.displayEditDvdBanner();

        //Get DVD to edit
        long id = view.getDVDId();
        DVD dvd = dao.getDVD(id);

        //Ensure DVD exists
        if (dvd == null) {
            view.displayNoSuchIdWarning(id);
            return;
        }

        //Collect DVD Edits
        DVD edittedDVD = view.getEdittedDVD(dvd);

        //Apply edits on confirmation
        if (view.confirmEditDVD(edittedDVD)) {
            dao.editDVD(id, edittedDVD);
            view.displayEditDvdCompleted();
        } else {
            view.displayOperationAbandoned();
        }
    }

    private void listDVDs() {
        view.displayListDvdsBanner();

        //Get all DVDs
        List<DVD> dvds = dao.getDVDs();

        //Display DVDs
        view.displayDVDs(dvds);
    }

    private void viewDVD() {
        view.displayViewDvdBanner();

        //Get DVD to view
        long id = view.getDVDId();
        DVD dvd = dao.getDVD(id);

        //Display DVD
        if (dvd == null) {
            view.displayNoSuchIdWarning(id);
        } else {
            view.displayDVD(dvd);
        }
    }

    private void searchForDVDs() {
        view.displaySearchForDvdBanner();

        //Get title of DVD to search for
        String title = view.getDVDTitle();

        //Search for DVD
        List<DVD> searchResults = dao.findDVDsByTitle(title);

        //Display DVDs if found
        if (searchResults.isEmpty()) {
            view.displayNoSearchResultsWarning(title);
        } else {
            view.displayDVDs(searchResults);
        }
    }

}
