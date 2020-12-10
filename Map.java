/*This class generates a map of nodes and edges with random locations*/

import java.util.Arrays;
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
        numNodes = 0;
        initNameList();         // Initialize namelist with data to use as node names 
        initNodeList();         // Initialize the nodes generated with data
        initNeighboursList();
    }

    // Constructor #2
    public Map(int numNodes)
    {
        rand = new Random();
        this.numNodes = numNodes;
        initNameList();   
        initNodeList();
        initNeighboursList();
    }

    // Populate namelist with all the english alphabets in capital letters (A-Z)
    // Only populate the list depending on the number of nodes e.g for 3 nodes only A, B, C will be generated
    // These will be used to name the nodes generated
    public void initNameList()
    {
        nameList = new ArrayList<>();

        Character c = 'A';

        for(int i = 0; c <= 'Z' && i < numNodes; c++)
        {
            nameList.add(c);
            i++;
        }
    }

    // Assigns a unique name to each node
    public void initNodeList()
    {
        nodeList = new ArrayList<>();
        for(int i = 0; i < numNodes; i++)
        {
            String nodeName = Character.toString(nameList.get(i));  // Retrieve the name from namelist
            NodeData newNode = new NodeData();  // Assigns a name and a location to the node
            newNode.setName(nodeName);  // sets the name of the node
            nodeList.add(newNode);  // Add the node to namelist
        }
    }

    // Assigns a random endNode to the nodes generated in namelist
    // Updates the connectedNodeList in class EdgeData with all the nodes connected to one particular node
    public void initNeighboursList()
    {
        // ArrayList<NodeData> neighboursList = new ArrayList<>(nodeList);    // Copy nodeList to endNodeList 
        // Collections.shuffle(neighboursList);

        // // Loop throught endNodeList to find a random node to assign as endNode
        // for(int i = 0; i < neighboursList.size(); i++)
        // {
        //     int neighbourCounter = 0;

        //     int rand_AmountOfNeighbours = rand.nextInt(numNodes);
        //     if(rand_AmountOfNeighbours < 1)
        //         rand_AmountOfNeighbours = 1;

        //     NodeData currentNode = nodeList.get(i);

        //     neighboursList.remove(currentNode);

        //     for(int j = 0; j < neighboursList.size(); j++)
        //     {
        //         NodeData evaluationNode = neighboursList.get(j);  // Choose a random node from neighboursList to assign to the variable
            
        //         if(neighbourCounter == rand_AmountOfNeighbours)
        //             break;

        //         if((isNeighbourAlreadyConnected(currentNode, evaluationNode)))
        //         {
        //             continue;
        //         }
        //         else
        //         {
        //             currentNode.addNeighbour(neighboursList.get(j));
        //             neighbourCounter++;
        //         }
        //     }
        //     neighboursList = new ArrayList<>(nodeList);    // Copy nodeList to endNodeList 
        //     Collections.shuffle(neighboursList);
        // }   
        // addAllConnectedNeighbours();

        ArrayList<NodeData> neighboursList = new ArrayList<>(Arrays.asList());
    }

    public boolean isNeighbourAlreadyConnected(NodeData currentNode, NodeData evaluationNode)
    {
        ArrayList<NodeData> connectedNodeList = new ArrayList<>(currentNode.getNeighbours());

        for(int i = 0; i < connectedNodeList.size(); i++)
        {
            if(connectedNodeList.get(i).equals(evaluationNode))
                return true;
        }

        return false;
    }

    // Updates the connectedNodeList with all the connected Nodes
    // public void updateNeighboursList(NodeData currentNode)
    // {
    //     ArrayList<NodeData> connectedNodeList = new ArrayList<>(currentNode.getNeighbours());

    //     for(int i = 0; i < connectedNodeList.size(); i++)
    //     {
    //         NodeData evaluationNode = connectedNodeList.get(i);

    //         if(evaluationNode.getNeighbours().isEmpty())
    //         {
    //             int nodeIndex = nodeList.indexOf(evaluationNode);
    //             nodeList.get(nodeIndex).addNeighbour(currentNode);
    //         }
    //         else if(!isNeighbourAlreadyConnected(currentNode, evaluationNode))
    //         {
    //             int nodeIndex = nodeList.indexOf(evaluationNode);
    //             nodeList.get(nodeIndex).addNeighbour(currentNode);
    //         }
    //     }
    // }

    public void addAllConnectedNeighbours()
    {
        for(int i = 0; i < nodeList.size(); i++)
        {
            NodeData currentNode = nodeList.get(i);

            for(int j = 0; j < nodeList.size(); j++)
            {
                NodeData evaluationNode = nodeList.get(j);

                if(i!=j)
                {
                    if(evaluationNode.getNeighbours().contains(currentNode) && !currentNode.getNeighbours().contains(evaluationNode))
                    {
                        nodeList.get(i).addNeighbour(evaluationNode);
                    }
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

    public void printNeighbourNodes()
    {
        for(int i = 0; i < numNodes; i++)
        {
            NodeData sourceNode = nodeList.get(i);
            ArrayList<NodeData> neighbourList = new ArrayList<>(sourceNode.getNeighbours());

            System.out.printf("%s --> ", sourceNode.getName());
            for(int j = 0; j < neighbourList.size(); j++)
            {
                System.out.printf("%s ", neighbourList.get(j).getName());
            }
            System.out.println();
        }
    }

    // public void printAllConnections()
    // {
    //     StringBuilder nodeName = new StringBuilder();
    //     for(int i = 0; i < numNodes; i++)
    //     {
    //         NodeData node = nodeList.get(i);
    //         nodeName.append(node.getName() + " --> ");
    //         nodeName.append(node.getEdge().printAllConnectedNodes() + "\n");
    //     }
    //     System.out.println(nodeName.toString());
    // }
}
