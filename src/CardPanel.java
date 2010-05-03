/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CardPanel.java
 *
 * Created on May 2, 2010, 2:24:27 PM
 */

/**
 *
 * @author aidandaly
 */
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.File;

public class CardPanel extends javax.swing.JPanel {

    /** Creates new form CardPanel */

    public CardPanel(){
        initComponents();
        cardName.setText("");
        neutral.setText("");
        red.setText("");
        blue.setText("");
        green.setText("");
        white.setText("");
        black.setText("");
    }

    public CardPanel(Card c) {
        initComponents();
        this.c = c;
        updateDisplay();
    }

    public void updateDisplay() {
        displayName();
        displayCost();
        displayInfo();
        this.repaint();
    }

    public void changeCard(Card c) {
        this.c = c;
        updateDisplay();
    }

    private void displayName()
    {
        if (c!=null)
        {
            cardName.setText(c.getName());
        }
    }

    private void displayCost()
    {
        PointList cost = c.getCost();
        neutral.setText(""+cost.get(Color.NEUTRAL));
        red.setText(""+cost.get(Color.RED));
        green.setText(""+cost.get(Color.GREEN));
        blue.setText(""+cost.get(Color.BLUE));
        white.setText(""+cost.get(Color.WHITE));
        black.setText(""+cost.get(Color.BLACK));
        if (c instanceof Creature)
        {
            PointList po = ((Creature)c).getPayoff();
            neutral.setText(neutral.getText()+"/"+po.get(Color.NEUTRAL));
            red.setText(red.getText()+"/"+po.get(Color.RED));
            green.setText(green.getText()+"/"+po.get(Color.GREEN));
            blue.setText(blue.getText()+"/"+po.get(Color.BLUE));
            white.setText(white.getText()+"/"+po.get(Color.WHITE));
            black.setText(black.getText()+"/"+po.get(Color.BLACK));
        }
    }

    private void displayInfo()
    {
        if (c instanceof Creature)
        {
            Ability a = ((Creature)c).getAbility();
            if (a != null)
            {
                Trigger t = a.getTrigger();
                cardInfo.setText("  "+t.toString()+":\n");
                cardInfo.setText(cardInfo.getText()+"  "+
                        a.getEffect().toString());
            }
            cardInfo.setText(cardInfo.getText()+
                    "  POW: "+((Creature)c).getPow()+"\n");
            cardInfo.setText(cardInfo.getText()+
                    "  HP: "+((Creature)c).getHP()+"\n");
        }
        else
        {
            if (((Spell)c).getEffect()!=null)
                cardInfo.setText(((Spell)c).getEffect().toString());
        }
    }
    
    private String getImagePath()
    {
        String dir = System.getProperty("user.dir");
        if (c.getImage() != null)
            return c.getImage().getPath();
        else
            return dir+"/src/swirl.jpg";
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        //String dir = System.getProperty("user.dir");
        String imagePath = getImagePath();
        img = CardPanel.getBufferedImage(imagePath, this);
        Rectangle imageRect = new Rectangle(135, 80, 225, 200);
        TexturePaint imagePaint1 = new TexturePaint(img, imageRect);
        g2d.setPaint(imagePaint1);
        g2d.fill(imageRect);
        g2d.draw(imageRect);
    }

    public static BufferedImage getBufferedImage(String imageFile, Component c)
    {
        Image image = c.getToolkit().getImage(imageFile);
        waitForImage(image, c);
        /*BufferedImage bufferedImage = new BufferedImage(image.getWidth(c),
                image.getHeight(c), BufferedImage.TYPE_INT_RGB);*/
        BufferedImage bufferedImage = new BufferedImage(100,
                100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(image, 0, 0, c);
        return(bufferedImage);
    }
/** Take an Image associated with a file, and wait until it is * done loading (just a simple application of MediaTracker). * If you are loading multiple images, don't use this * consecutive times; instead, use the version that takes
* an array of images. */
    public static boolean waitForImage(Image image, Component c)
    {
        MediaTracker tracker = new MediaTracker(c);
        tracker.addImage(image, 0);
        try { tracker.waitForAll(); }
        catch(InterruptedException ie) {}
        return(!tracker.isErrorAny());
    }


    public static void main(String args[]) {
        JFrame j = new JFrame();
        CardPanel p = new CardPanel(Test.randomCard());
        j.add(p);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setBounds(0, 0, 400, 520);
        j.setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        cardInfo = new javax.swing.JTextArea();
        namePanel = new javax.swing.JPanel();
        cardName = new javax.swing.JLabel();
        neutral = new javax.swing.JTextField();
        red = new javax.swing.JTextField();
        green = new javax.swing.JTextField();
        blue = new javax.swing.JTextField();
        white = new javax.swing.JTextField();
        black = new javax.swing.JTextField();

        setBackground(new java.awt.Color(153, 51, 0));
        setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new javax.swing.ImageIcon(getClass().getResource("/swirl.jpg")))); // NOI18N

        cardInfo.setBackground(new java.awt.Color(241, 192, 117));
        cardInfo.setColumns(20);
        cardInfo.setEditable(false);
        cardInfo.setFont(new java.awt.Font("Charlemagne Std", 0, 18));
        cardInfo.setRows(5);
        cardInfo.setWrapStyleWord(true);
        cardInfo.setAutoscrolls(false);
        jScrollPane1.setViewportView(cardInfo);

        namePanel.setBackground(cardInfo.getBackground());

        cardName.setFont(new java.awt.Font("Charlemagne Std", 0, 18));
        cardName.setText("jLabel1");

        org.jdesktop.layout.GroupLayout namePanelLayout = new org.jdesktop.layout.GroupLayout(namePanel);
        namePanel.setLayout(namePanelLayout);
        namePanelLayout.setHorizontalGroup(
            namePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(namePanelLayout.createSequentialGroup()
                .add(cardName)
                .addContainerGap(331, Short.MAX_VALUE))
        );
        namePanelLayout.setVerticalGroup(
            namePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, namePanelLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .add(cardName))
        );

        neutral.setBackground(new java.awt.Color(204, 204, 204));
        neutral.setColumns(6);
        neutral.setEditable(false);
        neutral.setFont(new java.awt.Font("Charlemagne Std", 0, 14));
        neutral.setText("jTextField1");
        neutral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                neutralActionPerformed(evt);
            }
        });

        red.setBackground(new java.awt.Color(204, 0, 0));
        red.setColumns(6);
        red.setEditable(false);
        red.setFont(new java.awt.Font("Charlemagne Std", 0, 14));
        red.setText("jTextField2");

        green.setBackground(new java.awt.Color(102, 153, 0));
        green.setColumns(6);
        green.setEditable(false);
        green.setFont(new java.awt.Font("Charlemagne Std", 0, 14));
        green.setText("jTextField3");

        blue.setBackground(new java.awt.Color(0, 0, 153));
        blue.setColumns(6);
        blue.setEditable(false);
        blue.setFont(new java.awt.Font("Charlemagne Std", 0, 14));
        blue.setForeground(new java.awt.Color(255, 255, 255));
        blue.setText("jTextField4");

        white.setColumns(6);
        white.setEditable(false);
        white.setFont(new java.awt.Font("Charlemagne Std", 0, 14));
        white.setText("jTextField5");

        black.setBackground(new java.awt.Color(0, 0, 0));
        black.setColumns(6);
        black.setEditable(false);
        black.setFont(new java.awt.Font("Charlemagne Std", 0, 14));
        black.setForeground(new java.awt.Color(255, 255, 255));
        black.setText("jTextField6");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, namePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(white, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(neutral, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(red, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(green, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(blue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, black, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(namePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(neutral, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(5, 5, 5)
                .add(red, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(6, 6, 6)
                .add(green, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(5, 5, 5)
                .add(blue, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(white, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(black, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void neutralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_neutralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_neutralActionPerformed

    private Card c;
    private BufferedImage img;
    private File f;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField black;
    private javax.swing.JTextField blue;
    private javax.swing.JTextArea cardInfo;
    private javax.swing.JLabel cardName;
    private javax.swing.JTextField green;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel namePanel;
    private javax.swing.JTextField neutral;
    private javax.swing.JTextField red;
    private javax.swing.JTextField white;
    // End of variables declaration//GEN-END:variables

}
