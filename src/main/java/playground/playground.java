/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playground;

import chesscontroller.Controller;
import chesscontroller.ControllerImpl;
import chessmodel.Model;
import chessview.View;
import chessview.ViewImpl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author kratk
 */
public class playground {
    public static void main(String[] args) {
        Model model = new Model();        
        Controller controller = new ControllerImpl(model);
        
        View view = new ViewImpl(model);        
        model.setView(view);        
        
        controller.setNewGame(); 
    }
    
}
