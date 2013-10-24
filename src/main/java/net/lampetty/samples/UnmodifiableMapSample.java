package net.lampetty.samples;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UnmodifiableMapSample {

    public static void main(String[] args) {
        Map<String, String> orderedMap = new HashMap<String, String>();
        for (int i = 0; i < 100; i++) {
            orderedMap.put("oinume-" + i, String.valueOf(i));
        }
        System.out.println(Collections.unmodifiableMap(orderedMap));
    }
    
}
