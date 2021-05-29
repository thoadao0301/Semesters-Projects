
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
        CategoryList list = new CategoryList();
        ProductList productList = new ProductList();
        OrderList orderList = new OrderList();
       
        int choice;
        
        menu.addChoices("1. Add new category.");
        menu.addChoices("2. Update category. ");
        menu.addChoices("3. Add new product.");
        menu.addChoices("4. Update product.");
        menu.addChoices("5. Order product.");
        menu.addChoices("6. Show order list report.");
        menu.addChoices("Other. Quit");
        
        System.out.println("=====Manage Product=====");
        System.out.println("Enter category product file: ");
        String fName = list.sc.nextLine();
        list.readFile(fName);
        list.print();
        System.out.println("Enter product file:");
        String fPName = productList.sc.nextLine() ;
        productList.readFile(fPName);
        productList.print();
         do { 
            choice = menu.getUserChoices();
            System.out.println("==================");
            switch(choice)
            {
                case 1:
                {
                   list.addNewCategory();
                   list.saveToFile(fName);
                }break;
                case 2:
                    System.out.println("==List before Update==");
                    list.print();
                    list.update(fName);
                    break;
                case 3:
                    productList.addNewProduct(list);
                    productList.saveToFile(fPName);break;
                    
                case 4:
                    productList.update(list, fPName);
                    break;
                case 5:
                    orderList.orderProduct(productList);break;
                case 6:
                    orderList.viewOrderList();
                    productList.saveToFile(fPName);
                    //orderList.printInventory(productList);
                break;
                default:
                {
                    if(choice!=-1) System.out.println("Quitt");break;
                }
            }
        } while (choice > 0 && choice < 8 || choice == -1);
        
    }
}
