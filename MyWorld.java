import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class MyWorld extends World
{ 
    public MyWorld()
    {            
        super(69*13, 840, 1);//70-FOV 13-px/angle       
        prepare(); 
    }    
    int length = 13;//  px/angle 
    static boolean isGameOver=false;
    public void act()
    {   if(!isGameOver)
        {   
            background.setVolume(20);
        if(Greenfoot.isKeyDown("M"))
        {
            getBackground().setColor(Color.BLACK);            
            getBackground().fillRect(0, 0,getWidth(),getHeight());
            rays_draw();
            time.getImage().setTransparency(0);
        } 
        else{
            time.getImage().setTransparency(255);
            getBackground().setColor(Color.GRAY);            
            getBackground().fillRect(0, 0,getWidth(),getHeight()/2+15);
            getBackground().setColor(new Color(255,255,255));
            getBackground().fillRect(0, getWidth()/2-5,getWidth(),getHeight()/2);
            if(Greenfoot.mousePressed(null)==false)
            {
                rays_draw();
                inviz(); 
                for(int i=0;i<k;i++)
                {          
                    if(rays[i].color!=0)
                    render_wall(i,rays[i].color);
                }
        } 
        GreenfootImage image = new GreenfootImage(String.valueOf(Time.getMinutes()) +":" + String.valueOf(Time.getSeconds()), 90, Color.BLACK, null);
        getBackground().drawImage(image, time.getX() + 95, time.getY() -50);
    }
    }      
    else 
    {
        time.getImage().setTransparency(0);
        background.setVolume(0);
    }
    }    
    int imgSize = 64;
    int mazeTexture;
    static int FOV=35;//half of the fov    
    Ray[] rays = new Ray[361];//max fov 360
    Wall[] walls = Wall_Matrix.walls; 
    Main_Ray main_Ray = new Main_Ray();  
    Player pl = new Player(main_Ray);    
    int k=0;   
    Time time = new Time();
    GreenfootSound background = new GreenfootSound("Old_Castle_Ambience.mp3");
    private void prepare()
    {        
        background.play();
        background.setVolume(0);
        isGameOver=false;
        mazeTexture = (Greenfoot.getRandomNumber(14)+1)*2;
        Maze_Generation maze_gen = new Maze_Generation();
        addObject(maze_gen,1,1);
        Wall_Matrix wall_matrix = new Wall_Matrix();
        addObject(wall_matrix,1,1);
        addObject(pl,25,25);     

        rays_init(); 
        prep_bg();
        prep_image(mazeTexture-1);
        prep_image(mazeTexture); 
        
        addObject(time,90,50); 
        Time.isCounting=true;
        Time.Time = 0;
    }    

    public static BufferedImage cropImage(BufferedImage bufferedImage, int x, int y, int width, int height)
    {
        BufferedImage croppedImage = bufferedImage.getSubimage(x, y, width, height);
        return croppedImage;
    }
    GreenfootImage bg = new GreenfootImage("bg2.png");
    BufferedImage image;
    public void prep_bg()
    {       
        int imgWidth = bg.getWidth();//15
        File original = new File("images/bg2.png");        
        File[] cuts = new File[imgWidth/8+1];       
        try {
            image = ImageIO.read(original);            
            for(int i=0;i<imgWidth-8;i+=8)
            {
                cuts[i/8] = new File("images/bg2_seg/slice"+i+".png");
                BufferedImage sec = cropImage(image,i,0,8,bg.getHeight());
                ImageIO.write(sec, "png",cuts[i/8]);
            }  
        }
        catch(IOException e) {
            e.printStackTrace();                
        }
    }
    public void prep_image(int wall_index)
    {
        int imgWidth = imgSize;///(getWidth()/k+1);//15
        File original = new File("images/wall_"+wall_index+".png");        
        File[] cuts = new File[imgWidth];       
        try {
            BufferedImage image = ImageIO.read(original);            
            for(int i=0;i<imgWidth;i++)
            {
                cuts[i] = new File("images/wall_"+wall_index+"_seg/slice"+i+".png");
                BufferedImage sec = cropImage(image,i,0,1,imgSize);
                ImageIO.write(sec, "png",cuts[i]);
            }  
        }
        catch(IOException e) {
            e.printStackTrace();                
        }
        cuts = null;
        original = null;
    }
    int next_slice;
    int scl;     
    GreenfootImage fillwall;
    //int gap;
    private void render_wall(int i,int color)
    {                 
        int angle = main_Ray.getRotation()-rays[i].getRotation();
        double cos = Math.cos(Math.toRadians(angle));
        int record = (int)(cos*rays[i].record); 
        double diagonal = getWidth() / Math.cos(Math.toRadians(45));
        double height = ((double)getHeight()*22000/diagonal /(record));
        if(height>getHeight()*10) height=getHeight()*10;
         if(height<=0)height=1;
        getBackground().setColor(new Color(58, 58, 58));            
        getBackground().fillRect(i * length, 0,length,getHeight()/2+15);
        getBackground().setColor(new Color(114, 114, 114));
        getBackground().fillRect(i * length, getWidth()/2-15,length,getHeight()/2);
        
        int slice = (int)((rays[i].procent_hit*(imgSize)))%64;//0-img.Width  
        rays[i].slice = slice;
        int next_slice;
        int x;
        int gap = walls[rays[i].record_index].gap;        
        int texture = mazeTexture+color-2;
        if(texture>=150)
            texture = 255;
        if(i<k-1)// && rays[i].record_index==rays[i+1].record_index)
        {                
            next_slice = (int)((rays[i+1].procent_hit*(imgSize)))%64;//0-img.Width                
            walls[rays[i].record_index].gap=Math.abs(rays[i].slice-next_slice)+1;      
            if(gap>13)gap=13;
            if(rays[i].slice<next_slice)
            {                   
                for(int z=0;z<gap;z++)
                {
                    if(rays[i].slice+z<imgSize)
                    {    
                        GreenfootImage fillwall=new GreenfootImage("wall_"+(texture)+"_seg/slice"+(rays[i].slice+z)+".png");                
                        fillwall.scale((13/gap+1), (int)height);                          
                        getBackground().drawImage(fillwall,i * length+z*(13/gap+1),getHeight()/2-(int)height/2);
                    }
                }
            }
            else if(rays[i].slice>next_slice)  
            {
                for(int z=gap-1;z>=0;z--)
                {
                    if(rays[i].slice-gap<0)gap=rays[i].slice;
                    if(rays[i].slice-z>0 )
                    {                         
                        GreenfootImage fillwall=new GreenfootImage("wall_"+(texture)+"_seg/slice"+(rays[i].slice-z)+".png");                
                        fillwall.scale((13/gap+1), (int)height);                          
                        getBackground().drawImage(fillwall,i * length+z*(13/gap+1),getHeight()/2-(int)height/2);
                    }
                }
            }
            else if(rays[i].slice==next_slice) 
            {
                GreenfootImage fillwall=new GreenfootImage("wall_"+(texture)+"_seg/slice"+(rays[i].slice)+".png");                
                fillwall.scale((13), (int)height);
                getBackground().drawImage(fillwall,i * length,getHeight()/2-(int)height/2);
            }
        }
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

    private void rays_draw()////act////
    {
        k=0;
        rays[0].check_ray_draw();
        rays[FOV*2].check_ray_draw();
        for(int i=main_Ray.getRotation()-FOV;i<=main_Ray.getRotation()+FOV;i++,k++)
        { 
            rays[k].setLocation(pl.getX(),pl.getY());
            rays[k].setRotation(main_Ray.getRotation()-FOV+k);
            rays[k].drawn=false;
        }
    }   

public void inviz()
    {
        main_Ray.getImage().setTransparency(0);
        pl.getImage().setTransparency(0);
        for(int i=0;i<rays.length;i++){
            if(rays[i]!=null)
                rays[i].getImage().setTransparency(0);
        }
    }
}
