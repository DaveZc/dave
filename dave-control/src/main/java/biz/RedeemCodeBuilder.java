package biz;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author chen.z
 * @date 2018/8/13
 */
public class RedeemCodeBuilder {

    private final static String[] CODE_MAP = {"2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","J","K","L","M","N","P","Q","R","S","T","U","V","W","X","Y","Z"};

    private final static String CODE_STR = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    /**
     * 生成【兑换码】 长度10（前创建批次4位+后随机数6位）
     * 取值范围：32位, "2~9"8位 + 大写英文字母（除去O和I）24位
     * -----------------------------------------------------
     * 随机数          最大排列组合数
     * 5        :     32*32*32*32*32          = 3355.4432
     * 6        :     32*32*32*32*32*32       = 10.7374.1824
     * 7        :     32*32*32*32*32*32*32    = 343.5973.8368
     *
     * @param createBathNo 创建批次号
     * @param totalCount   兑换码数量
     * @param codeLength   兑换码长度
     * @return
     */

    private static volatile int duplicateTimes = 0;
    public static Set<String> buildCode(int createBathNo, int totalCount, int codeLength) {

        Set<String> resultSet = new HashSet<>();

        //根据【创建批次号】生成兑换码前4位
        String preCode =
                CODE_MAP[(byte) ((createBathNo >> 15) & 0x1F)]
                + CODE_MAP[(byte) ((createBathNo >> 10) & 0x1F)]
                + CODE_MAP[(byte) ((createBathNo >> 5) & 0x1F)]
                + CODE_MAP[(byte) (createBathNo & 0x1F)];

        // 计算兑换码 随机位 长度
        int randomCodeCount = codeLength - 4;

        // 根据5个bit位表示一个数字,计算所需要的,总随机bit位数
        int randomBitCount = randomCodeCount * 5;

        // 根据总随机bit位数,计算所需要的byte值个数
        int randomByteCount = randomBitCount / 8;
        if (randomBitCount % 8 != 0) {
            randomByteCount++;
        }

        while (resultSet.size() < totalCount) {

            StringBuilder strBuilder = new StringBuilder(preCode);

            //生成 所需要的byte值个数 的随机（byte）数组
            byte[] randomByteArray = new byte[randomByteCount];
            for (int j = 0; j < randomByteCount; j++) {
                randomByteArray[j] = randomByteArray[j] = (byte) (Math.random() * Byte.MAX_VALUE);
            }

            //遍历随机（byte）数组,每5bit置换成一个 随机码
            int startIndex = 0;
            int startArrayIndex = 0;
            for (int i = 0; i < randomCodeCount; i++) {

                int endIndex = startIndex + 5;
                int endArrayIndex = endIndex / 8;

                byte val;
                if (startArrayIndex == endArrayIndex) {
                    val = (byte) ((randomByteArray[startArrayIndex] >> (8 - endIndex % 8) & 0x1F));
                }
                else {
                    int nextElementBits = endIndex % 8;
                    int preElementBits = 5 - nextElementBits;
                    byte preVal = (byte) (randomByteArray[startArrayIndex] & (byte) (Math.pow(2, preElementBits) - 1));
                    val = (byte) ((preVal << nextElementBits) + ((randomByteArray[endArrayIndex] >> (8 - nextElementBits)
                            & 0x1F)));
                }
                strBuilder.append(CODE_MAP[val]);
                startIndex = endIndex;
                startArrayIndex = endArrayIndex;
            }

            String resultCode = strBuilder.toString();

            if (resultSet.contains(resultCode)){
                duplicateTimes++;
                continue;
            }

            resultSet.add(resultCode);

        }

        return resultSet;
    }

    /**
     * 根据【兑换码】解析出【创建批次号】
     * @param redeemCode NOT NULL
     * @return
     */
    public static int analysisBathNo(String redeemCode, int length) {
        int val = 0;
        for (int i = 0; i < length; ) {
            byte tempVal = (byte) CODE_STR.indexOf(redeemCode.charAt(i++));
            val += tempVal << (length - i) * 5;
        }
        return val;
    }

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        Set<String> result = buildCode(180813, 100000, 10);
        Long costTime = System.currentTimeMillis() - startTime;
        int i = 1;
        for (String str : result) {
            System.out.println(i + "\t:\t" + str + ",analysisBathNo(180813)\t:\t" + analysisBathNo(str, 4));
            i++;
        }
        System.out.println("cost:" + costTime + ",duplicateTimes:" + duplicateTimes);

    }

}
