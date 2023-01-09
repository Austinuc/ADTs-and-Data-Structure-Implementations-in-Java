package org.example;

import HashTable.ArrayMap;


import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, Integer> m = new ArrayMap<>();
        m.put("Austin", 1);
        m.put("austin", 2);
        m.put("Uche", 3);
        m.put("uche", 4);
        m.put("Nkechi", 5);
        m.put("Baby", 6);
        m.put("Joy", 7);
        m.put("Favor", 8);
        m.put("Precious", 9);
        m.put("Ada", 10);
        m.put("Chinwe", 11);
        m.put("Ngozi", 12);
        m.put("Ogo", 13);
        m.put("Ify", 14);
        m.put("Dera", 15);
        m.put("Chi", 16);
        m.put("Kosi", 17);
        m.put("Eze", 18);
        m.put("Eze", 19);
        System.out.println(m);
        System.out.println(m.size());
//        System.out.println(m.containsKey("Ify"));
        System.out.println(m.remove("Ify"));
        System.out.println(m.remove("Favor"));
        System.out.println(m.remove("Dera"));
        System.out.println(m.remove("Eze"));
        System.out.println(m.remove("uche"));
//        System.out.println(m.containsKey("Ify"));
        System.out.println(m);
        System.out.println(m.size());


//        for (String s : m.keySet()) {
//            System.out.println(s);
//        }
//        for (Integer i : m.values()) {
//            System.out.println(i);
//        }
    }
}