package net.lampetty.samples.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;

public class Regexp_VS_StartsWith {

    public static void main(String[] args) {
        new Regexp_VS_StartsWith().run(args);
    }
    
    private void run(String[] args) {
        String arg = args.length == 0 ? "100000" : args[0];
        int num = NumberUtils.toInt(arg);
        System.out.printf("regexp     = %04d ms%n", regexp(num));
        System.out.printf("startsWith = %04d ms%n", startsWith(num));
    }
    
    private long regexp(int num) {
        long startedAt = System.currentTimeMillis();
        List<Boolean> result = new ArrayList<Boolean>(num);
        for (int i = 0; i < num; i++) {
            Pattern p = Pattern.compile("^2");
            Matcher m = p.matcher("4.0.2");
            result.add(m.find());
        }
        return System.currentTimeMillis() - startedAt;
    }

    private long startsWith(int num) {
        long startedAt = System.currentTimeMillis();
        List<Boolean> result = new ArrayList<Boolean>(num);
        for (int i = 0; i < num; i++) {
            result.add("4.0.2".startsWith("2"));
        }
        return System.currentTimeMillis() - startedAt;
    }

}
