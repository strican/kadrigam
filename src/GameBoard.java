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
        damagePanel.setVisible(true);

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

    public void updateAllies()
    {
        ally1.changeCard(p.getAllies().getCard(0));
        ally2.changeCard(p.getAllies().getCard(1));
        ally3.changeCard(p.getAllies().getCard(2));
        ally4.changeCard(p.getAllies().getCard(3));
        ally5.changeCard(p.getAllies().getCard(4));


        if (ally1.getCard() == null)
            ally1.setVisible(false);
        else
            ally1.setVisible(true);

        if (ally2.getCard() == null)
            ally2.setVisible(false);
        else
            ally2.setVisible(true);

        if (ally3.getCard() == null)
            ally3.setVisible(false);
        else
            ally3.setVisible(true);

        if (ally4.getCard() == null)
            ally4.setVisible(false);
        else
            ally4.setVisible(true);

        if (ally5.getCard() == null)
            ally5.setVisible(false);
        else
            ally5.setVisible(true);
    }

    public void updateHand()
    {
        card1.changeCard(p.getHand().getCard(0));
        card2.changeCard(p.getHand().getCard(1));
        card3.changeCard(p.getHand().getCard(2));
        card4.changeCard(p.getHand().getCard(3));
        card5.changeCard(p.getHand().getCard(4));
        card6.changeCard(p.getHand().getCard(5));
        card7.changeCard(p.getHand().getCard(6));

        if (card1.getCard() == null)
            card1.setVisible(false);
        else
            card1.setVisible(true);

        if (card2.getCard() == null)
            card2.setVisible(false);
        else
            card2.setVisible(true);

        if (card3.getCard() == null)
            card3.setVisible(false);
        else
            card3.setVisible(true);

        if (card4.getCard() == null)
            card4.setVisible(false);
        else
            card4.setVisible(true);

        if (card5.getCard() == null)
            card5.setVisible(false);
        else
            card5.setVisible(true);

        if (card6.getCard() == null)
            card6.setVisible(false);
        else
            card6.setVisible(true);

        if (card7.getCard() == null)
            card7.setVisible(false);
        else
            card7.setVisible(true);
    }

    public void updateOppAllies()
    {
        oppAlly1.changeCard(opp.getAllies().getCard(0));
        oppAlly2.changeCard(opp.getAllies().getCard(1));
        oppAlly3.changeCard(opp.getAllies().getCard(2));
        oppAlly4.changeCard(opp.getAllies().getCard(3));
        oppAlly5.changeCard(opp.getAllies().getCard(4));


        if (oppAlly1.getCard() == null)
            oppAlly1.setVisible(false);
        else
            oppAlly1.setVisible(true);

        if (oppAlly2.getCard() == null)
            oppAlly2.setVisible(false);
        else
            oppAlly2.setVisible(true);

        if (oppAlly3.getCard() == null)
            oppAlly3.setVisible(false);
        else
            oppAlly3.setVisible(true);

        if (oppAlly4.getCard() == null)
            oppAlly4.setVisible(false);
        else
            oppAlly4.setVisible(true);

        if (oppAlly5.getCard() == null)
            oppAlly5.setVisible(false);
        else
            oppAlly5.setVisible(true);

    }

    public void updateHealth()
    {
        PointList health = p.getLife();
        neutLife.setText("" + health.get(Color.NEUTRAL));
        redLife.setText("" + health.get(Color.RED));
        greenLife.setText("" + health.get(Color.GREEN));
        blueLife.setText("" + health.get(Color.BLUE));
        whiteLife.setText("" + health.get(Color.WHITE));
        blackLife.setText("" + health.get(Color.BLACK));

        health = opp.getLife();
        neutLife1.setText("" + health.get(Color.NEUTRAL));
        redLife1.setText("" + health.get(Color.RED));
        greenLife1.setText("" + health.get(Color.GREEN));
        blueLife1.setText("" + health.get(Color.BLUE));
        whiteLife1.setText("" + health.get(Color.WHITE));
        blackLife1.setText("" + health.get(Color.BLACK));


    }

    public void update() {

        updateHealth();
        updateAllies();
        updateHand();
        updateOppAllies();
        
        if (p.getPhase() != Type.DAMAGE)
            damagePanel.setVisible(false);
        

        

        spellStack.setText(p.getSpellStack().toString());

        updateButton();
        updatePhase();

        
    }

    private void updatePhase() {
        Type t = p.getPhase();

        if (t == null)
            t = Type.WAIT;
        
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

        if (t == null)
            t = Type.WAIT;
        
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
        spellStack = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        ally1 = new javax.swing.JTextArea();
        ally2 = new javax.swing.JTextArea();
        ally5 = new javax.swing.JTextArea();
        ally3 = new javax.swing.JTextArea();
        ally4 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        handPanel = new javax.swing.JPanel();
        card1 = new MiniPanel();
        card2 = new MiniPanel();
        card3 = new MiniPanel();
        card4 = new MiniPanel();
        card5 = new MiniPanel();
        card6 = new MiniPanel();
        card7 = new MiniPanel();
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
        phasePanel = new javax.swing.JPanel();
        phaseLabel = new javax.swing.JLabel();
        phaseButton = new javax.swing.JButton();
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
        allAllyPanel = new javax.swing.JPanel();
        oppAllyPanel = new javax.swing.JPanel();
        oppAlly1 = new MiniPanel();
        oppAlly2 = new MiniPanel();
        oppAlly3 = new MiniPanel();
        oppAlly4 = new MiniPanel();
        oppAlly5 = new MiniPanel();
        allyPanel = new javax.swing.JPanel();
        ally1 = new MiniPanel();
        ally2 = new MiniPanel();
        ally3 = new MiniPanel();
        ally4 = new MiniPanel();
        ally5 = new MiniPanel();

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


        handPanel.setMaximumSize(new java.awt.Dimension(1022, 207));

        card1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card1MouseClicked(evt);
            }
        });

        card2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card2MouseClicked(evt);
            }
        });

        card3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card3MouseClicked(evt);
            }
        });

        card4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card4MouseClicked(evt);
            }
        });

        card5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card5MouseClicked(evt);
            }
        });

        card6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card6MouseClicked(evt);
            }
        });

        card7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                card7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout handPanelLayout = new javax.swing.GroupLayout(handPanel);
        handPanel.setLayout(handPanelLayout);
        handPanelLayout.setHorizontalGroup(
            handPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(handPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(card5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(card6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(card7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(handPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
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
                .addGap(40, 40, 40))
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
                        .addComponent(pNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 454, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 520, Short.MAX_VALUE))
                .addContainerGap())
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
            .addGroup(damagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(damagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, damagePanelLayout.createSequentialGroup()
                        .addComponent(damageSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(damagePanelLayout.createSequentialGroup()
                        .addComponent(damageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(damageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97))))
        );
        damagePanelLayout.setVerticalGroup(
            damagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(damagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(damageSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(damagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(damagePanelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(damageLabel))
                    .addComponent(damageButton))
                .addContainerGap())
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
                .addGap(40, 40, 40))
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
                .addGroup(oppDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(oppDetailsLayout.createSequentialGroup()
                        .addComponent(oppNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 454, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(oppHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 520, Short.MAX_VALUE))
                .addContainerGap())
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

        allAllyPanel.setMaximumSize(new java.awt.Dimension(860, 403));

        oppAllyPanel.setMaximumSize(new java.awt.Dimension(840, 187));
        oppAllyPanel.setPreferredSize(new java.awt.Dimension(840, 187));

        oppAlly1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oppAlly1MouseClicked(evt);
            }
        });

        oppAlly2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oppAlly2MouseClicked(evt);
            }
        });

        oppAlly3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oppAlly3MouseClicked(evt);
            }
        });

        oppAlly4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oppAlly4MouseClicked(evt);
            }
        });

        oppAlly5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                oppAlly5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout oppAllyPanelLayout = new javax.swing.GroupLayout(oppAllyPanel);
        oppAllyPanel.setLayout(oppAllyPanelLayout);
        oppAllyPanelLayout.setHorizontalGroup(
            oppAllyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(oppAllyPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(oppAlly1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(oppAlly2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(oppAlly3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(oppAlly4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(oppAlly5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        oppAllyPanelLayout.setVerticalGroup(
            oppAllyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(oppAllyPanelLayout.createSequentialGroup()
                .addGroup(oppAllyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(oppAlly1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oppAlly2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oppAlly3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oppAlly4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oppAlly5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        allyPanel.setMaximumSize(new java.awt.Dimension(840, 187));
        allyPanel.setPreferredSize(new java.awt.Dimension(840, 187));

        ally1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ally1MouseClicked(evt);
            }
        });

        ally2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ally2MouseClicked(evt);
            }
        });

        ally3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ally3MouseClicked(evt);
            }
        });

        ally4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ally4MouseClicked(evt);
            }
        });

        ally5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ally5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout allyPanelLayout = new javax.swing.GroupLayout(allyPanel);
        allyPanel.setLayout(allyPanelLayout);
        allyPanelLayout.setHorizontalGroup(
            allyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allyPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(ally1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(ally2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(ally3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(ally4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(ally5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        allyPanelLayout.setVerticalGroup(
            allyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allyPanelLayout.createSequentialGroup()
                .addGroup(allyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ally1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ally2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ally3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ally4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ally5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout allAllyPanelLayout = new javax.swing.GroupLayout(allAllyPanel);
        allAllyPanel.setLayout(allAllyPanelLayout);
        allAllyPanelLayout.setHorizontalGroup(
            allAllyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allAllyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(allAllyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(allyPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oppAllyPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        allAllyPanelLayout.setVerticalGroup(
            allAllyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allAllyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(oppAllyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(allyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(pDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(oppDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(allAllyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(damagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spellStack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(phasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(handPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(oppDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(spellStack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(damagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(allAllyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(handPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()

                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void phaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phaseButtonActionPerformed
        Type t = p.getPhase();

        if (t == null)
            t = Type.WAIT;

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

    private void damageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_damageButtonActionPerformed
        damagePanel.setVisible(false);
        

        if (selected == null)
        {
            damageSlider.setValue(0);
            return;
        }

        int dmg = damageSlider.getValue();
        Move m = new DamageMove(p, selected, dmg);

        if (m.isLegal(r)) {
            m.execute();
            opp.subDamage(dmg);
        }

        damageSlider.setValue(0);
        update();

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

    private void card1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card1MouseClicked
        handleHand(0);
    }//GEN-LAST:event_card1MouseClicked

    private void card2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_card2MouseClicked
        handleHand(1);
    }//GEN-LAST:event_card2MouseClicked

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

    private void oppAlly1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oppAlly1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_oppAlly1MouseClicked

    private void oppAlly2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oppAlly2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_oppAlly2MouseClicked

    private void oppAlly3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oppAlly3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_oppAlly3MouseClicked

    private void oppAlly4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oppAlly4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_oppAlly4MouseClicked

    private void oppAlly5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_oppAlly5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_oppAlly5MouseClicked
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

    private javax.swing.JPanel allAllyPanel;
    private MiniPanel ally1;
    private MiniPanel ally2;
    private MiniPanel ally3;
    private MiniPanel ally4;
    private MiniPanel ally5;
    private javax.swing.JPanel allyPanel;
    private javax.swing.JLabel blackLife;
    private javax.swing.JLabel blackLife1;
    private javax.swing.JLabel blueLife;
    private javax.swing.JLabel blueLife1;
    private MiniPanel card1;
    private MiniPanel card2;
    private MiniPanel card3;
    private MiniPanel card4;
    private MiniPanel card5;
    private MiniPanel card6;
    private MiniPanel card7;
    private javax.swing.JButton damageButton;
    private javax.swing.JLabel damageLabel;
    private javax.swing.JSlider damageSlider;
    private javax.swing.JLabel greenLife;
    private javax.swing.JLabel greenLife1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel life;
    private javax.swing.JLabel life1;
    private javax.swing.JLabel neutLife;
    private javax.swing.JLabel neutLife1;
    private MiniPanel oppAlly1;
    private MiniPanel oppAlly2;
    private MiniPanel oppAlly3;
    private MiniPanel oppAlly4;
    private MiniPanel oppAlly5;
    private javax.swing.JPanel oppAllyPanel;
    private javax.swing.JPanel oppDetails;
    private javax.swing.JPanel oppHealth;
    private javax.swing.JLabel oppNameLabel;
    private javax.swing.JPanel pDetails;
    private javax.swing.JPanel pHealth;
    private javax.swing.JLabel pNameLabel;
    private javax.swing.JButton phaseButton;
    private javax.swing.JLabel phaseLabel;
    private javax.swing.JLabel redLife;
    private javax.swing.JLabel redLife1;
    private javax.swing.JTextArea spellStack;
    private javax.swing.JLabel whiteLife;
    private javax.swing.JLabel whiteLife1;
    // End of variables declaration//GEN-END:variables

    }

