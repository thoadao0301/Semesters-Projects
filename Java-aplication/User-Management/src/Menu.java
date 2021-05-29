
import java.util.ArrayList;
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
public class Menu extends ArrayList<String> implements I_Menu{
    Scanner sc = new Scanner(System.in);
    @Override
    public void addChoices(String choice)
    {
        add(choice);
    }
    
    @Override
    public int getUserChoices()
    {
        int result = 0;
        if(size() >= 0)
        {
            for(String choice : this)
            {
                System.out.println(choice);
            }
            System.out.println("Please select option: ");
            try {
                result = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("It must be a number");
                result = -1;
            }
        }
        return result;
    }
    
}
