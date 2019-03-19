package pers.pro.core;

import java.io.*;

/**
 * @program: automat
 * @description:
 * @author: zan.Kang
 * @create: 2019-03-19 13:18
 **/
public class FileUtils {
    private static final String LOGPATH = "wechat.tlog";
    private static final String WECHAT_FILE_NAME= "WeChat.exe";
    private static Integer count = 1;

    //加载程序记录的wechat.exe所在地址
    private static String loadPath() {
        File f = new File(LOGPATH);
        if (!f.exists()) {
            return null;
        }
        String tpath = "";
        Reader rdr = null;
        try {
            rdr = new InputStreamReader(new FileInputStream(f));
            int temp;
            while ((temp = rdr.read()) != -1) {
                //对于window下，\r\n这两个字符在一起时，表示一个换行。
                //但是如果这两个字符分开显示时，会换两行。
                //因此，屏蔽掉\r，或者屏蔽掉\n。否则，将会出现很多空行
//                if(((char)temp)!='\r'){
                tpath += (char) temp;
//                }
            }
        } catch (Exception e) {
            try {
                rdr.close();
            } catch (Exception e2) {
            }
        }
        return tpath;

    }

    //打开程序记录的wechat地址
    public static boolean openLocalWCPath() {
        String path = loadPath();
        if (path != null && "".equals(path.trim())) {
            try {
                opWeChat(path);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    //默认扫描C盘下所有目录
    public static void findWechat() throws Exception {
        find("C:\\");
    }

    //路径扫描
    private static void find(String root) throws Exception {
        File fc = new File(root);
        System.out.println("开始扫描路径：");
        for (String str : fc.list()) {
            System.out.println(root + "\\" + str);
            if (find(root, str)) {
                return;
            }
        }
        System.out.println("对不起，在C盘没有扫描到微信！");
    }

    //将@param path写入文件logpath中
    public static void write(String path) throws Exception {
        File f = new File(LOGPATH);
        FileWriter fw;
        BufferedWriter bw;
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            fw = new FileWriter(f.getAbsoluteFile(), false);  //true表示追加新内容,false为覆盖
            bw = new BufferedWriter(fw);
            bw.write(path);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据地址path与需要打开个数count打开微信
    public static void opWeChat(String path) throws Exception {
        for (int i = 0; i < count; i++) {
            Runtime.getRuntime().exec(path);
        }
    }


    //遍历路径下文件夹找寻文件名为WeChat.exe的文件
    private static boolean find(String name, String nm) throws Exception {
        File f = new File(name + "\\" + nm);
        if (!f.isDirectory() || f.list() == null) {
            if (f.getName().equals(WECHAT_FILE_NAME)) {
                opWeChat(name + "\\" + nm);
                write(name + "\\" + nm);
                System.out.println("\n\n找到WeChat在路径下 :" + name + "\\" + nm + ";\n\n保存路径信息到文件中:" + System.getProperty("user.dir") + "\\" + LOGPATH + ";");
                return true;
            }
            return false;
        }
        for (String str : f.list()) {
            if (find(name + "\\" + nm, str)) {
                return true;
            }
        }
        return false;
    }

    //设置wechat打开个数
    public static void setCount(Integer counts) {
        if (counts < 0) {
            counts = 1;
        } else if (counts > 10) {
            counts = 10;
        }
        count = counts;
    }

}
