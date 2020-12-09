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
    public int getPaths(NodeData pathStart)
    {    
        if(pathStart.equals(sourceNode))
        {
            if(pathStart.getUnsettledNodes().size() == 0)
            {
                if(destinationCounter < Map.getNodeList().size()-2)
                {
                    destinationCounter++;
                }
                else
                    return 1;

                for(int i = 0; i < Map.getNodeList().size(); i++)
                {
                    Map.getNodeList().get(i).resetAll();
                }
            }
            newPath = new PathData();
            newPath.addNode(pathStart);
        }

        newPath.addSettledNode(pathStart);

        NodeData evaluationNode = new NodeData();

        pathStart.getUnsettledNodes().remove(sourceNode);

        int evalIndex = 0;

        try
        {
            evaluationNode = pathStart.getUnsettledNodes().get(evalIndex);
        }
        catch(Exception e)
        {
            newPath = new PathData();
            return getPaths(sourceNode);
        }

        while(newPath.getSettledNodes().indexOf(evaluationNode) > -1)
        {
            evalIndex++;
            try
            {
                evaluationNode = pathStart.getUnsettledNodes().get(evalIndex);  
            }
            catch(Exception e)
            {
                if(evalIndex > evaluationNode.getUnsettledNodes().size() - 1)
                {
                    if(!evaluationNode.getUnsettledNodes().isEmpty())
                    {
                        evaluationNode = pathStart.getUnsettledNodes().get(0);
                        pathStart.popUnsettledNode(evaluationNode);
                        newPath = new PathData();
                        return getPaths(sourceNode);
                    }
                    else
                    {
                        newPath = new PathData();
                        return getPaths(sourceNode);
                    }

                // System.out.println();
                // System.out.println("Evaluation Node is Empty");
                // System.out.println(e + "\n");
                // System.out.printf("Index: %d\n", evalIndex);
                // System.out.printf("pathStart: %s\n", pathStart.getName());
                // System.out.printf("evaluationNode: %s\n", evaluationNode.getName());
                // pathList.add(newPath);
                // printPath();
                // System.exit(1);         
            }
        }

        // if(newPath.getSettledNodes().indexOf(evaluationNode) == -1)
        // {
        //     evaluationNode = pathStart.getUnsettledNodes().get(0);
        // }
        // else if(pathStart.getUnsettledNodes().size() > 1)
        // {
        //     evaluationNode = pathStart.getUnsettledNodes().get(1);
        // }
        // else if(pathStart != sourceNode)
        // {
        //     pathStart.popUnsettledNode(evaluationNode);
        //     newPath = new PathData();
        //     return getPaths(sourceNode);
        // }

        if(evaluationNode.equals(getDestinationNode()))
        {
            pathStart.popUnsettledNode(evaluationNode);
            newPath.addNode(evaluationNode);
            pathList.add(newPath);
            newPath = new PathData();
            return getPaths(sourceNode);
        }
        else if(!evaluationNode.getUnsettledNodes().isEmpty())
        {
            newPath.addNode(evaluationNode);
            getPaths(evaluationNode);
        }
        else
        {
            pathStart.popUnsettledNode(evaluationNode);
            newPath = new PathData();

            if(pathStart.equals(sourceNode))
            {
                if(sourceNode.getUnsettledNodes().size() == 0)
                {
                    if(destinationCounter < Map.getNodeList().size()-2)
                    {
                        destinationCounter++;
                        newPath = new PathData();
                    }
                    else
                        return 1;

                    for(int i = 0; i < Map.getNodeList().size(); i++)
                    {
                        Map.getNodeList().get(i).resetAll();
                    }
                }
                else
                {
                    int index = Map.getNodeList().indexOf(sourceNode);

                    for(int i = 0; i < Map.getNodeList().size(); i++)
                    {
                        if(i == index)
                            continue;

                        Map.getNodeList().get(i).resetNeighbours();
                    }
                }
            }
        }
            return getPaths(sourceNode);
        }
        return 1;
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
