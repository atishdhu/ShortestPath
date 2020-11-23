import java.util.ArrayList;

public class PathData
{
    private ArrayList<NodeData> path;
    private int totalDistance;
    private ArrayList<NodeData> settledNodes;

    public PathData()
    {
        path = new ArrayList<>();
        totalDistance = 0;
        settledNodes = new ArrayList<>();
    }

    public PathData(ArrayList<NodeData> newPath, int totalDistance)
    {
        this.path = new ArrayList<>(newPath);
        this.totalDistance = totalDistance;
        this.settledNodes = new ArrayList<>();
    }

    public void setTotalDistance(int totalDistance)
    {
        this.totalDistance = totalDistance;
    }

    public int getTotalDistance()
    {
        return totalDistance;
    }

    public void addNode(NodeData node)
    {
        path.add(node);
        
        if(path.size() > 1)
            this.totalDistance += node.getEdge().getDistance();
    }

    public ArrayList<NodeData> getpathList()
    {
        return path;
    }

    public void addSettledNode(NodeData node)
    {
        settledNodes.add(node);
    }

    public ArrayList<NodeData> getSettledNodes()
    {
        return settledNodes;
    }

    public boolean findSettledNode(NodeData node)
    {
        int index = path.indexOf(node);

        if(index != -1)
            return true;
        
        return false;
    }

    public void printPath()
    {
        System.out.println("Shortest Path:");
        for(int i = 0; i < path.size(); i++)
        {
            System.out.print(path.get(i).getName() + " ");
        }
        System.out.printf("Distance: %d\n", totalDistance);
    }

}
