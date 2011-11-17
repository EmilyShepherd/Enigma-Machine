
/**
 * ReflectorUIHandler
 * 
 * Handles the UI of a reflector
 * 
 * @author Emily Shepherd
 *
 */
public class ReflectorUIHandler extends RotorUIHandler
{  
    /**
     * ReflectorUIHandler
     * 
     * @param rotor The reflector to handle
     */
    public ReflectorUIHandler(Reflector rotor)
    {
        super(rotor, 4);
    }
    
    /**
     * draw
     * 
     * Limits the allowedTypes to "I ", "II " and calls
     * RotorUIHandler.draw(). Then hides the position
     * selector, as that isn't appopriate for a reflector
     * 
     * @see RotorUIHandler.draw()
     */
    protected void draw()
    {
        allowedTypes = new String[] { "I ", "II " };
        
        super.draw();
        
        positionSelect.setVisible(false);
        text.setText("Reflector");
    }
    
}
