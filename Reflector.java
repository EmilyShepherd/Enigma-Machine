/**
 * Reflector
 * 
 * Models the reflector rotor in the Enigma Machine
 * 
 * @author Alexand Shepherd
 *
 */
public class Reflector extends Rotor
{
    /**
     * Reflector
     * 
     * 
     * @param  type The type of relector this will be
     * @throws      RotorException
     */
    public Reflector(String type) throws RotorException
    {
        super(type);
    }

    /**
     * initialise
     * 
     * Creates the mapping array from the given type.
     * 
     * @param  type The rotor type
     * @return True on success
     */
    protected boolean initialise(String type)
    {
        if (type.equals("I"))
        {
            this.mapping = new int[] { 24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19 };
        }
        else if (type.equals("II"))
        {
            this.mapping = new int[] { 5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11 };
        }
        else
        {
            return false;
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
        checkChar(chr);
        return this.mapping[chr];
    }
}
