package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Dobavljac extends AbstractDomainObject {

    private int dobavljacID;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public Dobavljac() {
    }

    public Dobavljac(int dobavljacID, String ime, String prezime, String korisnickoIme, String lozinka) {
        this.dobavljacID = dobavljacID;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    @Override
    public String nazivTabele() {
        return " Dobavljac ";
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
            
            Dobavljac d = new Dobavljac(
                    rs.getInt("DobavljacID"),
                    rs.getString("Ime"),
                    rs.getString("Prezime"),
                    rs.getString("KorisnickoIme"),
                    rs.getString("Lozinka"));
            
            lista.add(d);
            
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, KorisnickoIme, Lozinka) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', '" + korisnickoIme + "', '" + lozinka + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "KorisnickoIme = '" + korisnickoIme + "', Lozinka = '" + lozinka + "' ";
    }

    @Override
    public String uslov() {
        return " DobavljacID = " + dobavljacID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(IME) LIKE '%" + ime + "%' "
                + "AND LOWER(PREZIME) LIKE '%" + prezime + "%' "
                + "AND LOWER(KORISNICKOIME) LIKE '%" + korisnickoIme + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY DOBAVLJACID ASC ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dobavljac)) {
            return false;
        }
        Dobavljac that = (Dobavljac) o;
        return dobavljacID == that.dobavljacID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dobavljacID);
    }

    public int getDobavljacID() {
        return dobavljacID;
    }

    public void setDobavljacID(int dobavljacID) {
        this.dobavljacID = dobavljacID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}
