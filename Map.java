/*This class generates a map of nodes and edges with random locations*/

import java.util.ArrayList;
import java.util.Random;

public class Map 
{
    private Random rand;            // Used to generate random values for x,y position of nodes and to connect random nodes
    private static ArrayList<Character> nameList;   // Stores the names to be used for each node
    private int numNodes;           // Number of nodes to generate
    private static ArrayList<NodeData> nodeList; // Stores all the nodes generated

    // Constructor #1
    public Map()
    {
        rand = new Random();
        initNameList();         // Initialize namelist with data to use as node names
        numNodes = 0;
        initNodeList();         // Initialize the nodes generated with data
        initEdgeList();         // Initialize the edges to be used to connect the nodes
    }

    // Constructor #2
    public Map(int numNodes)
    {
        rand = new Random();
        initNameList();
        this.numNodes = numNodes;
        initNodeList();
        initEdgeList();    
    }

    // Populate namelist with all the english alphabets in capital letters (A-Z)
    // These will be used to name the nodes generated
    public void initNameList()
    {
        nameList = new ArrayList<>();
        for(Character c = 'A'; c <= 'Z'; c++)
        {
            nameList.add(c);
        }
    }

    // Generate random x & y locations for the nodes generated
    // Assigns a unique name to each node
    public void initNodeList()
    {
        nodeList = new ArrayList<>();
        for(int i = 0; i < numNodes; i++)
        {
            int rand_xPos = rand.nextInt(11);   // Generate a random number between 0 & 10
            int rand_yPos = rand.nextInt(11);

            String nodeName = Character.toString(nameList.get(i));  // Retrieve the name from namelist
            NodeData newNode = new NodeData(nodeName, rand_xPos, rand_yPos);    // Assigns a name and a location to the node
            nodeList.add(newNode);  // Add the node to namelist
        }
    }

    // Assigns a random endNode to the nodes generated in namelist
    // Updates the connectedNodeList in class EdgeData with all the nodes connected to one particular node
    public void initEdgeList()
    {
        ArrayList<NodeData> endNodeList = new ArrayList<>(nodeList);    // Copy nodeList to endNodeList 

        int rand_numNodes = numNodes; // Assigns the number of generated nodes to the variable
        
        // Loop throught endNodeList to find a random node to assign as endNode
        for(int i = 0; i < numNodes; i++)
        {
            int rand_node = rand.nextInt(rand_numNodes);
            String startNodeName = nodeList.get(i).getName();   // Assigns the name of the ith node to the variable 
            String endNodeName = endNodeList.get(rand_node).getName();  // Choose a random node from endNodeList to assign to the variable

            String edgeName = startNodeName + endNodeName; // Combines the name of the start and ending node

            // 1st condition: Checks if the selected startNode and endNode are the same
            // 2nd condition: Perform a check to prevent two paths from connecting to the same two nodes
            // If anyone of them is true, another random node is selected until the conditions are false
            while(startNodeName.equals(endNodeName) || checkEdge(edgeName, i))
            {
                rand_node = rand.nextInt(rand_numNodes);
                endNodeName = endNodeList.get(rand_node).getName();
                edgeName = startNodeName + endNodeName;
            }

            // Once the selectedEndNode has been validated, the following methods are called
            nodeList.get(i).getEdge().setEndNode(endNodeList.get(rand_node));   // Set the endNode to the ith node in nodeList
            nodeList.get(i).getEdge().addConnectedNode(endNodeList.get(rand_node)); // Update the connectedNodeList in EdgeData with the endNode
            nodeList.get(i).getEdge().setEdgeName(edgeName);    //  Set the edgeName
            nodeList.get(i).getEdge().setDistance();    // Calculates and Set the distance between the startNode and endNode
            endNodeList.remove(rand_node);  // Removes the endNode from endNodeList as each edge must have a unique endNode

            rand_numNodes -= 1; // The variable is reduced by 1 as one node has been removed from endNodeList
        }
        addAllConnectedNodes(); // Finds all the connectedNodes and updates the appropriate node connectedNodeList
    }
    
    // Checks if the edge has already been assigned to two particular nodes
    // by comparing the edgeName to prevent errors such as:
    // Node B Connected to Node C & Node C Connected to Node B which represent the same edge
    // The variable counter is used to limit the search upto the assigned edges
    // which prevents any nullpointer error
    public boolean checkEdge(String edgeName, int counter)
    {
        StringBuilder input = new StringBuilder();
        input.append(edgeName);     // Assign the edgeName to variable input
        input = input.reverse();    // Reverse the edgeName

        // Loops through nodeList between 0 to counter
        for(int i = 0; i < counter; i++)
        {
            String testEdgeName = nodeList.get(i).getEdge().getEdgeName();  // Assigns the edgeName of the ith node in nodeList 
            
            // If there is a match between testEdgeName and input then it means the edge has already been assigned
            if((input.toString()).equals(testEdgeName))
            {
                return true;
            }
        }
        return false;
    }

    // Updates the connectedNodeList with all the connected Nodes
    public void addAllConnectedNodes()
    {
        // Loop through nodeList to get ith node's name in nodeList
        for(int i = 0; i < nodeList.size(); i++)
        {
            String nodeName = nodeList.get(i).getName();    // Assign the name of the ith node in nodeList

            // Loop through nodeList to lookup nodes connected to the ith element in nodeList
            for(int j = 0; j < nodeList.size(); j++)
            {
                String tempNodeName = nodeList.get(j).getEdge().getEdgeName();  // Assigns the jth element edgeName which consist of startNode and endNode
                int index = tempNodeName.indexOf(nodeName); // Lookup for nodeName in tempNodeName; if there is no match, indexOf returns -1

                // Performs a check so that the node itself is not added to the connectedNodeList
                if((i != j) && index != -1)
                {
                    nodeList.get(i).getEdge().addConnectedNode(nodeList.get(j));    // Add node to connectedNodeList
                }
            }
        }
    }

    public static ArrayList<NodeData> getNodeList()
    {
        return nodeList;
    }

    public void printNodeList()
    {
        for(int i = 0; i < numNodes; i++)
        {
            System.out.print(nodeList.get(i).getName() + " ");
        }
        System.out.println("\n");
    }

    public void printEdgeList()
    {
        for(int i = 0; i < numNodes; i++)
        {
            String startNode = nodeList.get(i).getEdge().getStartNode().getName();
            String endNode = nodeList.get(i).getEdge().getEndNode().getName();
            int edgeDistance = nodeList.get(i).getEdge().getDistance();
            System.out.printf("%s -- > %s = %d\n", startNode, endNode, edgeDistance);
        }
    }

    public void printAllConnections()
    {
        StringBuilder nodeName = new StringBuilder();
        for(int i = 0; i < numNodes; i++)
        {
            NodeData node = nodeList.get(i);
            nodeName.append(node.getName() + " --> ");
            nodeName.append(node.getEdge().printAllConnectedNodes() + "\n");
        }
        System.out.println(nodeName.toString());
    }
}
