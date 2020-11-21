// import java.util.ArrayList;

// public class ShortestRoute
// {
//     private ArrayList<NodeData> nodeList;
//     private NodeData sourceNode;
//     private NodeData destinationNode;
//     private ArrayList<EdgeData> pathList;

//     public ShortestRoute(String sourceName, String destinationName)
//     {
//         nodeList = new ArrayList<>(Map.getNodeList());
//         this.sourceNode = getNode(sourceName);
//         this.destinationNode = getNode(destinationName);
//     }

//     public NodeData getNode(String nodeName)
//     {
//         for(int i = 0; i < (Map.getNodeList()).size(); i++)
//         {
//             if((Map.getNodeList().get(i).getName()).equals(nodeName))
//             {
//                 return Map.getNodeList().get(i);
//             }
//         }
//         return null;
//     }

//     public String getPaths()
//     {
//         String sourceName = sourceNode.getName();
//         String destinationName = "";
        
//         ArrayList<NodeData> tempList = new ArrayList<>(Map.getNodeList());
        
//         int i = 0;

//         String tempSourceName = nodeList.get(i).getName();

//         while(!tempSourceName.equals(sourceName) || i < nodeList.size())
//         {
//             i++;
//             tempSourceName = nodeList.get(i).getName();
//         }

//         EdgeData edge = tempList.get(i).getEdge();
//         pathList.add(edge);

//         while((tempList.size() != 0))
//         {
//             if(edge.getEndNode().equals(destinationNode))
//             {
                
//             }
//         }

//     }
// }
