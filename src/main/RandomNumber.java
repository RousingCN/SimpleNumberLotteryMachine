package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author RousingCN
 */
public class RandomNumber implements ActionListener,Runnable {
    JPanel titlePanel = new JPanel();
    JPanel numShowPanel = new JPanel(new BorderLayout());
    JPanel buttonPanel = new JPanel(new BorderLayout());
    JLabel title = new JLabel("抽 奖 机", JLabel.CENTER);
    JLabel numberText = new JLabel("000000", JLabel.CENTER);
    public static JButton btnOk = new JButton("开 始");
    JButton btnShow = new JButton("查看获奖名单");
    JButton btnSwitch = new JButton("切换至模式二");
    //保存对ShowResult类的引用
    public static ShowResult showResult;
    /**
     * 界面
     */
    public RandomNumber() {
        JFrame f = new JFrame("号码抽奖机");
        f.setVisible(true);
        f.setSize(500, 300);
        //使窗口居中显示
        f.setLocation(Setting.toolkit.setCentered(f));
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //使用自定义字体，下同
        title.setFont(Setting.toolkit.setTextFont(20));
        //调整字体颜色
        title.setForeground(Color.white);
        numberText.setFont(Setting.toolkit.setNumberFont(30));
        //按钮“透明化”设置
        btnOk.setContentAreaFilled(false);
        btnOk.setFocusPainted(false);
        btnOk.setFont(Setting.toolkit.setTextFont(18));
        btnOk.addActionListener(this);
        btnShow.setContentAreaFilled(false);
        btnShow.setFocusPainted(false);
        btnShow.setFont(Setting.toolkit.setTextFont(14));
        btnShow.addActionListener(this);
        btnSwitch.setBackground(new Color(154, 161, 151));
        btnSwitch.setFocusPainted(false);
        btnSwitch.setFont(Setting.toolkit.setTextFont(14));
        btnSwitch.addActionListener(this);

        titlePanel.setBackground(new Color(89, 89, 89));
        titlePanel.add(title);
        numShowPanel.setBackground(new Color(180, 180, 180));
        numShowPanel.add(numberText);
        buttonPanel.setBackground(new Color(145, 145, 145));
        buttonPanel.add(btnOk,BorderLayout.CENTER);
        buttonPanel.add(btnShow,BorderLayout.EAST);
        buttonPanel.add(btnSwitch,BorderLayout.WEST);

        f.add(titlePanel, BorderLayout.NORTH);
        f.add(numShowPanel, BorderLayout.CENTER);
        f.add(buttonPanel, BorderLayout.SOUTH);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOk) {
            //点击的是“开始”按钮
            if (!Setting.endRandom) {
                //设置另一条线程
                Setting.startRandom = true;
                Thread t = new Thread(this);
                t.start();
                //将按钮修改为“结束”按钮
                btnOk.setText("结 束");
                Setting.endRandom = true;
                //设置开始随机抽取号码时不能按其他按钮
                btnSwitch.setEnabled(false);
                btnShow.setEnabled(false);
            }
            //点击的是“结束”按钮
            else {
                //使线程内的循环结束，导致线程结束
                Setting.startRandom = false;
                //将按钮修改为“开始”按钮
                btnOk.setText("开 始");
                Setting.endRandom = false;
                //设置结束随机抽取号码后启用其他按钮
                btnSwitch.setEnabled(true);
                btnShow.setEnabled(true);
            }
        } else if (e.getSource() == btnShow) {
            //点击的是“查看获奖名单”按钮
            if (Data.allWinner.size() != 0) {
                //聚焦”获奖者名单“界面，如果没有界面就生成一个
                if (showResult == null) {
                    showResult = new ShowResult();
                }
                showResult.update();
            } else {
                JOptionPane.showMessageDialog(null, "请先抽取至少一个获奖者号码！", "没有获奖者", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == btnSwitch) {
            //点击的是“切换模式”按钮
            //当前模式为模式一
            if (Setting.model == 1) {
                Setting.model = 2;
                btnSwitch.setText("切换至模式一");
                btnSwitch.setBackground(new Color(140, 132, 132));
            }
            //当前模式为模式二
            else if (Setting.model == 2) {
                Setting.model = 1;
                btnSwitch.setText("切换至模式二");
                btnSwitch.setBackground(new Color(154, 161, 151));
            }
        }
    }
    @Override
    public void run() {
        AllNumber allNumber = new AllNumber();
        //通过startRandom来设置何时结束循环
        while (Setting.startRandom) {
            //获取随机数文本
            Data.winnerNumber = allNumber.getNumberText(6);
            //重新设置界面上显示的数字
            numberText.setText(Data.winnerNumber);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //保存获奖者编号
        Data.allWinner.add(Data.winnerNumber);

    }

}
