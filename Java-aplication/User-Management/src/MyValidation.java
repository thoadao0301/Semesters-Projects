/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public class MyValidation {
    public boolean isValidUserName(String username)
    {
        return (username.matches(".*\\s.*") || username.length() < 5);
    }
    
    public boolean isValidPassword(String password)
    {
        return(password.matches(".*\\s.*") || password.length() < 6);
    }
    
    public boolean isValidPhonenumber(String phone)
    {
        return(10 == phone.length()  && phone.matches("\\d{10}"));
    }
    
    public boolean isValidEmail(String email)
    {
        return(email.matches("^[\\w]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$"));
    }
}
