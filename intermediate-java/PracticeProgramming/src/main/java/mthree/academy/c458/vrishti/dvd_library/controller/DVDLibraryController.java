package mthree.academy.c458.vrishti.dvd_library.controller;

import mthree.academy.c458.vrishti.dvd_library.dto.DVD;
import mthree.academy.c458.vrishti.dvd_library.service.DVDLibraryDuplicateIdException;
import mthree.academy.c458.vrishti.dvd_library.service.DVDLibraryPersistenceException;
import mthree.academy.c458.vrishti.dvd_library.service.DVDLibraryService;
import mthree.academy.c458.vrishti.dvd_library.service.DVDLibraryValidationException;
import mthree.academy.c458.vrishti.dvd_library.ui.DVDLibraryView;

import java.util.List;
import java.util.Map;

public class DVDLibraryController {

    private DVDLibraryView view;
    private DVDLibraryService service;

    public DVDLibraryController(DVDLibraryView view, DVDLibraryService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {

        boolean skipEnterToProceed;
        boolean keepGoing = true;
        do {
            skipEnterToProceed = false;

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
                    searchForDVDsByTitle();
                    break;
                case 7:
                    runSubMenu();
                    skipEnterToProceed = true;
                    break;
                case 8:
                    skipEnterToProceed = true;
                    keepGoing = false;
                    break;
                default:
                    view.printUnknownOption();
            }

            //Display enter to proceed
            if (!skipEnterToProceed)
                view.printEnterToProceed();

        } while (keepGoing);

        //End program
        view.printGoodBye();
    }

    private void runSubMenu() {

        boolean keepGoing = true;
        do {
            switch (view.printAndGetSubMenuSelection()) {
                case 1:
                    findDVDsLastNYears();
                    break;
                case 2:
                    findDVDsByMpaaRating();
                    break;
                case 3:
                    findDVDsByDirector();
                    break;
                case 4:
                    findDVDsByStudio();
                    break;
                case 5:
                    viewAverageDVDAge();
                    break;
                case 6:
                    findNewestDVD();
                    break;
                case 7:
                    findOldestDVD();
                    break;
                case 8:
                    keepGoing = false;
                    break;
                default:
                    view.printUnknownOption();
            }

            //Display enter to proceed
            view.printEnterToProceed();

        } while (keepGoing);
    }

    private void findDVDsLastNYears() {
        view.displaySearchForDvdBanner();

        //Search
        int nYears = view.getDVDSearchLastNYears();
        //Get
        List<DVD> searchResults = service.findDVDsUnderNYearsOld(nYears);
        //Display
        view.displayDVDs(searchResults);
    }

    private void findDVDsByMpaaRating() {
        view.displaySearchForDvdBanner();

        //Search
        String searchInput = view.getDVDSearchMpaaRating();
        //Get
        List<DVD> searchResults = service.findDVDsByMpaaRating(searchInput);
        //Display
        view.displayDVDs(searchResults);
    }

    private void findDVDsByDirector() {
        view.displaySearchForDvdBanner();

        //Search
        String searchInput = view.getDVDSearchDirector();
        //Get
        Map<String, List<DVD>> searchResults = service.findDVDsByDirector(searchInput);
        //Display
        view.displayDVDsByMpaaRating(searchResults);
    }

    private void findDVDsByStudio() {
        view.displaySearchForDvdBanner();

        //Search
        String searchInput = view.getDVDSearchStudio();
        //Get
        List<DVD> searchResults = service.findDVDsByStudio(searchInput);
        //Display
        view.displayDVDs(searchResults);
    }

    private void viewAverageDVDAge() {
        view.displayAvgDvdAgeBanner();

        //Get
        Integer avgAgeInDays = service.getDVDsAverageAgeInDays();

        //Display
        if (avgAgeInDays != null)
            view.displayDVDAvgAgeInDays(avgAgeInDays);
        else
            view.displayEmptyDVDLibraryWarning();
    }

    private void findNewestDVD() {
        view.displayNewestDvdBanner();

        //Get Newest DVD
        DVD dvd = service.getNewestDVD();

        //Display
        if (dvd != null)
            view.displayDVD(dvd);
        else
            view.displayEmptyDVDLibraryWarning();
    }

    private void findOldestDVD() {
        view.displayOldestDvdBanner();

        //Get Oldest DVD
        DVD dvd = service.getOldestDVD();

        //Display
        if (dvd != null) {
            view.displayDVD(dvd);
        } else {
            view.displayEmptyDVDLibraryWarning();
        }
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

    private void searchForDVDsByTitle() throws DVDLibraryPersistenceException {
        view.displaySearchForDvdBanner();

        //Get title of DVD to search for
        String title = view.getDVDSearchTitle();

        //Search for DVD
        List<DVD> searchResults = service.searchForDVDsByTitle(title);

        //Display DVDs if found
        if (searchResults.isEmpty()) {
            view.displayNoTitleSearchResultsWarning(title);
        } else {
            view.displayDVDs(searchResults);
        }
    }

}
