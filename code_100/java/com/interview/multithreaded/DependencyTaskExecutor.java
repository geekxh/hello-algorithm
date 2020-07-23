package com.interview.multithreaded;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;

/**
 * Given a Task with list of its dependencies and execute method. Run the task such that dependencies are executed first.
 * You are given x number of threads. Increase parallelism as much as possible.
 */
public class DependencyTaskExecutor {

    Map<String, CompletableFuture<Void>> taskTracker = new HashMap<>();
    void scheduleTask(List<Task> tasks, int threads) {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        CompletableFuture<Void> future = CompletableFuture.completedFuture(null);
        for (Task task : tasks) {
            future = future.thenAcceptBothAsync(scheduleTaskUtil(task, executor), (a, b) -> {}, executor);
        }
        future.thenRunAsync(() -> {System.out.println("All tasks done. Closing executor"); executor.shutdown();});
    }

    CompletableFuture<Void> scheduleTaskUtil(Task task, Executor executor) {
        CompletableFuture<Void> f = taskTracker.get(task.name());
        if (f != null) {
            return f;
        }
        if (task.dependencies().isEmpty()) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> task.execute(), executor);
            taskTracker.put(task.name(), future);
            return future;
        }
        CompletableFuture<Void> future = CompletableFuture.completedFuture(null);;
        for (Task upstreamTask : task.dependencies()) {
            future = future.thenAcceptBothAsync(scheduleTaskUtil(upstreamTask, executor), (a, b) -> {}, executor);
        }
        future = future.thenRunAsync(() -> task.execute(), executor);
        taskTracker.put(task.name(), future);
        return future;
    }

    public static void main(String args[]) {
        DependencyTaskExecutor taskExecutor = new DependencyTaskExecutor();
        SimpleSleepTask a = new SimpleSleepTask("a", 2000);
        SimpleSleepTask b = new SimpleSleepTask("b", 4000);
        SimpleSleepTask c = new SimpleSleepTask("c", 6000);
        SimpleSleepTask d = new SimpleSleepTask("d", 3000);
        SimpleSleepTask x = new SimpleSleepTask("x", 4000);
        SimpleSleepTask y = new SimpleSleepTask("y", 6000);
        SimpleSleepTask z = new SimpleSleepTask("z", 3000);

        d.addDependency(b);
        d.addDependency(c);
        c.addDependency(a);
        b.addDependency(a);
        x.addDependency(y);
        x.addDependency(z);
        y.addDependency(a);
        taskExecutor.scheduleTask(Lists.newArrayList(a, b, c, d, x, y, z), 4);
    }
}

interface Task {
    String name();
    List<Task> dependencies();
    void execute();
}

class SimpleSleepTask implements Task {
    String name;
    int sleepTimeInMillis;
    List<Task> dependencies = new ArrayList<>();
    SimpleSleepTask(String name, int sleepTimeInMillis) {
        this.name = name;
        this.sleepTimeInMillis = sleepTimeInMillis;
    }

    void addDependency(Task task) {
        dependencies.add(task);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public List<Task> dependencies() {
        return dependencies;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Starting sleep for task " + name);
            Thread.sleep(sleepTimeInMillis);
            System.out.println("Ending sleep for task " + name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class FutureTask implements Runnable {

    Task task;
    List<FutureTask> chainedTasks = new ArrayList<>();
    Executor executor;
    FutureTask(Task task, Executor executor) {
        this.task = task;
        this.executor = executor;
    }
    @Override
    public void run() {
        task.execute();
        for (FutureTask t : chainedTasks) {
            supplyAsync(t, executor);
        }
    }

    void supplyAsync(FutureTask task, Executor executor) {
        executor.execute(task);
    }

    void addChain(FutureTask task) {
        task.addChain(this);
    }


}