import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Time here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Time extends UI
{
    static int Time = 0;
    static boolean isCounting;
    int k;
    public void act() 
    {
        k++;
        if(isCounting)
        {
            if(k%60==0)
            Time++;
        }
    }   
    static public int getMinutes()
    {
        return (Time/60);
    }
    static public int getSeconds()
    {
        return (Time%60);
    }
}
