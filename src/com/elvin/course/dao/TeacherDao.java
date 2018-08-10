/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvin.course.dao;

import com.elvin.course.model.Course;
import com.elvin.course.model.Teacher;
import java.util.List;

/**
 *
 * @author Khal_vh04
 */
public interface TeacherDao {
     List<Teacher> getTeacherByCourseId(int id);
     List<Teacher> getAllTeacher();
     boolean deleteTeacher(int id);
     public Teacher getTeacherById(int id);
     boolean registerNewTeacher(Teacher teacher, Course course);
     boolean updateTeacher(Teacher teacher);
}
