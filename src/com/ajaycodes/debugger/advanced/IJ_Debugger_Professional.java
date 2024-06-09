package com.ajaycodes.debugger.advanced;

public class IJ_Debugger_Professional {

    /**
     * <h2>Loose Ends</h2>
     * <li>Caller Filter</li>
     * <li>also with a pattern</li>
     */
    public static class Warmup {

        public static void main(String[] args) {
            warmup();

            realWork();
        }

        private static void warmup() {
            for (int i = 0; i < 10; i++) {
                work();
            }
        }

        private static void realWork() {
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
     * <h2>Loose Ends</h2>
     * <li>Renders for primitives and arrays (built and custom)</li>
     */
    public static class Primitives {
        public static void main(String[] args) {
            int a = 100;
            System.out.println(a);
        }
    }
}
