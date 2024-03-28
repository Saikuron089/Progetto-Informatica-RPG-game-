package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyboardInput implements KeyListener {

    public boolean w = false;
    public boolean a = false;
    public boolean s = false;
    public boolean d = false;
    public boolean enter = false;

    @Override
    public void keyTyped(KeyEvent e) {
        // Metodo non utilizzato per la pressione dei tasti
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            this.w = true;
        }else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
            this.a = true;
        }else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){
            this.s = true;
        }else if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
            this.d = true;
        }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            this.enter = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){
            this.w = false;
        }else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){
            this.a = false;
        }else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){
            this.s = false;
        }else if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){
            this.d = false;
        }else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            this.enter = false;
        }
    }
}