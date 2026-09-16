package mthree.academy.c458.vrishti.dvd_library.dao;

import mthree.academy.c458.vrishti.dvd_library.dto.DVD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao {

    long lastDVDId;
    Map<Long, DVD> dvdMap;

    public DVDLibraryDaoFileImpl() {
        this.dvdMap = new HashMap<>();
        this.lastDVDId = dvdMap.size();
    }

    public long getNewDVDId() {
        return ++lastDVDId;
    }

    @Override
    public DVD addDVD(DVD dvd) {
        return dvdMap.put(getNewDVDId(), dvd);
    }

    @Override
    public DVD removeDVD(long id) {
        return dvdMap.remove(id);
    }

    @Override
    public DVD editDVD(long id, DVD edittedDVD) {
        return dvdMap.replace(id, edittedDVD);
    }

    @Override
    public List<DVD> getDVDs() {
        return new ArrayList<>(dvdMap.values());
    }

    @Override
    public DVD getDVD(long id) {
        return dvdMap.get(id);
    }

    @Override
    public List<DVD> findDVDsByTitle(String title) {

        List<DVD> matchingResults = new ArrayList<>();

        //Go through all DVDs
        for (DVD dvd : dvdMap.values()) {

            //Add DVD if title fits
            if (dvd.getTitle().contains(title)) {
                matchingResults.add(dvd);
            }
        }

        return matchingResults;
    }

}
