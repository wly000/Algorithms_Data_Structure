import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
        String str = br.readLine();
        String[] s = str.split("[ \n\r\t]");
        String[] S = uper(s);
        for (String i : S) {
            System.out.print(i+" ");
        }
    }

    public static String[] uper(String[] s) {
        for (int i = 0; i < s.length; i++) {
            if(Character.isUpperCase(s[i].charAt(0))) {
                ;
            } else { s[i] = new StringBuilder().append(Character.toUpperCase(s[i].charAt(0))).append(s[i].substring(1)).toString();}
        }
        return s;
    }
}