/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvin.course.dao;

import com.elvin.course.DBUtil.DBUtil;
import com.elvin.course.model.Course;
import com.elvin.course.model.Student;
import com.elvin.course.model.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khal_vh04
 */
public class StudentDaoImpl implements StudentDao {

    @Override
    public List<Student> getAllStudent() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();
        String sql = "select s.first_name as student_first_name, s.last_name as student_last_name, s.telephone_number, t.first_name as teacher_first_name, t.last_name as teacher_last_name, c.course_name, c.duration from student s inner join teachers t on s.id_teacher=t.id \n"
                + "inner join course c on t.id_course=c.id;";
        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setFirstName(rs.getString("firstName"));
                student.setLastName(rs.getString("lastName"));
                student.setTelephoneNumb(rs.getString("telephoneNumb"));
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setFirstName(rs.getString("firstName"));
                teacher.setLastName(rs.getString("lastName"));
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setCourseName(rs.getString("courseName"));
                course.setDuration(rs.getInt("duration"));
                teacher.setCourse(course);
                student.setTeacher(teacher);
                list.add(student);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    @Override
    public boolean registerNewStudent(Student student, Teacher teacher, Course course) {
        Connection con = null;
        PreparedStatement psStudent = null;
        PreparedStatement psTeacher = null;
        PreparedStatement psCourse = null;

        ResultSet rsCourse = null;
        ResultSet rsTeacher=null;
        int newCourseId;
        int newTeacherId;
        boolean result = false;
        String sqlStudent = "insert into student(first_name,last_name,telephone_number,id_teacher)values(?,?,?,?)";
        String sqlTeacher = "insert into teachers(first_name,last_name,id_course)values)(?,?)";
        String sqlCourse = "insert into course(course_name,duration)values(?,?)";

        try {
            con = DBUtil.getConnection();
            psCourse = con.prepareStatement(sqlCourse, PreparedStatement.RETURN_GENERATED_KEYS);
            psCourse.setString(1, course.getCourseName());
            psCourse.setInt(2, course.getDuration());
            rsCourse = psCourse.getGeneratedKeys();
            psCourse.execute();
            if (rsCourse.next()) {
                newCourseId = rsCourse.getInt(1);
            } else {
                throw new Exception();
            }
            psTeacher = con.prepareStatement(sqlTeacher, PreparedStatement.RETURN_GENERATED_KEYS);
            psTeacher.setString(1, teacher.getFirstName());
            psTeacher.setString(2, teacher.getLastName());
            psTeacher.setInt(3, newCourseId);
            rsTeacher = psTeacher.getGeneratedKeys();
            psTeacher.execute();
            if (rsTeacher.next()) {
                newTeacherId = rsTeacher.getInt(1);
            } else {
                throw new Exception();
            }
            
            psStudent=con.prepareStatement(sqlStudent, PreparedStatement.RETURN_GENERATED_KEYS);
            psStudent.setString(1, student.getFirstName());
            psStudent.setString(2, student.getLastName());
            psStudent.setString(3,student.getTelephoneNumb());
            
            psStudent.setInt(4,newTeacherId );
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, psStudent, null);
        }
        return result;
    }

}
