import jdk.nashorn.internal.ir.BinaryNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Chapter3 {
    public static void main(String[] args) {
        LinkedList<Integer> L = new LinkedList<>();
        int N = 20;
        for (int i = 1; i < N; i++) {
            L.add(i);
        }
        LinkedList<Integer> P = new LinkedList<>();
        P.add(1);P.add(3);P.add(4);P.add(6);

        long startTime_1 = System.currentTimeMillis();
        printLots(L,P);
        long endTime_1 = System.currentTimeMillis();
        System.out.println("ArrayList's get Runtime: "+ (endTime_1 - startTime_1) + "ms");
    }

    public static void printLots(LinkedList<Integer> L, LinkedList<Integer> P) {
        for (Integer i:P) {
            System.out.println(L.get(i));
        }
    }
    BinaryNode
}
