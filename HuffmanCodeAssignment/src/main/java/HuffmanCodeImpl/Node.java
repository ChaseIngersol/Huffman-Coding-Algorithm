package HuffmanCodeImpl;

class Node {

    int freq;
    char ch;

    Node left;
    Node right;

    public Node(){

    }

    public Node(char ch, int freq){
        this.ch = ch;
        this.freq = freq;
    }
}
