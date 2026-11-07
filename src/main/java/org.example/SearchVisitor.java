package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchVisitor implements Visitor {
    private String nameToFind;
    private long minSize;
    private List<String> results = new ArrayList<>();

    public SearchVisitor(String nameToFind, long minSize) {
        this.nameToFind = nameToFind;
        this.minSize = minSize;
    }

    public void visit(FileNode file) {
        if (file.getName().equals(nameToFind) && file.getSize() > minSize) {
            results.add(buildPath(file));
        }
    }

    public void visit(DirectoryNode dir) {
        for (Element child : dir.getChildren()) {
            child.accept(this);
        }
    }

     private String buildPath(Element node) {
        List<String> parts = new ArrayList<>();
        Element current = node;
        while (current != null) {
            parts.add(current.getName());
            current = current.getParent();
        }
        Collections.reverse(parts);
        parts.remove(0); 
        return String.join("/", parts);
    }

    public List<String> getResults() {
        List<String> sorted = new ArrayList<>(results);
        Collections.sort(sorted);
        return sorted;
    }
}
