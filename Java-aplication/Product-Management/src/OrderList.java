
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
public class OrderList extends ArrayList<Order> implements I_OrderList{
    Scanner sc = new Scanner(System.in);
    
    
    @Override
    public void printAllProducts(ProductList productList)
    {
        System.out.println("|++ No ++|++ Product Name ++|++ Price ++|");
        int count = 1;
        for (Product product : productList) {
            System.out.printf("    %-8s%-18s%.1f\n",count++,product.getPName(),product.getPrice());
        }
    }
    
    public Product searchSelect(int select, ProductList productList)
    {
        select = select -1;
        for (int i = 0; i< productList.size();i++) {
            if(select == i)
                return productList.get(i);
        }
        return null;
    }
    
    public String checkNull(){
        String check = "";
        boolean flag = false;
        while(!flag){
            String tmp = sc.nextLine();
            check = tmp.trim();
            if(check.equalsIgnoreCase("")){
                System.out.println("Information is not allowed blank");
                flag = false;
            }
            else flag = true;
        }
        return check;
    }
    
    public String CheckIntNumber()
    {
        String check = "";
        boolean flag = false;
        while(!flag)
        {
            check = sc.nextLine();
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
    
    
    
    @Override
    public void orderCusProduct(ProductList productList)
    {
        String agree = "";
        ProductList orderList = new ProductList();
        int n;
        do{
            int orderQuantity = 0;
            System.out.println("Enter your select: ");
            int select = Integer.parseInt(sc.nextLine());
            Product product = searchSelect(select, productList);
            if(product == null) System.out.println("Product is not in list of product");
            else{
                n = product.getQuantity();
                System.out.println("You select: " + product.getPName());
                if(n == 0) System.out.println("this is out of stoke");
                else{
                while(orderQuantity > product.getQuantity() || orderQuantity == 0)
                {
                    System.out.print("Enter quantity: ");
                    orderQuantity = Integer.parseInt(CheckIntNumber());
                    if(orderQuantity > n) System.out.println("My products are not enough: " + n);
                }
                orderList.add(new Product(product.getPName(), product.getPrice(), orderQuantity));
                product.setQuantity(String.valueOf(n - orderQuantity));
            }
            }
            if(orderList.isEmpty()) System.out.println("Your bag is empty");
            System.out.println("Do you want to order now (Y/N) ?");
            agree = sc.nextLine();
        }while(agree.equalsIgnoreCase("Y"));
        if(!orderList.isEmpty()){
        System.out.println("|++ Product ++|++ Quantity ++|++ Price ++|++ Amount ++|");
        double total = 0;
        for (Product product : orderList) {
            System.out.printf("   %-17s%-15d%-12.1f%.2f\n",product.getPName()
            ,product.getOrderquantity(),product.getPrice(),product.getAmount());
            total += product.getAmount();
        }
        System.out.println("Total: " + total);
        System.out.print("Enter your name: ");
        String name = checkNull();
        add(new Order(orderList, total, name));
        System.out.println("Added!" + "\nProducts: " + orderList.size());
        }
        System.out.println("Thank you for passing");
        System.out.println("===Back to main menu===");
    }
    
    
    @Override
    public void viewOrderList()
    {
        for (Order thi : this) {
            System.out.println("Customer: " + thi.getName());
            System.out.println("Product | Quantity | Price | Amount");
            for (Product prd : thi.getOrderPLst()) {
                System.out.println(prd.toString2());
            }
            System.out.println("Total: " + thi.getTotal());
            System.out.println("----------------------------");
        }
    }
    
    /*
    public ProductList printInventory(ProductList productList)
    {
        for (Product product : productList) {
           int inventory = product.getQuantity();
            for (Order ord : this) {
                for (Product preOrd : ord.getOrderPLst()) {
                    if(product.getPName().equals(preOrd.getPName()))
                    {
                        inventory = inventory - preOrd.getOrderquantity();
                        
                    }
                }
            }
            product.setQuantity(String.valueOf(inventory));
            
        }
        return productList;
    }*/
    
    public void orderProduct(ProductList productList)
    {
        if(productList.isEmpty()){
            System.out.println("There is no product");
            return;
        }
        Menu mnUpdate = new Menu();
        
        int choice;
        
        //hasBeenDeleted = false;
        mnUpdate.addChoices("1. Display all products ");
        mnUpdate.addChoices("2. Order product");
         do {
            System.out.println("\n====ORDER PRODUCT====");
            choice = mnUpdate.getUserChoices();
            System.out.println("==================");
            switch(choice)
            {
                case 1:
                {
                    printAllProducts(productList);
                }break;
                case 2:
                {
                    orderCusProduct(productList);
                }
                 break;
                 
            }
            }while(choice == 1 || choice == 2 || choice == -1);
         System.out.println();
    }
}
   
