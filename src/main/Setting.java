package main;

import toolkit.MyToolkit;

public class Setting {
    //自定义类，用于使窗口居中和使用自定义字体
    public static MyToolkit toolkit = new MyToolkit();
    //用于在另一个线程内决定是否该结束线程
    public static boolean endRandom = false;
    //用于切换按钮执行的事件
    public static boolean startRandom = true;
    //用于切换随机数获取的模式,1. 所有号码完全随机生成,2. 仅从外部文件中获取所有的号码
    public static int model = 1;
    //用于声明是否成功的从文件里获取了数据
    public static boolean loadSuccess = false;

}
