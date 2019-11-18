import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author leahy(583310958 @ qq.com)
 * @date 2019/11/18 14:29
 */
public class UniqueStr {
    public static void main(String[] args) {
        String input = "google";
        int index = FirstNotRepeatingChar(input);
        System.out.println(index);
    }


    public static int FirstNotRepeatingChar(String str) {
        if (str == "" || str.length() <= 0) {
            return 0;
        }
        Map<String, Integer> result = new LinkedHashMap<>();
        String[] strs = str.split("");
        for (int i = 0; i < strs.length; i++) {
            if (result.containsKey(strs[i])) {
                result.put(strs[i],result.get(strs[i]) + 1);
            }
            else {
                result.put(strs[i], 1);
            }
        }
        String temp = "";
        int index = 0;
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            if (entry.getValue() == 1) {
                temp = entry.getKey();
                break;
            }
        }
        for (int j = 0; j < strs.length; j++) {
            if (temp != strs[j]) {
                index++;
            }
            else {
                break;
            }
        }
        if (index == strs.length) {
            return -1;
        }
        else {
            return index;
        }
    }
}
