import java.util.ArrayList;

public class EdgeData 
{
    private String edgeName;
    private int distance;
    private NodeData startNode;
    private NodeData endNode;
    private ArrayList<NodeData> connectedNodesList;

    public EdgeData()
    {
        startNode = new NodeData();
        endNode = new NodeData();
        edgeName = "";
        distance = 0;
        connectedNodesList = new ArrayList<>();
    }

    public EdgeData(NodeData startNode)
    {
        this.startNode = startNode;
        this.endNode = startNode;
        edgeName = startNode.getName() + "," + endNode.getName();
        distance = 0;
        connectedNodesList = new ArrayList<>();
    }
    
    public EdgeData(int distance, NodeData startNode, NodeData endNode)
    {  
        startNode = new NodeData(startNode);
        endNode = new NodeData(endNode);
        edgeName = startNode.getName() + "," + endNode.getName();
        this.distance = distance;
        connectedNodesList = new ArrayList<>();
    }

    // Copy Constructor
    public EdgeData(EdgeData edge)
    {
        this.distance = edge.distance;
        this.startNode = edge.startNode;
        this.endNode = edge.endNode;
        edgeName = edge.startNode.getName() + "," + edge.endNode.getName();
        this.connectedNodesList = edge.connectedNodesList;
    }

    public int getDistance()
    {
        return distance;
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

    public void addConnectedNode(NodeData node)
    {
        connectedNodesList.add(node);
    }

    public int getNumConnectedNodes()
    {
        return connectedNodesList.size();
    }

    public ArrayList<NodeData> getConnectedNodeList()
    {
        return connectedNodesList;
    }

    public void popConnectedNode(int index)
    {
        connectedNodesList.remove(index);
    }

    public void popConnectedNode(NodeData node)
    {
        connectedNodesList.remove(node);
    }

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
