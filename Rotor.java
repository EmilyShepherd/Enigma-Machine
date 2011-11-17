/**
 * Rotor
 * 
 * Models a rotor on the Enigma Machine
 * 
 * @author Emily Shepherd
 *
 */
public abstract class Rotor
{
    /**
     * The type of rotor this is
     */
    protected String type;
    
    /**
     * The current position of the rotor
     */
    protected int position = 0;
    
    /**
     * This array maps each character to another character
     */
    protected int[] mapping;
    
    /**
     * Rotor
     * 
     * Sets up a new Rotor
     * 
     * @param  type     The type of rotor this is
     * @param  position The initial position of the rotor
     * @throws          RotorException
     */
    public Rotor(String type, int position) throws RotorException
    {
        setType(type);
        this.position = position;
    }
    
    public void setType(String type) throws RotorException
    {
      //Attempt to set up the mapping array
        if (!initialise(type))
        {
            throw new RotorException("Unknown Rotor type: " + type); 
        }
        
        this.type = type;
    }

    /**
     * Rotor
     * 
     * Calls constructor with given type and 0 for
     * the position
     * 
     * @param  type           The type of rotor this is
     * @throws RotorException
     * @see    this(String, int)
     */
    public Rotor(String type) throws RotorException
    {
        this(type, 0);
    }

    /**
     * setPosition
     * 
     * Sets the position of this rotor
     * 
     * @param position The new positions for this rotor
     */
    public void setPosition(int position)
    {
        this.position = position;
    }
    
    /**
     * getPosition
     * 
     * Returns this rotor's current position
     * 
     * @return The current position
     */
    public int getPosition()
    {
        return position;
    }
    
    /**
     * getType
     * 
     * Returns this rotor's type
     * 
     * @return The rotor's type
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * equals
     * 
     * Used to check if the given rotor is the same
     * as this one. Returns true if the types and
     * classNames match.
     * 
     * @param  rotor The Rotor to compare this one to
     * @return True if the two rotors are equal
     */
    public boolean equals(Rotor rotor)
    {
        //We check className as well because "TurnoverRotor Type I"
        //is not the same as "BasicRotor Type I"
        if
        (
            this.getClass().getName() == rotor.getClass().getName() &&
            this.type                 == rotor.type
        )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * checkChar
     * 
     * Checks that the given char lies within the mappable
     * region. Throws a RotorException if it is not.
     * 
     * @param  chr The character to test
     * @throws RotorException
     */
    protected void checkChar(int chr) throws RotorException
    {
        if (chr < 0 || chr >= mapping.length)
        {
            throw new RotorException("Cannot substitute unknown char: " + chr);
        }
    }
    
    /**
     * initialise
     * 
     * Should populate the mapping array using the given type.
     * If false is returned, the constructor will throw
     * a RotorException.
     * 
     * @param  type The type of this rotor
     * @return      True if succeeded
     */
    protected abstract boolean initialise(String type);
    
    /**
     * substitute
     * 
     * Should substitute the given character for another
     * 
     * @param  chr The character to substitute
     * @return     The substituted character
     * @throws     RotorException
     */
    public abstract int substitute(int chr) throws RotorException;
    
    @SuppressWarnings("serial")
    /**
     * RotorException
     * 
     * Thrown when Rotor errors occur
     */
    public class RotorException extends Exception
    {
        public RotorException(String msg)
        {
            super(msg);
        }
    }
}
