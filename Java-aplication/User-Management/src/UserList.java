
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public class UserList extends ArrayList<User> implements I_UserList{
    Scanner sc = new Scanner(System.in);
    MyValidation valid = new MyValidation();
    
    
    public int searchUsername(String username){
        for (int i = 0; i < size(); i++) {
            if(username.equals(get(i).getUsername()))
                return i;
        }
        return -1;
    }
    
     public boolean checkBlankForUp(String newString){
        if(newString.equalsIgnoreCase("") || newString.matches(".*[0-9A-Za-z].*")==false)
            return true;
        return false;
    }
    
    
    private String checkValidUsername()
    {
        String username = "";
        boolean flag = false;
        while(!flag){
            System.out.print("Enter username: ");
            username = sc.nextLine();
            if(!valid.isValidUserName(username))
            {
                flag = true;
            }
            else System.out.println("    Wrong");
        }
        return username;
    }
    
    private String checkValidPassword()
    {
        String password = "";
        boolean flag = false;
        while(!flag){
            System.out.print("Enter password: ");
            password = sc.nextLine();
            if(!valid.isValidPassword(password)){
                System.out.println("   Right!   " );
                flag = true;
            }
            else{
                System.out.println("    Wrong");
            }
        }
        return password;
    }
    
    private void confirmPassword( String password){
        String comfirm = "";
        boolean flag = false;
        while(!flag){
            System.out.print("Enter comfirm password: ");
            comfirm = sc.nextLine();
            if(password.equalsIgnoreCase(comfirm)){
                System.out.println("   Right!   " );
                flag = true;
            }
            else{
                System.out.println("    Wrong");
            }     
        }
    }
    
    private String isValidatePhoneNum()
    {
        String phone = "";
        boolean flag = false;
        while(!flag){
            System.out.print("Enter phone number: ");
            phone = sc.nextLine();
        if (valid.isValidPhonenumber(phone)){
            flag = true;
        }
        else System.out.println("    Wrong ");
        }
        return phone;
    }
    
     private String checkValidEmail(){
        String email = "";
        boolean flag = false;
        while(!flag){
            System.out.print("Enter email: ");
            email = sc.nextLine();
            if(valid.isValidEmail(email)){
                flag = true;
                System.out.println("   Right!   " );
            }
            else{
                System.out.println("    Wrong");
            }
                
        }
        return email;
    }
    
    @Override
    public void createUserAccount(String fName){
       boolean goBack = true;
       while(goBack != false) 
       {
        String username, firstName, lastName;
        String password;
        String phone, email;
        username = checkValidUsername();
        int pos = searchUsername(username);
        if(pos >= 0) System.out.println("Username has been existed");
        else{
            System.out.println("    Right!");
            System.out.print("Enter first name: ");
            firstName = sc.nextLine();
            System.out.print("Enter last name: ");
            lastName = sc.nextLine();
            password = checkValidPassword();
            confirmPassword(password);
            phone = isValidatePhoneNum();
            email = checkValidEmail();
            add(new User(username, password, firstName, lastName, phone, email));
            System.out.println("Account is created successfully");
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
    
    
    private UserList readUserFile(String fName)
    {
        UserList userList = new UserList();
        try {
            File f = new File(fName);
            if(!f.exists()){
                System.out.println("File does not exist");
                return null;
            }
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String user;
            while((user = br.readLine())!= null){
                String stk[] = user.split(", ");
                String username = stk[0];
                String name = stk[1];
                String lastName = name.substring(0, name.lastIndexOf(" "));
                String firstName = name.substring(name.lastIndexOf(" ") + 1);
                String password = stk[2];
                String phone = stk[3];
                String email = stk[4];
                userList.add(new User(username, password, firstName, lastName, phone, email));
            }
            br.close();fr.close();
        } catch (Exception e) {
           // System.out.println(e);
            return null;
        }
        return userList;
    }
    
    public int checkUserInFile(String fName, String username){
        UserList userList = readUserFile(fName);
        if(userList == null) return -2;
        return userList.searchUsername(username);
        
    }
    
    @Override
    public void checkExistUser(String fName){
      boolean goBack = true;
      while(goBack != false) 
      {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        int pos = checkUserInFile(fName, username);
        if(pos<0)
            System.out.println("No User Found!");
        else
            System.out.println("User exist");
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
    public void searchUserByName(){
      boolean goBack = true;
      while(goBack != false) 
      { 
        if(this.isEmpty()){
            System.out.println("Have no any user");
            return;
        }
        String name;
        int count = 0;
        Collections.sort(this,User.objCompare);
        System.out.print("Enter Last name or First name or Full name: ");
        name = sc.nextLine();
        name = name.trim();
        for (int i = 0; i < size(); i++) {
            String fullName = get(i).getLastName() + " " + get(i).getFirstName();
            if(fullName.contains(name)){
                System.out.println(get(i).toString());
            }
            else  count+=1;
        }
        if(count == size()) System.out.println("Have no any user");
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
    
    private int login()
    {
        if(this.isEmpty()) 
        {
            System.out.println("Empty list");
            return -1;
        }
        String username, password = "";
        System.out.print("Enter username: ");
        username = sc.nextLine();
        
        int pos = searchUsername(username);
        if(pos < 0) {
            System.out.println("Username does not exist");
            return -1;
        }
        else{
            System.out.print("Enter password: ");
            password = sc.nextLine();
            if(pos >= 0 && get(pos).getPassword().equals(password)){
            System.out.println("Login");
            return pos;
            }
            else System.out.println("You have wrong password");
        }
        return -1;
    }
    
    
    @Override
    public void updateUserInfo(String fName){
       boolean goBack = true;
       while(goBack){
           int pos = login();
           if(pos >= 0){
               System.out.println("====Information====");
               System.out.println(get(pos).toString());
               System.out.print("Enter username: ");
               String username = sc.nextLine();
               int p = searchUsername(username);
               if(checkBlankForUp(username)) System.out.println("Update omitted");
               else{
                   if(p >= 0) System.out.println("Fail");
                   else{
                   if(!valid.isValidUserName(username)){
                   get(pos).setUsername(username);
                   System.out.println("Successfull");
                   }
                   else{
                       System.out.println("!Wrong \nUpdate Fail");
                   }
                   }
               }
               System.out.print("Enter password: ");
               String password = sc.nextLine();
               if(checkBlankForUp(password)) System.out.println("Update omitted");
               else{
                   if(!valid.isValidPassword(password)){
                       confirmPassword(password);
                       get(pos).setPassword(password);
                       System.out.println("Successful");
                   }
                   else System.out.println("Wrong \nUpdate Fail");
               }
               System.out.print("Enter first name: ");
               String firstName = sc.nextLine();
               if(checkBlankForUp(firstName)) System.out.println("Update omitted");
               else{
                   get(pos).setFirstName(firstName);
                   System.out.println("Successful");
               }
               System.out.print("Enter last name: ");
               String lastName = sc.nextLine();
               if(checkBlankForUp(lastName)) System.out.println("Update omitted");
               else{
                   get(pos).setLastName(lastName);
                   System.out.println("Successful");
               }
               System.out.print("Enter phone number: ");
               String phonenumber = sc.nextLine();
               if(checkBlankForUp(phonenumber)) System.out.println("Update omitted");
               else{
                   if(valid.isValidPhonenumber(phonenumber)){
                   get(pos).setPhoneNumber(phonenumber);
                   System.out.println("Successful");
                   }
                   else System.out.println("!Wrong \nUpdate Fail");
               }
               System.out.print("Enter email: ");
               String email = sc.nextLine();
               if(checkBlankForUp(email)) System.out.println("Update omitted");
               else{
                   if(valid.isValidEmail(email)){
                   get(pos).setEmail(email);
                   System.out.println("Successful");
                   }
                   else System.out.println("!Wrong \nUpdate Fail");
               }
               System.out.println("Updated");
           }
           else
           {
               System.out.println("Not found \nUpdate Fail"); 
           }
            System.out.println("==Enter any key to Go back Menu== \n==Enter YES to Continue== ");
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
    public void deleteUser(){
        boolean goBack = true;
        while(goBack){
        int pos = login();
        if(pos >= 0)
        {
            System.out.println("Do you want to delete this user? (Yes/No)");
            String agree = sc.nextLine();
            if(agree.equalsIgnoreCase("Yes")){
                remove(pos);
                System.out.println("This user has been deleted");
            }
            else System.out.println("Delete fail");
        }
        else System.out.println("Delete fail");
        System.out.println("==Enter any key to Go back Menu== \n==Enter YES to Continue== ");
            String agreeString;
            agreeString = sc.nextLine();
            if(agreeString.equalsIgnoreCase("Yes"))
            {
                goBack = true; 
            }
            else goBack = false;
        }
    }
    
    public void update(String fName)
    {
        Menu menu = new Menu();
        menu.addChoices("1. Update user information");
        menu.addChoices("2. Delete user information");
        int choice = 0;
        System.out.println("===Update===");
        do{
            choice = menu.getUserChoices();
            switch(choice){
                case 1:
                    updateUserInfo(fName);break;
                case 2:
                    deleteUser();break;
            }
        }while (choice == -1 );
        System.out.println("===List after update===");
        print();
    }
    
    @Override
    public void saveAccountToFile(String fName){
        if(isEmpty()){
            System.out.println("Empty List");
            System.out.println("Save fail!");
            return;
        }
        try {
            File f = new File(fName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (User user : this) {
                User encodeUser = new User(user.getUsername(), encryptPassword(user.getPassword()), user.getFirstName(), 
                     user.getLastName(), user.getPhoneNumber(), user.getEmail());
                pw.println(encodeUser.toString());
            }
            pw.close();fw.close();
            System.out.println("Save successfully");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Save fail!");
        }
    }
    
    @Override
    public void printListUserFromFile(String fName){
        UserList userList = readUserFile(fName);
        if(userList == null) {
            System.out.println("Empty");
            return;
        }
        System.out.println("=====List of user in current file=====");
        for (User user : userList) {
            System.out.println(user.toString());
        }
       System.out.println("Do you want to print list of user in program? (Yes/No)");
            String agreeString;
            agreeString = sc.nextLine();
            if(agreeString.equalsIgnoreCase("Yes"))
            {
                System.out.println("===List of user===");
                this.print();
            }
    }
    
    
    
    @Override
    public String encryptPassword(String password){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            System.out.println(e);
        }
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String encoded = DatatypeConverter.printHexBinary(hash);
        return encoded;
    }
    
    public void print()
    {
        for (User user : this) {
            System.out.println(user.toString());
        }
    }
}

