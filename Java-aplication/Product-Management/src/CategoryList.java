
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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
public class CategoryList extends ArrayList<category> implements  I_CategoryList{
    Scanner sc = new Scanner(System.in);
    boolean isUpdate;
    boolean hasBeenDeleted = false;
    
    
    public int searchID(String categoryID)
    {
        return indexOf(new category(categoryID));
    }
    
    public int searchName(String Name)
    {
        for (category cat : this) {
            if(cat.getCategoryName().equalsIgnoreCase(Name))
                return indexOf(cat);
        }
        return -1;
    }
    
    public String checkNullString()
    {
        String check ="";
        boolean flag;
        do{
           check = sc.nextLine();
           String tmp = check.trim();
           if(tmp.equals(""))
           {
               if(!isUpdate) System.out.println("Input is not allowed a blank");
               flag = false;
           }
           else flag = true;
        }while(!flag && !isUpdate);
        return check;
    }
    
    public category input()
    {
        int pos = 0;
        String categoryID = "";
        String categoryName = "";
        while (pos >= 0) {            
            System.out.print("Enter category ID: ");
            categoryID = checkNullString();
            pos = searchID(categoryID);
            if(pos >= 0) System.out.println("Exist");
        }
        pos = 0;
        while (pos >= 0) {            
            System.out.println("Enter category Name: ");
            categoryName = checkNullString();
            pos = searchName(categoryName);
            if(pos >= 0) System.out.println("Exist");
        }
        category cat = new category(categoryID, categoryName);
        return cat;
    }
    

    
    @Override
    public void addNewCategory(){
        isUpdate = false;
        category cat = input();
        add(cat);
        System.out.println("Added");
    }
    
    @Override
    public void updateCategory(int pos)
    {
        isUpdate = true;
        System.out.println("===Old information===");
        System.out.println(get(pos).toString());
        System.out.println("Enter blank if not need updated");
        category cat = input();
        get(pos).setCategoryID(cat.getCategoryID());
        get(pos).setCategoryName(cat.getCategoryName());
    }
    
    
    @Override
    public void deleteCategory(int pos)
    {
        System.out.println("Do you want to remove this category (Yes/No(anykeys)");
        String agree;
        agree = sc.nextLine();
       if(agree.equalsIgnoreCase("Yes")){
            remove(pos);
            System.out.println("Category has been deleted");
            hasBeenDeleted = true;
        }
        else{
            System.out.println("Removed fail");
            hasBeenDeleted = false;
        }
        System.out.println("==Back to update menu==");
    }
    
    public void update(String fName)
    {
        if(isEmpty()){
            System.out.println("There is no category");
            return;
        }
        Menu mnUpdate = new Menu();
        String catID;
        int choice;
        int pos;
        //hasBeenDeleted = false;
        mnUpdate.addChoices("1. Update information of category");
        mnUpdate.addChoices("2. Delete category ");
        mnUpdate.addChoices("Others. Quit");
        System.out.println("Enter category ID: ");
        catID = sc.nextLine();
        pos = searchID(catID);
        if(pos<0) System.out.println("category does not exist\n==Back to main menu==");
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
                        updateCategory(pos);
                    else{
                       choice = -1; 
                       System.out.println("This category can have been already deleted\n==Enter others numbers to back main menu==");
                    }
                }break;
                case 2:
                {
                   if(hasBeenDeleted == false){
                      deleteCategory(pos);
                      
                   }
                   else{
                       choice = -1; 
                       System.out.println("This category can have been already deleted\n==Enter others numbers to back main menu==");
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
            saveToFile(fName);
    }
    }
    
    public void readFile(String fName)
    {
        try {
            File f = new File(fName);
            if(!f.exists())
            {
                System.out.println("File does not exist");
                return;
            }
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String category;
            while((category = bf.readLine()) != null)
            {
                String stk[] = category.split(", ");
                String categoryID = stk[0];
                String categoryName = stk[1];
                this.add(new category(categoryID, categoryName));
            }
            bf.close();fr.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void saveToFile(String fName){
        if(isEmpty()){
            System.out.println("Empty List");
            System.out.println("Save fail!");
            return;
        }
        try {
            File f = new File(fName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (category cat : this) {
                pw.println(cat.toString());
            }
            pw.close();fw.close();
            System.out.println("Save successfully");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Save fail!");
        }
        System.out.println("==Back to main menu==");
    }
    
    public void print()
    {
        for (category thi : this) {
            System.out.println(thi.toString());
        }
    }
}
