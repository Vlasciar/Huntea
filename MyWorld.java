import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1080, 675, 1); 
        prepare();
    }
    public void act()
    {
        
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    Player pl = new Player();
    private void prepare()
    {
        
        addObject(pl,586,327);
        rays();
          
        
        
        Wall_Vert wall_Vert = new Wall_Vert();
        addObject(wall_Vert,274,157);
        Wall_Vert wall_Vert2 = new Wall_Vert();
        addObject(wall_Vert2,741,478);
        Wall_Vert wall_Vert3 = new Wall_Vert();
        addObject(wall_Vert3,750,216);
        Wall_Vert wall_Vert4 = new Wall_Vert();
        addObject(wall_Vert4,407,214);
        Wall_Vert wall_Vert5 = new Wall_Vert();
        addObject(wall_Vert5,407,454);
        wall_Vert.setLocation(286,183);
        removeObject(wall_Vert);
        Wall_Oriz wall_Oriz = new Wall_Oriz();
        addObject(wall_Oriz,452,164);
        Wall_Oriz wall_Oriz2 = new Wall_Oriz();
        addObject(wall_Oriz2,703,167);
        Wall_Oriz wall_Oriz3 = new Wall_Oriz();
        addObject(wall_Oriz3,698,518);
        Wall_Oriz wall_Oriz4 = new Wall_Oriz();
        addObject(wall_Oriz4,451,497);
        
    }
    Sec_Ray[] sec_rays;
    private void rays()
    {
        Main_Ray main_Ray = new Main_Ray(pl);
        addObject(main_Ray,pl.getX(),pl.getY());
        
        for(int i=-100;i<=100;i+=20)
        {
            if(i!=0)
            {
            Sec_Ray sec_Ray = new Sec_Ray(main_Ray,i);
            addObject(sec_Ray,pl.getX(),pl.getY());
        }
        }
        
        
    }
}
