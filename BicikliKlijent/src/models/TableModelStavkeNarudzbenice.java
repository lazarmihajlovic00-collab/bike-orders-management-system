/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domain.StavkaNarudzbenice;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelStavkeNarudzbenice extends AbstractTableModel {

    private ArrayList<StavkaNarudzbenice> lista;
    private String[] kolone = {"Rb", "Bicikl", "Cena po jedinici", "Kolicina", "Iznos"};
    private int rb;

    public TableModelStavkeNarudzbenice() {
        lista = new ArrayList<>();
    }

    public TableModelStavkeNarudzbenice(ArrayList<StavkaNarudzbenice> stavkeNarudzbenice) {
        lista = stavkeNarudzbenice;
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
        StavkaNarudzbenice sn = lista.get(row);

        switch (column) {
            case 0:
                return sn.getRb();
            case 1:
                return sn.getBicikl().getNaziv();
            case 2:
                return sn.getBicikl().getCenaPoJedinici() + "din";
            case 3:
                return sn.getKolicina();
            case 4:
                return sn.getIznos() + "din";

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaNarudzbenice sn) {

        for (StavkaNarudzbenice stavkaNarudzbenice : lista) {
            if (stavkaNarudzbenice.getBicikl().getBiciklID() == sn.getBicikl().getBiciklID()) {
                stavkaNarudzbenice.setIznos(stavkaNarudzbenice.getIznos() + sn.getIznos());
                stavkaNarudzbenice.setKolicina(stavkaNarudzbenice.getKolicina() + sn.getKolicina());
                fireTableDataChanged();
                return;
            }
        }

        rb = lista.size();
        sn.setRb(++rb);
        lista.add(sn);
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        lista.remove(row);

        rb = 0;
        for (StavkaNarudzbenice stavkaNarudzbenice : lista) {
            stavkaNarudzbenice.setRb(++rb);
        }

        fireTableDataChanged();
    }

    public double vratiUkupanIznos() {
        double ukupanIznos = 0;

        for (StavkaNarudzbenice stavkaNarudzbenice : lista) {
            ukupanIznos += stavkaNarudzbenice.getIznos();
        }

        return ukupanIznos;
    }

    public ArrayList<StavkaNarudzbenice> getLista() {
        return lista;
    }

}
