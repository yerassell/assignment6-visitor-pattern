package filesystem;

public class FileNode implements Element {
    private String name;
    private long size;
    private DirectoryNode parent;

    public FileNode(String name, long size) {
        if (size < 0) throw new IllegalArgumentException("Size cannot be negative");
        this.name = name;
        this.size = size;
    }

    public String getName() { return name; }
    public long getSize() { return size; }
    public DirectoryNode getParent() { return parent; }

    public void setParent(DirectoryNode parent) {
        if (this.parent != null) {
            throw new IllegalStateException("Already belongs to a directory");
        }
        this.parent = parent;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
