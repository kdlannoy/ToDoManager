/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import java.io.Serializable;

/**
 *
 * @author Kiani
 */
public class ToDoType extends ToDoObject implements Serializable {
    private String type;
    private static int numberOfTypes;

    public ToDoType(String type) {
        this.type = type;
        numberOfTypes++;
        item = 0;
    }
    
    @Override
    public boolean equals(Object b){
        if (this.type.equals(((ToDoType)b).getType()))
                return true;
        return false;
    }

    public String getType() {
        return type;
    }

    public static int getNumberOfTypes() {
        return numberOfTypes;
    }
    
    @Override
    public String toString(){
        return type;
    }
    
    
    
}
