package pers.pro.core;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @program: automat
 * @description:
 * @author: zan.Kang
 * @create: 2019-03-19 10:57
 **/
public class OpenWCMain {
    //获取合法数字输入
    private static Integer nextInt() {
        System.out.println("请输入数字(1~10):");
        Scanner in = new Scanner(System.in);
        try {
            Integer i = in.nextInt();
            return i;
        } catch (InputMismatchException exception) {
            return nextInt();
        }
    }


    public static void main(String[] args) throws Exception {
        System.out.println("开始前请关闭微信;");
        System.out.print("请问需要打开几个微信？");
        FileUtils.setCount(nextInt());
        try {
            if (!FileUtils.openLocalWCPath()) {
                System.out.println("首次使用请告知wechat路径？(不知道可输入任意字符，由系统默认扫描C盘下文件)：");
                Scanner in = new Scanner(System.in);
                String scanPath = in.nextLine();
                FileUtils.opWeChat(scanPath);
                FileUtils.write(scanPath);
            }
        } catch (Exception e) {
            FileUtils.findWechat();
        }
        System.out.println("结束会话！");
        new Scanner(System.in).next();
    }


}
