/**
 * Name: Austin
 * Techer Name: Mr Hardman
 * Class: ptec software development
 * Date: 12/10/2018
 */
import greenfoot.*;

/**
 * Space. Something for rockets to fly in.
 * 
 * @author Michael Kölling
 * @version 1.0
 */
public class Space extends World
{
    private Counter scoreCounter;
    
    //TODO (62): Declare an integer instance constant called START_ASTEROIDS initialized to a one-digit number
    private final int START_ASTEROIDS = Greenfoot.getRandomNumber(7) + 3;


    /**
     * Space Constructor creates a world that is 600 by 500 pixles wide and runs 
     * the createBackground method then runs the paintStars method witha value of 25
     * and then run the prepare game method
     * 
     *@param nothing there are no parameters
     *@return nothing is returned
     */
    public Space() 
    {
        super(600, 500, 1);

        createBackground();
        
        /**
         * TODO (10): Make a method call to the paint stars method.
         *            Play around with the parameter value until you are 
         *            happy with the look of your scenario
         */
        paintStars(25);
        
        prepareGame();
    }
    
    /**
     * createBackground fills the background with a black colour
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void createBackground()
    {
        GreenfootImage background = getBackground();
        background.setColor(Color.BLACK);
        background.fill();
    }
    
    
    /**
     * Method paintStars adds white stars at random locations and with random transparancys
     *
     * @param count A parameter that sets how many stars are to be added
     * @return nothing is returned
     */
    private void paintStars(int count)
    {
        int x;
        int y;
        int transparency;
        GreenfootImage background = getBackground();
        for(int i = 0; i < count; i++)
        {
            x = Greenfoot.getRandomNumber(getWidth());
            y = Greenfoot.getRandomNumber(getHeight());
            transparency = Greenfoot.getRandomNumber(256);
            background.setColor(new Color(255, 255, 255, transparency));
            background.fillOval(x, y, 3, 3);
        }
    }
    /**
     * prepareGame adds the objects to the game to get the game ready
     * to be played
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void prepareGame()
    {
        Rocket rocket = new Rocket();
        
        scoreCounter = new Counter("Score: ");
        
        addObject(rocket, getWidth()/2 + 100, getHeight()/2);
        
        addObject(scoreCounter, 60, 480);
        
        //TODO (69): Make a method call to addAsteroids that uses your constant for the number of asteroids
        addAsteroids(START_ASTEROIDS);
    }
    

    /**
     * Method addAsteroids adds the count number of Asteroid objects to random locations
     *
     * @param count A parameter used to set how many Asteroids are to be added
     * @return nothing is returned
     */
    private void addAsteroids(int count)
    {
        int x;
        int y;
        for(int i = 0; i < count; i++)
        {
            x = Greenfoot.getRandomNumber(getWidth()/2);
            y = Greenfoot.getRandomNumber(getHeight()/2);
            addObject(new Asteroid(), x, y);
        }
    }

    /**
     * gameOver displays a ScoreBoard and the player's score
     * after the player is destroyed by an asteroid
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void gameOver() 
    {
        ScoreBoard endGame = new ScoreBoard("You Lose!", scoreCounter.getValue());
        addObject(endGame, getWidth()/2, getHeight()/2);
    }
    
    /**
     * gameWon displays a congratulatory ScoreBoard and the player's score
     * after the player destroys all asteroids
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void gameWon()
    {
        ScoreBoard endGame = new ScoreBoard("You Win!", scoreCounter.getValue());
        addObject(endGame, getWidth()/2, getHeight()/2);
    }
    
    /**
     * countScore adds a number of points to the score counter
     * 
     * @param score is the number of points being added to the score
     * @return Nothing is returned
     */
    public void countScore(int score)
    {
        scoreCounter.add(score);
    }
}