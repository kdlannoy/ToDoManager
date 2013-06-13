/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import todo.ToDoModel;

/**
 *
 * @author Kiani
 */
public class ItemCreator extends JFrame {
    
    private ToDoModel model;
    
 

    public ItemCreator(ToDoModel model) {
        super("Item Creator");

        this.model = model;
        ItemCreatorForm panel = new ItemCreatorForm(model);
        this.add(panel);
        
        this.getRootPane().setDefaultButton(panel.getButton());
        
        
    }

    
}
