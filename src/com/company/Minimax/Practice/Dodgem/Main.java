package com.company.Minimax.Practice.Dodgem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        int COUNT = 0;

        // JPanel right=new JPanel();
        //public JFrame frame1 = new JFrame();//tạo mới 1 jframe có tên là frame
        JPanel panelNorth = new JPanel();
        JPanel panelWest = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelCenter = new JPanel();//tạo mới 1 jpanel có tên là pan

        JFrame frame = new JFrame();//tạo mới 1 jframe có tên là frame
        int n = 19, m = 19, num = 0, diem = 0;//khởi tạo các giá trị : kích thước của từng button, điểm, số
        JButton btn[][] = new JButton[n][m];//Tạo mới 1 jbutton kiểu mảng 2 chiều có n,m phần tử
        int pos[][] = new int[m][n]; //Tạo 1 biến kiểu int chứa giá trị tương ứng của phần tử trong mảng 2 chiều

        //ham add

        frame.setResizable(false);

        frame.setLayout(new BorderLayout());

        panelNorth.setBackground(Color.pink);
        panelEast.setBackground(Color.yellow);
        panelSouth.setBackground(Color.red);
        panelWest.setBackground(Color.yellow);

        JLabel lbHeader = new JLabel("GAME CARO");
        JLabel lbTime = new JLabel("Time : ");

        panelNorth.add(lbHeader);
        panelNorth.add(lbTime);

        JLabel lbPlayer1 = new JLabel("Player1");
        JLabel lbImagePlayer1 = new JLabel();
        ImageIcon ImgPlay1 = new ImageIcon("C://Users//DUONGNGUYEN//Pictures/player1.jpg");
        lbImagePlayer1.setIcon(ImgPlay1);
        JLabel lbName1 = new JLabel("Steps : ");
        JLabel lbTime1 = new JLabel("Time Thinhking : ");

        //panelEast.setLocation(2, 2);
        panelEast.add(lbPlayer1);
        panelEast.add(lbImagePlayer1);
        panelEast.add(lbName1);
        panelEast.add(lbTime1);

        //lbName1.setLocation(0,0);
        JLabel lbPlayer2 = new JLabel("Player2");
        JLabel lbImagePlayer2 = new JLabel();
        ImageIcon ImgPlay2 = new ImageIcon("C://Users//DUONGNGUYEN//Pictures/player2.jpg");
        lbImagePlayer2.setIcon(ImgPlay2);
        JLabel lbName2 = new JLabel("Steps : ");
        JLabel lbTime2 = new JLabel("Time Thinhking : ");

        panelWest.add(lbImagePlayer2);
        panelWest.add(lbPlayer2);
        panelWest.add(lbName2);
        panelWest.add(lbTime2);

        JButton btnStart = new JButton("Start");
        JButton btnPause = new JButton("Pause");
        JButton btnPlay = new JButton("Play");
        JButton btnRule = new JButton("Rule");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "Trò Chơi Mới", "Thoát", JOptionPane.INFORMATION_MESSAGE);
                for (int i1 = 0; i1 < n; i1++) {
                    for (int j1 = 0; j1 < m; j1++) {
                        btn[i1][j1].setText("");
                        btn[i1][j1].setBackground(Color.white);
                    }
                }
            }
        });

        btnRule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, "-	Cách chơi: \n"
                        + "Trò chơi được chơi trên bàn cờ có 324 ô, với 18 dòng và 18 cột."
                        + " Người chiến thắng là người tạo được đường thẳng theo chiều dọc"
                        + " hoặc ngang hoặc chéo với chính xác 5 con cờ của mình.", "Thoát", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        panelSouth.add(btnStart);
        panelSouth.add(btnPause);
        panelSouth.add(btnPlay);
        panelSouth.add(btnRule);

        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelEast, BorderLayout.EAST);
        frame.add(panelWest, BorderLayout.WEST);
        frame.add(panelSouth, BorderLayout.SOUTH);
        frame.add(panelCenter); //add jpanel vào 1 jframe
        panelCenter.setLayout(new GridLayout(n, m));//xét giá trị vào jpanel
        for (int i = 0; i < n; i++) {//số hàng của jpanel
            for (int j = 0; j < m; j++) {
                //số cột của jpanel
                pos[i][j] = num;//gán từng giá trị của từng phần tử bằng giá trị số int
                num++;//tăng giá trị số lên lần lượt từng đơn vị
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                btn[i][j] = new JButton();//khởi tạo bộ nhớ cho từng jbutton
//                btn[i][j].addActionListener(this);//khi con trỏ chuột trỏ vào phần tử tương ứng nào
                panelCenter.add(btn[i][j]);//add giá trị vào phần tử trỏ chuột tương ứng
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                btn[i][j].setBackground(Color.white);//Xét màu cho Jbutton tương ứng đang xét
            }
        }
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        panelCenter.setSize(700, 9000);
        panelEast.setSize(50, 9000);
        panelWest.setSize(50, 9000);
        frame.setSize(1500, 700);
    }
}
