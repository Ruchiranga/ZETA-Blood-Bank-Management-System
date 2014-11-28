/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connection;

import controller.blood_stock_management.BloodStockUpdateNotifier;
import view.users.Nurse;

/**
 *
 * @author Anuradha
 */
public class NotifierConnection {
    private static BloodStockUpdateNotifier notifier = null;

    
    public static BloodStockUpdateNotifier getNotifierConnection(Nurse nurse){
        if (notifier == null) {
            notifier = new BloodStockUpdateNotifier();
            if(nurse != null){
                notifier.addObserver(nurse);
            }
            
        }
        return notifier;
    }
}
