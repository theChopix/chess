/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chesscontroller;

import chessmodel.Model;
import chessview.View;
import java.util.*;  

/**
 *
 * @author kratk
 */
public class ControllerImpl implements Controller{
    private Model model;
    private View view;
    private boolean isWin = false;

    public ControllerImpl(Model model) {      
        this.model = model;
    }
    
    @Override
    public void setNewGame() {
        model.setNewGame();
        
        model.turn();
            
        
    }  

    @Override
    public void setView(View view) {
        this.view = view;
        // mozna zbytecne
    }
}