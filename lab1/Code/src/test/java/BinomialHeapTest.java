import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinomialHeapTest {

    private BinomialHeap heap;

    @BeforeEach
    private void setup() {
        heap = new BinomialHeap();
    }

    //Size test
    @Test
    void size_EmptySizeTest() {
        assertEquals(0, heap.getSize());
    }

    @Test
    void empty_Test() {
        assertTrue(heap.isEmpty());
    }

    @Test
    void empty_DeleteTest() {
        heap.insert(1);
        heap.deleteMin();
        assertTrue(heap.isEmpty());
    }

    @Test
    void empty_FalseTest() {
        heap.insert(1);
        assertFalse(heap.isEmpty());
    }

    @Test
    void size_Insert100Test() {
        final int count = 100;
        for (int i = 0; i < count; i++) {
            heap.insert(1);
        }
        assertEquals(count, heap.getSize());
    }

    @Test
    void size_ClearTest() {
        final int count = 100;
        for (int i = 0; i < count; i++) {
            heap.insert(1);
        }
        heap.clear();
        assertEquals(0, heap.getSize());
    }

    @Test
    void size_ClearEmptyTest() {
        assertDoesNotThrow(() -> heap.clear());
    }

    @Test
    void size_InsertDeleteTest() {
        final int insertCount = 100;
        final int deleteCount = 20;
        for (int i = 0; i < insertCount; i++) {
            heap.insert(1);
        }
        for (int i = 0; i < deleteCount; i++) {
            heap.deleteMin();
        }
        assertEquals(insertCount - deleteCount, heap.getSize());
    }

    @Test
    void size_InsertDeleteInsertTest() {
        final int insertCount = 100;
        final int deleteCount = 20;
        final int insertCount2 = 6;
        for (int i = 0; i < insertCount; i++) {
            heap.insert(1);
        }
        for (int i = 0; i < deleteCount; i++) {
            heap.deleteMin();
        }
        for (int i = 0; i < insertCount2; i++) {
            heap.insert(1);
        }
        assertEquals(insertCount + insertCount2 - deleteCount, heap.getSize());
    }
    //Size test


    //Exceptions test
    @Test
    void delete_FromEmptyTest() {
        assertThrows(IllegalStateException.class, () -> heap.deleteMin());
    }

    @Test
    void findMin_FromEmptyTest() {
        assertThrows(IllegalStateException.class, () -> heap.findMin());
    }
    //Exceptions test


    //Sort test
    @Test
    void deleteMin_RandomNumbersSortTest() {
        int[] source = {1, -4, 5, 7, 8, -20, 1, 2, 100};
        int[] result = {-20, -4, 1, 1, 2, 5, 7, 8, 100};
        for (int s : source) {
            heap.insert(s);
        }
        for (int r : result) {
            assertEquals(r, heap.deleteMin());
        }
    }

    @Test
    void deleteMin_OneDifferentSortTest() {
        int[] source = {4, 4, 4, 4, 2, 4, 4, 4, 4};
        int[] result = {2, 4, 4, 4, 4, 4, 4, 4, 4};
        for (int s : source) {
            heap.insert(s);
        }
        for (int r : result) {
            assertEquals(r, heap.deleteMin());
        }
    }

    @Test
    void deleteMin_SortedNumbersSortTest() {
        int[] source = {-50, -6, -2, 0, 0, 1, 2, 3, 3, 10, 100, 578};
        int[] result = {-50, -6, -2, 0, 0, 1, 2, 3, 3, 10, 100, 578};
        for (int s : source) {
            heap.insert(s);
        }
        for (int r : result) {
            assertEquals(r, heap.deleteMin());
        }
    }

    @Test
    void deleteMin_ReversedNumbersSortTest() {
        int[] source = {798, 54, 20, 11, 4, 3, 2, 2, 0, -5, -9};
        int[] result = {-9, -5, 0, 2, 2, 3, 4, 11, 20, 54, 798};
        for (int s : source) {
            heap.insert(s);
        }
        for (int r : result) {
            assertEquals(r, heap.deleteMin());
        }
    }

    @Test
    void findMin_RandomNumbersFindMinTest() {
        int[] source = {-10, -121, -5, -7, -8, -20, -1, -2};
        int min = -121;
        for (int s : source) {
            heap.insert(s);
        }
        assertEquals(min, heap.findMin());
    }

    @Test
    void findMin_SortedNumbersFindMinTest() {
        int[] source = {3, 3, 10, 23, 100, 578};
        int min = 3;
        for (int s : source) {
            heap.insert(s);
        }
        assertEquals(min, heap.findMin());
    }

    @Test
    void findMin_ReversedNumbersFindMinTest() {
        int[] source = {798, 54, 20, 11, 4, 3, 2, 2};
        int min = 2;
        for (int s : source) {
            heap.insert(s);
        }
        assertEquals(min, heap.findMin());
    }

    @Test
    void findMin_OneDifferentSortTest() {
        int[] source = {4, 4, 4, 4, 2, 4, 4, 4, 4};
        int min = 2;
        for (int s : source) {
            heap.insert(s);
        }
        assertEquals(min, heap.findMin());
    }
    //Sort test


    //Merge test
    @Test
    void merge_OneElementTest() {
        heap.insert(4);
        heap.insert(5);
        final BinomialHeap heap2 = new BinomialHeap(2);
        heap.merge(heap2);
        assertEquals(2, heap.deleteMin());
        assertEquals(4, heap.deleteMin());
        assertEquals(5, heap.deleteMin());
    }

    @Test
    void merge_SmallToBigTest() {
        heap.insert(2);
        heap.insert(4);
        heap.insert(3);
        heap.insert(8);
        heap.insert(10);
        final BinomialHeap heap2 = new BinomialHeap(1);
        heap2.insert(3);
        heap2.insert(9);
        heap.merge(heap2);
        assertEquals(1, heap.deleteMin());
        assertEquals(2, heap.deleteMin());
        assertEquals(3, heap.deleteMin());
        assertEquals(3, heap.deleteMin());
        assertEquals(4, heap.deleteMin());
        assertEquals(8, heap.deleteMin());
        assertEquals(9, heap.deleteMin());
        assertEquals(10, heap.deleteMin());
    }

    @Test
    void merge_BigToSmallTest() {
        final BinomialHeap heap2 = new BinomialHeap(-1);
        heap2.insert(-2);
        heap2.insert(-4);
        heap2.insert(-3);
        heap2.insert(-8);
        heap2.insert(-10);

        heap.insert(-3);
        heap.insert(-9);
        heap.merge(heap2);
        assertEquals(-10, heap.deleteMin());
        assertEquals(-9, heap.deleteMin());
        assertEquals(-8, heap.deleteMin());
        assertEquals(-4, heap.deleteMin());
        assertEquals(-3, heap.deleteMin());
        assertEquals(-3, heap.deleteMin());
        assertEquals(-2, heap.deleteMin());
        assertEquals(-1, heap.deleteMin());
    }

    @Test
    void merge_SameSizeTest() {
        final BinomialHeap heap2 = new BinomialHeap();
        heap.insert(12);
        heap2.insert(12);
        heap.insert(-3);
        heap2.insert(-3);
        heap.insert(-9);
        heap2.insert(-9);
        heap.insert(5);
        heap2.insert(5);

        heap.merge(heap2);
        assertEquals(-9, heap.deleteMin());
        assertEquals(-9, heap.deleteMin());
        assertEquals(-3, heap.deleteMin());
        assertEquals(-3, heap.deleteMin());
        assertEquals(5, heap.deleteMin());
        assertEquals(5, heap.deleteMin());
        assertEquals(12, heap.deleteMin());
        assertEquals(12, heap.deleteMin());
    }

    @Test
    void merge_SelfTest() {
        heap.insert(12);
        heap.insert(-3);
        heap.insert(-9);
        heap.insert(5);

        final BinomialHeap heap2 = heap;
        heap.merge(heap2);
        assertEquals(-9, heap.deleteMin());
        assertEquals(-3, heap.deleteMin());
        assertEquals(5, heap.deleteMin());
        assertEquals(12, heap.deleteMin());
    }
    //Merge test

}
