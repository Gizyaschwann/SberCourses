package Practice_Lesson3;

/**
 * Created by Ilyuza on 11/28/2017.
 */
public class Main {
    public static void main(String[] args) {

        MyLinkedList linkedList = new MyLinkedList();
        MyLinkedList linkedList1 = new MyLinkedList();

        linkedList.add(2);
        linkedList.add("SSS");
        linkedList.addLast(12344);

        System.out.println("Let's look at our list:");
        System.out.println(linkedList);

        linkedList.deleteLast();
        linkedList.deleteFirst();
        linkedList.delete();
        linkedList.delete();

        System.out.println("\nLet's look at our list after deletion:");
        System.out.println(linkedList);

        System.out.println("Let's look at our list1:");
        System.out.println(linkedList1);

    }
}
