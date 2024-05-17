import java.util.StringTokenizer;

/**
 * Cette classe sert à interpreter les mots rentrez dans la console en tant que commande de deux mots.
 * Cette classe permet notamment de lire sur la console et de renvoyer (par le biais de la 
 * méthode getCommand() ) un objet de type Command si les deux mots sont reconnus en tant que commande.
 * 
 * @author  Michael Kolling and David J. Barnes + D.Bureau + T.Gueuret
 * @version 2008.03.30 + 2013.09.15 + 2019.02.20
 */
public class Parser 
{
    private CommandWords aValidCommands;  // (voir la classe CommandWords)
    
    /**
     * Constructeur par defaut qui cree les 2 objets prevus pour les attributs
     */
    public Parser() 
    {
        this.aValidCommands = new CommandWords();
    } // Parser()

    /**
     * Affiche la liste des commandes valide.
     * @return un String contenant la liste des commandes valdies.
     */
    public String showCommands()
    {
        return(aValidCommands.getCommandList());
    } //showCommands()
    
    /**
     * Fonction permettant de créer un objet Command si la ligne entrée par l'utilisateur est une commande valide, sinon renvoie une commande avec un premier attribut aCommand nul.
     * @param pInputLine un String contenant la ligne entrée dans la barre de saisie.
     * @return La prochaine commande de l'utilisateur.
     */
    public Command getCommand( final String pInputLine ) 
    {
        String vWord1 = null;
        String vWord2 = null;

        StringTokenizer vTokenizer = new StringTokenizer( pInputLine );

        if ( vTokenizer.hasMoreTokens() )
            vWord1 = vTokenizer.nextToken();      // get first word
        else
            vWord1 = null;

        if ( vTokenizer.hasMoreTokens() )
            vWord2 = vTokenizer.nextToken();      // get second word
        else
            vWord2 = null;

        // note: we just ignore the rest of the input line.

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).

        if ( this.aValidCommands.isCommand( vWord1 ) )
            return new Command( vWord1, vWord2 );
        else
            return new Command( null, vWord2 );
    } // getCommand()
} // Parser