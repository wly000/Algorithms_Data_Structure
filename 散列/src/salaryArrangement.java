import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;

public class salaryArrangement {

    public static void main(String[] args) throws IOException{
        //int num = 19;
        //int slalay[] = { 10000, 20000, 40000, 30000, 30000, 30000, 40000, 20000, 50000, 50000, 50000, 50000, 60000,
               // 60000, 60000, 70000, 80000, 90000, 100000 };// 测试数据
        //slalay = b(num, slalay); // 保存结果
        int i;

        BufferedReader br0 = new BufferedReader(new InputStreamReader(System.in));
        String[] nu;

    {
        try {
            nu = br0.readLine().split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
        String[] sa;

    {
        try {
            sa = br1.readLine().split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int num = Integer.parseInt(nu[0]);
        int slalay = Integer.parseInt(sa[0]);

        slalay = b(num, slalay); // 保存结果

        System.out.println("输出结果为：");
        for (i = 0; i < num; i++)// 输出结果
        {
            System.out.print(" ");
            System.out.print(slalay[i]);
        }
    }

    public static int[] b(int num, int slalay[]) {
        int i, j, k;
        int temp_frequency;
        int temp_slalay;
        int frequency[] = new int[num]; // 频次
        for (i = 0; i < num; i++) // 给频次赋初值
        {
            frequency[i] = 0;
        }

        for (i = 0; i < num; i++) // 对已排序的数据统计频次
        {
            for (j = 0; j < num; j++) {
                if (slalay[i] == slalay[j])
                {
                    frequency[i]++;
                }
            }
        }

        for (i = 1; i < num; i++) // 对频次进行插入排序，同时根据频数交换的顺序排列原数据
        {
            temp_frequency = frequency[i];
            temp_slalay = slalay[i];
            j = i - 1;
            while (j >= 0 && temp_frequency > frequency[j]) //频次递减排序
            {
                frequency[j + 1] = frequency[j];
                slalay[j + 1] = slalay[j]; // 变换原数据
                j--;
            }
            frequency[j + 1] = temp_frequency;
            slalay[j + 1] = temp_slalay;
        }

        for (i = 1; i < num; i++) // 再进行一次插入排序
        {
            temp_slalay = slalay[i];
            j = i - 1;
            k = i - 1;
            while (k >= 0)
            {
                if (temp_slalay == slalay[k]) // 判断前半部分子序列是否存在当前当前数据
                {
                    while (j >= 0 && temp_slalay != slalay[j]) // 插入到相同的数的后面
                    {
                        slalay[j + 1] = slalay[j];
                        j--;
                    }
                    slalay[j + 1] = temp_slalay;
                    break; // 退出循环判断下个数
                }
                k--;
            }

        }

        return slalay;
    }
}