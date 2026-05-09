/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Bicikl;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mladja
 */
public class TableModelBicikli extends AbstractTableModel implements Runnable {

    private ArrayList<Bicikl> lista;
    private String[] kolone = {"ID", "Naziv", "Cena po jedinici"};
    private String parametar = "";
    private Bicikl bicikl = new Bicikl(-1, "", "", 0);

    public TableModelBicikli() {
        try {
            lista = ClientController.getInstance()
                    .getAllBicikl(new Bicikl(-1, "", "", 0));
        } catch (Exception ex) {
            Logger.getLogger(TableModelBicikli.class.getName()).log(Level.SEVERE, null, ex);
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
        Bicikl b = lista.get(row);

        switch (column) {
            case 0:
                return b.getBiciklID();
            case 1:
                return b.getNaziv();
            case 2:
                return b.getCenaPoJedinici() + "din";

            default:
                return null;
        }
    }

    public Bicikl getSelectedBicikl(int row) {
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
            Logger.getLogger(TableModelBicikli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {

            bicikl.setNaziv(parametar.toLowerCase());

            lista = ClientController.getInstance().getAllBicikl(bicikl);
            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Bicikl> getLista() {
        return lista;
    }

}
