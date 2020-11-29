package ru.geekbrains.lesson_4.homework;

public class PrintA implements Runnable {
    private final Main lock;

    public PrintA(Main lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    char a = 'A';
                    while (Main.currentChar != a) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                    }
                    System.out.print(a);
                    Main.currentChar = 'B';
                    lock.notifyAll();
                }
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}