import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.reverse;


public class Main {

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            threads.add(new Thread(new RouteGenerator()));
        }
        Thread maximumLoggerThread = new Thread(new LeaderWatcher());

        maximumLoggerThread.start();
        threads.forEach(Thread::start);

        threads.forEach(x -> {
            try {
                x.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        maximumLoggerThread.interrupt();

        Iterator<Map.Entry<Integer, Integer>> iterator = mostFrequentCase("Самое частое количество повторений");
        System.out.println("Другие размеры:");
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            System.out.println("- " + entry.getKey() + " (" + entry.getValue() + " раз)");

        }

    }

    static Iterator<Map.Entry<Integer, Integer>> mostFrequentCase(String message) {
        List<Map.Entry<Integer, Integer>> pairList = RouteGenerator.sizeToFreq.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
        reverse(pairList);

        Iterator<Map.Entry<Integer, Integer>> iterator = pairList.iterator();
        Map.Entry<Integer, Integer> firstEntry = iterator.next();

        System.out.println(message + " " + firstEntry.getKey()
                + " (встретилось " + firstEntry.getValue() + " раз)");
        return iterator;
    }
}
