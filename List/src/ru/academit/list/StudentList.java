package ru.academit.list;

public class StudentList<E> {
    private Node<E> firstNode;
    private Node<E> lastNode;
    private int length = 0;

    public StudentList() {
        lastNode = new Node<>(firstNode, null, null);
        firstNode = new Node<>(null, lastNode, null);
    }

    public void addToEndOfList(E e) {
        Node<E> temp = lastNode;
        temp.setNodeValue(e);
        lastNode = new Node<>(temp, null, e);
        temp.setNextNode(lastNode);
        length++;
    }

    public void addToStartOfList(E e) {
        Node<E> temp = firstNode;
        temp.setNodeValue(e);
        firstNode = new Node<>(null, temp, e);
        temp.setPreviousNode(firstNode);
        length++;
    }

    public E getFirstElement() {
        return this.firstNode.getNodeValue();
    }

    public void setFirstElement(E e) {
        this.firstNode.setNodeValue(e);
    }

    public E getElementById(int id) {
        if (id >= this.length || id < 0) {
            throw new NullPointerException("getElementById ERROR: Incorrect id");
        }
        Node<E> resultNode = firstNode;
        for (int i = 0; i < id; i++) {
            resultNode = resultNode.getNextNode();
        }
        return resultNode.getNodeValue();
    }

    public void setElementById(int id, E value) {
        if (id >= this.length || id < 0) {
            throw new NullPointerException("getElementById ERROR: Incorrect id");
        }
        Node<E> resultNode = firstNode;
        for (int i = 0; i < id; i++) {
            resultNode = resultNode.getNextNode();
        }
        resultNode.setNodeValue(value);
    }

    public Node<E> delElementById(int id) {
        if (id >= this.length || id < 0) {
            throw new NullPointerException("getElementById ERROR: Incorrect id");
        }
        Node<E> resultNode = firstNode.getNextNode();
        for (int i = 0; i < id; i++) {
            resultNode = resultNode.getNextNode();
        }
        Node<E> temp = resultNode;
        resultNode.setNodeValue(null);
        return temp;
    }

    public boolean delElementByValue(E value) {
        Node<E> resultNode = firstNode;
        while (resultNode != null) {
            if (resultNode.getNodeValue() != null) {
                if (resultNode.getNodeValue().equals(value)) {
                    resultNode.getPreviousNode().setNextNode(resultNode.getNextNode());
                    resultNode.getNextNode().setPreviousNode(resultNode.getPreviousNode());
                    return true;
                }
            }
            resultNode = resultNode.getNextNode();
        }
        return false;
    }

    public void delFirstElement() {
        Node<E> temp = firstNode.getNextNode();
        temp.setPreviousNode(null);
        firstNode = temp;
        System.out.println("Минус первый элемент");
    }

    public void reverse() {
        Node<E> firstReverseNode = firstNode;
        Node<E> previousReverseNode = null;

        while (firstReverseNode != null) {
            Node<E> temp = firstReverseNode.getNextNode();
            firstReverseNode.setNextNode(previousReverseNode);
            previousReverseNode = firstReverseNode;
            firstNode = firstReverseNode;
            firstReverseNode = temp;
        }
    }

    public int getLength() {
        return length;
    }

    public StudentList<E> copyList(StudentList<E> list) {
        StudentList<E> resultList = new StudentList();
        for (int i = 0; i < list.length; i++) {
            resultList.addToEndOfList(list.getElementById(i));
        }
        return resultList;
    }
}