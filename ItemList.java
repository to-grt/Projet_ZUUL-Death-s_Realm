import java.util.Collection;
import java.util.HashMap;

/** 
 * Classe permettant de gerer une collection d'item
 *
 * @author Théo Gueuret
 * @version 10/04/2019
 */
public class ItemList
{
    private HashMap<String, Item> aItemList;

    /**
     * Constructeur de la classe ItemList.
     */
    public ItemList()
    {
        this.aItemList = new HashMap<String, Item>();
    }
    
    /**
     * Accesseur de l'attribut aItemList de la classe ItemList.
     *  @return un HashMap contenant les Items de l'ItemList.
     */
    public HashMap getItemList() 
    {
        return( this.aItemList );
    }
    
    /**
     * Fonction permettant de retourner un Item précis.
     * @param vName un String contenant le nom d'un Item existant.
     * @return un Item tel que le nom de celui-ci est le String passé en paramètre.
     */
    public Item getItem(final String vName) 
    {
        return( this.aItemList.get( vName ) );
    }
    
    /**
     * Modificateur de l'attribut aItemList de la classe ItemList.
     * @param pItemList une HashMap de String et d'Items.
     */
    public void setItemList(final HashMap pItemList)
    {
        this.aItemList = pItemList;
    }
    
    /**
     * Fonction permettant d'ajouter un Item dans une ItemList
     * @param pItemName un String contenant le nom d'un Item.
     * @param pItem     un Item, son nom est le même que le celui contenu dans le String du précedent paramètre.
     */
    public void addItem(final String pItemName, final Item pItem)
    {
        this.aItemList.put(pItemName, pItem);
    }

    /**
     * Fonction permettant de retirer un Item d'une ItemList
     * @param pItemName un String contenant le nom de l'Item voulant être retiré
     */
    public void removeItem(final String pItemName)
    {
        this.aItemList.remove(pItemName);
    }
    
    /**
     * Fonction permettant de retourner une collection de toutes les values de l'attribut aItemList.
     * @return une Collecion d'Item correspondant a toutes les values de l'attribut aItemList. Ceci est possible 
     * grâce au fait que aItemList est une HashMap et que certaines fonctions sont applicables sur celle-ci dont .values().
     */
    public Collection<Item> getValues()
    {
        return( this.aItemList.values() );
    }
    
    /**
     * Fonction permettant de récuperer la liste des items de la ItemList ainsi que leur poids sous forme de String
     * @return un String contenant la liste des Item contenus dans ItemList (via l'attribut aItemList).
     */
    public String getTotalString()
    {
        String vTotal = "";
        Collection<Item> vItems = this.aItemList.values();
        for( Item vItem : vItems ) {
            vTotal += "a " + vItem.getItemDescr() + "\n";
        }
        return( vTotal );
    }
    
    /**
     * Fonction retournant la somme du poids de tous les items de la ItemList.
     * @return un int symbolisant la somme des poids de tous les Items de la ItemList.
     */
    public int getTotalWeight()
    {
        int vResult = 0;
        Collection<Item> vItems = this.aItemList.values();
        for( Item vItem : vItems) {
            vResult += vItem.getPoids();
        }
        return( vResult );
    }
}
