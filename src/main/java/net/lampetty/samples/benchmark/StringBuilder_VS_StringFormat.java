package net.lampetty.samples.benchmark;

/**
 * StringBulder V.S. String.format to concat strings.
 */
public class StringBuilder_VS_StringFormat {

    public static void main(String[] args) {
        new StringBuilder_VS_StringFormat().run(args);
    }
    
    private void run(String[] args) {
        if (args.length == 0) {
            System.exit(1);
        }
        
        int times = Integer.parseInt(args[0]);        
        String host = getHost();
        int userId = getUserId();
        String param = getParam();
        String[] urls1 = new String[times];
        String[] urls2 = new String[times];
        
        long start = System.currentTimeMillis();
        
        for (int i = 0; i < times; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("http://");
            sb.append(host);
            sb.append("/status/");
            sb.append(userId);
            sb.append("?param=");
            sb.append(param);
            urls1[i] = sb.toString();
        }
        long elapsed1 = System.currentTimeMillis() - start;
        
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            urls2[i] = String.format(
                    "http://%s/status/%d?param=%s",
                    host, userId, param
            );
        }
        long elapsed2 = System.currentTimeMillis() - start2;
        
        System.out.println("StringBuilder:" + elapsed1);
        System.out.println("String.format:" + elapsed2);
    }

    private String getHost() {
        return "localhost";
    }
    
    private int getUserId() {
        return 100 % 1;
    }
    
    private String getParam() {
        return "hoge";
    }
    
//  BenchmarkResult result = new Benchmarker().add("hoge", new Code() {
//      public void run() {
//
//      }
//  }).add("fuga", new Code() {
//      public void run() {
//
//      }
//  }).timeThese(500);
//  System.out.println(result);

}
