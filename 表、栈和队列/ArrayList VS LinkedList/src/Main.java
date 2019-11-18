import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> lst_array = new ArrayList<Integer>();
        LinkedList<Integer> lst_linked = new LinkedList<Integer>();
        for (int j = 0; j < 4; j++) {
            lst_array.add(j);
            lst_linked.add(j);
        }
        //计算ArrayList的get的运行时间
        long startTime_1 = System.currentTimeMillis();
        int total_1 = sum(lst_array, 10000);
        long endTime_1 = System.currentTimeMillis();
        System.out.println("ArrayList's get Runtime: "+ (endTime_1 - startTime_1) + "ms");

        //计算LinkedList的get的运行时间
        long startTime_2 = System.currentTimeMillis();
        int total_2 = sum(lst_linked, 10000);
        long endTime_2 = System.currentTimeMillis();
        System.out.println("LinkedList's get Runtime: "+ (endTime_2 - startTime_2) + "ms");

        //计算ArrayList的add的运行时间
        long startTime_3 = System.currentTimeMillis();
        insert(lst_array);
        long endTime_3 = System.currentTimeMillis();
        System.out.println("ArrayList's add Runtime: "+ (endTime_3 - startTime_3) + "ms");

        //计算LinkedList的add的运行时间
        long startTime_4 = System.currentTimeMillis();
        insert(lst_linked);
        long endTime_4 = System.currentTimeMillis();
        System.out.println("LinkedList's add Runtime: "+ (endTime_4 - startTime_4) + "ms");
    }

    public static int sum(List<Integer> lst, int N) {
        int total = 0;
        for (int i = 0; i < N; i++) {
            total += lst.get(i);
        }
        return total;
    }

    public static void insert(List<Integer> lst) {
        int n = 1000;
        for (int i = 0; i < n; i++) {
            lst.add(i,i);
        }
    }
}
