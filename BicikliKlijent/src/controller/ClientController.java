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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author Mladja
 */
public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Dobavljac login(Dobavljac dobavljac) throws Exception {
        return (Dobavljac) sendRequest(Operation.LOGIN, dobavljac);
    }

    public void logout(Dobavljac ulogovani) throws Exception {
        sendRequest(Operation.LOGOUT, ulogovani);
    }

    public void addProdavac(Prodavac prodavac) throws Exception {
        sendRequest(Operation.ADD_PRODAVAC, prodavac);
    }

    public void addBicikl(Bicikl bicikl) throws Exception {
        sendRequest(Operation.ADD_BICIKL, bicikl);
    }

    public void addDrzava(Drzava drzava) throws Exception {
        sendRequest(Operation.ADD_DRZAVA, drzava);
    }
    
    public void addNarudzbenica(Narudzbenica narudzbenica) throws Exception {
        sendRequest(Operation.ADD_NARUDZBENICA, narudzbenica);
    }

    public void deleteProdavac(Prodavac prodavac) throws Exception {
        sendRequest(Operation.DELETE_PRODAVAC, prodavac);
    }

    public void deleteBicikl(Bicikl bicikl) throws Exception {
        sendRequest(Operation.DELETE_BICIKL, bicikl);
    }

    public void deleteDrzava(Drzava drzava) throws Exception {
        sendRequest(Operation.DELETE_DRZAVA, drzava);
    }
    
    public void deleteNarudzbenica(Narudzbenica narudzbenica) throws Exception {
        sendRequest(Operation.DELETE_NARUDZBENICA, narudzbenica);
    }

    public void updateProdavac(Prodavac prodavac) throws Exception {
        sendRequest(Operation.UPDATE_PRODAVAC, prodavac);
    }

    public void updateBicikl(Bicikl bicikl) throws Exception {
        sendRequest(Operation.UPDATE_BICIKL, bicikl);
    }
    
    public void updateDrzava(Drzava drzava) throws Exception {
        sendRequest(Operation.UPDATE_DRZAVA, drzava);
    }

    public void updateNarudzbenica(Narudzbenica narudzbenica) throws Exception {
        sendRequest(Operation.UPDATE_NARUDZBENICA, narudzbenica);
    }

    public ArrayList<Prodavac> getAllProdavac(Prodavac prodavac) throws Exception {
        return (ArrayList<Prodavac>) sendRequest(Operation.GET_ALL_PRODAVAC, prodavac);
    }

    public ArrayList<Narudzbenica> getAllNarudzbenica(Narudzbenica narudzbenica) throws Exception {
        return (ArrayList<Narudzbenica>) sendRequest(Operation.GET_ALL_NARUDZBENICA, narudzbenica);
    }

    public ArrayList<Drzava> getAllDrzava(Drzava drzava) throws Exception {
        return (ArrayList<Drzava>) sendRequest(Operation.GET_ALL_DRZAVA, drzava);
    }

    public ArrayList<Bicikl> getAllBicikl(Bicikl bicikl) throws Exception {
        return (ArrayList<Bicikl>) sendRequest(Operation.GET_ALL_BICIKL, bicikl);
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }

}
