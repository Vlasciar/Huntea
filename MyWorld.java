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

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Player player = new Player();
        addObject(player,586,327);
        Wall_Oriz wall_Oriz = new Wall_Oriz();
        addObject(wall_Oriz,321,112);
        Wall_Oriz wall_Oriz2 = new Wall_Oriz();
        addObject(wall_Oriz2,776,104);
        Wall_Oriz wall_Oriz3 = new Wall_Oriz();
        addObject(wall_Oriz3,759,529);
        Wall_Oriz wall_Oriz4 = new Wall_Oriz();
        addObject(wall_Oriz4,302,513);
        Wall_Vert wall_Vert = new Wall_Vert();
        addObject(wall_Vert,256,459);
        Wall_Vert wall_Vert2 = new Wall_Vert();
        addObject(wall_Vert2,270,149);
        Wall_Vert wall_Vert3 = new Wall_Vert();
        addObject(wall_Vert3,820,148);
        Wall_Vert wall_Vert4 = new Wall_Vert();
        addObject(wall_Vert4,806,483);
    }
}
