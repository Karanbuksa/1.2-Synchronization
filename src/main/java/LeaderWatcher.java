public class LeaderWatcher implements Runnable {
    @Override
    public void run() {
        synchronized (RouteGenerator.sizeToFreq) {
            while (!Thread.interrupted()) {
                try {
                    RouteGenerator.sizeToFreq.wait();
                } catch (InterruptedException e) {
                    return;
                }
                Main.mostFrequentCase("Лидер:");
            }
        }
    }
}
