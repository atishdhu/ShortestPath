/*This class defines an edge*/

import java.util.ArrayList;

public class EdgeData 
{
    private String edgeName;        // Assigns a unique identifier for the edge in the following format: name of start node name of end node
    private int distance;           // Defines the distance between the start node and the end node
    private NodeData startNode;     // Defines where the edge starts
    private NodeData endNode;       // Defines where the edge ends
    private ArrayList<NodeData> connectedNodesList; // Stores all the nodes connected to the start node via different edges

    // Constructor #1
    public EdgeData()
    {
        startNode = new NodeData();
        endNode = new NodeData();
        edgeName = "";
        distance = 0;
        connectedNodesList = new ArrayList<>();
    }

    // Constructor #2
    public EdgeData(NodeData startNode)
    {
        this.startNode = startNode;
        this.endNode = startNode;
        edgeName = startNode.getName() + endNode.getName();
        distance = 0;
        connectedNodesList = new ArrayList<>();
    }

    // Copy Constructor
    public EdgeData(EdgeData edge)
    {
        this.distance = edge.distance;
        this.startNode = edge.startNode;
        this.endNode = edge.endNode;
        edgeName = edge.startNode.getName() + edge.endNode.getName();
        this.connectedNodesList = edge.connectedNodesList;
    }

    public int getDistance()
    {
        return distance;
    }

    // Calculates the edge distance using the formula: distance =√((x_2 - x_1)² + (y_2 - y_1)²)
    public void setDistance()
    {
        int x1 = startNode.getXPos();
        int x2 = endNode.getXPos();
        int y1 = startNode.getYPos();
        int y2 = endNode.getYPos();

        double sum = Math.pow((x2 - x1), 2.0) + Math.pow((y2 - y1), 2.0);
        this.distance = (int) Math.sqrt(sum);
    }

    public String getEdgeName()
    {
        return edgeName;
    }

    public void setEdgeName(String edgeName)
    {
        this.edgeName = edgeName;
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

    // Add node to connectedNodeList
    public void addConnectedNode(NodeData node)
    {
        connectedNodesList.add(node);
    }

    // Get the number of elements in connectedNodeList
    public int getNumConnectedNodes()
    {
        return connectedNodesList.size();
    }

    public ArrayList<NodeData> getConnectedNodeList()
    {
        return connectedNodesList;
    }

    // Remove the element at index from connectedNodeList
    public void popConnectedNode(int index)
    {
        connectedNodesList.remove(index);
    }

    // Remove the given node from connectedNodeList
    public void popConnectedNode(NodeData node)
    {
        connectedNodesList.remove(node);
    }

    // Returns the name of all nodes in connectedNodeList
    public String printAllConnectedNodes()
    {
        StringBuilder connectedNodes = new StringBuilder();

        for(int i = 0; i < getNumConnectedNodes(); i++)
        {
            connectedNodes.append(connectedNodesList.get(i).getName() + " ");
        }

        return connectedNodes.toString();
    }
}
