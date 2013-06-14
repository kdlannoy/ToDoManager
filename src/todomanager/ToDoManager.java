/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todomanager;

import View.ToDoPanel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import todo.ToDoModel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
        final JFrame frame = new JFrame("ToDoManager");
        final ToDoModel model = new ToDoModel();
        
        //ToDoListPanel panel = new ToDoListPanel(model);
        ToDoPanel panel = new ToDoPanel(model);
        frame.getContentPane().add(panel);
        
        frame.getRootPane().setDefaultButton(panel.getButton());
        JMenuBar menubar = new JMenuBar();
        JMenu filemenu = new JMenu("File");
        JMenuItem readFromList = new JMenuItem();
        JMenuItem writeToList = new JMenuItem();
        
        readFromList.setAction(new AbstractAction("Read todo list from textfile"){

            @Override
            public void actionPerformed(ActionEvent e) {
                LoadFromList.readFromFile();
                model.loadFromFile();
            }
            
        });
        
        writeToList.setAction(new AbstractAction("Write todo list to textfile") {

            @Override
            public void actionPerformed(ActionEvent e) {
                WriteListToFile.schrijf();
            }
        });
        
        filemenu.add(readFromList);
        filemenu.add(writeToList);
        menubar.add(filemenu);
        frame.setJMenuBar(menubar);
        
        frame.setMenuBar(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        
        
        
    }
}
