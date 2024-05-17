
/**
 * Cette classe sert à définir les commandes considérées comme valides.
 * 
 * @author  Michael Kolling and David J. Barnes + D.Bureau + T.Gueuret
 * @version 2008.03.30 + 2013.09.15 + 2019.02.20
 */
public class CommandWords
{
    // tableau constant qui contient tous les mots de commande valides
    private static final String[] sValidCommands = {
        "go", "quit", "help", "look", "eat", "back", "test", "drop", "take", "inventory", "charge", "fire"
    };

    /**
     * Constructeur par defaut de la classe CommandWords
     */
    public CommandWords()
    {
        // rien a faire pour le moment...
    } // CommandWords()

    /**
     * Affiche toutes les commandes valides
     * @return un String contenant la liste des Commandes valides.
     */
    public String getCommandList()
    {
        String vString = new String("");
        for(String command : sValidCommands)
        {
            vString += command + " ";
        }
        return(vString);
    } //showAll()
    
    /**
     * Verifie si une String donnee fait partie des commandes valides. 
     * @param pString la String a tester
     * @return true si pString est une comande valide, false sinon
     */
    public boolean isCommand( final String pString )
    {
        // pour chacune des commandes valides (du tableau constant)
        for ( int i=0; i<sValidCommands.length; i++ ) {
            // si elle est egale a pString
            if ( sValidCommands[i].equals( pString ) )
                return true;
        } // for
        // si nous arrivons la, c'est que la commande
        // n'a pas ete trouvee dans le tableau
        return false;
    } // isCommand()
} // CommandWords