package screen;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

// util

import utility.money;

public class Negozio {
   
   public void build(){

      // prende le monete

      money m = new money();

      Frame f = new Frame("Prova");

      Label l = new Label("Negozio");
      l.setBounds(220, 50, 200, 30);
      f.add(l);

      Label err = new Label("Non hai abbastanza monete");
      err.setBounds(161, 230, 200, 30);
      // cambio colore alla label
      err.setForeground(Color.RED);
      err.setVisible(false);
      f.add(err);

      Button l1 = new Button("Evoca personaggio (20 monete)");
      l1.setBounds(140, 100, 200, 30);
      l1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            // controlla se ci sono abbastanza monete

            if (m.getMoney() < 20){
               err.setVisible(true);
               return;
            }

            m.removeMoney(20);

            f.dispose();
            Evoca ev = new Evoca();
         }
      });
      f.add(l1);

      Button l2 = new Button("Torna al menu principale");
      l2.setBounds(140, 150, 200, 30);
      l2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
               System.out.print("scc");
               f.dispose();
               Main m = new Main();
               m.build();
         }
      });


      f.add(l2);

      Label l3 = new Label("Monete disponibili: " + m.getMoney());
      l3.setBounds(140, 200, 500, 30);
      f.add(l3);

      f.setResizable(false);
      f.setSize(500, 500);
      f.setLayout(null);
      f.setVisible(true);

      f.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e)
         {
             System.exit(0);
         }
     });

   }

   public static void main(String[] args) {
      Negozio n = new Negozio();
      n.build();
   }

}