import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;


import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Insets;

import java.net.URL;

//import java.awt.image.*;


/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling (DB edited) + Quentin LEBON + HAVY Audrik + AGOU David + DOUAY Valérie
 * @version 1.0 (Jan 2003) + 2019.04.20
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JButton    aButtonSecret;
    private JButton    aButtonLook;
    private JButton    aButtonInventory;
    private JButton    aButtonHelp;
    private JButton    aButtonNorth;
    private JButton    aButtonSouth;
    private JButton    aButtonEast;
    private JButton    aButtonWest;
    private JButton    aButtonUp;
    private JButton    aButtonDown;
    private JButton    aButtonBack;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JLabel     aItem;

    /**
     * Constructeur de la classe UserInterface.     * 
     * @param pGameEngine un GameEngine capable de gérer les commandes de l'utilisateur.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Affiche du texte dans la zone adaptée.
     * @param pText un String contenant le message voulant être affiché.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Affiche du texte dans la zone adaptée, suivi d'un retour à la ligne.
     * @param pText un String contenant le message voulant être affiché.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Affiche une image dans la zone adaptée.
     * @param pImageName un String contenant le nom de l'image voulant être affichée.
     */
    public void showImage( final String pImageName )
    {
        URL vImageURL = this.getClass().getClassLoader().getResource( pImageName );
        if ( vImageURL == null )
            System.out.println( "image not found" );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * active ou désactive la/les zones ou l'utilisateur peut rentrer des commandes.
     * @param pOnOff un boolean permettant de changer l'etat de la zone de commande.
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff );
        this.aButtonLook.removeNotify();
        this.aButtonInventory.removeNotify();
        this.aButtonHelp.removeNotify();
        this.aButtonBack.removeNotify();
        this.aButtonNorth.removeNotify();
        this.aButtonSouth.removeNotify();
        this.aButtonEast.removeNotify();
        this.aButtonWest.removeNotify();
        this.aButtonUp.removeNotify();
        this.aButtonDown.removeNotify();
        this.aButtonSecret.removeNotify();
        if ( ! pOnOff )
            this.aEntryField.getCaret().setBlinkRate( 0 );
    } // enable()

    /**
     * Créer une interface graphique pour l'utilisateur.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Death's Realm" );
        this.aMyFrame.setMinimumSize( new Dimension( 1000, 600 ) );
        this.aEntryField = new JTextField( 34 );
        
        this.aButtonNorth = new JButton( new ImageIcon( "Images/fleche haut.png" ));
        this.aButtonNorth.setBounds(750,15,55,81);
        
        this.aButtonSouth = new JButton( new ImageIcon( "Images/fleche bas.png" ) );
        this.aButtonSouth.setBounds(750,151,55,81);
        
        this.aButtonEast = new JButton( new ImageIcon( "Images/fleche droite.png" ) );
        this.aButtonEast.setBounds(805,96,81,55);
        
        this.aButtonWest = new JButton( new ImageIcon( "Images/fleche gauche.png" ) );
        this.aButtonWest.setBounds(669,96,81,55);
        
        this.aButtonSecret = new JButton( new ImageIcon( "Images/fleche secret.png" ) );
        this.aButtonSecret.setBounds(750,96,55,55);
        
        this.aButtonUp = new JButton( new ImageIcon ( "Images/fleche up.png" ) );
        this.aButtonUp.setBounds(669,15,81,81);
                
        this.aButtonDown = new JButton( new ImageIcon ( "Images/fleche down.png" ) );
        this.aButtonDown.setBounds(805,15,81,81);
        
        this.aButtonBack = new JButton( new ImageIcon ( "Images/fleche back.png") );
        this.aButtonBack.setBounds(905,20,55,55);
        
        this.aButtonInventory = new JButton( new ImageIcon ( "Images/fleche inventory.png") );
        this.aButtonInventory.setBounds(905,75,55,55);
        
        this.aButtonHelp = new JButton( new ImageIcon ( "Images/fleche help.png") );        
        this.aButtonHelp.setBounds(905,130,55,55);
        
        this.aButtonLook = new JButton( new ImageIcon ( "Images/fleche look.png") );
        this.aButtonLook.setBounds(905,185,55,55);
                             
        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        
        this.aItem = new JLabel( this.aEngine.getItemsInfo() );
        this.aItem.setBounds(560,450,600,20);
        
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        JPanel vPanel = new JPanel();
        
        this.aImage = new JLabel();

        vPanel.setLayout( new BorderLayout() );
        
        vPanel.add(this.aButtonNorth);
        vPanel.add(this.aButtonSouth);
        vPanel.add(this.aButtonEast);
        vPanel.add(this.aButtonWest);
        vPanel.add(this.aButtonSecret);
        vPanel.add(this.aButtonUp);
        vPanel.add(this.aButtonDown);
        vPanel.add(this.aButtonBack);
        vPanel.add(this.aButtonInventory);
        vPanel.add(this.aButtonHelp);
        vPanel.add(this.aButtonLook);
        vPanel.add(this.aItem);
                
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );

        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

        this.aMyFrame.addWindowListener( new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            { 
                System.exit(0); 
            }
        }
        );

        this.aEntryField.addActionListener( this );
        this.aButtonLook.addActionListener( this );
        this.aButtonInventory.addActionListener( this );
        this.aButtonBack.addActionListener( this );
        this.aButtonHelp.addActionListener( this );
        this.aButtonNorth.addActionListener( this );
        this.aButtonSouth.addActionListener( this );
        this.aButtonEast.addActionListener( this );
        this.aButtonWest.addActionListener( this );
        this.aButtonUp.addActionListener( this );
        this.aButtonDown.addActionListener( this );
        this.aButtonSecret.addActionListener( this );
        
        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Fonction détectant les actions de l'utilisateur.
     * @param pE un ActionEvent permettant de déterminer quelle ation à été effectuée par l'utilisateur.
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        if( pE.getSource() == this.aButtonLook ) { 
            this.aEntryField.setText( "" );
            this.aEngine.interpretCommand( "look" ); 
        }
        else if ( pE.getSource() == this.aButtonInventory ) { 
            this.aEntryField.setText( "" );
            this.aEngine.interpretCommand( "inventory" );
        }
        else if ( pE.getSource() == this.aButtonBack ) { 
            this.aEntryField.setText( "" );
            this.aEngine.interpretCommand( "back" );
        }
        else if ( pE.getSource() == this.aButtonHelp ) { 
            this.aEntryField.setText( "" );
            this.aEngine.interpretCommand( "help" );
        }
        else if ( pE.getSource() == this.aButtonNorth ) { 
            this.aEntryField.setText( "" );
            this.aEngine.interpretCommand( "go north" );
        }
        else if ( pE.getSource() == this.aButtonSouth ) { 
            this.aEntryField.setText( "" );
            this.aEngine.interpretCommand( "go south" );
        }
        else if ( pE.getSource() == this.aButtonEast ) { 
            this.aEntryField.setText( "" );
            this.aEngine.interpretCommand( "go east" );
        }
        else if ( pE.getSource() == this.aButtonWest ) { 
            this.aEntryField.setText( "" );
            this.aEngine.interpretCommand( "go west" );
        }
        else if ( pE.getSource() == this.aButtonSecret ) { 
            this.aEntryField.setText( "" );
            this.aEngine.interpretCommand( "go secret" );
        }
        else if ( pE.getSource() == this.aButtonUp ) { 
            this.aEntryField.setText( "" );
            this.aEngine.interpretCommand( "go up" );
        }
        else if ( pE.getSource() == this.aButtonDown ) { 
            this.aEntryField.setText( "" );
            this.aEngine.interpretCommand( "go down" );
        }
        else {
            processCommand();
        }
        
    } // actionPerformed(.)

    /**
     * Cette fonction permet de lire la commande entrée par l'utilisateur et de démarer l'interpretation 
     * de cette commande.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
    
    public void update()
    {
        this.aItem.setText( this.aEngine.getItemsInfo() );
    }
} // UserInterface 
