/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todomanager;

import View.ToDoListPanel;
import todo.ToDoModel;
import javax.swing.JFrame;

/**
 *
 * @author Kiani
 */
public class ToDoManager {

    /**
     * 
     * EDIT Er moet nog een menu komen waar we de settings kunnen veranderen (bv waar de settings worden opgeslaan, waar de lijst wordt opgeslaan)
     * 
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("ToDoList");
        ToDoModel model = new ToDoModel();
        
        ToDoListPanel panel = new ToDoListPanel(model);
        frame.getContentPane().add(panel);
        
        frame.getRootPane().setDefaultButton(panel.getButton());
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        
        
        
    }
}
