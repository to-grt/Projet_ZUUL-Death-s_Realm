import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;

/**
 *  Classe s'occupant de la partie graphique du jeu (programmation est desormais evenementielle)
 * 
 * @author  Gueuret Théo
 * @version 19/03/2019
 */
public class GameEngine
{
    private Parser aParser;
    private Player aPlayer;
    private UserInterface aGui;
    private final HashMap<String, Room> aRooms;
    private int aTimeLeft;

    /**
     * Constructeur de la classe GameEngine.
     */
    public GameEngine()
    {
        this.aParser = new Parser();
        this.aRooms = new HashMap();
        this.aTimeLeft = 50;
        createRooms();
    }

    /**
     * Fonction retournant le player (accesseur)
     * @return un Player.
     */
    final Player getPlayer()
    {
        return( this.aPlayer );
    }
    
    /**
     * Modificateur de l'attribut aGui de la classe GameEngine.
     * @param userInterface un UserInterface permettant de définir l'attribut aGui de la classe GameEngine.
     */
    public void setGUI(final UserInterface userInterface)
    {
        this.aGui = userInterface;
        printWelcome();
    }

    /**
     * Affiche le message de bienvenue lorsque le joueur ouvre le jeu.
     */
    private void printWelcome()
    {
        this.aGui.println("\n Welcome to the Hades' Temple!");
        this.aGui.println("A very dangerous place, take care of yourself");
        this.aGui.println("You feel a strange and hazardous aura on you skin, you must take care");
        this.aGui.println("Type 'help' if you need help.\n");
        printLocationInfo();
        if(this.aPlayer.getRoomImageName() != null) this.aGui.showImage(this.aPlayer.getRoomImageName());
    }

    /**
     * Créer les différentes pièces du jeu et leurs positions par rapport aux autres.
     */
    private void createRooms()
    {
        //Items' creation
        Item vSword = new Item("a typical Spartian's sword", 7, "sword", false, null, 50, 5, 15);
        Item vBag = new Item("a solid bag", 0, "bag", false, null, 0, 0, 3);
        Item vShield = new Item("a strong Spartian' shield", 10, "shield", false, null, 5, 60, 30);
        Item vArmor = new Item("a valuable armor", 23, "armor", false, null, 0, 120, 0);
        Item vBow = new Item("a strange occidental bow", 5, "bow", false, null, 30, 1, 5);
        Item vApple = new Item("a weird purple apple", 1, "apple", true, "+5kg", 0, 0, 1);
        Item vSpear = new Item("a typical Spartian's Spear", 10, "spear", false, null, 70, 40, 10);
        Beamer vBeamer = new Beamer("a large blue wood stick", 10, "beamer", false, null, 0, 0, 5);
        
        Item vMagicCookie = new Item("a large brown cookie with some little chocolate chip en it", 0, "cookie", true, "+5kg", 0, 0, 5);
        
        Item vBook = new Item("a weird looking book", 0, "book", false, null, 0, 0, 1);
       
        
        //Rooms' description.
        
        Room vTrappedRoom = new Room("in front of a wall, you can smell a lot of magic in the air", "Images/Trap.jpg");
        aRooms.put("Trapped Room", vTrappedRoom);
                
        Room vSwordRoom = new Room("in a room where you can find a sword", "Images/Sword.jpg");
        aRooms.put("Sword's Room", vSwordRoom);
        vSwordRoom.addItem(vSword);
        
        Room vArmorRoom  = new Room("in a room where you can find an armor", "Images/Armor.jpg");
        aRooms.put("Armor's Room", vArmorRoom);
        vArmorRoom.addItem(vArmor);
        
        Room vAppleRoom  = new Room("in a room where you can find an apple", "Images/Apple.jpg");
        aRooms.put("Apple's Room", vAppleRoom);
        vAppleRoom.addItem(vApple);
        
        Room vMain = new Room("in the main entrance of the temple.", "Images/Main.jpg");
        aRooms.put("Main Room", vMain);
        vMain.addItem(vBook);
        
        Room vShieldRoom  = new Room("in a room where you can find a shield", "Images/Shield.jpg");
        aRooms.put("Shield's Room", vShieldRoom);
        vShieldRoom.addItem(vShield);
        
        Room vBowRoom = new Room("in a room where you can find a bow", "Images/Bow.jpg");
        aRooms.put("Bow's Room", vBowRoom);
        vBowRoom.addItem(vBow);
        
        Room vBagRoom = new Room("in a room where you can find a bag", "Images/Bag.jpg");
        aRooms.put("Bag's Room", vBagRoom);
        vBagRoom.addItem(vBag);
        
        Room vSpearRoom = new Room("in a room where you can find a spear", "Images/Spear.jpg");
        aRooms.put("Spear's Room", vSpearRoom);
        vSpearRoom.addItem(vSpear);
        
        Room vFinalRoom = new Room("in a room with a strange throne, \nin front of you stands Hades, he stares you for a long minute and says :\n'Such a courageous young man, I have not had so much fun for decades\n I owe you something, go ahead and I'll transport you to your worst enemy'\n", "Images/Throne.jpg");
        aRooms.put("Final Room", vFinalRoom);
        
        Room vBattleRoom = new Room("in a room for the final fight", "Images/Final.jpg");
        aRooms.put("Battle Room", vBattleRoom);
        
        Room vLibrary = new Room("in a large library", "Images/Library.jpg");
        aRooms.put("Library", vLibrary);
        vLibrary.addItem(vMagicCookie);
        vLibrary.addItem(vBeamer);
        
        this.aPlayer = new Player(20, "Main Player", vMain);
        
        //TrappedRoom
        vTrappedRoom.setExit("south", vLibrary);
        //SwordRoom
        vSwordRoom.setExit("north", vArmorRoom);
        vSwordRoom.setExit("east", vMain);
        vSwordRoom.setExit("secret", vFinalRoom);
        //ArmorRoom
        vArmorRoom.setExit("south", vSwordRoom);
        vArmorRoom.setExit("east", vShieldRoom);
        //AppleRoom
        vAppleRoom.setExit("east", vBowRoom);
        vAppleRoom.setExit("secret", vBagRoom);
        //Main
        vMain.setExit("north", vShieldRoom);
        vMain.setExit("west", vSwordRoom);
        vMain.setExit("up", vLibrary);
        //ShieldRoom
        vShieldRoom.setExit("north", vBowRoom);
        vShieldRoom.setExit("south", vMain);
        vShieldRoom.setExit("west", vArmorRoom);
        //BowRoom
        vBowRoom.setExit("south", vShieldRoom);
        vBowRoom.setExit("west", vAppleRoom);
        //BagRoom
        vBagRoom.setExit("north", vSpearRoom);
        vBagRoom.setExit("secret", vAppleRoom);
        //SpearRoom
        vSpearRoom.setExit("south", vBagRoom);
        //FinalRoom
        vFinalRoom.setExit("north", vBattleRoom);
        vFinalRoom.setExit("secret", vSwordRoom);
        //Library
        vLibrary.setExit("down", vMain);
        vLibrary.setExit("north", vTrappedRoom);
    } //createRooms()
    
    /**
     * Execute la fonction adaptée à la commande passée en paramètre, si la commande n'existe pas un message d'erreur est affiché
     * @param pCommandLine un String contenant la commande entrée par l'utilisateur.
     */
    public void interpretCommand(String pCommandLine) 
    {
        this.aGui.println("");
        this.aGui.println(pCommandLine);
        Command vCommand = this.aParser.getCommand(pCommandLine);

        if(vCommand.isUnknown()) {
            this.aGui.println("I don't know what you mean...");
            return;
        }
        String vCommandWord = vCommand.getCommandWord();
        if (vCommandWord.equals("help")) {
            printHelp();
        }
        else if (vCommandWord.equals("go")) {
            goRoom(vCommand);
        }
        else if (vCommandWord.equals("quit")) {
            if(vCommand.hasSecondWord()) {
                this.aGui.println("Quit what?" );
                return;
            }
            else {
                endGame();
            }
        }
        else if(vCommandWord.equals("look")) {
            look(vCommand);
        }
        else if(vCommandWord.equals("eat")) {
            eat(vCommand);
        }
        else if(vCommandWord.equals("back")) {
            back(vCommand);
        }
        else if(vCommandWord.equals("test")) {
            if( !vCommand.hasSecondWord() ) {
                this.aGui.println("test what ?");
                return;
            }
            test( vCommand.getSecondWord() );
        }
        else if(vCommandWord.equals("take")) {
            take(vCommand);
        }
        else if(vCommandWord.equals("drop")) {
            drop(vCommand);
        }
        else if(vCommandWord.equals("inventory")) {
            inventory(vCommand);
        }
        else if(vCommandWord.equals("charge")) {
            charge(vCommand);
        }
        else if(vCommandWord.equals("fire")) {
            fire(vCommand);
        }
        else {
            endGame();
            return;
        }
        this.aTimeLeft--;
        if( this.aTimeLeft == 25 ) {
            this.aGui.println("The death's aura of the temple is biting you gradually, you must take care !");
        }
        if( this.aTimeLeft == 0 ) {
            this.aGui.println("This was your last move \nThe death's aura of the Temple managed to slay you down, you are dead.");
            endGame();        
            return;
        }
    }

    /**
     * Permet de lire les commandes d'un fichier texte.
     * @param pString un String contenant le nom du fichier à executé sans le '.txt' .
     */
    public void test(String pString) {
        Scanner vSc;
        try {
            vSc = new Scanner ( new File( "essais/" + pString + ".txt" ) );
            while ( vSc.hasNextLine() ) {
                String vCommand = vSc.nextLine();
                interpretCommand(vCommand);
            }
        } //try
        catch( final FileNotFoundException pE ) {
            this.aGui.println(" This file doesn't exist ! \n");
            return;
        }
        
    }
    
    /**
     * retourne un String contenant les Item de la salle actuelle
     * @return un String contenant les Items de la salle.
     */
    public String getItemsInfo()
    {
        String vResult = "";
        vResult = ( "In this room you can find : \n");
        if( !this.aPlayer.getRoomItems().isEmpty() ) {
            Collection<Item> vItems = this.aPlayer.getRoomItems().values();
            for(Item vItem : vItems) {
                vResult += ( "  a " + vItem.getName() );
            }
            vResult += (".");
        }
        else vResult += ("nothing \n");
        return vResult;
    }
    
    /**
     * Retourne un String sur la description de la salle actuelle.
     * @return un String contenant les informations sur la Room et les Items actuels.
     */
    public String getLocationInfo()
    {
        String vResult = "";
        vResult += this.aPlayer.getRoomDescription() + "\n";
        vResult += getItemsInfo();
        vResult += ("\n");
        return vResult;
    }
    
    /**
     * Affiche une description precise de la piece actuelle et retourne un String pour aGui.
     */
    private void printLocationInfo()
    {
        this.aGui.println(getLocationInfo());
    }
    
    /**
     * Affiche le message lorsque le joueur tape la commande 'help';
     */
    private void printHelp() 
    {
        this.aGui.println("You are lost. You are alone.");
        this.aGui.println("You wander around at the temple.");
        this.aGui.println("Your command words are: " + this.aParser.showCommands() + "\n");
    }

    /**
     * Reviens à la salle precedente
     * @param pCommand une Command permettant de vérifiée si les conditions pour revenir en arrière sont bien respectées.
     */
    private void back(Command pCommand)
    {
        if( pCommand.hasSecondWord() ) {
            this.aGui.println("You can't do that ! 'back' is a single word command \n");
            return;
        }
        if( this.aPlayer.getPreviousRoom().empty() ) {
            this.aTimeLeft++;
            this.aGui.println("Vous etes deja revenu au debut de votre aventure \n");
            return;
        }
        Room vRoom = this.aPlayer.removeRoom();
        if( this.aPlayer.getCurrentRoom().isExit(vRoom)) {
            this.aPlayer.changeRoom( vRoom );
            printLocationInfo();
            if(this.aPlayer.getRoomImageName() != null) this.aGui.showImage(this.aPlayer.getRoomImageName());
        }
        else {
            this.aGui.println("It was a trapped door ! \n Sorry you can't go back there !");
            this.aPlayer.addRoom(vRoom);
        }
        
    }
    
    /**
     * Permet d'afficher l'inventaire du joueur voulu
     * @param pCommand une Command permettant de vérifiée si les conditions pour afficher l'inventaire sont bien respectées.
     */
    private void inventory(Command pCommand)
    {
        if( pCommand.hasSecondWord() ) {
            this.aGui.println("You can't do that ! 'inventory' is a single word command \n");
            return;
        }
        this.aGui.println(this.aPlayer.getPlayerInventory());
    }
        
    /**
     * Fonction representant l'action de manger.
     * @param pCommand une Command permettant de vérifiée si les conditions pour manger un Item sont bien respectées.
     */
    private void eat(Command pCommand)
    {
        if( !pCommand.hasSecondWord() ) {
            this.aGui.println("What would you like to eat ? \n");
            return;
        }
        
        String vWantedItem = pCommand.getSecondWord();
        
        this.aGui.println( this.aPlayer.eatItem(vWantedItem) );
    } //eat()
    
    /**
     * Fonction permettant d'afficher les descriptions des items de la salle
     */
    private void printItemsDescr()
    {
        this.aGui.println(this.aPlayer.getItemsDescr());
    }
    
    /**
     * Fonction reprensentant l'action de regarder, afficher l'aide ou encore d'afficher la description d'un objet.
     * @param pCommand une Command permettant de réagir différement selon l'instruction entrée par le joueur.
     */
    private void look(Command pCommand)
    {
        if(!pCommand.hasSecondWord()) {
            this.printLocationInfo();
            this.printItemsDescr();
            return;
        }
        
        String vWantedItem = pCommand.getSecondWord();
        
        if( this.aPlayer.getItem( vWantedItem ) == null )
        {
            this.aGui.println("Cet item n'est pas dans cette salle ou n'existe pas ! \n");
        }
        else this.aGui.println( "this item, " + this.aPlayer.getItem(vWantedItem).getItemDescr() + "\n");
    } //look()
    
    /**
     * Fonction permettant de prendre un item d'une salle si cela est possible
     * @param pCommand une Command permettant de vérifier si toutes les actions sont vérifiées avant de prendre un Item de la Room du joueur.
     */
    public void take(final Command pCommand) {
        if(!pCommand.hasSecondWord()) 
        {
            this.aGui.println("Take what ? \n");
            return;
        }
        
        String vWantedItem = pCommand.getSecondWord();
        
        if(this.aPlayer.getItemFromRoom( vWantedItem ) != null)
        {
            Item vItem = (Item)(this.aPlayer.getItemFromRoom( vWantedItem ));
            if( (vItem.getPoids() + this.aPlayer.getCurrentWeight()) <= this.aPlayer.getMaxWeight())
            {
                this.aGui.println( this.aPlayer.pickItem(vItem) );
                this.aPlayer.getCurrentRoom().removeItem(vItem);
            }
            else this.aGui.println("Too heavy for you ! You can't pick that up. \n");
        }
        else this.aGui.println("This item : '" + vWantedItem + "' does not exist or is not in this room. \n");
        this.aGui.update();
    }
    
    /**
     * Fonction permettant de laisser un item dans une salle.
     * @param pCommand une Command permettant de vérifier si toutes les actions sont vérifiées avant de déposer un Item de la Room du joueur.
     */
    public void drop(final Command pCommand) {
        if(!pCommand.hasSecondWord()) 
        {
            this.aGui.println("Drop what ? \n");
            return;
        }
        String vWantedItem = pCommand.getSecondWord();
        if(this.aPlayer.getCarriedItems().get(vWantedItem) != null)
        {
            Item vItem = (Item)(this.aPlayer.getCarriedItems().get(vWantedItem));
            this.aGui.println( this.aPlayer.dropItem(vItem) );
            this.aPlayer.getCurrentRoom().addItem(vItem);
        }
        else this.aGui.println("This item : '" + vWantedItem + "' does not exist or is not on you.");
        this.aGui.update();
        
    }
    
    /**
     * Fonction permettant à un Player de charger un Beamer
     * @param pCommand une Command permettant de vérifier si toutes les actions sont vérifiées avant de déposer un Item de la Room du joueur.
     */
    public void charge(final Command pCommand) {
        if(pCommand.hasSecondWord()) 
        {
            this.aGui.println("You can't do that ! 'charge' is a single word command \n");
            return;
        }
        this.aGui.println(this.aPlayer.chargeBeamer());
    }
    
    /**
     * Fonction permettant à un Player de charger un Beamer
     * @param pCommand une Command permettant de vérifier si toutes les actions sont vérifiées avant de déposer un Item de la Room du joueur.
     */
    public void fire(final Command pCommand) {
        if(pCommand.hasSecondWord()) 
        {
            this.aGui.println("You can't do that ! 'fire' is a single word command \n");
            return;
        }
        this.aGui.println(this.aPlayer.fireBeamer());
        
        if(this.aPlayer.getRoomImageName() != null) this.aGui.showImage(this.aPlayer.getRoomImageName());
    }
    
    /**
     * Selecteur qui permet de diriger le joueur selon la commande entrée par l'utilisateur.
     * @param pCommand Commande de l'utilisateur traitée en amont par le programme.
     */
    private void goRoom(Command pCommand) 
    {
        if(!pCommand.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            this.aGui.println("Go where? \n");
            return;
        }

        String vDirection = pCommand.getSecondWord();

        // Try to leave current room.
        Room vNextRoom = this.aPlayer.getCurrentRoom().getExit(vDirection, this.aRooms.values(), this.aRooms.size());

        if (vNextRoom == null)
            this.aGui.println("There is no door!\n");
        else {
            this.aPlayer.addRoom(aPlayer.getCurrentRoom());
            this.aPlayer.changeRoom(vNextRoom);
            this.printLocationInfo();
            if(this.aPlayer.getRoomImageName() != null)
                this.aGui.showImage(this.aPlayer.getRoomImageName());
        }
        if( this.aPlayer.getCurrentRoom().getImageName().equals("Images/Final.jpg") ) {
            this.aGui.println( this.aPlayer.finalFight() );
            endGame();
        }
        this.aGui.update();
    }

    private void endGame()
    {
        this.aGui.println("Thanks for playing ! see you soon \n");
        this.aGui.enable(false);
    }
}
