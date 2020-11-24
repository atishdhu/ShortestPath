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

    public int getTotalDistance()
    {
        return totalDistance;
    }

    public void setDistance()
    {

        for(int i = 0; i < path.size() - 1; i++)
        {
            int x1 = path.get(i).getXPos();
            int x2 = path.get(i+1).getXPos();
            int y1 = path.get(i).getYPos();
            int y2 = path.get(i+1).getYPos();

            double distance = Math.sqrt(Math.pow((x2 - x1), 2.0) + Math.pow((y2 - y1), 2.0));
            this.totalDistance += distance;
        }
    }

    public void addNode(NodeData node)
    {
        path.add(node);
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

    public String getPath()
    {
        String str = "";

        for(int i = 0; i < path.size(); i++)
        {
            str += path.get(i).getName() + " ";
        }

        str += "\nDistance: " + totalDistance + "\n";

        return str;
    }
}
