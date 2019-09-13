import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        Check_Movement();
    }  
    int speed=3;//viteza de miscare
    int pX;//player x
    int pY;//player y
    
    
    private void Check_Movement()
    {
       pX=getX();
       pY=getY(); 
       
       
       if((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D")) && 
       (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W"))){
         turnTowards(pX+1,pY-1);         
         if(check_move()==true)
         {
         this.setLocation(pX+speed,pY-speed);
        }
        if(check_move()==false)
        {
         this.setLocation(pX-speed,pY+speed);
        } 
        
        }
        
        /////////////////////////
         else if((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D")) 
         && (Greenfoot.isKeyDown("down")|| Greenfoot.isKeyDown("S")) ){
        turnTowards(pX+1,pY+1);
        if(check_move()==true)
         {
         this.setLocation(pX+speed,pY+speed);
        }
        if(check_move()==false)
        {
         this.setLocation(pX-speed,pY-speed);
        }
        }
        
        ///////////////////////////////
         else if((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A")) &&
          (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W"))){
         turnTowards(pX-1,pY-1);
         if(check_move()==true)
         {
         this.setLocation(pX-speed,pY-speed);
        }
        if(check_move()==false)
        {
         this.setLocation(pX+speed,pY+speed);
        }
        }
        
        /////////////////
        else if((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A"))&&
          (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S"))){
         turnTowards(pX-1,pY+1);
         if(check_move()==true)
         {
         this.setLocation(pX-speed,pY+speed);
        }
        if(check_move()==false)
        {
         this.setLocation(pX+speed,pY-speed);
        }
        }
         
       ////////////////
        else if((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D"))){
         turnTowards(pX+1,pY);         
        if(check_move()==true)
         {
         this.setLocation(pX+speed,pY);
        }
        if(check_move()==false)
        {
         this.setLocation(pX-speed,pY);
        }
        }
        
        ////////////////////////
        else if((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A"))){
         turnTowards(pX-1,pY);         
         if(check_move()==true)
         {
         this.setLocation(pX-speed,pY);
        }
        if(check_move()==false)
        {
         this.setLocation(pX+speed,pY);
        }        
        }
        
        /////////////////////////
        else if((Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W")))
        {
         turnTowards(pX,pY-1);
        if(check_move()==true)
        {
         this.setLocation(pX,pY-speed);
        }
        if(check_move()==false)
        {
         this.setLocation(pX,pY+speed);
        }
        }
        
        ////////////////////////
        else if((Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S"))){ 
         turnTowards(pX,pY+1);         
         if(check_move()==true)
         {
         this.setLocation(pX,pY+speed);
        }
        if(check_move()==false)
        {
         this.setLocation(pX,pY-speed);
        }
        } 
    }
    public boolean check_move()//coliziuni cu obstacolele
     {
         Actor props = getOneIntersectingObject(Props.class);

         if(props == null)       
            return true;
            else {
                return false;
            }
        
            
        
     }
}
