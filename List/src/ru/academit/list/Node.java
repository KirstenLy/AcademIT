package ru.academit.list;

class Node<E> {
    private Node<E> previousNode;
    private Node<E> nextNode;
    private E nodeValue;

    Node(Node<E> previousNode, Node<E> nextNode, E nodeValue) {
        this.previousNode = previousNode;
        this.nextNode = nextNode;
        this.nodeValue = nodeValue;
    }

    public void setNodeValue(E nodeValue) {
        this.nodeValue = nodeValue;
    }

    public void setPreviousNode(Node<E> previousNode) {
        this.previousNode = previousNode;
    }

    public void setNextNode(Node<E> nextNode) {
        this.nextNode = nextNode;
    }

    public Node<E> getPreviousNode() {
        return previousNode;
    }

    public Node<E> getNextNode() {
        return nextNode;
    }

    public E getNodeValue() {
        return nodeValue;
    }
}
