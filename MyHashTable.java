import java.util.Stack;

public class MyHashTable {

    private int size;
    private boolean initiated = false;
    private int maxSearch;
    private BST[] table;

    //Constructor of the MyHashtable class
    public MyHashTable(int size, int maxSearch) throws InvalidInputException{

        if(size<=0){
            System.out.println("It's not a valid size.");
            throw new InvalidInputException();
        }
        if(maxSearch<=0){
            System.out.println("it's not a valid value for max search");
            throw new InvalidInputException();
        }
        this.size = size;
        this.table = new BST[size];
        this.initiated = true;
        this.maxSearch = maxSearch;
        for(int i=0; i<size; i++){
            table[i] = new BST();
        }
    }

    //adding an object to the table
    public boolean add(HashObject value){
        if(value==null){
            System.out.println("You tried to add a null object!");
            return false;
        }
        if(value.getString().isEmpty()){
            System.out.println("You tried to add an object with empty string!");
            return false;
        }
        int index = getHash(value.hashValue());
        if(!this.initiated){
            return false;
        }
        //each row of the table is corresponding to a binary search tree
        BST tree = table[index];
        //if the object was not in that BST, we will add it to the BST
        if(tree.find(value.getString()) == null){
            tree.insert(value.getString(),value);
            return true;
        }else{
            return false;
        }

    }

    //find an object. at first we calculate its row in the hashtable by using its hash
    //after that we search in the corresponding BST to find it
    public HashObject find(HashObject value){

        if(value==null){
            return null;
        }
        if(value.getString().isEmpty()){
            System.out.println("You tried to find an object with empty string!");
            return null;
        }
        int index = getHash(value.hashValue());
        BST tree = table[index];
        HashObject founded;
        if(tree.find(value.getString()) != null){
            founded = tree.find(value.getString()).getData();
        }else{
            founded = null;
        }

        //check whether we have exceeded the maxSearch limit.
        //if we did, we call the resize method
        if(tree.findSearches(value.getString()) >= this.maxSearch){
            this.resize();
        }
        return founded;
    }

    //deleting an object from hashtable
    public boolean delete(HashObject value){
        if(value == null){
            return false;
        }
        if(value.getString().isEmpty()){
            return false;
        }
        int index = getHash(value.hashValue());
        return table[index].delete(value.getString());
    }

    //printing size of each of the BSTs in the hashtable
    String printSizes(){
        String out = "";
        for(BST bst:table){
            out = out + Integer.toString(bst.getSize())+"\n";
        }

        return out;
    }

    //calculating hash and forcing it to be between 0 and (size-1)
    private int getHash(Integer x){

        int hash = x.hashCode()%size;
        if(hash<0){
            hash += size;
        }

        return hash;
    }

    //this method is used for resizing the hashtable from x to 2x+1
    //we gget all the objects in the hashtable, then create a new table and add all that items to it
    private void resize(){

        int objectNums = 0;
        for(BST bst:table){
            objectNums += bst.getSize();
        }

        //System.out.println(objectNums);
        int i=0;
        HashObject[] objects = new HashObject[objectNums];


        for(BST bst:table){
            i = addObjects(bst.getRoot(),objects,i);
        }

        this.size = 2*this.size+1;
        this.table = new BST[size];

        for(int j=0; j<size; j++){
            table[j] = new BST();
        }


        for(HashObject object:objects){
            this.add(object);
        }

    }

    //traverse nodes of the bst and add all its nodes to the stack
    private int addObjects(Node root, HashObject[] objects,int i){
        Node current, pre;

        if(root == null){
            return i;
        }

        current = root;
        while(current != null){
            if(current.getLeft() == null){
                objects[i] = current.getData();
                i++;
                current = current.getRight();
            }else{
                pre = current.getLeft();
                while(pre.getRight() != null && pre.getRight() != current){
                    pre = pre.getRight();
                }

                if(pre.getRight() == null){
                    pre.setRight(current);
                    current = current.getLeft();
                }else{
                    pre.setRight(null);
                    objects[i] = current.getData();
                    i++;
                    current = current.getRight();
                }
            }
        }

        return i;
    }
}
