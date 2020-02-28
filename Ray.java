
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Ray extends Actor
{
    //detecteaza ce este in fata jucatorului pt a desena segmentele care creaza iluzia 3d
    
    
    double x3,y3;//position
    double x4,y4;//direction  
    int angle;
    boolean drawn;
    double sin;
    double cos;
    
    double alaturata;
    double opusa;   
    public void act() 
    {     
        coordonates();
        Walls();
    }   

    public void coordonates()
    { //informatii despre raza       
        angle = getRotation();
        sin = Math.sin(Math.toRadians(angle));//0
        cos = Math.cos(Math.toRadians(angle));//1

        alaturata = cos*(1000);
        opusa = sin*(1000);

        x3 = getX();
        y3 = getY();
        x4 = getX() + alaturata;
        y4 = getY() + opusa;                  
    }    
    double record = 9999999;
    int color;
    double procent_hit;
    int record_index;
    int slice;
    //int gap;
    Wall[] walls = Wall_Matrix.walls;
    double record_pt_X=0;
    double record_pt_Y=0;
    public void Walls()
    { //calculeaza cel mai apropiat zid si punctul de contact cu acesta            
        int k=0;
        record = 9999999;
        double distance = 0;
        record_pt_X=0;
        record_pt_Y=0;
        double pt_X,pt_Y;
        while(walls[k]!=null)
        {
            double t;
            double u;
            double x1 = walls[k].x1;
            double y1 = walls[k].y1;
            double x2 = walls[k].x2;
            double y2 = walls[k].y2;            

            double den = (x1-x2)*(y3-y4)-(y1-y2)*(x3-x4);
            t = ((x1-x3)*(y3-y4)-(y1-y3)*(x3-x4))/den;
            u = ((x1-x2)*(y1-y3)-(y1-y2)*(x1-x3))/den;

            if(t>=0 && t<=1 && u<=0){
                pt_X = x1+t*(x2-x1);
                pt_Y = y1+t*(y2-y1);
                distance = Math.sqrt((x3-pt_X)*(x3-pt_X)+(y3-pt_Y)*(y3-pt_Y));
                if(distance < record) {
                    procent_hit = Math.sqrt(Math.pow((pt_X-x1),2)+Math.pow((pt_Y-y1),2))/walls[k].length;                     
                    record_index=k;
                    color = walls[k].color;
                    record = distance;
                    record_pt_X = pt_X;
                    record_pt_Y = pt_Y;
                }
            }
            k++;             
        }  
        walls[record_index].discovered = true;                     
    }   
    public void check_ray_draw()
    {
        if(color == 255 && record<20)
        {
        TransitionOut transition = new TransitionOut();
        getWorld().addObject(transition,1,1);
        }
        if(Greenfoot.isKeyDown("M"))
        {
        getWorld().getBackground().setColor(Color.WHITE);
        getWorld().getBackground().drawLine((int) x3, (int) y3, (int) record_pt_X, (int) record_pt_Y);   
    }
    }
}
