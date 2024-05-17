/**
 * La classe Beamer est une classe decrivant le fonctionnement de l'Item Beamer.
 * @author Théo GUEURET
 * @version  05/05/2019
 */
public class Beamer extends Item
{
    private Room aMemorizedRoom;
    private boolean aIsCharged;
    
    public Beamer(final String pDescr, final int pPoids, final String pName, final boolean pIsEatable, final String pEffect, final int pStrenght, final int pDefense, final int pMiss)
    {
        super(pDescr, pPoids, pName, pIsEatable, pEffect, pStrenght, pDefense, pMiss);
        this.aMemorizedRoom = null;
        this.aIsCharged = false;
    }
    
    /**
     * Accesseur de l'attribut aMemorizedRoom.
     * @return un Room : la Room qui à été mémorisée dans le Beamer.
     */
    public Room getMemorizedRoom() {
        return this.aMemorizedRoom;
    }
    
    /**
     * Accesseur de l'attribut aIsCharged.
     * @return un boolean décrivant si le Beamer à été chargé ou non.
     */
    public boolean getIsCharged() {
        return this.aIsCharged;
    }
    
    /**
     * Modificateur de l'attribut aMemorizedRoom
     * @param pMemorizedRoom une Room étant celle qui va être mémorisée par le Beamer.
     */
    public void setMemorizedRoom(final Room pMemorizedRoom) {
        this.aMemorizedRoom = pMemorizedRoom;
    }
    
    /**
     * Modificateur de l'attribut aIsCharged
     * @param pIsCharged un boolean devenant la nouvelle valeur de l'attribut aIsCharged
     */
    public void setIsCharged(final boolean pIsCharged) {
        this.aIsCharged = pIsCharged;
    }
    
    /**
     * Fonction représentant l'action de charger un Beamer.
     * @param pRoom une Room : celle qui va être mémorisée par le Beamer.
     */
    public void charge(final Room pRoom) 
    {
        setIsCharged(true);
        setMemorizedRoom(pRoom);
    }
    
    /**
     * Fonction représentant l'action d'utiliser un Beamer.
     * @return une Room : la Room dans laquelle le PLayer ayant activé le Beamer va être déplacé.
     */
    public Room fire()
    {
        setIsCharged(false);
        return getMemorizedRoom();
    }
}