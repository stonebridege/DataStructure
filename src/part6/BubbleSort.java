package part6;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {

    public static void main(String[] args) {
        //����һ��ð��������ٶ�O(n^2), ��80000�����ݣ�����
        //����Ҫ��80000�������������
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); //����һ��[0, 8000000) ��
        }
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("����ǰ��ʱ����=" + date1Str);
        //����ð������
        bubbleSort(arr);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("������ʱ����=" + date2Str);
    }

    /**
     * ��ð�������㷨����װ��һ��������ð�������ʱ�临�Ӷ� O(n^2), �Լ�д����
     */
    public static void bubbleSort(int[] arr) {
        // ��ʱ����
        int temp = 0;
        // ��ʶ��������ʾ�Ƿ���й�����
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // ���ǰ������Ⱥ���������򽻻�
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //System.out.println("��" + (i + 1) + "������������");
            //System.out.println(Arrays.toString(arr));
            if (!flag) { // ��һ�������У�һ�ν�����û�з�����
                break;
            } else {
                flag = false; // ����flag!!!, �����´��ж�
            }
        }
    }
}
