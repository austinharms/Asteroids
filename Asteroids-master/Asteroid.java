import greenfoot.*;

/**
 * A rock in space.
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 */
public class Asteroid extends SmoothMover
{
    //TODO (26): Declare an integer instance variable called size
    /** Size of this asteroid */
    private int size;

    //TODO (27): Declare an integer instance variable called stability
    /** When the stability reaches 0 the asteroid will explode */
    private int stability;

    /**
     * This is the default constructor for objects of type Asteroid
     * 
     * @param None There are no parameters
     * @return Nothing is returned
     */
    public Asteroid()
    {
        this(60);
    }

    /**
     * Asteroid is the constructor for objects of type Asteroid.
     * It allows customization of the size of the Asteroid
     * 
     * @param size represents the size of the Asteroid
     * @return An object of type Asteroid
     */
    public Asteroid(int size)
    {
        super(new Vector(Greenfoot.getRandomNumber(360), 2));

        //TODO (32): Delete this line and make a call to the setSize method instead
        setSize(size);
    }

    /**
     * Asteroid is the constructor for objects of type Asteroid.
     * It allows customization of the size of the Asteroid and
     * the speed and direction of the Asteroid
     * 
     * @param size represents the size of the Asteroid
     * @param velocity represents the speed and direction of the Asteroid
     * @return An object of type Asteroid
     */
    public Asteroid(int size, Vector velocity)
    {
        super(velocity);

        //TODO (33): Delete this line and make a call to the setSize method instead
        getImage().scale(size, size);
    }

    /**
     * act is the method that is run on every 
     * iteration of the act cycle
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void act()
    {         
        move();
    }


    /**
     * Method setSize sets the size of the astroid
     *
     * @param size A parameter used to set the size of an astroid
     * @return nothing is returned
     */
    private void setSize(int size)
    {
        stability = size;
        this.size = size;
        getImage().scale(size, size);
    }


    /**
     * Method hit damages the astroid the damage amount
     *
     * @param damage A parameter used to set how much damage happens to the astroid
     * @return nothing is returned
     */
    public void hit(int damage)
    {
        Space space = (Space) getWorld();
        stability = stability - damage;
        if(stability <= 0)
        {
            if(size >= 50)
            {
                space.countScore(15);
            }
            else if(size >= 25)
            {
                space.countScore(30);
            }
            else
            {
                space.countScore(50);
            }
            breakUp(Greenfoot.getRandomNumber(5));
        }
    }
    
    
    /**
     * Method breakUp gets rid of the astroid and splits it into mulitpul diffrent smaller pices
     *
     * @param numBreakUp A parameter use to say how many pices the astroid should break up into
     * @return nothing is returned
     */
    private void breakUp(int numBreakUp)
    {
        int rotation;
        double length;
        Vector speed;
        Asteroid debris;
        Greenfoot.playSound("Explosion.wav");
        if(size <= 15)
        {
            getWorld().removeObject(this);   
        }
        else
        {
            rotation = getVelocity().getDirection() + Greenfoot.getRandomNumber(45);
            length = getVelocity().getLength();
            for(int i = 0; i < numBreakUp; i++)
            {
                speed = new Vector(rotation + Greenfoot.getRandomNumber(201) - 100, length * 1.2);
                debris = new Asteroid(size/numBreakUp, speed);
                getWorld().addObject(debris, getX(), getY());
                debris.move();
            }
            getWorld().removeObject(this);
        }
    }
}
