package Practice_Lesson3;

import java.util.NoSuchElementException;

/**
 * Created by Ilyuza on 11/28/2017.
 */
public class MyLinkedList<T> {

    Node head;

    int length = 0;

    public MyLinkedList() {

        this.head = null;
    }

    public MyLinkedList(T value) {
        addFirst(value);
        length++;
    }

    public Node getHead() {
        return head;
    }

    public void addFirst(T value) {

        if (isEmpty()){
            head = new Node(value);
        }
        else {
            head = new Node(value, head);
        }
        length++;
    }

    public void addLast(T value) {

        if (isEmpty()){
            head = new Node(value);
        }
        else {
            Node node = getLastNode();
            node.next = new Node(value);
        }

        length++;
    }

    public void add(T value) {
        addLast(value);
    }

    public Object getFirst(){
        return head.value;
    }

    public Object getLast(){

        return getLastNode().value;
    }

    private Node getLastNode(){

        Node node = head;
        while (node.next != null){
            node = node.next;
        }
        return node;
    }

    public void deleteFirst() throws NoSuchElementException{
        try {
            if (head == null)
                throw new NoSuchElementException();
            head = head.next;
            length--;
        } catch (NoSuchElementException e){
            System.out.println("You can't delete first element. It doesn't exist.");

        }

    }

    public void deleteLast() throws NoSuchElementException{
        try {
            if (head == null)
                throw new NoSuchElementException();

            if (head.next == null){
                head = null;
            }
            else {
                Node node = head;
                while (node.next.next != null) {
                    node = node.next;
                }
                node.next = null;
            }
            length--;
        } catch (NoSuchElementException e){
            System.out.println("You can't delete last element. It doesn't exist.");
        }

    }

    public void delete(){
        deleteLast();
    }

    public void deleteAll(){
        head = null;
    }
    public boolean isEmpty(){
        return this.head == null;
    }

    public int getLength(){
        return this.length;
    }

    @Override
    public String toString() throws NoSuchElementException {

        try {
            if (head == null){
                throw new NoSuchElementException();
            }

            Node node = head;
            String s = "";
            while (node.next != null) {
                s += node + "\n";
                node = node.next;
            }
            s += node + "\n";
            return s;

        } catch (NoSuchElementException e){
            System.out.println("List is empty. No element to print");
            return "";
        }


    }
}
