/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ImageChooser.java
 *
 * Created on May 1, 2010, 4:24:41 PM
 */

/**
 *
 * @author aidandaly
 */
public class ImageChooser extends javax.swing.JFrame {

    /** Creates new form ImageChooser */
    public ImageChooser() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        c = new CardCreator();
        jFileChooser1 = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Choose Card Image");
        setBackground(new java.awt.Color(153, 51, 0));

        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jFileChooser1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 529, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .add(jFileChooser1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 325, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        // TODO add your handling code here:
        c.setImage(jFileChooser1.getSelectedFile());
        this.dispose();
    }//GEN-LAST:event_jFileChooser1ActionPerformed


    public void run(CardCreator c) {
        this.c = c;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImageChooser().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private CardCreator c;
    private javax.swing.JFileChooser jFileChooser1;
    // End of variables declaration//GEN-END:variables

}