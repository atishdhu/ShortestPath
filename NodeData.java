public class NodeData 
{
    private String name;
    private PathData path;

    public NodeData()
    {
        name = "";
        path = new PathData();
    }

    public NodeData(String name)
    {
        this.name = name;
        path = new PathData(this);
    }

    // Copy Constructor
    public NodeData(NodeData node)
    {
        name = node.name;
    }

    public NodeData(PathData path)
    {
        this.path = new PathData(path);
    }

    public String getName()
    {
        return name;
    }

    public void setPath(PathData path)
    {
        this.path = new PathData(path);
    }

    public PathData getPath()
    {
        return path;
    }
}
