//https://www.geeksforgeeks.org/activity-selection-problem-greedy-algo-1/

package greedyalgorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class ActivitySchedule {

    private static int[] activitySequence(Integer st[], Integer en[]) {
        List<Integer> start = Arrays.asList(st);
        List<Integer> end = Arrays.asList(en);
        int[] arr = new int[start.size()];
        int ind = 0;
        Arrays.fill(arr, Integer.MAX_VALUE);
        PriorityQueue<List<Integer>> qu = new PriorityQueue<>((a, b) -> {
            return a.get(1) - b.get(1);
        });
        for (int i = 0; i < start.size(); i++) {
            qu.add(Arrays.asList(start.get(i), end.get(i)));
        }
        int prevfinish = 0;
        while (qu.peek() != null) {
            List<Integer> ls = qu.poll();
            if (ls.get(0) >= prevfinish) {
                arr[ind++] = start.indexOf(ls.get(0));
                prevfinish = ls.get(1);
            }

        }
        return arr;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("./input.txt")));
        Integer start[] = new Integer[1];
        Integer end[] = new Integer[1];
        String str[];
        for (int i = 0; i < 2; i++) {
            str = br.readLine().split(" ");
            if (i == 0) {
                start = new Integer[str.length];
                for (int j = 0; j < str.length; j++) {
                    start[j] = Integer.parseInt(str[j]);
                }
            } else {
                end = new Integer[str.length];
                for (int j = 0; j < str.length; j++) {
                    end[j] = Integer.parseInt(str[j]);
                }
            }
        }
        int[] seq = activitySequence(start, end);
        int i = 0;
        while (seq[i] != Integer.MAX_VALUE) {
            System.out.print(seq[i] + " ");
            i++;
        }
    }
}