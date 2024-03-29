package main;

import javax.swing.*;
import java.awt.*;

public class ShowResult extends JFrame {
    JPanel showResult = new JPanel();
    public ShowResult() {
        super("获奖者名单");
        //根据当前集合内的数据调整窗体的大小
        this.setSize(250, 40 + Data.allWinner.size() * 40);
        //使窗口居中显示
        this.setLocation(Setting.toolkit.setCentered(this));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        showResult.setBackground(new Color(154, 154, 154));
    }

    /**
     * 更新窗口数据
     */
    public void update() {
        this.setVisible(true);
        //重新调整窗体的大小
        this.setSize(250, 40 + Data.allWinner.size() * 40);
        //重新添加数据
        showResult.removeAll();
        showResult.setLayout(new GridLayout(Data.allWinner.size(), 0,1,1));
        for (String s : Data.allWinner) {
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
