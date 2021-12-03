package main;

import toolkit.MyToolkit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author RousingCN
 */
public class RandomNumber implements ActionListener,Runnable {
    JPanel titlePanel = new JPanel();
    JPanel numShowPanel = new JPanel(new BorderLayout());
    JPanel buttonPanel = new JPanel(new BorderLayout());
    JLabel title = new JLabel("抽 奖 机", JLabel.CENTER);
    JLabel numberText = new JLabel("000000", JLabel.CENTER);
    JButton btnOk = new JButton("开 始");
    JButton btnShow = new JButton("查看结果");
    //自定义类，用于使窗口居中和使用自定义字体
    MyToolkit toolkit = new MyToolkit();
    //用于在另一个线程内决定是否该结束线程
    boolean endRandom = false;
    //用于切换按钮执行的事件
    boolean startRandom = true;
    //用于保存当前随机生成的号码
    String winnerNumber = "";
    //用于保存所有获奖者号码的集合
    ArrayList<String> allWinner = new ArrayList<>();
    /**
     * 图形界面
     */
    public RandomNumber() {
        JFrame f = new JFrame("号码抽奖机");
        f.setVisible(true);
        f.setSize(500, 300);
        //使窗口居中显示
        f.setLocation(toolkit.setCentered(f));
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //使用自定义字体，下同
        title.setFont(toolkit.setTextFont(20));
        //调整字体颜色
        title.setForeground(Color.white);
        numberText.setFont(toolkit.setNumberFont(30));
        //按钮“透明化”设置
        btnOk.setContentAreaFilled(false);
        btnOk.setFocusPainted(false);
        btnOk.setFont(toolkit.setTextFont(16));
        btnOk.addActionListener(this);
        btnShow.setContentAreaFilled(false);
        btnShow.setFocusPainted(false);
        btnShow.setFont(toolkit.setTextFont(14));
        btnShow.addActionListener(this);

        titlePanel.setBackground(new Color(89, 89, 89));
        titlePanel.add(title);
        numShowPanel.setBackground(new Color(180, 180, 180));
        numShowPanel.add(numberText);
        buttonPanel.setBackground(new Color(145, 145, 145));
        buttonPanel.add(btnOk,BorderLayout.CENTER);
        buttonPanel.add(btnShow,BorderLayout.EAST);

        f.add(titlePanel, BorderLayout.NORTH);
        f.add(numShowPanel, BorderLayout.CENTER);
        f.add(buttonPanel, BorderLayout.SOUTH);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOk) {
            //点击的是“开始”按钮
            if (!endRandom) {
                //设置另一条线程
                startRandom = true;
                Thread t = new Thread(this);
                t.start();
                //将按钮修改为“结束”按钮
                btnOk.setText("结 束");
                endRandom = true;
            }
            //点击的是“结束”按钮
            else {
                //使线程内的循环结束，导致线程结束
                startRandom = false;
                //将按钮修改为“开始”按钮
                btnOk.setText("开 始");
                endRandom = false;
            }
        } else if (e.getSource() == btnShow) {
            if (allWinner.size() != 0) {
                //打开”获奖者名单“界面
                new ShowResult(allWinner);
            } else {
                JOptionPane.showMessageDialog(null, "请先抽取至少一个获奖者号码！", "没有获奖者", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    @Override
    public void run() {
        while (startRandom) {
            //获取随机数文本
            winnerNumber = getNumberText(6);
            //重新设置界面上显示的数字
            numberText.setText(winnerNumber);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //保存获奖者编号
        allWinner.add(winnerNumber);
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

    /**
     * 获取随机数文本
     * @param numLength 号码长度
     */
    public String getNumberText(int numLength) {
        return Arrays.toString(getNumber(numLength));
    }

}
