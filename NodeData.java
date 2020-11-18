public class NodeData 
{
    private String nodeName;
    private int xPos;
    private int yPos;
    private PathData path;

    public NodeData()
    {
        nodeName = "";
        xPos = 0;
        yPos = 0;
        path = new PathData();
    }

    public NodeData(String name, int xPos, int yPos)
    {
        this.nodeName = name;
        this.xPos = xPos;
        this.yPos = yPos;
        path = new PathData(this); 
    }

    // Copy Constructor
    public NodeData(NodeData node)
    {
        this.nodeName = node.nodeName;
        this.xPos = node.xPos;
        this.yPos = node.yPos;
    }

    public NodeData(PathData path)
    {
        this.path = new PathData(path);
    }

    public String getName()
    {
        return nodeName;
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
