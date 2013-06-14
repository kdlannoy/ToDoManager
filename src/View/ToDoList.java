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
import java.awt.Graphics;
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

    @Override
    public Object getSelectedValue() {
        return model.getItemByString(super.getSelectedValue().toString());

    }

    public void updateList() {

        // EDIT, eens Filter geïmplementeerd is, moeten we ervoor zorgen dat in de lijs enkel nog de Types worden weergegeven die in de filter voorkomen
        lijstmd.removeAllElements();
        this.removeAll();
        
        //add headers
        lijstmd.addElement("Id   Topic");
        
        // add all types
        for (ToDoType type : model.getTypes()) {
            lijstmd.addElement(type);
            // add all tasks
            for (ToDoItem item : model.getItems()) {
                if (item.getType() == type) {
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

        Color background = null;
        Color foreground = null;

        //als het item geselecteerd is (om te kunnen verwijderen bv)

        //de categoriën / type in ander kleur
        if (value instanceof ToDoType) {

            setText(String.format("%-5s  " + value.toString(), ""));
            background = Color.BLUE;
            foreground = Color.WHITE;

            if (isSelected) {
                background = Color.DARK_GRAY;
                foreground = Color.BLUE;
            }
        } else if(value instanceof ToDoItem){
            ToDoItem item = (ToDoItem) value;
            setText(String.format("%-5d%-30s\t", item.getId(), item.getMessage()));
            background = Color.WHITE;
            foreground = Color.BLACK;
            if (isSelected) {
                background = Color.DARK_GRAY;
                foreground = Color.BLUE;
            }
        }else{
            setText(value.toString());
            background = Color.RED;
            foreground = Color.CYAN;
       }

        setBackground(background);
        setForeground(foreground);




        return this;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
        g.drawLine(17,0,17,getHeight()-1);
        
    }
}