/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessview;

/**
 *
 * @author kratk
 */
public interface View {
    void initializeView();
    void setPiece(int[] position, String name);
    void updateView(int[] oldPos, int[] newPos);
    void activateSquare(int[] position);
    void deactivateSquare(int[] position);
}
