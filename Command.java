/**
 * Classe Command, cette classe gère les informations des objets de type Command, elle permet d'acceder aux attibut des instances
 * de la classe mais elle sert aussi à savoir si c'est une commande valide ou encore si elle comporte 2 mots.
 * 
 * @author Théo Gueuret
 * @version 12/02/2019
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    /**
     * Constructeur de la classe Command
     * @param pCommandWord première partie de la commande.
     * @param pSecondWord  deuxième partie de la commande.
     */
    public Command(final String pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }
    
    /**
     * accesseur de l'attribut aCommandWord
     * @return un String contenant le premier mot de l'objte Command voulue.
     */
    public String getCommandWord()
    {
        return(this.aCommandWord);
    }
    
    /**
     * accesseur de l'attribut aSecondWord.
     * @return un String contenant le deuxième mot de l'objet Command voulue.
     */
    public String getSecondWord()
    {
        return(this.aSecondWord);
    }
    
    /**
     * Fonction permettant de savoir si il y a présence ou non d'un second mot dans la commande.
     * @return un booolean permettant de savoir si le deuxième mot de la Command est nul ou non.
     */
    public boolean hasSecondWord()
    {
        return(this.aSecondWord != null);
    }
    
    /**
     * Fonction permettant de savoir si la commande est connue ou non.
     * @return un boolean permettant de savoir si la Command est connue ou non.
     */
    public boolean isUnknown()
    {
        return(this.aCommandWord == null);
    }
} // Command
