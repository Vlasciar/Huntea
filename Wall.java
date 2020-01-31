import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wall_oriz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wall extends Props
{
    int length;//lungimea zidului
    int angle;
    
    double sin;
    double cos;
    
    double alaturata;
    double opusa;
    
    double x1,y1; //pt 1
    double x2,y2; //pt 2
    
    int color;
    boolean discovered;
    int index;  
    public Wall(int cl, int i)
    {
        color=cl;
        index=i;                 
    }
    int gap;
    boolean solid=true;
    protected void addedToWorld(World world)
    {
        discovered=false;
        getImage().setTransparency(0);
        int x = getImage().getWidth();
        int y = getImage().getHeight();
        if(x>y) length = x;
         else length = y;
 
         angle = getRotation();
         sin = Math.sin(Math.toRadians(angle));
         cos = Math.cos(Math.toRadians(angle));
         
         alaturata = cos*(length/2);
         opusa = sin*(length/2);
         
         x1 = getX() + alaturata;
         y1 = getY() + opusa;
         x2 = getX() - alaturata;
         y2 = getY() - opusa;  
  
    }    
    public void act() 
    {         
         if(Greenfoot.isKeyDown("M") && discovered){
             getImage().setTransparency(255);
        }
        else
        {
            getImage().setTransparency(0);
        } 
    }    
}
