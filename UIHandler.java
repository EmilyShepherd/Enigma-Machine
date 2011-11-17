
/**
 * UIHandler
 * 
 * @author Emily Shepherd
 *
 */
public abstract class UIHandler
{
    /**
     * The singleton instance of Interface
     */
    static Interface form;
    
    /**
     * init
     * 
     * Sets up the static field of this Class
     * 
     * @param form The signleton instance of this Interface
     */
    final public static void init(Interface form)
    {
        UIHandler.form = form;
    }
    
    /**
     * draw
     * 
     * Should draw the UI
     */
    abstract protected void draw();
}
