/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessview;

import chesscontroller.Controller;
import chessmodel.Model;
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author kratk
 */
public class ViewImpl extends JFrame implements View{
    private final Model model;
    private final JButton[][] chessBoardSquares = new JButton[8][8];
    private boolean displayed = false;
    private List<int[]> displayedPossibleMoves;
    
    private Icon whitePawnIcon;
    private Icon blackPawnIcon;
    
    public ViewImpl(Model chessboard) {
        this.model = chessboard;
    }
    
    @Override
    public void initializeView() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLayout(new GridLayout(8,8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoardSquares[i][j] = new JButton();
                this.add(chessBoardSquares[i][j]);
                if ((i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0)) {
                    chessBoardSquares[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    chessBoardSquares[i][j].setBackground(Color.DARK_GRAY);
                }
            }
        }
        this.setVisible(true);
        
        getIcons();       
    } 
    
    @Override
    public void setPiece(int[] position, String name) {
        switch(name) {
            case "whitePawn":
                chessBoardSquares[position[0]][position[1]].setIcon(whitePawnIcon);
                break;
            case "blackPawn":
                chessBoardSquares[position[0]][position[1]].setIcon(blackPawnIcon);
        }
    }
    
    @Override
    public void updateView(int[] oldPos, int[] newPos) {
        chessBoardSquares[newPos[0]][newPos[1]].setIcon(chessBoardSquares[oldPos[0]]
        [oldPos[1]].getIcon());
        chessBoardSquares[oldPos[0]][oldPos[1]].setIcon(null);
        chessBoardSquares[newPos[0]][newPos[1]].setBorder
        (BorderFactory.createEmptyBorder());
        deactivateSquare(oldPos);
        
    }

    @Override
    public void activateSquare(int[] position) {
        ChosenPieceHandler pieceMovesHandler = new ChosenPieceHandler(position);
        chessBoardSquares[position[0]][position[1]].
                addActionListener(pieceMovesHandler);
    }
    
    @Override
    public void deactivateSquare(int[] position) {
        for (ActionListener a : chessBoardSquares[position[0]][position[1]].
                getActionListeners()) {
            chessBoardSquares[position[0]][position[1]].removeActionListener(a);
        }
    }
    
    public void displayPossibleMoves(int[] currentPos, List<int[]> positions) {
        if (displayed) {
              hidePossibleMoves();
        }
        for (int[] pos : positions) {
            chessBoardSquares[pos[0]][pos[1]].setBorder(BorderFactory.
                    createLineBorder(Color.blue));
            ChosenMoveHandler chosenMoveHandler = 
                    new ChosenMoveHandler(currentPos, pos);
            chessBoardSquares[pos[0]][pos[1]].addActionListener(chosenMoveHandler);
        }
        displayed = true;
        displayedPossibleMoves = positions;
    }
    
    public void hidePossibleMoves() {
        for (int[] oldPos : displayedPossibleMoves) {
                chessBoardSquares[oldPos[0]][oldPos[1]].setBorder
                    (BorderFactory.createEmptyBorder());
                deactivateSquare(oldPos);
        }
    }
    
    public void getIcons() {
        whitePawnIcon = new ImageIcon(new ImageIcon("piece_icons/whitePawn.png").getImage().
                getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
        blackPawnIcon = new ImageIcon(new ImageIcon("piece_icons/blackPawn.png").getImage().
                getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH));
    }
    
    // ActionListeners--------------------------------------------------  
    
    private class ChosenPieceHandler implements ActionListener {
        int[] position;
        
        public ChosenPieceHandler(int[] position) {
            this.position = position;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            displayPossibleMoves(position, model.getField()[position[0]][position[1]].
                    getValidMoves());
            System.out.println("ChosenPieceHandler");
        }
    }
    
    private class ChosenMoveHandler implements ActionListener {
        int[] oldPosition;
        int[] newPosition;
        
        public ChosenMoveHandler(int[] oldPosition, int[] newPosition) {
            this.oldPosition = oldPosition;
            this.newPosition = newPosition;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            model.getField()[oldPosition[0]][oldPosition[1]].makeMove(newPosition);
            System.out.println("ChosenMoveHandler");
            hidePossibleMoves();
        }
        
    }
}
