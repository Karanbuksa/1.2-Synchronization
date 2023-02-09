import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RouteGenerator implements Runnable {
    public static final Map<Integer, Integer> sizeToFreq = new LinkedHashMap<>();

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

    @Override
    public void run() {
        Pattern pattern = Pattern.compile("R+");
        String str = generateRoute("RLRFR", 100);
        Matcher matcher = pattern.matcher(str);
        int max = 0;
        while (matcher.find()) {
            int f = matcher.end() - matcher.start();
            if (f > max) {
                max = f;
            }
        }
        synchronized (sizeToFreq) {
            sizeToFreq.put(max, (sizeToFreq.get(max) == null ? 1 : sizeToFreq.get(max) + 1));
            sizeToFreq.notify();
        }
    }
}
