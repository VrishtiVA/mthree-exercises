package mthree.academy.c458.vrishti.dvd_library.controller;

import mthree.academy.c458.vrishti.dvd_library.dao.DVDLibraryDao;
import mthree.academy.c458.vrishti.dvd_library.dto.DVD;
import mthree.academy.c458.vrishti.dvd_library.service.DVDLibraryDuplicateIdException;
import mthree.academy.c458.vrishti.dvd_library.service.DVDLibraryPersistenceException;
import mthree.academy.c458.vrishti.dvd_library.service.DVDLibraryService;
import mthree.academy.c458.vrishti.dvd_library.service.DVDLibraryValidationException;
import mthree.academy.c458.vrishti.dvd_library.ui.DVDLibraryView;

import java.util.List;

public class DVDLibraryController {

    private DVDLibraryView view;
    private DVDLibraryService service;

    public DVDLibraryController(DVDLibraryView view, DVDLibraryService service) {
        this.view = view;
        this.service = service;
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

    private void addDVD() throws DVDLibraryPersistenceException {
        view.displayAddDvdBanner();

        boolean hasErrors = false;
        do {
            //Collect DVD details
            DVD newDVD = view.getNewDvdInfo();

            try {
                //Add DVD
                service.addDVD(newDVD);

                //Display operation completed
                view.displayAddDvdCompleted();
                hasErrors = false;

            } catch (DVDLibraryDuplicateIdException | DVDLibraryValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }

    private void removeDVD() throws DVDLibraryPersistenceException {
        view.displayRemoveDvdBanner();

        //Get DVD to remove
        long id = view.getDVDId();
        DVD dvd = service.getDVD(id);

        //Ensure DVD exists
        if (dvd == null) {
            view.displayNoSuchIdWarning(id);
            return;
        }

        //Remove DVD on confirmation
        if (view.confirmRemoveDVD(dvd)) {
            service.removeDVD(id);
            view.displayRemoveDvdCompleted();
        } else {
            view.displayOperationAbandoned();
        }
    }

    private void editDVD() throws DVDLibraryPersistenceException {
        view.displayEditDvdBanner();

        //Get DVD to edit
        long id = view.getDVDId();
        DVD dvd = service.getDVD(id);

        //Ensure DVD exists
        if (dvd == null) {
            view.displayNoSuchIdWarning(id);
            return;
        }

        boolean hasErrors = false;
        do {
            //Collect DVD Edits
            DVD edittedDVD = view.getEdittedDVD(dvd);
            try {
                //Apply edits on confirmation
                if (view.confirmEditDVD(edittedDVD)) {
                    service.editDVD(id, edittedDVD);
                    view.displayEditDvdCompleted();
                } else {
                    view.displayOperationAbandoned();
                }
                hasErrors = false;

            } catch (DVDLibraryValidationException e) {
                view.displayErrorMessage(e.getMessage());
                hasErrors = true;
            }
        } while (hasErrors);
    }

    private void listDVDs() throws DVDLibraryPersistenceException {
        view.displayListDvdsBanner();

        //Get all DVDs
        List<DVD> dvds = service.getAllDVDs();

        //Display DVDs
        view.displayDVDs(dvds);
    }

    private void viewDVD() throws DVDLibraryPersistenceException {
        view.displayViewDvdBanner();

        //Get DVD to view
        long id = view.getDVDId();
        DVD dvd = service.getDVD(id);

        //Display DVD
        if (dvd == null) {
            view.displayNoSuchIdWarning(id);
        } else {
            view.displayDVD(dvd);
        }
    }

    private void searchForDVDs() throws DVDLibraryPersistenceException {
        view.displaySearchForDvdBanner();

        //Get title of DVD to search for
        String title = view.getDVDTitle();

        //Search for DVD
        List<DVD> searchResults = service.searchForDVDsByTitle(title);

        //Display DVDs if found
        if (searchResults.isEmpty()) {
            view.displayNoSearchResultsWarning(title);
        } else {
            view.displayDVDs(searchResults);
        }
    }

}
