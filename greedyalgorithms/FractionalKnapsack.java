package greedyalgorithms;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class FractionalKnapsack {
    private static double[] findSolution(int tot, Integer[] item, Integer[] weight) {
        List<Integer> items = Arrays.asList(item);
        List<Integer> weights = Arrays.asList(weight);
        double arr[] = new double[item.length];
        int ind = 0;
        Arrays.fill(arr, Integer.MAX_VALUE);
        PriorityQueue<List<Integer>> qu = new PriorityQueue<>((a, b) -> {
            return -1 * ((a.get(1) * b.get(0)) - (b.get(1) * a.get(0)));
        });
        for (int i = 0; i < arr.length; i++) {
            qu.add(Arrays.asList(item[i], weight[i]));
        }
        int sum = 0;
        while (qu.peek() != null) {
            List<Integer> ls = qu.poll();
            if (sum + ls.get(1) <= tot) {
                arr[items.indexOf(ls.get(0))] = 1;
                sum += ls.get(1);
            } else {
                arr[items.indexOf(ls.get(0))] = (double) (tot - sum) / ls.get(1);
                break;
            }
        }
        return arr;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("./input.txt")));
        int tot = Integer.parseInt(br.readLine());
        Integer items[] = new Integer[1];
        Integer weights[] = new Integer[1];
        String str[];
        for (int i = 0; i < 2; i++) {
            str = br.readLine().split(" ");
            if (i == 0) {
                items = new Integer[str.length];
                for (int j = 0; j < str.length; j++) {
                    items[j] = Integer.parseInt(str[j]);
                }
            } else {
                weights = new Integer[str.length];
                for (int j = 0; j < str.length; j++) {
                    weights[j] = Integer.parseInt(str[j]);
                }
            }
        }
        double[] seq = findSolution(tot, items, weights);
        int i = 0;
        while (i < seq.length && seq[i] != Integer.MAX_VALUE) {
            System.out.print(seq[i] + " ");
            i++;
        }
    }
}