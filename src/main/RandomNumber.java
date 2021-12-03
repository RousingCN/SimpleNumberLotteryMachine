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
    JLabel title = new JLabel("�� �� ��", JLabel.CENTER);
    JLabel numberText = new JLabel("000000", JLabel.CENTER);
    JButton btnOk = new JButton("�� ʼ");
    JButton btnShow = new JButton("�鿴���");
    //�Զ����࣬����ʹ���ھ��к�ʹ���Զ�������
    MyToolkit toolkit = new MyToolkit();
    //��������һ���߳��ھ����Ƿ�ý����߳�
    boolean endRandom = false;
    //�����л���ťִ�е��¼�
    boolean startRandom = true;
    //���ڱ��浱ǰ������ɵĺ���
    String winnerNumber = "";
    //���ڱ������л��ߺ���ļ���
    ArrayList<String> allWinner = new ArrayList<>();
    /**
     * ͼ�ν���
     */
    public RandomNumber() {
        JFrame f = new JFrame("����齱��");
        f.setVisible(true);
        f.setSize(500, 300);
        //ʹ���ھ�����ʾ
        f.setLocation(toolkit.setCentered(f));
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //ʹ���Զ������壬��ͬ
        title.setFont(toolkit.setTextFont(20));
        //����������ɫ
        title.setForeground(Color.white);
        numberText.setFont(toolkit.setNumberFont(30));
        //��ť��͸����������
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
            //������ǡ���ʼ����ť
            if (!endRandom) {
                //������һ���߳�
                startRandom = true;
                Thread t = new Thread(this);
                t.start();
                //����ť�޸�Ϊ����������ť
                btnOk.setText("�� ��");
                endRandom = true;
            }
            //������ǡ���������ť
            else {
                //ʹ�߳��ڵ�ѭ�������������߳̽���
                startRandom = false;
                //����ť�޸�Ϊ����ʼ����ť
                btnOk.setText("�� ʼ");
                endRandom = false;
            }
        } else if (e.getSource() == btnShow) {
            if (allWinner.size() != 0) {
                //�򿪡���������������
                new ShowResult(allWinner);
            } else {
                JOptionPane.showMessageDialog(null, "���ȳ�ȡ����һ�����ߺ��룡", "û�л���", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    @Override
    public void run() {
        while (startRandom) {
            //��ȡ������ı�
            winnerNumber = getNumberText(6);
            //�������ý�������ʾ������
            numberText.setText(winnerNumber);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //������߱��
        allWinner.add(winnerNumber);
    }

    /**
     * ���������
     * @param numLength ���볤��
     */
    public int[] getNumber(int numLength) {
        int[] number = new int[numLength];
        for (int i = 0; i < number.length; i++) {
            //ÿһ�񶼻��0-10��������10��֮�������������
            number[i] = new Random().nextInt(10);
        }
        return number;
    }

    /**
     * ��ȡ������ı�
     * @param numLength ���볤��
     */
    public String getNumberText(int numLength) {
        return Arrays.toString(getNumber(numLength));
    }

}
