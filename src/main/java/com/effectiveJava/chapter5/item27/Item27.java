package com.effectiveJava.chapter5.item27;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Item27 {

    public static void main(String[] args) {
        Set<Integer> integers1 = new HashSet(); // Unchecked assignment: 'java.util.HashSet' to 'java.util.Set<java.lang.Integer>'
        // Set<Integer> integers2 = new HashSet<>(); // OK
        List<String> strings = new ArrayList<>();
    }

}
