import java.util.ArrayList;
import java.util.List;

/**
 * @author leahy(583310958 @ qq.com)
 * @date 2019/11/17 16:18
 */
public class LongestSubString {
    public static void main(String[] args) {
        String input = "arabcacfr";
       String[] strs  = input.split("");
       String maxSubString = subString(strs);
        System.out.println(maxSubString);

    }
    public static String subString(String[] strs) {
        int maxLen = 0;
        String maxSubString = "";
        List<String> result = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            if (!result.contains(strs[i])) {
                result.add(strs[i]);
                maxLen=result.size();
                maxSubString = result.toString();
            }
            else {
                result.add(strs[i]);
                int j = 0;
                for (; j < result.size(); j++) {
                    if (result.get(j) == strs[i]) {
                        break;
                    }
                }
                int len = result.size();
                result = result.subList(j-1,len);
                if (result.size() > maxLen) {
                    maxLen = result.size();
                    maxSubString = result.toString();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i));
        }
        maxSubString = sb.toString();
        return maxSubString;
    }
}
