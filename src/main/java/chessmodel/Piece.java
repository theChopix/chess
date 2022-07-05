/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessmodel;

import java.util.List;

/**
 *
 * @author kratk
 */
public abstract class Piece {
    protected int[] position;
    protected boolean color;
    protected Model model;

    public Piece(int[] position, boolean color, Model board) {
        this.position = position;
        this.color = color;
        this.model = board;
    }
        
    public abstract List<int[]> getValidMoves();
    public abstract void makeMove(int[] position);
    
    
    public boolean isValidMove(int[] pos, String operation) {
        if (pos[0] >= 0 && pos[0] < 8 && pos[1] >= 0 && pos[1] < 8) {
            switch(operation) {
                case "b":
                    return model.getField()[pos[0]][pos[1]] == null;
                case "t":
                    return model.getField()[pos[0]][pos[1]] != null && 
                            model.getField()[pos[0]][pos[1]].getColor() 
                            != this.color;
                case "bt":
                    return model.getField()[pos[0]][pos[1]] == null ||
                            model.getField()[pos[0]][pos[1]].getColor() !=
                            this.color;
                default:
                    return true;
            }
        } else {
            return false;
        }
    }

    public boolean getColor() {
        return color;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int[] sumPositions(int[] first, int[] second) {
        return new int[]{first[0] + second[0], first[1] + second[1]};
    }
}
