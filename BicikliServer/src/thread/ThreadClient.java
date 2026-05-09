/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Dobavljac;
import domain.Narudzbenica;
import domain.Prodavac;
import domain.Bicikl;
import domain.Drzava;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author Mladja
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();

                Response response = handleRequest(request);

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_NARUDZBENICA:
                    ServerController.getInstance().addNarudzbenica((Narudzbenica) request.getData());
                    break;
                case Operation.ADD_BICIKL:
                    ServerController.getInstance().addBicikl((Bicikl) request.getData());
                    break;
                case Operation.ADD_DRZAVA:
                    ServerController.getInstance().addDrzava((Drzava) request.getData());
                    break;
                case Operation.ADD_PRODAVAC:
                    ServerController.getInstance().addProdavac((Prodavac) request.getData());
                    break;
                case Operation.DELETE_NARUDZBENICA:
                    ServerController.getInstance().deleteNarudzbenica((Narudzbenica) request.getData());
                    break;
                case Operation.DELETE_BICIKL:
                    ServerController.getInstance().deleteBicikl((Bicikl) request.getData());
                    break;
                case Operation.DELETE_DRZAVA:
                    ServerController.getInstance().deleteDrzava((Drzava) request.getData());
                    break;
                case Operation.DELETE_PRODAVAC:
                    ServerController.getInstance().deleteProdavac((Prodavac) request.getData());
                    break;
                case Operation.UPDATE_NARUDZBENICA:
                    ServerController.getInstance().updateNarudzbenica((Narudzbenica) request.getData());
                    break;
                case Operation.UPDATE_BICIKL:
                    ServerController.getInstance().updateBicikl((Bicikl) request.getData());
                    break;
                case Operation.UPDATE_DRZAVA:
                    ServerController.getInstance().updateDrzava((Drzava) request.getData());
                    break;
                case Operation.UPDATE_PRODAVAC:
                    ServerController.getInstance().updateProdavac((Prodavac) request.getData());
                    break;
                case Operation.GET_ALL_DRZAVA:
                    response.setData(ServerController.getInstance().getAllDrzava((Drzava) request.getData()));
                    break;
                case Operation.GET_ALL_NARUDZBENICA:
                    response.setData(ServerController.getInstance().getAllNarudzbenica((Narudzbenica) request.getData()));
                    break;
                case Operation.GET_ALL_PRODAVAC:
                    response.setData(ServerController.getInstance().getAllProdavac((Prodavac) request.getData()));
                    break;
                case Operation.GET_ALL_BICIKL:
                    response.setData(ServerController.getInstance().getAllBicikl((Bicikl) request.getData()));
                    break;
                case Operation.LOGIN:
                    Dobavljac dobavljac = (Dobavljac) request.getData();
                    Dobavljac dob = ServerController.getInstance().login(dobavljac);
                    response.setData(dob);
                    break;
                case Operation.LOGOUT:
                    Dobavljac ulogovani = (Dobavljac) request.getData();
                    ServerController.getInstance().logout(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception ex) {
            response.setResponseStatus(ResponseStatus.Error);
            response.setException(ex);
        }
        return response;
    }

}
