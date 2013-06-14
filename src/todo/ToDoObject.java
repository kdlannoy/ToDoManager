/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

/**
 *
 * @author Kiani
 */
public abstract class ToDoObject {
    //0 = type
    //1 = item
    //-1 = header
    protected int item=-1;
    
    public int isItem(){
        return item;
    }
    
    
    public void setIsItem(int i){
        item = i;
    }
}
