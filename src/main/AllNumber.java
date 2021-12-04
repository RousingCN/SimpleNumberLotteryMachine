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

    public void loadNumber() {
        //�ļ�����
        File file = new File("allNumber.txt");
        //����ļ�������
        if (!file.exists()) {
            try {
                //�����ļ�
                file.createNewFile();
                JOptionPane.showMessageDialog(null, "�ļ��Ѵ��������������ݺ���������", "�����ɹ�", JOptionPane.WARNING_MESSAGE);
                FileWriter fw = new FileWriter(file);
                //ͨ��д�뻺�������ļ�д������
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("000000,123456,~~~");
                bw.close();
                fw.close();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //�ļ���СΪ��
        if (file.length() == 0) {
            JOptionPane.showMessageDialog(null, "��ȡ����ʧ�ܣ������¼��allNumber.txt�ļ�", "��ȡʧ��", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            String[] data = new String[0];
            try {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                //��ȡһ�����ݲ��Զ��Ų�����ݣ���������
                data = br.readLine().split(",");
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (data.length < 3) {
                JOptionPane.showMessageDialog(null, "������������������", "����̫����", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //�������е�������ӽ�������
            Data.allText.addAll(Arrays.asList(data));
            Setting.loadSuccess = true;
        }
    }

    /**
     * ��ȡ������ı�
     * @param numLength ���볤��
     */
    public String getNumberText(int numLength) {
        //ģʽһ ������ɺ���
        if (Setting.model == 1) {
            //���������
            int[] number = getNumber(numLength);
            String randomNumberText = "";
            for (int i = 0; i < number.length; i++) {
                //��6������ת��Ϊһ���ַ���
                randomNumberText += number[i];
            }
            return randomNumberText;
        }
        //ģʽ�� ���ļ����ȡ����
        else if (Setting.model == 2) {
            //����ɹ��������ļ��������
            if (Setting.loadSuccess) {
                //����Ӽ������ȡһ�����ݲ�����
                int serialNumber = new Random().nextInt(Data.allText.size());
                return Data.allText.get(serialNumber);
            } else {
                //����ť�޸�Ϊ����ʼ����ť
                RandomNumber.btnOk.setText("�� ʼ");
                Setting.endRandom = false;
            }
        }
        return "000000";
    }

}
