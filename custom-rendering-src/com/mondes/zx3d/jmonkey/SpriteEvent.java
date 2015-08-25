package com.mondes.zx3d.jmonkey;

public class SpriteEvent
{
    private int x;
    private int y;
    private int bitMemoryAddress;

    public SpriteEvent(int x, int y, int bitMemoryAddress)
    {
	this.x= x;
	this.y= y;
	this.bitMemoryAddress= bitMemoryAddress;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x= x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y= y;
    }

    public int getBitMemoryAddress()
    {
        return bitMemoryAddress;
    }

    public void setBitMemoryAddress(int bitMemoryAddress)
    {
        this.bitMemoryAddress= bitMemoryAddress;
    }

}
