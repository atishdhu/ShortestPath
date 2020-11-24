/*This class defines the main method*/

public class Main
{
    public static void main(String[] args)
    {
        Map newMap = new Map(25);
    
        System.out.println("NodeList");
        newMap.printNodeList();

        System.out.println("Edge List");
        newMap.printEdgeList();

        System.out.println();
        System.out.println("Connected Nodes:");
        newMap.printAllConnections();

        ShortestRoute pathfinding = new ShortestRoute("A", "I");
        NodeData startNode = pathfinding.getNode("A");
        pathfinding.getPaths(startNode);

        pathfinding.printPath();
        pathfinding.printShortestPath(pathfinding.getShortestPath());
    }
}