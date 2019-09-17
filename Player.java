import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor {
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        Movement();
    }
    int speed_diagonal=3; //viteza pe diagonala
    int speed_normal=4;
    int pX; //player x
    int pY; //player y
    private void DoMove(int next_pX, int next_pY)//miscare diagonala
    {
        pX = getX();
        pY = getY();
        this.setLocation(next_pX, next_pY);
        if (check_move() == false) {
            this.setLocation(pX, pY);
        }
        if (getX() == pX && getY() == pY) {
            this.setLocation(next_pX, pY);
            if (check_move() == false) {
                this.setLocation(pX, pY);
            }
        }
        if (getX() == pX && getY() == pY) {
            this.setLocation(pX, next_pY);
            if (check_move() == false) {
                this.setLocation(pX, pY);
            }
        }
    }

    private void Movement() {
        pX = getX();
        pY = getY();
        if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D")) &&
        (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W"))) {
            // turnTowards(pX+1,pY-1);                     
            DoMove(pX+speed_diagonal,pY-speed_diagonal);
        }////////////////////
        else if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D")) &&
        (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S"))) {
            // turnTowards(pX+1,pY+1);            
            DoMove(pX+speed_diagonal,pY+speed_diagonal);
        }///////////////////////////////
        else if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A")) &&
        (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W"))) {
            // turnTowards(pX-1,pY-1);           
            DoMove(pX-speed_diagonal,pY-speed_diagonal);
        }        /////////////////
        else if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A")) &&
        (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S"))) {
            //  turnTowards(pX-1,pY+1);            
            DoMove(pX-speed_diagonal,pY+speed_diagonal);
        }        ////////////////
        else if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D"))) {
            //  turnTowards(pX+1,pY);         
            this.setLocation(pX + speed_normal, pY);
            if (check_move() == false) {
                this.setLocation(pX, pY);
            }
        }        ////////////////////////
        else if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A"))) {
            //   turnTowards(pX-1,pY);         
            this.setLocation(pX - speed_normal, pY);
            if (check_move() == false) {
                this.setLocation(pX, pY);
            }
        }        /////////////////////////
        else if ((Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W"))) {
            //   turnTowards(pX,pY-1);
            this.setLocation(pX, pY - speed_normal);
            if (check_move() == false) {
                this.setLocation(pX, pY);
            }
        }        ////////////////////////
        else if ((Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S"))) {
            //  turnTowards(pX,pY+1);         
            this.setLocation(pX, pY + speed_normal);
            if (check_move() == false) {
                this.setLocation(pX, pY);
            }
        }
    }

    private boolean check_move() //coliziuni cu obstacolele
    {
        Actor prop = getOneIntersectingObject(Props.class);
        if (prop == null)
            return true;
        else {
            return false;
        }

    }
}