
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
public class Manage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();
        StudentList list = new StudentList();
        SubjectList listOfSub = new SubjectList();
        GradeList listOfGrade = new GradeList();
        int choice;
        
        menu.addChoices("1. Add new student.");
        menu.addChoices("2. Update Student. ");
        menu.addChoices("3. Add new Subject.");
        menu.addChoices("4. Update Subject.");
        menu.addChoices("5. Enter Grade.");
        menu.addChoices("6. Display Student Grade Report.");
        menu.addChoices("7. Display Subject Grade Report.");
        menu.addChoices("Other. Quit");
        
        System.out.println("=====Manage Student=====");
         do { 
            choice = menu.getUserChoices();
            System.out.println("==================");
            switch(choice)
            {
                case 1:
                {
                   list.addNewStudent();
                }break;
                case 2:
                    System.out.println("==List before Update==");
                    list.print();
                    list.Update();
                    break;
                case 3:
                    listOfSub.addSubject();break;
                    
                case 4:
                    System.out.println("==List before Update==");
                    listOfSub.print();
                    listOfSub.Update();
                    break;
                case 5:
                    listOfGrade.addNewGrade(list, listOfSub);break;
                case 6:
                    listOfGrade.printStudentReport(list);break;
                case 7:
                    listOfGrade.printSubjectReport(listOfSub);break;
                default:
                {
                    if(choice!=-1) System.out.println("Quitt");break;
                }
            }
        } while (choice > 0 && choice < 8 || choice == -1);
        
    }
}
 
