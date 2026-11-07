package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode docs = new DirectoryNode("docs");
        DirectoryNode empty = new DirectoryNode("empty");

        FileNode fileA = new FileNode("a.txt", 100);
        FileNode fileB = new FileNode("b.txt", 250);

        docs.addChild(fileB);
        root.addChild(fileA);
        root.addChild(docs);
        root.addChild(empty);

        TotalSizeVisitor sizeVisitor = new TotalSizeVisitor();
        root.accept(sizeVisitor);
        System.out.println("Total size: " + sizeVisitor.getTotalSize());

        SearchVisitor searchVisitor = new SearchVisitor("b.txt", 100);
        root.accept(searchVisitor);
        List<String> results = searchVisitor.getResults();
        System.out.println("Search: " + results);

        StatisticsVisitor statsVisitor = new StatisticsVisitor();
        root.accept(statsVisitor);
        System.out.println("Files: " + statsVisitor.getFileCount());
        System.out.println("Directories: " + statsVisitor.getDirectoryCount());
        System.out.println("Max depth: " + statsVisitor.getMaxDepth());
    }
}
