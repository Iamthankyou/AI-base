package com.company.Minimax.Practice.Dodgem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
//Khởi tạo 1 lưới các ô vuông là các button

public class Game extends JPanel implements ActionListener {

    static int COUNT = 0;

    JPanel panelNorth = new JPanel();
    JPanel panelWest = new JPanel();
    JPanel panelEast = new JPanel();
    JPanel panelSouth = new JPanel();
    JPanel panelCenter = new JPanel();//tạo mới 1 jpanel có tên là pan

    private Dodgem game;

    public JFrame frame = new JFrame();//tạo mới 1 jframe có tên là frame
    public int n = 3, m = 3, num = 0, diem = 0;//khởi tạo các giá trị : kích thước của từng button, điểm, số
    public JButton btn[][] = new JButton[n][m];//Tạo mới 1 jbutton kiểu mảng 2 chiều có n,m phần tử
    int pos[][] = new int[m][n]; //Tạo 1 biến kiểu int chứa giá trị tương ứng của phần tử trong mảng 2 chiều
    int turn = 1;
    //ham add

    private int player;

    private Dodgem_Minimax AI;

    public Game(){
        add();
    }

    public void add() {
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(400, 400));

        panelNorth.setBackground(Color.pink);
        panelEast.setBackground(Color.yellow);
        panelSouth.setBackground(Color.red);
        panelWest.setBackground(Color.yellow);

        JLabel lbHeader = new JLabel("DODGAME");
        panelNorth.add(lbHeader);

        JButton btnBlack = new JButton("Black");
        JButton btnWhite = new JButton("White");
        JButton btnPlay = new JButton("Play");
        JButton btnRule = new JButton("Rule");

        btnBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player = -1;
                startGame();
            }
        });

        btnWhite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                player = 1;
                startGame();
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

        panelSouth.add(btnBlack);
        panelSouth.add(btnWhite);
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
                btn[i][j].addActionListener(this);//khi con trỏ chuột trỏ vào phần tử tương ứng nào
                panelCenter.add(btn[i][j]);//add giá trị vào phần tử trỏ chuột tương ứng
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                btn[i][j].setBackground(Color.white);//Xét màu cho Jbutton tương ứng đang xét
            }
        }

        game = new Dodgem();
        AI = new Dodgem_Minimax(game);

        refresh();

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        panelCenter.setSize(700, 9000);
        panelEast.setSize(50, 9000);
        panelWest.setSize(50, 9000);
        frame.setSize(400, 400);
        /*frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOS E);*/
    }

    private void startGame() {
        if (player==1){
            game = new Dodgem();
            AI = new Dodgem_Minimax(game);
            AI.setMAX_PLAYER(-1);
            AI.setMIN_PLAYER(1);
            turn =1;

            if (turn ==-1){
                Move best = AI.getBestMove(-1);
                game.doMove(best);
                turn*=-1;

                if (game.isEndGame()!=0){
                    if (game.isEndGame()==1){
                        JOptionPane.showMessageDialog(null, "Trắng thắng: \n");
                        game = new Dodgem();
                        AI = new Dodgem_Minimax(game);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Đen thắng: \n");
                        game = new Dodgem();
                        AI = new Dodgem_Minimax(game);
                    }
                }
            }

            refresh();
        }
        else{
            game = new Dodgem();
            AI = new Dodgem_Minimax(game);
            AI.setMAX_PLAYER(1);
            AI.setMIN_PLAYER(-1);
            turn = 1;

            if (turn ==1){
                Move best = AI.getBestMove(1);
                game.doMove(best);
                turn*=-1;

                if (game.isEndGame()!=0){
                    if (game.isEndGame()==1){
                        JOptionPane.showMessageDialog(null, "Trắng thắng: \n");
                        game = new Dodgem();
                        AI = new Dodgem_Minimax(game);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Đen thắng: \n");
                        game = new Dodgem();
                        AI = new Dodgem_Minimax(game);
                    }
                }
            }

            refresh();
        }
    }
    /**/
//    JLabel lb1 = new JLabel("Tọa độ: ");
//    JLabel lb2 = new JLabel("Tọa độ : ");

    public void refresh(){
        if (game.isEndGame()!=0){
            if (game.isEndGame()==1){
                JOptionPane.showMessageDialog(null, "Trắng thắng: \n");
                game = new Dodgem();
                AI = new Dodgem_Minimax(game);
            }
            else{
                JOptionPane.showMessageDialog(null, "Đen thắng: \n");
                game = new Dodgem();
                AI = new Dodgem_Minimax(game);
            }
        }

        if (player==1){
            if (turn==-1){
                Move best = AI.getBestMove(-1);
                game.doMove(best);
                turn*=-1;

                if (game.isEndGame()!=0){
                    if (game.isEndGame()==1){
                        JOptionPane.showMessageDialog(null, "Trắng thắng: \n");
                        game = new Dodgem();
                        AI = new Dodgem_Minimax(game);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Đen thắng: \n");
                        game = new Dodgem();
                        AI = new Dodgem_Minimax(game);
                    }
                }
            }
        }
        else{
            if (turn==1){
                Move best = AI.getBestMove(1);
                game.doMove(best);
                turn*=-1;

                if (game.isEndGame()!=0){
                    if (game.isEndGame()==1){
                        JOptionPane.showMessageDialog(null, "Trắng thắng: \n");
                        game = new Dodgem();
                        AI = new Dodgem_Minimax(game);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Đen thắng: \n");
                        game = new Dodgem();
                        AI = new Dodgem_Minimax(game);
                    }
                }
            }
        }

//        if (turn ==1){
//            Move best = AI.getBestMove(1);
//            game.doMove(best);
//            turn*=-1;
//
//            if (game.isEndGame()!=0){
//                if (game.isEndGame()==1){
//                    JOptionPane.showMessageDialog(null, "Trắng thắng: \n");
//                    game = new Dodgem();
//                    AI = new Dodgem_Minimax(game);
//                }
//                else{
//                    JOptionPane.showMessageDialog(null, "Đen thắng: \n");
//                    game = new Dodgem();
//                    AI = new Dodgem_Minimax(game);
//                }
//            }
//        }

        for (int i1 = 0; i1 < n; i1++) {
            for (int j1 = 0; j1 < m; j1++) {
                btn[i1][j1].setText("");
                btn[i1][j1].setBackground(Color.white);
                btn[i1][j1].setFont(new Font("Arial", Font.PLAIN, 80));
                if (game.getBoard().getMove()[i1][j1]==-1){
                    btn[i1][j1].setName("-1");
                    btn[i1][j1].setForeground(Color.BLACK);
                    Image img = new ImageIcon("/home/beter/AI-base/src/com/company/Minimax/Practice/Dodgem/black1.png").getImage();
                    img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                    Icon icon = new ImageIcon(img);
                    btn[i1][j1].setIcon(icon);
                }
                else if (game.getBoard().getMove()[i1][j1]==1){
                    btn[i1][j1].setName("1");
                    btn[i1][j1].setForeground(Color.RED);
                    Image img = new ImageIcon("/home/beter/AI-base/src/com/company/Minimax/Practice/Dodgem/white1.png").getImage();
                    img = img.getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH);
                    Icon icon = new ImageIcon(img);
                    btn[i1][j1].setIcon(icon);
                }
                else{
                    btn[i1][j1].setName("0");
                    btn[i1][j1].setIcon(null);
                }
            }
        }

    }

    /**/
    //tim o trong de set text
    public void actionPerformed(ActionEvent e) {
        /**/
        if (COUNT == 0) {
//            panelEast.add(lb1);
//            lb1.setLocation(100, 100);
//            panelWest.add(lb2);
//            lb2.setLocation(100, 100);
        } else {
//            panelEast.remove(lb1);
//            panelWest.remove(lb2);
//            panelEast.add(lb1);
//            lb1.setLocation(100, 100);
//            panelWest.add(lb2);
//            lb2.setLocation(100, 100);
        }
        COUNT = 1;
        /**/

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (e.getSource() == btn[i][j] && (btn[i][j].getName() == "1" || btn[i][j].getName() == "-1")  ) {
                    if (turn==1 && btn[i][j].getName() == "1"){
                        final JPopupMenu menu = new JPopupMenu("Menu");
                        JMenuItem up = new JMenuItem("↑");
                        JMenuItem right = new JMenuItem("→");
                        JMenuItem left = new JMenuItem("←");

                        menu.add(up);
                        menu.add(right);
                        menu.add(left);
                        menu.show(btn[i][j], btn[i][j].getWidth()/3, btn[i][j].getHeight()/5);


                        int[][] map = game.getBoard().getMove();

                        int finalI = i;
                        int finalJ = j;
                        up.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                if ((finalI-1>=0 && map[finalI -1][finalJ]==0)|| finalI ==0 ){
                                    int[][] newMove = map.clone();
                                    for (int x=0; x<newMove.length; x++){
                                        newMove[x] = newMove[x].clone();
                                    }

                                    if (finalI==0){
                                        newMove[finalI][finalJ]=0;
                                    }
                                    else{
                                        newMove[finalI][finalJ]=0;
                                        newMove[finalI-1][finalJ]=1;
                                    }

                                    game.doMove(new Move(newMove));
                                    turn*=-1;
                                    refresh();
                                }
                            }
                        });

                        right.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                if ((finalJ+1<3 ) && map[finalI][finalJ+1]==0){
                                    int[][] newMove = map.clone();
                                    for (int x=0; x<newMove.length; x++){
                                        newMove[x] = newMove[x].clone();
                                    }

                                    newMove[finalI][finalJ]=0;
                                    newMove[finalI][finalJ+1]=1;

                                    game.doMove(new Move(newMove));
                                    turn*=-1;
                                    refresh();
                                }
                            }
                        });

                        left.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                if ((finalJ-1>=0 ) && map[finalI][finalJ-1]==0){
                                    int[][] newMove = map.clone();
                                    for (int x=0; x<newMove.length; x++){
                                        newMove[x] = newMove[x].clone();
                                    }

                                    newMove[finalI][finalJ]=0;
                                    newMove[finalI][finalJ-1]=1;

                                    game.doMove(new Move(newMove));
                                    turn*=-1;
                                    refresh();
                                }
                            }
                        });
                    }

                    if (turn==-1 && btn[i][j].getName() == "-1"){
                        final JPopupMenu menu = new JPopupMenu("Menu");
                        JMenuItem up = new JMenuItem("↑");
                        JMenuItem right = new JMenuItem("→");
                        JMenuItem down = new JMenuItem("↓");

                        menu.add(up);
                        menu.add(right);
                        menu.add(down);
                        menu.show(btn[i][j], btn[i][j].getWidth()/3, btn[i][j].getHeight()/5);

                        int[][] map = game.getBoard().getMove();

                        int finalI = i;
                        int finalJ = j;

                        up.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                if (finalI-1>=0 && map[finalI -1][finalJ]==0 ){
                                    int[][] newMove = map.clone();
                                    for (int x=0; x<newMove.length; x++){
                                        newMove[x] = newMove[x].clone();
                                    }

                                    newMove[finalI][finalJ]=0;
                                    newMove[finalI-1][finalJ]=-1;

                                    game.doMove(new Move(newMove));
                                    turn*=-1;
                                    refresh();
                                }
                            }
                        });

                        right.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                if ((finalJ+1<3 && map[finalI][finalJ+1]==0) || finalJ==2){
                                    int[][] newMove = map.clone();
                                    for (int x=0; x<newMove.length; x++){
                                        newMove[x] = newMove[x].clone();
                                    }

                                    if (finalJ==2){
                                        newMove[finalI][finalJ]=0;
                                    }
                                    else{
                                        newMove[finalI][finalJ]=0;
                                        newMove[finalI][finalJ+1]=-1;
                                    }
                                    game.doMove(new Move(newMove));
                                    turn*=-1;
                                    refresh();
                                }
                            }
                        });

                        down.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                                if (finalI+1>=0 && map[finalI +1][finalJ]==0){
                                    int[][] newMove = map.clone();
                                    for (int x=0; x<newMove.length; x++){
                                        newMove[x] = newMove[x].clone();
                                    }

                                    newMove[finalI][finalJ]=0;
                                    newMove[finalI+1][finalJ]=-1;

                                    game.doMove(new Move(newMove));
                                    turn*=-1;
                                    refresh();
                                }
                            }
                        });
                    }

                }

            }
        }
    }

    //kiem tra thang
    public boolean win(int x, int y, String name) {
        int k, j;
        int d = 0;
        // kt chieu doc
        for (k = -4; k <= 4; k++) {
            if (x + k >= 0 && x + k < n) {
                if (btn[x + k][y].getText() == name) {
                    d++;
                } else if (d < 5) {
                    d = 0;
                }
            }
        }
        if (d >= 5) {
            return true;
        } else {
            d = 0;
        }
        //xet ngang
        for (k = -5; k <= 5; k++) {
            if (y + k >= 0 && y + k < n) {
                if (btn[x][y + k].getText() == name) {
                    d++;
                } else if (d < 5) {
                    d = 0;
                }
            }
        }
        if (d >= 5) {
            return true;
        } else {
            d = 0;
        }
        //cheo
        for (k = -4, j = 4; k <= 4 && j >= -4; k++, j--) {
            if (y + k >= 0 && y + k < n && x + j >= 0 && x + j < m) {
                if (btn[x + j][y + k].getText() == name) {
                    d++;
                } else if (d < 5) {
                    d = 0;
                }
            }
        }
        if (d >= 5) {
            return true;
        } else {
            d = 0;
        }
        for (k = -4; k <= 4; k++) {
            if (y + k >= 0 && y + k < n && x + k >= 0 && x + k < m) {
                if (btn[x + k][y + k].getText() == name) {
                    d++;
                } else if (d < 5) {
                    d = 0;
                }
            }
        }
        if (d >= 5) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Game c = new Game();
    }
}