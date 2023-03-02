package part2;

/**
 * @ClassName:SparseArray
 * @Description:TODO
 * @Author:叶林
 * @Date 2020/1/18 18:32
 * @Version 1.0
 */
public class SparseArray {
    public static void main(String[] args) {
        /**
         * 1.创建一个原始的二维数组 11 * 11，并设置测试数据
         *   0:表示没有棋子;1表示黑子;2表示蓝子
         */
        int[][] chessArr = new int[11][11];
        chessArr[0][0] = 1;
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[4][5] = 2;
        chessArr[0][10] = 1;
        chessArr[10][10] = 2;
        chessArr[10][0] = 1;
        /**
         * 2.输出原始的二维数组
         */
        System.out.println("原始的二维数组~~");
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        /**
         * 3.将二维数组转化为稀疏队列
         */
        int[][] sparseArr = arrayToSparseArray(chessArr, 0);
        /**
         * 4.输出稀疏数组的形式
         */
        System.out.println();
        System.out.println("得到稀疏数组为~~~~");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("恢复后的二维数组");
        /**
         * 5.将《稀疏数组》恢复成《二维数组》
         */
        int[][] chessArr2 = sparseArrayToArray(sparseArr);
        /**
         * 6.打印恢复后的二位数组
         */
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    /**
     * 将《稀疏数组》 ---->恢复成《原始的二维数组》
     *
     * @param sparseArray :稀疏数组
     * @return :原始的二维数组
     */
    public static int[][] sparseArrayToArray(int[][] sparseArray) {
        //1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int row = sparseArray[0][0];
        int col = sparseArray[0][1];
        int[][] chessArr = new int[row][col];
        //2. 在读取稀疏数组后几行的数据(从第二行开始)，并赋给 原始的二维数组
        for (int i = 1; i < sparseArray.length; i++) {
            chessArr[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return chessArr;
    }

    /**
     * 《将二维数组》转《稀疏数组》
     *
     * @param chessArr :二维数组
     * @param val      :二维数组的原始值
     * @return :二维数组转化的稀疏数组
     */
    public static int[][] arrayToSparseArray(int[][] chessArr, int val) {
        int sum = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != val) {
                    sum++;
                }
            }
        }
        int[][] sparseArray = new int[sum + 1][3];
        sparseArray[0][0] = chessArr.length;
        sparseArray[0][1] = chessArr[0].length;
        sparseArray[0][2] = sum;
        int index = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != val) {
                    index++;
                    sparseArray[index][0] = i;
                    sparseArray[index][1] = j;
                    sparseArray[index][2] = chessArr[i][j];
                }
            }
        }
        return sparseArray;
    }
}
