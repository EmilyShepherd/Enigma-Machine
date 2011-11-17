import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * EnigmaFile
 * 
 * Encrypts files
 * 
 * @author Emily Shepherd
 *
 */
public class EnigmaFile
{
    /**
     * writeToFile
     * 
     * Writes the given char array to the given file
     * 
     * @param  chars The char array to write
     * @param  file  The path
     * @throws       IOException
     */
    public void writeToFile(char[] chars, String file) throws IOException
    {
        FileWriter fr = new FileWriter(file);
        
        fr.write(chars);
        
        fr.close();
    }
    
    /**
     * readFile
     * 
     * Reads the given file
     * 
     * @param  file The file to read
     * @return      The char array of the contents
     * @throws      IOException
     */
    public char[] readFile(String file) throws IOException
    {
        File       f      = new File(file);
        FileReader fr     = new FileReader(f);
        char[]     chars  = new char[(int) f.length()];
        
        fr.read(chars);
        
        fr.close();
        
        return chars;
    }
    
    /**
     * clean
     * 
     * Cleans the char array by stripping out
     * unencryptable characters and capitalising
     * lower case.
     * 
     * @param  chars The char array to clean
     * @return       The cleaned char array
     */
    public char[] clean(char[] chars)
    {
        int success = 0;
        for (int i = 0; i < chars.length; i++)
        {
            
            //The character is lower case
            if (chars[i] >= 97 && chars[i] <= 122)
            {
                chars[i] -= 32;
                success++;
            }
            //Character is already good
            else if (chars[i] >= 65 && chars[i] <= 90)
            {
                success++;
            }
            //Don't know what this is, so mark it as null
            else
            {
                chars[i] = 0;
            }
        }
        
        //This creates the output array, without the null
        //spaces of removed characters
        char[] output = new char[success];
        int    j      = 0;
        for (int i = 0; i < chars.length; i++)
        {
            if (chars[i] != 0)
            {
                output[j] = chars[i];
                j++;
            }
        }
        
        return output;
    }
}
