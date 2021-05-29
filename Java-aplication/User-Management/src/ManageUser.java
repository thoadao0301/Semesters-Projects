/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public class ManageUser {
    public static void main(String[] args) {
        UserList list = new UserList();
        Menu menu = new Menu();
        String fName = "user.txt";
        int choice = 0;
       
        menu.addChoices("1. Create user account");
        menu.addChoices("2. Check exits user.");
        menu.addChoices("3. Search user information by name.");
        menu.addChoices("4. Update user");
        menu.addChoices("5. Save account to file");
        menu.addChoices("6. Print list user from file.");
        menu.addChoices("Others. Quit program");
        
        System.out.println("====Manage User Account====");
        do {            
            choice = menu.getUserChoices();
            switch(choice){
                case 1:
                {
                    list.createUserAccount(fName);
                    list.print();break;
                }
                case 2:
                    list.checkExistUser(fName);break;
                case 3:
                    list.searchUserByName();break;
                case 4:
                    list.update(fName);break;
                case 5:
                    list.saveAccountToFile(fName);
                    break;
                case 6:
                    list.printListUserFromFile(fName);break;
                default:{
                    if(choice!= -1)
                    {
                        System.out.println("Quit");
                    }
                }
            }
        } while (choice > 0 && choice < 7 || choice == -1);
    }
}
