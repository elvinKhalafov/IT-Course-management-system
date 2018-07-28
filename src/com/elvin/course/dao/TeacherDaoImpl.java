/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvin.course.dao;


import com.elvin.course.DBUtil.DBUtil;
import com.elvin.course.model.Course;


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
public class TeacherDaoImpl implements TeacherDao{

    @Override
    public List<Teacher> getTeacherByCourseId(int id) {
         Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Teacher> list = new ArrayList<>();
        String sql="select  * from teachers where id_course=?";
        try{
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
           while(rs.next()){
            Teacher teacher = new Teacher();
            teacher.setId(rs.getInt("id"));
            teacher.setFirstName(rs.getString("first_name"));
            teacher.setLastName(rs.getString("last_name"));
           list.add(teacher);
           }
            
            
        }catch(Exception E){
            E.printStackTrace();
        }finally{
            DBUtil.close(con, ps, rs);
        }
     return list;   
    }
    
}
