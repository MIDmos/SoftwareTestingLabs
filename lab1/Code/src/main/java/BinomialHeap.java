import java.util.Arrays;

public class BinomialHeap {

    private static final int DEFAULT_TREES = 1;

    private int currentSize;
    private BinomialNode[] roots;


    public BinomialHeap() {
        roots = new BinomialNode[DEFAULT_TREES];
        clear();
    }

    public BinomialHeap(int item) {
        currentSize = 1;
        roots = new BinomialNode[1];
        roots[0] = new BinomialNode(item, null, null);
    }


    private void expandTheTrees(int newNumTrees) {
        BinomialNode[] old = roots;
        int oldNumTrees = roots.length;

        roots = new BinomialNode[newNumTrees];
        for (int i = 0; i < oldNumTrees; i++)
            roots[i] = old[i];
        for (int i = oldNumTrees; i < newNumTrees; i++)
            roots[i] = null;
    }


    public void merge(BinomialHeap rhs) {
        if (this == rhs)
            return;

        currentSize += rhs.currentSize;

        if (currentSize > getCapacity()) {
            int newNumTrees = Math.max(roots.length, rhs.roots.length) + 1;
            expandTheTrees(newNumTrees);
        }

        BinomialNode carry = null;
        for (int i = 0, j = 1; j <= currentSize; i++, j *= 2) {
            BinomialNode t1 = roots[i];
            BinomialNode t2 = i < rhs.roots.length ? rhs.roots[i] : null;

            int whichCase = t1 == null ? 0 : 1;
            whichCase += t2 == null ? 0 : 2;
            whichCase += carry == null ? 0 : 4;

            switch (whichCase) {
                case 0:
                case 1:
                    break;
                case 2:
                    roots[i] = t2;
                    rhs.roots[i] = null;
                    break;
                case 4:
                    roots[i] = carry;
                    carry = null;
                    break;
                case 3:
                    carry = combineTrees(t1, t2);
                    roots[i] = rhs.roots[i] = null;
                    break;
                case 5:
                    carry = combineTrees(t1, carry);
                    roots[i] = null;
                    break;
                case 6:
                    carry = combineTrees(t2, carry);
                    rhs.roots[i] = null;
                    break;
                case 7:
                    roots[i] = carry;
                    carry = combineTrees(t1, t2);
                    rhs.roots[i] = null;
                    break;
            }
        }

        for (int k = 0; k < rhs.roots.length; k++)
            rhs.roots[k] = null;
        rhs.currentSize = 0;
    }

    private BinomialNode combineTrees(BinomialNode t1, BinomialNode t2) {
        if (t1.element > t2.element)
            return combineTrees(t2, t1);
        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;
    }

    public void insert(int x) {
        merge(new BinomialHeap(x));
    }

    public int findMin() {
        if (isEmpty())
            throw new IllegalStateException("Tree is empty");

        return roots[findMinIndex()].element;
    }


    private int findMinIndex() {
        int i;
        int minIndex;

        for (i = 0; roots[i] == null; i++) {
        }

        for (minIndex = i; i < roots.length; i++)
            if (roots[i] != null &&
                    roots[i].element < roots[minIndex].element)
                minIndex = i;

        return minIndex;
    }

    public int deleteMin() {
        if (isEmpty())
            throw new IllegalStateException("Tree is empty");

        int minIndex = findMinIndex();
        int minItem = roots[minIndex].element;

        BinomialNode deletedTree = roots[minIndex].leftChild;

        BinomialHeap deletedQueue = new BinomialHeap();
        deletedQueue.expandTheTrees(minIndex + 1);

        deletedQueue.currentSize = (1 << minIndex) - 1;
        for (int j = minIndex - 1; j >= 0; j--) {
            deletedQueue.roots[j] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            deletedQueue.roots[j].nextSibling = null;
        }

        roots[minIndex] = null;
        currentSize -= deletedQueue.currentSize + 1;

        merge(deletedQueue);

        return minItem;
    }


    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void clear() {
        currentSize = 0;
        Arrays.fill(roots, null);
    }

    public int getSize() {
        return currentSize;
    }

    private int getCapacity() {
        return (1 << roots.length) - 1;
    }


    private static class BinomialNode {

        BinomialNode(int theElement, BinomialNode lt, BinomialNode nt) {
            element = theElement;
            leftChild = lt;
            nextSibling = nt;
        }

        int element;
        BinomialNode leftChild;
        BinomialNode nextSibling;
    }
}
