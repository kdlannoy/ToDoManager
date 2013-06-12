/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Kiani
 */
public class ToDoSetting implements Serializable{
    private File file;

    public ToDoSetting() {
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    
    
}
