/**
 * @author leahy(583310958 @ qq.com)
 * @date 2019/11/13 15:57
 */
import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public String PrintMinNumber(int [] numbers) {
        if(numbers == null || numbers.length <= 0) {
            return null;
        }
        String[] str = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            str[i] = String.valueOf(numbers[i]);
        }

        for (int i = 0; i < str.length; i++) {
            for (int j = 0; j < str.length - i -1; j++) {
                String a = str[j] + str[j+1];
                String b = str[j+1] + str[j];
                if (Comp(a,b)) {
                    Swap(j,j+1,str);
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for (String a: str) {
            sb.append(a);
        }
        return sb.toString();
    }

    public void Swap(int i, int j, String[] str) {
        String temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public boolean Comp(String a, String b) {
        int bool = a.compareTo(b);
        if (bool > 0) {
            return true;
        }
        else
            return false;
    }
}