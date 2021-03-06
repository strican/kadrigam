/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditorUI.java
 *
 * Created on May 2, 2010, 3:22:34 PM
 */
/**
 *
 * @author Willie
 */
public class EditorUI extends javax.swing.JFrame {

    /** Creates new form EditorUI */
    /*public EditorUI() {
        initComponents();
        createDeckButton.setEnabled(false);
        user = null;
    }*/

    public EditorUI(User u) {
        initComponents();
        createDeckButton.setEnabled(false);
        user = u;
        username.setText("Player: " + user.getAccountName());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createCardButton = new javax.swing.JButton();
        eidtDeckButton = new javax.swing.JButton();
        createDeckText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        createDeckButton = new javax.swing.JButton();
        successfulDeckLabel = new javax.swing.JLabel();
        username = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        createCardButton.setText("Create Card");
        createCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createCardButtonActionPerformed(evt);
            }
        });

        eidtDeckButton.setText("Edit Decks");
        eidtDeckButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eidtDeckButtonActionPerformed(evt);
            }
        });

        createDeckText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                createDeckTextKeyTyped(evt);
            }
        });

        jLabel1.setText("Deck Name:");

        createDeckButton.setText("Create");
        createDeckButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createDeckButtonActionPerformed(evt);
            }
        });

        successfulDeckLabel.setBackground(new java.awt.Color(204, 204, 204));
        successfulDeckLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        successfulDeckLabel.setForeground(new java.awt.Color(0, 0, 153));
        successfulDeckLabel.setText("Not Created");

        username.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        username.setText("Player");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(createDeckText, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(eidtDeckButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(createCardButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(successfulDeckLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(createDeckButton)))))
                .addContainerGap(79, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(username)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createDeckText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(successfulDeckLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createDeckButton))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createCardButton)
                    .addComponent(eidtDeckButton))
                .addGap(91, 91, 91))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createCardButtonActionPerformed
        // TODO add your handling code here:
        CreatorUI creator = new CreatorUI(user.getLibrary());
        creator.run();
    }//GEN-LAST:event_createCardButtonActionPerformed

    private void eidtDeckButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eidtDeckButtonActionPerformed
        DeckListUI dl = new DeckListUI(user);
        dl.run();
    }//GEN-LAST:event_eidtDeckButtonActionPerformed

    private void createDeckTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_createDeckTextKeyTyped
        if (createDeckText.getText().length() > 0) {
            createDeckButton.setEnabled(true);
        } else {
            createDeckButton.setEnabled(false);
        }
    }//GEN-LAST:event_createDeckTextKeyTyped

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowOpened

    private void createDeckButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createDeckButtonActionPerformed
        String newDeckName = createDeckText.getText();
        if(user.createDeck(newDeckName)){
            Login.serialize(user, user.getAccountName());
            successfulDeckLabel.setText(newDeckName + " Created!");
        }
        else successfulDeckLabel.setText(newDeckName + " Not Created");
    }//GEN-LAST:event_createDeckButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // serialize when closing frame to save any cards created
        Login.serialize(user, user.getAccountName());
    }//GEN-LAST:event_formWindowClosing
    /**
     * @param args the command line arguments
     */
    public void run(User u) {
           /*java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {


                new EditorUI(u).setVisible(true);
            }
        });*/
        setVisible(true);
    }
    //private final User user;

    private final User user;
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton createCardButton;
    private javax.swing.JButton createDeckButton;
    private javax.swing.JTextField createDeckText;
    private javax.swing.JButton eidtDeckButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel successfulDeckLabel;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
