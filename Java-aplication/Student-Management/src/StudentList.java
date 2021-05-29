
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public class StudentList extends ArrayList<Student> implements I_StudentList{
    Scanner sc = new Scanner(System.in);
    private boolean hasBeenDeleted = false;
    
    @Override
    public int search(String stdID)
    {
        for(int i = 0; i<size();i++)
        {
            if(stdID.equalsIgnoreCase(get(i).getStudentID()))
            {
                return i;
            }
        }
        return -1;
    }
    
    private boolean checkFormatDate(String DOB){
        return(DOB.matches("^\\d{2}/\\d{2}/\\d{4}$"));
    }
    
    private boolean beRealDate(String DOB)
    {
        if (checkBlankForUp(DOB)) return false;
        StringTokenizer date = new StringTokenizer(DOB,"/");
        int d = Integer.parseInt(date.nextToken());
        int m = Integer.parseInt(date.nextToken());
        int y = Integer.parseInt(date.nextToken());
        int maxD = 31;
        if(d<1 || d>31 || m<1 || m>12 || y<1917 || y > 2020)
        {
            return false;
        }
        if( m>=10 && y>=2020) return false;
        if(m==4 || m==6 || m==9 || m==11) maxD = 30;
        else if(m==2)
        {
            if(y%400==0 || ((y%4==0)&&(y%100!=0))) maxD = 29;
            else maxD = 28;
        }
        return d<=maxD;       
    }
    
    private boolean isValidateEmail(String email)
    {
        if(checkBlankForUp(email)) return false;
        return (email.matches("^(.+)@(.+)$"));
    }
    
    private boolean isValidatePhoneNum(String phone)
    {
        if(checkBlankForUp(phone)) return false;
        return (10 <= phone.length() && phone.length() <= 12 && Double.parseDouble(phone) > 0);
    }
    
    public String checkBlank()
    {
        String check = "";
        boolean flag = true;
        while(flag != false)
        {
            check = sc.nextLine();
            if(check.equalsIgnoreCase("") || check.matches(".*[0-9A-Za-z].*") == false){
                flag = true;
                System.out.println("the input is not allowed blank");
                System.out.println("Enter information again: ");
            }
            else flag = false;
        }
        return check;
    }
    
    public boolean checkBlankForUp(String newString){
        if(newString.equalsIgnoreCase("") || newString.matches(".*[0-9A-Za-z].*")==false)
            return true;
        return false;
    }
    
    
    @Override
    public void addNewStudent()
    {
        
        
        boolean goBack = true;
        while(goBack != false) 
        {
            String stdID;
            String fistName, lastName;
            String genderString = "", DOB = "";
            String emailString = "",phone = "";
            System.out.print("Enter student ID: ");
            stdID = checkBlank();
            int pos = search(stdID);
            if(pos>=0)
            {
                System.out.println("Student exist");
            }
            else{
                System.out.print("Enter firstname: ");
                fistName = checkBlank();
                System.out.print("Enter lastname: ");
                lastName = checkBlank();
                boolean flag = false;
                while(flag == false){
                System.out.println("Enter gender: (F/M)");
                genderString = checkBlank();
                if(genderString.matches("F|M|Female|Male"))
                    flag = true;
                else System.out.println("You need to enter F|M|Female|Male");
                }
                
                flag = false;
                while(flag == false)
                {
                    System.out.println("Enter Date of birth: dd/mm/yyyy");
                    DOB = checkBlank();
                    if(!checkFormatDate(DOB)) {flag = false;
                        System.out.println("Wrong format dd/mm/yyyy");
                    }
                    else flag = beRealDate(DOB);
                    if(flag == false)
                    {
                        System.out.println("Wrong date!\n");
                    }
                }
                flag = false;
                while (flag == false) {                    
                    System.out.print("Enter email: \n");
                    emailString = checkBlank();
                    flag = isValidateEmail(emailString);
                    if(flag == false)
                    {
                        System.out.print("Wrong format email!\n");
                    }
                }
                flag = false;
                while (flag == false)
                {
                    System.out.print("Enter phone number: ");
                    phone = checkBlank();
                    flag = isValidatePhoneNum(phone);
                    if(flag == false)
                    {
                        System.out.println("Wrong phone number!");
                    }
                }
                add(new Student(stdID, fistName, lastName,genderString,DOB,emailString,phone));
                System.out.println("Added!");
            }
            System.out.println("==Enter any key to Go back menu== \n==Enter YES to Continue==");
            String agreeString;
            agreeString = sc.nextLine();
            if(agreeString.equalsIgnoreCase("Yes"))
            {
                goBack = true; 
            }
            else goBack = false;
        }       
    }
    
    public void print()
    {
        for (Student thi : this) {
            System.out.println(thi.toString());
        }
    }
        
    
    @Override
    public void updateStudent(int pos)
    {
        String stdID;
        String fistNameNew, lastNameNew;
        String genderStringNew, DOBNew = "";
        String emailStringNew = "",phoneNew = "";
        boolean flag = false;
            System.out.println("==Old information of student==");
            System.out.println(get(pos).toString());
            System.out.print("Update student ID: ");
            
            int p = 0;
            while(p>=0){
                stdID = sc.nextLine();
                p = search(stdID);
            if(checkBlankForUp(stdID))
                System.out.println("Update omitted");
            else{
                
                if(p<0){
                get(pos).setStudentID(stdID);
                System.out.println("Updated!");
                }
                else System.out.println("Exist \nRetype");
            }
            }
            System.out.print("Update the first name: ");
            fistNameNew = sc.nextLine();
            if(checkBlankForUp(fistNameNew))
                System.out.println("Update omitted");
            else{
                get(pos).setFirstName(fistNameNew);
                System.out.println("Updated!");
            }
            
            System.out.print("Update the last name: ");
            lastNameNew = sc.nextLine();
            if(checkBlankForUp(lastNameNew))
                System.out.println("Update omitted");
            else{
                get(pos).setLastName(lastNameNew);
                System.out.println("Updated!");
            }
            
            System.out.print("Update the gender: ");
            genderStringNew = sc.nextLine();
            if(checkBlankForUp(genderStringNew))
                System.out.println("Update omitted");
            else{
                get(pos).setGender(genderStringNew);
                System.out.println("Updated!");
            }
            while(flag == false)
                {
                    System.out.print("Enter Date of birth: ");
                    DOBNew = sc.nextLine();
                    if(!checkFormatDate(DOBNew) && !checkBlankForUp(DOBNew)){ 
                        flag = false;
                        System.out.println("Wrong format dd/mm/yyyy");
                    }
                    else flag = beRealDate(DOBNew);
                    if(flag == false && !checkBlankForUp(DOBNew))
                    {
                        System.out.println("Wrong date! /nRetype!");
                    }   
                    if(checkBlankForUp(DOBNew) && flag == false){
                        System.out.println("Update omitted");
                        flag = true;
                    } 
                }
            if(!checkBlankForUp(DOBNew))
            {
                get(pos).setDOB(DOBNew);
                System.out.println("Updated");
            }
            flag = false;
             while(flag == false)
                {
                    System.out.print("Enter Email: ");
                    emailStringNew = sc.nextLine();
                    flag = isValidateEmail(emailStringNew);
                    if(flag == false && !checkBlankForUp(emailStringNew))
                    {
                        System.out.println("Wrong date! /nRetype!");
                    }   
                    if(checkBlankForUp(emailStringNew) && flag == false){
                        System.out.println("Update omitted");
                        flag = true;
                    } 
                }
            if(!checkBlankForUp(emailStringNew))
            {
                get(pos).setEmail(emailStringNew);
                System.out.println("Updated");
            }
            
            flag = false;
             while(flag == false)
                {
                    System.out.print("Enter phone number: ");
                    phoneNew = sc.nextLine();
                    flag = isValidatePhoneNum(phoneNew);
                    if(flag == false && !checkBlankForUp(phoneNew))
                    {
                        System.out.println("Wrong date! /nRetype!");
                    }   
                    if(checkBlankForUp(phoneNew) && flag == false){
                        System.out.println("Update omitted");
                        flag = true;
                    } 
                }
            if(!checkBlankForUp(phoneNew))
            {
                get(pos).setPhone(phoneNew);
                System.out.println("Updated");
            }
            System.out.println("  Scucessfully Update  ");
            System.out.println("==Back to update menu==");
}
    @Override
    public void deleteStudent(String stdID) {
        int pos = search(stdID);
        if(get(pos).CanDelete() == false) 
        {
            System.out.println("Student can not delete");
            hasBeenDeleted = false;
        }
        else{
            System.out.println("Do you want to remove this student (Yes/No(anykeys)");
            String agree;
            agree = sc.nextLine();
            if(agree.equalsIgnoreCase("Yes")){
                remove(pos);
                System.out.println("Student has been deleted");
            }
            else{
                System.out.println("Removed fail");
                hasBeenDeleted = false;
            }
            System.out.println("==Back to update menu==");
        }
    }
    
    public void Update()
    {
        if(isEmpty()){
            System.out.println("There is no student");
            return;
        }
        Menu mnUpdate = new Menu();
        String stdID;
        int choice;
        int pos;
        
        mnUpdate.addChoices("1. Update information of student");
        mnUpdate.addChoices("2. Delete student ");
        mnUpdate.addChoices("Others. Quit");
        System.out.println("Enter student ID: ");
        stdID = checkBlank();
        pos = search(stdID);
        if(pos<0) System.out.println("Student does not exist\n==Back to main menu==");
        else{
            
         do {
            System.out.println("\n====UPDATE MENU====");
            choice = mnUpdate.getUserChoices();
            System.out.println("==================");
            switch(choice)
            {
                case 1:
                {
                    if(hasBeenDeleted == false)
                        updateStudent(pos);
                    else{
                        choice = -1; 
                       System.out.println("This student can have been already deleted\n==Enter others numbers to back main menu==");
                    }
                }break;
                case 2:
                {
                   if(hasBeenDeleted == false){
                      hasBeenDeleted = true;
                      deleteStudent(stdID);
                      
                   }
                   else{
                       choice = -1; 
                       System.out.println("This student can have been already deleted\n==Enter others numbers to back main menu==");
                   }
                }
                    break;
                default:
                    
                    if(choice!= -1){
                        System.out.println("==List after Update==");
                        print();
                        System.out.println("Back to main menu");
                    }     
            }
            }while(choice > 0 && choice<3 || choice == -1);
    }
    }
}

