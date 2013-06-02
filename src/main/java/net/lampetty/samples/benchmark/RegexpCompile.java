package net.lampetty.samples.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;

public class RegexpCompile {

    private static final Pattern PATTERN = Pattern.compile("[0-9]\\.([0-9]*\\.*)*");
    
    public static void main(String[] args) {
        new RegexpCompile().run(args);
    }
    
    private void run(String[] args) {
        String arg = args.length == 0 ? "100000" : args[0];
        int num = NumberUtils.toInt(arg);
        long asStatic = asStatic(num);
        long asLocal = asLocal(num);
        System.out.printf("asStatic     = %04d ms%n", asStatic);
        System.out.printf("asLocal      = %04d ms%n", asLocal);
    }
    
    private long asStatic(int num) {
        long startedAt = System.currentTimeMillis();
        List<Boolean> result = new ArrayList<Boolean>(num);
        for (int i = 0; i < num; i++) {
            Matcher m = PATTERN.matcher("4.0.4");
            result.add(m.find());
        }
        return System.currentTimeMillis() - startedAt;
    }
    
    private long asLocal(int num) {
        long startedAt = System.currentTimeMillis();
        List<Boolean> result = new ArrayList<Boolean>(num);
        for (int i = 0; i < num; i++) {
            Pattern p = Pattern.compile("[0-9]\\.([0-9]*\\.*)*");
            Matcher m = p.matcher("4.0.2");
            result.add(m.find());
        }
        return System.currentTimeMillis() - startedAt;
    }
    
}
