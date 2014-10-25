/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.anu;

import java.util.Observable;

/**
 *
 * @author Anuradha
 */
public class BloodStockUpdateNotifier extends Observable{
    
    public void notifyUpdateBloodStock(){
        this.setChanged();
        this.notifyObservers(null);
    }
    
}
