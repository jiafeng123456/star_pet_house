package org.star_pet_house_commons.io;

import com.google.common.io.Resources;

import java.io.*;

/*
 *@description:
 *@author jiafeng
 *@date 2021/1/18 0018 11:03
 */
public class ReadToStringUtil {

    public static void main(String[] args) {
        String soucePath = Resources.getResource("testmsg.txt").getPath();
        System.out.println(readToString(soucePath));
    }

    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }
}
