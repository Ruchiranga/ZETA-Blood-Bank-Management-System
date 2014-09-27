/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Anu;

/**
 *
 * @author ruchiranga
 */
public class IDGenerator {

    public static String generateNextID(String curid) throws Exception {
        int index = 0;
        int x;
        try {
            for (int i = curid.length() - 1; i >= 0; i--) {
                x = Integer.parseInt(curid.substring(i, curid.length()));
                index = i;
            }
        } catch (Exception e) {
        }
        String prefix = curid.substring(0, index);
        int digitCount =curid.substring(index, curid.length()).length();
        int digit = Integer.parseInt(curid.substring(index, curid.length()));
        digit++;
        String newDigit=Integer.toString(digit);
        String leadingZeros ="";
        if(newDigit.length()>digitCount){
            throw new Exception("Failed to generate ID");
        }else{
            int times = digitCount-newDigit.length();
            for (int i = 0; i < times; i++) {
                leadingZeros+="0";
            }
        }
        return prefix+leadingZeros+newDigit;
//        String newid = null;
//        if (digit < 10) {
//            newid = prefix + "0000" + digit;
//        } else if (digit < 100) {
//            newid = prefix + "0" + digit;
//        } else if (digit < 1000) {
//            newid = prefix + digit;
//        }
//        return newid;
    }

}
