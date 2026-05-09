/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Drzava;
import domain.Prodavac;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mladja
 */
public class TableModelProdavci extends AbstractTableModel implements Runnable {

    private ArrayList<Prodavac> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Email", "Telefon", "Drzava"};
    private String parametarIme = "";
    private String parametarPrezime = "";
    private String parametarEmail = "";
    private Prodavac prodavac = new Prodavac(-1, "", "", "", "",
            new Drzava(-1, ""));

    public TableModelProdavci() {
        try {
            lista = ClientController.getInstance()
                    .getAllProdavac(new Prodavac(-1, "", "", "", "", new Drzava(-1, "")));
        } catch (Exception ex) {
            Logger.getLogger(TableModelProdavci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Prodavac p = lista.get(row);

        switch (column) {
            case 0:
                return p.getProdavacID();
            case 1:
                return p.getIme();
            case 2:
                return p.getPrezime();
            case 3:
                return p.getEmail();
            case 4:
                return p.getTelefon();
            case 5:
                return p.getDrzava();

            default:
                return null;
        }
    }

    public Prodavac getSelectedProdavac(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelProdavci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametarIme(String ime) {
        this.parametarIme = ime;
        refreshTable();
    }

    public void setParametarPrezime(String prezime) {
        this.parametarPrezime = prezime;
        refreshTable();
    }

    public void setParametarEmail(String email) {
        this.parametarEmail = email;
        refreshTable();
    }

    public void refreshTable() {
        try {

            prodavac.setIme(parametarIme.toLowerCase());
            prodavac.setPrezime(parametarPrezime.toLowerCase());
            prodavac.setEmail(parametarEmail.toLowerCase());

            lista = ClientController.getInstance().getAllProdavac(prodavac);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Prodavac> getLista() {
        return lista;
    }

}
