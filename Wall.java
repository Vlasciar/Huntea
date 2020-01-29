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
    int index;
    public Wall(int cl, int i)
    {
        color=cl;
        index=i;
        
    }
    boolean edgeWall=false;
    int gap;
    boolean solid=true;
    protected void addedToWorld(World world)
    {
        GreenfootImage img = new GreenfootImage(getImage());
        int x = img.getWidth();
        int y = img.getHeight();
        if(x>y) length = x;
         else length = y;
         
        /*Wall[] walls = ((MyWorld) getWorld()).walls;
        if(main&& edgeWall==false)
        {
         int inX = getX();
         int inY = getY();
         int inR = getRotation();
         walls[index+1] = new Wall(color,false,0);
         turn(90);
         move(length/2); 
         turn(-90);
         walls[index+1].setRotation(getRotation());
         getWorld().addObject(walls[index+1],getX(),getY());           
         setLocation(inX,inY);
         setRotation(inR);
         
         walls[index+2] = new Wall(color,false,0);
         turn(90);
         move(length/4);
         turn(-90);
         move(length/2);
         turn(90);
         walls[index+2].setRotation(this.getRotation());
         walls[index+2].getImage().scale(length/2,1);
         getWorld().addObject(walls[index+2],this.getX(),this.getY());
         setLocation(inX,inY);
         setRotation(inR);
         
         walls[index+3] = new Wall(color,false,0);
         turn(90);
         move(length/4);
         turn(-90);
         move(-length/2);
         turn(90);
         walls[index+3].setRotation(this.getRotation());
         walls[index+3].getImage().scale(length/2,1);
         getWorld().addObject(walls[index+3],this.getX(),this.getY());
         setLocation(inX,inY);
         setRotation(inR);
    }
     */   
                

         
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
