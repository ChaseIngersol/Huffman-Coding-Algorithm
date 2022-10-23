package HuffmanCodeImpl;

public class PriorityQueue {
    // array in sorted order, from max at 0 to min at size-1
    private int maxSize;
    private Node[] queArray;
    private int nItems;

    public PriorityQueue(int s) {
        maxSize = s;
        queArray = new Node[maxSize];
        nItems = 0;
    }

    public void insert(Node item) {
        int j;
        if (nItems == 0) { // if no items,
            queArray[nItems++] = item; // insert at 0
        } else { // if items,
            for (j = nItems - 1; j >= 0; j--) { // start at end,
                if (item.freq > queArray[j].freq) { // if new item larger,
                    queArray[j + 1] = queArray[j]; // shift upward
                } else { // if smaller,
                    break; // done shifting
                }
            }
            queArray[j + 1] = item; // insert it
            nItems++;
        }
    }

    public Node remove() {
        return queArray[--nItems];
    }

    public Node peekMin() {
        return queArray[nItems - 1];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

    public int size() {
        return this.nItems;
    }

} 

