package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Prodavac extends AbstractDomainObject {

    private int prodavacID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private Drzava drzava;
    
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public Prodavac() {
    }

    public Prodavac(int prodavacID, String ime, String prezime, String email, String telefon, Drzava drzava) {
        this.prodavacID = prodavacID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.drzava = drzava;
    }

    @Override
    public String nazivTabele() {
        return " Prodavac ";
    }

    @Override
    public String alijas() {
        return " pr ";
    }

    @Override
    public String join() {
        return " JOIN DRZAVA D ON (D.DRZAVAID = PR.DRZAVAID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            
            Drzava d = new Drzava(rs.getInt("DrzavaID"), rs.getString("Naziv"));
            
            Prodavac p = new Prodavac(
                    rs.getInt("ProdavacID"),
                    rs.getString("Ime"),
                    rs.getString("Prezime"),
                    rs.getString("Email"),
                    rs.getString("Telefon"),
                    d
            );
            
            lista.add(p);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Email, Telefon, DrzavaID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', '" + email + "', "
                + "'" + telefon + "', " + drzava.getDrzavaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Email = '" + email + "', Telefon = '" + telefon + "', "
                + "drzavaID = " + drzava.getDrzavaID() + " ";
    }

    @Override
    public String uslov() {
        return " ProdavacID = " + prodavacID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(IME) LIKE '%" + ime + "%' "
                + "AND LOWER(PREZIME) LIKE '%" + prezime + "%' "
                + "AND LOWER(EMAIL) LIKE '%" + email + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY PRODAVACID ASC ";
    }

    public int getProdavacID() {
        return prodavacID;
    }

    public void setProdavacID(int prodavacID) {
        this.prodavacID = prodavacID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }
}
