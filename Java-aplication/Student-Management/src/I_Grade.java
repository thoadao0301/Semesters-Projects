/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public interface I_Grade {
    public void addNewGrade(StudentList stdList, SubjectList subList);
    public void printStudentReport(StudentList stdList);
    public void printSubjectReport( SubjectList subList);
}
