/**
 * BasicRotor
 * 
 * Models the basic rotor, used in the Engima Machine
 * 
 * @author Emily Shepherd
 *
 */
public class BasicRotor extends Rotor
{
    /**
     * The reverse of mapping
     */
    private int[] inverseMapping;
    
    /**
     * BasicRotor
     * 
     * @param  type     The type of rotor this is
     * @param  position The rotor's initial position
     * @throws          RotorException
     * @see             Rotor(String, int)
     */
    public BasicRotor(String type, int position) throws RotorException
    {
        super(type, position);
    }
    
    /**
     * initialise
     * 
     * Creates the mapping array from the given type.
     * Also creates the inverseMapping array by inverting
     * the mapping array.
     * 
     * @param  type The rotor type
     * @return True on success
     */
    protected boolean initialise(String type)
    {
        if (type.equals("I"))
        {
            mapping = new int[] { 4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 };
        }
        else if (type.equals("II"))
        {
            mapping = new int[] { 0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4 };
        }
        else if (type.equals("III"))
        {
            mapping = new int[] { 1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14 };
        }
        else if (type.equals("IV"))
        {
            mapping = new int[] { 4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1 };
        }
        else if (type.equals("V"))
        {
            mapping = new int[] { 21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10 };
        }
        else
        {
            return false;
        }
        
        //Do the inverse mapping
        inverseMapping = new int[26];
        for (int i = 0; i < 26; i++)
        {
            inverseMapping[mapping[i]] = i;
        }
        
        return true;
    }

    /**
     * substitute
     * 
     * Substitutes the given character for another using
     * the mapping array
     * 
     * @param  chr The character to substitute
     * @return     The substituted character
     * @throws     RotorException
     */
    public int substitute(int chr) throws RotorException
    {
        return map(chr, mapping);
    }
    
    /**
     * substitute
     * 
     * Substitutes the given character for another using
     * the inverseMapping array
     * 
     * @param  chr The character to substitute
     * @return     The substituted character
     * @throws     RotorException
     */
    public int substitueBack(int chr) throws RotorException
    {
        return map(chr, inverseMapping);
    }
    
    /**
     * map
     * 
     * Does the logic of substitute() and subtituteBack()
     * 
     * @param  chr The character to substitute
     * @param  arr The array to use
     * @return The substituted character
     * @throws     RotorException
     */
    private int map(int chr, int[] arr) throws RotorException
    {
        checkChar(chr);
        
        chr = chr - position;
        if (chr < 0)
        {
            chr += 26;
        }
        
        chr = arr[chr] + position;
        if (chr >= 26)
        {
            chr -= 26;
        }
        
        return chr;
    }

    /**
     * rotate
     * 
     * Increments the position.
     * (25 will increment to 0)
     * 
     */
    public void rotate()
    {
        if (position == 25)
        {
            position = 0;
        }
        else
        {
            position++;
        }
    }
}
