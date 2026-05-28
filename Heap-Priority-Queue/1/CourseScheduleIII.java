/**
 * PROBLEM: Course Schedule III
 * * There are n different online courses numbered from 1 to n. You are given an array courses where 
 * courses[i] = [durationi, lastDayi] indicates that the ith course should be taken continuously for 
 * durationi days and must be finished before or on lastDayi.
 * Return the maximum number of courses that you can take.
 * * Example:
 * Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
 * Output: 3
 * * Approach:
 * Sort courses by their last day (earliest deadline first).
 * Keep track of total time spent. Use a Max-Heap to store the durations of accepted courses.
 * If adding a course exceeds its deadline, remove the course with the longest duration (root of Max-Heap)
 * to free up the most time while maintaining the same course count.
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class CourseScheduleIII {
    public static int scheduleCourse(int[][] courses) {
        // Sort by the last day
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        
        // Max-Heap to track the durations of the courses we have taken
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        int time = 0;
        
        for (int[] course : courses) {
            int duration = course[0];
            int lastDay = course[1];
            
            time += duration;
            maxHeap.add(duration);
            
            // If the total time exceeds the deadline, drop the longest course taken so far
            if (time > lastDay) {
                time -= maxHeap.poll();
            }
        }
        
        return maxHeap.size();
    }

    public static void main(String[] args) {
        int[][] courses = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};
        System.out.println("Max courses: " + scheduleCourse(courses)); // 3
    }
}