package org.example;

public class TotalSizeVisitor implements Visitor {
    private long totalSize = 0;

    public void visit(FileNode file) {
        totalSize += file.getSize();
    }

    public void visit(DirectoryNode dir) {
        for (Element child : dir.getChildren()) {
            child.accept(this);
        }
    }

    public long getTotalSize() {
        return totalSize;
    }
}
