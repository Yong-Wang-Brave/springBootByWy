package com.wy.demo.Tools;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtilsWy {
    public static void main(String[] args) {
/*        // 判断字符串是否为 null，或 ""。注意，包含空白符的字符串为非空
        boolean isEmpty(Object str)
// 判断字符串是否是以指定内容结束。忽略大小写
        boolean endsWithIgnoreCase(String str, String suffix)
// 判断字符串是否已指定内容开头。忽略大小写
        boolean startsWithIgnoreCase(String str, String prefix)
// 是否包含空白符
        boolean containsWhitespace(String str)
// 判断字符串非空且长度不为 0，即，Not Empty
        boolean hasLength(CharSequence str)
// 判断字符串是否包含实际内容，即非仅包含空白符，也就是 Not Blank
        boolean hasText(CharSequence str)
// 判断字符串指定索引处是否包含一个子串。
        boolean substringMatch(CharSequence str, int index, CharSequence substring)
// 计算一个字符串中指定子串的出现次数
        int countOccurrencesOf(String str, String sub)*/
          String aa="wangyong";
        boolean empty = StringUtils.isEmpty(aa);
  // 判断字符串是否为 null，或 ""。注意，包含空白符的字符串为非空
        System.out.println(empty);
        // 判断字符串是否是以指定内容结束。忽略大小写
        boolean g = StringUtils.endsWithIgnoreCase(aa,".");
        System.out.println(g);
        // 判断字符串是否已指定内容开头。忽略大小写
        boolean b = StringUtils.startsWithIgnoreCase(aa,"w");
        System.out.println(b);
        String aaa=" ";
        // 是否包含空白符
        boolean b1 = StringUtils.containsWhitespace(aaa);
        // 是否包含空白符
        System.out.println(b1);
// 计算一个字符串中指定子串的出现次数
int g1 = StringUtils.countOccurrencesOf(aa, "g");
        System.out.println(g1);

        // 查找并替换指定子串
        String replace1 = StringUtils.replace(aa, "wang", "WANG");
        System.out.println(replace1);


// 去除尾部的特定字符
        String ng = StringUtils.trimTrailingCharacter(aa, 'g');
        System.out.println(ng);
// 去除头部的特定字符
        String w = StringUtils.trimLeadingCharacter(aa, 'w');
        System.out.println(w);
// 去除头部的空白符
        System.out.println(StringUtils.trimLeadingWhitespace(aa));
// 去除尾部的空白符
        System.out.println(StringUtils.trimTrailingWhitespace(aa));

// 去除头部和尾部的空白符
        String s = StringUtils.trimWhitespace(" v ");
        System.out.println(s);

// 删除开头、结尾和中间的空白符
        String s1 = StringUtils.trimWhitespace(" a b ");
        System.out.println(s1);

// 删除指定子串
        String ng1 = StringUtils.delete(aa, "ng");
        System.out.println(ng1);

// 删除指定字符（可以是多个）
        String s2 = StringUtils.deleteAny(aa, "w");
        System.out.println(s2);

// 对数组的每一项执行 trim() 方法tr
        String[] array = new String[]{"a "," b"};

        System.out.println(Arrays.stream(StringUtils.trimArrayElements(array)).collect(Collectors.toList()));
// 将 URL 字符串进行解码


    }
}
