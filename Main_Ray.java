import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Main_Ray here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main_Ray extends Actor 
{
    /**
     * Act - do whatever the Main_Ray wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Player player= new Player();
    public Main_Ray (Player pl)
    {
        player=pl;      
    }
    boolean Mouse1_down=false;
    boolean Mouse2_down=false;
    int relative_X=0;
    int relative_Y=-100;
    public void act() 
    {
        // Add your action code here.
        this.setLocation(player.getX(),player.getY());        
        //relative_X=-(player.getX()-getX());
        //relative_Y=-(player.getY()-getY());
        GetMouse();        
    } 
    private void GetMouse()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse!=null)
        {
            if(Greenfoot.mousePressed(null) && mouse.getButton()==1)
            {
                Mouse1_down=true;
            }
            if(Greenfoot.mouseClicked(null) && mouse.getButton()==1)
            {
                Mouse1_down=false;
            }
            if(Greenfoot.mousePressed(null) && mouse.getButton()==3)
            {
                Mouse2_down=true;
            }
            if(Greenfoot.mouseClicked(null) && mouse.getButton()==3)
            {
                Mouse2_down=false;
            }
        }
        if(Mouse1_down==true)
        {
            DoMove(-2);
        }
        if(Mouse2_down==true)
        {
            DoMove(2);
        }
    }
  //  int position=0;
    private void DoMove(int direction)
    {        
        if(relative_Y<=-100 )
        {
            relative_X+= direction;            
        }
        if(relative_X>=100 )
        {
            relative_Y+= direction;            
        }
        if(relative_Y>=100 )
        {
            relative_X-= direction;            
        }
        if(relative_X<=-100)
        {
            relative_Y-= direction;            
        }
        this.turnTowards(player.getX()+relative_X,player.getY()+relative_Y);
    }
}
