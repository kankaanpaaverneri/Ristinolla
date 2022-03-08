public class MyPoint
{
    private int y, x;
    public MyPoint()
    {
        setY(-1);
        setX(-1);
    }

    public MyPoint(final int y, final int x)
    {
        setY(y);
        setX(x);
    }

    public int getY()
    {
        return this.y;
    }

    public int getX()
    {
        return this.x;
    }

    public void setY(final int y)
    {
        this.y = y;
    }

    public void setX(final int x)
    {
        this.x = x;
    }
}