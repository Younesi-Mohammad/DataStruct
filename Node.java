public class Node {

    //class of node. it's consist of a pair of left node and right node and they initiated to null with the constructor
    //It has a key and we do all comparisons of the bst with this key
    //it has a data which is our HashObject
    private Node left, right;
    private String key;
    private HashObject data;

    public Node(String key, HashObject data) {
        this.data = data;
        this.key = key;
        this.left = null;
        this.right = null;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public HashObject getData() {
        return data;
    }

    public void setData(HashObject data) {
        this.data = data;
    }
}
