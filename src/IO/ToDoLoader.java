/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import todo.ToDoItem;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kiani
 * 
 * Laad de todo items uit het bestand die gespecifieerd is in ToDoSetting
 */
public class ToDoLoader {

    private ArrayList<ToDoItem> items;
    private File filepath;

    public ToDoLoader(File filepath) {
        this.filepath = filepath.getAbsoluteFile();
        items = new ArrayList<>();
    }

    public void readItems() {
        
        try {
            InputStream file = new FileInputStream((filepath.getAbsoluteFile()));
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput in = new ObjectInputStream(buffer);
            ToDoItem item;
            try {
                while (true) {
                    item = (ToDoItem) in.readObject();
                    items.add(item);
                }                
            } finally {
                in.close();
            }
        } catch (EOFException ex){
            
        }catch (FileNotFoundException ex) {
            Logger.getLogger(ToDoLoader.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (ClassNotFoundException ex) {
            Logger.getLogger(ToDoLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ToDoLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<ToDoItem> getItems() {
        readItems();
        return items;
    }
    
    
}
