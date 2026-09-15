package com.mthree.c458.vrishti.classroster.service;

import com.mthree.c458.vrishti.classroster.dao.ClassRosterAuditDao;
import com.mthree.c458.vrishti.classroster.dao.ClassRosterPersistenceException;

public class ClassRosterAuditDaoStubImpl implements ClassRosterAuditDao {

    @Override
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        //do nothing...
    }
}
