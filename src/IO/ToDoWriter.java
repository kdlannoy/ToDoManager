/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import todo.ToDoItem;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import todomanager.WriteListToFile;

/**
 *Schrijft de items weg naar een gespecifieerde locatie
 * @author Kiani
 */
public class ToDoWriter {
       private List<ToDoItem> items;
    private File filepath;

    public ToDoWriter(File filepath) {
        this.filepath = filepath;
        items = new ArrayList<>();
    }

    public void writeItems(List<ToDoItem> list) {
        items = list;
        try {
            OutputStream file = new FileOutputStream(filepath);
            
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput out = new ObjectOutputStream(buffer);
            
            try {
                for (ToDoItem toDoItem : items) {
                    out.writeObject(toDoItem);
                }
                    
                
            } finally {
                out.close();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ToDoLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ToDoLoader.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

   
    
}
