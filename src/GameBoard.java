/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GameBoard.java
 *
 * Created on Apr 30, 2010, 12:43:21 PM
 */

/**
 *
 * @author Steve
 */
public class GameBoard extends javax.swing.JFrame {

    private Player p;
    private Player opp;
    private Rules r;

    /** Creates new form GameBoard */
    public GameBoard(Player p, Player opp, Rules r)
    {
        this.p = p;
        this.opp = opp;
        this.r = r;
        initComponents();
    }

      public boolean isOver()
      {
        boolean p1lost = true;
        boolean p2lost = true;

        for (Color c : Color.values())
        {
          p1lost = p1lost && (p.getLife().get(c) <= 0);
          p2lost = p2lost && (opp.getLife().get(c) <= 0);
        }
        p1lost = (p1lost || p.getDeck().isEmpty());
        p2lost = (p2lost || opp.getDeck().isEmpty());

        return (p1lost || p2lost);
      }

      // Ends game
      public Player winner()
      {
          //TODO: Make game end
        boolean p1lost = true;
        boolean p2lost = true;


          for (Color c : Color.values())
          {
            p1lost = p1lost && (p.getLife().get(c) == 0);
            p2lost = p2lost && (opp.getLife().get(c) == 0);
          }
          p1lost = (p1lost || p.getDeck().isEmpty());
          p2lost = (p2lost || opp.getDeck().isEmpty());

          if (p1lost && !p2lost)
            return opp;
          else if (p2lost && !p1lost)
            return p;
          else
            //Throw a tie game exception?
            return null;

      }

    public void getDamageInput(Card c)
    {
        damageSlider.setVisible(true);
        damageButton.setVisible(true);



        int dmg = damageSlider.getValue();
        Move m = new DamageMove(p, c, dmg);

        if (m.isLegal(r))
        {
            m.execute();
            opp.subDamage(dmg);
        }
    }



    public void dealDirect()
    {
        PointVal splash = new PointVal(Color.NEUTRAL,opp.getDamage());
        ColorPoints dmg = new ColorPoints();
        dmg.add(splash);
        p.takeDamage(dmg);
    }

    public void update()
    {
        PointList health = p.getLife();
        neutLife.setText("" + health.get(Color.NEUTRAL));
        redLife.setText("" + health.get(Color.RED));
        greenLife.setText("" + health.get(Color.GREEN));
        blueLife.setText("" + health.get(Color.BLUE));
        whiteLife.setText("" + health.get(Color.WHITE));
        blackLife.setText("" + health.get(Color.BLACK));

        neutLife.setForeground(java.awt.Color.GRAY);
        redLife.setForeground(java.awt.Color.RED);
        greenLife.setForeground(java.awt.Color.GREEN);
        blueLife.setForeground(java.awt.Color.BLUE);
        whiteLife.setForeground(java.awt.Color.WHITE);
        blackLife.setForeground(java.awt.Color.BLACK);

        card1.setText(""+ p.getHand().display(0));
        card2.setText(""+ p.getHand().display(1));
        card3.setText(""+ p.getHand().display(2));
        card4.setText(""+ p.getHand().display(3));
        card5.setText(""+ p.getHand().display(4));
        card6.setText(""+ p.getHand().display(5));
        card7.setText(""+ p.getHand().display(6));
        card8.setText(""+ p.getHand().display(7));

        spellStack.setText(p.getSpellStack().toString());

        ally1.setText("" + p.getAllies().display(0));
        ally2.setText("" + p.getAllies().display(1));
        ally3.setText("" + p.getAllies().display(2));
        ally4.setText("" + p.getAllies().display(3));
        ally5.setText("" + p.getAllies().display(4));

        updateButton();
        updatePhase();
    }

    private void updatePhase()
    {
        Type t = p.getPhase();
        System.out.println(t);
        switch(t)
        {
            case DRAW:
                phaseLabel.setText("Upkeep");
                break;
            case PLAY1:
                phaseLabel.setText("Play Phase 1");
                break;
            case PLAY2:
                phaseLabel.setText("Play Phase 2");
                break;
            case WAIT:
                phaseLabel.setText("Please Wait");
                break;
            case ATTACK:
                phaseLabel.setText("Attack Phase");
                break;
            case DAMAGE:
                phaseLabel.setText("Damage Phase");
                break;
            case SACRIFICE:
                phaseLabel.setText("Sacrifice");
                break;
            case DISCARD:
                phaseLabel.setText("Discard");
                break;
            default:
                phaseLabel.setText("Game in Progress");
                break;
        }
    }
    
    public void updateButton()
    {
        Type t = p.getPhase();
        switch(t)
        {
            case DRAW:
                phaseButton.setText("Draw a Card");
                break;
            case SACRIFICE:
                phaseButton.setText("Move to First Play Phase");
                break;
            case PLAY1:
                phaseButton.setText("Move to Attack Phase");
                break;
            case ATTACK:
                phaseButton.setText("Finish Attacking");
                break;
            case DAMAGE:
                phaseButton.setText("End Damage Phase");
                break;
            case PLAY2:
                phaseButton.setText("Move to Discard Phase");
                break;
            case DISCARD:
                phaseButton.setText("End Turn");
                break;
            case WAIT:
                phaseButton.setText("Wait for Opponent");
                break;
            default:
                break;
        }
    }

    private int promptInt()
    {
        return 0;
    }

    private void handleAlly(int i)
    {
        Card c = p.getAllies().getCard(i);

        // Do nothing if there is no card there
        if (c == null)
        {
            return;
        }

        // Else do something based on the phase
        Type phase = p.getPhase();
        Move m;
        switch(phase)
        {
              case PLAY1:
                  break;

              case PLAY2:
                  break;

              case DISCARD:
                  break;

              case ATTACK:
                  m = new AttackMove(p, c);
                  if (m.isLegal(r))
                  {
                      m.execute();
                  }
                  System.out.println(p.getDamage());
                  break;

              case DAMAGE:
                  getDamageInput(c);
                  break;

              case SACRIFICE:
                  m = new SacrificeMove(p, c);
                  if (m.isLegal(r))
                      m.execute();
                  break;

              default:
                  break;
        }
        
        update();
    }

    private void handleHand(int i)
    {
        
        //spellStack.setText(p.getHand().toString());
        Card c = p.getHand().getCard(i);
        spellStack.setText(c.toString());


        // Do nothing if there is no card there
        if (c == null)
        {
            return;
        }

        // Else do something based on the phase
        Type phase = p.getPhase();
        Move m;
        switch(phase)
        {
              case PLAY1:
                  m = new Play1Move(p, c);
                  if (m.isLegal(r))
                      m.execute();
                  break;

              case PLAY2:
                m = new Play2Move(p, c);
                  if (m.isLegal(r))
                      m.execute();
                  break;

              case DISCARD:
                  m = new DiscardMove(p, c);
                  if (m.isLegal(r))
                      m.execute();
                  break;

              case ATTACK:
                  break;

              case DAMAGE:
                  break;

              case SACRIFICE:
                  break;

              default:
                  break;
        }

        update();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        life = new javax.swing.JLabel();
        neutLabel = new javax.swing.JLabel();
        redLabel = new javax.swing.JLabel();
        greenLabel = new javax.swing.JLabel();
        blueLabel = new javax.swing.JLabel();
        whiteLabel = new javax.swing.JLabel();
        blackLabel = new javax.swing.JLabel();
        blackLife = new javax.swing.JLabel();
        whiteLife = new javax.swing.JLabel();
        neutLife = new javax.swing.JLabel();
        redLife = new javax.swing.JLabel();
        greenLife = new javax.swing.JLabel();
        blueLife = new javax.swing.JLabel();
        card1 = new javax.swing.JTextArea();
        card2 = new javax.swing.JTextArea();
        card3 = new javax.swing.JTextArea();
        card4 = new javax.swing.JTextArea();
        card5 = new javax.swing.JTextArea();
        card6 = new javax.swing.JTextArea();
        card7 = new javax.swing.JTextArea();
        card8 = new javax.swing.JTextArea();
        spellStack = new javax.swing.JTextArea();
        phaseButton = new javax.swing.JButton();
        phaseLabel = new javax.swing.JLabel();
        ally1 = new javax.swing.JTextArea();
        ally2 = new javax.swing.JTextArea();
        ally3 = new javax.swing.JTextArea();
        ally4 = new javax.swing.JTextArea();
        ally5 = new javax.swing.JTextArea();
        damageSlider = new javax.swing.JSlider();
        damageButton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jTree1);

        jScrollPane2.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        life.setText("Health");
        life.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lifeMouseClicked(evt);
            }
        });
        life.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lifePropertyChange(evt);
            }
        });

        neutLabel.setText("Neutral:");

        redLabel.setText("Red:");

        greenLabel.setText("Green:");

        blueLabel.setText("Blue:");

        whiteLabel.setText("White:");

        blackLabel.setText("Black:");

        blackLife.setText("jLabel2");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, blackLabel, org.jdesktop.beansbinding.ELProperty.create("${horizontalAlignment}"), blackLife, org.jdesktop.beansbinding.BeanProperty.create("horizontalAlignment"));
        bindingGroup.addBinding(binding);

        whiteLife.setText("jLabel1");

        neutLife.setText("jLabel1");

        redLife.setText("jLabel2");

        greenLife.setText("jLabel1");

        blueLife.setText("jLabel2");

        card1.setColumns(20);
        card1.setRows(5);
        card1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card1MouseClicked(evt);
            }
        });

        card2.setColumns(20);
        card2.setRows(5);
        card2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card2MouseClicked(evt);
            }
        });

        card3.setColumns(20);
        card3.setRows(5);
        card3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card3MouseClicked(evt);
            }
        });

        card4.setColumns(20);
        card4.setRows(5);
        card4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card4MouseClicked(evt);
            }
        });

        card5.setColumns(20);
        card5.setRows(5);
        card5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card5MouseClicked(evt);
            }
        });

        card6.setColumns(20);
        card6.setRows(5);
        card6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card6MouseClicked(evt);
            }
        });

        card7.setColumns(20);
        card7.setRows(5);
        card7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card7MouseClicked(evt);
            }
        });

        card8.setColumns(20);
        card8.setRows(5);
        card8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card8MouseClicked(evt);
            }
        });

        spellStack.setColumns(20);
        spellStack.setRows(5);

        phaseButton.setText("Play 1");
        phaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phaseButtonActionPerformed(evt);
            }
        });

        phaseLabel.setText("Phase");

        ally1.setColumns(20);
        ally1.setRows(5);
        ally1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ally1MouseClicked(evt);
            }
        });

        ally2.setColumns(20);
        ally2.setRows(5);
        ally2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ally2MouseClicked(evt);
            }
        });

        ally3.setColumns(20);
        ally3.setRows(5);
        ally3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ally3MouseClicked(evt);
            }
        });

        ally4.setColumns(20);
        ally4.setRows(5);
        ally4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ally4MouseClicked(evt);
            }
        });

        ally5.setColumns(20);
        ally5.setRows(5);
        ally5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ally5MouseClicked(evt);
            }
        });

        damageButton.setText("jButton1");
        damageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                damageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(290, 290, 290)
                .addComponent(life, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(neutLife)
                    .addComponent(neutLabel))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(redLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(redLife)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(greenLife)
                        .addGap(18, 18, 18)
                        .addComponent(blueLife)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(whiteLife)
                        .addGap(18, 18, 18)
                        .addComponent(blackLife))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(409, 409, 409)
                        .addComponent(greenLabel)
                        .addGap(18, 18, 18)
                        .addComponent(blueLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(whiteLabel)
                        .addGap(18, 18, 18)
                        .addComponent(blackLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 430, Short.MAX_VALUE)
                .addComponent(spellStack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card8, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card7, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card6, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card5, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 514, Short.MAX_VALUE)
                .addComponent(phaseLabel)
                .addGap(217, 217, 217)
                .addComponent(phaseButton)
                .addGap(256, 256, 256))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ally1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ally2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ally3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ally4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ally5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(damageSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(damageButton))
                .addContainerGap(499, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(phaseButton)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(life, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(neutLabel)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(redLabel)
                                                .addComponent(greenLabel)
                                                .addComponent(blueLabel)
                                                .addComponent(blackLabel)
                                                .addComponent(whiteLabel)))
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(blackLife)
                                            .addComponent(redLife)
                                            .addComponent(greenLife)
                                            .addComponent(blueLife)
                                            .addComponent(whiteLife)))
                                    .addComponent(neutLife))
                                .addGap(90, 90, 90))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(spellStack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(ally1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(71, 71, 71)
                                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(11, 11, 11)
                                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(11, 11, 11)
                                    .addComponent(card5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(card6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(11, 11, 11)
                                    .addComponent(card7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(card8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(90, 90, 90)
                                    .addComponent(ally2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(364, 364, 364))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(90, 90, 90)
                                    .addComponent(ally3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(364, 364, 364))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(90, 90, 90)
                                    .addComponent(ally4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(364, 364, 364))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(90, 90, 90)
                                    .addComponent(ally5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(364, 364, 364)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addComponent(damageSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(damageButton)
                                .addGap(350, 350, 350)
                                .addComponent(phaseLabel)))))
                .addGap(51, 51, 51))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lifePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lifePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_lifePropertyChange

    private void lifeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lifeMouseClicked
        //life.setText("Health: " + p.getLife().toStringConcise());
    }//GEN-LAST:event_lifeMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        update();

    }//GEN-LAST:event_formMouseClicked

    private void card2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card2MouseClicked
        handleHand(1);
    }//GEN-LAST:event_card2MouseClicked

    private void phaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phaseButtonActionPerformed
        Type t = p.getPhase();
        switch(t)
        {
            case DRAW:
                Move draw = new DrawMove(p);
                if (draw.isLegal(r))
                    draw.execute();
                else if (isOver())
                    winner();

                p.setPhase(Type.SACRIFICE);
                break;
            case SACRIFICE:
                p.setPhase(Type.PLAY1);
                break;
            case PLAY1:
                p.setPhase(Type.ATTACK);
                break;
            case ATTACK:
                p.setPhase(Type.WAIT);
                opp.setPhase(Type.DAMAGE);
                break;
            case DAMAGE:
                dealDirect();
                p.setPhase(Type.WAIT);
                opp.setPhase(Type.PLAY2);
                break;
            case PLAY2:
                p.setPhase(Type.DISCARD);
                break;
            case DISCARD:
                if (p.getHand().size() >= 8)
                    break;

                p.setPhase(Type.WAIT);
                opp.setPhase(Type.DRAW);
                break;
            case WAIT:
                break;
            default:
                p.setPhase(Type.SACRIFICE);
                break;
        }

        updateButton();

        update();
    }//GEN-LAST:event_phaseButtonActionPerformed

    private void card1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card1MouseClicked
        handleHand(0);
    }//GEN-LAST:event_card1MouseClicked

    private void card3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card3MouseClicked
        handleHand(2);
    }//GEN-LAST:event_card3MouseClicked

    private void card4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card4MouseClicked
        handleHand(3);
    }//GEN-LAST:event_card4MouseClicked

    private void card5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card5MouseClicked
        handleHand(4);
    }//GEN-LAST:event_card5MouseClicked

    private void card6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card6MouseClicked
        handleHand(5);
    }//GEN-LAST:event_card6MouseClicked

    private void card7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card7MouseClicked
        handleHand(6);
    }//GEN-LAST:event_card7MouseClicked

    private void card8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card8MouseClicked
        handleHand(7);
    }//GEN-LAST:event_card8MouseClicked

    private void ally1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ally1MouseClicked
        handleAlly(0);
    }//GEN-LAST:event_ally1MouseClicked

    private void ally2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ally2MouseClicked
        handleAlly(1);
    }//GEN-LAST:event_ally2MouseClicked

    private void ally3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ally3MouseClicked
        handleAlly(2);
    }//GEN-LAST:event_ally3MouseClicked

    private void ally4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ally4MouseClicked
        handleAlly(3);
    }//GEN-LAST:event_ally4MouseClicked

    private void ally5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ally5MouseClicked
        handleAlly(4);
    }//GEN-LAST:event_ally5MouseClicked

    private void damageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_damageButtonActionPerformed
        damageButton.setVisible(false);
        damageSlider.setVisible(false);
        damageSlider.setValue(0);

    }//GEN-LAST:event_damageButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameBoard().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea ally1;
    private javax.swing.JTextArea ally2;
    private javax.swing.JTextArea ally3;
    private javax.swing.JTextArea ally4;
    private javax.swing.JTextArea ally5;
    private javax.swing.JLabel blackLabel;
    private javax.swing.JLabel blackLife;
    private javax.swing.JLabel blueLabel;
    private javax.swing.JLabel blueLife;
    private javax.swing.JTextArea card1;
    private javax.swing.JTextArea card2;
    private javax.swing.JTextArea card3;
    private javax.swing.JTextArea card4;
    private javax.swing.JTextArea card5;
    private javax.swing.JTextArea card6;
    private javax.swing.JTextArea card7;
    private javax.swing.JTextArea card8;
    private javax.swing.JButton damageButton;
    private javax.swing.JSlider damageSlider;
    private javax.swing.JLabel greenLabel;
    private javax.swing.JLabel greenLife;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel life;
    private javax.swing.JLabel neutLabel;
    private javax.swing.JLabel neutLife;
    private javax.swing.JButton phaseButton;
    private javax.swing.JLabel phaseLabel;
    private javax.swing.JLabel redLabel;
    private javax.swing.JLabel redLife;
    private javax.swing.JTextArea spellStack;
    private javax.swing.JLabel whiteLabel;
    private javax.swing.JLabel whiteLife;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

}
