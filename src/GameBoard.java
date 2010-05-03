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
    private Card selected;

    /** Creates new form GameBoard */
    public GameBoard(Player p, Player opp, Rules r) {
        this.p = p;
        this.opp = opp;
        this.r = r;
        initComponents();
        update();
    }

    public boolean isOver() {
        boolean p1lost = true;
        boolean p2lost = true;

        for (Color c : Color.values()) {
            p1lost = p1lost && (p.getLife().get(c) <= 0);
            p2lost = p2lost && (opp.getLife().get(c) <= 0);
        }
        p1lost = (p1lost || p.getDeck().isEmpty());
        p2lost = (p2lost || opp.getDeck().isEmpty());

        return (p1lost || p2lost);
    }

    // Ends game
    public Player winner() {
        //TODO: Make game end
        boolean p1lost = true;
        boolean p2lost = true;


        for (Color c : Color.values()) {
            p1lost = p1lost && (p.getLife().get(c) == 0);
            p2lost = p2lost && (opp.getLife().get(c) == 0);
        }
        p1lost = (p1lost || p.getDeck().isEmpty());
        p2lost = (p2lost || opp.getDeck().isEmpty());

        if (p1lost && !p2lost) {
            return opp;
        } else if (p2lost && !p1lost) {
            return p;
        } else //Throw a tie game exception?
        {
            return null;
        }

    }

    public void getDamageInput(Card c) {
        damageSlider.setVisible(true);
        damageButton.setVisible(true);
        damageLabel.setVisible(true);

        System.out.println(opp.getDamage());
        System.out.println(((Creature) c).getHP());
        damageSlider.setMaximum(Math.min(opp.getDamage(), ((Creature) c).getHP()));
        selected = c;
    }

    public void dealDirect() {
        PointVal splash = new PointVal(Color.NEUTRAL, opp.getDamage());
        ColorPoints dmg = new ColorPoints();
        dmg.add(splash);
        p.takeDamage(dmg);
    }

    public void update() {
/*        PointList health = p.getLife();
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
*/
        ally1.changeCard(p.getAllies().getCard(0));

        if (ally1.getCard() == null)
            ally1.setVisible(false);
        else
            ally1.updateDisplay();
        
        card1.setText("" + p.getHand().display(0));
        card2.setText("" + p.getHand().display(1));
        card3.setText("" + p.getHand().display(2));
        card4.setText("" + p.getHand().display(3));
        card5.setText("" + p.getHand().display(4));
        card6.setText("" + p.getHand().display(5));
        card7.setText("" + p.getHand().display(6));
        card8.setText("" + p.getHand().display(7));

        //spellStack.setText(p.getSpellStack().toString());

//        ally1.setText("" + p.getAllies().display(0));
        ally2.setText("" + p.getAllies().display(1));
        ally3.setText("" + p.getAllies().display(2));
        ally4.setText("" + p.getAllies().display(3));
        ally5.setText("" + p.getAllies().display(4));

        updateButton();
        updatePhase();

        damageLabel.setText("" + damageSlider.getValue());
    }

    private void updatePhase() {
        Type t = p.getPhase();
        System.out.println(t);
        switch (t) {
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

    public void updateButton() {
        Type t = p.getPhase();
        switch (t) {
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

    private int promptInt() {
        return 0;
    }

    private void handleAlly(int i) {
        Card c = p.getAllies().getCard(i);

        // Do nothing if there is no card there
        if (c == null) {
            return;
        }

        // Else do something based on the phase
        Type phase = p.getPhase();
        Move m;
        switch (phase) {
            case PLAY1:
                break;

            case PLAY2:
                break;

            case DISCARD:
                break;

            case ATTACK:
                m = new AttackMove(p, c);
                if (m.isLegal(r)) {
                    m.execute();
                }
                System.out.println(p.getDamage());
                break;

            case DAMAGE:
                getDamageInput(c);
                break;

            case SACRIFICE:
                m = new SacrificeMove(p, c);
                if (m.isLegal(r)) {
                    m.execute();
                }
                break;

            default:
                break;
        }

        update();
    }

    private void handleHand(int i) {

        //spellStack.setText(p.getHand().toString());
        Card c = p.getHand().getCard(i);
        spellStack.setText(c.toString());


        // Do nothing if there is no card there
        if (c == null) {
            return;
        }

        // Else do something based on the phase
        Type phase = p.getPhase();
        Move m;
        switch (phase) {
            case PLAY1:
                m = new Play1Move(p, c);
                if (m.isLegal(r)) {
                    m.execute();
                }
                break;

            case PLAY2:
                m = new Play2Move(p, c);
                if (m.isLegal(r)) {
                    m.execute();
                }
                break;

            case DISCARD:
                m = new DiscardMove(p, c);
                if (m.isLegal(r)) {
                    m.execute();
                }
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel2 = new javax.swing.JPanel();
        handPanel = new javax.swing.JPanel();
        card6 = new javax.swing.JTextArea();
        card7 = new javax.swing.JTextArea();
        card2 = new javax.swing.JTextArea();
        card5 = new javax.swing.JTextArea();
        card8 = new javax.swing.JTextArea();
        card4 = new javax.swing.JTextArea();
        card1 = new javax.swing.JTextArea();
        card3 = new javax.swing.JTextArea();
        phasePanel = new javax.swing.JPanel();
        phaseLabel = new javax.swing.JLabel();
        phaseButton = new javax.swing.JButton();
        pDetails = new javax.swing.JPanel();
        pHealth = new javax.swing.JPanel();
        neutLife = new javax.swing.JLabel();
        life = new javax.swing.JLabel();
        redLife = new javax.swing.JLabel();
        blueLife = new javax.swing.JLabel();
        greenLife = new javax.swing.JLabel();
        blackLife = new javax.swing.JLabel();
        whiteLife = new javax.swing.JLabel();
        pNameLabel = new javax.swing.JLabel();
        spellStack = new javax.swing.JTextArea();
        damagePanel = new javax.swing.JPanel();
        damageLabel = new javax.swing.JLabel();
        damageButton = new javax.swing.JButton();
        damageSlider = new javax.swing.JSlider();
        oppDetails = new javax.swing.JPanel();
        oppHealth = new javax.swing.JPanel();
        neutLife1 = new javax.swing.JLabel();
        life1 = new javax.swing.JLabel();
        redLife1 = new javax.swing.JLabel();
        blueLife1 = new javax.swing.JLabel();
        greenLife1 = new javax.swing.JLabel();
        blackLife1 = new javax.swing.JLabel();
        whiteLife1 = new javax.swing.JLabel();
        oppNameLabel = new javax.swing.JLabel();
        allyPanel = new javax.swing.JPanel();
        ally2 = new javax.swing.JTextArea();
        ally5 = new javax.swing.JTextArea();
        ally3 = new javax.swing.JTextArea();
        ally4 = new javax.swing.JTextArea();
        ally1 = new MiniPanel();

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

        card2.setColumns(20);
        card2.setRows(5);
        card2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card2MouseClicked(evt);
            }
        });

        card5.setColumns(20);
        card5.setRows(5);
        card5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card5MouseClicked(evt);
            }
        });

        card8.setColumns(20);
        card8.setRows(5);
        card8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card8MouseClicked(evt);
            }
        });

        card4.setColumns(20);
        card4.setRows(5);
        card4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card4MouseClicked(evt);
            }
        });

        card1.setColumns(20);
        card1.setRows(5);
        card1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card1MouseClicked(evt);
            }
        });

        card3.setColumns(20);
        card3.setRows(5);
        card3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card3MouseClicked(evt);
            }
        });

        phaseLabel.setText("Phase");

        phaseButton.setText("Play 1");
        phaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phaseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout phasePanelLayout = new javax.swing.GroupLayout(phasePanel);
        phasePanel.setLayout(phasePanelLayout);
        phasePanelLayout.setHorizontalGroup(
            phasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(phaseLabel)
                .addGap(27, 27, 27)
                .addComponent(phaseButton)
                .addContainerGap())
        );
        phasePanelLayout.setVerticalGroup(
            phasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(phasePanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(phasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phaseLabel)
                    .addComponent(phaseButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout handPanelLayout = new javax.swing.GroupLayout(handPanel);
        handPanel.setLayout(handPanelLayout);
        handPanelLayout.setHorizontalGroup(
            handPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(handPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(handPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(handPanelLayout.createSequentialGroup()
                        .addGroup(handPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(card8, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(card7, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(phasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(card6, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card5, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        handPanelLayout.setVerticalGroup(
            handPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(handPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(handPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(phasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(handPanelLayout.createSequentialGroup()
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
                        .addComponent(card8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        neutLife.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        neutLife.setText("" + p.getLife().get(Color.NEUTRAL));

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

        redLife.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        redLife.setText("" + p.getLife().get(Color.RED));

        blueLife.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blueLife.setText("" + p.getLife().get(Color.BLUE));

        greenLife.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        greenLife.setText("" + p.getLife().get(Color.GREEN));

        blackLife.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blackLife.setText("" + p.getLife().get(Color.BLACK));

        whiteLife.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        whiteLife.setText("" + p.getLife().get(Color.WHITE));

        javax.swing.GroupLayout pHealthLayout = new javax.swing.GroupLayout(pHealth);
        pHealth.setLayout(pHealthLayout);
        pHealthLayout.setHorizontalGroup(
            pHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHealthLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(life, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(neutLife)
                .addGap(6, 6, 6)
                .addComponent(redLife, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenLife, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(blueLife, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(whiteLife, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(blackLife, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pHealthLayout.setVerticalGroup(
            pHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHealthLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(life, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(neutLife, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(redLife, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(greenLife, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blueLife, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(whiteLife, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blackLife, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pHealthLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {blackLife, blueLife, greenLife, life, neutLife, redLife, whiteLife});

        neutLife.setForeground(java.awt.Color.GRAY);
        redLife.setForeground(java.awt.Color.RED);
        blueLife.setForeground(java.awt.Color.BLUE);
        greenLife.setForeground(java.awt.Color.GREEN);
        blackLife.setForeground(java.awt.Color.BLACK);
        whiteLife.setForeground(java.awt.Color.WHITE);

        pNameLabel.setText(p.getName());

        javax.swing.GroupLayout pDetailsLayout = new javax.swing.GroupLayout(pDetails);
        pDetails.setLayout(pDetailsLayout);
        pDetailsLayout.setHorizontalGroup(
            pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDetailsLayout.createSequentialGroup()
                        .addComponent(pHealth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(pDetailsLayout.createSequentialGroup()
                        .addComponent(pNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(434, 434, 434))))
        );
        pDetailsLayout.setVerticalGroup(
            pDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pHealth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        spellStack.setColumns(20);
        spellStack.setRows(5);

        damageLabel.setText("" + damageSlider.getValue());

        damageButton.setText("Deal Damage");
        damageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                damageButtonActionPerformed(evt);
            }
        });

        damageSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                damageSliderStateChanged(evt);
            }
        });
        damageSlider.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                damageSliderPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout damagePanelLayout = new javax.swing.GroupLayout(damagePanel);
        damagePanel.setLayout(damagePanelLayout);
        damagePanelLayout.setHorizontalGroup(
            damagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, damagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(damagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, damagePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
                        .addComponent(damageSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, damagePanelLayout.createSequentialGroup()
                        .addComponent(damageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(damageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        damagePanelLayout.setVerticalGroup(
            damagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(damagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(damageSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addGroup(damagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(damagePanelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(damageLabel))
                    .addComponent(damageButton))
                .addGap(26, 26, 26))
        );

        neutLife1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        neutLife1.setText("" + opp.getLife().get(Color.NEUTRAL));

        life1.setText("Health");
        life1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                life1MouseClicked(evt);
            }
        });
        life1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                life1PropertyChange(evt);
            }
        });

        redLife1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        redLife1.setText("" + opp.getLife().get(Color.RED));

        blueLife1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blueLife1.setText("" + opp.getLife().get(Color.BLUE));

        greenLife1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        greenLife1.setText("" + opp.getLife().get(Color.GREEN));

        blackLife1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blackLife1.setText("" + opp.getLife().get(Color.BLACK));

        whiteLife1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        whiteLife1.setText("" + opp.getLife().get(Color.WHITE));

        javax.swing.GroupLayout oppHealthLayout = new javax.swing.GroupLayout(oppHealth);
        oppHealth.setLayout(oppHealthLayout);
        oppHealthLayout.setHorizontalGroup(
            oppHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(oppHealthLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(life1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(neutLife1)
                .addGap(6, 6, 6)
                .addComponent(redLife1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(greenLife1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(blueLife1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(whiteLife1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(blackLife1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        oppHealthLayout.setVerticalGroup(
            oppHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(oppHealthLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(oppHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(life1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(neutLife1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(redLife1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(greenLife1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blueLife1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(whiteLife1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blackLife1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        oppHealthLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {blackLife1, blueLife1, greenLife1, life1, neutLife1, redLife1, whiteLife1});

        neutLife1.setForeground(java.awt.Color.GRAY);
        redLife1.setForeground(java.awt.Color.RED);
        blueLife1.setForeground(java.awt.Color.BLUE);
        greenLife1.setForeground(java.awt.Color.GREEN);
        blackLife1.setForeground(java.awt.Color.BLACK);
        whiteLife1.setForeground(java.awt.Color.WHITE);

        oppNameLabel.setText(opp.getName());

        javax.swing.GroupLayout oppDetailsLayout = new javax.swing.GroupLayout(oppDetails);
        oppDetails.setLayout(oppDetailsLayout);
        oppDetailsLayout.setHorizontalGroup(
            oppDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(oppDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(oppDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(oppNameLabel)
                    .addComponent(oppHealth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        oppDetailsLayout.setVerticalGroup(
            oppDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(oppDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(oppNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(oppHealth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ally2.setColumns(20);
        ally2.setRows(5);
        ally2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ally2MouseClicked(evt);
            }
        });

        ally5.setColumns(20);
        ally5.setRows(5);
        ally5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ally5MouseClicked(evt);
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

        javax.swing.GroupLayout allyPanelLayout = new javax.swing.GroupLayout(allyPanel);
        allyPanel.setLayout(allyPanelLayout);
        allyPanelLayout.setHorizontalGroup(
            allyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allyPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(ally1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ally2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ally3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ally4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ally5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        allyPanelLayout.setVerticalGroup(
            allyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allyPanelLayout.createSequentialGroup()
                .addGroup(allyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(allyPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(allyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ally2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(allyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(ally4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ally3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ally5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ally1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(pDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(oppDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(damagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(spellStack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(allyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(handPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(237, 237, 237))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oppDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(damagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spellStack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(handPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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
        switch (t) {
            case DRAW:
                Move draw = new DrawMove(p);
                if (draw.isLegal(r)) {
                    draw.execute();
                } else if (isOver()) {
                    winner();
                }

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
                if (p.getHand().size() >= 8) {
                    break;
                }

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
        damageLabel.setVisible(false);
        

        if (selected == null)
        {
            damageSlider.setValue(0);
            return;
        }

        int dmg = damageSlider.getValue();
        Move m = new DamageMove(p, selected, dmg);

        if (m.isLegal(r)) {
            System.out.println("FUCKED UP!!! " + dmg);
            m.execute();
            opp.subDamage(dmg);
        }

        damageSlider.setValue(0);

    }//GEN-LAST:event_damageButtonActionPerformed

    private void damageSliderPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_damageSliderPropertyChange
        
    }//GEN-LAST:event_damageSliderPropertyChange

    private void damageSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_damageSliderStateChanged
        damageLabel.setText("" + damageSlider.getValue());
    }//GEN-LAST:event_damageSliderStateChanged

    private void life1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_life1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_life1MouseClicked

    private void life1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_life1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_life1PropertyChange
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
    private MiniPanel ally1;
    private javax.swing.JTextArea ally2;
    private javax.swing.JTextArea ally3;
    private javax.swing.JTextArea ally4;
    private javax.swing.JTextArea ally5;
    private javax.swing.JPanel allyPanel;
    private javax.swing.JLabel blackLife;
    private javax.swing.JLabel blackLife1;
    private javax.swing.JLabel blueLife;
    private javax.swing.JLabel blueLife1;
    private javax.swing.JTextArea card1;
    private javax.swing.JTextArea card2;
    private javax.swing.JTextArea card3;
    private javax.swing.JTextArea card4;
    private javax.swing.JTextArea card5;
    private javax.swing.JTextArea card6;
    private javax.swing.JTextArea card7;
    private javax.swing.JTextArea card8;
    private javax.swing.JButton damageButton;
    private javax.swing.JLabel damageLabel;
    private javax.swing.JPanel damagePanel;
    private javax.swing.JSlider damageSlider;
    private javax.swing.JLabel greenLife;
    private javax.swing.JLabel greenLife1;
    private javax.swing.JPanel handPanel;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel life;
    private javax.swing.JLabel life1;
    private javax.swing.JLabel neutLife;
    private javax.swing.JLabel neutLife1;
    private javax.swing.JPanel oppDetails;
    private javax.swing.JPanel oppHealth;
    private javax.swing.JLabel oppNameLabel;
    private javax.swing.JPanel pDetails;
    private javax.swing.JPanel pHealth;
    private javax.swing.JLabel pNameLabel;
    private javax.swing.JButton phaseButton;
    private javax.swing.JLabel phaseLabel;
    private javax.swing.JPanel phasePanel;
    private javax.swing.JLabel redLife;
    private javax.swing.JLabel redLife1;
    private javax.swing.JTextArea spellStack;
    private javax.swing.JLabel whiteLife;
    private javax.swing.JLabel whiteLife1;
    // End of variables declaration//GEN-END:variables

    }

