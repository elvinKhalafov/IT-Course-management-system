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
        String sql = "select s.id as student_id, s.first_name as student_first_name, s.last_name as student_last_name, s.telephone_number, t.id as teacher_id, t.first_name as teacher_first_name, t.last_name as teacher_last_name,c.id as course_id, c.course_name, c.duration from student s inner join teachers t on s.id_teacher=t.id \n"
                + "inner join course c on t.id_course=c.id";
        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setFirstName(rs.getString("student_first_name"));
                student.setLastName(rs.getString("student_last_name"));
                student.setTelephoneNumb(rs.getString("telephone_number"));
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("teacher_id"));
                teacher.setFirstName(rs.getString("teacher_first_name"));
                teacher.setLastName(rs.getString("teacher_last_name"));
                Course course = new Course();
                course.setId(rs.getInt("course_id"));
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

    @Override
    public boolean updateStudent(Student student) {
        
        boolean result=false;
        Connection con = null;
        PreparedStatement ps=null;
        String sql="update student set first_name=?,  last_name=?,id_teacher=?,telephone_number=? where id=?";
         try{
            con = DBUtil.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, student.getFirstName());
            ps.setString(2,student.getLastName());
            ps.setInt(3, student.getTeacher().getId());
            ps.setString(4,student.getTelephoneNumb());
            ps.setInt(5,student.getId());
            
            ps.executeUpdate();
            result=true;
            
        }catch(Exception e){
             e.printStackTrace();
            
        }finally{
            DBUtil.close(con,ps,null);
        }
        
        
        
        return result;
    }

    @Override
    public Student getStudentById(int id) {
      Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student=new Student();
      String sql = "select s.id as student_id, s.first_name as student_first_name, s.last_name as student_last_name, s.telephone_number, t.id as teacher_id, t.first_name as teacher_first_name, t.last_name as teacher_last_name,c.id as course_id, c.course_name, c.duration from student s inner join teachers t on s.id_teacher=t.id \n"
                + "inner join course c on t.id_course=c.id where s.id=?";
        try{
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
             while (rs.next()){
                 
                student.setId(rs.getInt("student_id"));
                student.setFirstName(rs.getString("student_first_name"));
                student.setLastName(rs.getString("student_last_name"));
                student.setTelephoneNumb(rs.getString("telephone_number"));
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("teacher_id"));
                teacher.setFirstName(rs.getString("teacher_first_name"));
                teacher.setLastName(rs.getString("teacher_last_name"));
                Course course = new Course();
                course.setId(rs.getInt("course_id"));
                course.setCourseName(rs.getString("course_name"));
                course.setDuration(rs.getInt("duration"));
                teacher.setCourse(course);
                student.setTeacher(teacher);
             }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
             DBUtil.close(con,ps,null);
        }
        return student;
    }

   
    
    

}
