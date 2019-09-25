import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(70*13, 850, 1); 
        prepare();
    }
    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        setBackground("bg.png");
        if(Greenfoot.mousePressed(null)==false)
        {
        rays_draw();
        render_wall();
       }       
    }    
    int FOV=35;//half of the fov
    Player pl = new Player();
    Ray[] rays = new Ray[361];//max fov 360
    Wall[] walls = new Wall[255];
    Main_Ray main_Ray = new Main_Ray(pl);
    int k=0;
    private void prepare()
    {
        addObject(pl,586,327);
        rays_init();   
        prep_edge_walls();
        prep_walls();
    }    
    private void rays_init()
    {       
        addObject(main_Ray,pl.getX(),pl.getY()); 
        k=0;
        for(int i=main_Ray.getRotation()-FOV;i<=main_Ray.getRotation()+FOV;i++,k++)
        {            
            rays[k] = new Ray();
            addObject(rays[k],pl.getX(),pl.getY());            
            //showText(String.valueOf(k), 600, 300);
        }        
    } 
    private void render_wall()
    {
        int length = getWidth()/k+1;
        double height=0;
        
        for(int i=0;i<k;i++)
        {
            int record;
            int angle = main_Ray.getRotation()-rays[i].getRotation();
            double cos = Math.cos(Math.toRadians(angle));
            record = (int)(cos*rays[i].record); 
            double diagonal = getWidth() / Math.cos(Math.toRadians(45));
            height = ((double)getHeight()/diagonal *record);
            int color = rays[i].color;
            if(color==0)
            {
                getBackground().setColor(Color.WHITE);
            }
            else if(color==1)
            {
                getBackground().setColor(Color.BLUE);
            }
            else if(color==2)
            {
                getBackground().setColor(Color.GREEN);
            }
            else if(color==3)
            {
                getBackground().setColor(Color.RED);
            }
            else if(color==4)
            {
                getBackground().setColor(Color.YELLOW);
            } 
            
            getBackground().fillRect(i * length, 0,length,getHeight());
            
            getBackground().setColor(Color.BLACK);            
            getBackground().fillRect(i * length, 0,length,(int)height/2);
            getBackground().setColor(Color.GRAY);
            getBackground().fillRect(i * length, getHeight()-(int)height/2,length,(int)height/2);            
            //showText(String.valueOf(height), 200, 200);
        }
    }
    private void rays_draw()////act////
    {
        k=0;
        for(int i=main_Ray.getRotation()-FOV;i<=main_Ray.getRotation()+FOV;i++,k++)
        { 
        rays[k].setLocation(main_Ray.player.getX(),main_Ray.player.getY());
        rays[k].setRotation(main_Ray.getRotation()-FOV+k);
     }
    }
    public void prep_walls()
    {
        int w=4;       
        walls[w] = new Wall(1);
        walls[w].setRotation(0);
        addObject(walls[w],100,200);    
        w++;        
        walls[w] = new Wall(2);
        walls[w].setRotation(25);
        addObject(walls[w],260,216);
        w++;
        walls[w] = new Wall(3);
        walls[w].setRotation(45);
        addObject(walls[w],770,116);        
        w++;
        walls[w] = new Wall(4);
        walls[w].setRotation(40);
        addObject(walls[w],270,600);
        w++;
        walls[w] = new Wall(1);
        walls[w].setRotation(110);
        addObject(walls[w],870,416);
        w++;
        walls[w] = new Wall(2);
        walls[w].setRotation(20);
        addObject(walls[w],470,416);
        w++;
        walls[w] = new Wall(3);
        walls[w].setRotation(300);
        addObject(walls[w],670,316);
    }
    public void prep_edge_walls()
    {
        int w=0; 
        int W=getWidth();
        int H=getHeight();
        walls[w] = new Wall(0);
        walls[w].setRotation(0);
        addObject(walls[w],540,0); 
        walls[w].x1=0;walls[w].y1=0;walls[w].x2=W;walls[w].y2=0;
        w++;        
        walls[w] = new Wall(0);
        walls[w].setRotation(0);
        addObject(walls[w],540,675);
        walls[w].x1=0;walls[w].y1=H;walls[w].x2=W;walls[w].y2=H;
        w++;
        walls[w] = new Wall(0);
        walls[w].setRotation(90);
        addObject(walls[w],0,350); 
        walls[w].x1=0;walls[w].y1=0;walls[w].x2=0;walls[w].y2=H;
        w++;
        walls[w] = new Wall(0);
        walls[w].setRotation(90);
        addObject(walls[w],1080,350);
        walls[w].x1=W;walls[w].y1=0;walls[w].x2=W;walls[w].y2=H;
        
    }
}
