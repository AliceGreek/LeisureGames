

package brainstormy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

public class JigsawPuzzle extends JFrame implements ActionListener {

    JButton btn1, btn2;
    JLabel label1;
    JPanel panel, panel2;
    JTextField tf1;

    public JigsawPuzzle() {
        this.setTitle("Jigsaw Puzzle");
        btn1 = new JButton("registrer");
        btn1.setSize(30, 10);
        btn2 = new JButton("cancel");
        btn2.setSize(30, 10);
        label1 = new JLabel("name:");
        tf1 = new JTextField(15);
        panel = new JPanel();
        panel2 = new JPanel();
        panel.add(label1);
        panel.setBackground(Color.yellow);
        panel.add(tf1);
        this.setSize(400, 200);
        this.setLocationRelativeTo(null); 
        panel2.add(btn1);
        panel2.add(btn2);
        panel2.setBackground(Color.yellow);
        this.add(panel);
        this.add(panel2);
        this.setLayout(new GridLayout(2, 1));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.yellow);
        this.setVisible(true);

        btn1.addActionListener(this);



        btn2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
     public static void main(String[] args) {
       new JigsawPuzzle();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==btn1){
           this.setVisible(false);
      GameFrame gf=new GameFrame();
    }
    }}

 class GameFrame extends JFrame implements ActionListener {

    private int row = 2;
    private int column = 2;
    private int clickedNum = 0;

    private boolean clickedLeft = false; //左边labelStart中有点击事件发生
    private boolean clickedRight = false;//右边labelEnd中有点击事件发生
    private boolean[] used = new boolean[4];
    private final String[] answer = new String[4];//= {"Images\\11.png", "Images\\12.png", "Images\\13.png", "Images\\14.png"};

    private ImageIcon leftIcon = new ImageIcon();
    private JLabel leftLabel = new JLabel();

    private JButton btnOK;
    private JButton btnCancel;

    private JLabel[] labStart = new JLabel[row * column];
    private JLabel[] labEnd = new JLabel[row * column];

    private ImageIcon select = new ImageIcon("Images\\ok.png");
    private ImageIcon image1 = new ImageIcon("Images\\11.png");
    private ImageIcon image2 = new ImageIcon("Images\\12.png");
    private ImageIcon image3 = new ImageIcon("Images\\13.png");
    private ImageIcon image4 = new ImageIcon("Images\\14.png");
    private ImageIcon image5 = new ImageIcon("Images\\15.png");
    private ImageIcon image6 = new ImageIcon("Images\\16.png");
    private ImageIcon image7 = new ImageIcon("Images\\17.png");
    private ImageIcon image8 = new ImageIcon("Images\\18.png");

    public GameFrame() {

        setBounds(100, 100, 1200, 480);
        getContentPane().add(getWest(), BorderLayout.WEST);
        getContentPane().add(getEast(), BorderLayout.EAST);
        getContentPane().add(getSouth(), BorderLayout.SOUTH);
        setTitle("JiasawPuzzle");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setRightLabelId();
    }

    private JPanel getWest() {
        JPanel p = new JPanel(new GridLayout(row, column));
        JLabel lab0 = new JLabel(image4);
        p.add(lab0);
        labStart[0] = lab0;
        labStart[0].addMouseListener(new ClickListener());

        JLabel lab1 = new JLabel(image3);
        p.add(lab1);
        labStart[1] = lab1;
        labStart[1].addMouseListener(new ClickListener());

        JLabel lab2 = new JLabel(image2);
        p.add(lab2);
        labStart[2] = lab2;
        labStart[2].addMouseListener(new ClickListener());

        JLabel lab3 = new JLabel(image1);
        p.add(lab3);
        labStart[3] = lab3;
        labStart[3].addMouseListener(new ClickListener());

        return p;
    }

    private JPanel getEast() {
        JPanel p = new JPanel(new GridLayout(row, column));
        JLabel lab0 = new JLabel(image5);
        p.add(lab0);
        labEnd[0] = lab0;
        labEnd[0].addMouseListener(new SetIconListener());

        JLabel lab1 = new JLabel(image6);
        p.add(lab1);
        labEnd[1] = lab1;
        labEnd[1].addMouseListener(new SetIconListener());

        JLabel lab2 = new JLabel(image7);
        p.add(lab2);
        labEnd[2] = lab2;
        labEnd[2].addMouseListener(new SetIconListener());

        JLabel lab3 = new JLabel(image8);
        p.add(lab3);
        labEnd[3] = lab3;
        labEnd[3].addMouseListener(new SetIconListener());

        return p;

    }

    private JPanel getSouth() {
        JPanel p = new JPanel(new GridLayout(1, 2));
        btnOK = new JButton("ok");
        btnOK.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameOver()) {
                new GameOverShowwin();
            }
            else {
                new GameOverShowlose();
            }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        btnCancel = new JButton("cancle");
        btnCancel.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        p.add(btnOK);
        p.add(btnCancel);
        btnOK.addActionListener(this);
        btnCancel.addActionListener(this);
        return p;

    }

    private void saveAns(int pos, String str) {
        answer[pos] = str;
    }

    private void setRightLabelId() {
        for (int i = 0; i < row * column; i++) {
            labEnd[i].setName(i + "");
        }
    }

    private String getIconName(JLabel label) {
        return label.getIcon().toString();
    }

    private int getLabelId(JLabel lab) {
        return Integer.parseInt(lab.getName().toString());
    }

    private void clickedLeftPanel() {
        clickedLeft = true;
        clickedRight = false;
    }

    private void clickedRightPanel() {
        clickedLeft = false;
        clickedRight = true;
    }

    private boolean getClickedLeftStatus() {
        return clickedLeft;
    }

    private boolean getClickedRightStatus() {
        return clickedRight;
    }

    private void saveClickedIcon(ImageIcon leftIcon) {
        this.leftIcon = leftIcon;
    }

    void display() {
        for (String s : answer) {
            System.out.println(s);
        }
    }

    private boolean gameOver() {
        try {
            if (answer[0].equals("Images\\11.png") && answer[1].equals("Images\\12.png")
                    && answer[2].equals("Images\\13.png") && answer[3].equals("Images\\14.png")) {
                return true;
            }  
        } catch (NullPointerException e) {
        }
        return false;
    }

    private void saveClickedLeftLabel(JLabel lab) {
        leftLabel = lab;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    

    class ClickListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            if (getClickedLeftStatus() && !getClickedRightStatus()) {
                try {
                    leftLabel.setIcon(leftIcon);
                } catch (NullPointerException ne) {
                }
            }
            saveClickedIcon((ImageIcon) label.getIcon());
            saveClickedLeftLabel(label);
            label.setIcon(select);
            clickedLeftPanel();
            System.out.println(label.getIcon());
        }
    }

    class SetIconListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            clickedRightPanel();
            try {
                label.setIcon(leftIcon);
                saveAns(getLabelId(label), getIconName(label));
            } catch (NullPointerException ne) {
            }
            
        }

    }

    class GameOverShowwin extends JFrame {

        ImageIcon showImage = new ImageIcon("Images//21.jpg");
        

        public GameOverShowwin() {
            setBounds(500, 500, 400, 200);
            this.setLocationRelativeTo(null); 
            JLabel show = new JLabel();
            //setLayout(null);
            show.setIcon(showImage);
            this.add(show);
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setResizable(false);
            setVisible(true);
        }

    }
    
    class GameOverShowlose extends JFrame {

        ImageIcon showImage1 = new ImageIcon("Images//lose.png");
        

        public GameOverShowlose() {
            setBounds(500, 500, 400, 200);
                this.setLocationRelativeTo(null); 
            JLabel show = new JLabel();
            //setLayout(null);
            show.setIcon(showImage1);
            this.add(show);
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setResizable(false);
            setVisible(true);
        }

    }

}

