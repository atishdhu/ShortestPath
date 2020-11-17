public class PathData 
{
    private int distance;
    private NodeData startNode;
    private NodeData endNode;

    public PathData()
    {
        distance = 0;
        startNode = new NodeData();
        endNode = new NodeData();
    }

    public PathData(NodeData startNode)
    {
        distance = 0;
        this.startNode = startNode;
        this.endNode = startNode;
    }
    
    // Copy Constructor
    public PathData(PathData path)
    {
        this.distance = path.distance;
        this.startNode = path.startNode;
        this.endNode = path.endNode;
    }

    public PathData(int distance, NodeData startNode, NodeData endNode)
    {
        this.distance = distance;
        startNode = new NodeData(startNode);
        endNode = new NodeData(endNode);
    }

    public void setDistance(int distance)
    {
        this.distance = distance;
    }

    public int getDistance()
    {
        return distance;
    }

    public void setStartNode(NodeData startNode)
    {
        this.startNode = new NodeData(startNode);
    }

    public void setEndNode(NodeData endNode)
    {
        this.endNode = new NodeData(endNode);
    }
    
    public NodeData getStartNode()
    {
        return startNode;
    }

    public NodeData getEndNode()
    {
        return endNode;
    }

}
