package com.ajaycodes.debugger.advanced;

import com.ajaycodes.debugger.advanced.my.Somewhere;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

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

        @Test
        public void main() throws InterruptedException {
            Assertions.assertSame(1, work().size());
        }
    }

    /**
     * <h2>Breakpoint settings</h2>
     * <li>Filters</li>
     * <li>Intentions</li>
     */
    public static class Human {
        private final String name;
        private int age;

        public Human(String name) {
            this.name = name;
        }

        public static void main(String[] args) {
            List<Human> humans = Arrays.asList(
                    new Man("Paul"),
                    new Woman("Marry"),
                    new Man("Max"));
            for (int i = 0; i < 10; i++) {
                for (Human human : humans) {
                    human.inc();
                }
            }
        }

        private void inc() {
            age++;
        }

        static class Man extends Human {
            public Man(String name) {
                super(name);
            }
        }

        static class Woman extends Human {
            public Woman(String name) {
                super(name);
            }
        }
    }

    /**
     * <h2>Breakpoint types</h2>
     * <li>Method, entry and or exit</li>
     * <li>Wildcard method (any method in the</li>
     * project, class, in main, all methods)
     */
    public static class MethodBpt {
        public static void main(String[] args) {
            MethodBpt.BaseInterface o = Somewhere.getObject();
            System.out.println(o.boo());
        }

        public interface BaseInterface {
            String foo();

            String boo();
        }
    }

    /**
     * <h2>Breakpoint types</h2>
     * <li>Field</li>
     * <li>Fun: select all occurrences</li>
     */
    public static class FieldBpt {

        private int var = new Random().nextInt(100);

        public static void main(String[] args) {
            FieldBpt field = new FieldBpt();
            Somewhere.doSomething(field);
            if (field.var > 5) {
                System.out.println("Big");
            }
        }

        public void foo() {
            this.var = 1;
        }

        public void boo() {
            this.var = 2;
        }

        public void goo() {
            this.var = 3;
        }

        public void zoo() {
            this.var = 2;
        }

        public void doo() {
            this.var = 10;
        }

        public void moo() {
            this.var = 6;
        }

        public void too() {
            this.var = 6;
        }

        public void koo() {
            this.var = 8;
        }

        public void yoo() {
            this.var = 9;
        }

    }

    /**
     * <h2>Breakpoint types</h2>
     * <li>Exception (any exception, caught/uncaught)</li>
     * <li>Catch class filter</li>
     */
    public static class ExceptionBpt {
        public static void main(String[] args) {
            Somewhere.doSomething();
            for (int i = 0; i < 20; i++) {
                try {
                    Somewhere.doSomethingWithException();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            int a = 1 / 0;
        }
    }

    /**
     * <h2>Advance stepping</h2>
     * <li>Drop frame</li>
     * <li>Force return</li>
     * <li>Throw exception</li>
     */
    public static class Input {
        public static void main(String[] args) throws IOException {
            while (true) {
                int read = System.in.read();
                System.out.println("Input : " + read);
                if (filter(read)) {
                    process(read);
                }
            }
        }
        private static boolean filter(int read) {
            return read != '\n' && read != 'a';
        }

        private static void process(int arg) {
            arg = arg * 2;
            arg += 4;
            if (arg > 1) {
                arg = Math.max(10, arg);
            }
            if (Math.max(arg, 90) % 2 == 0) {
                System.out.println("!");
            }
        }
    }

    /**
     * <h2>Renders</h2>
     * <li>Mute</li>
     * <li>Settings</li>
     * <li>Switch</li>
     */
    public static class Human2 {
        private final String name;
        private int age;

        public Human2(String name) {
            this.name = name;
        }

        public static void main(String[] args) {
            List<Human2> all = Arrays.asList(new Man("Paul"), new Woman("Mary"), new Man("Max"));
            for (int i = 0; i < 10; i++) {
                for (Human2 human2 : all) {
                    human2.inc();
                }
            }
        }

        private void inc() {
            age++;
        }

        static class Man extends Human2 {
            public Man(String name) {
                super(name);
            }
        }

        static class Woman extends Human2 {
            public Woman(String name) {
                super(name);
            }
        }
    }
}
