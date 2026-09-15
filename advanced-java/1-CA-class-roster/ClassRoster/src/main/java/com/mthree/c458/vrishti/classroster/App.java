package com.mthree.c458.vrishti.classroster;

import com.mthree.c458.vrishti.classroster.controller.ClassRosterController;
import com.mthree.c458.vrishti.classroster.dao.ClassRosterAuditDao;
import com.mthree.c458.vrishti.classroster.dao.ClassRosterAuditDaoFileImpl;
import com.mthree.c458.vrishti.classroster.dao.ClassRosterDao;
import com.mthree.c458.vrishti.classroster.dao.ClassRosterDaoFileImpl;
import com.mthree.c458.vrishti.classroster.service.ClassRosterServiceLayer;
import com.mthree.c458.vrishti.classroster.service.ClassRosterServiceLayerImpl;
import com.mthree.c458.vrishti.classroster.ui.ClassRosterView;
import com.mthree.c458.vrishti.classroster.ui.UserIO;
import com.mthree.c458.vrishti.classroster.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {

        //Decide implementations
        UserIO myIo = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIo);
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);

        //Use controller to call the run method.
        ClassRosterController controller = new ClassRosterController(
            myService,
            myView
        );
        controller.run();

    }
}
