/*This class defines the algorithm to traverse the map*/

import java.util.ArrayList;

public class ShortestRoute
{
    private NodeData sourceNode;            // Node where the route will start
    private PathData newPath;               // Stores the path information
    private ArrayList<PathData> pathList;   // Stores all possible paths from sourceNode to destinationNode
    private int destinationCounter;

    // Constructor
    public ShortestRoute(String sourceName)
    {
        this.sourceNode = getNode(sourceName);      // Assigns the node returned by getNode to the variable sourceNode
        destinationCounter = 0;
        newPath = new PathData();
        pathList = new ArrayList<>();
    }

    // Finds the node by comparing the name given in the parameter to the name of the nodes
    // in nodeList (nodeList is found in class EdgeData)
    public NodeData getNode(String nodeName)
    {
        // Loop through nodeList by comparing the node name
        for(int i = 0; i < (Map.getNodeList()).size(); i++)
        {
            // If the name in nodeList and name given in the parameter match,
            // the method returns the ith node in nodeList
            if((Map.getNodeList().get(i).getName()).equals(nodeName))
            {
                return Map.getNodeList().get(i);
            }
        }
        return null;
    }

    // This method finds all possible paths from sourceNode to destinationNode
    public void getPaths(NodeData pathStart)
    {    
        if(sourceNode.getUnsettledNodes().isEmpty())
        {
            if(destinationCounter < Map.getNodeList().size()-1)
            {
                for(int i = 0; i < Map.getNodeList().size(); i++)
                {
                    Map.getNodeList().get(i).resetAll();
                }
                destinationCounter++;
                newPath = new PathData();
            }
            else
            {
                return;
            }
            
        }

        if(pathStart.equals(sourceNode))
        {
            newPath.addNode(pathStart);
        }
        
        NodeData evaluationNode = new NodeData();

        pathStart.popUnsettledNode(sourceNode); // Remove sourceNode from unsettledNodes

        if(pathStart.getUnsettledNodes().get(0).equals(pathStart.getPreviousNode()))
        {
            if(pathStart.getUnsettledNodes().size() == 1)
            {
                pathStart.popUnsettledNode(pathStart.getUnsettledNodes().get(0));
                newPath = new PathData();
                getPaths(sourceNode);
            }
        }
        else
        {
            evaluationNode = pathStart.getUnsettledNodes().get(0);
            evaluationNode.setupPreviousNode();
            evaluationNode.setPreviousNode(pathStart);
        }
        

        if(evaluationNode.equals(getDestinationNode()))
        {
            pathStart.setHasDestinationNode(true);
            pathStart.popUnsettledNode(evaluationNode);
            newPath.addNode(evaluationNode);
            pathList.add(newPath);
            newPath = new PathData();
            getPaths(sourceNode);
        }
        else if(!evaluationNode.getUnsettledNodes().isEmpty())
        {
            newPath.addNode(evaluationNode);
            getPaths(evaluationNode);
        }
        else
        {
            if(pathStart.equals(sourceNode) && sourceNode.getUnsettledNodes().isEmpty())
            {
                pathStart.popUnsettledNode(evaluationNode);
                for(int i = 0; i < Map.getNodeList().size(); i++)
                {
                    if(Map.getNodeList().get(i).hasDestinationAsNeighbour())
                    {
                        Map.getNodeList().get(i).addNeighbour(getDestinationNode());
                        Map.getNodeList().get(i).setHasDestinationNode(false);
                    }
                }
                newPath = new PathData();
                getPaths(sourceNode);
            }
            else
            {
                pathStart.popUnsettledNode(evaluationNode);
                newPath = new PathData();
                getPaths(sourceNode);
            }    
        }
    }

    public NodeData getDestinationNode()
    {
        ArrayList<NodeData> nodeList = new ArrayList<>(Map.getNodeList());
        nodeList.remove(sourceNode);
        return nodeList.get(destinationCounter);
    }

    public PathData getShortestPath()
    {
        PathData shortestRoute = pathList.get(0);
        for(int i = 1; i < pathList.size(); i++)
        {
            if(pathList.get(i).getTotalDistance() < shortestRoute.getTotalDistance())
                shortestRoute = pathList.get(i);
        }

        return shortestRoute;
    }

    public void printPath()
    {
        System.out.println("All Paths:");
        for(int i = 0; i < pathList.size(); i++)
        {
            System.out.printf("Path %d : %s\n", i + 1 , (pathList.get(i).getPath()));
        }
    }

    public void printShortestPath(PathData shortestRoute)
    {
        System.out.println("Shortest Path:");
        System.out.printf("%s\n", shortestRoute.getPath());
    }
}
