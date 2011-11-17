/**
 * EnigmaMachine
 * 
 * Models an EnigmaMachine
 * 
 * @author Emily Shepherd
 *
 */
public class EnigmaMachine
{
    /**
     * The plugboard that this machine uses
     */
    private Plugboard plugboard;
    
    /**
     * The rotor slots
     */
    private BasicRotor[] slots;
    
    /**
     * The reflector
     */
    private Reflector reflector;
    
    /**
     * EnigmaMachine
     * 
     */
    public EnigmaMachine()
    {
        plugboard  = new Plugboard();
        slots      = new BasicRotor[3];
    }
    
    /**
     * addPlug
     * 
     * Adds a new plug to plugboard
     * 
     * @param  char1 One end of the plug
     * @param  char2 The other end of the plug
     * @throws       Plugboard.PlugboardException
     * @see          plugboard.addPlug(int, int)
     */
    public void addPlug(char char1, char char2) throws Plugboard.PlugboardException
    {
        plugboard.addPlug(charToInt(char1), charToInt(char2));
    }
    
    /**
     * clearPlugboard
     * 
     * Clears the plugboard of all plugs
     * 
     * @see Plugboard.clear()
     */
    public void clearPlugboard()
    {
        plugboard.clear();
    }
    
    /**
     * clearRotors
     * 
     * Clears the slots array
     */
    public void clearRotors()
    {
        slots[0] = null;
        slots[1] = null;
        slots[2] = null;
    }
    
    /**
     * addRotor
     * 
     * Adds a new rotor
     * 
     * @param  rotor The rotor to add
     * @param  slot  The slot to add the rotor to
     * @throws       EnigmaException
     */
    public void addRotor(BasicRotor rotor, int slot) throws EnigmaException
    {
        if (rotor != null)
        {
            for (int i = 0; i < 3; i++)
            {
                if (slots[i] != null && slots[i].equals(rotor))
                {
                    throw new EnigmaException("Rotor type already exists: " + rotor.getType());
                }
            }
        }

        if (slot != 0)
        {
            BasicRotor br = slots[slot - 1];
            if (br instanceof TurnoverRotor)
            {
                ((TurnoverRotor) br).setNextRotor(rotor);
            }
        }
        
        slots[slot] = rotor;
    }
    
    /**
     * getRotor
     * 
     * Returns the rotor at the given slot
     * 
     * @param  slot The slot to retrieve
     * @return The rotor at that slot
     */
    public BasicRotor getRotor(int slot)
    {
        return slots[slot];
    }
    
    /**
     * addReflector
     * 
     * Uses the given reflector
     * 
     * @param reflector The reflector to use
     */
    public void addReflector(Reflector reflector)
    {
        this.reflector = reflector;
    }
    
    /**
     * getReflector
     * 
     * Returns The current reflector
     * @return The current reflector
     */
    public Reflector getReflector()
    {
        return reflector;
    }
    
    /**
     * setPosition
     * 
     * Sets the position for the rotor at the given
     * slot.
     * 
     * @param slot     The slot of the rotor to change
     * @param position The new position
     * @see            getRotor(int)
     * @see            Rotor.setPosition(int)
     */
    public void setPosition(int slot, int position)
    {
        getRotor(slot).setPosition(position);
    }
    
    /**
     * encodeLetter
     * 
     * Encodes the given character
     * 
     * @param  chr The character to encode
     * @return     The encoded character
     * @throws     Rotor.RotorException
     * @throws     EnigmaException
     */
    public char encodeLetter(char chr) throws Rotor.RotorException, EnigmaException
    {
        //Check the slots are all filled and we have a reflector
        if (slots[0] == null || slots[1] == null || slots[2] == null)
        {
            throw new EnigmaException
            (
                  "Unable to encode character: "
                + "Missing rotor. There should be 3"
            );
        }
        else if (reflector == null)
        {
            throw new EnigmaException
            (
                  "Unable to encode character: "
                + "Missing reflector"
            );
        }
        
        int charInt = charToInt(chr);
        
        charInt = plugboard.encode(charInt);
        
        //Forward
        for (Rotor rotor : slots)
        {
            charInt = rotor.substitute(charInt);
        }
        
        charInt = reflector.substitute(charInt);
        
        //Back
        for (int i = 2; i >= 0; i--)
        {
            charInt = slots[i].substitueBack(charInt);
        }
        
        charInt = plugboard.encode(charInt);
        
        slots[0].rotate();
        
        return (char)(charInt + 65);
    }
    
    /**
     * encode
     * 
     * Encodes each character in the char array
     * 
     * @param  chars The char array to encode
     * @return       The encoded char array
     * @throws       Rotor.RotorException
     * @throws       EnigmaException
     * @see          encode(char)
     */
    public char[] encode(char[] chars) throws Rotor.RotorException, EnigmaException
    {
        for (int i = 0; i < chars.length; i++)
        {
            chars[i] = encodeLetter(chars[i]);
        }
        
        return chars;
    }
    
    /**
     * encode
     * 
     * Encodes each character in the given string
     * 
     * @param  string The string to encode
     * @return        The encoded string
     * @throws        Rotor.RotorException
     * @throws        EnigmaException
     * @see           encode(char)
     */
    public String encode(String string) throws Rotor.RotorException, EnigmaException
    {
        return String.valueOf(encode(string.toCharArray()));
    }
    
    /**
     * charToInt
     * 
     * Converts the given char to int form, which
     * is used throughout the encryption process
     * 
     * @param  chr The character to convert
     * @return The converted character
     */
    private int charToInt(char chr)
    {
        return (int)chr - 65;
    }
    
    @SuppressWarnings("serial")
    /**
     * EnigmaException
     * 
     * Thrown when Exceptions occur within the EnigmaMachine
     */
    public class EnigmaException extends Exception
    {
        public EnigmaException(String msg)
        {
            super(msg);
        }
    }
}
