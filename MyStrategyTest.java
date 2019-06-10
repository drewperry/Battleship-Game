import sofia.micro.*;
import sofia.battleship.*;
import static sofia.battleship.ShipType.*;
import sofia.util.Random;
// -------------------------------------------------------------------------
/**
 *  test for first strategy
 *
 *  @author Andrew Perry (andrewperry)
 *  @version 2018.10.31
 */
public class MyStrategyTest extends TestCase
{
    //~ Fields ................................................................
    private MyStrategy myStrategy;
    private TestableGameState state;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new FirstStrategyTest test object.
     */
    public MyStrategyTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }

    //~ Methods ...............................................................
    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        /*# Insert your own setup code here */
        state = new TestableGameState();
        myStrategy = new MyStrategy();
        myStrategy.newGame();
    }

    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    /**
     * tests call next shot
     */
    public void testcallNextShot()
    {
        myStrategy.callNextShot(state);
        assertTrue(state.getOpponentsBoard().canFireAt(1, 1));

        // Make assertions about the (x, y) location of the move
    }

    /**
     * tests call next shot when null
     */
    public void testcallNextShotFalse()
    {
        TestableBoard board = state.getOpponentsBoard();
        board.placeShips(
            ".........." +
            "CCCCC....." +
            ".BBBB....." +
            ".....DDD.." +
            ".SSS......" +
            "........PP" +
            ".........." +
            ".........." +
            ".........." +
            "..........");
        board.firePattern(
            "**********" +
            "**********" +
            "**********" +
            "**********" +
            "**********" +
            "**********" +
            "**********" +
            "**********" +
            "**********" +
            "**********") ;
        CallShotMove shootah = myStrategy.callNextShot(state);
        assertEquals(null, shootah); 

    }

    /**
     * tests getName()
     */
    public void testgetName()
    {
        String name = "andrewperry";
        String getter = myStrategy.getName();
        assertTrue(name.equals(getter));
    }

    /**
     * tests canPlayDeviously()
     */
    public void testcanPlayDeviously()
    {
        boolean devious = myStrategy.canPlayDeviously();
        assertFalse(devious);
    }

    /**
     * tests placeShips()
     */
    public void testplaceShips()
    {

        Random.setNextInts(3, 3, 3, 3, 3);
        ShipPlacementMove placed = myStrategy.placeShips(state);
        assertTrue(placed.getShip(BATTLESHIP).occupies(3, 1));
        assertTrue(placed.getShip(CARRIER).occupies(3, 2));
        assertTrue(placed.getShip(DESTROYER).occupies(3, 3));
        assertTrue(placed.getShip(PATROL).occupies(3, 4));
        assertTrue(placed.getShip(SUBMARINE).occupies(3, 5));

    }
    /**
     * tests new game
     */
    public void testnewGame()
    {
        ShipPlacementMove placed = myStrategy.placeShips(state);
        BattleshipStrategy goods = new MyStrategy();
        goods.placeShips(state);
        ShipPlacementMove placed2 = goods.placeShips(state);
        assertNotSame(placed2, placed);
    }

}

