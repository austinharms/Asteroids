import greenfoot.*;

/**
 * A rocket that can be controlled by the arrowkeys: up, left, right.
 * The gun is fired by hitting the 'space' key. 'z' releases a proton wave.
 * 
 * @author Poul Henriksen
 * @author Michael Kölling
 * 
 * @version 1.1
 */
public class Rocket extends SmoothMover
{
    //TODO (91): Declare a static integer instance constant called GUN_RELOAD_TIME initialized to 10
    private final int GUN_RELOAD_TIME = 10;
    //TODO (92): Declare a static integer instance constant called WAVE_RELOAD_TIME initialized to 500
    private final int WAVE_RELOAD_TIME = 500;
    //TODO (93): Declare an integer instance variable called reloadDelayCount
    private int reloadDelayCount;
    //TODO (94): Declare an integer instance variable called waveDelayCount
    private int waveDelayCount;
    /**
     * Rocket is the constructor for objects of type Rocket
     * 
     * @param None There are not parameters
     * @return Nothing is returned
     */
    public Rocket()
    {

        Vector startMotion = new Vector(getRotation(), 0.7);
        addToVelocity(startMotion);

        //TODO (95): Initialize reloadDelayCount to 10
        reloadDelayCount = 10;
        //TODO (96): Initialize waveDelayCount to 500
        waveDelayCount = 500;
    }

    /**
     * Do what a rocket's gotta do. (Which is: mostly flying about, and turning,
     * accelerating and shooting when the right keys are pressed.)
     */
    public void act()
    {
        //TODO (113): Remove the two slashes in front of this line of code
        checkWin();

        move();

        //TODO (25): Make a call to the method that checks if the user has pressed keys
        checkKeys();
        //TODO (77): Make a call to the method that checks if the user has collided with an asteroid
        checkCollision();
        //TODO (97): Increase reloadDelayCount and waveDelayCount by 1 each
        reloadDelayCount++;
        waveDelayCount++;
    }

    /**
     * checkWin checks to see if there are no more asteroids in the world,
     * which means that the game has been won
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void checkWin()
    {
        Space world = (Space)getWorld();

        if( world.getObjects(Asteroid.class).size() == 0 )
        {
            world.gameWon();
        }
    }

    
    /**
     * Method checkKeys
     *
     *@param nothing there are no parameters
     *@return nothing is returned
     */
    private void checkKeys()
    {
        if(Greenfoot.isKeyDown("space"))
        {
            fire();
        }
        
        if(Greenfoot.isKeyDown("left"))
        {
            turn(-5);
        }
        
        if(Greenfoot.isKeyDown("right"))
        {
            turn(5);
        }

        if(Greenfoot.isKeyDown("z"))
        {
            startProtonWave();
        }
        ignite(Greenfoot.isKeyDown("up"));
    }

    
    /**
     * Method fire fires a bullet 
     *
     *@param nothing there are no prameters
     *@return nothing is returned
     */
    private void fire()
    {
        Bullet bullet = new Bullet(getVelocity(), getRotation());
        if( reloadDelayCount >= GUN_RELOAD_TIME)
        {
            getWorld().addObject(bullet, getX(), getY());
            bullet.move();
            reloadDelayCount = 0;
        }
    }
    
    
    /**
     * Method startProtonWave adds a proton wave object at the rockets location
     * and sets the waveDelayCount to 0
     *
     *@param nothing there are no parameters
     *@return nothing is returned
     */
    private void startProtonWave()
    {
        ProtonWave wave = new ProtonWave();
        if(waveDelayCount >= WAVE_RELOAD_TIME)
        {
            getWorld().addObject(wave, getX(), getY());
            waveDelayCount = 0;
        }
    }
    
    
    /**
     * Method ignite sets the image for the rocket based on the boosterOn parameter
     *
     * @param boosterOn A parameter used to set the proper option for the rocket image
     * @return nothing is returned
     */
    private void ignite(boolean boosterOn)
    {
        GreenfootImage rocket = new GreenfootImage("rocket.png");
        GreenfootImage rocketWithThrust = new GreenfootImage("rocketWithThrust.png");
        if(boosterOn == true)
        {
            setImage(rocketWithThrust);
            addToVelocity(new Vector(getRotation(), 0.3));
        }
        else
        {
            setImage(rocket); 
        }
    }

    
    
    /**
     * Method checkCollision cheks if the rocket has collied with an astroid
     * and if it has it adds an explosion at the rockets location, it than removes
     * its self and runs the gameOver mthod for the world
     *
     *@param nothing there are no parameters
     *@return nothing is returned
     */
    private void checkCollision()
    {
        Space space = (Space) getWorld();
        Actor currentAsteroid = getOneIntersectingObject(Asteroid.class);
        if(currentAsteroid != null)
        {
            space.addObject(new Explosion(), getX(), getY());
            space.removeObject(this);
            space.gameOver(); 
        }
    }
}