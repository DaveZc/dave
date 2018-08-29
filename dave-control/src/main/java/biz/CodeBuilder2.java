package biz;

import org.apache.commons.lang.StringUtils;

import java.util.Set;

/**
 * @author chen.z
 * @date 2018/7/31
 */
public class CodeBuilder2 {
    /**
     * 1.去除容易混淆的 I,O;
     *
     * 2.总共34位
     *
     * 3.可兑换的数量
     *  a.兑换码不出现重复数字
     *  码长度   兑换码数量
     *  4   ：   34*33*32*31         = 111,3024
     *  5   ：   34*33*32*31*30      = 3339,0720
     *  6   :   34*33*32*31*30*29    = 9,6833,0880
     *  b.兑换码可以出现重复数字
     *  码长度   兑换码数量
     *  5   :   32*32*32*32*32          = 3355.4432
     *  6   :   32*32*32*32*32*32       = 10.7374.1824
     *  7   :   32*32*32*32*32*32*32    = 343.5973.8368
     *
     */

    private final static String[] codes = new String[] {"2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","J","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","Z" };


    public static void main(String[] args) {
        Long num1 = 180801L;
        Long num2 = 991299L;
        System.out.println(Long.toBinaryString(num1));
        System.out.println(Long.toBinaryString(num2));
    }

    private static Set<String> create(int createBathNum, int totalCount, int codeLength, String pwd) {

    return null;

    }

    private static byte pathNumTo32Byte(int pathNum) {

        return 0;
    }

    private static int to32Num(int pathNum) {

        String hexStr = Long.toHexString(pathNum);
        byte[] hexBytes = hexStr.getBytes();

        int val = 1;
        for (int index = hexBytes.length - 2; index >= 0; index--) {
            val = val * hexBytes[index] * 10;
        }

        return val;
    }

}
