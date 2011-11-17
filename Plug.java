/**
 * Plug
 * 
 * Models a plug on the plugboard
 * 
 * @author Emily Shepherd
 *
 */
public class Plug
{
    /**
     * The ends of the plug, as ints
     * A = 0, B = 1, C = 2, etc...
     */
    private int end1;
    private int end2;

    /**
     * Plug
     * 
     * Convenience constructor. Will turn characters
     * into int form, which is used throughout the
     * Enigma Machine encryption process.
     * 
     * @param end1 One end of the plug, as a char
     * @param end2 The other end of the plug, as a char
     * @deprecated
     */
    public Plug (char end1, char end2)
    {
        this.end1 = (int)end1 - 65;
        this.end2 = (int)end2 - 65;
    }
    
    /**
     * Plug
     * 
     * @param end1 One end of the plug, as an int
     * @param end2 The other end of the plug, as an int
     */
    public Plug(int end1, int end2)
    {
        this.end1 = end1;
        this.end2 = end2;
    }
    
    /**
     * getEnd1
     * 
     * Returns the character at end 1, as an int
     * 
     * @return The character at end 1, as an int
     */
    public int getEnd1()
    {
        return end1;
    }
    
    /**
     * getEnd2
     * 
     * Returns the character at end 2, as an int
     * 
     * @return The character at end 2, as an int
     */
    public int getEnd2()
    {
        return end2;
    }
    
    /**
     * test
     * 
     * Attempts to convert the character
     * 
     * @param  letter The character to convert
     * @return        The converted character
     */
    public int test(int letter)
    {
        if (end1 == letter)
        {
            return end2;
        }
        else if (end2 == letter)
        {
            return end1;
        }
        
        return letter;
    }
    
    /**
     * clashesWith
     * 
     * Tests if this plug already knows about one
     * of the ends of this plugs clashes with one
     * of the ends of the given plug
     * 
     * @param  plug The plug to compare this with
     * @return      True if there is a clash
     * @deprecated
     */
    public boolean clashesWith(Plug plug)
    {
        if
        (
            plug.getEnd1() == end1 ||
            plug.getEnd2() == end1 ||
            plug.getEnd1() == end2 ||
            plug.getEnd2() == end2
        )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
