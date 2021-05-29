
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public class Student {
    private String studentID;
    private String firstName;
    private String lastName;
    private String gender;
    private String DOB;
    private String email;
    private String phone;
    
    private boolean canDelete = true;

    public Student(String studentID, String firstName, String lastName, String gender, String DOB, String email, String phone) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.DOB = DOB;
        this.email = email;
        this.phone = phone;
    }

    public Student() {
        this.studentID = "";
        this.firstName = "";
        this.lastName = "";
        this.gender = "";
        this.DOB = "";
        this.email = "";
        this.phone = "";
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean CanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }
    
    public String toString()
    {
        return (" " + this.studentID + "\t" + this.lastName + " " + this.firstName + "\t" +this.gender+ "\t"+ this.email + "\t" + this.phone + "\t" + this.DOB);
    }
    
    
  
}
