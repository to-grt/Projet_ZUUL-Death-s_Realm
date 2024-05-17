import java.util.HashMap;
import java.util.Stack;

/**
 * Cette classe represente le joueur ainsi que ses differentes caractéristiques
 */
public class Player
{
    private int aMaxWeight;
    private int aCurrentWeight;
    private String aName;
    private ItemList aCarriedItems;
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRooms;
    
    /**
     * Constructeur de la classe Player.
     * @param pMaxWeight    un int indiquant la capacité maximale du Player en kg.
     * @param pName         un String contenant le nom du Player.
     * @param pRoom         un Room contenant la Room actuelle du Player.
     */
    public Player(final int pMaxWeight, final String pName, final Room pRoom)
    {
        this.aMaxWeight = pMaxWeight;
        this.aCurrentWeight = 0;
        this.aName = pName;
        this.aCarriedItems = new ItemList();
        this.aCurrentRoom = pRoom;
        this.aPreviousRooms = new Stack<Room>();
    }
    
    /**
     * Accesseur de l'attribut aMaxWeight  de la classe Player.
     * @return un int contenant la capacité maximale du Player.
     */
    public int getMaxWeight() {
        return( this.aMaxWeight );
    }
    
    /**
     * Accesseur de l'attribut aCurrentWeight  de la classe Player.
     * @return un int contenant la charge actuelle du Player.
     */
    public int getCurrentWeight() {
        return( this.aCurrentWeight );
    }
    
    /**
     * Accesseur de l'attribut aName de la classe Player.
     * @return un String contenant le nom du Player.
     */
    public String getName() {
        return( this.aName);
    }
    
    /**
     * Accesseur de l'attribut aCarriedItems de la classe Player.
     * @return une HashMap contenant les Items portés par le Player.
     */
    public HashMap getCarriedItems() {
        return( this.aCarriedItems.getItemList() );
    }

    /**
     * Accesseur de l'attribut aCurrentRoom  de la classe Player.
     * @return une Room étant la Room actuelle du Player.
     */
    public Room getCurrentRoom() {
        return( this.aCurrentRoom );
    }
    
    /**
     * Accesseur de l'attribut aPreviousRoom de la classe Player.
     * @return un Stack contenant les différentes pièces précédement traversées par le Player.
     */
    public Stack getPreviousRoom() {
        return( this.aPreviousRooms );
    }
    
    /**
     * Modificateur de l'attribut aMaxWeight permettant de définir le poids maximum que le Player peut porter.
     * @param pMaxWeight un int représentant la capacité maximale du Player.
     */
    public void setMaxWeight(final int pMaxWeight) {
        this.aMaxWeight = pMaxWeight;
    }
    
    /**
     * Modificateur de l'attribut aCurrenWeight permettant de définir le poids actuelle du Player.
     * @param pCurrentWeight un int représentant la capacité actuelle du Player.
     */
    public void setCurrentWeight(final int pCurrentWeight) {
        this.aCurrentWeight = pCurrentWeight;
    }
    
    /**
     * Modificateur de l'attribut aName permettant de définir le nom du PLayer.
     * @param pName un String contenant le nom du Player.
     */
    public void setName(final String pName) {
        this.aName = pName;
    }
    
    /**
     * Modificateur de l'attribut aCarriedItems permettant de définir les Item portés par le Player.
     * @param pCarriedItems une HashMap de String et d'Items.
     */
    public void setCarriedItems(final HashMap pCarriedItems) {
        this.aCarriedItems.setItemList(pCarriedItems);
    }
    
    /**
     * Permet de d'ajouter une Room dans l'historique des pièces du joueur (aPreviousRoom)
     * @param pRoom une Room qui va être ajoutée dans l'attribut aPreviousRooms.
     */
    public void addRoom(final Room pRoom) {
        this.aPreviousRooms.push(pRoom);
    }
    
    /**
     * Permet de retirer une Room de l'historique des pièces du joueur (aPreviousRoom)
     * @return une Room qui à été retirée de l'attribut aPreviousRooms.
     */
    public Room removeRoom() {
        return( this.aPreviousRooms.pop() );
    }
    
    /**
     * Fonction permettant de prendre un item présent dans la salle.
     * @param pItem un Item pouvant être prit par le Player.
     * @return un String contenant sous un certain format quel Item à été prit.
     */
    public String pickItem(final Item pItem) {
        this.aCarriedItems.addItem(pItem.getName(), pItem); 
        this.setCurrentWeight(this.getCurrentWeight() + pItem.getPoids());
        if( pItem.getName().equals("bag") ) { this.setMaxWeight(this.getMaxWeight() + 20); }
        return("You just picked up a " + pItem.getName() + ". \n");
    }
    
    /**
     * Fonction permettant de lacher un item porté par le joueur.
     * @param pItem un Item pouvant être déposé par le Player.
     * @return un String contenant sous un certain format quel Item à été déposé.
     */
    public String dropItem(final Item pItem) {
        this.aCarriedItems.removeItem(pItem.getName());
        this.setCurrentWeight(this.getCurrentWeight() - pItem.getPoids());
        if( pItem.getName().equals("bag") ) { this.setMaxWeight(this.getMaxWeight() - 20); }
        return("You just dropped a " + pItem.getName() + ".");
    }
    
    /**
     * Fonction permettant de changer la piece actuelle du joueur.
     * @param pDest une Room indiquant quelle sera la prochaine Room.
     */
    public void changeRoom(final Room pDest) {
        this.aCurrentRoom = pDest;
    }
    
    /**
     * Fonction permettant de generer une description des items de la salle.
     * @return un String contenant une description formatée des Item de la Room actuelle.
     */
    public String getItemsDescr()
    {
        return( this.aCurrentRoom.getItemsDescr() );
    }
    
    /**
     * Fonction permettant de generer l'image liée à la salle actuelle.
     * @return un String contenant le nom de l'image associée à la Room actuelle.
     */
    public String getRoomImageName()
    {
        return( this.aCurrentRoom.getImageName() );
    }
    
    /**
     * Fonction permettant de generer la description de la pièce actuelle.
     * @return un String contenant la description formatée de la Room actuelle.
     */
    public String getRoomDescription()
    {
        return( this.aCurrentRoom.getLongDescription() );
    }
    
    /**
     * Fonction permettant de generer la HashMap des Items présents dans la salle.
     * @return une HashMap de String et d'Items contenant les Item présent dans la Room actuelle du Player.
     */
    public HashMap getRoomItems()
    {
        return( this.aCurrentRoom.getItems() );
    }
    
    /**
     * Retourne l'Item voulu si il est présent dans la salle actuelle (sinon null)
     * @param pName un Sring contenant le nom d'un Item (ou non).
     * @return un Item si et seulement si le nom passé en paramètre correspond à un Item de la Room
     * actuelle du Player, sinon renvoit null.
     */
    public Item getItemFromRoom(final String pName)
    {
        if(this.aCurrentRoom.getItem( pName ) == null ) return(null);
        else return( this.aCurrentRoom.getItem( pName ) );
    }
    
    /**
     * Retourne l'Item voulu si il est présent dans l'inventiare du joueur (sinon null)
     * @param pName un Sring contenant le nom d'un Item (ou non).
     * @return un Item si et seulement si le nom passé en paramètre correspond à un Item de la ItemList
     * actuelle du Player, sinon renvoit null.
     */
    public Item getItemFromPlayer(final String pName)
    {
        if(this.aCarriedItems.getItem( pName ) == null ) return(null);
        else return( this.aCarriedItems.getItem( pName ) );
    }
    
    /**
     * Retourne l'Item voulu (si il existe dans les items portés ou dans les items de la salle)
     * @param pName un String contenant le nom de l'objet voulu (ou non).
     * @return un Item si celui-ci existe dans la Room actuelle du Player ou dans l'ItemList du Player, sinon null.
     */
    public Item getItem(final String pName)
    {
        if( !isItemHere( pName ) ) return( null );
        else {
            Item vItem;
            if( getItemFromPlayer( pName ) != null ) vItem = getItemFromPlayer( pName );
            else vItem = getItemFromRoom( pName );
            return(vItem);
        }
    }
    
    /**
     * Enleve l'item d'un Player defintivement du jeu.
     * @param pItem un Item allant être enlevé de l'inventaire d'un Player.
     */
    public void removeItem(final Item pItem)
    {
        this.setCurrentWeight( this.getCurrentWeight() - pItem.getPoids() );
        this.aCarriedItems.removeItem(pItem.getName());
    }
    
    /**
     * Fonction permettant d'obtenir sous forme de String une description de l'inventaire actuel du joueur
     * @return un String contenant une liste formatée des Item de l'ItemList aCarriedItems du Player.
     */
    public String getPlayerInventory()
    {
        String vTotal = new String();
        if( this.aCarriedItems.getTotalString() != "" ) {
            vTotal += "this inventory contains : \n" + this.aCarriedItems.getTotalString() + "\n and the total weight of your inventory is : " + this.aCarriedItems.getTotalWeight() + " / " + this.aMaxWeight + "kg";
        }
        else vTotal = "this inventory is empty ! \n";
        return( vTotal );
    }
    
    /**
     * Fonction permettant de déterminer si un Item est présent dans la salle actuelle ou sur le joueur
     * @param pName un String contenant le nom de l'Item cible.
     * @return un boolean indiquant si l'Item dont le nom est donné en paramètre est dans la Room actuelle du Player
     * ou dans l'ItemList aCarriedItem du Player.
     */
    public boolean isItemHere( final String pName )
    {
        if( getItemFromPlayer( pName ) == null && getItemFromRoom( pName ) == null) return false;
        return true;
    }
    
    /**
     * Fontion permettant de manger l'Item (si possible) passé en parametre
     * @param pName un String contenant le nom l'Item cible de la commande eat().
     * @return un String contenant la réponse de la fonction face à cette demande. Si l'Item n'existe pas, s'il
     * n'est pas mangeable, si il à un effet spécial lorsque le joueur le mange ou lorsque il n'y a pas d'effet spécial.
     */
    public String eatItem( final String pName )
    {
        String vResult = new String();
        
        if( getItemFromPlayer( pName ) != null)
        {
            if( getItemFromPlayer( pName ).getEatable() )
            {
                Item vAteItem = getItemFromPlayer( pName );
                if( vAteItem.getEffect().equals("+5kg"))
                {
                    setMaxWeight( getMaxWeight() + 5 );
                    vResult += "you just gained 5kg on your max weight  \n";
                }
                removeItem(vAteItem);
                return( "You just eated a " + vAteItem.getName() + ", you are not hungry anymore\n" + vResult );
            }
            return( "Humm not sure it is a good idea to eat that" );
        }
        return( "this item is currently not in your inventory or does not exist" );
    }
    
    /**
     * Fonction décrivant la charge du beamer sur le Player
     * @return un String contenant le réponse de la fonction par rapport à la demande du joueur.
     */
    public String chargeBeamer()
    {
        if( getItemFromPlayer("beamer") == null ) { return "You currently don't carry the beamer"; }
        Beamer vBeamer = (Beamer)(getItemFromPlayer("beamer"));
        vBeamer.setIsCharged(true);
        vBeamer.setMemorizedRoom(this.aCurrentRoom);
        return "The beamer has been charged";
    }
    
    /**
     * Fonction décrivant l'utilisation du beamer sur le Player.
     * @return un String contenant la réponse de la focntion par rapport à la demande du joueur.
     */
    public String fireBeamer()
    {
        if( getItemFromPlayer("beamer") == null ) { return "You currently don't carry the beamer"; }
        Beamer vBeamer = (Beamer)(getItemFromPlayer("beamer"));
        if( vBeamer.getIsCharged() == false ) { return "The beamer is not charged ! You can't use it for now"; }
        vBeamer.setIsCharged(false);
        changeRoom( vBeamer.getMemorizedRoom() );
        vBeamer.setMemorizedRoom(null);
        return "It worked, you have been teleported successfully !";
    }
    
    /**
     * Fonction pour le dernier combat du jeu.
     * @return un String contenant le résultat du combat final.
     */
    public String finalFight()
    {
        int vTotalStrenght = 0;
        int vTotalDefense = 0;
        int vTotalMiss = 0;
        for( Item pItem : this.aCarriedItems.getValues() ) {
            vTotalStrenght += pItem.getStrenght();
            vTotalDefense += pItem.getDefense();
            vTotalMiss += pItem.getMiss();
        }
        double vScore = 0.5 * vTotalStrenght + 0.4 * vTotalDefense + 0.1 * vTotalMiss;
        
        if( vScore > 94.0 ) { return(win()); }
        return(lose());
    }
    
    /**
     * Fin du jeu : victoire
     * @return un String contenant le message de victoire.
     */
    public String win() {
        return( "You won ! You just beat Thérée, the half-god. It was an incredible enemy but you were stronger \n" );
    }
    
    /**
     * Fin du jeu : défaite
     * @return un String contenant le message de défaite
     */
    public String lose()
    {
        return( "You lost against Thérée ... You could try the adventure again. You should try to pay more attention to the items you choose.\n You might lack in strenght, defense or escape ability. \n");
    }
}