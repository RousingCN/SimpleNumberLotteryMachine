package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShowResult extends JFrame {
    JPanel showResult;
    public ShowResult(ArrayList<String> result) {
        super("获奖者名单");
        this.setVisible(true);
        //根据当前集合内的数据调整窗体的大小
        this.setSize(250, 40 + result.size() * 40);
        //使窗口居中显示
        this.setLocation(Setting.toolkit.setCentered(this));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //总面板
        showResult = new JPanel(new GridLayout(result.size(), 0,1,1));
        showResult.setBackground(new Color(154, 154, 154));
        //添加内容
        for (String s : result) {
            //内部面板 每一个号码都会单独一个面板内
            JPanel panel = new JPanel(new BorderLayout());
            JLabel text = new JLabel(s, JLabel.CENTER);
            panel.setBackground(new Color(220, 220, 220));
            panel.setSize(250,40);
            //使用自定义字体
            text.setFont(Setting.toolkit.setTextFont(20));
            panel.add(text, BorderLayout.CENTER);
            showResult.add(panel);
        }
        this.add(showResult);
    }
}
