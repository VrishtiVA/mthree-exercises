package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE

    //Course service used to provide access to getting valid courses.
    @Autowired
    private CourseServiceInterface courseService;

    private StudentDao studentDao;

    //Using constructor injection only for student DAO (since unable to update test, this constructor is required).
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        return studentDao.getAllStudents();

        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE

        try {
            return studentDao.findStudentById(id);

        } catch (DataAccessException ex) {

            //Returning dummy student object if not found.
            Student student = new Student();
            student.setStudentFirstName("Student Not Found");
            student.setStudentFirstName("Student Not Found");
            return student;
        }

        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE

        //Handle edge cases
        boolean isValid = true;
        if (student.getStudentFirstName().isBlank()) {
            student.setStudentFirstName("First Name blank, student NOT added");
            isValid = false;
        }
        if (student.getStudentLastName().isBlank()) {
            student.setStudentLastName("Last Name blank, student NOT added");
            isValid = false;
        }
        if (!isValid)
            return student;

        //Create new student
        return studentDao.createNewStudent(student);

        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE

        //Handle edge case
        if (id != student.getStudentId()) {
            student.setStudentFirstName("IDs do not match, student not updated");
            student.setStudentLastName("IDs do not match, student not updated");
            return student;
        }

        //Update student
        studentDao.updateStudent(student);
        return student;

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE

        studentDao.deleteStudent(id);

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        //Find student
        Student student = getStudentById(studentId);
        if (student.getStudentFirstName().equals("Student Not Found")) {
            System.out.println("Student not found");
            return;
        }

        //Find course
        Course course = courseService.getCourseById(courseId);
        if (course.getCourseName().equals("Course Not Found")) {
            System.out.print("Course not found");
            return;
        }

        //Perform delete existing student from existing course
        studentDao.deleteStudentFromCourse(studentId, courseId);
        System.out.println("Student: " + studentId + " deleted from course: " + courseId);

        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        //Find student
        Student student = getStudentById(studentId);
        if (student.getStudentFirstName().equals("Student Not Found")) {
            System.out.println("Student not found");
            return;
        }

        //Find course
        Course course = courseService.getCourseById(courseId);
        if (course.getCourseName().equals("Course Not Found")) {
            System.out.print("Course not found");
            return;
        }

        try {
            //Try to add existing student to existing course
            studentDao.addStudentToCourse(studentId, courseId);
            System.out.println("Student: " + studentId + " added to course: " + courseId);

        } catch (DataIntegrityViolationException ex) {
            //Catch if student already enrolled.
            System.out.println("Student: " + studentId + " already enrolled in course: " + courseId);
        }

        //YOUR CODE ENDS HERE
    }
}
