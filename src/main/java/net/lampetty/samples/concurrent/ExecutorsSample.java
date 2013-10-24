package net.lampetty.samples.concurrent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class ExecutorsSample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5, new ThreadFactory() {
            private int count = 0;
            public Thread newThread(Runnable r) {
                Thread t =  new Thread(r);
                t.setName("hoge-" + ++count);
                t.setDaemon(true);
                return t;
            }
        });
        
        final int taskCount = 10;
        final long range = (long)Math.pow(10, 4);
        //executor.submit(new Task());
        CompletionService<List<Long>> completion = new ExecutorCompletionService<List<Long>>(executor);
        for (int i = 0; i < taskCount; i++) {
            final long from = i * range + 1;
            final long to = (i + 1) * range;
            completion.submit(new PrimeGenerator(i, from, to));
        }
        
        List<Long> totalPrimes = new ArrayList<Long>();
        for (int i = 0; i < taskCount; i++) {
            try {
                Future<List<Long>> future = completion.take();
                List<Long> primes = future.get();
                System.out.println(i + ": primes = " + primes.size() );
                totalPrimes.addAll(primes);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                continue;
            } catch (ExecutionException e) {
                e.printStackTrace();
                continue;
            }
        }
        
        System.out.println("totalPrimes =" + totalPrimes);
        executor.shutdown();
    }
    
    private static class PrimeGenerator implements Callable<List<Long>> {

        private int id;
        private long from; 
        private long to;

        public PrimeGenerator(final int id, long from, long to) {
            this.id = id;
            this.from = from;
            this.to = to;
        }

        public List<Long> call() throws Exception {
            System.out.println("call() id = " + this.id);
            List<Long> primes = new ArrayList<Long>();
            for (long i = from; i <= to; i++) {
                if (isPrime(i)) {
                    primes.add(i);
                }
            }
            if (id == 5) {
                throw new RuntimeException("stop");
            }
            return primes;
        }

        private boolean isPrime(long number) {
            for (long i = 2; i < number - 1; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
