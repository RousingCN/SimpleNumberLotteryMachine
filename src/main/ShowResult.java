package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ShowResult extends JFrame {
    JPanel showResult;
    public ShowResult(ArrayList<String> result) {
        super("��������");
        this.setVisible(true);
        //���ݵ�ǰ�����ڵ����ݵ�������Ĵ�С
        this.setSize(250, 40 + result.size() * 40);
        //ʹ���ھ�����ʾ
        this.setLocation(Setting.toolkit.setCentered(this));
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //�����
        showResult = new JPanel(new GridLayout(result.size(), 0,1,1));
        showResult.setBackground(new Color(154, 154, 154));
        //�������
        for (String s : result) {
            //�ڲ���� ÿһ�����붼�ᵥ��һ�������
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
