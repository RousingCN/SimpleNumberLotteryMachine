package main;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class AllNumber {

    public AllNumber() {
        if (Setting.model == 2 && !Setting.loadSuccess) {
            loadNumber();
        }
    }

    /**
     * 生成随机数
     * @param numLength 号码长度
     */
    public int[] getNumber(int numLength) {
        int[] number = new int[numLength];
        for (int i = 0; i < number.length; i++) {
            //每一格都会从0-10（不包括10）之间生成随机数字
            number[i] = new Random().nextInt(10);
        }
        return number;
    }

    public void loadNumber() {
        //文件对象
        File file = new File("allNumber.txt");
        //如果文件不存在
        if (!file.exists()) {
            try {
                //创建文件
                file.createNewFile();
                JOptionPane.showMessageDialog(null, "文件已创建，请输入数据后重新运行", "创建成功", JOptionPane.WARNING_MESSAGE);
                FileWriter fw = new FileWriter(file);
                //通过写入缓冲器向文件写入数据
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("000000,123456,~~~");
                bw.close();
                fw.close();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //文件大小为空
        if (file.length() == 0) {
            JOptionPane.showMessageDialog(null, "获取号码失败，请重新检查allNumber.txt文件", "获取失败", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            String[] data = new String[0];
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                //读取一行数据并以逗号拆分数据，放入数组
                data = br.readLine().split(",");
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (data.length < 3) {
                JOptionPane.showMessageDialog(null, "请输入至少三个号码", "数据太少了", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //将数组中的数据添加进集合里
            Data.allText.addAll(Arrays.asList(data));
            Setting.loadSuccess = true;
        }
    }

    /**
     * 获取随机数文本
     * @param numLength 号码长度
     */
    public String getNumberText(int numLength) {
        //模式一 随机生成号码
        if (Setting.model == 1) {
            //生成随机数
            int[] number = getNumber(numLength);
            String randomNumberText = "";
            for (int i = 0; i < number.length; i++) {
                //将6个数字转换为一个字符串
                randomNumberText += number[i];
            }
            return randomNumberText;
        }
        //模式二 从文件里获取号码
        else if (Setting.model == 2) {
            //如果成功加载了文件里的数据
            if (Setting.loadSuccess) {
                //随机从集合里获取一个数据并返回
                int serialNumber = new Random().nextInt(Data.allText.size());
                return Data.allText.get(serialNumber);
            } else {
                //将按钮修改为“开始”按钮
                RandomNumber.btnOk.setText("开 始");
                Setting.endRandom = false;
            }
        }
        return "000000";
    }

}
