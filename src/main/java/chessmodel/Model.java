/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessmodel;

import java.util.List;
import chessview.View;
import java.util.ArrayList;

/**
 *
 * @author kratk
 */
public class Model {
    private final Piece[][] field = new Piece[8][8];
    private PieceSet whiteSet;
    private PieceSet blackSet;
    private View view;
    private boolean turn = true;    
    
    public void setView(View view) {
        this.view = view;   
        view.initializeView();
    }
    
    public Piece[][] getField(){
        return field;
    }
    
    public void setNewGame() {
        for (int i = 0; i < 8; i++) {
            for (int k = 0; i < 8; i++) {
                field[i][k] = null;
            }
        }        
        whiteSet = new PieceSet(true, this);
        blackSet = new PieceSet(false, this);
        whiteSet.standardLayout();
        blackSet.standardLayout();        
    }
    
    public void turn() {
        if (turn) {
            whiteSet.activate();
            blackSet.deactivate();
            turn = !turn;
        } else {
            blackSet.activate();
            whiteSet.deactivate();
            turn = !turn;
        }  
    }
    
    public void movePiece(int[] oldPos, int[] newPos, boolean color, 
            String name) {
        field[newPos[0]][newPos[1]] = field[oldPos[0]][oldPos[1]];
        field[oldPos[0]][oldPos[1]] = null;
        if (color) {
            whiteSet.updateSetPositions(oldPos, newPos);
            blackSet.cleanPosition(newPos);
        } else {
            blackSet.updateSetPositions(oldPos, newPos);
            whiteSet.cleanPosition(newPos);
        }
        view.updateView(oldPos, newPos);

        turn();
    }
    
    //---------------------------------------------------
    
    public class PieceSet {
        private final boolean color;
        private final Model model;
        List<int[]> setPositions = new ArrayList<>();
        
        public PieceSet(boolean color, Model chessboard) {
            this.color = color;
            this.model = chessboard;
        }
        
        public void standardLayout() {
            for (int i = 0; i < 8; i++) {
                Integer row = color ? 1 : 6;
                field[row][i] = new Pawn(new int[]{row,i}, color, model);
                
                String c = color ? "white" : "black";
                view.setPiece(new int[]{row,i}, c + "Pawn");
                setPositions.add(new int[]{row,i});
            }   
        } 
        
        public void updateSetPositions(int[] oldPos, int[] newPos) {
            for (int[] pos : setPositions) {
                if (pos[0] == oldPos[0] && pos[1] == oldPos[1]) {
                    setPositions.remove(pos);
                    break;
                }
            }
            setPositions.add(newPos);
        }
        
        public void activate() {
            for (int[] position : setPositions) {
                view.activateSquare(position);
            }
        }
        
        public void deactivate() {
            for (int[] position : setPositions) {
                view.deactivateSquare(position);
            }
        }

        public void cleanPosition(int[] position) {
            for (int[] pos : setPositions) {
                if (pos[0] == position[0] && pos[1] == position[1]) {
                    setPositions.remove(pos);
                    break;
                }
            }
        }
    }  
}
