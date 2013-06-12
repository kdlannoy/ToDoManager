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
        sp.setPreferredSize(new Dimension(250,500));
        add(sp);

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
    
    private void writeModel(){
        model.writeItems();
    }

    //maakt een JFrame waarin je een item kan specifieren en toevoegen
    public void createItem() {
        JFrame frm = new JFrame("Item Creator");
        JLabel lbl = new JLabel();
        frm.add(lbl);
        frm.setSize(350, 200);
        frm.setVisible(true);
        frm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            }
        });

        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        final JLabel typelabel = new JLabel("Type:");
        final JTextField typetf = new JTextField(20);
        final JLabel itemlabel = new JLabel("Item");
        final JTextField itemtf = new JTextField(20);
        p.setLayout(new GridLayout(3, 1));
        p.add(typelabel);
        p.add(typetf);
        p.add(itemlabel);
        p.add(itemtf);
        JButton submit = new JButton("Submit");
        p.add(submit);
        p1.add(p);
        frm.add(p1, BorderLayout.NORTH);
        frm.pack();
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!model.containsTypeString(typetf.getText())) {
                    model.addItem(new ToDoItem(typetf.getText(), itemtf.getText()));
                } else {
                    model.addItem(new ToDoItem(model.getTypeString(typetf.getText()), itemtf.getText()));
                }

                model.writeItems();
            }
        });


    }

    
}
