package Practice_Lesson3;

/**
 * Created by Ilyuza on 11/28/2017.
 */
class Node<T> {

    T value;
    Node next;

    Node(){
        this.value = null;
        this.next = null;
    }

    Node(T value) {
        this.value = value;
        this.next = null;
    }

    Node(T value, Node next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
