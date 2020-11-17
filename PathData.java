public class PathData 
{
    private int distance;
    private NodeData startNode;
    private NodeData endNode;

    public PathData()
    {     
        startNode = new NodeData();
        endNode = new NodeData();
        distance = 0;
    }

    public PathData(NodeData startNode)
    {
        this.startNode = startNode;
        this.endNode = startNode;
        distance = 0;
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

    public void setDistance()
    {
        int x1 = startNode.getXPos();
        int x2 = endNode.getXPos();
        int y1 = startNode.getYPos();
        int y2 = endNode.getYPos();

        double sum = Math.pow((x2 - x1), 2.0) + Math.pow((y2 - y1), 2.0);
        this.distance = (int) Math.sqrt(sum);
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
