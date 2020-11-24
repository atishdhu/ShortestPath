/*This class defines the algorithm to traverse the map*/

import java.util.ArrayList;

public class ShortestRoute
{
    private NodeData sourceNode;            // Node where the route will start
    private NodeData destinationNode;       // Node where the route will end
    private PathData newPath;               // Stores the path information
    private ArrayList<PathData> pathList;   // Stores all possible paths from sourceNode to destinationNode

    // Constructor
    public ShortestRoute(String sourceName, String destinationName)
    {
        this.sourceNode = getNode(sourceName);      // Assigns the node returned by getNode to the variable sourceNode
        this.destinationNode = getNode(destinationName);    // Assigns the node returned by getNode to the variable destinationNode
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
    public int getPaths(NodeData pathStart)
    {    
        // Checks if the method has reached the destination
        // to prevent any recursion on the destinationNode
        if(pathStart.equals(destinationNode))
        {
            return 1;
        }

        // Copies and stores the connectedNodes of pathStart
        ArrayList<NodeData> connectedNodes = new ArrayList<>(pathStart.getEdge().getConnectedNodeList());

        // Add the currentNode to settledNodeList found in the class PathData
        // The settledNodeList is used to store all nodes we have already visited in this particular path
        newPath.addSettledNode(pathStart);
        
        // Loop through connectedNodes to check if the sourceNode is in
        // connectedNodes or if the node is in settledNodeList.
        // If that is the case then the node is removed from connectedNodes
        // and the node is removed from connectedNodeList
        for(int i = 0; i < connectedNodes.size(); i++)
        {
            if(connectedNodes.get(i).equals(sourceNode) || newPath.findSettledNode(connectedNodes.get(i)))
            {
                connectedNodes.remove(i);                   // Remove the ith node in connectedNodes
                pathStart.getEdge().popConnectedNode(i);    // Remove the ith node in connectedNodesList
            }
        }

        pathStart.getEdge().popConnectedNode(0);    // Remove the first element in connectedNodes from connectedNodesList

        // Checks if the current node is the source node
        // If that is the case then it is added to path found in PathData
        if(pathStart.equals(sourceNode))
            newPath.addNode(pathStart); // Add to path in PathData

        newPath.addNode(connectedNodes.get(0)); // Add the connectedNodes to path

        // Checks if the path has reached the destinationNode
        if(connectedNodes.get(0).equals(destinationNode))
        {
            pathList.add(newPath);  // Add the path to pathList
            newPath.setDistance();

            // Checks if there are more connected nodes to the sourceNode
            if((sourceNode.getEdge().getNumConnectedNodes()) > 0)
            {
                newPath = new PathData(); // Creates a new empty path
                getPaths(sourceNode); // Restart the function with the source node
            }
            
            return 1; // Returns if there are no more connected nodes to the source node
        }
        else // if we have not reached the destination
        {
            // Restart the function with the first node in connectedNodes
            getPaths(connectedNodes.get(0));
        }
        
        return 1;
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
