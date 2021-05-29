
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public class isDirectore {
    public void isFile (File f)
    {
        String L[] = f.list();
        for (int i = 0; i < L.length; i++) {
             File f2 = new File(f,L[i]);
             if(f2.isFile())
                {
                    System.out.println("    " + L[i]);
                }
             else
             {
                 System.out.println(L[i] + "   [DIR]");
                 isFile(f2);
             }
            }
    }
}
