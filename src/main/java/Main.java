import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.reverse;


public class Main {

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            threads.add(new Thread(new RouteGenerator()));
        }
        threads.forEach(Thread::start);
        List<Map.Entry<Integer, Integer>> pairList = RouteGenerator.sizeToFreq.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
        reverse(pairList);

        Iterator<Map.Entry<Integer, Integer>> iterator = pairList.iterator();
        Map.Entry<Integer, Integer> firstEntry = iterator.next();

        System.out.println("Самое частое количество повторений " + firstEntry.getKey()
                + " (встретилось " + firstEntry.getValue() + " раз)" + "\n" + "Другие размеры:");

        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            System.out.println("- " + entry.getKey() + " (" + entry.getValue() + " раз)");

        }

    }
}
