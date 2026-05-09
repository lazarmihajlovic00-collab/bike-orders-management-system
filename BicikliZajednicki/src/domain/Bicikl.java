package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Bicikl extends AbstractDomainObject {

    private int biciklID;
    private String naziv;
    private String opis;
    private double cenaPoJedinici;

    @Override
    public String toString() {
        return naziv + " (Cena po jedinici: " + cenaPoJedinici + "din)";
    }

    public Bicikl() {
    }

    public Bicikl(int Bicikl, String naziv, String opis, double cenaPoJedinici) {
        this.biciklID = Bicikl;
        this.naziv = naziv;
        this.opis = opis;
        this.cenaPoJedinici = cenaPoJedinici;
    }

    @Override
    public String nazivTabele() {
        return " Bicikl ";
    }

    @Override
    public String alijas() {
        return " b ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
       
            Bicikl b = new Bicikl(
                    rs.getInt("BiciklID"),
                    rs.getString("Naziv"),
                    rs.getString("Opis"),
                    rs.getDouble("cenaPoJedinici")
            );
            
            lista.add(b);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Naziv, Opis, cenaPoJedinici) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + opis + "', " + cenaPoJedinici + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Opis = '" + opis + "', "
                + "cenaPoJedinici = " + cenaPoJedinici + " ";
    }

    @Override
    public String uslov() {
        return " biciklID = " + biciklID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(NAZIV) LIKE '%" + naziv + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY BICIKLID ASC ";
    }

    public int getBiciklID() {
        return biciklID;
    }

    public void setBiciklID(int biciklID) {
        this.biciklID = biciklID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCenaPoJedinici() {
        return cenaPoJedinici;
    }

    public void setCenaPoJedinici(double cenaPoJedinici) {
        this.cenaPoJedinici = cenaPoJedinici;
    }

}
