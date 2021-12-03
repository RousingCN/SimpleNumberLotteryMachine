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
     * 设置窗体位置为居中
     * @param f
     */
    public Point setCentered(JFrame f) {
        //获取窗口大小
        int width = f.getWidth();
        int height = f.getHeight();
        //获取屏幕大小
        int maxScreenWidth = toolkit.getScreenSize().width;
        int maxScreenHeight = toolkit.getScreenSize().height;
        int x = (maxScreenWidth - width) / 2;
        int y = (maxScreenHeight - height) / 2;
        return new Point(x, y);
    }

    /**
     * 自定义字体-数字
     * @param fontSize 字体大小
     */
    public Font setNumberFont(int fontSize) {
        Font font;
        File file = new File("src/assets/Exo-Medium.ttf");
        try{
            //创建字体
            font = Font.createFont(Font.TRUETYPE_FONT, file);
            font = font.deriveFont(Font.PLAIN, fontSize);
        } catch (FontFormatException | IOException e){
            e.printStackTrace();
            return null;
        }
        return font;
    }

    /**
     * 自定义字体-普通文字
     * @param fontSize 字体大小
     */
    public Font setTextFont(int fontSize) {
        Font font;
        File file = new File("src/assets/NotoSansCJKsc-Regular.otf");
        try {
            //创建字体
            font = Font.createFont(Font.TRUETYPE_FONT, file);
            font = font.deriveFont(Font.PLAIN, fontSize);
        } catch (FontFormatException | IOException e) {
            return null;
        }
        return font;
    }
}
