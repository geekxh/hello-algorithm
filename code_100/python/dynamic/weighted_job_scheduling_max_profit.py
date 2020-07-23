"""
Problem Statement
=================

Given set of jobs with start and end interval and profit, how to maximize profit such that jobs in subset do not
overlap.

Video
-----
* https://youtu.be/cr6Ip0J9izc

Complexity
----------

* Runtime Complexity: O(n^2)
* Space Complexity: O(n)

Reference Link
--------------
* http://www.cs.princeton.edu/courses/archive/spr05/cos423/lectures/06dynamic-programming.pdf
"""


def can_sequence(job1, job2):
    _, job1_finish_time = job1
    job2_start_time, _ = job2
    return job1_finish_time <= job2_start_time


def find_max_profit(jobs):
    sequenced_jobs = sorted(jobs.keys(), key=lambda x: x[1])
    T = [jobs[job_key] for job_key in sequenced_jobs]
    num_jobs = len(sequenced_jobs)

    for j in range(1, num_jobs):
        for i in range(0, j):
            if can_sequence(sequenced_jobs[i], sequenced_jobs[j]):
                T[j] = max(T[j], T[i] + jobs[sequenced_jobs[j]])

    return max(T)


if __name__ == '__main__':
    jobs = {
        (1, 3): 5,      # (start_time, end_time, total_cost)
        (2, 5): 6,
        (4, 6): 5,
        (6, 7): 4,
        (5, 8): 11,
        (7, 9): 2
    }

    assert 17 == find_max_profit(jobs)
