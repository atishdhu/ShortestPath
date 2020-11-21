import java.util.ArrayList;
import java.util.Random;

public class Map 
{
    private Random rand;
    private static ArrayList<Character> nameList;
    private int numNodes;
    private ArrayList<NodeData> endNodeList;
    private static ArrayList<NodeData> nodeList;

    public Map()
    {
        rand = new Random();
        initNameList();
        numNodes = 0;
        initNodeList();
        initEdgeList();     
    }

    public Map(int numNodes)
    {
        rand = new Random();
        initNameList();
        this.numNodes = numNodes;
        initNodeList();
        initEdgeList();    
    }

    public void initNameList()
    {
        nameList = new ArrayList<>();
        for(Character c = 'A'; c <= 'Z'; c++)
        {
            nameList.add(c);
        }
    }

    public void initNodeList()
    {
        nodeList = new ArrayList<>();
        for(int i = 0; i < numNodes; i++)
        {
            int rand_xPos = rand.nextInt(11);
            int rand_yPos = rand.nextInt(11);

            String nodeName = Character.toString(nameList.get(i));
            NodeData newNode = new NodeData(nodeName, rand_xPos, rand_yPos);
            nodeList.add(newNode);
        }
    }

    public void initEdgeList()
    {
        endNodeList = new ArrayList<>(nodeList);

        int rand_numNodes = numNodes;
        
        for(int i = 0; i < numNodes; i++)
        {
            int rand_node = rand.nextInt(rand_numNodes);
            String startNodeName = nodeList.get(i).getEdge().getStartNode().getName();
            String endNodeName = endNodeList.get(rand_node).getName();

            String edgeName = startNodeName + endNodeName;

            while(startNodeName.equals(endNodeName) || checkEdge(edgeName, i))
            {
                rand_node = rand.nextInt(rand_numNodes);
                endNodeName = endNodeList.get(rand_node).getName();
                edgeName = startNodeName + endNodeName;
            }

            nodeList.get(i).getEdge().setEndNode(endNodeList.get(rand_node));
            nodeList.get(i).getEdge().addConnectedNode(endNodeList.get(rand_node));
            nodeList.get(i).getEdge().setEdgeName(edgeName);
            nodeList.get(i).getEdge().setDistance();
            endNodeList.remove(rand_node);

            rand_numNodes -= 1;
        }
        addAllConnectedNodes();
    }
    
    public boolean checkEdge(String edgeName, int counter)
    {
        StringBuilder input = new StringBuilder();
        input.append(edgeName);
        input = input.reverse();

        for(int i = 0; i < counter; i++)
        {
            String testEdgeName = nodeList.get(i).getEdge().getEdgeName();
            if((input.toString()).equals(testEdgeName))
            {
                return true;
            }
        }
        return false;
    }

    public void addAllConnectedNodes()
    {
        for(int i = 0; i < nodeList.size(); i++)
        {
            String nodeName = nodeList.get(i).getName();

            for(int j = 0; j < nodeList.size(); j++)
            {
                String tempNodeName = nodeList.get(j).getEdge().getEdgeName();
                int index = tempNodeName.indexOf(nodeName);

                if((i != j) && index != -1)
                {
                    nodeList.get(i).getEdge().addConnectedNode(nodeList.get(j));
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
