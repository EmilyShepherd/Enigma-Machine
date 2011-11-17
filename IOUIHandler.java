import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

/**
 * IOUIHandler
 * 
 * Handles the UI of the input and output boxes, and
 * their buttons
 * 
 * @author Emily Shepherd
 *
 */
public class IOUIHandler extends UIHandler
{
    /**
     * The box the user types messages into
     */
    private JTextArea input;
    
    /**
     * The box that contains the encoded message
     */
    private JTextArea output;
    
    /**
     * IOUIHandler
     * 
     */
    public IOUIHandler()
    {
        draw();
    }
    
    /**
     * draw
     * 
     * Draws the UI
     */
    protected void draw()
    {
        /* INPUT BOX */
        input = new JTextArea();
        input.setBounds(10, 320, 770, 100);
        form.add(input);
        
        /* OUTPUT BOX */
        output = new JTextArea();
        output.setBounds(10, 440, 770, 100);
        output.setEditable(false);
        form.add(output);
        
        /* "Encode!" Button */
        JButton doNiceEncode = new JButton("Encode!");
        doNiceEncode.setBounds(10, 560, 120, 30);
        doNiceEncode.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               form.beginEncoding();
           }
        });
        form.add(doNiceEncode);
    
        /* "Quick Encode" Button */
        JButton doEncode = new JButton("Quick Encode");
        doEncode.setBounds(140, 560, 120, 30);
        doEncode.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
               setOutput(form.encode(input.getText()));
           }
        });
        form.add(doEncode);
        
        /* "Clear" Button */
        JButton clear = new JButton("Clear");
        clear.setBounds(270, 560, 120, 30);
            clear.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent e)
               {
                   input.setText("");
                   output.setText("");
               }
            });
        form.add(clear);
        
        //Update all the UIs now that we have finished drawing
        input.updateUI();
        output.updateUI();
        doNiceEncode.updateUI();
        doEncode.updateUI();
        clear.updateUI();
    }
    
    /**
     * getCharArray
     * 
     * Gets the text in the input box, as an char array
     * 
     * @return The text in the input box
     */
    public char[] getCharArray()
    {
        return input.getText().toCharArray();
    }
    
    /**
     * addChar
     * 
     * Adds the given char to the output box
     * 
     * @param chr The char to add to the output box
     */
    public void addChar(char chr)
    {
        output.append(String.valueOf(chr));
    }
    
    /**
     * setOutput
     * 
     * Sets the text in the output box
     * 
     * @param str The text to put in the output box
     */
    public void setOutput(String str)
    {
        output.setText(str);
    }
}
