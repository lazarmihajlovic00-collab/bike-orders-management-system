/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Dobavljac;
import domain.Drzava;
import domain.Narudzbenica;
import domain.Prodavac;
import domain.Bicikl;
import java.util.ArrayList;
import so.bicikl.SOAddBicikl;
import so.bicikl.SODeleteBicikl;
import so.login.SOLogin;
import so.narudzbenica.SOAddNarudzbenica;
import so.narudzbenica.SODeleteNarudzbenica;
import so.narudzbenica.SOGetAllNarudzbenica;
import so.narudzbenica.SOUpdateNarudzbenica;
import so.prodavac.SOAddProdavac;
import so.prodavac.SODeleteProdavac;
import so.prodavac.SOGetAllProdavac;
import so.prodavac.SOUpdateProdavac;
import so.bicikl.SOGetAllBicikl;
import so.bicikl.SOUpdateBicikl;
import so.drzava.SOAddDrzava;
import so.drzava.SODeleteDrzava;
import so.drzava.SOGetAllDrzava;
import so.drzava.SOUpdateDrzava;

/**
 *
 * @author Mladja
 */
public class ServerController {

    private static ServerController instance;
    private ArrayList<Dobavljac> ulogovaniDobavljaci = new ArrayList<>();

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public ArrayList<Dobavljac> getUlogovaniDobavljaci() {
        return ulogovaniDobavljaci;
    }

    public void setUlogovaniDobavljaci(ArrayList<Dobavljac> ulogovaniDobavljaci) {
        this.ulogovaniDobavljaci = ulogovaniDobavljaci;
    }

    public Dobavljac login(Dobavljac dobavljaci) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(dobavljaci);
        return so.getUlogovani();
    }

    public void addProdavac(Prodavac prodavac) throws Exception {
        (new SOAddProdavac()).templateExecute(prodavac);
    }

    public void addBicikl(Bicikl bicikl) throws Exception {
        (new SOAddBicikl()).templateExecute(bicikl);
    }

    public void addDrzava(Drzava drzava) throws Exception {
        (new SOAddDrzava()).templateExecute(drzava);
    }
    
    public void addNarudzbenica(Narudzbenica narudzbenica) throws Exception {
        (new SOAddNarudzbenica()).templateExecute(narudzbenica);
    }

    public void deleteProdavac(Prodavac prodavac) throws Exception {
        (new SODeleteProdavac()).templateExecute(prodavac);
    }

    public void deleteBicikl(Bicikl bicikl) throws Exception {
        (new SODeleteBicikl()).templateExecute(bicikl);
    }

    public void deleteDrzava(Drzava drzava) throws Exception {
        (new SODeleteDrzava()).templateExecute(drzava);
    }
    
    public void deleteNarudzbenica(Narudzbenica narudzbenica) throws Exception {
        (new SODeleteNarudzbenica()).templateExecute(narudzbenica);
    }

    public void updateProdavac(Prodavac prodavac) throws Exception {
        (new SOUpdateProdavac()).templateExecute(prodavac);
    }

    public void updateBicikl(Bicikl bicikl) throws Exception {
        (new SOUpdateBicikl()).templateExecute(bicikl);
    }

     public void updateDrzava(Drzava drzava) throws Exception {
        (new SOUpdateDrzava()).templateExecute(drzava);
    }
    
    public void updateNarudzbenica(Narudzbenica narudzbenica) throws Exception {
        (new SOUpdateNarudzbenica()).templateExecute(narudzbenica);
    }

    public ArrayList<Prodavac> getAllProdavac(Prodavac prodavac) throws Exception {
        SOGetAllProdavac so = new SOGetAllProdavac();
        so.templateExecute(prodavac);
        return so.getLista();
    }

    public ArrayList<Narudzbenica> getAllNarudzbenica(Narudzbenica narudzbenica) throws Exception {
        SOGetAllNarudzbenica so = new SOGetAllNarudzbenica();
        so.templateExecute(narudzbenica);
        return so.getLista();
    }

    public ArrayList<Drzava> getAllDrzava(Drzava drzava) throws Exception {
        SOGetAllDrzava so = new SOGetAllDrzava();
        so.templateExecute(drzava);
        return so.getLista();
    }

    public ArrayList<Bicikl> getAllBicikl(Bicikl bicikl) throws Exception {
        SOGetAllBicikl so = new SOGetAllBicikl();
        so.templateExecute(bicikl);
        return so.getLista();
    }

    public void logout(Dobavljac ulogovani) {
        ulogovaniDobavljaci.remove(ulogovani);
    }

}
