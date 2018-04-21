package ru.academit.list;

public class Main {

    public static void main(String[] args) throws NullPointerException {
        StudentList<String> list = new StudentList();
        for (int i = 0; i < 10; i++) {
            list.addToEndOfList("String № " + i);
        }

        list.addToStartOfList("FirstString");

        for (int i = 0; i < list.getLength(); i++) {
            System.out.println(list.getElementById(i));
        }


        System.out.println();
        System.out.println("Длинна: " + list.getLength());

        System.out.println();
        System.out.println("Первый элемент: " + list.getFirstElement());
        System.out.println("Замена первого элемента на CHANGED:");
        list.setFirstElement("CHANGED");
        System.out.println("Первый элемент:" + list.getFirstElement());

        System.out.println();
        System.out.println("Элемент № 5:" + list.getElementById(4));
        System.out.println("Замена этого элемента на \"CHANGED\":");
        list.setElementById(4, "CHANGED");
        System.out.println(list.getElementById(4));

        System.out.println();
        System.out.println("Узел № 5:" + list.getElementById(4));
        System.out.println("Удаление содержимого этого узла...");
        System.out.println("Удалённый элемент:" + list.delElementById(4).getNodeValue());
        System.out.println("Проверка:");
        for (int i = 0; i < list.getLength(); i++) {
            System.out.println(list.getElementById(i));
        }

        System.out.println();
        System.out.println("Попытка удалить узел по содержанию: qweewq");
        System.out.println(list.delElementByValue("qweewq"));
        System.out.println("Попытка удалить узел по содержанию: String № - 0");
        System.out.println(list.delElementByValue("String № - 0"));
        System.out.println("Текущий список:");

        for (int i = 0; i < list.getLength(); i++) {
            System.out.println(list.getElementById(i));
        }

        System.out.println();
        list.delFirstElement();

        for (int i = 0; i < list.getLength(); i++) {
            System.out.println(list.getElementById(i));
        }

        System.out.println();
        System.out.println("Реверс:");
        list.reverse();
        for (int i = 0; i < list.getLength(); i++) {
            System.out.println(list.getElementById(i));
        }

        StudentList<String> copyList = list.copyList(list);
        System.out.println("Скопированный список: ");
        for (int i = 0; i < copyList.getLength(); i++) {
            System.out.println(copyList.getElementById(i));
        }
    }
}
