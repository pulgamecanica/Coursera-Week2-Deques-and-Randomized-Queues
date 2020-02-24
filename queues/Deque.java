import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

    private Node firstNode;
    private Node lastNode;
    private int size = 0;
    
    private class Node {
        Item item;
        Node next;
        Node prev;
    }
    // construct an empty deque
    public Deque(){
    }

    // is the deque empty?
    public boolean isEmpty(){
        return size  == 0;
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null)
            throw new IllegalArgumentException("Fuck off");
        Node nodeToRemove = firstNode;
        firstNode = new Node();
        firstNode.item = item;
        firstNode.next = nodeToRemove;
        if(size > 0)
            nodeToRemove.prev = firstNode;
        else    
            lastNode = firstNode;
        size++;
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null)
            throw new IllegalArgumentException("Fuck off");
        Node oldLast = lastNode;
        lastNode = new Node();
        lastNode.item = item;
        lastNode.prev = oldLast;
        if(size > 0)
            oldLast.next = lastNode;
        else    
            firstNode = lastNode;
        size ++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()) 
            throw new NoSuchElementException();
        Item itemToRemove = firstNode.item;
        if(size > 1){
            firstNode.prev = null;
            firstNode = firstNode.next;
        }else{
            lastNode = null;
            firstNode = null;
        }
        size--;
        return itemToRemove;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty()) 
            throw new NoSuchElementException();
        Item lastToRemove = lastNode.item;
        if(size > 1){
            lastNode.next = null;
            lastNode = lastNode.prev;
        }else{
            firstNode = null;
            lastNode = null;
        }
        size--;
        return lastToRemove;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new GeneralIterator();  
    }

    private class GeneralIterator implements Iterator<Item> {
        private Node precentNode = firstNode;

        public boolean hasNext() {
            return precentNode != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next()
        {
            if (!hasNext()) 
                throw new NoSuchElementException();
            Item result = precentNode.item;
            precentNode = precentNode.next;
            return result;
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        //TEST CLIENT
    }
}