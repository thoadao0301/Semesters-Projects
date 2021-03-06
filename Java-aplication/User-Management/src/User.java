
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public class User{
    private String username,password;
    private String firstName, lastName;
    private String phoneNumber,email;

    public User() {
    }

    public User(String username, String password, String firstName, String lastName, String phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String toString(){
        return (this.username + ", " + this.lastName + " " + this.firstName +", " + this.password +
                ", " + this.phoneNumber + ", " + this.email);
    }
    
    public static Comparator objCompare = new Comparator<User>() {
        @Override
        public int compare(User s1, User s2) {
           if(s1.getFirstName().compareTo(s2.getFirstName()) > 0)
               return 1;
           else{
               if(s1.getFirstName().compareTo(s2.getFirstName()) < 0)
                   return -1;
               else{
                   return 0;
                   }
               }
           }
        };
    
}
