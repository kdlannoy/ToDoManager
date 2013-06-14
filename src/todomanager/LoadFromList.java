/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todomanager;

import IO.ToDoLoader;
import IO.ToDoWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import todo.ToDoItem;

/**
 *
 * @author Kiani
 */
public class LoadFromList {
     public static void main(String[] args) throws ParseException {
         readFromFile();
        
    }
     
     public static void readFromFile(){
         ArrayList<ToDoItem> lijstje = new ArrayList<ToDoItem>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("lijstje.txt"));
            StringBuilder sb = new StringBuilder();
            int aantallijnen = Integer.parseInt(br.readLine());
            
            for (int i = 0; i < aantallijnen; i++) {
                String lijn = br.readLine();
                System.out.println(lijn);
                String[] delen;
                String delimeter = "---";
                delen = lijn.split(delimeter);
                System.out.println(delen);
                ToDoItem item = new ToDoItem(delen[1], delen[3]);
                //item.setDueDate(delen[2]));
                item.setImportance(Integer.parseInt(delen[4]));
                lijstje.add(item);
            }
            br.close();
            
            ToDoWriter writer = new  ToDoWriter(new File("todo.list"));
            writer.writeItems(lijstje);
            
            


        } catch (FileNotFoundException ex) {
            Logger.getLogger(ToDoLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ToDoLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
