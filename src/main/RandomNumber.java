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
    JLabel title = new JLabel("�� �� ��", JLabel.CENTER);
    JLabel numberText = new JLabel("000000", JLabel.CENTER);
    public static JButton btnOk = new JButton("�� ʼ");
    JButton btnShow = new JButton("�鿴������");
    JButton btnSwitch = new JButton("�л���ģʽ��");


    /**
     * ͼ�ν���
     */
    public RandomNumber() {
        JFrame f = new JFrame("����齱��");
        f.setVisible(true);
        f.setSize(500, 300);
        //ʹ���ھ�����ʾ
        f.setLocation(Setting.toolkit.setCentered(f));
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //ʹ���Զ������壬��ͬ
        title.setFont(Setting.toolkit.setTextFont(20));
        //����������ɫ
        title.setForeground(Color.white);
        numberText.setFont(Setting.toolkit.setNumberFont(30));
        //��ť��͸����������
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
            //������ǡ���ʼ����ť
            if (!Setting.endRandom) {
                //������һ���߳�
                Setting.startRandom = true;
                Thread t = new Thread(this);
                t.start();
                //����ť�޸�Ϊ����������ť
                btnOk.setText("�� ��");
                Setting.endRandom = true;
                //���ÿ�ʼ�����ȡ����ʱ���ܰ�������ť
                btnSwitch.setEnabled(false);
                btnShow.setEnabled(false);
            }
            //������ǡ���������ť
            else {
                //ʹ�߳��ڵ�ѭ�������������߳̽���
                Setting.startRandom = false;
                //����ť�޸�Ϊ����ʼ����ť
                btnOk.setText("�� ʼ");
                Setting.endRandom = false;
                //���ý��������ȡ���������������ť
                btnSwitch.setEnabled(true);
                btnShow.setEnabled(true);
            }
        } else if (e.getSource() == btnShow) {
            //������ǡ��鿴����������ť
            if (Data.allWinner.size() != 0) {
                //�򿪡���������������
                new ShowResult(Data.allWinner);
            } else {
                JOptionPane.showMessageDialog(null, "���ȳ�ȡ����һ�����ߺ��룡", "û�л���", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == btnSwitch) {
            //������ǡ��л�ģʽ����ť
            //��ǰģʽΪģʽһ
            if (Setting.model == 1) {
                Setting.model = 2;
                btnSwitch.setText("�л���ģʽһ");
                btnSwitch.setBackground(new Color(140, 132, 132));
            }
            //��ǰģʽΪģʽ��
            else if (Setting.model == 2) {
                Setting.model = 1;
                btnSwitch.setText("�л���ģʽ��");
                btnSwitch.setBackground(new Color(154, 161, 151));
            }
        }
    }
    @Override
    public void run() {
        AllNumber allNumber = new AllNumber();
        //ͨ��startRandom�����ú�ʱ����ѭ��
        while (Setting.startRandom) {
            //��ȡ������ı�
            Data.winnerNumber = allNumber.getNumberText(6);
            //�������ý�������ʾ������
            numberText.setText(Data.winnerNumber);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //������߱��
        Data.allWinner.add(Data.winnerNumber);

    }

}
