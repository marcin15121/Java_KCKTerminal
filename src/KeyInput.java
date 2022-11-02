import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_UP;

public class KeyInput  {

    public KeyInput(){

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                switch (keyCode){
                    case VK_DOWN:
                        System.out.println("up");
                        break;
                    case VK_UP:
                        System.out.println("down");
                        break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

    }


}
