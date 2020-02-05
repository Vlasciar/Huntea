import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Maze_Generation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Maze_Generation extends Actor
{ 
    protected void addedToWorld(World world)
    {     
        init_maze();
        Wall_Matrix.Maze[1][1] = 0;
        
        //Wall_Matrix.Maze[1][2] = 1;
        walls_unvisited[0][0] = 1;
        walls_unvisited[0][1] = 2;        
        walls_index++;
        
        //Wall_Matrix.Maze[2][1] = 1;
        walls_unvisited[1][0] = 2;
        walls_unvisited[1][1] = 1;
        walls_index++;

        while(walls_index>0)
        {     
            int next_visit = Greenfoot.getRandomNumber(walls_index);
            if(!isEdge(next_visit))
            check_neighbors(next_visit);
            remove_wall(next_visit);
        }     
    }

    private void init_maze()
    {
        for(int row = 0;row<32;row++)
        {
            for(int col = 0;col<34;col++)
            {
                if(col==0 || row == 0 || col==34 || row == 32 || row%2==0||col%2==0)
                    Wall_Matrix.Maze[row][col] = 1;
                else
                    Wall_Matrix.Maze[row][col] = 999;//unvisited
            } 
        }        
    }
    int[][] walls_unvisited = new int[1023][2];
    int walls_index=0;
    private void remove_wall(int to_remove)
    {
        for(int i=to_remove;i<walls_index;i++)
        {
            walls_unvisited[i][0] = walls_unvisited[i+1][0];
            walls_unvisited[i][1] = walls_unvisited[i+1][1];
        }
        walls_index--;
    }
    private boolean isEdge(int check_index)
    {
        int c_row = walls_unvisited[check_index][0];
        int c_col= walls_unvisited[check_index][1];
        if(c_col==0 || c_row == 0 || c_col==34 || c_row == 32)
            return true;
        else return false;    
    }
    private void check_neighbors(int check_index)
    {
        int c_row = walls_unvisited[check_index][0];
        int c_col= walls_unvisited[check_index][1];
        if(Wall_Matrix.Maze[c_row][c_col+1] > 100 && Wall_Matrix.Maze[c_row][c_col-1] == 0)
        {
            Wall_Matrix.Maze[c_row][c_col] = 0;
            Wall_Matrix.Maze[c_row][c_col+1] = 0;
            add_neighbors(c_row,c_col+1);
        }
        else if(Wall_Matrix.Maze[c_row][c_col-1] > 100 && Wall_Matrix.Maze[c_row][c_col+1] == 0)
        {
            Wall_Matrix.Maze[c_row][c_col] = 0;
            Wall_Matrix.Maze[c_row][c_col-1] = 0;
            add_neighbors(c_row,c_col-1);
        }
        else if(Wall_Matrix.Maze[c_row+1][c_col] > 100 && Wall_Matrix.Maze[c_row-1][c_col] == 0)
        {
            Wall_Matrix.Maze[c_row][c_col] = 0;
            Wall_Matrix.Maze[c_row+1][c_col] = 0;
            add_neighbors(c_row+1,c_col);
        }
        else if(Wall_Matrix.Maze[c_row-1][c_col] > 100 && Wall_Matrix.Maze[c_row+1][c_col] == 0)
        {
            Wall_Matrix.Maze[c_row][c_col] = 0;
            Wall_Matrix.Maze[c_row-1][c_col] = 0;
            add_neighbors(c_row-1,c_col);
        }
    }

    private void add_neighbors(int row, int col)
    {   
        if(Wall_Matrix.Maze[row][col+1] > 0)
        {                
            walls_unvisited[walls_index][0]=row;
            walls_unvisited[walls_index][1]=col+1;
            walls_index++;
        }
        if(Wall_Matrix.Maze[row][col-1] > 0)
        {                
            walls_unvisited[walls_index][0]=row;
            walls_unvisited[walls_index][1]=col-1;
            walls_index++;
        }
        if(Wall_Matrix.Maze[row+1][col] > 0)
        {                
            walls_unvisited[walls_index][0]=row+1;
            walls_unvisited[walls_index][1]=col;
            walls_index++;
        }
        if(Wall_Matrix.Maze[row-1][col] > 0)
        {                
            walls_unvisited[walls_index][0]=row-1;
            walls_unvisited[walls_index][1]=col;
            walls_index++;
        }

    }
}
