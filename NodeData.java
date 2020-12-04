/*This class defines a node*/

import java.util.ArrayList;
import java.util.Random;

public class NodeData 
{
    private String nodeName;
    private Random rand; 
    private int xPos;
    private int yPos;
    private ArrayList<NodeData> neighbourNodesList; // Stores all nodes connected to this node
    private final int positionRange = 11;           // Range between 0 and 10
    
    // Constructor #1
    public NodeData()
    {
        rand = new Random();
        nodeName = "";
        xPos = generateRandomPosition();
        yPos = generateRandomPosition();
        neighbourNodesList = new ArrayList<>();
    }

    // Constructor #2
    public NodeData(String name, ArrayList<NodeData> neighbours)
    {
        this.rand = new Random();
        this.nodeName = name;
        this.xPos = generateRandomPosition();
        this.yPos = generateRandomPosition();
        this.neighbourNodesList = new ArrayList<>(neighbours);
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
        this.neighbourNodesList = neighbours;
    }

    public void addNeighbour(NodeData neighbour)
    {
        this.neighbourNodesList.add(neighbour);
    }

    public ArrayList<NodeData> getNeighbours()
    {
        return neighbourNodesList;
    }
}
