/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todomanager;

import IO.LoadSettings;
import IO.ToDoLoader;
import IO.ToDoWriter;
import View.ToDoListPanel;
import todo.ToDoModel;
import todo.ToDoType;
import todo.ToDoItem;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Kiani
 */
public class ToDoManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("ToDoList");
        ToDoModel model = new ToDoModel();
        
        
        frame.getContentPane().add(new ToDoListPanel(model));
        
        
        
        
        
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        
        
        
    }
}
