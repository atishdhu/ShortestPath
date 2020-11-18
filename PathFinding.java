// import java.util.ArrayList;

// public class PathFinding 
// {
//     private ArrayList<NodeData> nodeList;
//     private NodeData sourceNode;
//     private NodeData destinationNode;
//     private ArrayList<NodeData> settledNodes;
//     private ArrayList<NodeData> unsettledNodes;
//     private int pathDistance;

//     public PathFinding()
//     {
//         nodeList = new ArrayList<>(Map.getNodeList());
//         settledNodes = new ArrayList<>();
//         unsettledNodes = new ArrayList<>();
//     }

//     public PathFinding(String sourceName, String destinationName)
//     {
//         nodeList = new ArrayList<>(Map.getNodeList());
//         setSourceNode(sourceName);
//         setDestinationNode(destinationName);
//         settledNodes = new ArrayList<>();
//         unsettledNodes = new ArrayList<>();
//     }

//     public void setSourceNode(String nodeName)
//     {

//         for(int i = 0; i < (Map.getNodeList()).size(); i++)
//         {
//             if((Map.getNodeList().get(i).getName()).equals(nodeName))
//             {
//                 this.sourceNode = Map.getNodeList().get(i);
//             }
//         }
//     }

//     public void setDestinationNode(String nodeName)
//     {

//         for(int i = 0; i < (Map.getNodeList()).size(); i++)
//         {
//             if((Map.getNodeList().get(i).getName()).equals(nodeName))
//             {
//                 this.destinationNode = Map.getNodeList().get(i);
//             }
//         }
//     }

    

//     public void Dijkstra()
//     {
//         unsettledNodes.add(sourceNode);
//         pathDistance = 0;

//         while(unsettledNodes.size() != 0)
//         {
//             int nodeIndex = getNodeWithLowestDistance(unsettledNodes);
//             NodeData evaluationNode = unsettledNodes.get(nodeIndex);
//             unsettledNodes.remove(nodeIndex);
//             settledNodes.add(evaluationNode);
//         }
//     }

//     public int getNodeWithLowestDistance(ArrayList<NodeData> nodeList)
//     {
//         NodeData shortestDistanceNode = unsettledNodes.get(0);
//         int index = 0;

//         for(int i = 1; i < unsettledNodes.size(); i++)
//         {
//             if(unsettledNodes.get(i).getEdge().getDistance() < shortestDistanceNode.getEdge().getDistance())
//             {
//                 shortestDistanceNode = unsettledNodes.get(i);
//                 index = i;
//             }
//         }
//         return index;
//     }
// }
