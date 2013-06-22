/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Kiani
 */
public class TabelLuisteraar implements TableModelListener {

    private ToDoModel moddel;

    public TabelLuisteraar(ToDoModel model) {
        this.moddel = model;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel) e.getSource();

        System.out.println("row:" + row);

        if( model.getValueAt(row,0)==null){
            return;
        }
        ToDoObject data = (ToDoObject) model.getValueAt(row, 0);

        if (data.isItem() == 1) {
            ToDoItem d = (ToDoItem) data;
            if (d.getDueDate() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date convertedDate = dateFormat.parse(model.getValueAt(row, 3).toString());
                    d.setDueDate(convertedDate);
                } catch (ParseException parseException) {
                }
            }

            d.setMessage(model.getValueAt(row, 2).toString());
            d.setId(Integer.parseInt(model.getValueAt(row, 1).toString()));
        }


        moddel.writeItems();

    }
}
