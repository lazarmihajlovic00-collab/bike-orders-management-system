/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Dobavljac;
import domain.Drzava;
import domain.Narudzbenica;
import domain.Prodavac;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class TableModelNarudzbenice extends AbstractTableModel implements Runnable {

    private ArrayList<Narudzbenica> lista;
    private String[] kolone = {"ID", "Dobavljac", "Prodavac", "Datum isporuke", "Ukupan iznos"};
    private String parametarIme = "";
    private String parametarPrezime = "";
    Narudzbenica narudzbenica = new Narudzbenica(0, null, null, 0,
            new Dobavljac(0, "", "", "", ""),
            new Prodavac(0, "", "", "", "", new Drzava(0, "")), null);

    public TableModelNarudzbenice() {
        try {
            lista = ClientController.getInstance()
                    .getAllNarudzbenica(new Narudzbenica(0, null, null, 0,
                            new Dobavljac(0, "", "", "", ""),
                            new Prodavac(0, "", "", "", "", new Drzava(0, "")), null));
        } catch (Exception ex) {
            Logger.getLogger(TableModelNarudzbenice.class.getName()).log(Level.SEVERE, null, ex);
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
        Narudzbenica n = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        switch (column) {
            case 0:
                return n.getNarudzbenicaID();
            case 1:
                return n.getDobavljac();
            case 2:
                return n.getProdavac();
            case 3:
                return sdf.format(n.getDatumNarudzbine());
            case 4:
                return n.getUkupnaCena() + "din";

            default:
                return null;
        }
    }

    public Narudzbenica getSelectedNarudzbenica(int row) {
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
            Logger.getLogger(TableModelNarudzbenice.class.getName()).log(Level.SEVERE, null, ex);
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

    public void refreshTable() {
        try {

            narudzbenica.getProdavac().setIme(parametarIme.toLowerCase());
            narudzbenica.getProdavac().setPrezime(parametarPrezime.toLowerCase());

            lista = ClientController.getInstance().getAllNarudzbenica(narudzbenica);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Narudzbenica> getLista() {
        return lista;
    }

}
