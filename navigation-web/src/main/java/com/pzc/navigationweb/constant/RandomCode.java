package com.pzc.navigationweb.constant;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by carl on 15/12/9.
 * 随机码
 */
public final class RandomCode {

    //默认码因子
    public static final String[] NUMBER_FACTOR = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static final String[] EN_LOWER_FACTOR = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public static final String[] EN_UPPER_FACTOR = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static final String[] FACTOR_EN = ArrayUtils.addAll(EN_LOWER_FACTOR, EN_UPPER_FACTOR);

    public static final String[] FACTOR_ALL = ArrayUtils.addAll(NUMBER_FACTOR, FACTOR_EN);

    private static final String DATE_FORMAT = "yyyyMMddHHmmssSS";

    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

    /**
     * 流水随机码
     *
     * @param factor 码因子数组
     * @param bit    随机码位数
     * @return
     */
    public static String waterRandomCode(String[] factor, Integer bit) {
        String randomCode = randomCode(factor, bit);
        return waterCode(randomCode);
    }

    /**
     * 流水随机码
     *
     * @param bit 随机码位数
     * @return
     */
    public static String waterRandomCode(Integer bit) {
        return waterRandomCode(EN_LOWER_FACTOR, bit);
    }


    /**
     * 流水码
     *
     * @param prefix 前缀
     * @return
     */
    public static String waterCode(String prefix) {
        return waterCode(prefix, randomCodeByBit(2), DATE_FORMAT);
    }

    /**
     * 流水码
     *
     * @param prefix     前缀
     * @param suffix     后缀
     * @param dateFormat 日期格式
     * @return
     */
    public static String waterCode(String prefix, String suffix, String dateFormat) {
        String dateStr;
        Calendar calendar = Calendar.getInstance();
        if (DATE_FORMAT.equals(dateFormat)) {
            dateStr = sdf.format(calendar.getTime());
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            dateStr = sdf.format(calendar.getTime());
        }
        return prefix + dateStr + suffix;
    }

    public static String randomCodeByBit(Integer bit) {
        return randomCode(FACTOR_ALL, bit);
    }


    /**
     * 生成随机码
     *
     * @param factor 码因子数组
     * @param bit    随机码位数
     * @return
     */
    public static String randomCode(String[] factor, Integer bit) {
        if (null == bit) {
            bit = 4;
        }
        if (null == factor) {
            factor = NUMBER_FACTOR;
        }
        List<String> randomFactor = Arrays.asList(factor);
        //随机排序因子
        Collections.shuffle(randomFactor);
        //获取随机首位
        int size = randomFactor.size();
        int startIndex = 0, endIndex = 0;
        do {
            startIndex = Math.abs(new Random().nextInt(size));
        } while ((endIndex = startIndex + bit) > size);
        //获取随机码
        List<String> randomCode = randomFactor.subList(startIndex, endIndex);
        return StringUtils.join(randomCode, "");
    }


    public static void main(String[] args) {
        for (int i = 0, j = 10; i < j; i++) {
            System.out.println(randomCode(NUMBER_FACTOR, 4));
            System.out.println(waterCode("PU"));
            System.out.println(waterRandomCode(new String[]{"A", "B"}, 2));
            System.out.println(randomCodeByBit(5));
            System.out.println("-----------");
        }
    }


}
