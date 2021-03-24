import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinomialTreeTest {

    private BinomialTree tree;

    @BeforeEach
    private void setup() {
        tree = new BinomialTree();
    }

    //Size test
    @Test
    void tree_EmptySizeTest() {
        assertEquals(0, tree.getSize());
    }

    @Test
    void empty_Test() {
        assertTrue(tree.isEmpty());
    }

    @Test
    void empty_DeleteTest() {
        tree.insert(1);
        tree.deleteMin();
        assertTrue(tree.isEmpty());
    }

    @Test
    void empty_FalseTest() {
        tree.insert(1);
        assertFalse(tree.isEmpty());
    }

    @Test
    void size_Insert100Test() {
        final int count = 100;
        for (int i = 0; i < count; i++) {
            tree.insert(1);
        }
        assertEquals(count, tree.getSize());
    }

    @Test
    void size_ClearTest() {
        final int count = 100;
        for (int i = 0; i < count; i++) {
            tree.insert(1);
        }
        tree.clear();
        assertEquals(0, tree.getSize());
    }

    @Test
    void size_ClearEmptyTest() {
        assertDoesNotThrow(() -> tree.clear());
    }

    @Test
    void size_InsertDeleteTest() {
        final int insertCount = 100;
        final int deleteCount = 20;
        for (int i = 0; i < insertCount; i++) {
            tree.insert(1);
        }
        for (int i = 0; i < deleteCount; i++) {
            tree.deleteMin();
        }
        assertEquals(insertCount - deleteCount, tree.getSize());
    }

    @Test
    void size_InsertDeleteInsertTest() {
        final int insertCount = 100;
        final int deleteCount = 20;
        final int insertCount2 = 6;
        for (int i = 0; i < insertCount; i++) {
            tree.insert(1);
        }
        for (int i = 0; i < deleteCount; i++) {
            tree.deleteMin();
        }
        for (int i = 0; i < insertCount2; i++) {
            tree.insert(1);
        }
        assertEquals(insertCount + insertCount2 - deleteCount, tree.getSize());
    }
    //Size test


    //Exceptions test
    @Test
    void delete_FromEmptyTest() {
        assertThrows(IllegalStateException.class, () -> tree.deleteMin());
    }

    @Test
    void findMin_FromEmptyTest() {
        assertThrows(IllegalStateException.class, () -> tree.findMin());
    }
    //Exceptions test


    //Sort test
    @Test
    void deleteMin_RandomNumbersSortTest() {
        int[] source = {1, -4, 5, 7, 8, -20, 1, 2, 100};
        int[] result = {-20, -4, 1, 1, 2, 5, 7, 8, 100};
        for (int s : source) {
            tree.insert(s);
        }
        for (int r : result) {
            assertEquals(r, tree.deleteMin());
        }
    }

    @Test
    void deleteMin_OneDifferentSortTest() {
        int[] source = {4, 4, 4, 4, 2, 4, 4, 4, 4};
        int[] result = {2, 4, 4, 4, 4, 4, 4, 4, 4};
        for (int s : source) {
            tree.insert(s);
        }
        for (int r : result) {
            assertEquals(r, tree.deleteMin());
        }
    }

    @Test
    void deleteMin_SortedNumbersSortTest() {
        int[] source = {-50, -6, -2, 0, 0, 1, 2, 3, 3, 10, 100, 578};
        int[] result = {-50, -6, -2, 0, 0, 1, 2, 3, 3, 10, 100, 578};
        for (int s : source) {
            tree.insert(s);
        }
        for (int r : result) {
            assertEquals(r, tree.deleteMin());
        }
    }

    @Test
    void deleteMin_ReversedNumbersSortTest() {
        int[] source = {798, 54, 20, 11, 4, 3, 2, 2, 0, -5, -9};
        int[] result = {-9, -5, 0, 2, 2, 3, 4, 11, 20, 54, 798};
        for (int s : source) {
            tree.insert(s);
        }
        for (int r : result) {
            assertEquals(r, tree.deleteMin());
        }
    }

    @Test
    void findMin_RandomNumbersFindMinTest() {
        int[] source = {-10, -121, -5, -7, -8, -20, -1, -2};
        int min = -121;
        for (int s : source) {
            tree.insert(s);
        }
        assertEquals(min, tree.findMin());
    }

    @Test
    void findMin_SortedNumbersFindMinTest() {
        int[] source = {3, 3, 10, 23, 100, 578};
        int min = 3;
        for (int s : source) {
            tree.insert(s);
        }
        assertEquals(min, tree.findMin());
    }

    @Test
    void findMin_ReversedNumbersFindMinTest() {
        int[] source = {798, 54, 20, 11, 4, 3, 2, 2};
        int min = 2;
        for (int s : source) {
            tree.insert(s);
        }
        assertEquals(min, tree.findMin());
    }

    @Test
    void findMin_OneDifferentSortTest() {
        int[] source = {4, 4, 4, 4, 2, 4, 4, 4, 4};
        int min = 2;
        for (int s : source) {
            tree.insert(s);
        }
        assertEquals(min, tree.findMin());
    }
    //Sort test


    //Merge test
    @Test
    void merge_OneElementTest() {
        tree.insert(4);
        tree.insert(5);
        final BinomialTree secondTree = new BinomialTree(2);
        tree.merge(secondTree);
        assertEquals(2, tree.deleteMin());
        assertEquals(4, tree.deleteMin());
        assertEquals(5, tree.deleteMin());
    }

    @Test
    void merge_SmallToBigTest() {
        tree.insert(2);
        tree.insert(4);
        tree.insert(3);
        tree.insert(8);
        tree.insert(10);
        final BinomialTree secondTree = new BinomialTree(1);
        secondTree.insert(3);
        secondTree.insert(9);
        tree.merge(secondTree);
        assertEquals(1, tree.deleteMin());
        assertEquals(2, tree.deleteMin());
        assertEquals(3, tree.deleteMin());
        assertEquals(3, tree.deleteMin());
        assertEquals(4, tree.deleteMin());
        assertEquals(8, tree.deleteMin());
        assertEquals(9, tree.deleteMin());
        assertEquals(10, tree.deleteMin());
    }

    @Test
    void merge_BigToSmallTest() {
        final BinomialTree secondTree = new BinomialTree(-1);
        secondTree.insert(-2);
        secondTree.insert(-4);
        secondTree.insert(-3);
        secondTree.insert(-8);
        secondTree.insert(-10);

        tree.insert(-3);
        tree.insert(-9);
        tree.merge(secondTree);
        assertEquals(-10, tree.deleteMin());
        assertEquals(-9, tree.deleteMin());
        assertEquals(-8, tree.deleteMin());
        assertEquals(-4, tree.deleteMin());
        assertEquals(-3, tree.deleteMin());
        assertEquals(-3, tree.deleteMin());
        assertEquals(-2, tree.deleteMin());
        assertEquals(-1, tree.deleteMin());
    }

    @Test
    void merge_SameSizeTest() {
        final BinomialTree secondTree = new BinomialTree();
        tree.insert(12);
        secondTree.insert(12);
        tree.insert(-3);
        secondTree.insert(-3);
        tree.insert(-9);
        secondTree.insert(-9);
        tree.insert(5);
        secondTree.insert(5);

        tree.merge(secondTree);
        assertEquals(-9, tree.deleteMin());
        assertEquals(-9, tree.deleteMin());
        assertEquals(-3, tree.deleteMin());
        assertEquals(-3, tree.deleteMin());
        assertEquals(5, tree.deleteMin());
        assertEquals(5, tree.deleteMin());
        assertEquals(12, tree.deleteMin());
        assertEquals(12, tree.deleteMin());
    }

    @Test
    void merge_SelfTest() {
        tree.insert(12);
        tree.insert(-3);
        tree.insert(-9);
        tree.insert(5);

        final BinomialTree secondTree = tree;
        tree.merge(secondTree);
        assertEquals(-9, tree.deleteMin());
        assertEquals(-3, tree.deleteMin());
        assertEquals(5, tree.deleteMin());
        assertEquals(12, tree.deleteMin());
    }
    //Merge test

}
