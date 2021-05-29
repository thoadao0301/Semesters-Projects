
import java.util.ArrayList;
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
public class SubjectList extends ArrayList<Subject> implements I_SubjectList{
    Scanner sc = new Scanner(System.in);
    private boolean hasBeenDeleted = false;
    
    @Override
    public int searchSubject(String subjID)
    {
        for(int i = 0; i<size();i++)
        {
            if(subjID.equalsIgnoreCase(get(i).getSubjectID()))
                return i;
        }
        return -1;
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
    public void addSubject() 
    {
        boolean goBack = true;
        while(goBack != false){
        String subID, subName = "";
        int credit = -1;
        System.out.print("Enter subject ID: ");
        subID = checkBlank();
        int pos = searchSubject(subID);
        if(pos >= 0) System.out.println("Subject existed!");
        else{
            System.out.print("Enter subject name: ");
            subName = checkBlank();
            while(credit < 0){
                System.out.print("Enter credit: ");
                String tmp = checkBlank();
                try {
                credit = Integer.parseInt(tmp);
            } catch (Exception e) {
                System.out.println("It must be a number");
                credit = -1;
            }
                if(credit < 0 || credit > 30) {
                    System.out.println("Invalid");
                    credit = -1;
                }
            }   
            add(new Subject(subID, subName, credit));
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
    
    @Override
    public void updateSubject(int pos)
    {
        String subIDNew, subNameNew = "";
        int creditNew = -1;
        System.out.println("===Old information of subject===");
        System.out.println(get(pos).toString());
        System.out.println("=== Update ===");
        System.out.print("Enter subject ID: ");
        
        int p = 0;
        while(p>=0){
        subIDNew = sc.nextLine();
        p = searchSubject(subIDNew);
        if(checkBlankForUp(subIDNew)) System.out.println("Update omitted");
        else{
            if(p < 0)
                get(pos).setSubjectID(subIDNew);
            else
                System.out.println("Exist \nRetype: ");
        }
        }
        System.out.print("Enter subject name: ");
        subNameNew = sc.nextLine();
        if(checkBlankForUp(subNameNew)) System.out.println("Update omitted");
        else{
            get(pos).setSubjName(subNameNew);
        }
        System.out.print("Enter credit: ");
        
        while(creditNew < 0 ){
          String tmp = sc.nextLine();
          if(checkBlankForUp(tmp)) {
              System.out.println("Update omitted");
              break;
          }
          else{
            try {
                creditNew = Integer.parseInt(tmp);
            } catch (Exception e) {
                System.out.println("It must be a number");
                creditNew = -1;
            }
            if(creditNew < 0 || creditNew > 30 ) {
                System.out.println("Invalid");
                creditNew = -1;
            }
            else get(pos).setCredit(creditNew);
          }
        }
        System.out.println("Update Sucessful");
        System.out.println("==Back to update menu==");
    }
    
    
    @Override
    public void deleteSubject(int pos)
    {
        if(get(pos).CanDelete() == false) 
        {
            System.out.println("Subject can not delete");
            hasBeenDeleted = false;
        }
        else{
            System.out.println("Do you want to delete this subjec? Yes/No(any key)");
            String agree;
            agree = sc.nextLine();
            if(agree.equalsIgnoreCase("Yes")){
                remove(pos);
                System.out.println("Subject has been deleted");
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
        if(this.isEmpty())
        {
            System.out.println("There is no student");
            return;
        }
        Menu mnUpdate = new Menu();
        String subID;
        int choice;
        int pos;
        
        mnUpdate.addChoices("1. Update information of subject");
        mnUpdate.addChoices("2. Delete subject ");
        mnUpdate.addChoices("Others. Quit");
        System.out.println("Enter subject ID: ");
        subID = sc.nextLine();
        pos = searchSubject(subID);
        if(pos<0) System.out.println("Subject does not exist \n==Back to main menu==");
        else{
            
         do {
            System.out.println("\n====UPDATE MENU====");
            choice = mnUpdate.getUserChoices();
            System.out.println("==================");
            switch(choice)
            {
                case 1:
                {   if(hasBeenDeleted == false)   
                        updateSubject(pos);
                    else 
                    {
                        System.out.println("This subject have been already deleted\n==Enter other numbers to back main menu==");
                        choice = -1;
                    }
                }break;
                case 2:
                {
                    if(hasBeenDeleted == false){
                        hasBeenDeleted = true;
                        deleteSubject(pos);
                        
                    }
                    else
                    {
                        System.out.println("This subject have been already deleted\n==Enter other numbers to back main menu==");
                        choice = -1;
                    }
                    
                }
                    break;
                default:
                    if(choice != -1){
                        System.out.println("==List after Update==");
                        print();
                        System.out.println("Back to main menu");
                    }
            }
            }while(choice > 0 && choice<3 || choice == -1);
    }
    }
    public void print()
    {
        for (Subject thi : this) 
        {
            System.out.println(thi);
        }
    }
}
