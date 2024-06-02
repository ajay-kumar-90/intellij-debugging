package com.ajaycodes.debugger.advanced.my;

import com.ajaycodes.debugger.advanced.IJ_Debugger_Advanced;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Somewhere {

    public static void doSomething(IJ_Debugger_Advanced.FieldBpt field) {
        Random random = new Random();
        if (random.nextBoolean()) {
            field.boo();
        }
        if (random.nextBoolean()) {
            field.doo();
        }
        if (random.nextBoolean()) {
            field.foo();
        }
        if (random.nextBoolean()) {
            field.goo();
        }
        if (random.nextBoolean()) {
            field.moo();
        }
        if (random.nextBoolean()) {
            field.too();
        }
        if (random.nextBoolean()) {
            field.yoo();
        }
        if (random.nextBoolean()) {
            field.zoo();
        }
        if (random.nextBoolean()) {
            field.koo();
        }
    }

    public static int doSomething() {
        try {
            return doSomethingWithException();
        } catch (Exception e) {
            System.out.println("Ignored : " + e);
        }
        return 0;
    }

    public static IJ_Debugger_Advanced.MethodBpt.BaseInterface getObject() {
        Clazz3 obj = new Clazz3();
        doSomething(obj);
        return obj;
    }

    public static void doSomething(Object obj) {
        try {
            obj.getClass().getMethod("foo").invoke(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static int doSomethingWithException() {
        Object a = null;
        Object b = new Object();
        int x = 1, y = 0, z = Integer.MAX_VALUE;
        switch (new Random().nextInt(5)) {
            case 0:
                return Math.floorDiv(x, y);
            case 1:
                return a.hashCode();
            case 2:
                return Math.incrementExact(z);
            case 3:
                return Integer.parseInt("xxx");
            case 4:
                return ((String) b).length();
        }
        return 0;
    }

    public static class Clazz1 implements IJ_Debugger_Advanced.MethodBpt.BaseInterface {

        @Override
        public String foo() {
            System.out.println("Called");
            return "Clazz1";
        }

        @Override
        public String boo() {
            return "Clazz1";
        }
    }

    public static class Clazz2 extends Clazz1 {
        public String boo() {
            System.out.println("Called boo");
            return "Clazz2";
        }
    }

    public static class Clazz3 extends Clazz2 {
        public String foo() {
            System.out.println("Called foo");
            return "Clazz3";
        }
    }
}
