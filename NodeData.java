public class NodeData 
{
    private String nodeName;
    private int xPos;
    private int yPos;
    private EdgeData edge; // Node edge data
    

    public NodeData()
    {
        nodeName = "";
        xPos = 0;
        yPos = 0;
        edge = new EdgeData();
    }

    public NodeData(String name, int xPos, int yPos)
    {
        this.nodeName = name;
        this.xPos = xPos;
        this.yPos = yPos;
        edge = new EdgeData(this); // Initialise the edge with this node object so that the start node equals itself
    }

    // Copy Constructor
    public NodeData(NodeData node)
    {
        this.nodeName = node.nodeName;
        this.xPos = node.xPos;
        this.yPos = node.yPos;
    }

    public NodeData(EdgeData edge)
    {
        this.edge = new EdgeData(edge);
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

    public void setEdge(EdgeData edge)
    {
        this.edge = new EdgeData(edge);
    }

    public EdgeData getEdge()
    {
        return edge;
    }
}
