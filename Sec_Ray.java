
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sec_Ray here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sec_Ray extends Actor
{
    Main_Ray ray;
    int T_offset;
    int relative_Y;
    int relative_X;
    public Sec_Ray(Main_Ray MR, int ost)
    {
        ray=MR;
        T_offset=ost;
    }
    int offset;

    public void act() 
    {        
        offset = T_offset;
        relative_X = ray.relative_X;
        relative_Y = ray.relative_Y;
        this.setLocation(ray.player.getX(),ray.player.getY());
        
        while(offset!=0)
        {
        if(relative_Y<=-100 )
        {
            relative_X += offset; 
            if(Math.abs(relative_X) > 100)
            {
                //offset = Math.abs(relative_X) - 100;              
                if(relative_X > 0) 
                {
                    offset = relative_X - 100;
                    relative_X = 100;
                }
                    else 
                    {
                        offset = relative_X + 100;
                        relative_X = -100;
                    }
            }
            else offset = 0;
        }
        if(relative_X>=100 )
        {            
            relative_Y += offset; 
            if(Math.abs(relative_Y) > 100)
            {
               // offset = Math.abs(relative_Y) - 100;
                
                if(relative_Y > 0) 
                {
                    offset = relative_Y - 100;
                    relative_Y = 100;
                }
                    else 
                    {
                        offset = relative_Y + 100;
                        relative_Y = -100;
                    }
            }
            else offset = 0;
        }
        if(relative_Y>=100 )
        {
            relative_X -= offset; 
            if(Math.abs(relative_X) > 100)
            {
              //  offset = Math.abs(relative_X) - 100;
                
                if(relative_X > 0) 
                {
                    offset = relative_X - 100;
                    relative_X = 100;
                }
                    else 
                    {
                        offset = relative_X + 100;
                        relative_X = -100;
                    }
            }  
            else offset = 0;
        }
        if(relative_X<=-100)
        {
            relative_Y -= offset; 
            if(Math.abs(relative_Y) > 100)
            {
               // offset = Math.abs(relative_Y) - 100;
                if(relative_Y > 0) 
                {
                    offset = relative_Y - 100;
                    relative_Y = 100;
                }
                    else 
                    {
                        offset = relative_Y + 100;
                        relative_Y = -100;
                    }
            }  
            else offset = 0;
        } 
    }
    turnTowards(ray.player.getX()+relative_X,ray.player.getY()+relative_Y);
    }    
}
