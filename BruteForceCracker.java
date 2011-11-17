/**
 * BruteForceCracker
 * 
 * Attempts to decrypt the given message using
 * brute force (trying every combination of the
 * unknowns). The correct decryption is identified
 * by comparing the result to known fragments of
 * the original message.
 * 
 * This method is acceptable here as each crack
 * requires very few iterations (max 17576).
 * 
 * @author Emily Shepherd
 *
 */
public class BruteForceCracker
{
    /**
     * The EnigmaMachine that will be used for cracking
     */
    private EnigmaMachine em;
    
    /**
     * BruteForceCracker
     * 
     * 
     */
    public BruteForceCracker()
    {
        em = new EnigmaMachine();
    }
    
    /**
     * crackPlugs
     * 
     * Trys every single plug combination with hard coded rotors
     * 
     * Return for: JBZAQVEBRPEVPUOBXFLCPJQSYFJI
     * 
     * DAISY, DAISY, GIVE ME YOUR ANSWER DO
     * 
     * @param  msg      The message to decode
     * @param  contains An expected section of the message
     * @throws          EnigmaMachine.EnigmaException
     * @throws          Rotor.RotorException
     * @throws          Plugboard.PlugboardException
     */
    public void crackPlugs(String msg, String contains) throws EnigmaMachine.EnigmaException, Rotor.RotorException, Plugboard.PlugboardException
    {
        //Known configuration
        em.clearPlugboard();
        em.addRotor(new BasicRotor("IV",  8),  0);
        em.addRotor(new BasicRotor("III", 4),  1);
        em.addRotor(new BasicRotor("II",  21), 2);
        em.addReflector(new Reflector("I"));
        
        //Two loops - one per character
        //   (26 - |{'D'}| - |{'S'}|) * (26 - |{'D'}| - |{'S'}| - |{a}|)
        // = 24 * 23
        // = 552 interations
        //
        //Where a is the character chosen to pair D
        for (char a = 'A'; a <= 'Z'; a++)
        {
            //Plugs D and S are already in use
            if (a == 'D' || a == 'S') continue;
            
            for (char b = 'A'; b <= 'Z'; b++)
            {
                //Plugs D and S and whatever a is are already in
                //use
                if (b == 'S' || b == 'D' || b == a) continue;
                
                em.clearPlugboard();
                em.addPlug('D', a);
                em.addPlug('S', b);
                
                //Positions will change on each encoding -
                //We must reset all of them
                em.getRotor(0).setPosition(8);
                em.getRotor(1).setPosition(4);
                em.getRotor(2).setPosition(21);
                
                check(msg, contains);
            }
        }
    }
    
    /**
     * crackPositions
     * 
     * Trys every single rotor position for 3 hard coded
     * rotors, and plugs
     * 
     * Result for: AVPBLOGHFRLTFELQEZQINUAXHTJMXDWERTTCHLZ
     * TGBFUPORNHZSLGZMJNEINTBSTBPPQFPMLSVKPETWFD
     * 
     * WE'LL ALWAYS BE TOGETHER,
     * HOWEVER FAR IT SEEMS.
     * WE'LL ALWAYS BE TOGETHER,
     * TOGETHER IN ELECTRIC DREAMS.
     * 
     * @param  msg      The message to decode
     * @param  contains An expected section of the message
     * @throws          EnigmaMachine.EnigmaException
     * @throws          Rotor.RotorException
     * @throws          Plugboard.PlugboardException
     */
    public void crackPositions (String msg, String contains) throws EnigmaMachine.EnigmaException, Rotor.RotorException, Plugboard.PlugboardException
    {
        //Known configuration
        em.clearPlugboard();
        em.addPlug('H', 'L');
        em.addPlug('G', 'P');
        em.addRotor(new BasicRotor("V", 0),   0);
        em.addRotor(new BasicRotor("III", 0), 1);
        em.addRotor(new BasicRotor("II", 0),  2);
        em.addReflector(new Reflector("I"));

        //Three loops - One per rotor
        //   26 * 26 * 26
        // = 17576 iterations
        for (int a = 0; a < 26; a++)
        {
            for (int b = 0; b < 26; b++)
            {
                for (int c = 0; c < 26; c++)
                {
                    //Positions will change on each encoding -
                    //We must reset all of them
                    em.setPosition(0, a);
                    em.setPosition(1, b);
                    em.setPosition(2, c);

                    check(msg, contains);
                }
            }
        }
    }
    
    /**
     * crackRotors
     * 
     * Trys every rotor type combination for the hard
     * rotor positions and plugs
     * 
     * Result for: WMTIOMNXDKUCQCGLNOIBUYLHSFQSVIWYQCLRAAKZNJBOYWW
     * 
     * I LOVE COFFEE,
     * I LOVE TEA,
     * I LOVE THE JAVA JIVE,
     * AND IT LOVES ME.
     * 
     * @param  msg      The message to decode
     * @param  contains An expected section of the message
     * @throws          EnigmaMachine.EnigmaException
     * @throws          Rotor.RotorException
     * @throws          Plugboard.PlugboardException
     */
    public void crackRotors (String msg, String contains) throws EnigmaMachine.EnigmaException, Rotor.RotorException, Plugboard.PlugboardException
    {
        String[] types = {"I", "II", "III", "IV", "V"};
        
        //Known configuration
        em.clearPlugboard();
        em.addPlug('M', 'F');
        em.addPlug('O', 'I');
        em.addReflector(new Reflector("I"));

        //Three loops - One per rotor
        //   5 * (5 - |{a}|) * (5 - |{a}| - |{b}|)
        // = 5 * 4 * 3
        // = 60 iterations
        //
        //Where a is the rotor type of rotor 0,
        //      b is the rotor type of rotor 1
        for (int a = 0; a < 5; a++)
        {
            em.addRotor(new BasicRotor(types[a], 22), 0);
            
            for (int b = 0; b < 5; b++)
            {
                //We can only have one rotor of each type
                if (b == a) continue;
                
                em.addRotor(new BasicRotor(types[b], 24), 1);
                
                for (int c = 0; c < 5; c++)
                {
                    //We can only have one rotor of each type
                    if (c == b || c == a) continue;
                    
                    //Rotor positions will have changed if this isn't
                    //the first test. Reset them and readd the new third
                    //rotor
                    em.getRotor(0).setPosition(22);
                    em.getRotor(1).setPosition(24);
                    em.addRotor(new BasicRotor(types[c], 23), 2);

                    check(msg, contains);
                }
            }
        }
    }
    
    /**
     * check
     * 
     * Codes the message using the EnigmaMachine
     * and prints the result to the user if it contains
     * the given fragment
     * 
     * @param  msg      The message to decode
     * @param  contains An expected section of the message
     * @throws          EnigmaMachine.EnigmaException
     * @throws          Rotor.RotorException
     */
    private void check(String msg, String contains) throws Rotor.RotorException, EnigmaMachine.EnigmaException
    {
        String output = em.encode(msg);
        if (output.contains(contains))
        {
            System.out.println(output);
        }
    }
}
