public class DrawMove implements Move
{
     /* This allows Rules to see the information for each type of move
      * but still protects the from modification */
    private final Player p;

    DrawMove(Player p)
    {
            this.p = p;
    }

    public boolean isLegal(Rules r)
    {
            return r.check(Type.DRAW, this);
    }

    public void execute()
    {
            p.drawCard();
            for (int i=0; i<p.getAllies().size(); i++)
                ((Creature) p.getAllies().getCard(i)).setActive(true);
    }


    public Card getCard()
    {
        return null;
    }

    public Player getPlayer()
    {
        return p;
    }

}
