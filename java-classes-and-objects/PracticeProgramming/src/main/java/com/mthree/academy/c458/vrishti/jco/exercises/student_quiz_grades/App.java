package com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades;

import com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.controller.StudentQuizScoresController;
import com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.dao.StudentQuizScoresDao;
import com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.dao.StudentQuizScoresDaoMapImpl;
import com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.ui.StudentQuizScoresView;
import com.mthree.academy.c458.vrishti.jco.exercises.student_quiz_grades.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {

        //Wire up the application
        StudentQuizScoresView view = new StudentQuizScoresView(new UserIOConsoleImpl());
        StudentQuizScoresDao dao = new StudentQuizScoresDaoMapImpl();
        StudentQuizScoresController controller = new StudentQuizScoresController(dao, view);

        //Run application
        controller.run();
    }
}
