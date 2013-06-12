/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import todo.ToDoType;
import todo.ToDoModel;
import todo.ToDoTypeFilter;
import todo.ToDoItem;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Kiani
 */
public class ToDoList extends JList implements ChangeListener {

    private DefaultListModel lijstmd;
    private ToDoTypeFilter filter;
    private ToDoModel model;

    public ToDoList(ToDoModel model) {
        this.model = model;
        this.setModel(model);
        model.addListener(this);
        lijstmd = (DefaultListModel) this.getModel();
        this.setCellRenderer(new MyCellRenderer());
        this.updateList();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        updateList();
    }

    public void updateList() {

        lijstmd.removeAllElements();
        this.removeAll();
        System.out.println(model.getTypes());
        // add all types
        for (ToDoType type : model.getTypes()) {


            lijstmd.addElement(type);


            // add all tasks
            for (ToDoItem item : model.getItems()) {
                if (item.getType() == type) {
                    ((MyCellRenderer) this.getCellRenderer()).setItem();
                    lijstmd.addElement(item);
                }
            }
        }
        
        





    }
}

class MyCellRenderer extends JLabel implements ListCellRenderer<Object> {

    public MyCellRenderer() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList<?> list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        setText(value.toString());

        Color background = null;
        Color foreground = null;
        if (isSelected) {
            background = Color.DARK_GRAY;
            foreground = Color.BLUE;
        } else {
            if (value instanceof ToDoType) {

                background = Color.BLUE;
                foreground = Color.WHITE;
            } else {
                background = Color.WHITE;
                foreground = Color.BLACK;
            }
        }
        setBackground(background);
        setForeground(foreground);

        return this;
    }

    public void setType() {
        setBackground(Color.BLUE);
        setForeground(Color.WHITE);
    }

    public void setItem() {
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
    }
}