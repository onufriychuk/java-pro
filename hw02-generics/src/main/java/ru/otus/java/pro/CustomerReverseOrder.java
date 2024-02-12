package ru.otus.java.pro;

import java.util.ArrayDeque;

public class CustomerReverseOrder {
    private final ArrayDeque<Customer> arrayDeque = new ArrayDeque<>();

    public void add(Customer customer) {
        arrayDeque.push(customer);
    }

    public Customer take() {
        return arrayDeque.pop();
    }
}
