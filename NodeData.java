public class NodeData 
{
    private String name;
    private PathData path;

    public NodeData()
    {
        name = "";
        path = new path();
    }

    // Copy Constructor
    public NodeData(NodeData node)
    {
        name = node.name;
        path = node.path;
    }

    public NodeData(String name)
    {
        this.name = name;
        path = new path();
    }

    public NodeData(String name, PathData path)
    {
        this.name = name;
        PathData(path);
    }

}
