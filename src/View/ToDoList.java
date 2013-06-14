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
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import todo.ToDoObject;

/**
 *
 * @author Kiani
 */
public class ToDoList extends JTable implements ChangeListener {

    private DefaultTableModel lijstmd;
    private ToDoTypeFilter filter;
    private ToDoModel model;

    public ToDoList(ToDoModel model) {
        this.model = model;
        this.setModel(model);
        model.addListener(this);
        lijstmd = (DefaultTableModel) this.getModel();
        DefaultTableCellRenderer r = new MyCellRenderer();
        this.setDefaultRenderer(ToDoObject.class, r);



        lijstmd.addColumn(ToDoObject.class);
        lijstmd.addColumn("Id");
        lijstmd.addColumn("Message");

        
        TableColumn kolom = this.columnModel.getColumn(0);
        kolom.setMaxWidth(0);
        kolom.setMinWidth(0);
        initList();

        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnAdjuster tca = new TableColumnAdjuster(this);
        tca.adjustColumns();
        
        
    }

    public final void initList() {
        updateList();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        updateList();
    }

    public Object getSelectedValue() {
        int indexTable = super.getSelectedRow();
        ToDoObject b = (ToDoObject) super.getModel().getValueAt(indexTable, 0);
        if (b.isItem() != -1) {
            return model.getItemByString(((ToDoItem) b).getMessage());
        } else {
            return null;
        }

    }

    public void updateList() {

        // EDIT, eens Filter geïmplementeerd is, moeten we ervoor zorgen dat in de lijs enkel nog de Types worden weergegeven die in de filter voorkomen
        //lijstmd.removeAllElements();
        int rowcount = lijstmd.getRowCount();
        for (int i = 0; i < rowcount; i++) {
            lijstmd.removeRow(0);
        }



        //add headers
        //ToDoHeader t = new ToDoHeader(new String[]{"Id", "Topic / Message"});


        //lijstmd.addElement(t);
        int index = 0;

        // add all types
        for (ToDoType type : model.getTypes()) {

            //lijstmd.addElement(type);
            lijstmd.addRow(new Object[]{type, null, type.getType()});
            index++;
            // add all tasks
            for (ToDoItem item : model.getItems()) {
                if (item.getType() == type) {
                    lijstmd.addRow(new Object[]{item, item.getId(), item.getMessage()});
                    index++;

                    //lijstmd.addElement(item);
                }
            }
        }


    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);

        //  add custom rendering here

        Color background = null;
        Color foreground = null;

        //type
        if (((ToDoObject) lijstmd.getValueAt(row, 0)).isItem() == 0) {
            background = Color.BLUE;
            foreground = Color.WHITE;
            if (isRowSelected(row)) {
                background = Color.LIGHT_GRAY;
                foreground = Color.BLACK;
            }
        } else if (((ToDoObject) lijstmd.getValueAt(row, 0)).isItem() == 1) {
            background = Color.WHITE;
            foreground = Color.BLACK;
            if (isRowSelected(row)) {
                background = Color.LIGHT_GRAY;
                foreground = Color.BLACK;
            }

        }

        //setText(value != null ? value.toString() : "");
        c.setBackground(background);
        c.setForeground(foreground);

        return c;
    }
}

class MyCellRenderer extends DefaultTableCellRenderer {

    public MyCellRenderer() {
        super();
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel renderedLabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        renderedLabel.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);

        return renderedLabel;


    }
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        g.setColor(Color.black);
//        g.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
//        g.drawLine(17,0,17,getHeight()-1);
//        
//    }
}