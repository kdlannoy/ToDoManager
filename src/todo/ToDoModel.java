/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import IO.LoadSettings;
import IO.ToDoLoader;
import IO.ToDoSetting;
import IO.ToDoWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Kiani
 */
public class ToDoModel extends DefaultListModel {

    /**
     * Lijst van geregistreerde luisteraars.
     */
    private List<ChangeListener> ll2 = new ArrayList<>();
    private List<ToDoType> types = new ArrayList<>();
    private List<ToDoItem> items = new ArrayList<>();
    private ToDoSetting settings;

    public ToDoModel() {
        settings = LoadSettings.loadSettings();

        ToDoLoader tdl = new ToDoLoader(settings.getFile());
        for (ToDoItem i : tdl.getItems()) {
            addItem(i);
        }

    }

    /**
     * Breng alle luisteraars op de hoogte van een verandering van het model.
     */
    protected void fireStateChanged() {
        for (ChangeListener listener : ll2) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    public void addListener(ChangeListener listener) {
        ll2.add(listener);
    }

    public void removeListener(ChangeListener listener) {
        ll2.remove(listener);
    }

    public List<ToDoType> getTypes() {
        return types;
    }

    public List<ToDoItem> getItems() {
        return items;
    }

    public void addItem(ToDoItem item) {
        if (!containsTypeString(item.getType().getType())) {
            types.add(item.getType());

        }
        item.setType(getTypeString(item.getType().getType()));
        items.add(item);
        Collections.sort(items, new CustomComparator());
        fireStateChanged();

    }

    public ToDoType getTypeString(String t) {
        for (ToDoType d : types) {
            if (d.getType().equals(t)) {
                return d;
            }
        }
        return null;
    }

    public boolean containsTypeString(String t) {
        for (ToDoType d : getTypes()) {
            if (d.getType().equals(t)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Schrijft de huidige lijst van items weg naar het bestand gespecifieerd in de settings
     */
    public void writeItems() {
        System.out.println("Items geschreven naar " + settings.getFile());
        ToDoWriter writer = new ToDoWriter(settings.getFile());
        writer.writeItems(items);
    }

    public void removeItemString(String message) {
        ToDoType type=null;
        for (ToDoItem i : getItems()) {
            if (i.getMessage().equals(message)) {
                type = i.getType();
                items.remove(i);
                break;
            }
        }
        Collections.sort(items, new CustomComparator());
        int aantal = 0;
        for (ToDoItem i: getItems()){
            if(i.getType().equals(type)){
                aantal++;
            }
        }
        if(aantal==0){
            removeTypeString(type.getType());
        }
    }

    private void removeTypeString(String type) {
        for (ToDoType i : getTypes()) {
            if (i.getType().equals(type)) {
                types.remove(i);
                return;
            }
        }
        
    }
}

//klasse om twee ToDoItems te kunnen vergelijken (om te kunnen sorteren)
class CustomComparator implements Comparator<ToDoItem> {
    @Override
    public int compare(ToDoItem o1, ToDoItem o2) {
        return o1.getMessage().compareTo(o2.getMessage());
    }
}