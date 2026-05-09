/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Drzava;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mladja
 */
public class TableModelDrzave extends AbstractTableModel implements Runnable {

    private ArrayList<Drzava> lista;
    private String[] kolone = {"ID", "Naziv"};
    private String parametar = "";
    private Drzava drzava = new Drzava(-1, "");

    public TableModelDrzave() {
        try {
            lista = ClientController.getInstance()
                    .getAllDrzava(new Drzava(-1, ""));
        } catch (Exception ex) {
            Logger.getLogger(TableModelDrzave.class.getName()).log(Level.SEVERE, null, ex);
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
        Drzava d = lista.get(row);

        switch (column) {
            case 0:
                return d.getDrzavaID();
            case 1:
                return d.getNaziv();

            default:
                return null;
        }
    }

    public Drzava getSelectedDrzava(int row) {
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
            Logger.getLogger(TableModelDrzave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {

            drzava.setNaziv(parametar.toLowerCase());

            lista = ClientController.getInstance().getAllDrzava(drzava);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Drzava> getLista() {
        return lista;
    }

}
