/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvin.course.dao;

import com.elvin.course.DBUtil.DBUtil;
import com.elvin.course.model.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Khal_vh04
 */
public class CourseDaoImpl implements CourseDao {

    @Override
    public List<Course> getAllCourse() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Course> list = new ArrayList<>();
        String sql = "select * from course";
        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Course course=new Course();
                course.setId(rs.getInt("id"));
                course.setCourseName(rs.getString("course_name"));
                course.setDuration(rs.getInt("duration"));
                list.add(course);
            }
        } catch (Exception E) {
            E.printStackTrace();
        }finally{
             DBUtil.close(con,ps,rs);
        }
        return list;

    }

    @Override
    public boolean deleteCourse(int id) {
         Connection con = null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="delete from course where id = ?";
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
    public boolean updateCourse(Course course) {
        boolean result = false;
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "update course set course_name=?,  duration=? where id=?";
        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, course.getCourseName());
            ps.setInt(2, course.getDuration());
            ps.setInt(3, course.getId());

            ps.executeUpdate();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            DBUtil.close(con, ps, null);
        }

        return result;
    }

    @Override
    public Course getCourseById(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Course course=new Course();
        String sql="select * from course where id=?";
        try{
           con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
            
            course.setId(rs.getInt("id"));
                course.setCourseName(rs.getString("course_name"));
                course.setDuration(rs.getInt("duration"));
            
            }
            
        }catch(Exception e){
             e.printStackTrace();
        }finally{
            DBUtil.close(con, ps, null);
        }
        return course;
    }

    @Override
    public boolean registerNewCourse(Course course) {
        Connection con = null;
        PreparedStatement psCourse = null;
        ResultSet rsCourse = null;

        boolean result = false;
        String sqlCourse = "insert into course(course_name,duration)values(?,?)";
        try{
            con = DBUtil.getConnection();
            psCourse = con.prepareStatement(sqlCourse);
            psCourse.setString(1, course.getCourseName());
            psCourse.setInt(2,course.getDuration());
            psCourse.execute();
            result = true;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, psCourse, null);
        }
        return result;

    }
    
    

}
