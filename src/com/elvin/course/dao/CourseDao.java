/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvin.course.dao;

import com.elvin.course.model.Course;
import java.util.List;

/**
 *
 * @author Khal_vh04
 */
public interface CourseDao {
    List<Course>getAllCourse();
    boolean deleteCourse(int id);
    boolean updateCourse(Course course);
    public Course getCourseById(int id);
    boolean registerNewCourse(Course course);
}
