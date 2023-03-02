package part6;

import java.text.SimpleDateFormat;
import java.util.Date;

//ѡ������
public class SelectSort {
    public static void main(String[] args) {
        //����Ҫ��80000�������������
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // ����һ��[0, 8000000) ��
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("����ǰ��ʱ����=" + date1Str);
        selectSort(arr);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("����ǰ��ʱ����=" + date2Str);
    }

    //ѡ������
    public static void selectSort(int[] arr) {
        //���Ƶ��Ĺ��̣����Ƿ����˹��ɣ���ˣ�����ʹ��for�����
        //ѡ������ʱ�临�Ӷ��� O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) { // ˵���ٶ�����Сֵ����������С
                    min = arr[j]; // ����min
                    minIndex = j; // ����minIndex
                }
            }
            // ����Сֵ������arr[0], ������
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
