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
}
