package com.mthree.c458.vrishti.classroster;

import com.mthree.c458.vrishti.classroster.controller.ClassRosterController;
import com.mthree.c458.vrishti.classroster.dao.ClassRosterDao;
import com.mthree.c458.vrishti.classroster.dao.ClassRosterDaoFileImpl;
import com.mthree.c458.vrishti.classroster.ui.ClassRosterView;
import com.mthree.c458.vrishti.classroster.ui.UserIO;
import com.mthree.c458.vrishti.classroster.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {

        //Decide implementations
        UserIO myIo = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIo);
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();

        //Use controller to call the run method.
        ClassRosterController controller = new ClassRosterController(
            myDao,
            myView
        );
        controller.run();

    }
}
