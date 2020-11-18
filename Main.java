public class Main
{
    public static void main(String[] args)
    {
        Map newMap = new Map(10);
    
        System.out.println("NodeList");
        newMap.printNodeList();

        System.out.println("Edge List");
        newMap.printEdgeList();
    }
}