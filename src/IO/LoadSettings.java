/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import todomanager.SettingsWriter;

/**
 *
 * @author Kiani
 * 
 * Wordt altijd opgeroepen in het begin van het programma, en laad de instellingen (waar bv de lijst staat)
 */
public class LoadSettings {

    public static ToDoSetting loadSettings() {
        ToDoSetting settings = new ToDoSetting();
        try {
            if (!new File("settings.ini").isFile()) {
                SettingsWriter.write();
                settings.setFile(null);
            } else {
                InputStream file = new FileInputStream(new File("settings.ini"));
                ObjectInput in = new ObjectInputStream(file);

                try {
                    settings = (ToDoSetting) in.readObject();
                } catch (ClassNotFoundException | IOException ex) {
                    Logger.getLogger(LoadSettings.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    in.close();
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(LoadSettings.class.getName()).log(Level.SEVERE, null, ex);
        }

        return settings;
    }
}
