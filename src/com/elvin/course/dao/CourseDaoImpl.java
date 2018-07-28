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

}
