/*This class defines a path(route)*/

import java.util.ArrayList;

public class PathData
{
    private ArrayList<NodeData> path;               // Stores all the nodes for one path
    private int totalDistance;              
    private ArrayList<NodeData> settledNodeList;    // Stores the visited nodes

    // Constructor #1
    public PathData()
    {
        path = new ArrayList<>();
        totalDistance = 0;
        settledNodeList = new ArrayList<>();
    }

    // Constructor #2
    public PathData(ArrayList<NodeData> newPath, int totalDistance)
    {
        this.path = new ArrayList<>(newPath);
        this.totalDistance = totalDistance;
        this.settledNodeList = new ArrayList<>();
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
        settledNodeList.add(node);
    }

    public ArrayList<NodeData> getSettledNodes()
    {
        return settledNodeList;
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
