import sofia.battleship.*;
import static sofia.battleship.ShipType.*;
import sofia.micro.*;
import sofia.util.Random;

//-------------------------------------------------------------------------
/**
 *  Creates a new battleship strategy
 *
 *  @author Andrew Perry (andrewperry)
 *  @version 2018.10.28
 */
public class MyStrategy implements BattleshipStrategy
{
    //~ Fields ................................................................

    //~ Constructor ...........................................................
    // ----------------------------------------------------------

    /**
     * calls next shot
     * @param currentGameState is the state of the game
     * @return callshot returns where the shot is fired
     */
    public CallShotMove callNextShot(GameState currentGameState)
    {
        for (int x = 0; x < 10; x++)
        {
            for (int y = 0; y < 10; y++)
            {
                if (currentGameState.getOpponentsBoard().canFireAt(x, y))
                {
                    CallShotMove callshot = new CallShotMove(x, y);
                    return callshot;
                }
            }

        }
        return null;
    }

    /**
     * @return name returns PID
     */
    public String getName()
    {
        String name = "andrewperry";
        return name;
    }

    /**
     * @return false for playing deviously
     */
    public boolean canPlayDeviously()
    {
        return false;    
    }

    /**
     * places ships
     * @param currentGameState is game state
     * @return placed for ship placement
     */
    public ShipPlacementMove placeShips(GameState currentGameState)
    {
        ShipPlacementMove placed = new ShipPlacementMove(currentGameState);
        placed.placeShip(BATTLESHIP, Random.generator().nextInt(4), 1, true);
        placed.placeShip(CARRIER, Random.generator().nextInt(3), 2, true);
        placed.placeShip(DESTROYER, Random.generator().nextInt(6), 3, true);
        placed.placeShip(PATROL, Random.generator().nextInt(7), 4, true);
        placed.placeShip(SUBMARINE, Random.generator().nextInt(6), 5, true);

        return placed;
    }

    /**
     * starts newGame
     */
    public void newGame()
    {
        new MyStrategy();    
    }

}