import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Main_Ray here.
 * 
 * @author (your name) 
 * @version 2
 */
public class Main_Ray extends Actor 
{
    /**
     * Act - do whatever the Main_Ray wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
        
    boolean Mouse1_down=false;
    boolean Mouse2_down=false;
    int relative_X=0;
    int relative_Y=-100;
    public void act() 
    {
        
        //this.turnTowards(player.getX()+relative_X,player.getY()+relative_Y);
        GetMouse();    
      ///  move(3);
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
            turn(-1);
        }
        if(Mouse2_down==true)
        {
            turn(1);
        }
    }   
}
