package ru.nsu.kolodina.HashTable;

public class Main {
    public static void main(String[]args) {
        System.out.println("Aa".hashCode() % 128 + " " + "BB".hashCode() % 128);
    }
}
