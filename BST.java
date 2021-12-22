public class BST {

    //implementing BST
    private Node root;
    private int size;

    //constructor of the class. the root will initiated to null and size of the BST is 0 at first
    public BST(){
        this.root = null;
        this.size=0;
    }

    public Node getRoot() {
        return this.root;
    }

    //finding an element in the BST. we do it by searching.
    //if the value of the key that we are searching for is equal to current node's key we have found it
    //if its less than it we should search in the left subtree of the current node
    //if its more than it we should search in the right subtree of teh current node
    public Node find(String key){
        Node current = root;
        while(current!=null){

            if(current.getKey().equals(key)){
                return current;
            }else if(compare(current.getKey(),key)){
                current=current.getLeft();
            }else{
                current=current.getRight();
            }
        }

        return null;
    }

    //I count number of the comparisons for searching a key with this method
    //to see should we resize the hashtable or not?
    public int findSearches(String key){
        Node current = root;
        int searches = 0;
        while(current!=null){
            searches++;
            if(current.getKey().equals(key)){
                return searches;
            }else if(compare(current.getKey(),key)){
                current=current.getLeft();
            }else{
                current=current.getRight();
            }
        }

        return searches;
    }

    //adding a new Node to the BST
    public void insert(String key, HashObject object){

        Node addedNode = new Node(key, object);
        this.size++;
        if(root==null){
            root=addedNode;
            return;
        }

        Node current = root;
        Node parent = null;

        //going down in the tree from node to find the current position for adding the new Node
        //It will be added to the end of the tree as a new leaf
        while(true){
            parent=current;
            if(compare(current.getKey(),key)){
                current = current.getLeft();
                if(current==null){
                    parent.setLeft(addedNode);
                    return;
                }
            }else{
                current=current.getRight();
                if(current==null){
                    parent.setRight(addedNode);
                    return;
                }
            }
        }


    }

    //deleting a Node from the BST
    //The node which has our desired key will get removed from the BST
    public boolean delete(String key){
        Node parent = root;
        Node current = root;
        if(root==null){
            return false;
        }
        boolean isLeftChild = false;
        //at first we search in the tree to find it
        while (!current.getKey().equals(key)){
            parent = current;
            if(compare(current.getKey(),key)){
                isLeftChild = true;
                current=current.getLeft();
            }else{
                isLeftChild = false;
                current = current.getRight();
            }

            //if we had not found it we will return false
            if(current == null){
                return false;
            }
        }

        this.size--;
        //It has three cases for removing a node

        //case1: it has no child. (it's the easiest case)
        //we'll easily remove it from the BST by setting this child of its parent node to null
        if(current.getLeft()==null && current.getRight()==null){
            if(current==root){
                root= null;
            }
            if(isLeftChild){
                parent.setLeft(null);
            }else{
                parent.setRight(null);
            }
        }//case2a: it has one child. (left child) The child should be replaced to this node.
        else if(current.getRight() == null){
            if(current==root){
                root = current.getLeft();
            }else if(isLeftChild){
                parent.setLeft(current.getLeft());
            }else{
                parent.setRight(current.getLeft());
            }
        }//case2b: it has one child. (right child) The child should be replaced to this node.
        else if(current.getLeft()==null){
            if(current==root){
                root = current.getRight();
            }else if(isLeftChild){
                parent.setLeft(current.getRight());
            }else{
                parent.setRight(current.getRight());
            }
        }//case3: It has two children. this node should be replaced with tis successor in the tree.
        else{
            Node successor = getSuccessor(current);
            if(current==root){
                root = successor;
            }else if(isLeftChild){
                parent.setLeft(successor);
            }else{
                parent.setRight(successor);
            }

            successor.setLeft(current.getLeft());
        }

        return true;
    }

    public int getSize() {
        return size;
    }

    //method to get the successor node of a node
    private Node getSuccessor(Node node){
        Node successor = null;
        Node successorParent = null;
        Node current = node.getRight();

        while(current!=null){
            successorParent = successor;
            successor = current;
            current = current.getLeft();
        }

        //check if successor has the right child, it cannot have left child for sure
        // if it does have the right child, add it to the left of successorParent.
        if(successor != node.getRight()){
            successorParent.setLeft(successor.getRight());
            successor.setRight(node.getRight());
        }

        return successor;
    }

    //method for comparing strings (keys)
    private boolean compare(String s1, String s2){

        //return true if s1>s2
        if(s1.compareTo(s2)>0){
            return true;
        }else{
            return false;
        }
    }
}
