public abstract class Player
{
    public abstract char getMarkType();
    public abstract void setMarkType(final char markType);

    public abstract int getCurrentColumn();
    public abstract int getCurrentRow();

    public abstract void setCurrentColumn(final int column);
    public abstract void setCurrentRow(final int row);

    public abstract boolean getIsWinner();
    public abstract void setIsWinner(final boolean isWinner);
}