package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final List<Simulation> simulations;
    private final List<Thread> threadsList;
    private ExecutorService executorService;
    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
        this.threadsList = new ArrayList<>();
    }

    public void runSync() {
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }

    public void runAsync() {
        for (Simulation simulation : simulations) {
            Thread simulationThread = new Thread(simulation);
            threadsList.add(simulationThread);
            simulationThread.start();
        }
    }

    public void runAsyncInThreadPool() {
        executorService = Executors.newFixedThreadPool(4);
        for (Simulation simulation : simulations) {
            executorService.submit(simulation);
        }
    }

    public void awaitSimulationsEnd() throws InterruptedException {
        for (Thread thread : threadsList) {
            thread.join();
        }
        // w praktyce wykonanie programu trwa dluzej niz 10s, ale
        // i tak zakonczy sie on szybciej niz gdyby tutaj bylo
        // executorService.shutdown();
        // nie wiem dlaczego tak sie dzieje, choc domyslam sie
        // ze przez jakies zakolejkowanie operacji na System.out.println???
        // poniewaz jesli zakomentuje sie printa w ConsoleMapDisplay,
        // to wykonanie programu zawsze trwa te wymagane 10s
        if (executorService != null) {
            try {
                if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }
        }
    }
}
