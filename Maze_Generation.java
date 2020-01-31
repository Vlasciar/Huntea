import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Maze_Generation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Maze_Generation extends Actor
{
    /**
     * Act - do whatever the Maze_Generation wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }  
    static void generate_maze()
    {
        for(int row = 0;row<34;row++)
        {
            for(int col = 0;col<34;col++)
        {
            Wall_Matrix.Maze[row][col] = 1;
        }
        }
    }
}
