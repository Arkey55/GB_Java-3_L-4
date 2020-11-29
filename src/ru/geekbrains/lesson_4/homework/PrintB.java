package ru.geekbrains.lesson_4.homework;

public class PrintB implements Runnable{
    private final Main lock;

    public PrintB(Main lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    char b = 'B';
                    while (Main.currentChar != b) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            e.printStackTrace();
                        }
                    }
                    System.out.print(b);
                    Main.currentChar = 'C';
                    lock.notifyAll();
                }
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
