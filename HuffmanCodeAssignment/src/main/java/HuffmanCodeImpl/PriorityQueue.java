package HuffmanCodeImpl;

public class PriorityQueue {
    // array in sorted order, from max at 0 to min at size-1
    private int maxSize;
    private HuffmanNode[] queArray;
    private int nItems;

    //-------------------------------------------------------------
    public PriorityQueue(int s) // constructor
    {
        maxSize = s;
        queArray = new HuffmanNode[maxSize];
        nItems = 0;
    }

    //-------------------------------------------------------------
    public void insert(HuffmanNode item) // insert item
    {
        int j;
        if (nItems == 0) // if no items,
            queArray[nItems++] = item; // insert at 0
        else // if items,
        {
            for (j = nItems - 1; j >= 0; j--) // start at end,
            {
                if (item.c > queArray[j].c) // if new item larger,
                    queArray[j + 1] = queArray[j]; // shift upward


                else // if smaller,
                    break; // done shifting
            } // end for
            queArray[j + 1] = item; // insert it
            nItems++;
        } // end else (nItems > 0)
    } // end insert()

    //-------------------------------------------------------------
    public HuffmanNode remove() // remove minimum item
    {
        return queArray[--nItems];
    }

    //-------------------------------------------------------------
    public HuffmanNode peekMin() // peek at minimum item
    {
        return queArray[nItems - 1];
    }

    //-------------------------------------------------------------
    public boolean isEmpty() // true if queue is empty
    {
        return (nItems == 0);
    }

    //-------------------------------------------------------------
    public boolean isFull() // true if queue is full
    {
        return (nItems == maxSize);
    }

    public int size(){
        return this.nItems;
    }
//-------------------------------------------------------------
} // end class PriorityQueue
