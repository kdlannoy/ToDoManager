/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todomanager;

import IO.ToDoLoader;
import IO.ToDoSetting;
import IO.ToDoWriter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import todo.ToDoItem;
import todo.ToDoType;

/**
 *
 * @author Kiani
 */
public class SettingsWriter {
    public static void main(String[] args){
        write();
    }
    
    public static void write(){
        ToDoType type1 = new ToDoType("School");
        ToDoWriter writer = new ToDoWriter(new File("todo.list"));
        ArrayList<ToDoItem> lijstje = new ArrayList<ToDoItem>();
        writer.writeItems(lijstje);
        
        try {
            OutputStream file = new FileOutputStream(new File("settings.ini"));
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput out = new ObjectOutputStream(buffer);
            
            ToDoSetting tds = new ToDoSetting();
            tds.setFile(new File("todo.list"));
            
            try {
                out.writeObject(tds);
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
