import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class TransitionOut here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TransitionOut extends Actor
{
    /**
     * Act - do whatever the TransitionOut wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */ 
    double raySpeeds[];
    double raySize[];
    int size;
    protected void addedToWorld(World world)
    {
        Time.isCounting = false; 
        MyWorld.isGameOver = true;
        size = getWorld().getWidth();
        raySpeeds = new double[size];
        raySize = new double[size];     
        for(int i=0;i<size;i++)
        {
            raySpeeds[i] = ((double)Greenfoot.getRandomNumber​(20))+10;
            raySize[i]=0;
        }
    }
    int k=0;
    public void act()
    {               
        int done = size/8;//how many seg are in place
        if(k%2==0)
        for(int i=0;i<size/8+1;i++)
        {   
            int length = Greenfoot.getRandomNumber(10);
            GreenfootImage seg = new GreenfootImage("bg2_seg/slice"+i*8 + ".png");
            if(-getWorld().getHeight()+(int)raySize[i]>0)
            {
                getWorld().getBackground().drawImage(seg, i*8, 0);
                done--;
                continue;
            }
            //getWorld().getBackground().fillRect(i*8, 0,8,(int)raySize[i]);           
            getWorld().getBackground().drawImage(seg, i*8, -getWorld().getHeight()+(int)raySize[i]);
            raySize[i]+=raySpeeds[i]; 
            seg = null;
        }
        k++;   
        if(done<=1)
        {
            Escaped w = new Escaped();
            Greenfoot.setWorld(w);
        }
    }
    
}
