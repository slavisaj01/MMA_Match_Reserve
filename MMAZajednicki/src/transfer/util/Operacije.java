/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author slavi
 */
public interface Operacije {

    public static final int LOGIN = 0;

    public static final int ADD_BORAC = 1;
    public static final int DELETE_BORAC = 2;
    public static final int UPDATE_BORAC = 3;
    public static final int GET_ALL_BORAC = 4;

    public static final int ADD_MEC = 5;
    public static final int DELETE_MEC = 6;
    public static final int UPDATE_MEC = 7;
    public static final int GET_ALL_MEC = 8;

    public static final int GET_ALL_KARTA = 9;

    public static final int GET_ALL_KATEGORIJA = 10;
    
    public static final int GET_ALL_GRAD = 11;
    
    public static final int GET_ALL_KLIJENT = 12;

}
