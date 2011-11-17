import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * ButtonUIHandler
 * 
 * Handles the UI of each character button
 * 
 * @author Emily Shepherd
 *
 */
public class ButtonUIHandler extends UIHandler implements ActionListener
{
    /**
     * The character this button represents
     */
    private char text;
    
    /**
     * Position of button
     */
    private int x;
    private int y;
    
    /**
     * The button
     */
    private JButton button;
    
    /**
     * Colors and fonts
     */
    final private static Color GREEN = new Color(0, 255, 0);
    final private static Font  FONT  = new Font(null, 0, 18);
    
    /**
     * Timer used to return the buttons to non-green after a
     * pause
     */
    private static Timer     timer;
    
    /**
     * The parent panel
     */
    private static JPanel    parent;
    
    /**
     * initButtonUI
     * 
     * Sets up the static fields of the Class
     * 
     * @param timer  The timer
     * @param parent The parent panel
     */
    public static void initButtonUI(Timer timer, JPanel parent)
    {
        ButtonUIHandler.timer  = timer;
        ButtonUIHandler.parent = parent;
    }
    
    /**
     * ButtonUIHandler
     * 
     * @param text The character this button represents
     */
    public ButtonUIHandler(char text)
    {
        this.text   = text;
        
        createCoords();
        draw();
    }
    
    /**
     * createCoords
     * 
     * Calculates the coordinates of the button, based on
     * what character it is
     */
    private void createCoords()
    {
        int textInt = (int)text - 65;
        
        y = (int)(textInt / 7);
        x = (int)(textInt - (y * 7));
        
        //Last row should be centralised
        if (y == 3)
        {
            x++;
        }
        
        //Apply width & height (+margin)
        x = x * 110;
        y = y * 60;
    }
    
    /**
     * draw
     * 
     * Draws the UI
     */
    protected void draw()
    {
        button = new JButton(String.valueOf(text));
        button.setBounds(x, y, 100, 50);
        button.setFont(FONT);
        parent.add(button);
        
        button.addActionListener(this);
    }
    
    /**
     * actionPerformed
     * 
     * Called when the button is clicked. Calls form.encodeChar(text)
     * 
     * @see Interface.encodeChar()
     */
    public void actionPerformed(ActionEvent e)
    {
        form.encodeChar(text);
    }
    
    /**
     * goGreen
     * 
     * Makes this button go green and sets the timer to
     * return to normal after a pause
     */
    public void goGreen()
    {
        button.setBackground(GREEN);
        timer.schedule(new GoNormal(), 500);
    }
    
    /**
     * GoNormal
     * 
     * The TimerTask to return the button to normal
     * color after it's been green for a bit
     * 
     * @author Alex
     *
     */
    private class GoNormal extends TimerTask
    {
        public void run()
        {
            button.setBackground(null);
        }
    }
}
