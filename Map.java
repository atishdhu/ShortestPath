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
        initPathList();     
    }

    public Map(int numNodes)
    {
        rand = new Random();
        initNameList();
        this.numNodes = numNodes;
        initNodeList();
        initPathList();    
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
            String nodeName = Character.toString(nameList.get(i));
            NodeData newNode = new NodeData(nodeName);
            nodeList.add(newNode);
        }
    }

    public void initPathList()
    {
        endNodeList = new ArrayList<>(nodeList);

        int rand_numNodes = numNodes;
        
        for(int i = 0; i < numNodes; i++)
        {
            int rand_node = rand.nextInt(rand_numNodes);
            int rand_distance = rand.nextInt(20);

            while(nodeList.get(i).getName() == endNodeList.get(rand_node).getName())
            {
                rand_node = rand.nextInt(rand_numNodes);
            }

            nodeList.get(i).getPath().setEndNode(endNodeList.get(rand_node));
            nodeList.get(i).getPath().setDistance(rand_distance);
            endNodeList.remove(rand_node);

            rand_numNodes -= 1;
        }
    }
    
    public void printNodeList()
    {
        for(int i = 0; i < numNodes; i++)
        {
            System.out.println(nodeList.get(i).getName());
        }
    }

    public void printPathList()
    {
        for(int i = 0; i < numNodes; i++)
        {
            String nodeName = nodeList.get(i).getPath().getEndNode().getName();
            int nodeDistance = nodeList.get(i).getPath().getDistance();
            System.out.printf("%s --> %d\n", nodeName, nodeDistance);
        }
        
    }
}
