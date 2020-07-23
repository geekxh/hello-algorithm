# add to heapq things like removing any item and changing key value
# implementation of priority queue to support contains, change_task_priority
# and remove_task in log time

from heapq import *

class PriorityQueue(object):
    
    def __init__(self, is_min_heap):
        self.pq = []
        self.entry_finder = {}
        if(is_min_heap is True):
            self.mul = 1
        else :
            self.mul = -1
         
    def contains_task(self, task):
        if task in self.entry_finder:
            return True
        else:
            return False

    def get_task_priority(self, task):
        if task in self.entry_finder:
            return (self.entry_finder[task])[0]
        raise ValueError("task does not exist")
        
    def add_task(self, priority, task):
        if task in self.entry_finder:
            raise KeyError("Key already exists")
        entry = [self.mul*priority, False, task]
        self.entry_finder[task] = entry
        heappush(self.pq, entry)

    def change_task_priority(self, priority, task):
        if task not in self.entry_finder:
            raise KeyError("Task not found")
        self.remove_task(task)
        entry = [self.mul*priority, False, task]
        self.entry_finder[task] = entry
        heappush(self.pq, entry)
 
    def remove_task(self, task):
        entry = self.entry_finder.pop(task)
        entry[1] = True

    def pop_task(self):
        while self.pq:
            priority, removed, task = heappop(self.pq)
            if removed is False:
                del self.entry_finder[task]
                return task
        raise KeyError("pop from an empty priority queue")

    def peek_task(self):
        while self.pq:
            priority, removed, task = tuple(heappop(self.pq))
            if removed is False:
                 heappush(self.pq, [priority, False, task])
                 return task
        raise KeyError("pop from an empty priority queue")

    def is_empty(self):
        try:
            self.peek_task()
            return False
        except KeyError:
            return True

    def __str__(self):
        return str(self.entry_finder) + " " + str(self.pq)
        

if __name__ == '__main__':
    task1 = "Tushar"
    task2 = "Roy"
    task3 = "is"
    task4 = "coder"

    min_pq = PriorityQueue(True)
    min_pq.add_task(1, task1)
    min_pq.add_task(3, task2)
    min_pq.add_task(6, task3)
    min_pq.add_task(7, task4)
    print(min_pq.contains_task(task3))
    print(min_pq.get_task_priority(task3))
    print(min_pq)
    while min_pq.is_empty() is False:
        print(min_pq.pop_task())

    max_pq = PriorityQueue(False)
    max_pq.add_task(1, task1)
    max_pq.add_task(3, task2)
    max_pq.add_task(6, task3)
    max_pq.add_task(7, task4)
    while max_pq.is_empty() is False:
        print(max_pq.pop_task())

        
