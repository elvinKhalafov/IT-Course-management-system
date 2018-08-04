package com.elvin.course.model;

/**
 *
 * @author Khal_vh04
 */
public class Teacher {

    private int id;
    private Course course;
    private String firstName;
    private String lastName;
    public Teacher(){
        
    }

    public Teacher(int id, String firstName, String lastName, Course id_course) {
        this.id = id;
        this.course = id_course;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Teacher(Course course, String firstName, String lastName) {
        this.course = course;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    

}
