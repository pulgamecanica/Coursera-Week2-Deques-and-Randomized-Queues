import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue(){
        items = (Item[]) new Object[2];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    // add the item
    public void enqueue(Item item){
        checkException(item);
        if (items.length == size)
            duplicateStorageQueue();
        items[size++] = item;
    }
    private void resizeStorage(int a){
        Item[] newItem = (Item[]) new Object[a];
        for (int i = 0; i < size; i++) {
            newItem[i] = items[i];
        }
        items = newItem;
    }

    private void duplicateStorageQueue(){
        resizeStorage(items.length * 2);
    }

    private void checkException(Item item){
         if (item == null) 
            throw new IllegalArgumentException("Fuck off");
    }
    // remove and return a random item
    public Item dequeue(){
        if (size == 0)
            throw new NoSuchElementException("No Items!");
        int randomItemIndex = StdRandom.uniform(0, size);
        Item randomItem = items[randomItemIndex];
        size--;
        items[randomItemIndex] = items[size];
        items[size] = null;
        shrink();
        return randomItem;
    }

    private void shrink(){
        if (size > 0 && size == items.length/4) 
            resizeStorage(items.length/2);
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (size == 0)
            throw new NoSuchElementException("No Items!");
        int randomItemIndex = StdRandom.uniform(0, size);
        return items[randomItemIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item>{
        private final Item[] randomItems;
        private int num = 0;

        public RandomIterator() {
            randomItems = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                randomItems[i] = items[i];
            }
            StdRandom.shuffle(randomItems);
        }

        @Override
        public boolean hasNext(){
            return num < size;
        }
        @Override
        public Item next() {
            if (!hasNext()) 
                throw new NoSuchElementException();
            return randomItems[num++];
        }
    }

    // unit testing (required)
    public static void main(String[] args){

    }

}