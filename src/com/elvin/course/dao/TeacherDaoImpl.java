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
public class TeacherDaoImpl implements TeacherDao {

    @Override
    public List<Teacher> getTeacherByCourseId(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Teacher> list = new ArrayList<>();
        String sql = "select  * from teachers where id_course=?";
        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));
                list.add(teacher);
            }

        } catch (Exception E) {
            E.printStackTrace();
        } finally {
            DBUtil.close(con, ps, rs);
        }
        return list;
    }

    @Override
    public List<Teacher> getAllTeacher() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Teacher> list = new ArrayList<>();
        String sql = "select t.id as teacher_id, t.first_name as teacher_first_name, t.last_name as teacher_last_name, c.course_name, c.duration, c.id as course_id\n"
                + "from teachers t inner join course c on t.id_course=c.id";
        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("teacher_id"));
                teacher.setFirstName(rs.getString("teacher_first_name"));
                teacher.setLastName(rs.getString("teacher_last_name"));
                Course course = new Course();
                course.setId(rs.getInt("course_id"));
                course.setCourseName(rs.getString("course_name"));
                course.setDuration(rs.getInt("duration"));
                teacher.setCourse(course);
                list.add(teacher);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, ps, rs);
        }
        return list;

    }

    @Override
    public boolean deleteTeacher(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "delete from teachers where id = ?";
        boolean result = false;
        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            DBUtil.close(con, ps, rs);
        }
        return result;
    }

    @Override
    public Teacher getTeacherById(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Teacher teacher = new Teacher();
        String sql = "select t.id as teacher_id,t.first_name as teacher_first_name , t.last_name as teacher_last_name,c.id as course_id,  c.course_name,c.duration"
                + " from teachers t inner join course c on t.id_course=c.id where t.id=?";

        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                teacher.setId(rs.getInt("teacher_id"));
                teacher.setFirstName(rs.getString("teacher_first_name"));
                teacher.setLastName(rs.getString("teacher_last_name"));
                Course course = new Course();
                course.setId(rs.getInt("course_id"));
                course.setCourseName(rs.getString("course_name"));
                course.setDuration(rs.getInt("duration"));
                teacher.setCourse(course);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, ps, null);
        }

        return teacher;
    }

    @Override
    public boolean registerNewTeacher(Teacher teacher, Course course) {
        Connection con = null;
        PreparedStatement psTeacher = null;
        ResultSet rsCourse = null;

        boolean result = false;
        String sqlTeacher = "insert into teacher(first_name,last_name,id_course)values(?,?,?)";
        try {
            con = DBUtil.getConnection();
            psTeacher = con.prepareStatement(sqlTeacher);
            psTeacher.setString(1, teacher.getFirstName());
            psTeacher.setString(2, teacher.getLastName());
            psTeacher.setInt(3, course.getId());

            psTeacher.execute();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(con, psTeacher, null);
        }
        return result;

    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        boolean result = false;
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "update teacher set first_name=?,  last_name=?,id_course=? where id=?";
        try {
            con = DBUtil.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, teacher.getFirstName());
            ps.setString(2, teacher.getLastName());
            ps.setInt(3, teacher.getCourse().getId());

            ps.setInt(5, teacher.getId());

            ps.executeUpdate();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            DBUtil.close(con, ps, null);
        }

        return result;
    }

}
