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
    boolean twoD = false;
    public Wall(int cl)
    {
        color=cl;
    }
    protected void addedToWorld(World world)
    {
        GreenfootImage img = new GreenfootImage(getImage());
        int x = img.getWidth();
        int y = img.getHeight();
        
        //getWorld().addObject(this,100,100);
        
         if(x>y) length = x;
         else length = y;

         
         angle = getRotation();
         sin = Math.sin(Math.toRadians(angle));
         cos = Math.cos(Math.toRadians(angle));
         
         alaturata = cos*(length/2);
         opusa = sin*(length/2);
        // if(Math.abs(opusa)<(double)1/10) opusa=0;
         //if(Math.abs(alaturata)<(double)1/10) alaturata=0;
         
         x1 = getX() + alaturata;
         y1 = getY() + opusa;
         x2 = getX() - alaturata;
         y2 = getY() - opusa;  
         
         
       //  getWorld().showText(String.valueOf(x2), 200, 200);
       //  getWorld().showText(String.valueOf(y2), 200, 270);
    }    
    public void act() 
    {        
         //getWorld().showText(String.valueOf(x2), 200, 200);
         //getWorld().showText(String.valueOf(y2), 200, 270);
         //int myWorldVar = ((MyWorld) getWorld()).myVar;
         if(twoD==true){
         getWorld().getBackground().drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        }
    }    
}
