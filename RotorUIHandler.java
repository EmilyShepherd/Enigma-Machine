import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * RotorUIHandler
 * 
 * Handles a rotor's UI
 * 
 * @author Emily Shepherd
 *
 */
public class RotorUIHandler extends UIHandler implements ChangeListener
{
    /**
     * The rotor this is handling the UI of
     */
    private Rotor rotor;
    
    /**
     * The color to go on error
     */
    private static Color errorBG = new Color(255, 0, 0);
    
    /**
     * The panel for the rotor settings
     */
    private JPanel   rotorPane;
    
    /**
     * Rotor settings
     */
    protected JLabel    text;
    protected JSpinner  positionSelect;
    protected JSpinner  typeSelect;
    
    /**
     * The slot that this rotor uses
     */
    private int slot;
    
    /**
     * List of allowed types for the type selector
     */
    protected String[] allowedTypes = new String[]
    {
        "I ", "II ", "III ", "IV ", "V "
    };
    
    /**
     * RotorUIHandler
     * 
     * @param rotor The rotor to handle
     * @param slot  The slot the rotor is in
     */
    public RotorUIHandler (Rotor rotor, int slot)
    {
        this.rotor  = (Rotor)rotor;
        this.slot   = slot;
        
        draw();
        updateUI();
        
        if (rotorPane != null)
        {
            rotorPane.updateUI();
        }
    }
    
    /**
     * draw
     * 
     * Draws the UI
     */
    protected void draw()
    {
        /* PANEL */
        rotorPane = new JPanel();
        rotorPane.setBounds(790, 50 * slot, 400, 40);
        rotorPane.setLayout(null);
        form.add(rotorPane);
        
        /* TEXT */
        text = new JLabel("Rotor " + slot);
        text.setBounds(0, 0, 70, 25);
        rotorPane.add(text);
        
        /* TYPE SPINNER */
        typeSelect = new JSpinner(new SpinnerListModel
        (
            allowedTypes
        ));
        typeSelect.setBounds(60, 0, 35, 25);
        rotorPane.add(typeSelect);
        
        /* POSITION SPINNER */
        positionSelect = new JSpinner(new SpinnerListModel
        (
            new String[]
            {
                "A ", "B ", "C ", "D ", "E ", "F ",
                "G ", "H ", "I ", "J ", "K ", "L ",
                "M ", "N ", "O ", "P ", "Q ", "R ",
                "S ", "T ", "U ", "V ", "W ", "X ",
                "Y ", "Z "
            }
        ));
        positionSelect.setBounds(100, 0, 35, 25);
        rotorPane.add(positionSelect);
        
        typeSelect.addChangeListener(this);
        positionSelect.addChangeListener(this);
    }

    /**
     * stateChanged
     * 
     * Called when either typeSelect or positionSelect
     * are changed. Calls updateRotor()
     * 
     * @see updateRotor()
     */
    public void stateChanged(ChangeEvent e)
    {
        updateRotor();
    }
    
    /**
     * updateRotor
     * 
     * Updates the rotor with the settings from the UI
     */
    public void updateRotor()
    {
        try
        {
            rotor.setType
            (
                ((String)typeSelect.getValue()).trim()
            );
            rotor.setPosition
            (
                ((String) positionSelect.getValue()).toCharArray()[0] - 65
            );
            rotorPane.setBackground(null);
        }
        catch (Rotor.RotorException re)
        {
            rotorPane.setBackground(errorBG);
        }
    }
    
    /**
     * updateUI
     * 
     * Tells the RotorUIHandler that the rotor position /
     * type has changed. Updates the UI settings accordingly.
     */
    public void updateUI()
    {
        typeSelect.setValue(rotor.getType() + " ");
        positionSelect.setValue
        (
              String.valueOf
              (
                  (char)(rotor.getPosition() + 65)
              )
            + " "
        );
    }
}
