/**
 * Classe Game, gère les intéractions entre les pièces et entre le joueur et le programme
 * 
 * @author Théo Gueuret
 * @version 12/02/2019
 */
public class Game
{
    private UserInterface aGui;
    private GameEngine aEngine;
    
    /**
     * Créer une instance de jeu (démarre donc le jeu) dans une console.
     */
    public Game()
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface(aEngine);
        this.aEngine.setGUI(aGui);
    } //Game()
}