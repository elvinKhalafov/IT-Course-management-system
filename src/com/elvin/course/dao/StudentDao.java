/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elvin.course.dao;

import com.elvin.course.model.Course;
import com.elvin.course.model.Student;
import com.elvin.course.model.Teacher;
import java.util.List;

/**
 *
 * @author Khal_vh04
 */
public interface StudentDao {
    List<Student> getAllStudent();
    boolean registerNewStudent(Student student,Teacher teacher);
    boolean deleteStudent(int id);
    boolean updateStudent(Student student);
    
    
    
    
}
