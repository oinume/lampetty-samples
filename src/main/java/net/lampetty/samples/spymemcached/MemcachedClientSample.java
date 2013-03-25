package net.lampetty.samples.spymemcached;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

/**
 * MemcachedClientのreplace/deleteの返り値の確認
 */
public class MemcachedClientSample {

    private MemcachedClient client;
    
    public static void main(String[] args) throws Exception {
        new MemcachedClientSample().run(args);
    }
    
    public void run(String[] args) throws Exception {
        String host = args.length > 0 ? args[0] : "127.0.0.1:11211";
        client = new MemcachedClient(AddrUtil.getAddresses(host));
        
        replace();
        delete();
    }

    public void replace() throws Exception {
        client.set("replace1", 1000, "replace1");
        boolean replaced1 = client.replace("replace1", 1000, "replaced").get();
        // replaced = trueになるはず
        System.out.printf("replaced1 = %s, value = %s%n", replaced1, client.get("replace1"));
        
        boolean replaced2 = client.replace("replace2", 1000, "replaced").get();
        // replaced = falseになるはず
        System.out.printf("replaced2 = %s, value = %s%n", replaced2, client.get("replace2"));
    }
    
    public void delete() throws Exception {
        client.set("delete1", 1000, "delete1");
        boolean deleted1 = client.delete("delete1").get();
        System.out.printf("deleted = %s%n", deleted1);
        
        boolean deleted2 = client.delete("delete2").get();
        System.out.printf("deleted = %s%n", deleted2);
    }
}
