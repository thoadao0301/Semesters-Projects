
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public class GradeList extends ArrayList<Grade> implements I_Grade{
    Scanner sc = new Scanner(System.in);
    
    public int search(String stdID, String sbjID){
        for(int i = 0; i<size(); i++){
            if(stdID.equalsIgnoreCase(get(i).getStd().getStudentID()) 
                    && sbjID.equalsIgnoreCase(get(i).getSbj().getSubjectID()))
                return i;
        }
        return -1;
    }
    
    
    private double checkValidGrade()
    {
        double check = 0.0;
        boolean flag = false;
        while(flag != true)
        {
            try {
                check = Double.parseDouble(sc.nextLine());
                flag = true;
            } catch (Exception e) {
                System.out.println("It must be a number");
                flag = false;
            }
            
            if(check < 0.0 || check > 10.0 || flag == false){
                System.out.println("ReType: ");
                flag = false;
            }
            
        }
        return check;
    }
    
    
    @Override
    public void addNewGrade(StudentList stdList, SubjectList subList)
    {
        if(stdList.isEmpty()){
            System.out.println("There is no student");
            return;
        }
        if(subList.isEmpty())
        {
            System.out.println("There is no subject");
            return;
        }
        boolean goBack = true;
        while(goBack != false){
            double lab, Ptest, FE;
            String stdID; 
            String sbjID;
            System.out.println("==List of student ID==");
            for (Student student : stdList) {
                System.out.println("     " + student.getStudentID());
            }
            System.out.println("=================");
            System.out.print("Enter Student ID: ");
            stdID = sc.nextLine();
            int pos = stdList.search(stdID);
            if(pos < 0) System.out.println("Student does not exist");
            else{
                System.out.println("===List of subject ID===");
                for (Subject subject : subList) {
                     System.out.println("    " + subject.getSubjectID());
                 }
                System.out.println("===================");
                System.out.print("Enter subject ID: ");
                sbjID = sc.nextLine();
                int p = subList.searchSubject(sbjID);
                if(p<0) System.out.println("Subject does not exist");
                else{
                    int pInGrade = search(stdID, sbjID);
                    if (pInGrade >= 0) {
                        System.out.println("Student has already graded");
                        System.out.println("Do you want to override? Yes/ No");
                        String agree;
                        agree = sc.nextLine();
                        if(agree.equalsIgnoreCase("Yes")){
                            System.out.print("Enter Lab: ");
                            lab = checkValidGrade();
                            get(pInGrade).setLab(lab);
                            System.out.print("Enter Progress test: ");
                            Ptest = checkValidGrade();
                            get(pInGrade).setPtest(Ptest);
                            System.out.print("Enter Final Exam: ");
                            FE = checkValidGrade();
                            get(pInGrade).setFE(FE);
                            System.out.println("Overried sccessfully");
                        }
                        else System.out.println("The old grade does not change");
                    }
                    else{
                        System.out.print("Enter Lab: ");
                        lab = checkValidGrade();
                        System.out.print("Enter Progress test: ");
                        Ptest = checkValidGrade();
                        System.out.print("Enter Final Exam: ");
                        FE = checkValidGrade();
                        add(new Grade( stdList.get(pos), subList.get(p), lab, Ptest, FE));
                        System.out.println("Added!");
                    }
                    stdList.get(pos).setCanDelete(false);
                    subList.get(p).setCanDelete(false);
                }
            }
            System.out.println("==Enter any key to Go back menu== \n==Enter YES to Continue== ");
            String agreeString;
            agreeString = sc.nextLine();
            if(agreeString.equalsIgnoreCase("Yes"))
            {
                goBack = true; 
            }
            else goBack = false;
        }
    }
    
    public int searchStdInGrade(String stdID){
        for(int i = 0;i<size();i++){
            if(stdID.equalsIgnoreCase(get(i).getStd().getStudentID()))
                return i;
        }
        return -1;
    }
    
    public int searchSubInGrade(String subID)
    {
        for (int i = 0; i < size(); i++) {
            if(subID.equalsIgnoreCase(get(i).getSbj().getSubjectID()))
                return i;
        }
        return -1;
    }
    
    @Override
    public void printStudentReport(StudentList stdList)
    {
        if (this.isEmpty()) {
            System.out.println("The empty List");
            return;
        }
        
        
        boolean goBack = true;
        while(goBack != false){
        String stdID;
        Collections.sort(this,Grade.objCompare);
        System.out.println("==List of student ID==");
        for (Student student : stdList) {
            System.out.println("     " + student.getStudentID());
        }
        System.out.println("=================");
        System.out.print("Enter student ID: ");
        stdID = sc.nextLine();
        int pos = searchStdInGrade(stdID);
        int p = stdList.search(stdID);
        if(p<0)System.out.println("Student dose not exit");
        else{
        if(pos<0) System.out.println("Student does not have grade report");
        else{
            System.out.println("Student name: " + get(pos).getStd().getLastName() + " " + get(pos).getStd().getFirstName());
            System.out.println("|++No++|++Subject name++|++Average mark++|++Status++|");
            System.out.println();
            int count = 0;
            for(int i=0;i<size();i++){
                if(stdID.equalsIgnoreCase(get(i).getStd().getStudentID())){
                     count++;
                    System.out.printf("   %-8s%-20s%-15.2f%s\n",count,get(i).getSbj().getSubjName(),get(i).calculateAverage(),get(i).getStatus());
                }
            }
        }
        }
        System.out.println("==Enter any key to Go back menu== \n==Enter YES to Continue== ");
            String agreeString;
            agreeString = sc.nextLine();
            if(agreeString.equalsIgnoreCase("Yes"))
            {
                goBack = true; 
            }
            else goBack = false;
    }
    }
    
    @Override
    public void printSubjectReport(SubjectList subList){
        if(this.isEmpty()){
            System.out.println("The empty list");
            return;
        }
        boolean goBack = true;
        while(goBack != false){
        String stdID;
        String subID;
        Collections.sort(this,Grade.objCompareStd);
        System.out.println("===List of subject ID===");
        for (Subject subject : subList) {
            System.out.println("    " + subject.getSubjectID());
        }
        System.out.println("===================");
        System.out.print("Enter subject ID: ");
        subID = sc.nextLine();
        int pos = searchSubInGrade(subID);
        int p = subList.searchSubject(subID);
        if(p<0) System.out.println("Subject dose not exist");
        else{
        if(pos<0 && p >= 0) System.out.println("Subject does not have Grade Information");
        else{
            System.out.println("Subject name: " + get(pos).getSbj().getSubjName());
            System.out.println("|++Student ID++|++Student name++|++Average mark++|++Status++|");
            for(int i = 0;i<size();i++){
                if(subID.equalsIgnoreCase(get(i).getSbj().getSubjectID())){
                    System.out.printf("   %-15s%-10s %-9s%-14.2f%s\n",
                            get(i).getStd().getStudentID(),get(i).getStd().getLastName(),
                            get(i).getStd().getFirstName(),get(i).calculateAverage(),get(i).getStatus());
                }
            }
        }
        }
        System.out.println("==Enter any key to Go back menu== \n==Enter YES to Continue== ");
            String agreeString;
            agreeString = sc.nextLine();
            if(agreeString.equalsIgnoreCase("Yes"))
            {
                goBack = true; 
            }
            else goBack = false;
    }    
    }
    
    public void print(){
        for (Grade thi : this) {
            System.out.println(thi.getStd().getStudentID() + ", " + thi.getSbj().getSubjName() + ", " + thi.calculateAverage() + ", " + thi.getStatus());
        }
    } 
}
