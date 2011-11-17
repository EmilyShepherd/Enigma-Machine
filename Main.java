
@SuppressWarnings("unused")
/**
 * Main
 * 
 * Performs which ever action is required
 * 
 * @author Emily Shepherd
 * @see    Main.DO
 */
public class Main
{
    /**
     * These are actions that the main()
     * method can deal with
     */
    final private static int NOTHING = 0;
    final private static int GUI     = 1;
    final private static int CRACK1  = 2;
    final private static int CRACK2  = 3;
    final private static int CRACK3  = 4;
    
    /**
     * What the main() method should do, use
     * the constants above to set DO's value
     */
    final private static int DO = GUI;
    
    /**
     * main
     * 
     * Will start the program, doing whatever is specified
     * by the DO constant.
     * 
     * To quote Life's Too Short, this method
     * "does whatever"...
     * 
     * @param  args
     * @throws Exception 
     * @see    Main.DO
     */
    public static void main(String[] args) throws Exception
    {
        switch (DO)
        {
            case NOTHING:
                System.out.println(" :P");
                break;
               
            case GUI:
                new Interface();
                break;
               
            case CRACK1:
                crack(1);
                break;
                
            case CRACK2:
                crack(2);
                break;
                
            case CRACK3:
                crack(3);
                break;

            /* Commented out because the temptation is
             * to run this is too great:
            
            case 666:
                while (true)
                {
                    main(args);
                }
                break;
            
             */
                
            default:
                System.err.println
                (
                      "Unknown DO code (" + DO + "). "
                    + "Please stick to 0 <= DO <= 4"
                );
        }
    }
    
    private static void crack(int test)
    {
        try
        {
            BruteForceCracker bfc = new BruteForceCracker();
            
            switch (test)
            {
                case 1:
                    crack1(bfc);
                    break;
                case 2:
                    crack1(bfc);
                    break;
                case 3:
                    crack1(bfc);
                    break;
                default:
                    System.err.println("Never heard of that test!");
            }
        }
        //Hard coded settings mean this exception won't be
        //reached, so I'm not taking it seriously.
        catch (Exception e)
        {
            System.err.println("Logic has failed us...");
            e.printStackTrace();
        }
    }
    
    /**
     * DAISY, DAISY, GIVE ME YOUR ANSWER DO
     * 
     * @param  bfc
     * @throws Exception
     */
    private static void crack1(BruteForceCracker bfc) throws Exception
    {
        bfc.crackPlugs("JBZAQVEBRPEVPUOBXFLCPJQSYFJI", "ANSWER");
    }
    
    /**
     * WE'LL ALWAYS BE TOGETHER,
     * HOWEVER FAR IT SEEMS.
     * WE'LL ALWAYS BE TOGETHER,
     * TOGETHER IN ELECTRIC DREAMS.
     * 
     * @param  bfc
     * @throws Exception
     */
    private static void crack2(BruteForceCracker bfc) throws Exception
    {
        bfc.crackPositions("AVPBLOGHFRLTFELQEZQINUAXHTJMXDWERTTCHLZTGBFUPORNHZSLGZMJNEINTBSTBPPQFPMLSVKPETWFD", "ELECTRIC");
    }
    
    /**
     * I LOVE COFFEE,
     * I LOVE TEA,
     * I LOVE THE JAVA JIVE,
     * AND IT LOVES ME.
     * 
     * @param  bfc
     * @throws Exception
     */
    private static void crack3(BruteForceCracker bfc) throws Exception
    {
        bfc.crackRotors("WMTIOMNXDKUCQCGLNOIBUYLHSFQSVIWYQCLRAAKZNJBOYWW", "JAVA");
    }
}
