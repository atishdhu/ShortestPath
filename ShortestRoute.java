import java.util.ArrayList;

public class ShortestRoute
{
    private NodeData sourceNode;
    private NodeData destinationNode;
    private PathData newPath;
    private ArrayList<PathData> pathList;

    public ShortestRoute(String sourceName, String destinationName)
    {
        this.sourceNode = getNode(sourceName);
        this.destinationNode = getNode(destinationName);
        newPath = new PathData();
        pathList = new ArrayList<>();
    }

    public NodeData getNode(String nodeName)
    {
        for(int i = 0; i < (Map.getNodeList()).size(); i++)
        {
            if((Map.getNodeList().get(i).getName()).equals(nodeName))
            {
                return Map.getNodeList().get(i);
            }
        }
        return null;
    }

    public int getPaths(NodeData pathStart)
    {    
        if(pathStart.equals(destinationNode))
        {
            return 1;
        }

        ArrayList<NodeData> connectedNodes = new ArrayList<>(pathStart.getEdge().getConnectedNodeList());

        newPath.addSettledNode(pathStart);
        
        for(int i = 0; i < connectedNodes.size(); i++)
        {
            if(connectedNodes.get(i).equals(sourceNode) || newPath.findSettledNode(connectedNodes.get(i)))
            {
                connectedNodes.remove(i);
                pathStart.getEdge().popConnectedNode(i);
            }
        }

        pathStart.getEdge().popConnectedNode(0);

        if(pathStart.equals(sourceNode))
            newPath.addNode(pathStart);

        newPath.addNode(connectedNodes.get(0));

        if(connectedNodes.get(0).equals(destinationNode))
        {
            pathList.add(newPath);
            if((sourceNode.getEdge().getNumConnectedNodes()) > 0)
            {
                newPath = new PathData();
                getPaths(sourceNode);
            }
            
            return 1;
        }
        else
        {
            getPaths(connectedNodes.get(0));
        }
        
        return 1;
    }

    public void printPath()
    {
        for(int i = 0; i < pathList.size(); i++)
        {
            pathList.get(i).printPath();
        }
    }
}
