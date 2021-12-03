package toolkit;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author RousingCN
 */
public class MyToolkit {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    public MyToolkit() {
    }

    /**
     * ���ô���λ��Ϊ����
     * @param f
     */
    public Point setCentered(JFrame f) {
        //��ȡ���ڴ�С
        int width = f.getWidth();
        int height = f.getHeight();
        //��ȡ��Ļ��С
        int maxScreenWidth = toolkit.getScreenSize().width;
        int maxScreenHeight = toolkit.getScreenSize().height;
        int x = (maxScreenWidth - width) / 2;
        int y = (maxScreenHeight - height) / 2;
        return new Point(x, y);
    }

    /**
     * �Զ�������-����
     * @param fontSize �����С
     */
    public Font setNumberFont(int fontSize) {
        Font font;
        File file = new File("src/assets/Exo-Medium.ttf");
        try{
            //��������
            font = Font.createFont(Font.TRUETYPE_FONT, file);
            font = font.deriveFont(Font.PLAIN, fontSize);
        } catch (FontFormatException | IOException e){
            e.printStackTrace();
            return null;
        }
        return font;
    }

    /**
     * �Զ�������-��ͨ����
     * @param fontSize �����С
     */
    public Font setTextFont(int fontSize) {
        Font font;
        File file = new File("src/assets/NotoSansCJKsc-Regular.otf");
        try {
            //��������
            font = Font.createFont(Font.TRUETYPE_FONT, file);
            font = font.deriveFont(Font.PLAIN, fontSize);
        } catch (FontFormatException | IOException e) {
            return null;
        }
        return font;
    }
}
