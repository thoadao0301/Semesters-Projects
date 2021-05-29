
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
public class ProductList extends ArrayList<Product> implements I_ProductList{
    Scanner sc = new Scanner(System.in);
    boolean isUpdate = false;
    boolean hasBeenDeleted = false;
    
    public int find(String productID)
    {
        return indexOf(new Product(productID));
    }
    
    public String checkNull(){
        String check = "";
        boolean flag = false;
        while(!flag){
            String tmp = sc.nextLine();
            check = tmp.trim();
            if(check.equalsIgnoreCase("") && !isUpdate){
                System.out.println("Information is not allowed blank");
                flag = false;
            }
            else flag = true;
        }
        return check;
    }
    
    public String CheckDoubleNumber()
    {
        String check = "";
        boolean flag = false;
        while(!flag)
        {
            check = checkNull();
            if(check.equalsIgnoreCase("") && isUpdate)
                return check;
            try {
                double c = Double.parseDouble(check);
                flag = (c < 0) ? false : true;
            } catch (Exception e) {
                System.out.println("It must be a number");
                flag = false;
            }
        }
        return  check;
    }
    
    public String CheckIntNumber()
    {
        String check = "";
        boolean flag = false;
        while(!flag)
        {
            check = checkNull();
            if(check.equalsIgnoreCase("") && isUpdate)
                return check;
            try {
                int c = Integer.parseInt(check);
                flag = (c < 0) ? false : true;
            } catch (Exception e) {
                System.out.println("It must be a number");
                flag = false;
            }
        }
        return  check;
    }
    
    public int checkCat(String catID, CategoryList catList)
    {
        return catList.searchID(catID);
    }
    
    public Product input(Product prd, CategoryList catList)
    {
        System.out.println("Enter product ID: ");
        String code = checkNull();
        int pos = find(code);
        if(pos >= 0) 
        {
            System.out.println("Exist");
            return null;
        }
        else{
            prd.setProductID(code);
            System.out.println("Enter product name: ");
            prd.setPName(checkNull());
            System.out.println("Enter price: ");
            prd.setPrice(CheckDoubleNumber());
            System.out.println("Enter quantity: ");
            prd.setQuantity(CheckIntNumber());
            int p = -1;
            while(p <= -1)
            {
                 System.out.println("Enter category ID: ");
                 String catID = checkNull();
                 p = checkCat(catID, catList);
                 if(catID.equalsIgnoreCase("") && p<0) p = 0;
                 else if(p<0 && !catID.equals("")) System.out.println("Category does not exist");
                 else prd.setCatID(catID);
            }
        }
        return prd;
    }
    
    @Override
    public void addNewProduct(CategoryList catList)
    {
        
        isUpdate = false;
        Product str = new Product();
        
        if(input(str,catList) == null) System.out.println("Add fail");
        else{
            if(add(str)) System.out.println("Added");
        }
    }
    
    @Override
    public void updateInfoProduct( CategoryList catList)
    {
        System.out.println("Enter product ID: ");
        String productID = sc.nextLine();
        int pos = find(productID);
        if(pos < 0 ) System.out.println("Product does not exist");
        else{
        isUpdate = true;
        System.out.println("====Old Information====");
        System.out.println(get(pos).toString());
        System.out.println("=========Update=========");
        System.out.println("Enter Blank if not need updated");
        if(input(get(pos),catList) == null) System.out.println("Update fail");
        else System.out.println("Updated");
        }
        System.out.println("Back to main menu");
    }
    
    
    @Override
    public void deleteProduct(){
        System.out.println("Enter product ID: ");
        String productID = sc.nextLine();
        int pos = find(productID);
        if(pos < 0) System.out.println("Product does not exist");
        else{
            System.out.println("Do you want to remove this category (Yes/No(anykeys)");
            String agree;
            agree = sc.nextLine();
            if(agree.equalsIgnoreCase("Yes")){
                remove(pos);
                System.out.println("Category has been deleted");
            }
            else{
                System.out.println("Removed fail");
                
            }
            System.out.println("==Back to main menu==");
        }
    }
    
    public void update(CategoryList catList, String fName)
    {
        if(isEmpty()){
            System.out.println("There is no product");
            return;
        }
        Menu mnUpdate = new Menu();
        
        int choice;
        
        //hasBeenDeleted = false;
        mnUpdate.addChoices("1. Update information of product");
        mnUpdate.addChoices("2. Delete product ");
        mnUpdate.addChoices("Others. Quit");
        System.out.println("===Information of product===");
        print();
         do {
            System.out.println("\n====UPDATE MENU====");
            choice = mnUpdate.getUserChoices();
            System.out.println("==================");
            switch(choice)
            {
                case 1:
                {
                    updateInfoProduct(catList);
                   
                }break;
                case 2:
                {
                  deleteProduct();
                }
                 break;
                 
            }
            }while(choice == 1 || choice == 2 || choice == -1);
         System.out.println("===List after update===");
         print();
         saveToFile(fName);
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
                String productID = stk[0];
                String productName = stk[1];
                String catID = stk[2];
                double price = Double.parseDouble(stk[3]);
                int quantity = Integer.parseInt(stk[4]);
                

                this.add(new Product(productID, productName, catID, price, quantity));
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
            for (Product prd : this) {
                pw.println(prd.toString());
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
        for (Product thi : this) {
            System.out.println(thi.toString());
        }
    }
    
    
}
