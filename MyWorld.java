import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
public class MyWorld extends World
{
    boolean twoD = false;    
    public MyWorld()
    {            
        super(70*13, 850, 1);//70-FOV 13-px/angle       
        prepare();
        if(!twoD){inviz2d();}
    }    

    public void act()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        setBackground("bg.png");
        getBackground().setColor(Color.BLACK);            
        getBackground().fillRect(0, 0,getWidth(),getHeight()/2+15);
        getBackground().setColor(Color.GRAY);
        getBackground().fillRect(0, getWidth()/2-15,getWidth(),getHeight()/2);
        if(Greenfoot.mousePressed(null)==false)
        {
            rays_draw();
            render_wall();
        }       
    }    
    int FOV=35;//half of the fov    
    Ray[] rays = new Ray[361];//max fov 360
    Wall[] walls = new Wall[255]; 
    Main_Ray main_Ray = new Main_Ray();  
    Player pl = new Player(main_Ray);    
    int k=0;
    private void prepare()
    {
        addObject(pl,586,327);

        rays_init(); 
        prep_image();
        prep_edge_walls();
        prep_walls();

    }    

    public static BufferedImage cropImage(BufferedImage bufferedImage, int x, int y, int width, int height)
    {
        BufferedImage croppedImage = bufferedImage.getSubimage(x, y, width, height);
        return croppedImage;
    }
    //Wall[] walls = new Wall[255];
    GreenfootImage img = new GreenfootImage("wallB.png");
    public void prep_image()
    {
        int length = img.getWidth();///(getWidth()/k+1);//15
        File original = new File("images/wallB.png");        
        File[] cuts = new File[length];       
        try {
            BufferedImage image = ImageIO.read(original);            
            for(int i=0;i<length;i++)
            {
                cuts[i] = new File("images/swa_seg/swa"+i+".png");
                BufferedImage sec = cropImage(image,i,0,1,img.getHeight());
                ImageIO.write(sec, "png",cuts[i]);
            }  
        }
        catch(IOException e) {
            e.printStackTrace();                
        }
    }
    int last_slice;
    int scl=13;
    private void render_wall()
    {
        int length = 13;
        double height=0;           
        for(int i=0;i<k;i++)
        {           
            int angle = main_Ray.getRotation()-rays[i].getRotation();
            double cos = Math.cos(Math.toRadians(angle));
            int record = (int)(cos*rays[i].record); 
            double diagonal = getWidth() / Math.cos(Math.toRadians(45));
            height = ((double)getHeight()/diagonal /record)*100000;

            int color = rays[i].color; 

            switch(color) {
                case 1:      
                int slice=0;

                slice = (int)((rays[i].procent_hit*img.getWidth())/100);//0-imgW
                GreenfootImage Tswa=new GreenfootImage("swa_seg/swa"+slice+".png");
                
                showText(String.valueOf(last_slice), 200, 200);
                showText(String.valueOf(slice), 200, 220);
                Tswa.scale(scl, (int)height);               
                getBackground().drawImage(Tswa,i * length,getHeight()/2-(int)height/2);
               
                if(Math.abs(slice-last_slice)>1 && Math.abs(slice-last_slice)<13)
                {
                    int scl= length/Math.abs(slice-last_slice);
                    int offset=scl;
                    
                    if(slice>last_slice)
                        for(int s=slice-1;s>last_slice;s--)
                        {                        
                            Tswa=new GreenfootImage("swa_seg/swa"+s+".png");
                            Tswa.scale(scl, (int)height);           
                            getBackground().drawImage(Tswa,i * length-offset,getHeight()/2-(int)height/2);
                            offset+=scl;
                        }
                    else for(int s=last_slice-1;s>slice;s--)
                        {                        
                            Tswa=new GreenfootImage("swa_seg/swa"+s+".png");
                            Tswa.scale(scl, (int)height);           
                            getBackground().drawImage(Tswa,i * length-offset,getHeight()/2-(int)height/2);
                            offset+=scl;
                        }
                }                
                 last_slice = slice;
                /*getBackground().setColor(Color.WHITE);
                getBackground().fillRect(i * length, getHeight()/2-(int)height/2,length,(int)height);               
                 */
                break;

                case 0:
                getBackground().setColor(Color.BLUE);
                getBackground().fillRect(i * length, getHeight()/2-(int)height/2,length,(int)height);                
                break;

                case 2:
                getBackground().setColor(Color.GREEN);
                getBackground().fillRect(i * length, getHeight()/2-(int)height/2,length,(int)height);
                break;
                case 3:
                getBackground().setColor(Color.RED);
                getBackground().fillRect(i * length, getHeight()/2-(int)height/2,length,(int)height);
                break;

                case 4:
                getBackground().setColor(Color.YELLOW);
                getBackground().fillRect(i * length, getHeight()/2-(int)height/2,length,(int)height);
                break;
                default:
            }
            //getBackground().drawImage(new GreenfootImage("swa.png"),1,1);

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
        for(int i=main_Ray.getRotation()-FOV;i<=main_Ray.getRotation()+FOV;i++,k++)
        { 
            rays[k].setLocation(pl.getX(),pl.getY());
            rays[k].setRotation(main_Ray.getRotation()-FOV+k);
        }
    }

    public void prep_walls()
    {
        int w=4;       
        walls[w] = new Wall(1,true,w);
        walls[w].setRotation(0);
        addObject(walls[w],100,200);    
        w+=4;        
        walls[w] = new Wall(2,true,w);
        walls[w].setRotation(25);
        addObject(walls[w],260,216);
        w+=4;
        walls[w] = new Wall(3,true,w);
        walls[w].setRotation(45);
        addObject(walls[w],770,116);        
        w+=4;
        walls[w] = new Wall(4,true,w);
        walls[w].setRotation(40);
        addObject(walls[w],270,600);
        w+=4;
        walls[w] = new Wall(1,true,w);
        walls[w].setRotation(180);
        addObject(walls[w],870,416);
        w+=4;
        walls[w] = new Wall(2,true,w);
        walls[w].setRotation(20);
        addObject(walls[w],470,416);
        w+=4;
        walls[w] = new Wall(3,true,w);
        walls[w].setRotation(300);
        addObject(walls[w],670,316);
    }

    public void prep_edge_walls()
    {
        int w=0; 
        int W=getWidth();
        int H=getHeight();
        walls[w] = new Wall(0,true,w);
        walls[w].setRotation(0);
        walls[w].edgeWall=true;
        addObject(walls[w],0,0); 
        walls[w].x1=0;walls[w].y1=0;walls[w].x2=W;walls[w].y2=0;
        w++;        
        walls[w] = new Wall(0,true,w);
        walls[w].setRotation(0);
        walls[w].edgeWall=true;
        addObject(walls[w],0,0);
        walls[w].x1=0;walls[w].y1=H;walls[w].x2=W;walls[w].y2=H;
        w++;
        walls[w] = new Wall(0,true,w);
        walls[w].setRotation(90);
        walls[w].edgeWall=true;
        addObject(walls[w],0,0); 
        walls[w].x1=0;walls[w].y1=0;walls[w].x2=0;walls[w].y2=H;
        w++;
        walls[w] = new Wall(0,true,w);
        walls[w].setRotation(90);
        walls[w].edgeWall=true;
        addObject(walls[w],0,0);
        walls[w].x1=W;walls[w].y1=0;walls[w].x2=W;walls[w].y2=H;

    }

    public void inviz2d()
    {
        for(int i=0;i<walls.length;i++){
            if(walls[i]!=null)
                walls[i].getImage().setTransparency(0);
        }
        main_Ray.getImage().setTransparency(0);
        pl.getImage().setTransparency(0);
        for(int i=0;i<rays.length;i++){
            if(rays[i]!=null)
                rays[i].getImage().setTransparency(0);
        }
    }
}
