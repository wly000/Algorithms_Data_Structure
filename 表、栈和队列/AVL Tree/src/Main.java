import java.io.*;
import java.util.Stack;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int a = Integer.parseInt(s[0]);
        int b = Integer.parseInt(s[2]);
        String[] n = s[1].split("");
        TreeMap<String,Integer> map = new TreeMap<>();
        String[] keys = new String[] {"0","1","2","3","4","5","6","7","8","9","A","a","B","b","C","c","D","d","E","e","F","f"};
        int[] values = new int[] {0,1,2,3,4,5,6,7,8,9,10,10,11,11,12,12,13,13,14,14,15,15};
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i],values[i]);
        }
        int[] n_split = new int[n.length];
        for (int i = 0; i < n.length; i++) {
            n_split[i] = map.get(n[i]);
        }
        System.out.println(aTob(a, n_split, b));
    }

    public static String aTob (int a, int[] n, int b) {
        long aToD = 0;
        for (int i = 0; i < n.length; i++) {
            aToD += n[i]*Math.pow(a,n.length - 1 - i);
        }
        String DTob = "";
        change(aToD,b);
        while(!stack.isEmpty()) {
            String i = stack.pop();
            switch (Integer.parseInt(i)) {
                case 10:
                    i = "A";break;
                case 11:
                    i = "B";break;
                case 12:
                    i = "C";break;
                case 13:
                    i = "D";break;
                case 14:
                    i = "E";break;
                case 15:
                    i = "F";break;
                default :
                    ;
            }
            DTob += i;
        }
        return DTob;
    }

    private static Stack<String> stack = new Stack<>();

    public static long getResult(long n, int b) {
        return n/b;
    }

    public static long getRemain(long n, int b) {
        return n%b;
    }

    public static void change (long n, int b) {
        long result = getResult(n,b);
        if (result == 0) {
            stack.push(Long.toString(getRemain(n,b)));
        } else {
            stack.push(Long.toString(getRemain(n,b)));
            change(result,b);
        }
    }
}