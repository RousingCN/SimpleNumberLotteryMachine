package main;

import javax.swing.*;
import java.awt.*;

public class ShowResult extends JFrame {
    JPanel showResult = new JPanel();
    public ShowResult() {
        super("��������");
        //���ݵ�ǰ�����ڵ����ݵ�������Ĵ�С
        this.setSize(250, 40 + Data.allWinner.size() * 40);
        //ʹ���ھ�����ʾ
        this.setLocation(Setting.toolkit.setCentered(this));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        showResult.setBackground(new Color(154, 154, 154));
    }

    /**
     * ���´�������
     */
    public void update() {
        this.setVisible(true);
        //���µ�������Ĵ�С
        this.setSize(250, 40 + Data.allWinner.size() * 40);
        //�����������
        showResult.removeAll();
        showResult.setLayout(new GridLayout(Data.allWinner.size(), 0,1,1));
        for (String s : Data.allWinner) {
            JPanel panel = new JPanel(new BorderLayout());
            JLabel text = new JLabel(s, JLabel.CENTER);
            panel.setBackground(new Color(220, 220, 220));
            panel.setSize(250,40);
            //ʹ���Զ�������
            text.setFont(Setting.toolkit.setTextFont(20));
            panel.add(text, BorderLayout.CENTER);
            showResult.add(panel);
        }
        this.add(showResult);
    }
}
