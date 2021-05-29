/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public interface I_StudentList {
    public int search(String stdID);
    public void addNewStudent();
    public void updateStudent(int pos);
    public void deleteStudent(String stdID);
    
}
