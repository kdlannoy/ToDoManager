/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todomanager;

import IO.ToDoLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import todo.ToDoItem;

/**
 *
 * @author Kiani
 */
public class WriteListToFile {

    public static void main(String[] args) {
        schrijf();
    }
    
    public static void schrijf(){
        ArrayList<ToDoItem> lijstje = new ArrayList<ToDoItem>();
        ToDoLoader lader = new ToDoLoader(new File("todo.list"));

        lijstje = lader.getItems();
        
        
        try {
            
            FileWriter outfile = new FileWriter("lijstje.txt");
            PrintWriter out = new PrintWriter(outfile);


            out.println(""+lijstje.size());
            for (ToDoItem i : lijstje) {
                
                    //formaat:id---type---dueDate---message---importance
                    out.println(i.getId()+"---"+i.getType()+"---"+i.getDueDate()+"---"+i.getMessage()+"---"+i.getImportance());
                 
            }
            
            out.close();


        } catch (FileNotFoundException ex) {
            Logger.getLogger(ToDoLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ToDoLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
