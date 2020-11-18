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
            int rand_xPos = rand.nextInt(11);
            int rand_yPos = rand.nextInt(11);

            String nodeName = Character.toString(nameList.get(i));
            NodeData newNode = new NodeData(nodeName, rand_xPos, rand_yPos);
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
            String startNodeName = nodeList.get(i).getPath().getStartNode().getName();
            String endNodeName = endNodeList.get(rand_node).getName();

            String pathName = startNodeName + endNodeName;

            while(startNodeName.equals(endNodeName) || checkPath(pathName, i))
            {
                rand_node = rand.nextInt(rand_numNodes);
                endNodeName = endNodeList.get(rand_node).getName();
                pathName = startNodeName + endNodeName;
            }

            nodeList.get(i).getPath().setEndNode(endNodeList.get(rand_node));
            nodeList.get(i).getPath().setPathName(pathName);
            nodeList.get(i).getPath().setDistance();
            endNodeList.remove(rand_node);

            rand_numNodes -= 1;
        }
    }
    
    public boolean checkPath(String pathName, int counter)
    {
        StringBuilder input = new StringBuilder();
        input.append(pathName);
        input = input.reverse();

        for(int i = 0; i < counter; i++)
        {
            String testPathName = nodeList.get(i).getPath().getPathName();
            if((input.toString()).equals(testPathName))
            {
                return true;
            }
        }
        return false;
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
            String startNode = nodeList.get(i).getPath().getStartNode().getName();
            String endNode = nodeList.get(i).getPath().getEndNode().getName();
            int nodeDistance = nodeList.get(i).getPath().getDistance();
            System.out.printf("%s -- > %s = %d\n", startNode, endNode, nodeDistance);
        }
    }
}
