
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
public class category {
    private String categoryID ;
    private String categoryName ;
    Scanner sc = new Scanner(System.in);

    public category() {
    }
    
    public category(String categoryID)
    {
        this.categoryID = categoryID;
    }

    public category(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        if(!categoryID.equals(""))
            this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        if(!categoryName.equals(""))
            this.categoryName = categoryName;
    }
    
    public String toString()
    {
        return (this.categoryID + ", " + this.categoryName);
    }
    
    public boolean equals(Object obj)
    {
        return this.categoryID.equals(((category)obj).categoryID);
    }
    
    
}
