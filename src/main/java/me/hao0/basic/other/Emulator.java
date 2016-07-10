package me.hao0.basic.other;

import javax.swing.*;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.awt.*;
import java.io.InputStream;

public class Emulator extends JPanel {

     public static void main(String[] args) throws Exception {

         Runnable runner = new Runnable() {

              public void run() {

                   try {

                       SynthLookAndFeel synth = new SynthLookAndFeel();

                       Class aClass = Emulator.class;

                       InputStream is = aClass.getResourceAsStream("demo.xml");

                       synth.load(is, aClass);

                       UIManager.addAuxiliaryLookAndFeel(synth);

                       JFrame frame = new JFrame("SIP Client");

                       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                       frame.setLayout(new BorderLayout());

                       Emulator emulator = new Emulator();

                       emulator.setName("Emulator");

                       frame.add(emulator, BorderLayout.CENTER);

                       frame.setSize(360, 674);

                       frame.setResizable(false);

                       frame.setVisible(true);

                   } catch (Exception e) {

                       e.printStackTrace();

                   }

              }

         };

         EventQueue.invokeLater(runner);

     }
     public Emulator() {}

}
