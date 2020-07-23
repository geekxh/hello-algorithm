package com.interview.multithreaded;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Given a queue which gets millions of messages. Message is of form <Domain,Update>.
 * You have 10000 domain tables. Also you have 50 worker threads. You can only get 
 * data from front of the queue. Threads get data from the front and then update the 
 * domain table. If work is being done on domain table you cannot apply another update.
 * Update should also be applied sequentially. So an update coming later on should not
 * be applied before an update coming sooner.
 */

class Data{
    private String domain;
    private String update;
    public String getUpdate() {
        return update;
    }
    public String getDomain() {
        return domain;
    }
}

interface DomainLock{
    boolean acquireLock(String domain);
    boolean releaseLock(String domain);
    boolean isLocked(String domain);
}

class ThreadPoolManager{
    private ConcurrentMap<String, Queue<Data>> domainQueueMap = new ConcurrentHashMap<>();
    public ThreadPoolManager(int numOfThreads){
        //initialize numOfThreads of type ThreadWorker;
    }
}

interface DatabaseLayer{
    public void applyUpdates(String domain,String update);
}

class ThreadWorker implements Runnable{

    private QueueManager mgr;
    private ConcurrentMap<String,Queue<Data>> domainQueueMap;
    private DomainLock domainLock;
    private DatabaseLayer databaseLayer;
    public ThreadWorker(QueueManager mgr){
        this.mgr = mgr;
    }
    @Override
    public void run() {
        while(true){
            Pair p = mgr.getDataFromFrontOfQueue(domainLock);
            if(p.yourLock){
                Queue<Data> queue = domainQueueMap.get(p.data.getDomain());
                if(queue != null){
                    while(queue.size() > 0){
                        Data data = queue.poll();
                        databaseLayer.applyUpdates(data.getDomain(), data.getUpdate());
                    }
                }
                databaseLayer.applyUpdates(p.data.getDomain(), p.data.getUpdate());
                queue = domainQueueMap.get(p.data.getDomain());
                if(queue != null){
                    while(queue.size() > 0){
                        Data data = queue.poll();
                        databaseLayer.applyUpdates(data.getDomain(), data.getUpdate());
                    }
                }
                domainLock.releaseLock(p.data.getDomain());
                //check if queue is not empty
                //if queue is not empty try to acquire lock again
            }else{
                if(domainQueueMap.containsKey(p.data.getDomain())){
                    Queue<Data> queue = domainQueueMap.get(p.data.getDomain());
                    queue.offer(p.data);
                }
            }
        }
    }
    
}

interface QueueHandle{
    //this is a blocking call. If there is no data in the queue it just waits for data to be available
    public Data getNextData();
}

class Pair{
    Data data;
    boolean yourLock;
}

class QueueManager{
    private QueueHandle queueHandle;
    public QueueManager(QueueHandle queueHandle){
        this.queueHandle = queueHandle;
    }
    
    public synchronized Pair getDataFromFrontOfQueue(DomainLock domainLock){
        Data data = queueHandle.getNextData();
        boolean yourLock = false;
        //if lock for table does not exists or if it is false lock the table
        if(!domainLock.isLocked(data.getDomain())){
            domainLock.acquireLock(data.getDomain());
            yourLock = true;
        }
        Pair p = new Pair();
        p.data = data;
        p.yourLock = yourLock;
        return p;
    }
}
public class SingleQueueDomainTableUpdate {
}
