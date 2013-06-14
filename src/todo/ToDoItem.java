/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Kiani
 * 
 * EDIT hier moet nog een Filter worden toegepast, net als Prioriteit/importance en dueDate
 * 
 * 
 * 
 * @param id, defines the id of the ToDoItem
 * @param type, defines the type of the item
 * @param dueDate defines the date when the item should be done
 * @param message has the message to show for the list
 * @param importance indicatos how important the item is (from a scale from 0-10, 0 meaning not important at all)
 */
public class ToDoItem extends ToDoObject implements Serializable{
    
    private static int totalItems;
    
    private int id;
    private ToDoType type;
    private Date dueDate;
    private String message;
    private int importance;

    public ToDoItem(ToDoType type, String message) {
        this.type = type;
        this.message = message;
        totalItems++;
        id = totalItems;
        item = true;
    }

    public ToDoItem(String text, String text0) {
        this.type = new ToDoType(text);
        this.message = text0;
        totalItems++;
        id = totalItems;
        item = true;
    }
    
    public int getId() {
        return id;
    }
    
    public ToDoType getType() {
        return type;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getMessage() {
        return message;
    }

    public static int getTotalItems() {
        return totalItems;
    }
    
    
    
    /**
     * 
     * @return return how important the task is, from a scale from 0-10 (0 meaning not important at all)
     */
    public int getImportance() {
        return importance;
    }

    /**
     *
     * @param type
     */
    public void setType(ToDoType type) {
        this.type = type;
    }

    public int setDueDate(Date dueDate) {
        if(dueDate.before(new Date())){
            System.out.println("The date is already past");
            return -1;
        }
        this.dueDate = dueDate;
        return 1;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int setImportance(int importance) {
        if(importance>10 || importance <0){
            System.out.println("The importance is not in the right range (0-10)");
            return -1;
        }
        this.importance = importance;
        return 1;
    }
    
    
    
    @Override
    public String toString(){
        return message;
    }

    void setId(int id) {
        this.id = id;
    }
    
    
}
