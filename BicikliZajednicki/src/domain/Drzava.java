package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Drzava extends AbstractDomainObject {

    private int drzavaID;
    private String naziv;
    
    @Override
    public String toString() {
        return naziv;
    }

    public Drzava() {
    }

    public Drzava(int drzavaID, String naziv) {
        this.drzavaID = drzavaID;
        this.naziv = naziv;
    }

    @Override
    public String nazivTabele() {
        return " drzava ";
    }

    @Override
    public String alijas() {
        return " d ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Drzava d = new Drzava(rs.getInt("drzavaID"), rs.getString("Naziv"));
            lista.add(d);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Naziv) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Naziv = '" + naziv + "' ";
    }

    @Override
    public String uslov() {
        return " drzavaID = " + drzavaID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(NAZIV) LIKE '%" + naziv + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY DRZAVAID ASC ";
    }

    public int getDrzavaID() {
        return drzavaID;
    }

    public void setDrzavaID(int drzavaID) {
        this.drzavaID = drzavaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
