/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import todo.ToDoItem;
import todo.ToDoModel;

/**
 *
 * @author Kiani
 */
public class ToDoListPanel extends JPanel {

    private ToDoList todolijst;
    private ToDoModel model;
    private JScrollPane sp;

    public ToDoListPanel(ToDoModel model) {
        super();
        this.model = model;
        this.todolijst = new ToDoList(model);
        sp = (new JScrollPane(todolijst));
        sp.setPreferredSize(new Dimension(250, 500));
        add(sp);

        //action nu
        JButton button = new JButton("Add item");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createItem();
            }
        });

        JButton removebtn = new JButton("Remove item");
        removebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    removeItemModel((ToDoItem) todolijst.getSelectedValue());
                    writeModel();
                } catch (Exception ex) {
                    System.out.println("Geen item geselecteerd");
                }
                todolijst.updateList();
            }
        });
        this.add(button);
        this.add(removebtn);
    }

    protected void removeItemModel(ToDoItem item) {
        model.removeItemString(item.getMessage());
    }

    public ToDoList getTodolijst() {
        return todolijst;
    }

    private void writeModel() {
        model.writeItems();
    }

    //maakt een JFrame waarin je een item kan specifieren en toevoegen
    public void createItem() {
        JFrame frm = new ItemCreator(model);
        frm.setSize(300, 170);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
}
