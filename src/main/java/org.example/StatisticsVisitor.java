package org.example;

public class StatisticsVisitor implements Visitor {
    private int fileCount = 0;
    private int directoryCount = 0;
    private int maxDepth = 0;
    private int currentDepth = 0;

    public void visit(FileNode file) {
        fileCount++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;
    }

    public void visit(DirectoryNode dir) {
        directoryCount++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;

        currentDepth++;
        for (Element child : dir.getChildren()) {
            child.accept(this);
        }
        currentDepth--;
    }

    public int getFileCount() { return fileCount; }
    public int getDirectoryCount() { return directoryCount; }
    public int getMaxDepth() { return maxDepth; }
}
