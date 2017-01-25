import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Stuart on 1/24/2017.
 */
public class Input implements KeyListener {
    private String s = "";
    private boolean line = false;

    @Override
    public void keyTyped(KeyEvent e) {
        s += e.getKeyChar();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public String getLine() {
        while(!line);
        String line = s;
        s = "";
        return s;
    }

    public char getKey() {
        while(s.length() == 0);
        char c = s.charAt(0);
        s = "";
        return c;
    }

    public int getInt() {
        String line = getLine();
        int i = -42;
        while(i == -42) {
            try {
                i = Integer.valueOf(line);
            } catch(NumberFormatException e) {

            }
        }
        return i;
    }
}
