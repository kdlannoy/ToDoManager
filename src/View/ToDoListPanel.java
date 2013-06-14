/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
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
    private JButton button;

    public ToDoListPanel(ToDoModel model) {
        super();
        this.model = model;
        this.todolijst = new ToDoList(model);
        sp = (new JScrollPane(todolijst, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        sp.setPreferredSize(new Dimension(250, 500));
        add(sp);

        //action nu
        button = new JButton(new AbstractAction("Add new item") {
            @Override
            public void actionPerformed(ActionEvent e) {
                createItem();
            }
        });

        AbstractAction t = new AbstractAction("Remove item") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //removeItemModel((ToDoItem) todolijst.getSelectedValue());
                    removeItemModelByString(todolijst.getSelectedValue().toString());
                    writeModel();
                } catch (Exception ex) {
                    System.out.println("Geen item geselecteerd:\t"+ex);
                }
                todolijst.updateList();
            }
        };
        
        //zorg dat je met delete dingen kan verwijderen
        todolijst.getInputMap().put(KeyStroke.getKeyStroke("DELETE"),"DELETE");
        todolijst.getActionMap().put("DELETE", t);
        
        this.getInputMap().put(KeyStroke.getKeyStroke("DELETE"),"DELETE");
        this.getActionMap().put("DELETE", t);
        
        JButton removebtn = new JButton(t);
        this.add(button);
        this.add(removebtn);
    }

    public JButton getButton() {
        return button;
    }

    protected void removeItemModel(ToDoItem item) {
        model.removeItemString(item.getMessage());
    }
    
    protected void removeItemModelByString(String s){
        model.removeItemString(s);
    }

    public ToDoList getTodolijst() {
        return todolijst;
    }

    private void writeModel() {
        model.writeItems();
    }

    //maakt een JFrame waarin je een item kan specifieren en toevoegen (Item Creator)
    public void createItem() {
        final JFrame frm = new ItemCreator(model);
        frm.setSize(300, 170);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        //zorg ervoor dat de frame wordt gesloten door escape in te drukken
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        Action escapeAction = new AbstractAction() {
            // close the frame when the user presses escape
            @Override
            public void actionPerformed(ActionEvent e) {
                frm.dispose();
            }
        };
        frm.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        frm.getRootPane().getActionMap().put("ESCAPE", escapeAction);


    }
}
