/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adtllist;

public class LinkedList<T> implements LinkedListInterface<T> {

    private Node firstNode; // reference to first node
    private int length; // number of entries in list
    
    public LinkedList() {
        firstNode = null;
        length = 0;
    }

    @Override
    public boolean add(T newEntry) {
        //this will be used when adding new restaurant and new food item into menu
        Node newNode = new Node(newEntry);//create a new node

        if (isEmpty()){ // if the list is empty
            firstNode = newNode;
        } else { // if the list is not empty, add to end of list
            Node currentNode = firstNode;// traverse linked list with p pointing to the current node

            while (currentNode.next != null) {// while it is not the last node
                currentNode = currentNode.next;
            }
            currentNode.next = newNode; // make last node reference new node
        }
        length++;
        return true;
    }

    @Override
    public T remove(int givenPosition) {
        //this will be used in removing the food items from the menu
        T result = null;  // return value

        if ((givenPosition >= 1) && (givenPosition <= length)) {
            if (givenPosition == 1) { // if the givenPosition is the first entry
                result = firstNode.data;// get the node's data
                firstNode = firstNode.next; //move the fistNode to next 
            } else { // if it's the givenPosition is not the first entry
                Node nodePrevious = firstNode;
                for (int i = 1; i < givenPosition - 1; ++i) {
                    nodePrevious = nodePrevious.next;	//move the nodePrevious to the next
                }
                result = nodePrevious.next.data;  // save the entry to be removed
                nodePrevious.next = nodePrevious.next.next;	// make node before point to node after the
            } 	// one to be deleted (to disconnect node from chain)
            length--;
        }
        return result; // return removed entry, or
        // null if operation fails
    }

    @Override
    public T getEntry(int givenPosition) {
        //this will be used when getting the entry from the restaurant list and the food menu list
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= length)) {
            Node currentNode = firstNode;
            for (int i = 0; i < givenPosition - 1; ++i) { //search each entry from each position
                currentNode = currentNode.next;	//move currentNode to next node
            }
            result = currentNode.data; //gets the currentNode's data
        }

        return result; //return result of currentNode or null
    }

    @Override
    public int getLength() { //get the total length of the list
        //this will be used when I want to know the total length of the list and do loop
        return length;
    }

    @Override
    public boolean isEmpty() { //check if the list is empty
        return firstNode == null;
    }

    @Override
    public String toString() {
        String Str = "";
        Node currentNode = firstNode;
        while (currentNode != null) {
            Str += currentNode.data + "\n";
            currentNode = currentNode.next;
        }
        return Str;
    }


    private class Node {

        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            this.next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    } // end Node

}
