package com.brian.dp.test;

import org.codehaus.plexus.util.StringUtils;

/**
 * test unit demo
 *
 * @author BrianX
 * @date 2022/09/20 15:10
 **/
public class Text {
    private String content;

    public Text(String content){
        this.content = content;
    }

    /**
    * @param :
    *
    * @return Integer
    * @author BrianX
    * @description
     * 如果字符串只包含数字：“123”，toNumber() 函数输出对应的整数：123。
     * 如果字符串是空或者 null，toNumber() 函数返回：null。
     * 如果字符串包含首尾空格：“ 123”，“123 ”，“ 123 ”，toNumber() 返回对应的整数：123。
     * 如果字符串包含多个首尾空格：“ 123 ”，toNumber() 返回对应的整数：123；
     * 如果字符串包含非数字字符：“123a4”，“123 4”，toNumber() 返回 null；
    * @date 2022/9/20 15:16
    */
    public Integer toNumber() {
        if(content == null || content.isEmpty()){
            return null;
        }
        if(StringUtils.isNumeric(content.trim())){
            return Integer.parseInt(content.trim());
        }
        return null;
    }
}
