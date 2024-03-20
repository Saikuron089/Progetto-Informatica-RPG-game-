package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyboardInput implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        // Metodo non utilizzato per la pressione dei tasti
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            System.out.println("Hai premuto il tasto 'w'");
            // Aggiungi qui le azioni da eseguire quando viene premuto il tasto 'w'
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Metodo non utilizzato per il rilascio dei tasti
    }

    public static void main(String[] args) {
        // Aggiungi il KeyListener al componente appropriato
        // Esempio: frame.addKeyListener(new MyKeyListener());
    }
}