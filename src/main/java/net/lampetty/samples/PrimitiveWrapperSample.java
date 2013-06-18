package net.lampetty.samples;

public class PrimitiveWrapperSample {

    /*
value + 1 = 2
value + 1 = 2
Exception in thread "main" java.lang.NullPointerException
    at net.lampetty.samples.PrimitiveWrapperSample.wrapper(PrimitiveWrapperSample.java:20)
    at net.lampetty.samples.PrimitiveWrapperSample.main(PrimitiveWrapperSample.java:12)
     */
    public static void main(String[] args) {
        PrimitiveWrapperSample sample = new PrimitiveWrapperSample();
        sample.primitive(1);
        sample.wrapper(1);
        sample.wrapper(null);
    }

    private void primitive(int value) {
        System.out.println("value + 1 = " + (value + 1));
    }
    
    private void wrapper(Integer value) {
        System.out.println("value + 1 = " + (value + 1));
    }
}
