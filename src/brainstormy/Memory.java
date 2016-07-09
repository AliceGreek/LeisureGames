package brainstormy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Memory extends JFrame implements ActionListener {

    JButton btn1, btn2;
    JLabel label1;
    JPanel panel, panel2;
    JTextField tf1;

    public Memory() {
        this.setTitle("Memory");

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
        new Memory();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            this.setVisible(false);
            Memory_easy me = new Memory_easy();
        }
    }
}

class Memory_easy extends JFrame implements ActionListener {

    private final String scoreFlag = "分数 : ";
    private int clickNumber = 0; //鼠标的点击次数
    private String[] iconNameMatch = new String[2];
    private int row = 4;
    private int column = 4;
    private int score = 0;
    private JLabel showScoreLabel;           //分数
    private JLabel[] labs = new JLabel[row * column];
    private Boolean[] flags = new Boolean[16];
    private ImageIcon[] fruit = new ImageIcon[row * column];
    private ImageIcon[] totalfruit = new ImageIcon[16];
    private int[] clicked = new int[2];

    private JButton btn;
    private int rdIndex;                //产生的随机索引
    private Random rd = new Random();
    private boolean show = false;
    private boolean gameRunning = false;
    private JLabel[] labMatch = {new JLabel(), new JLabel()};
    private boolean[] showIcon = new boolean[16];
    public ImageIcon close1 = new ImageIcon("image\\9.png");//初始图片
    private ImageIcon open1 = new ImageIcon("image\\1.png");
    private ImageIcon open2 = new ImageIcon("image\\2.png");
    private ImageIcon open3 = new ImageIcon("image\\3.png");
    private ImageIcon open4 = new ImageIcon("image\\4.png");
    private ImageIcon open5 = new ImageIcon("image\\5.png");
    private ImageIcon open6 = new ImageIcon("image\\6.png");
    private ImageIcon open7 = new ImageIcon("image\\7.png");
    private ImageIcon open8 = new ImageIcon("image\\8.png");
    private ImageIcon open9 = new ImageIcon("image\\1.png");
    private ImageIcon open10 = new ImageIcon("image\\2.png");
    private ImageIcon open11 = new ImageIcon("image\\3.png");
    private ImageIcon open12 = new ImageIcon("image\\4.png");
    private ImageIcon open13 = new ImageIcon("image\\5.png");
    private ImageIcon open14 = new ImageIcon("image\\6.png");
    private ImageIcon open15 = new ImageIcon("image\\7.png");
    private ImageIcon open16 = new ImageIcon("image\\8.png");

    Memory_easy() {
        this.setBounds(300, 0, 780, 720);
        this.setTitle("Memory Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(getCenter(), BorderLayout.CENTER);
        this.getContentPane().add(getNorth(), BorderLayout.NORTH);
        this.setResizable(false);//不能更改窗体大小
        fruit();
        init();  //初始化labMatch数组和iconNameMatch数组
        initShowIcon();
        showScore();
        this.setVisible(true);
    }

    class TimeDelayThread extends Thread {

        JLabel label;
        ImageIcon icon;

        public TimeDelayThread(JLabel label, ImageIcon icon) {
            this.label = label;
            this.icon = icon;
        }

        @Override
        public void run() {
            try {

                label.setIcon(icon);
                Thread.currentThread().sleep(100 * 4);
                iconNameMatch[clickNumber % 2] = label.getIcon().toString();
                labMatch[clickNumber % 2] = label;
                saveClickedLabelId(label);
                if (!clickedSamePos()) {
                    if (!match()) {
                        changeIcon();//调用match方法，判断每个前后两次点击是否匹配
                    }
                }
                if (clickedSamePos()) {
                    changeIcon();
                }
                addClickNumber();

            } catch (InterruptedException e) {
            }
        }

    }

    private void addScore() {
        score += 125;
        if (score >= 1000) {
            score = 1000;
        }
    }

    private int getScore() {
        return score;
    }

    private void showScore() {
        if (getScore() != 1000) {
            showScoreLabel.setText(scoreFlag + score);
        } else if (getScore() == 1000) {
            showScoreLabel.setText("Win!");
            btn.setText("Game Over");
        }
    }

    private boolean clickedSamePos() {
        if (clicked[0] == clicked[1]) {
            return true;
        }
        return false;
    }

    private void gameStart() {
        gameRunning = true;
    }

    private void gamePause() {
        gameRunning = false;
    }

    private boolean getGameStatus() {
        return gameRunning;
    }

    private void addClickNumber() {
        if (!clickedSamePos()) {
            clickNumber++;
        }
    }

    private int getClickNumber() {
        return clickNumber;
    }

    private void initShowIcon() {
        for (int i = 0; i < 16; i++) {
            showIcon[i] = false;
        }
    }

    private void saveIconName(JLabel label) {
        iconNameMatch[clickNumber % 2] = label.getIcon().toString();
        System.out.println("clickNums : " + clickNumber % 2);// + "IconID : " + labMatch[clickNumber % 2].getName().toString());
    }

    private void saveClickedLabel(JLabel label) {
        labMatch[clickNumber % 2] = label;
    }

    private void saveClickedLabelId(JLabel lab) {
        clicked[clickNumber % 2] = getLabelId(lab);
    }

    private void init() {
        labMatch[0] = labs[0];
        labMatch[1] = null;
        iconNameMatch[0] = "image\\0.png";
        iconNameMatch[1] = "image\\9.png";
        clicked[0] = -1;
        clicked[1] = -2;
    }

    private boolean match() {
        if (iconNameMatch[0].equals(iconNameMatch[1])) {
            showIcon[Integer.parseInt(labMatch[0].getName())] = true;
            showIcon[Integer.parseInt(labMatch[1].getName())] = true;
            labMatch[0].setIcon(fruit[Integer.parseInt(labMatch[0].getName())]);
            labMatch[1].setIcon(fruit[Integer.parseInt(labMatch[1].getName())]);
            addScore();
            showScore();
            return true;
        }
        return false;
    }

    private void changeIcon() {
        try {
            if (!getLabelStatus(Integer.parseInt(labMatch[0].getName()))) {
                labMatch[0].setIcon(close1);
            }
            if (!getLabelStatus(Integer.parseInt(labMatch[1].getName()))) {
                labMatch[1].setIcon(close1);
            }
        } catch (NullPointerException e) {
        }
    }

    private void setLabelId() {
        for (int i = 0; i < 16; i++) {
            labs[i].setName(i + "");
        }
    }

    private int getLabelId(JLabel lab) {
        return Integer.parseInt(lab.getName());
    }

    private JPanel getCenter() {
        JPanel p = new JPanel(new GridLayout(row, column));
        MyMouseListenter1 mgl1 = new MyMouseListenter1();
        MyMouseListenter2 mgl2 = new MyMouseListenter2();
        MyMouseListenter3 mgl3 = new MyMouseListenter3();
        MyMouseListenter4 mgl4 = new MyMouseListenter4();
        MyMouseListenter5 mgl5 = new MyMouseListenter5();
        MyMouseListenter6 mgl6 = new MyMouseListenter6();
        MyMouseListenter7 mgl7 = new MyMouseListenter7();
        MyMouseListenter8 mgl8 = new MyMouseListenter8();

        MyMouseListenter9 mgl9 = new MyMouseListenter9();
        MyMouseListenter10 mgl10 = new MyMouseListenter10();
        MyMouseListenter11 mgl11 = new MyMouseListenter11();
        MyMouseListenter12 mgl12 = new MyMouseListenter12();
        MyMouseListenter13 mgl13 = new MyMouseListenter13();
        MyMouseListenter14 mgl14 = new MyMouseListenter14();
        MyMouseListenter15 mgl15 = new MyMouseListenter15();
        MyMouseListenter16 mgl16 = new MyMouseListenter16();

        JLabel lab0 = new JLabel(close1);
        lab0.addMouseListener(mgl1);
        p.add(lab0);
        labs[0] = lab0;

        JLabel lab1 = new JLabel(close1);
        lab1.addMouseListener(mgl2);
        p.add(lab1);
        labs[1] = lab1;

        JLabel lab2 = new JLabel(close1);
        lab2.addMouseListener(mgl3);
        p.add(lab2);
        labs[2] = lab2;

        JLabel lab3 = new JLabel(close1);
        lab3.addMouseListener(mgl4);
        p.add(lab3);
        labs[3] = lab3;

        JLabel lab4 = new JLabel(close1);
        lab4.addMouseListener(mgl5);
        p.add(lab4);
        labs[4] = lab4;

        JLabel lab5 = new JLabel(close1);
        lab5.addMouseListener(mgl6);
        p.add(lab5);
        labs[5] = lab5;

        JLabel lab6 = new JLabel(close1);
        lab6.addMouseListener(mgl7);
        p.add(lab6);
        labs[6] = lab6;

        JLabel lab7 = new JLabel(close1);
        lab7.addMouseListener(mgl8);
        p.add(lab7);
        labs[7] = lab7;

        JLabel lab8 = new JLabel(close1);
        lab8.addMouseListener(mgl9);
        p.add(lab8);
        labs[8] = lab8;

        JLabel lab9 = new JLabel(close1);
        lab9.addMouseListener(mgl10);
        p.add(lab9);
        labs[9] = lab9;

        JLabel lab10 = new JLabel(close1);
        lab10.addMouseListener(mgl11);
        p.add(lab10);
        labs[10] = lab10;

        JLabel lab11 = new JLabel(close1);
        lab11.addMouseListener(mgl12);
        p.add(lab11);
        labs[11] = lab11;

        JLabel lab12 = new JLabel(close1);
        lab12.addMouseListener(mgl13);
        p.add(lab12);
        labs[12] = lab12;

        JLabel lab13 = new JLabel(close1);
        lab13.addMouseListener(mgl14);
        p.add(lab13);
        labs[13] = lab13;

        JLabel lab14 = new JLabel(close1);
        lab14.addMouseListener(mgl15);
        p.add(lab14);
        labs[14] = lab14;

        JLabel lab15 = new JLabel(close1);
        lab15.addMouseListener(mgl16);
        p.add(lab15);
        labs[15] = lab15;

        setLabelId();

        return p;
    }

    private void fruit() {
        int k = 0;
        for (int j = 0; j < 16; j++) {
            flags[j] = true;
        }
        totalfruit[0] = open1;
        totalfruit[1] = open2;
        totalfruit[2] = open3;
        totalfruit[3] = open4;
        totalfruit[4] = open5;
        totalfruit[5] = open6;
        totalfruit[6] = open7;
        totalfruit[7] = open8;

        for (k = 0; k < 8; k++) {
            int count = 0;
            while (count < 2) {

                rdIndex = rd.nextInt(row * column);
                if (flags[rdIndex]) {
                    fruit[rdIndex] = totalfruit[k];
                    flags[rdIndex] = false;
                    count++;
                }
            }
        }
    }

    private JPanel getNorth() {
        FlowLayout fl = new FlowLayout(FlowLayout.RIGHT, 250, 10);
        JPanel p = new JPanel(fl);
        btn = new JButton("开始");
        btn.addActionListener(this);        //为按钮注册点击事件的处理者
        p.add(btn);
        showScoreLabel = new JLabel();
        showScoreLabel.setText(scoreFlag + 0);
        p.add(showScoreLabel);
        return p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (btn.getText().equals("开始")) {
            gameStart(); //游戏状态改为TRUE，游戏正在进行
            btn.setText("暂停");
        } else if (btn.getText().equals("暂停")) {
            gamePause();//将游戏状态标志gameRunning改为FALSE，游戏暂停
            btn.setText("开始");

        }

    }

    //showIcon数组中置为TRUE的位置表示已经正确匹配
    //查看showIcon数组中对应下标的label是否已经匹配
    private boolean getLabelStatus(int i) {
        return showIcon[i];
    }

    class MyMouseListenter1 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (getGameStatus() && !getLabelStatus(0)) {
                JLabel lab0 = (JLabel) e.getSource();
                TimeDelayThread th = new TimeDelayThread(labs[0], fruit[0]);
                th.start();
            }
        }

    }

    class MyMouseListenter2 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (getGameStatus() && !getLabelStatus(1)) {
                JLabel lab1 = (JLabel) e.getSource();
                TimeDelayThread th = new TimeDelayThread(labs[1], fruit[1]);
                th.start();
            }
        }
    }

    class MyMouseListenter3 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (getGameStatus() && !getLabelStatus(2)) {
                JLabel lab2 = (JLabel) e.getSource();
                TimeDelayThread th = new TimeDelayThread(labs[2], fruit[2]);
                th.start();
            }
        }
    }

    class MyMouseListenter4 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (getGameStatus() && !getLabelStatus(3)) {
                JLabel lab3 = (JLabel) e.getSource();
                TimeDelayThread th = new TimeDelayThread(labs[3], fruit[3]);
                th.start();
            }
        }
    }

    class MyMouseListenter5 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            //如果能确定事件源的真实类型，则可以强转
            if (getGameStatus() && !getLabelStatus(4)) {
                JLabel lab4 = (JLabel) e.getSource();
                TimeDelayThread th = new TimeDelayThread(labs[4], fruit[4]);
                th.start();
            }
        }
    }

    class MyMouseListenter6 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (getGameStatus() && !getLabelStatus(5)) {
                JLabel lab5 = (JLabel) e.getSource();
                TimeDelayThread th = new TimeDelayThread(labs[5], fruit[5]);
                th.start();
            }
        }
    }

    class MyMouseListenter7 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            //如果能确定事件源的真实类型，则可以强转
            if (getGameStatus() && !getLabelStatus(6)) {
                JLabel lab6 = (JLabel) e.getSource();
                TimeDelayThread th = new TimeDelayThread(labs[6], fruit[6]);
                th.start();
            }
        }
    }

    class MyMouseListenter8 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (getGameStatus() && !getLabelStatus(7)) {
                JLabel lab7 = (JLabel) e.getSource();
                TimeDelayThread th = new TimeDelayThread(labs[7], fruit[7]);
                th.start();
            }
        }
    }

    class MyMouseListenter9 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (getGameStatus() && !getLabelStatus(8)) {
                JLabel lab8 = (JLabel) e.getSource();
                TimeDelayThread th = new TimeDelayThread(labs[8], fruit[8]);
                th.start();
            }
        }
    }

    class MyMouseListenter10 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (getGameStatus() && !getLabelStatus(9)) {
                JLabel lab9 = (JLabel) e.getSource();
                TimeDelayThread th = new TimeDelayThread(labs[9], fruit[9]);
                th.start();
            }
        }
    }

    class MyMouseListenter11 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (getGameStatus() && !getLabelStatus(10)) {
                JLabel lab10 = (JLabel) e.getSource();
                TimeDelayThread th = new TimeDelayThread(labs[10], fruit[10]);
                th.start();
            }
        }
    }

    class MyMouseListenter12 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (getGameStatus() && !getLabelStatus(11)) {
                JLabel lab11 = (JLabel) e.getSource();
                TimeDelayThread th = new TimeDelayThread(labs[11], fruit[11]);
                th.start();
            }
        }
    }

    class MyMouseListenter13 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (getGameStatus() && !getLabelStatus(12)) {
                JLabel lab12 = (JLabel) e.getSource();
                TimeDelayThread th = new TimeDelayThread(labs[12], fruit[12]);
                th.start();
            }
        }
    }

    class MyMouseListenter14 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (getGameStatus() && !getLabelStatus(13)) {
                JLabel lab13 = (JLabel) e.getSource();
                TimeDelayThread th = new TimeDelayThread(labs[13], fruit[13]);
                th.start();
            }
        }
    }

    class MyMouseListenter15 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (getGameStatus() && !getLabelStatus(14)) {
                JLabel lab14 = (JLabel) e.getSource();
                TimeDelayThread th = new TimeDelayThread(labs[14], fruit[14]);
                th.start();
            }
        }
    }

    class MyMouseListenter16 extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
            if (getGameStatus() && !getLabelStatus(15)) {
                JLabel lab15 = (JLabel) e.getSource();
                TimeDelayThread th = new TimeDelayThread(labs[15], fruit[15]);
                th.start();
            }
        }
    }

}
