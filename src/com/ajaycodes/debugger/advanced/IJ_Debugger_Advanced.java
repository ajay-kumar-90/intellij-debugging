package com.ajaycodes.debugger.advanced;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IJ_Debugger_Advanced {

    public static class Cache {

        private static Cache instance;


        /**
         * <h2>Breakpoint Settings</h2>
         * <li>Log expression value, message, stack</li>
         * <li>Shift-click</li>
         * <li>Change values and Using Expression</li>
         */
        static Cache getInstance(int i) {
            if (instance == null) {
                instance = new Cache();
            }
            return instance;
        }

        public static void main(String[] args) {
            for (int i = 0; i < 10; i++) {
                System.out.println(getInstance(i));
            }
        }
    }
    /**
     * <h2>Break point Settings</h2>
     * <li>Dependent breakpoints</li>
     */
    public static class Warmup {

        public static void main(String[] args) {
            warmup();
            realWork();
        }
        static void warmup() {
            for (int i = 0; i < 10; i++) {
                work();
            }
        }

        static void realWork() {
            for (int i = 0; i < 10; i++) {
                work();
            }
        }
        private static void work() {
            // do something
            int a = 5;
        }

    }

    /**
     * <h2>Breakpoint Settings</h2>
     * <li>Suspend Thread vs All</li>
     */
    public static class ConcurrencyTest {

        @Test
        public void main() throws InterruptedException {
            Assertions.assertSame(1, work().size());
        }
        static List<Integer> work() throws InterruptedException {
            final List<Integer> list = Collections.synchronizedList(new ArrayList<>());
            Thread thread = new Thread(() -> addIfAbsent(list, 17), "T1"); // Thread T1
            thread.start();
            addIfAbsent(list, 17); // Main Thread
            thread.join();
            System.out.println("Elements : " + list);
            return list;
        }
        static void addIfAbsent(List<Integer> list, int x) {
            if (!list.contains(x)) {
                validateRange(x);
                list.add(x);
            }
        }

        private static void validateRange(int x) {
            // process and validate if value between 0 and 100
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
