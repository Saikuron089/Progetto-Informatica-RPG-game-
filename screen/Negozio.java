package screen;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;

// util

import utility.money;

public class Negozio extends JFrame {
   
   public void build(){

      JFrame f = this;

      // prende le monete

      money m = new money();

      Label l = new Label("Negozio");
      l.setBounds(220, 50, 200, 30);
      this.add(l);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      Label err = new Label("Non hai abbastanza monete");
      err.setBounds(161, 230, 200, 30);
      // cambio colore alla label
      err.setForeground(Color.RED);
      err.setVisible(false);
      this.add(err);

      Button l1 = new Button("Evoca personaggio (20 monete)");
      l1.setBounds(140, 100, 200, 30);
      l1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            f.dispose();

            // controlla se ci sono abbastanza monete

            if (m.getMoney() < 20){
               err.setVisible(true);
               return;
            }

            m.removeMoney(20);

            Evoca ev = new Evoca();
         }
      });
      this.add(l1);

      Button l2 = new Button("Torna al menu principale");
      l2.setBounds(140, 150, 200, 30);
      l2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
               f.dispose();
               Main m = new Main();
               m.build();
         }
      });


      this.add(l2);

      Label l3 = new Label("Monete disponibili: " + m.getMoney());
      l3.setBounds(140, 200, 500, 30);
      this.add(l3);

      this.setResizable(false);
      this.setSize(500, 500);
      this.setLayout(null);
      this.setVisible(true);

   }

   public static void main(String[] args) {
      Negozio n = new Negozio();
      n.build();
   }

}