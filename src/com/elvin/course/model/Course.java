
package com.elvin.course.model;

/**
 *
 * @author Khal_vh04
 */
public class Course {
    private int id;
    private String courseName;
    private int duration;
    public Course(){
        
    }

    public Course(int id, String course_name, int duration) {
        this.id = id;
        this.courseName = course_name;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    
}
