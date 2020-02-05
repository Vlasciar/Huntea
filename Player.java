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
        if(!Greenfoot.isKeyDown("M"))
            Movement();
    }
    int speed = 1;
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
                setRotation(getRotation()-45);
                if(!try_move_up())
                {
                    try_move_right();
                }
            }
            else
            {
            setRotation(getRotation()-45);
        }
        }////////////////////
        else if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D")) &&
        (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S"))) {
            setRotation(getRotation()+135);
            move(speed);
            if(check_move()==false)
            {
                move(-speed);
                setRotation(getRotation()-135);
                if(!try_move_down())
                {
                    try_move_right();
                }
            }
            else
            {
            setRotation(getRotation()-135);
            }
        }///////////////////////////////
        else if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A")) &&
        (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W"))) {           
            setRotation(getRotation()-45);
            move(speed);
            if(check_move()==false)
            {
                move(-speed);
                setRotation(getRotation()+45);
                if(!try_move_up())
                {
                    try_move_left();
                }
            }
            else
            {
            setRotation(getRotation()+45);
            }
        }        /////////////////
        else if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A")) &&
        (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S"))) {
            setRotation(getRotation()-135);
            move(speed);
            if(check_move()==false)
            {
                move(-speed);
                setRotation(getRotation()+135);
                if(!try_move_down())
                {
                    try_move_left();
                }
            }
            else
            {
            setRotation(getRotation()+135);
            }
        }        ////////////////
        else if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D"))) {
            try_move_right();
        }        ////////////////////////
        else if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A"))) {
            try_move_left();
        }        /////////////////////////
        else if ((Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W"))) {
            try_move_up();
        }        ////////////////////////
        else if ((Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S"))) {
            try_move_down();
        }
    }

    private boolean check_move() //coliziuni cu obstacolele
    {
        Actor prop = getOneIntersectingObject(Props.class);
        if(isAtEdge()==true){
            return false; 
        }
        if (prop != null ) 
        { 
            if(prop.getClass()==Wall.class)
            { 
                Wall wall=(Wall) prop;
                if(wall.solid==false)
                    return true;
            }
            return false; 
        }
        else {
            return true;
        }        
    }

    private boolean try_move_right()
    {
        setRotation(getRotation()+90);
        move(speed);
        if(check_move()==false)
        {
            move(-speed);
            return false;
        }
        setRotation(getRotation()-90);
        return true;
    }

    private boolean try_move_left()
    {
        setRotation(getRotation()-90);
        move(speed);
        if(check_move()==false)
        {
            move(-speed);
            return false;
        }
        setRotation(getRotation()+90);
        return true;
    }

    private boolean try_move_up()
    {
        move(speed);
        if(check_move()==false)
        {
            move(-speed);
            return false;
        }
        return true;
    }

    private boolean try_move_down()
    {
        move(-speed);
        if(check_move()==false)
        {
            move(speed);
            return false;
        }
        return true;
    }
}