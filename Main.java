/*This class defines the main method*/

public class Main
{
    public static void main(String[] args)
    {
        Map newMap = new Map(3);
    
        System.out.println("NodeList");
        newMap.printNodeList();

        System.out.println("Edge List");
        newMap.printNeighbourNodes();

        ShortestRoute path = new ShortestRoute("A");
        NodeData sourceNode = path.getNode("A");
        path.getPaths(sourceNode);
    }
}