/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import todo.ToDoItem;
import todo.ToDoModel;

/**
 *
 * @author Kiani
 */
public class ToDoPanel extends javax.swing.JPanel {

    
    private ToDoList todolijst;
    private ToDoModel model;
    private JScrollPane sp;
    
    
    
    
    /**
     * Creates new form ToDoPanel
     */
    public ToDoPanel(ToDoModel model) {
        initComponents();
        this.model = model;
        this.todolijst = new ToDoList(model);
        
        //jTable1 = todolijst;
        //JScrollPane sp = new JScrollPane();
        //sp.add();
        
        
        sp = new JScrollPane(todolijst,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        todolijst.setFillsViewportHeight(true);
        todolijst.setMinimumSize(getSize());
        
        //sp.add(todolijst);
        
        jPanel2.add(sp, BorderLayout.CENTER);
        
        
        //action nu
        AbstractAction addAction = new AbstractAction("Add new item") {
            @Override
            public void actionPerformed(ActionEvent e) {
                createItem();
            }
        };
        jButton1.setAction(addAction);

        
        AbstractAction removeAction = new AbstractAction("Remove item") {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //removeItemModel((ToDoItem) todolijst.getSelectedValue());
                    removeItemModelByString(todolijst.getSelectedValue().toString());
                    writeModel();
                } catch (Exception ex) {
                    System.out.println("Geen item geselecteerd:\t"+ex);
                }
                todolijst.updateList();
            }
        };
        
        
        jButton2.setAction(removeAction);
        
        //zorg dat je met delete dingen kan verwijderen
        todolijst.getInputMap().put(KeyStroke.getKeyStroke("DELETE"),"DELETE");
        todolijst.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "ENTER");
        
        todolijst.getActionMap().put("DELETE", removeAction);
        todolijst.getActionMap().put("ENTER", addAction);
        
        this.getInputMap().put(KeyStroke.getKeyStroke("DELETE"),"DELETE");
        this.getActionMap().put("DELETE", removeAction);
    }

    
    public JButton getButton() {
        return jButton1;
    }

    protected void removeItemModel(ToDoItem item) {
        model.removeItemString(item.getMessage());
    }
    
    protected void removeItemModelByString(String s){
        model.removeItemString(s);
    }

    public ToDoList getTodolijst() {
        return todolijst;
    }

    private void writeModel() {
        model.writeItems();
    }

    //maakt een JFrame waarin je een item kan specifieren en toevoegen (Item Creator)
    public void createItem() {
        final JFrame frm = new ItemCreator(model);
        frm.setSize(300, 170);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        //zorg ervoor dat de frame wordt gesloten door escape in te drukken
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);
        Action escapeAction = new AbstractAction() {
            // close the frame when the user presses escape
            @Override
            public void actionPerformed(ActionEvent e) {
                frm.dispose();
            }
        };
        frm.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        frm.getRootPane().getActionMap().put("ESCAPE", escapeAction);


    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        jButton1.setText("Add Item");

        jButton2.setText("Remove Item");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setMaximumSize(new java.awt.Dimension(300, 1200));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 1200));
        jPanel2.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 313, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
