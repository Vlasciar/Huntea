
public class LevelLoader implements Runnable
{
    private Escaped esc; //declare our instance variable
    
    /**
     * Constructor. 
     */
    public LevelLoader(Escaped esc)
    {
        this.esc = esc; //initialize our instance variable
    }
    
    /**
     * This is what the thread will do.
     */
    public void run()
    { 
        esc.loadLevel(); 
    }
}