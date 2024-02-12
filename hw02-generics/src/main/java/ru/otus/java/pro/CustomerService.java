package ru.otus.java.pro;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    private final TreeMap<Customer, String> treeMap = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> firstMapEntry = treeMap.firstEntry();
        if (firstMapEntry == null) {
            return null;
        }
        Customer key = firstMapEntry.getKey();
        String value = firstMapEntry.getValue();
        return Map.entry(new Customer(key.getId(), key.getName(), key.getScores()), value);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> mapEntry = treeMap.higherEntry(customer);
        if (mapEntry == null) {
            return null;
        }
        Customer key = mapEntry.getKey();
        String value = mapEntry.getValue();
        return Map.entry(new Customer(key.getId(), key.getName(), key.getScores()), value);
    }

    public void add(Customer customer, String data) {
        treeMap.put(customer, data);
    }
}
