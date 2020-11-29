package ru.geekbrains.lesson_4.homework;

public class PrintC implements Runnable{
    private final Main lock;

    public PrintC(Main lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    char c = 'C';
                    while (Main.currentChar != c) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                    }
                    System.out.print(c + " ");
                    Main.currentChar = 'A';
                    lock.notifyAll();
                }
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
