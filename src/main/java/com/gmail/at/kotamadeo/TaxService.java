package com.gmail.at.kotamadeo;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.concurrent.atomic.LongAdder;

@AllArgsConstructor
public class TaxService extends Thread{
    private final Shop shop;
    private final LongAdder earnings;

    @Override
    public void run() {
        System.out.printf("Выручка за день в магазине %s: %d рублей.%n", shop.getTitle(), shop.getEarnings());
        Arrays.stream(shop.getSales()).forEach(earnings::add);
    }
}
