public class NodeData 
{
    private String name;
    private int xPos;
    private int yPos;
    private PathData path;

    public NodeData()
    {
        name = "";
        xPos = 0;
        yPos = 0;
        path = new PathData();
    }

    // public NodeData(String name)
    // {
    //     this.name = name;
    //     xPos = 0;
    //     yPos = 0;
    //     path = new PathData(this);
    // }

    public NodeData(String name, int xPos, int yPos)
    {
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        path = new PathData(this);
    }

    // Copy Constructor
    public NodeData(NodeData node)
    {
        name = node.name;
    }

    public NodeData(PathData path)
    {
        this.path = new PathData(path);
    }

    public String getName()
    {
        return name;
    }

    public int getXPos()
    {
        return xPos;
    }

    public int getYPos()
    {
        return yPos;
    }

    public void setPath(PathData path)
    {
        this.path = new PathData(path);
    }

    public PathData getPath()
    {
        return path;
    }
}
