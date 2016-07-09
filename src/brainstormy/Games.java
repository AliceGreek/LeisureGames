
package brainstormy;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

class PlayGame extends JFrame implements ActionListener{
    JButton jb1,jb2;
    JLabel jl1;
    JPanel jp1;
    public PlayGame() throws HeadlessException {
         ImageIcon image = new ImageIcon("image\\2.jpg");
         ImageIcon bImage1 = new ImageIcon("image\\3.jpg");
         ImageIcon bImage2 = new ImageIcon("image\\4.jpg");
         
        jl1 = new JLabel(image);
        jl1.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());
        
        this.add(jl1);
       
        this.setSize(image.getIconWidth(),image.getIconHeight());
        this.setLocationRelativeTo(null); 
        this.setTitle("史上最卡哇伊游戏");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        
       
        
        jb1 = new JButton(bImage1);
        jb1.setIcon(bImage1);
        jb1.setBorderPainted(false);
        jb1.setBounds(280, 150, bImage1.getIconWidth(), bImage1.getIconHeight());
        this.getContentPane().add(jb1);
        jb1.addActionListener(this);
        
        jb2 = new JButton(bImage2);
        jb2.setIcon(bImage2);
        jb2.setBorderPainted(false);
        jb2.setBounds(280, 250, bImage2.getIconWidth(), bImage2.getIconHeight());
        this.getContentPane().add(jb2);
        jb2.addActionListener(this);
        
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==jb1){
            this.setVisible(false);
            JigsawPuzzle jp =new JigsawPuzzle();
       }
       else if(e.getSource()==jb2){
             this.setVisible(false);
             Memory m =new Memory();
       }
    }
    
}
public class Games {

    public static void main(String[] args) {
        PlayGame pg = new PlayGame();
    }
    
}
