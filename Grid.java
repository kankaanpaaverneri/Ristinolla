
public class Grid
{
    private char[][] grid;
    private static int gridHeight, gridWidth;

    public Grid()
    {
        this.setGridHeight(3);
        this.setGridWidth(3);
        this.grid = new char[Grid.getGridHeight()][Grid.getGridWidth()];
        this.initGrid();
    }

    private void initGrid()
    {
        for(int column = 0; column < Grid.getGridHeight(); column++)
        {
            for(int row = 0; row < Grid.getGridWidth(); row++)
            {
                this.grid[column][row] = ' ';
            }
        }
    }

    public int getMarkCountHorizontal(final int markType, final int column)
    {
        int markCount = 0;
        for(int row = 0; row < Grid.getGridWidth(); row++)
        {
            if(grid[column][row] == markType)
                markCount++;
        }
        return markCount;
    }

    public int getMarkCountVertical(final int markType, final int row)
    {
        int markCount = 0;
        for(int column = 0; column < Grid.getGridHeight(); column++)
        {
            if(grid[column][row] == markType)
                markCount++;
        }
        return markCount;
    }

    public int getMarkCountDiagonal(final int markType, DiagonalDirection direction)
    {
        int markCount = 0;
        if(direction == DiagonalDirection.LEFT_TO_RIGHT)
        {
            int column = 0;
            for(int row = 0; row < Grid.getGridWidth(); row++, column++)
            {
                if(this.getGridValue(column, row) == markType)
                    markCount++;
            }
        }
        else if(direction == DiagonalDirection.RIGHT_TO_LEFT)
        {
            int column = 0;
            for(int row = Grid.getGridWidth()-1; row >= 0; row--, column++)
            {
                if(this.getGridValue(column, row) == markType)
                    markCount++;
            }
        }
        return markCount;
    }

    public void blockMarkTypeHorizontal(final char markType, final int column)
    {
        for(int row = 0; row < Grid.getGridWidth(); row++)
        {
            if(this.grid[column][row] == ' ')
            {
                this.setGridValue(markType, column, row);
                break;
            }
        }
    }

    public void blockMarkTypeVertical(final char markType, final int row)
    {
        for(int column = 0; column < Grid.getGridWidth(); column++)
        {
            if(this.grid[column][row] == ' ')
            {
                this.setGridValue(markType, column, row);
                break;
            }
        }
    }
    //Sets a markType to the Grid if there is empty Square
    public void blockMarkTypeDiagonal(final char markType, DiagonalDirection direction)
    {
        if(direction == DiagonalDirection.LEFT_TO_RIGHT)
        {
            int column = 0;
            for(int row = 0; row < Grid.getGridWidth(); row++, column++)
            {
                if(this.getGridValue(column, row) == ' ')
                {
                    this.setGridValue(markType, column, row);
                    break;
                }
            }
        }
        else if(direction == DiagonalDirection.RIGHT_TO_LEFT)
        {
            int column = 0;
            for(int row = Grid.getGridWidth()-1; row >= 0; row--, column++)
            {
                if(this.getGridValue(column, row) == ' ')
                {
                    this.setGridValue(markType, column, row);
                    break;
                }
            }
        }
    }

    public void checkWinningCondition(final Player player)
    {
        for(int column = 0; column < Grid.getGridHeight(); column++)
        {
            if(this.getMarkCountHorizontal(player.getMarkType(), column) == 3)
                player.setIsWinner(true);
        }

        for(int row = 0; row < Grid.getGridWidth(); row++)
        {
            if(this.getMarkCountVertical(player.getMarkType(), row) == 3)
                player.setIsWinner(true);
        }

        if(this.getMarkCountDiagonal(player.getMarkType(), DiagonalDirection.LEFT_TO_RIGHT) == 3 ||
            this.getMarkCountDiagonal(player.getMarkType(), DiagonalDirection.RIGHT_TO_LEFT) == 3)
        {
            player.setIsWinner(true);
        }
    }

    public boolean isFull()
    {
        for(int column = 0; column < Grid.getGridHeight(); column++)
        {
            for(int row = 0; row < Grid.getGridWidth(); row++)
            {
                if(this.getGridValue(column, row) == ' ')
                    return false;
            }
        }
        return true;
    }

    //Getters and setters
    public static int getGridHeight()
    {
        return Grid.gridHeight;
    }

    public static int getGridWidth()
    {
        return Grid.gridWidth;
    }

    private void setGridHeight(final int gridHeight)
    {
        Grid.gridHeight = gridHeight;
    }

    private void setGridWidth(final int gridWidth)
    {
        Grid.gridWidth = gridWidth;
    }

    public char getGridValue(final int column, final int row)
    {
        return this.grid[column][row];
    }

    public void setGridValue(final char value, final int column, final int row)
    {
        this.grid[column][row] = value;
    }
}