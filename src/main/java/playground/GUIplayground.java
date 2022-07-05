/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playground;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author kratk
 */
public class GUIplayground extends JFrame{
    private JButton[][] chessBoardSquares = new JButton[8][8];
//    private JButton[][] chessBoardSquares;

    public GUIplayground() {
    }

    public void initializeView() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLayout(new GridLayout(8,8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoardSquares[i][j] = new JButton();
                this.add(chessBoardSquares[i][j]);
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
                    chessBoardSquares[i][j].setBackground(Color.BLACK);
                } else {
                    chessBoardSquares[i][j].setBackground(Color.WHITE);
                }
            }
        }
        this.setVisible(true);
        //
        
        
    }
    public Integer sum(int[] pair) {
        return pair[0] + pair[1];
    }
}
