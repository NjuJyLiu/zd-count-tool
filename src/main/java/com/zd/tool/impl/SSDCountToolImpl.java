package com.zd.tool.impl;

import com.zd.tool.SSDCountTool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

/**
 * Description: 这个实现不适合用注入的方式使用
 *
 * @author Liujy On 2019-06-21.
 * @version 1.0
 * @since JDK 1.8
 */
public class SSDCountToolImpl implements SSDCountTool {

    private String filePathName;

    private int tagNums;

    private int tagCapacity;

    public int initFile(final int tagNums, final int tagCapacity, String filePathName) throws Exception {
        this.filePathName = filePathName;
        File file = new File(filePathName);
        FileOutputStream writer = new FileOutputStream(file);
        for (int i = 0; i < tagNums; i++) {
            byte[] loginData = new byte[tagCapacity * tagNums];
            for (int j = 0; j < tagCapacity; j++) {
                loginData[j] = (byte) 0x00;
            }
            writer.write(loginData);
        }
        writer.flush();
        writer.close();
        return tagNums * tagCapacity;
    }

    public int increment(int tagNum, int dayNum) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile(filePathName, "w");
        System.out.println(randomAccessFile.length());

        int pos = tagNum + (dayNum / 8) - 1;

        randomAccessFile.seek(pos);

        byte[] data = new byte[1];
        int offset = dayNum % 8;
        data[offset] = (byte) (0x80 >> offset);
        randomAccessFile.write(data);
        randomAccessFile.close();
        return 1;
    }

    public int queryCount(int tagNum) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile(filePathName, "r");
        System.out.println(randomAccessFile.length());
        randomAccessFile.seek(tagNum - 1);
        byte[] data = new byte[tagCapacity];
        randomAccessFile.read(data);
        int count = 0;
        for (int i = 0; i < tagCapacity; i++) {
            byte b = data[i];
            do {
                if (b != (byte) 0x00) {
                    count++;
                }
            } while ((b = (byte) (b << 1)) != (byte) 0x00);
        }
        System.out.println("登录了: " + count);
        randomAccessFile.close();
        return count;
    }

    public int queryDay(int tagNum, int day) {
        RandomAccessFile randomAccessFile = null;
        int offset = day % 8;
        byte tmp = (byte) 0x80;
        byte[] data = new byte[1];
        try {
            randomAccessFile = new RandomAccessFile(filePathName, "r");
            randomAccessFile.seek(tagNum - 1 + (day / 8));
            tmp = (byte) (tmp >> offset);
            randomAccessFile.read(data);
            randomAccessFile.close();
        } catch (Exception e) {
            return 0;
        }
        int end = tmp & data[0];
        if (end != 0) {
            return 1;
        }
        return 0;
    }
}
