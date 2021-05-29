
import java.io.File;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author baoth
 */
public class DirCommand {
    public static void main(String[] args) throws IOException{
        File f = new File(args[1]);
        for (int i = 0; i < args.length; i++) {
            System.out.print(" ");
            System.out.print(args[i]);
        }
        System.out.println();
        isDirectore ck = new isDirectore();
        ck.isFile(f);
    }
}
