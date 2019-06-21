package com.zd.tool;

/**
 * Description: 文件存取计数器,当前这个功能就是，查询千万用户60天内有那些天登录了，暂时没想到什么扩展作用
 *
 * @author Liujy On 2019-06-21.
 * @version 1.0
 * @since JDK 1.8
 */
public interface SSDCountTool {

    /**
     * 初始化计数器文件
     * @param tagNums 文件中的标签数量
     * @param tagCapacity 每个标签的容量（单位是byte）
     * @return 返回总计的字节数
     */
    int initFile(int tagNums, int tagCapacity, String filePathName)throws Exception;

    /**
     * @param tagNum 标签位置
     * @param dayNum 第几天
     * @return 返回0-失败，返回1-成功
     */
    int increment(int tagNum, int dayNum) throws Exception;

    /**
     * @param tagNum 标签位置
     * @return 登录次数
     */
    int queryCount(int tagNum) throws Exception;

    /**
     * @param day 第几天
     * @return 0-未登录，1-登录
     */
    int queryDay(int tagNum, int day);
}
