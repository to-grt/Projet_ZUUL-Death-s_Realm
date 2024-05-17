/**
 * Modèle des items du jeu.
 *
 * @author Théo Gueuret
 * @version 2019.04.24
 */
public class Item
{
    private String aDescr;
    private int aPoids;
    private String aName;
    private boolean aIsEatable;
    private String aEffect;
    private int aStrenght;
    private int aDefense;
    private int aMiss;
    
    
    public Item(final String pDescr, final int pPoids, final String pName, final boolean pIsEatable, final String pEffect, final int pStrenght, final int pDefense, final int pMiss)
    {
        this.aDescr = pDescr;
        this.aPoids = pPoids;
        this.aName = pName;
        this.aIsEatable = pIsEatable;
        this.aEffect = pEffect;
        this.aStrenght = pStrenght;
        this.aDefense = pDefense;
        this.aMiss = pMiss;
    }
    
    /**
     * Accesseur de l'attribut aStrenght d'un objet Item.
     * @return un int représentant la force de l'Item.
     */
    public int getStrenght()
    {
        return(this.aStrenght);
    }
    
    /**
     * Accesseur de l'attribut aDefense d'un objet Item.
     * @return un int représentant la défense d'un Item.
     */
    public int getDefense()
    {
        return(this.aDefense);
    }
    
    /**
     * Accesseur de l'attribut aMiss d'un objet Item.
     * @return un int représentant la capacité d'esquive d'un Item.
     */
    public int getMiss()
    {
        return(this.aMiss);
    }
    
    /**
     * Accesseur de l'attribut aDescr d'un objet Item.
     * @return un String contenant la description de l'Item voulu.
     */
    public String getDescr()
    {
        return(this.aDescr);
    }
    
    /**
     * Accesseur de l'attribut aName d'un objet Item.
     * @return un String contenant le nom de l'Item voulu.
     */
    public String getName()
    {
        return(this.aName);
    }
    
    /**
     * Accesseur de l'attribut aPoids d'un objet Item.
     * @return un int symbolisant le poids d'un Item.
     */
    public int getPoids()
    {
        return(this.aPoids);
    }
    
    /**
     * Accesseur de l'attribut aIsEatable d'un objet Item.
     * @return un boolean symbolisant le fat qu'un Item soit mangeable ou non.
     */
    public boolean getEatable()
    {
        return(this.aIsEatable);
    }
    
    /**
     * Accesseur de l'attribut aEffect d'un objet Item.
     * @return un String contenant l'effet d'un Item lorsqu'on le mange.
     */
    public String getEffect()
    {
        return(this.aEffect);
    }
    
    /**
     * Modificateur de l'attribut aDescr d'un objet Item.
     * @param pDescr un String contenant la description de l'Item voulu.
     */
    public void setDescr(final String pDescr)
    {
        this.aDescr = pDescr;
    }
    
    /**
     * Modificateur de l'attribut aName d'un objet Item.
     * @param pName un String contenant le nom de l'Item voulu.
     */
    public void setName(final String pName)
    {
        this.aName = pName;
    }
    
    /**
     * Modificateur de l'attribut aPoids d'un objet Item.
     * @param pPoids un int symbolisant le poids d'un objet.
     */
    public void setPoids(final int pPoids)
    {
        this.aPoids = pPoids;
    }
    
    /**
     * Modificateur de l'attribut aEatable d'un objet Item.
     * @param pEatable un boolean symbolisant le fait qu'un Item soit mangeable ou non.
     */
    public void setEatable(final boolean pEatable)
    {
        this.aIsEatable = pEatable;
    }
    
    /**
     * Modificateur de l'attribut aEffect d'un objet Item.
     * @param pEffect un String contenant l'effet de l'Item lorsqu'on mange celui-ci.
     */
    public void setEffect(final String pEffect)
    {
        this.aEffect = pEffect;
    }
    
    /**
     * Fonction permettant de créer un String avec une description formatée de l'Item.
     * @return un String contenant la description formatée de l'Item.
     */
    public String getItemDescr()
    {
        String vString = new String("");
        vString = ( this.getName() + " which is " + this.aDescr );
        if(this.aPoids == 0) return(vString);
        vString += (", its weight is " + this.aPoids + " kg.");
        return(vString);
    }
}
