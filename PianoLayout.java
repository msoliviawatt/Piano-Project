import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
   
public class PianoLayout extends Canvas implements KeyListener {

   static Key[] keys;
   static HashMap<Integer, String> keyFile = null;

   final static int SCREEN_WIDTH = 1300;
   final static int SCREEN_HEIGHT = 500;
   static JFrame frame = null;
   public PianoLayout() {
      frame = new JFrame("Piano");
      frame.setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
      frame.getContentPane().setBackground(Color.GRAY);
      frame.add(initComponents());
      frame.setVisible(true);
      frame.setResizable(false);
      frame.addWindowListener(
         new WindowAdapter() { 
            public void windowClosing(WindowEvent e) {System.exit(0);} 
         });
      frame.setLocationRelativeTo(null);
   
      frame.addKeyListener(this);
   
      frame.requestFocusInWindow();
   }
      
   public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();
      System.out.println(key);
      String fileName = keyFile.get(key);
      if(fileName != null){
         Audio.playSound("AudioFiles/" + fileName);
      }
   }

   public void keyReleased(KeyEvent e) {
   }

   public void keyTyped(KeyEvent e) {
   }
   
   public static JLayeredPane initComponents() {
      JLayeredPane layer = new JLayeredPane();
      layer.setSize(1120, 1150);
      keys = new Key[48];
      int keyIndex = 0, i;
            
      String[] whiteKeyNames = {"C", "D", "E", "F", "G", "A", "B"};
      String[] blackKeyNames = {"C#", "D#", "F#", "G#", "A#"};
   
      int octave = 3; // starting octave number
      int blackKeyIndex = 0;
      for (i = 0; i < 28; i++) {
         JButton whiteKey = createWhiteKey(i);
         String whi = whiteKeyName(i, whiteKeyNames, octave);
         keys[keyIndex] = new Key(whi, whiteKey);
         layer.add(keys[keyIndex].getButton(), 0, -1);
         keyIndex += 1;
         if (i % 7 != 2 && i % 7 != 6) {
            JButton blackKey = createBlackKey(i);
            String bhi = blackKeyName(blackKeyIndex, blackKeyNames, octave);
            keys[keyIndex] = new Key(bhi, blackKey);
            layer.add(keys[keyIndex].getButton(), 1, -1);
            keyIndex += 1;
            blackKeyIndex++;
         }
      
        // Update octave number
         if (i % 7 == 6) {
            octave++;
         }
      }
     
     // Initiate Key-Filename mapping
      keyFile = new HashMap<Integer, String>();
      keyFile.put(KeyEvent.VK_1, "C3.mp3");
      keyFile.put(KeyEvent.VK_2, "C#3.mp3");
      keyFile.put(KeyEvent.VK_3, "D3.mp3");
      keyFile.put(KeyEvent.VK_4, "D#3.mp3");
      keyFile.put(KeyEvent.VK_5, "E3.mp3");
      keyFile.put(KeyEvent.VK_6, "F3.mp3");
      keyFile.put(KeyEvent.VK_7, "F#3.mp3");
      keyFile.put(KeyEvent.VK_8, "G3.mp3");
      keyFile.put(KeyEvent.VK_9, "G#3.mp3");
      keyFile.put(KeyEvent.VK_0, "A3.mp3");
      keyFile.put(KeyEvent.VK_MINUS, "A#3.mp3");
      keyFile.put(KeyEvent.VK_EQUALS, "B3.mp3");
      keyFile.put(KeyEvent.VK_TAB, "C4.mp3");
      keyFile.put(KeyEvent.VK_Q, "C#4.mp3");
      keyFile.put(KeyEvent.VK_W, "D4.mp3");
      keyFile.put(KeyEvent.VK_E, "D#4.mp3");
      keyFile.put(KeyEvent.VK_R, "E4.mp3");
      keyFile.put(KeyEvent.VK_T, "F4.mp3");
      keyFile.put(KeyEvent.VK_Y, "F#4.mp3");
      keyFile.put(KeyEvent.VK_U, "G4.mp3");
      keyFile.put(KeyEvent.VK_I, "G#4.mp3");
      keyFile.put(KeyEvent.VK_O, "A4.mp3");
      keyFile.put(KeyEvent.VK_P, "A#4.mp3");
      keyFile.put(KeyEvent.VK_OPEN_BRACKET, "B4.mp3");
      keyFile.put(KeyEvent.VK_CLOSE_BRACKET, "C5.mp3");
      keyFile.put(KeyEvent.VK_BACK_SLASH, "C#5.mp3");
      keyFile.put(KeyEvent.VK_CAPS_LOCK, "D3.mp3");
      keyFile.put(KeyEvent.VK_A, "D#5.mp3");
      keyFile.put(KeyEvent.VK_S, "E5.mp3");
      keyFile.put(KeyEvent.VK_D, "F5.mp3");
      keyFile.put(KeyEvent.VK_F, "F#5.mp3");
      keyFile.put(KeyEvent.VK_G, "G5.mp3");
      keyFile.put(KeyEvent.VK_H, "G#5.mp3");
      keyFile.put(KeyEvent.VK_J, "A5.mp3");
      keyFile.put(KeyEvent.VK_K, "A#5.mp3");
      keyFile.put(KeyEvent.VK_L, "B5.mp3");
      keyFile.put(KeyEvent.VK_SEMICOLON, "C6.mp3");
      keyFile.put(KeyEvent.VK_SHIFT, "C#6.mp3");
      keyFile.put(KeyEvent.VK_Z, "D6.mp3");
      keyFile.put(KeyEvent.VK_X, "D#6.mp3");
      keyFile.put(KeyEvent.VK_C, "E6.mp3");
      keyFile.put(KeyEvent.VK_V, "F6.mp3");
      keyFile.put(KeyEvent.VK_B, "F#6.mp3");
      keyFile.put(KeyEvent.VK_N, "G6.mp3");
      keyFile.put(KeyEvent.VK_M, "G#6.mp3");
      keyFile.put(KeyEvent.VK_COMMA, "A6.mp3");
      keyFile.put(KeyEvent.VK_PERIOD, "A#6.mp3");
      keyFile.put(KeyEvent.VK_SLASH, "B6.mp3"); 
           
     
      return layer;
   }

   public static JButton createWhiteKey(int i) {
      Icon whiteKeyIcon = new ImageIcon("WhiteKey.png");
      JButton whiteKey = new JButton(whiteKeyIcon);
      whiteKey.setBorder(new LineBorder(Color.BLACK));
      whiteKey.setLocation(90 + i*40, SCREEN_HEIGHT/4);
      whiteKey.setSize(40, 150);
   
      return whiteKey;
   }

   public static JButton createBlackKey(int i) {
      Icon blackKeyIcon = new ImageIcon("BlackKey.png");
      JButton blackKey = new JButton(blackKeyIcon);
      blackKey.setLocation(90 + SCREEN_WIDTH/52 + i * 40, SCREEN_HEIGHT/4);
      blackKey.setSize(30, 90);
   
      return blackKey;
   }

   public static String whiteKeyName(int i, String[] whiteKeyNames, int octave) {
      String keyName = whiteKeyNames[i % 7];
      return keyName + octave;
   }
  
   public static String blackKeyName(int i, String[] blackKeyNames, int octave) {
      String keyName = blackKeyNames[i % 5];
      return keyName + octave;
   }
  
   public static void main(String[] args) {
      new PianoLayout();
   }
}
