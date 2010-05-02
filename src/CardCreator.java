/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aidandaly
 */
import java.io.*;

public class CardCreator {
    public final int INITIALCOST = 800;
    public int HANDMODBASE = 6;
    public int DAMAGEBASE = 8;
    public int DESTROYBASE = 12;
    public int TAPBASE = 4;
    public int HEALBASE = 9;



    protected String cardName;
    protected int numCostColors;
    protected int numPOColors;
    protected int convertedCost;
    protected int convertedPO;

    protected int neutralCost;
    protected int redCost;
    protected int blueCost;
    protected int greenCost;
    protected int whiteCost;
    protected int blackCost;

    protected int neutralPO;
    protected int redPO;
    protected int bluePO;
    protected int greenPO;
    protected int whitePO;
    protected int blackPO;

    protected File image;

    protected int power;
    protected int hp;
    protected boolean isCreature;
    protected Trigger trig;
    protected EffectType eff;
    protected int mag;

    protected int cardVal;


    public CardCreator()
    {
        convertedCost = INITIALCOST;
        numCostColors = 0;
        cardVal = cardValue();
    }

    /**
     * Get the value of cardName
     *
     * @return the value of cardName
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * Set the value of cardName
     *
     * @param cardName new value of cardName
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void addCostColor() { numCostColors++; }
    public void removeCostColor() { numCostColors--; }
    public void modifyCost(int x) { convertedCost += x; }

    public void setNeutralCost(int x) { neutralCost = x; }
    public void setRedCost(int x) { redCost = x; }
    public void setGreenCost(int x) { greenCost = x; }
    public void setBlueCost(int x) { blueCost = x; }
    public void setWhiteCost(int x) { whiteCost = x; }
    public void setBlackCost(int x) { blackCost = x; }
    public void setRedPO(int x) { redPO = x;}
    public void setGreenPO(int x) { greenPO = x;}
    public void setBluePO(int x) { bluePO = x;}
    public void setWhitePO(int x) { whitePO = x;}
    public void setBlackPO(int x) { blackPO = x;}

    public void setConvertedCost(int x) {convertedCost = x;}
    public void setConvertedPO(int x) {convertedPO = x;}
    public void setNumCostColors(int x) {numCostColors = x;}
    public void setNumPOColors(int x) {numPOColors = x;}

    public void setCreatureStatus(boolean b) { isCreature = b; }
    public void setPower(int x) { power = x; }
    public void setHP(int x) { hp = x; }

    public void setTrigger(Trigger t) { trig = t; }
    public void setEffectType(EffectType et) { eff = et; }
    public void setEffectMagnitude(int x) { mag = x; }

    public void setImage(File f) { image = f; }
    public String getImageName() { return image.getName(); }

    public Card makeCard()
    {
        Card c;
        ColorPoints cost = new ColorPoints(neutralCost,redCost,greenCost,
                blueCost,whiteCost,blackCost);
        ColorPoints po = new ColorPoints(0,redPO,greenPO,bluePO,
                whitePO,blackPO);
        Effect e = new Effect(mag,eff);
        if (isCreature)
        {
            Ability a = null;
            if (trig != null)
                a = new Ability(trig,e);
            c = new Creature(cardName,power,hp,a,cost,po);
        }
        else
        {
            c = new Spell(cardName,e,cost);
        }
        c.setCV(cardValue());
        return c;
    }

    public int cardValue() {
        int ei;

        cardVal = 0;
        if (isCreature)
        {
            //System.out.println("Is a creature");
            cardVal += (power + hp) / 1000;

            if (trig != null)
            {
                //System.out.println("Has a trigger");
                ei = effectIndex();
                if (trig == Trigger.PAY)
                    cardVal += (convertedCost/500) / ei;
                if (trig == Trigger.DISCARD)
                    cardVal += 4 /ei;
                if (trig == Trigger.NOATTACK)
                    cardVal += 6 /ei;
                if (trig == Trigger.SACRIFICE)
                    cardVal += 2/ei;
                cardVal += ei;
            }
            cardVal += (convertedPO/((numPOColors+1)*200));
            cardVal += (convertedCost/((numCostColors+1)*200));

        }
        else
        {
            ei = effectIndex();
            cardVal += ei;
            cardVal *= 2;
            cardVal += (convertedCost/((numCostColors+1)*100));
        }

        return cardVal;
    }

   private int effectIndex()
   {
       int ei = 0;
       if (eff == EffectType.DRAW || eff == EffectType.DISCARD)
            {
                ei += HANDMODBASE;
                ei += mag;
            }
       if (eff == EffectType.DEACTIVATE)
            {
                ei += TAPBASE;
                ei += mag;          
            }
      if (eff == EffectType.DESTROY)
            {
                ei += DESTROYBASE;
                ei += mag * 8;
            }
      if (eff == EffectType.HEAL)
            {
                ei += HEALBASE;
                ei += mag/100;
            }
       return ei;
   }



}
