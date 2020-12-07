/*This class defines a node*/

import java.util.ArrayList;
import java.util.Random;

public class NodeData 
{
    private Random rand;
    private String nodeName;
    private int xPos;
    private int yPos;
    private boolean visited;
    private ArrayList<NodeData> neighbourNodesList; // Stores all nodes connected to this node
    private ArrayList<NodeData> unsettledNodes;
    private final int positionRange = 11;           // Range between 0 and 10 for x/y coordinates
    
    // Constructor #1
    public NodeData()
    {
        rand = new Random();
        nodeName = "";
        xPos = generateRandomPosition();
        yPos = generateRandomPosition();
        visited = false;
        neighbourNodesList = new ArrayList<>();
        unsettledNodes = new ArrayList<>();
    }

    // Constructor #2
    public NodeData(String name, ArrayList<NodeData> neighbours)
    {
        this.rand = new Random();
        this.nodeName = name;
        this.xPos = generateRandomPosition();
        this.yPos = generateRandomPosition();
        this.visited = false;
        this.neighbourNodesList = new ArrayList<>(neighbours);
        this.unsettledNodes = new ArrayList<>(neighbours);
    }

    public String getName()
    {
        return nodeName;
    }

    public void setName(String nodeName)
    {
        this.nodeName = nodeName;
    }

    public int generateRandomPosition()
    {
        return rand.nextInt(positionRange);
    }

    public int getXPos()
    {
        return xPos;
    }

    public int getYPos()
    {
        return yPos;
    }

    public void setNeighbours(ArrayList<NodeData> neighbours)
    {
        this.neighbourNodesList = new ArrayList<>(neighbours);
        this.unsettledNodes = new ArrayList<>(neighbours);
    }

    public void addNeighbour(NodeData neighbour)
    {
        this.neighbourNodesList.add(neighbour);
        this.unsettledNodes.add(neighbour);
    }

    public ArrayList<NodeData> getNeighbours()
    {
        return neighbourNodesList;
    }

    public ArrayList<NodeData> getUnsettledNodes()
    {
        return unsettledNodes;
    }

    public void popUnsettledNode(NodeData node)
    {
        unsettledNodes.remove(node);
    }

    public void setVisited(boolean visited)
    {
        this.visited = visited;
    }

    public boolean isVisited()
    {
        return visited;
    }

    public void resetAll()
    {
        visited = false;
        unsettledNodes = new ArrayList<>(neighbourNodesList);
    }

    public void resetNeighbours()
    {
        unsettledNodes = new ArrayList<>(neighbourNodesList);
    }
}
