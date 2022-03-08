public class Human extends Player
{
    private char markType;
    private int column, row;
    private boolean isWinner;
    public Human()
    {}

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