package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryNode implements Element {
    private String name;
    private List<Element> children = new ArrayList<>();
    private DirectoryNode parent;

    public DirectoryNode(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public DirectoryNode getParent() { return parent; }

    public void setParent(DirectoryNode parent) {
        if (this.parent != null) throw new IllegalStateException("Already has parent");
        this.parent = parent;
    }

    public List<Element> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(Element child) {
        for (Element existing : children) {
            if (existing.getName().equals(child.getName())) {
                throw new IllegalArgumentException("Duplicate name: " + child.getName());
            }
        }

       DirectoryNode current = this;
        while (current != null) {
            if (current == child) throw new IllegalArgumentException("Cycle detected");
            current = current.getParent();
        }

        child.setParent(this);
        children.add(child);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
