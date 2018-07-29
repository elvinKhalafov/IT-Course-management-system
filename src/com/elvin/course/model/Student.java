package com.elvin.course.model;

/**
 *
 * @author Khal_vh04
 */
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private Teacher teacher;
    private String telephoneNumb;
    public Student(){
        
    }

    public Student(int id, String firstName, String lastName, Teacher teacher, String telephoneNumb) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teacher = teacher;
        this.telephoneNumb = telephoneNumb;
    }

    public Student(String firstName, String lastName, Teacher teacher, String telephoneNumb) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.teacher = teacher;
        this.telephoneNumb = telephoneNumb;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getTelephoneNumb() {
        return telephoneNumb;
    }

    public void setTelephoneNumb(String telephoneNumb) {
        this.telephoneNumb = telephoneNumb;
    }
    
}
