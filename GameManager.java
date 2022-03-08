public class GameManager
{
    private Human human;
    private Computer computer;
    private Grid grid;
    private static int moveCounter;

    //Constructor
    public GameManager()
    {
        this.human = new Human();
        this.computer = new Computer();
        this.grid = new Grid();
        GameManager.setMoveCounter(0);
    }

    public void eventHandler(Player player)
    {
        /*This is called from Ui objects actionPerformed method
        every time the user clicks something*/

        //Player can be either Computer or Human object
        if(player == this.computer)
        {
            this.computer.computerLogic(this);
            this.getGrid().checkWinningCondition(player);
            return;
        }

        //Updates the Grid object
        this.getGrid().setGridValue(player.getMarkType(), 
                                    player.getCurrentColumn(), 
                                    player.getCurrentRow());
        this.getGrid().checkWinningCondition(player);
    }

    //Getters and setters
    public Human getHuman()
    {
        return this.human;
    }

    public Computer getComputer()
    {
        return this.computer;
    }

    public Grid getGrid()
    {
        return this.grid;
    }

    public static int getMoveCounter()
    {
        return GameManager.moveCounter;
    }

    public static void setMoveCounter(final int moveCounter)
    {
        GameManager.moveCounter = moveCounter;
    }
}