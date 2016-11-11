/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlvUserClient.service.user;

import com.mlv.client.service.student.Student;
import com.mlv.client.service.student.StudentService;
import com.mlv.client.service.student.StudentService_Service;
import com.mlv.client.service.teacher.Teacher;
import com.mlv.client.service.teacher.TeacherService;
import com.mlv.client.service.teacher.TeacherService_Service;
import com.mlvUserClient.binding.PersonEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
public class UserServiceClient {

    /**
     * Check if the given person is a student or a teacher
     *
     * @param o
     * @return
     */
    public static String getUserType(PersonEntity o) {
        StudentService_Service sv = new StudentService_Service();
        StudentService service = sv.getStudentServicePort();
        TeacherService_Service sv_t = new TeacherService_Service();
        TeacherService service_t = sv_t.getTeacherServicePort();

        if (service.getStudentByID(o.getId()) != null) {
            return "Student";
        }

        if (service_t.getTeacherByID(o.getId()) != null) {
            return "Teacher";
        }
        return "Unknown";
    }

    public static PersonEntity retrieveMLVUserByID(int id) {
        StudentService_Service sv = new StudentService_Service();
        StudentService service = sv.getStudentServicePort();
        TeacherService_Service sv_t = new TeacherService_Service();
        TeacherService service_t = sv_t.getTeacherServicePort();

        if (service.getStudentByID(id) != null) {
            return PersonEntity.fromStudent(service.getStudentByID(id));
        }

        if (service_t.getTeacherByID(id) != null) {
            return PersonEntity.fromTeacher(service_t.getTeacherByID(id));
        }

        return null;
    }

    public static boolean userHasEnoughMoney(int user_id, int purchase_amount) {
        return userHasEnoughMoney(retrieveMLVUserByID(user_id), purchase_amount);
    }

    public static boolean userHasEnoughMoney(PersonEntity o, int purchase_amount) {
        if (getUserType(o).equals("Student")) {
            return studentHasEnoughMoney(o.getId(), purchase_amount);
        }
        return teacherHasEnoughMoney(o.getId(), purchase_amount);
    }

    private static boolean studentHasEnoughMoney(int student_id, int purchase_amount) {
        StudentService_Service sv = new StudentService_Service();
        StudentService service = sv.getStudentServicePort();
        return service.studentHasEnoughMoney(student_id, purchase_amount);
    }

    private static boolean teacherHasEnoughMoney(int teacher_id, int purchase_amount) {
        TeacherService_Service sv = new TeacherService_Service();
        TeacherService service = sv.getTeacherServicePort();
        return service.teacherHasEnoughMoney(teacher_id, purchase_amount);
    }

    /**
     * Retrieve all the students/teachers using the specific web services
     *
     * @return
     */
    public static List<PersonEntity> retrieveMLVUsers() {
        List<PersonEntity> persons = new ArrayList<>();

        UserServiceClient.retrieveMLVStudents().stream().forEach((s) -> {
            persons.add(PersonEntity.fromStudent(s));
        });

        UserServiceClient.retrieveMLVTeachers().stream().forEach((t) -> {
            persons.add(PersonEntity.fromTeacher(t));
        });

        return persons;
    }

    /**
     * Retrieve all the students by using the web service StudentService
     *
     * @return
     */
    public static List<Student> retrieveMLVStudents() {
        StudentService_Service sv = new StudentService_Service();
        StudentService service = sv.getStudentServicePort();

        return service.getAllStudents();
    }

    /**
     * Retrieve all the teachers by using the web service TeacherService
     *
     * @return
     */
    public static List<Teacher> retrieveMLVTeachers() {
        TeacherService_Service sv = new TeacherService_Service();
        TeacherService service = sv.getTeacherServicePort();

        return service.getAllTeachers();
    }

}
