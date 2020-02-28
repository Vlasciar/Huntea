import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Escaped extends World
{    
    public Escaped()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(69*13, 840, 1);
        prepare();
    }    
    Play play = new Play();
    private void prepare()
    {
        background.play();
        background.setVolume(20);        
        addObject(play,701,451);
        Time time = new Time();
        addObject(time,600,600);
        
        GreenfootImage image = new GreenfootImage(String.valueOf(Time.getMinutes()) +":" + String.valueOf(Time.getSeconds()), 90, Color.BLACK, null);
        getBackground().drawImage(image, time.getX() + 95, time.getY() -50);        
        
        Thread loader = new Thread(new LevelLoader(this));
        loader.start();
        loading = false;
        
    } 
    int frame=0; 
    MyWorld w =null; //new MyWorld();
    boolean loading;
    GreenfootSound background = new GreenfootSound("The_Tomb_of_the_Ancients.mp3");
    public void act()
    {        
        Time.isCounting = false; 
        if(Greenfoot.mouseClicked(play) || loading)
        {      
            background.setVolume(0);            
            Time.isCounting = true; 
            try {// verifica daca lumea e incarcata
                Greenfoot.setWorld(w);
            }
            catch(Exception e) {
                GreenfootImage image = new GreenfootImage("Loading...", 40, Color.BLACK, null);
                getBackground().drawImage(image, play.getX() - 75, play.getY() + 20 );
                
                loading = true;
            }                
        }
    }    
    public synchronized void loadLevel()
    {
        w = new MyWorld();
    }
}
