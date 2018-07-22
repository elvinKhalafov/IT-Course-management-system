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
public class StudentDaoImpl implements StudentDao{

    @Override
    public List<Student> getAllStudent() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();
        String sql = "select * from student";
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
                Teacher teacher=new Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setFirstName(rs.getString("firstName"));
                teacher.setLastName(rs.getString("lastName"));
                Course course=new Course();
                course.setId(rs.getInt("id"));
                course.setCourseName(rs.getString("courseName"));
                course.setDuration(rs.getInt("duration"));
                teacher.setCourse(course);
                student.setTeacher(teacher);
                
                
            }       
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
}
