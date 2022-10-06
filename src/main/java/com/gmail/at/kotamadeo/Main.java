package com.gmail.at.kotamadeo;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.concurrent.atomic.LongAdder;

public class Main {
    private static final int NUMBER_OF_SHOPS = 3;
    private static final Shop[] shops = new Shop[NUMBER_OF_SHOPS];

    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        generateShops();
        getReport(longAdder);
    }

    @SneakyThrows
    private static void generateShops() {
        for (int i = 0; i < NUMBER_OF_SHOPS; i++) {
            shops[i] = new Shop("магазин № " + (i + 1));
            shops[i].start();
        }
        for (Shop thread : shops) {
            thread.join();
        }
    }
    private static void getReport(LongAdder earnings) {
        TaxService[] reports = new TaxService[NUMBER_OF_SHOPS];
        for (int i = 0; i < NUMBER_OF_SHOPS; i++) {
            reports[i] = new TaxService(shops[i], earnings);
            reports[i].start();
        }
        Arrays.stream(reports).forEach(x -> {
            try {
                x.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.printf("Общая выручка: %d рублей", earnings.sum());

    }
}