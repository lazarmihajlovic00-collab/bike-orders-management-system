package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipProizvoda extends AbstractDomainObject {

    private int tipProizvodaID;
    private String naziv;
    private String opis;

    public TipProizvoda() {
    }

    public TipProizvoda(int tipProizvodaID, String naziv, String opis) {
        this.tipProizvodaID = tipProizvodaID;
        this.naziv = naziv;
        this.opis = opis;
    }

    @Override
    public String nazivTabele() {
        return " TipProizvoda ";
    }

    @Override
    public String alijas() {
        return " tp ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            TipProizvoda tp = new TipProizvoda(
                    rs.getInt("TipProizvodaID"),
                    rs.getString("Naziv"),
                    rs.getString("Opis")
            );
            lista.add(tp);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Naziv, Opis) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + opis + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Naziv = '" + naziv + "', Opis = '" + opis + "' ";
    }

    @Override
    public String uslov() {
        return " TipProizvodaID = " + tipProizvodaID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(NAZIV) LIKE '%" + naziv + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY TIPPROIZVODAID ASC ";
    }

    public int getTipProizvodaID() {
        return tipProizvodaID;
    }

    public void setTipProizvodaID(int tipProizvodaID) {
        this.tipProizvodaID = tipProizvodaID;
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
}
