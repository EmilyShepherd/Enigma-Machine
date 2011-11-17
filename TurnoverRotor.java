/**
 * TurnoverRotor
 * 
 * Models a turnover rotor, used in the Engima Machine
 * 
 * @author Emily Shepherd
 *
 */
public class TurnoverRotor extends BasicRotor
{
    /**
     * When the position meets this, the next rotor will
     * be turned
     */
    private int turnoverPosition = 0;
    
    /**
     * The rotor to the right of this one
     */
    private BasicRotor nextRotor;
    
    /**
     * TurnoverRotor
     * 
     * @param  type      The type of rotor this is
     * @param  position  The rotor's initial position
     * @param  nextRotor The rotor to the right of this
     * @throws           RotorException
     */
    public TurnoverRotor(String type, int position, BasicRotor nextRotor) throws RotorException
    {
        super(type, position);
        initialiseTurnover(type);
        this.nextRotor = nextRotor;
    }
    
    /**
     * TurnoverRotor
     * 
     * calls this(type, position)
     * 
     * @param  type      The type of rotor this is
     * @param  position  The rotor's initial position
     * @throws           RotorException
     * @see              TurnoverRotor(String, int, BasicRotor)
     */
    public TurnoverRotor(String type, int position) throws RotorException
    {
        this(type, position, null);
    }
    
    /**
     * initialiseTurnover
     * 
     * Similar to initialise(). Sets up the
     * turnoverPosition based on the given type
     * 
     * @param  type The rotor type
     */
    private void initialiseTurnover(String type)
    {
        if (type.equals("I"))
        {
            turnoverPosition = 24;
        }
        else if (type.equals("II"))
        {
            turnoverPosition = 12;
        }
        else if (type.equals("III"))
        {
            turnoverPosition = 3;
        }
        else if (type.equals("IV"))
        {
            turnoverPosition = 17;
        }
        else if (type.equals("V"))
        {
            turnoverPosition = 7;
        }
    }
    
    /**
     * rotor
     * 
     * If the position is at the turnover position
     * and there is a nextRotor set, it will call
     * nextRotor's rotate() method.
     * 
     */
    public void rotate()
    {
        super.rotate();
        if (position == turnoverPosition && nextRotor != null)
        {
            nextRotor.rotate();
        }
    }
    
    /**
     * setNextRotor
     * 
     * Sets the next rotor
     * 
     * @param nextRotor The BasicRotor to the right of this
     */
    public void setNextRotor(BasicRotor nextRotor)
    {
        this.nextRotor = nextRotor;
    }
}
