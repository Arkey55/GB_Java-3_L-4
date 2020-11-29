package ru.geekbrains.lesson_4.homework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static char currentChar = 'A';
    public static void main(String[] args) {
        Main lock = new Main();

        PrintA a = new PrintA(lock);
        PrintB b = new PrintB(lock);
        PrintC c = new PrintC(lock);

        ExecutorService exec = Executors.newFixedThreadPool(3);
        exec.execute(a);
        exec.execute(b);
        exec.execute(c);
        exec.shutdown();
    }
}

