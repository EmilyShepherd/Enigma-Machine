import java.awt.Font;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

@SuppressWarnings("serial")
/**
 * Interface
 * 
 * The main class of the GUI
 * 
 * @author Emily Shepherd
 *
 */
public class Interface extends JFrame implements KeyListener
{   
    /**
     * The font object to use for all buttons
     */
    private Font defaultFont;
    
    /**
     * The EnigmaMachine for encoding
     */
    private EnigmaMachine em;
    
    /**
     * The UIHandlers for running certain parts of 
     * the GUI's interface
     */
    private RotorUIHandler[]  rotors;
    private ButtonUIHandler[] buttons;
    private IOUIHandler       IO;
    //private PlugboardUIHandler puih; //PlugboardUIHandler is not finished :(
    
    /**
     * The timer that clicks buttons and returns them
     * to their normal colour after they've gone green.
     * 
     * We use only one, because creating a new one per
     * task creates a new Thread, which is unneeded
     */
    private Timer timer = new Timer();
    
    /**
     * Interface
     * 
     * @throws Exception
     */
    public Interface() throws Exception
    {
        buttons = new ButtonUIHandler[26];
        em      = new EnigmaMachine();
        
        this.addKeyListener(this);
        
        em.addRotor(new TurnoverRotor("I",   0), 0);
        em.addRotor(new TurnoverRotor("II",  0), 1);
        em.addRotor(new TurnoverRotor("III", 0), 2);
        em.addReflector(new Reflector("I"));
        
        UIHandler.init(this);
        rotors = new RotorUIHandler[]
        {
            new RotorUIHandler(em.getRotor(0), 1),
            new RotorUIHandler(em.getRotor(1), 2),
            new RotorUIHandler(em.getRotor(2), 3)
        };
        new ReflectorUIHandler(em.getReflector());   
        
        draw();
        
        //puih = new PlugboardUIHandler();
    }
    
    /**
     * draw
     * 
     * Draws the form
     * 
     */
    private void draw()
    {
        //Setup basic settings
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Enigma Machine");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setLayout(null);
        
        defaultFont = new Font(null, 0, 18);
        
        JLabel title = new JLabel("Enigma Machine");
            title.setBounds(10, 10, 500, 50);
            title.setFont(defaultFont);
        this.add(title);
        
        drawButtons(10, 70);
        IO = new IOUIHandler();
    }
    
    /**
     * beginEncoding
     * 
     * Loops through each ButtonUIHandler, setting timers
     * for each to be clicked, so that it flashes on the
     * screen nicely
     * 
     */
    public void beginEncoding()
    {
        char[] inputText = IO.getCharArray();
        
        long time = 0;
        for (char c : inputText)
        {
            if (65 <= c && c <= 90)
            {
                timer.schedule(new encodeLetter(c), time);
                time += 50;
            }
            else if (97 <= c && c <= 122)
            {
                timer.schedule(new encodeLetter((char)(c - 32)), time);
                time += 50;
            }
        }
    }
    
    public String encode(String str)
    {
        String output = "";
        try
        {
            EnigmaFile ef = new EnigmaFile();
            output = String.valueOf
            (
                em.encode(ef.clean(str.toCharArray()))
            );
            updateRotorUI();
        }
        catch (Exception e1)
        {
            throw new RuntimeException("");
        }
        
        return output;
    }
    
    /**
     * updateRotorUI
     * 
     * Lopps through each rotor, and causes it to update with
     * its updates rotor positions
     */
    public void updateRotorUI()
    {
        for (RotorUIHandler ruih : rotors)
        {
            ruih.updateUI();
        }
    }
    
    /**
     * getButton
     * 
     * Returns the ButtonUIHandler for the given char
     * @param  chr Which ButtonUIHandler
     * @return     The ButtonUIHandler for the given UI
     */
    public ButtonUIHandler getButton(char chr)
    {
        return buttons[chr - 65];
    }
    
    /**
     * encodeChar
     * 
     * Encodes one character and prints it to the output
     * TextArea
     * 
     * @param chr The character to encode
     */
    public void encodeChar(char chr)
    {
        char out = 'A';
        
        this.requestFocus();

        try
        {
            out = em.encodeLetter(chr);
        }
        catch (Exception e1)
        {
            throw new RuntimeException("Odd misconfiguration");
        }
        
        IO.addChar(out);
        
        getButton(out).goGreen();
        updateRotorUI();
    }
    
    /**
     * drawButtons
     * 
     * Creates a panel for the buttons and creates a ButtonUIHandler
     * for each character 'A' - 'Z'
     * 
     * @param _x The x position of the button panel
     * @param _y The y position of the button panel
     */
    private void drawButtons(int _x, int _y)
    {
        JPanel buttons = new JPanel();
            buttons.setBounds(_x, _y, 770, 240);
            buttons.setLayout(null);
        this.add(buttons);
        
        ButtonUIHandler.initButtonUI(timer, buttons);
    
        for (char chr = 'A'; chr <= 'Z'; chr++)
        {
            this.buttons[chr - 65] = new ButtonUIHandler(chr);
        }
        
        buttons.updateUI();
    }

    /**
     * keyTyped
     * 
     * Listens for key types and presses the appropriate button
     */
    public void keyTyped(KeyEvent e)
    {
        char chr = e.getKeyChar();
        
        if (65 <= chr && chr <= 90)
        {
            encodeChar(chr);
        }
        else if (97 <= chr && chr <= 122)
        {
            encodeChar((char)(chr - 32));
        }
    }
    
    /**
     * encodeLetter
     * 
     * The timer task used by beginEncoding()
     * 
     * @see    Interface.beginEncoding()
     * @author Alex
     *
     */
    private class encodeLetter extends TimerTask
    {
        /**
         * The character of the ButtonUIHandler to
         * click
         */
        private char chr;
        
        /**
         * encodeLetter
         * 
         * @param chr The character of the ButtonUIHandler
         *            to click
         */
        public encodeLetter(char chr)
        {
            this.chr = chr;
        }
        
        /**
         * run
         * 
         * Calls Interface.encodeChar(chr), where
         * chr was specified in the constructor
         * 
         * @see Interface.encodeChar(char)
         */
        public void run()
        {
            encodeChar(chr);
        }
    }
    
    /**
     * Unused methods
     * 
     * Required to satisfy implements KeyListener
     */
    public void keyPressed (KeyEvent e){}
    public void keyReleased(KeyEvent e){}
}
