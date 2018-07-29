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
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setTelephoneNumb(rs.getString("telephone_number"));
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setCourseName(rs.getString("course_name"));
                course.setDuration(rs.getInt("duration"));
                teacher.setCourse(course);
                student.setTeacher(teacher);
                list.add(student);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBUtil.close(con,ps,rs);
        }
        return list;

    }

    @Override
    public boolean registerNewStudent(Student student, Teacher teacher) {
        Connection con = null;
        PreparedStatement psStudent = null;
       

        ResultSet rsCourse = null;
        ResultSet rsTeacher=null;
        
        boolean result = false;
        String sqlStudent = "insert into student(first_name,last_name,telephone_number,id_teacher)values(?,?,?,?)";
       

        try {
            con=DBUtil.getConnection();
            psStudent=con.prepareStatement(sqlStudent);
            psStudent.setString(1, student.getFirstName());
            psStudent.setString(2, student.getLastName());
            psStudent.setString(3,student.getTelephoneNumb());
            psStudent.setInt(4,teacher.getId());
            psStudent.execute();
           result=true;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, psStudent, null);
        }
        return result;
    }

    @Override
    public boolean deleteStudent(int id) {
       Connection con = null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="delete from student where id = ?";
        boolean result=false;
        try{
            con = DBUtil.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            result=true;
            
        }catch(Exception e){
             e.printStackTrace();
            
        }finally{
            DBUtil.close(con,ps,rs);
        }
        return result;
    }
    

}
