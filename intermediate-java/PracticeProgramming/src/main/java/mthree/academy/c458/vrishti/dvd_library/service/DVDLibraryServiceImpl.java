package mthree.academy.c458.vrishti.dvd_library.service;

import mthree.academy.c458.vrishti.dvd_library.dao.DVDLibraryDao;
import mthree.academy.c458.vrishti.dvd_library.dto.DVD;

import java.util.List;

public class DVDLibraryServiceImpl implements DVDLibraryService {

    private DVDLibraryDao dao;

    public DVDLibraryServiceImpl(DVDLibraryDao dao) {
        this.dao = dao;
    }

    private void validateDVDInformation(DVD dvd) {
        if (dvd.getTitle() == null || dvd.getTitle().isBlank()) {
            throw new DVDLibraryValidationException("DVD title should not be blank.");
        }
    }

    @Override
    public void addDVD(DVD dvd) throws DVDLibraryPersistenceException, DVDLibraryDuplicateIdException, DVDLibraryValidationException {

        //Ensure not duplicate ID
        if (dao.getDVD(dvd.getId()) != null) {
            throw new DVDLibraryDuplicateIdException("DVD with the same ID already exists");
        }

        //Apply DVD Validation
        validateDVDInformation(dvd);

        //Add DVD
        dao.addDVD(dvd);
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryPersistenceException {
        return dao.getDVDs();
    }

    @Override
    public DVD getDVD(long id) throws DVDLibraryPersistenceException {
        return dao.getDVD(id);
    }

    @Override
    public List<DVD> searchForDVDsByTitle(String title) throws DVDLibraryPersistenceException {
        return dao.findDVDsByTitle(title);
    }

    @Override
    public DVD removeDVD(long id) throws DVDLibraryPersistenceException {
        return dao.removeDVD(id);
    }

    @Override
    public DVD editDVD(long id, DVD edittedDVD) throws DVDLibraryPersistenceException {

        //Validate editted DVD
        validateDVDInformation(edittedDVD);
        //Apply edits
        return dao.editDVD(id, edittedDVD);
    }

}
