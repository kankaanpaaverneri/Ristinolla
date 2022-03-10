import java.util.Random;

public class Computer extends Player
{
    private char markType;
    private int column, row;
    private boolean isWinner;

    public Computer()
    {}

    //Here is implemented all of the computer logic
    public void computerLogic(final GameManager gameManager)
    {
        switch(GameManager.getMoveCounter())
        {
            case 0:
                firstMove(gameManager);
                break;
            case 1:
                secondMove(gameManager);
                break;
            case 2:
                thirdMove(gameManager);
                break;
            default:
                secondMove(gameManager);
                break;
        }
        GameManager.setMoveCounter(GameManager.getMoveCounter() + 1);
    }

    private void firstMove(final GameManager gameManager)
    {
        char humanMarkType = gameManager.getHuman().getMarkType();
        if(gameManager.getGrid().getGridValue(1, 1) == humanMarkType)
        {
            //If Human player has placed mark to the middle
            //Then Computer will place the mark in one of the corners
            Random random = new Random();
            switch(random.nextInt(4) + 1)
            {
                case 1:
                    gameManager.getGrid().setGridValue(this.getMarkType(), 0, 0);
                    break;
                case 2:
                    gameManager.getGrid().setGridValue(this.getMarkType(), 0, 2);
                    break;
                case 3:
                    gameManager.getGrid().setGridValue(this.getMarkType(), 2, 2);
                    break;
                default:
                    gameManager.getGrid().setGridValue(this.getMarkType(), 2, 0);
                    break;
            }
        }
        else
        {
            //If Human player has placed mark to the corner
            //Then Computer will place the mark in the middle
            gameManager.getGrid().setGridValue(this.getMarkType(), 1, 1);
        }
    }

    private void secondMove(final GameManager gameManager)
    {
        //If Human player has two marks in row. Then computer tries to block it
        if(blockHumanThreat(gameManager, 2))
            return;
        if(GameManager.getMoveCounter() == 1)
        {
            //These will only be executed on the second turn
            if(blockBottomRightCorner(gameManager))
                return;
            
            if(blockTriangleFormation(gameManager))
                return;
        }

        //Try to fill the corners if that isn´t possible. Try to make a full row
        if(!fillEmptyCorners(gameManager))
            this.tryToMakeFullRow(gameManager, 1);
    }

    private void thirdMove(final GameManager gameManager)
    {
        if(!this.tryToMakeFullRow(gameManager, 2))
        {
            secondMove(gameManager);
        }
    }

    /*
    Blocks this type of formation
    - - -
    - O X
    - X -
    */

    private boolean blockBottomRightCorner(final GameManager gameManager)
    {
        if(gameManager.getGrid().getGridValue(2, 1) == gameManager.getHuman().getMarkType())
        {
            if(gameManager.getGrid().getGridValue(1, 2) == gameManager.getHuman().getMarkType())
            {
                gameManager.getGrid().setGridValue(this.getMarkType(), 2, 0);
                return true;
            }
        }
        return false;
    }

    /*
    Blocks this type of formation
    - - X
    - O -
    - X -
    */

    private boolean blockTriangleFormation(final GameManager gameManager)
    {
        if(gameManager.getGrid().getGridValue(0, 1) == gameManager.getHuman().getMarkType())
        {
            if(gameManager.getGrid().getGridValue(2, 0) == gameManager.getHuman().getMarkType())
            {
                gameManager.getGrid().setGridValue(this.getMarkType(), 1, 2);
                return true;
            }
            else if(gameManager.getGrid().getGridValue(2, 2) == gameManager.getHuman().getMarkType())
            {
                gameManager.getGrid().setGridValue(this.getMarkType(), 1, 0);
                return true;
            }
        }
        else if(gameManager.getGrid().getGridValue(2, 1) == gameManager.getHuman().getMarkType())
        {
            if(gameManager.getGrid().getGridValue(0, 0) == gameManager.getHuman().getMarkType())
            {
                gameManager.getGrid().setGridValue(this.getMarkType(), 1, 2);
                return true;
            }
            else if(gameManager.getGrid().getGridValue(0, 2) == gameManager.getHuman().getMarkType())
            {
                gameManager.getGrid().setGridValue(this.getMarkType(), 1, 0);
                return true;
            }
        }

        return false;
    }

    private boolean blockHumanThreat(final GameManager gameManager, final int threatLevel)
    {
        //Block Human marks horizontal
        char humanMarkType = gameManager.getHuman().getMarkType();
        for(int column = 0; column < Grid.getGridHeight(); column++)
        {
            if(gameManager.getGrid().getMarkCountHorizontal(humanMarkType, column) == threatLevel &&
                gameManager.getGrid().getMarkCountHorizontal(' ', column) >= 1)
            {
                gameManager.getGrid().blockMarkTypeHorizontal(this.getMarkType(), column);
                return true;
            }
        }

        //Block Human marks vertical
        for(int row = 0; row < Grid.getGridWidth(); row++)
        {
            if(gameManager.getGrid().getMarkCountVertical(humanMarkType, row) == threatLevel &&
                gameManager.getGrid().getMarkCountVertical(' ', row) >= 1)
            {
                gameManager.getGrid().blockMarkTypeVertical(this.getMarkType(), row);
                return true;
            }
        }

        //Block Human marks Diagonal from LEFT_TO_RIGHT
        if(gameManager.getGrid().getMarkCountDiagonal(humanMarkType, DiagonalDirection.LEFT_TO_RIGHT) == threatLevel &&
            gameManager.getGrid().getMarkCountDiagonal(' ', DiagonalDirection.LEFT_TO_RIGHT) >= 1)
        {
            gameManager.getGrid().blockMarkTypeDiagonal(this.getMarkType(), DiagonalDirection.LEFT_TO_RIGHT);
            return true;
        }
        //Block Human marks Diagonal from RIGHT_TO_LEFT
        else if(gameManager.getGrid().getMarkCountDiagonal(humanMarkType, DiagonalDirection.RIGHT_TO_LEFT) == threatLevel &&
                gameManager.getGrid().getMarkCountDiagonal(' ', DiagonalDirection.RIGHT_TO_LEFT) >= 1)
        {
            gameManager.getGrid().blockMarkTypeDiagonal(this.getMarkType(), DiagonalDirection.RIGHT_TO_LEFT);
            return true;
        }
        return false;
    }

    private boolean tryToMakeFullRow(final GameManager gameManager, final int maxMarkCount)
    {
        //If Computer marks are maxMarkCount amount in a row. Then try to make a full row

        //Try to make a full row horizontal
        for(int column = 0; column < Grid.getGridHeight(); column++)
        {
            if(gameManager.getGrid().getMarkCountHorizontal(this.getMarkType(), column) == maxMarkCount &&
                gameManager.getGrid().getMarkCountHorizontal(' ', column) >= 1)
            {
                gameManager.getGrid().blockMarkTypeHorizontal(this.getMarkType(), column);
                return true;
            }
        }
        
        //Try to make a full row vertical
        for(int row = 0; row < Grid.getGridWidth(); row++)
        {
            if(gameManager.getGrid().getMarkCountVertical(this.getMarkType(), row) == maxMarkCount &&
                gameManager.getGrid().getMarkCountVertical(' ', row) >= 1)
            {
                gameManager.getGrid().blockMarkTypeVertical(this.getMarkType(), row);
                return true;
            }
        }

        //Try to make a full row diagonal LEFT_TO_RIGHT
        if(gameManager.getGrid().getMarkCountDiagonal(this.getMarkType(), DiagonalDirection.LEFT_TO_RIGHT) == maxMarkCount &&
            gameManager.getGrid().getMarkCountDiagonal(' ', DiagonalDirection.LEFT_TO_RIGHT) >= 1)
        {
            gameManager.getGrid().blockMarkTypeDiagonal(this.getMarkType(), DiagonalDirection.LEFT_TO_RIGHT);
            return true;
        }
        //Try to make a full row diagonal RIGHT_TO_LEFT
        else if(gameManager.getGrid().getMarkCountDiagonal(this.getMarkType(), DiagonalDirection.RIGHT_TO_LEFT) == maxMarkCount &&
                gameManager.getGrid().getMarkCountDiagonal(' ', DiagonalDirection.RIGHT_TO_LEFT) >= 1)
        {
            gameManager.getGrid(). blockMarkTypeDiagonal(this.getMarkType(), DiagonalDirection.RIGHT_TO_LEFT);
            return true;
        }
        return false;
    }

    private boolean fillEmptyCorners(final GameManager gameManager)
    {
        //If any of the corner slots are free. Computer places it´s mark on that slot
        if(gameManager.getGrid().getGridValue(0, 0) == ' ')
        {
            gameManager.getGrid().setGridValue(this.getMarkType(), 0, 0);
            return true;
        }
        else if(gameManager.getGrid().getGridValue(0, 2) == ' ')
        {
            gameManager.getGrid().setGridValue(this.getMarkType(), 0, 2);
            return true;
        }
        else if(gameManager.getGrid().getGridValue(2, 2) == ' ')
        {
            gameManager.getGrid().setGridValue(this.getMarkType(), 2, 2);
            return true;
        }
        else if(gameManager.getGrid().getGridValue(2, 0) == ' ')
        {
            gameManager.getGrid().setGridValue(this.getMarkType(), 2, 0);
            return true;
        }
        return false;
    }

    //Tries to find empty Square in the Grid and place mark on there
    //This is unused method because computer has to have logic behind it´s decisions
    /*
    private void tryToFindEmptySquare(final GameManager gameManager)
    {
        for(int column = 0; column < Grid.getGridHeight(); column++)
        {
            for(int row = 0; row < Grid.getGridWidth(); row++)
            {
                if(gameManager.getGrid().getGridValue(column, row) == ' ')
                {
                    gameManager.getGrid().setGridValue(this.getMarkType(), column, row);
                    return;
                }
            }
        }
    }
    */

    //Overrided getters and setters
    @Override
    public char getMarkType()
    {
        return this.markType;
    }

    @Override
    public void setMarkType(final char markType)
    {
        this.markType = markType;
    }

    @Override
    public int getCurrentColumn()
    {
        return this.column;
    }

    @Override
    public int getCurrentRow()
    {
        return this.row;
    }

    @Override
    public void setCurrentColumn(final int column)
    {
        this.column = column;
    }

    @Override
    public void setCurrentRow(final int row)
    {
        this.row = row;
    }

    @Override
    public boolean getIsWinner()
    {
        return this.isWinner;
    }

    @Override
    public void setIsWinner(final boolean isWinner)
    {
        this.isWinner = isWinner;
    }
}