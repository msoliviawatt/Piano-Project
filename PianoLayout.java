import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
   
public class PianoLayout extends Canvas implements KeyListener {
   private BufferStrategy strategy;

   //counters
   static int counterA = 0;
   static int counterB = 0;
   static int counterC = 0;
   static int counterD = 0;
   static int counterE = 0;
   static int counterF = 0;
   static int counterG = 0;
   static int counterSharpC = 0;
   static int counterSharpD = 0;
   static int counterSharpF = 0;
   static int counterSharpG = 0;
   static int counterSharpA = 0;
   static Key[] keys;

   //keys
   static boolean isPressed = false;
   static boolean isReleased = false;
   static boolean A2 = false;
   static boolean A3 = false;
   static boolean A4 = false;
   static boolean A5 = false;
   static boolean As2 = false;
   static boolean As3 = false;
   static boolean As4 = false;
   static boolean As5 = false;
   static boolean B2 = false;
   static boolean B3 = false;
   static boolean B4 = false;
   static boolean B5 = false;
   static boolean C2 = false;
   static boolean C3 = false;
   static boolean C4 = false;
   static boolean C5 = false;
   static boolean Cs2 = false;
   static boolean Cs3 = false;
   static boolean Cs4 = false;
   static boolean Cs5 = false;
   static boolean D2 = false;
   static boolean D3 = false;
   static boolean D4 = false;
   static boolean D5 = false;
   static boolean Ds2 = false;
   static boolean Ds3 = false;
   static boolean Ds4 = false;
   static boolean Ds5 = false;
   static boolean E2 = false;
   static boolean E3 = false;
   static boolean E4 = false;
   static boolean E5 = false;
   static boolean F2 = false;
   static boolean F3 = false;
   static boolean F4 = false;
   static boolean F5 = false;
   static boolean Fs2 = false;
   static boolean Fs3 = false;
   static boolean Fs4 = false;
   static boolean Fs5 = false;
   static boolean G2 = false;
   static boolean G3 = false;
   static boolean G4 = false;
   static boolean G5 = false;
   static boolean Gs2 = false;
   static boolean Gs3 = false;
   static boolean Gs4 = false;
   static boolean Gs5 = false;

   final static int SCREEN_WIDTH = 1120;
   final static int SCREEN_HEIGHT = 300;

   public PianoLayout() {
      JFrame frame = new JFrame("Piano");
      frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
      frame.getContentPane().setBackground(Color.GRAY);
      frame.add(initComponents());
      frame.setVisible(true);
      frame.setResizable(false);
      frame.addWindowListener(new WindowAdapter() { 
         public void windowClosing(WindowEvent e) {System.exit(0);} 
      });

      render();
   }

   public void render() {
      //renders # of frames in the background then shows them in order
      //the parameter is the number of frames that are cycled through
      createBufferStrategy(2);
      strategy = getBufferStrategy();
      Graphics g = null;
      do {
         try{
            g =  strategy.getDrawGraphics();

         } finally {
            paint(g);
         }
         strategy.show();
         g.dispose();
      } while (strategy.contentsLost());
      Toolkit.getDefaultToolkit().sync();
   }  
   public static JLayeredPane initComponents() {
      JLayeredPane layer = new JLayeredPane();
      layer.setSize(1120,1150);
      keys = new Key[48];
      int keyIndex = 0, i;
   
      for(i=0; i<28; i++)
      {
         JButton whiteKey = createWhiteKey(i);
         String whi = whiteKeyName(i);
         keys[keyIndex] = new Key(whi, whiteKey);
         if(whi.equals("A")) {
            counterA++;
            whi = whi + counterA;
         } else if (whi.equals("B")) {
            counterB++;
            whi = whi + counterB;
         } else if (whi.equals("C")) {
            counterC++;
            whi = whi + counterC;
         } else if (whi.equals("D")) {
            counterD++;
            whi = whi + counterD;
         } else if (whi.equals("E")) {
            counterE++;
            whi = whi + counterE;
         } else if (whi.equals("F")) {
            counterF++;
            whi = whi + counterF;
         } else if (whi.equals("G")) {
            counterG++;
            whi = whi + counterG;
         }
         keys[keyIndex].setKey(whi);
         layer.add(keys[keyIndex].getButton(), 0, -1);
         keyIndex += 1;
         if(i % 7 != 2 && i % 7 != 6)
         {
            JButton blackKey = createBlackKey(i);
            String bhi = blackKeyName(i);
            keys[keyIndex] = new Key(bhi, blackKey);
            if(bhi.equals("C#")) {
               counterSharpC++;
               bhi = bhi + counterSharpC;
            } else if (bhi.equals("D#")) {
               counterSharpD++;
               bhi = bhi + counterSharpD;
            } else if (bhi.equals("F#")) {
               counterSharpF++;
               bhi = bhi + counterSharpF;
            } else if (bhi.equals("G#")) {
               counterSharpG++;
               bhi = bhi + counterSharpG;
            } else if (bhi.equals("A#")) {
               counterSharpA++;
               bhi = bhi + counterSharpA;
            }
            keys[keyIndex].setKey(bhi);
            layer.add(keys[keyIndex].getButton(), 1, -1);
            keyIndex+=1;

            //temporary thing
            System.out.println(keys[i].getKey());
         }
      }
      return layer;
   }

   public static JButton createWhiteKey(int i) {
      Icon whiteKeyIcon = new ImageIcon("WhiteKey.png");
      JButton whiteKey = new JButton(whiteKeyIcon);
      whiteKey.setBorder(new LineBorder(Color.BLACK));
      whiteKey.setLocation(i*40, SCREEN_HEIGHT/4);
      whiteKey.setSize(40, 150);
      whiteKey.addActionListener(
      new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.out.println("white key");
         }
      });

      return whiteKey;
   }

   public static JButton createBlackKey(int i) {
      Icon blackKeyIcon = new ImageIcon("BlackKey.png");
      JButton blackKey = new JButton(blackKeyIcon);
      blackKey.setLocation(SCREEN_WIDTH/52 + i * 40, SCREEN_HEIGHT/4);
      blackKey.setSize(30, 90);
      blackKey.addActionListener(
      new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.out.println("black key");
         }
      });
      return blackKey;
   }

   public static String whiteKeyName(int i) {
      String[] whiteKeyNames = {"C","D","E","F","G","A","B"};
      return whiteKeyNames[i%7];
   }

   public static String blackKeyName(int i) {
      String[] blackKeyNames = {"C#","D#","F#","G#","A#"};
      return blackKeyNames[i%5];
   }

   //key events
   public void keyPressed(KeyEvent e) {
      if(e.getKeyCode() == KeyEvent.VK_A) {
         A2 = true;
      }
   }

   public void keyReleased(KeyEvent e) {
   }

   public void keyTyped(KeyEvent e) {
   }

   ActionListener actionListener = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
         if (A2) {
            Audio.playSound("A2.mp3");
         } if (A3) {
            Audio.playSound("A3.mp3");
         } if (A4) {
            Audio.playSound("A4.mp3");
         } if (A5) {
            Audio.playSound("A5.mp3");
         }
         render();
      }
   };

   public static void main(String[] args) {
      new PianoLayout();
   }

   
}
