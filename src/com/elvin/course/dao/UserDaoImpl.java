/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvin.course.dao;

import com.elvin.course.DBUtil.DBUtil;
import com.elvin.course.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Khal_vh04
 */
public class UserDaoImpl implements UserDao{
    public User login(int role,String username, String password){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user=null;
        String sql="select * from user where role=? and username=? and password=?";
        try{
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, role);
            ps.setString(2, username);
            ps.setString(3,password);
            rs = ps.executeQuery();
            if (rs.next()){
                user=new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setRole(rs.getInt("role"));
                user.setUsername(username);
                user.setPassword(password);
               
                
          
             }
            
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBUtil.close(con,ps,rs);
        }
        return user;
    } 
}
