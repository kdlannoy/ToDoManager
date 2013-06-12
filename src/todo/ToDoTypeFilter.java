/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import java.util.ArrayList;

/**
 *EDIT nog te implementeren
 * @author Kiani
 */
public class ToDoTypeFilter {
    private ArrayList<ToDoType> shownItems;

    public ToDoTypeFilter() {
        shownItems = new ArrayList<ToDoType>();    
    }
    
    /**
     * 
     * @param type defines what ToDoType should be shown in a list with this filter applied on 
     */
    public void addFilterOption(ToDoType type){
        shownItems.add(type);
    }
    
    /**
     * This method removes a ToDoItem from the filtered list, so the item won't show up on a list with this filter applied
     * 
     * @param ToDoType defines what type must not be shown anymore
     * @return returns 1 if succeeded to remove, 0 if the item is not in the list and -1 on an unexpected error.
     */
    public int removeFilterOption(ToDoType type){
        if(!shownItems.contains(type)){
            return 0;
        }else{
            shownItems.remove(type);
            return 1;
        }
    }

    public ArrayList<ToDoType> getAllowedTypes() {
        return shownItems;
    }
    
    
    
}
