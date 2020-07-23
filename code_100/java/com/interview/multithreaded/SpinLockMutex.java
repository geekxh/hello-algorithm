package com.interview.multithreaded;

/**
 * Design a mutex using 2 variable method assuming operation ice happens atomically
 */
class Mutex {

    int val = 0;

    /**
     * Val is stored somewhere. If oldVal is same as val
     * then you change val to newVal and return oldVal
     * Otherwise you do nothing but return val
     * @param oldValue
     * @param newValue
     * @return
     */
    private synchronized int ice(int oldValue, int newValue) {
        if (oldValue == val) {
            val = newValue;
            return oldValue;
        } else {
            return val;
        }
    }

    void acquireLock() {
        while (ice(0, 1) != 0);
    }

    void releaseLock() {
        ice(1, 0);
    }

}

public class SpinLockMutex {

    StringBuffer buff = new StringBuffer();

    // some method needs mutex protection
    public void changeBuffer(String str) {
        buff.append(str);
    }

    public String getBuffer() {
        return buff.toString();
    }

    public static void main(String args[]) throws Exception{
        SpinLockMutex slm = new SpinLockMutex();
        Mutex m = new Mutex();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                m.acquireLock();
                slm.changeBuffer("a" + i);
                m.releaseLock();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                m.acquireLock();
                slm.changeBuffer("b" + i);
                m.releaseLock();
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw e;
        }
        System.out.println(slm.getBuffer());
    }

}
