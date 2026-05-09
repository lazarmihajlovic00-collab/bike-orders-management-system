/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author Mladja
 */
public interface Operation {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int ADD_PRODAVAC = 2;
    public static final int DELETE_PRODAVAC = 3;
    public static final int UPDATE_PRODAVAC = 4;
    public static final int GET_ALL_PRODAVAC = 5;

    public static final int ADD_BICIKL = 6;
    public static final int DELETE_BICIKL = 7;
    public static final int UPDATE_BICIKL = 8;
    public static final int GET_ALL_BICIKL = 9;

    public static final int ADD_NARUDZBENICA = 10;
    public static final int DELETE_NARUDZBENICA = 11;
    public static final int UPDATE_NARUDZBENICA = 12;
    public static final int GET_ALL_NARUDZBENICA = 13;

    public static final int ADD_DRZAVA = 14;
    public static final int DELETE_DRZAVA = 15;
    public static final int UPDATE_DRZAVA = 16;
    public static final int GET_ALL_DRZAVA = 17;
    

}
