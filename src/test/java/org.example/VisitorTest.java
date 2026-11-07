package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class VisitorTest {

    @Test
    void t1_doubleDispatch() {
        Visitor visitor = new Visitor() {
            @Override
            public void visit(FileNode file) {
                assertEquals("a.txt", file.getName());
            }
            @Override
            public void visit(DirectoryNode dir) {
                fail("Should not visit directory");
            }
        };
        new FileNode("a.txt", 10).accept(visitor);
    }

    @Test
    void t2_sizeNesting() {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode sub = new DirectoryNode("sub");
        root.addChild(new FileNode("a.txt", 10));
        root.addChild(sub);
        sub.addChild(new FileNode("b.txt", 20));

        TotalSizeVisitor visitor = new TotalSizeVisitor();
        root.accept(visitor);
        assertEquals(30, visitor.getTotalSize());
    }

    @Test
    void t3_searchBothConditions() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("a.txt", 50));
        root.addChild(new FileNode("b.txt", 10));

        SearchVisitor visitor = new SearchVisitor("a.txt", 40);
        root.accept(visitor);
        assertEquals(1, visitor.getResults().size());
    }

    @Test
    void t4_searchPathsSorted() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("same.txt", 10));
        DirectoryNode sub = new DirectoryNode("sub");
        sub.addChild(new FileNode("same.txt", 10));
        root.addChild(sub);

        SearchVisitor visitor = new SearchVisitor("same.txt", 5);
        root.accept(visitor);
        assertEquals(List.of("same.txt", "sub/same.txt"), visitor.getResults());
    }

    @Test
    void t5_statistics() {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode sub = new DirectoryNode("sub");
        root.addChild(new FileNode("a.txt", 10));
        root.addChild(sub);

        StatisticsVisitor visitor = new StatisticsVisitor();
        root.accept(visitor);
        assertEquals(1, visitor.getFileCount());
        assertEquals(2, visitor.getDirectoryCount());
        assertEquals(1, visitor.getMaxDepth());
    }

    @Test
    void t6_emptyAndNoMatch() {
        DirectoryNode root = new DirectoryNode("root");
        SearchVisitor visitor = new SearchVisitor("none.txt", 0);
        root.accept(visitor);
        assertTrue(visitor.getResults().isEmpty());
    }

    @Test
    void t7_noStateLeakBetweenNewVisitors() {
        DirectoryNode root = new DirectoryNode("root");
        root.addChild(new FileNode("a.txt", 10));

        TotalSizeVisitor firstVisitor = new TotalSizeVisitor();
        root.accept(firstVisitor);
        long firstTotal = firstVisitor.getTotalSize();

        TotalSizeVisitor secondVisitor = new TotalSizeVisitor();
        root.accept(secondVisitor);
        assertEquals(firstTotal, secondVisitor.getTotalSize());
    }

    @Test
    void t8_integrity() {
        DirectoryNode root = new DirectoryNode("root");

        assertThrows(IllegalArgumentException.class, () -> new FileNode("neg.txt", -1));

        root.addChild(new FileNode("dup.txt", 1));
        assertThrows(IllegalArgumentException.class,
                () -> root.addChild(new FileNode("dup.txt", 1)));

        DirectoryNode sub = new DirectoryNode("sub");
        root.addChild(sub);
        assertThrows(IllegalArgumentException.class, () -> sub.addChild(root));
    }
}
