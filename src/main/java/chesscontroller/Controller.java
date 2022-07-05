/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesscontroller;

import chessview.View;
import chessmodel.Model;
/**
 *
 * @author kratk
 */
public interface Controller {
    void setNewGame();
    void setView(View view);
    //tlacitka set New Game, Options...
}
