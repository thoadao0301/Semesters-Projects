/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public interface I_UserList {
    public void createUserAccount(String fName);
    public void checkExistUser(String fName);
    public void searchUserByName();
    public void updateUserInfo(String fName);
    public void deleteUser();
    public void saveAccountToFile(String fName);
    public void printListUserFromFile(String fname);
    public String encryptPassword(String password);
}
