/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessmodel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kratk
 */
public class Pawn extends Piece {
    private final String name;
    private boolean firstMove = true;
    private final int[] basicWhiteMove = {1, 0};
    private final int[] basicBlackMove = {-1, 0};
    private final int[][] takeWhiteMove = {{1, 1}, {1, -1}};
    private final int[][] takeBlackMove = {{-1, 1}, {-1, -1}};
    private final int[] firstWhiteMove = {2, 0};
    private final int[] firstBlackMove = {-2, 0};

    public Pawn(int[] position, boolean colour, Model board) {
        super(position, colour, board);
        name = colour ? "whitPawn" : "blackPawn";
    }

    @Override
    public List<int[]> getValidMoves() {
        List<int[]> validMoves = new ArrayList<>();
        if (color) {
            if (isValidMove(sumPositions(position, basicWhiteMove), "b")) {
                validMoves.add(sumPositions(position, basicWhiteMove));
            }
            for (int[] sumPos : takeWhiteMove) {
                if (isValidMove(sumPositions(position, sumPos), "t")) {
                    validMoves.add(sumPositions(position, sumPos));
                }
            }
            if (firstMove) {
                if (isValidMove(sumPositions(position, firstWhiteMove), "b")) {
                    validMoves.add(sumPositions(position, firstWhiteMove));
                }
            }
        } else {
            if (isValidMove(sumPositions(position, basicBlackMove), "b")) {
                validMoves.add(sumPositions(position, basicBlackMove));
            }
            for (int[] sumPos : takeBlackMove) {
                if (isValidMove(sumPositions(position, sumPos), "t")) {
                    validMoves.add(sumPositions(position, sumPos));
                }
            }
            if (firstMove) {
                if (isValidMove(sumPositions(position, firstBlackMove), "b")) {
                    validMoves.add(sumPositions(position, firstBlackMove));
                }
            }
        } 
        return validMoves;
    }    
    
    @Override
    public void makeMove(int[] newPosition) {
        firstMove = false;
        model.movePiece(position, newPosition, color, name);
        this.setPosition(newPosition);
    }
    
}

