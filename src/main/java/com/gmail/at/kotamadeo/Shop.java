package com.gmail.at.kotamadeo;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Random;

@RequiredArgsConstructor
@Getter
public class Shop extends Thread {
    private static final int MIN_SALES_VALUE = 500;
    private static final int MAX_SALES_VALUE = 10_000;
    private static final int MIN_PRICE = 50;
    private static final int MAX_PRICE = 100_000;
    private static final Random RANDOM = new Random();
    private final int[] sales = new int[RANDOM.nextInt(MAX_SALES_VALUE - MIN_SALES_VALUE) + MIN_SALES_VALUE];
    @NonNull
    private String title;
    private long earnings;

    @Override
    public void run() {
        for (int i = 0; i < sales.length; i++) {
            sales[i] = RANDOM.nextInt(MAX_PRICE - MIN_PRICE) + MIN_PRICE;
        }
        earnings = Arrays.stream(sales).parallel().sum();
    }
}
