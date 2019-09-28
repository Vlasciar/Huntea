import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor {
    public Player(Main_Ray r)
    {
        ray = r;
    }
    Main_Ray ray;
    public void act() {
        setRotation(ray.getRotation());
        ray.setLocation(getX(),getY());
        Movement();
    }
    int speed = 2;
    int pX,pY; //player x,y    
    private void Movement() {
        pX = getX();
        pY = getY();

        if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D")) &&
        (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W"))) {
            setRotation(getRotation()+45);
            move(speed);
            if(check_move()==false)
            {
                move(-speed);
            }
            setRotation(getRotation()-45);
        }////////////////////
        else if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D")) &&
        (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S"))) {
            setRotation(getRotation()+135);
            move(speed);
            if(check_move()==false)
            {
                move(-speed);
            }
            setRotation(getRotation()-135);
        }///////////////////////////////
        else if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A")) &&
        (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W"))) {
            setRotation(getRotation()-45);
            move(speed);
            if(check_move()==false)
            {
                move(-speed);
            }
            setRotation(getRotation()+45);
        }        /////////////////
        else if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A")) &&
        (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S"))) {
            setRotation(getRotation()-135);
            move(speed);
            if(check_move()==false)
            {
                move(-speed);
            }
            setRotation(getRotation()+135);
        }        ////////////////
        else if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D"))) {
            setRotation(getRotation()+90);
            move(speed);
            if(check_move()==false)
            {
                move(-speed);
            }
            setRotation(getRotation()-90);
        }        ////////////////////////
        else if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A"))) {
            setRotation(getRotation()-90);
            move(speed);
            if(check_move()==false)
            {
                move(-speed);
            }
            setRotation(getRotation()+90);
        }        /////////////////////////
        else if ((Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W"))) {
            move(speed);
            if(check_move()==false)
            {
                move(-speed);
            }
        }        ////////////////////////
        else if ((Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S"))) {
            move(-speed);
            if(check_move()==false)
            {
                move(speed);
            }
        }
    }

    private boolean check_move() //coliziuni cu obstacolele
    {
        Actor prop = getOneIntersectingObject(Props.class);
        if (prop != null || isAtEdge()==true)            
            return false;
        else {
            return true;
        }

    }
}