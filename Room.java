import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Classe Room, gère les pièces du jeu, leurs data et le traitement de celle-ci
 * 
 * @author Théo Gueuret
 * @version 12/02/2019
 */
public class Room
{
    private String aDescription;
    private HashMap<String, Room> aExits;
    private String aImageName;
    private ItemList aItems;
    
    public Room(final String pDescription, final String pImage)
    {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
        this.aImageName = pImage;
        this.aItems = new ItemList();
    }
    
    /**
     * Ajoute un Item dans l'attribut aItems étant un ItemList. Cette fonction permet donc d'ajouter un Item dans la
     * Room voulue.
     * @param pItem un Item valide qui va être ajouté dans la Room voulue en agrandissant l'attriut aItems.
     */
    public void addItem(final Item pItem)
    {
        aItems.addItem(pItem.getName(), pItem);
    }
    
    /**
     * Retire un Item dans l'attribut aItems étant un ItemList. Cette fonction permet donc de retirer un Item de la
     * Room voulue.
     * @param pItem un Item valide qui va être retiré dans la Room voulue en réduisant l'attribut aItems.
     */
    public void removeItem(final Item pItem)
    {
        aItems.removeItem(pItem.getName());
    }
    
    /**
     * Ajoute une sortie à une pièce
     * @param pDirection un String contenant la direction voulue.
     * @param pNeighboor une Room, désigne la pièce affectée à la direction
     */
    public void setExit(final String pDirection, final Room pNeighboor)
    {
        aExits.put(pDirection, pNeighboor);
    } //setExit()
    
    /**
     * Retourne un String contenant la description de la pièce souhaitée.
     * 
     * @return this.aDescription
     */
    public String getDescription()
    {
        return(this.aDescription + "\n");
    } //getDescription()
    
    /**
     * Retourne les Item liés à la room.
     * 
     * @return une HashMap de String et d'Items des Item situés dans la Room.
     */
    public HashMap getItems()
    {
        return(this.aItems.getItemList());
    }
    
    /**
     * Retourne l'Item voulu
     * @param vName un String contenant le nom d'un Item.
     * @return un Item tel que son nom est le paramêtre de la fonction.
     */
    public Item getItem(final String vName)
    {
        return( this.aItems.getItem( vName ) );
    }
    
    /**
     * Retourne une pièce (Room) située dans la direction voulue par rapport à la pièce actuelle
     * @param pDirection String indiquant la direction
     * @param pRooms une Collection de Room : les Room de la salle.
     * @param pSize un int contenant le nombre de Rooms à selectionner.
     * @return aExits.get(pDirection)
     */
    public Room getExit(final String pDirection, final Collection<Room> pRooms, final int pSize)
    {
        if( this.aImageName.equals("Images/Trap.jpg") ) {
            return(getRandomRoom(pRooms, pSize));
        }
        return( aExits.get(pDirection) );
    } //getExit
    
    /**
     * Retourne une pièce aléatoire.
     * @param pRooms une Collection de Room : les Rooms pouvant etre selectionnées aléatoirement.
     * @param pSize un Int contenant la taille de la liste des Room selectionnables. 
     * @return une Room aléatoire parmis celle disponible(toute sauf la Room finale et elle meme)
     */
    public Room getRandomRoom(final Collection<Room> pRooms, final int pSize) {
        Random randomizer = new Random();
        Iterator<Room> vListRoom = pRooms.iterator();
        int vCompteur = 0;
        int vMax = randomizer.nextInt(pSize);
        Room vResult = new Room("erreur", "erreur");
        while( vCompteur <= vMax ) {
            vResult = vListRoom.next();
            vCompteur++;
        }
        //System.out.println(vResult.
        if( vResult.getImageName().equals("Final.jpg") || vResult.getImageName().equals("Trap;jpg") ) { return( getRandomRoom(pRooms, pSize) );}
        return vResult;
    }
    
    /**
     * Retourne un String indiquant toute les sortie possible d'une pièce spécifique
     * @return un String contenant les directions de sorties existantes.
     */
    public String getExitString()
    {
        String vString = new String("Exits :");
        Set<String> keys = aExits.keySet();
        for( String exit : keys)
        {
            vString += " " + exit;
        }
        return( vString );
    } //getExitString()

    /**
     * Retourne une description des items de la salle
     * @return un String contenant la description formatée des Item de la Room.
     */
    public String getItemsDescr()
    {
        String vString = new String();
        for(Item vItem : this.aItems.getValues()) vString += "this " + vItem.getItemDescr() + "\n";
        return(vString);
    }
        
    /**
     * Retourne une longue description de la pièce voulue.
     * @return un String une description formatée de la Room.
     */
    public String getLongDescription()
    {
        return("You are " + aDescription + ".\n" + getExitString() + "\n");
    } //getLongDescription()

    /**
     * Retourne un String decrivant le nom de l'image de la piece
     * @return un String contenant le nom de l'image adpté à la pièce.
     */
    public String getImageName()
    {
        return(this.aImageName);
    }
    
    /**
     * Retourne un boolean permettant de savoir si la pièce passée en paramètre fait partie des sorties de la Room actuelle
     * @param pRoom, une Room, on veut savoir si cettte Room fait partie des sorties de la salle actuelle.
     * @return un boolean permettant de savoir si cette Room est une exit de la salle actuelle ou non .
     */
    public boolean isExit(final Room pRoom)
    {
        Collection<Room> vRooms = this.aExits.values();
        for( Room vRoom : vRooms ) {
            if( vRoom == pRoom ) {
                return true;
            }
        }
        return false;
    }
} // Room
