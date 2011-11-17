import java.util.ArrayList;

/**
 * Plugboard
 * 
 * Models the plugboard of the Enigma Machine
 * 
 * @author Emily Shepherd
 *
 */
public class Plugboard
{
    /**
     * The plugs that are currently on this board
     */
    private ArrayList<Plug> plugs = new ArrayList<Plug>();
    
    /**
     * The characters on the board.
     */
    private Plug[] chars = new Plug[26];
    
    /**
     * addPlug
     * 
     * Adds a new plug to the board
     * 
     * @param  char1 One end of the plug
     * @param  char2 One end of the plug
     * @throws       PlugboardException
     * @see          Plug(int, int)
     */
    public void addPlug(int char1, int char2) throws PlugboardException
    {
        //Check that these plugs aren't already in use
        if (chars[char1] != null || chars[char2] != null)
        {
            throw new PlugboardException("One of the ends of that plug are already in use!");
        }
        
        Plug plug = new Plug(char1, char2);
        plugs.add(plug);
        chars[char1] = plug;
        chars[char2] = plug;
    }
    
    /**
     * getNumPlugs
     * 
     * Returns the number of plugs on the board
     * 
     * @return The number of plugs on the board
     */
    public int getNumPlugs()
    {
        return plugs.size();
    }
    
    /**
     * clear
     * 
     * Removes all plugs on the board
     * 
     */
    public void clear()
    {
        plugs.clear();
        chars = new Plug[26];
    }
    
    /**
     * encode
     * 
     * If a plug is found for the given character, the
     * character at the other end of the plug is returned.
     * Otherwise the character is returned unchanged.
     * 
     * @param chr The character to encode
     * @return    The encoded character
     */
    public int encode(int chr)
    {
        if (chars[chr] != null)
        {
            return chars[chr].test(chr);
        }
        else
        {
            return chr;
        }
    }
    
    @SuppressWarnings("serial")
    /**
     * PlugboardException
     * 
     * Thrown when Plugboard is asked to put a new plug
     * on a character that already has a plug
     */
    public class PlugboardException extends Exception
    {
        public PlugboardException(String msg)
        {
            super(msg);
        }
    }
}
