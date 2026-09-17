package mthree.academy.c458.vrishti.dvd_library.ui;

import mthree.academy.c458.vrishti.dvd_library.dto.DVD;

import java.time.LocalDate;
import java.util.List;

public class DVDLibraryView {

    private UserIO userIO;

    public DVDLibraryView(UserIO userIO) {
        this.userIO = userIO;
    }

    public int printAndGetMenuSelection() {
        userIO.print(
                "\nSelect an option:" +
                "\n1. Add DVD" +
                "\n2. Remove a DVD" +
                "\n3. Edit DVD" +
                "\n4. List DVDs" +
                "\n5. View DVD" +
                "\n6. Search for DVDs" +
                "\n7. Quit"
        );
        return userIO.readInt("Your selection (#) : ", 1, 7);
    }

    public void printEnterToProceed() {
        userIO.readString("Press Enter to Proceed ...");
    }

    public void printUnknownOption() {
        userIO.print("Unknown Option");
    }

    public void printGoodBye() {
        userIO.print("Good Bye!");
    }

    //Display Banner Methods
    public void displayAddDvdBanner() { userIO.print("\n=== Add DVD ==="); }
    public void displayRemoveDvdBanner() { userIO.print("\n=== Remove DVD ==="); }
    public void displayEditDvdBanner() { userIO.print("\n=== Edit DVD ==="); }
    public void displayListDvdsBanner() { userIO.print("\n=== List DVDs ==="); }
    public void displayViewDvdBanner() { userIO.print("\n=== View DVD ==="); }
    public void displaySearchForDvdBanner() { userIO.print("\n=== Find DVDs ==="); }

    //Display Operation Completed Methods
    public void displayAddDvdCompleted() { userIO.print("\nDVD has been added."); }
    public void displayRemoveDvdCompleted() { userIO.print("\nDVD has been removed."); }
    public void displayEditDvdCompleted() { userIO.print("\nDVD has been modified."); }

    public DVD getNewDvdInfo() {

        //Collect inputs
        long id = userIO.readLong("Enter DVD Id : ");
        String title = userIO.readString("Enter Title of DVD : ");
        LocalDate releaseDate = userIO.readDate("Enter Release Date of DVD : ", true, null, null);
        String mpaaRating = userIO.readString("Enter MPAA Rating of DVD : ");
        String directorName = userIO.readString("Enter DVD Directors Name : ");
        String studio = userIO.readString("Enter DVD Studio : ");
        String userRating = userIO.readString("Provide a note for User Rating : ");

        //Create DVD object
        DVD newDVD = new DVD(id);
        newDVD.setTitle(title);
        newDVD.setReleaseDate(releaseDate);
        newDVD.setMpaaRating(mpaaRating);
        newDVD.setDirectorName(directorName);
        newDVD.setStudio(studio);
        newDVD.setUserRatingNote(userRating);

        //Return new DVD object
        return newDVD;
    }

    public long getDVDId() {
        return userIO.readLong("Enter DVD Id : ");
    }

    public void displayNoSuchIdWarning(long id) {
        userIO.print("\nNo DVD with Id " + id + " exists.");
    }

    public void displayNoSearchResultsWarning(String title) {
        userIO.print("\nNo DVDs with titles matching \"" + title + "\" exist.");
    }

    public void displayOperationAbandoned() {
        userIO.print("\nOperation has been abandoned.");
    }

    public void displayDVD(DVD dvd) {
        userIO.print(
            "\n- Id: " + dvd.getId() +
            "\n- Title: " + dvd.getTitle() +
            "\n- Release Date: " + dvd.getReleaseDate().format(UserIO.DATE_FORMAT) +
            "\n- MPAA Rating: " + dvd.getMpaaRating() +
            "\n- Director: " + dvd.getDirectorName() +
            "\n- Studio: " + dvd.getStudio() +
            "\n- User Rating: " + dvd.getUserRatingNote()
        );
    }

    public DVD getEdittedDVD(DVD currentDVD) {

        userIO.print(
                "\nYou are now editing this DVD." +
                "\nYou will be presented with each field." +
                "\nIf you wish to edit the field, enter the new data. " +
                "\nIf you wish to keep the current data, simply press enter and move on." +
                "\n"
        );

        //Collect edits
        String title = userIO.readString("Enter Title of DVD (" + currentDVD.getTitle() + "): ");
        LocalDate releaseDate = userIO.readDate("Enter Release Date of DVD (" + currentDVD.getReleaseDate() + "): ", true,null, null);
        String mpaaRating = userIO.readString("Enter MPAA Rating of DVD (" + currentDVD.getMpaaRating() + "): ");
        String directorName = userIO.readString("Enter DVD Directors Name (" + currentDVD.getDirectorName() + "): ");
        String studio = userIO.readString("Enter DVD Studio (" + currentDVD.getStudio() + "): ");
        String userRating = userIO.readString("Provide a note for User Rating (" + currentDVD.getUserRatingNote() + "): ");

        //Build editted DVD object
        DVD edittedDVD = new DVD(currentDVD.getId());
        edittedDVD.setTitle( title.isBlank() ? currentDVD.getTitle() : title );
        edittedDVD.setReleaseDate( releaseDate == null ? currentDVD.getReleaseDate() : releaseDate );
        edittedDVD.setMpaaRating( mpaaRating.isBlank() ? currentDVD.getMpaaRating() : mpaaRating );
        edittedDVD.setDirectorName( directorName.isBlank() ? currentDVD.getDirectorName() : directorName );
        edittedDVD.setStudio( studio.isBlank() ? currentDVD.getStudio() : studio );
        edittedDVD.setUserRatingNote( userRating.isBlank() ? currentDVD.getUserRatingNote() : userRating );

        //Return editted DVD
        return edittedDVD;
    }

    private boolean makeYesNoDecision(String prompt) {

        //Desired input
        String userInput;

        do {
            //Take decision input
            //Only want to consider first character, and avoid error due to no input.
            userInput = userIO.readString(prompt).trim();
            userInput = userInput.substring(0, Math.min(1, userInput.length())).toUpperCase();

            //Evaluate decision
            switch (userInput) {
                case "Y": return true;
                case "N": return false;
                default: userIO.print("Invalid input. Please indicate Y/N.");
            }

        } while (true);
    }

    public boolean confirmEditDVD(DVD dvd) {
        displayDVD(dvd);
        return makeYesNoDecision("\nConfirm edits to the DVD? (Y/N) : ");
    }

    public boolean confirmRemoveDVD(DVD dvd) {
        displayDVD(dvd);
        return makeYesNoDecision("\nConfirm removal of the DVD? (Y/N) : ");
    }

    public void displayDVDs(List<DVD> dvds) {

        //Display all DVDs
        for (DVD dvd : dvds) {
            displayDVD(dvd);
        }
        userIO.print("");
    }

    public String getDVDTitle() {
        return userIO.readString("Search for DVDs by Title : ");
    }

    public void displayErrorMessage(String message) {
        userIO.print("\nError: " + message);
    }
}
