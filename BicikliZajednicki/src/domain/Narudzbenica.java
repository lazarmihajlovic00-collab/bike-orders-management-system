package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Narudzbenica extends AbstractDomainObject {

    private int narudzbenicaID;
    private Date datumVremeFormiranja;
    private Date datumNarudzbine;
    private double ukupnaCena;
    private Dobavljac dobavljac;
    private Prodavac prodavac;
    private ArrayList<StavkaNarudzbenice> stavkeNarudzbenice;

    public Narudzbenica() {
    }

    public Narudzbenica(int narudzbenicaID, Date datumVremeFormiranja, Date datumNarudzbine, double ukupnaCena, Dobavljac dobavljac, Prodavac prodavac, ArrayList<StavkaNarudzbenice> stavkeNarudzbenice) {
        this.narudzbenicaID = narudzbenicaID;
        this.datumVremeFormiranja = datumVremeFormiranja;
        this.datumNarudzbine = datumNarudzbine;
        this.ukupnaCena = ukupnaCena;
        this.dobavljac = dobavljac;
        this.prodavac = prodavac;
        this.stavkeNarudzbenice = stavkeNarudzbenice;
    }

    @Override
    public String nazivTabele() {
        return " Narudzbenica ";
    }

    @Override
    public String alijas() {
        return " n ";
    }

    @Override
    public String join() {
        return " JOIN Dobavljac d ON (d.DobavljacID = n.DobavljacID) "
                + " JOIN Prodavac pr ON (pr.ProdavacID = n.ProdavacID) "
                + " JOIN DRZAVA DR ON (DR.DRZAVAID = PR.DRZAVAID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Drzava drz = new Drzava(rs.getInt("DrzavaID"), rs.getString("DR.Naziv"));
            Prodavac pr = new Prodavac(
                    rs.getInt("ProdavacID"),
                    rs.getString("pr.Ime"),
                    rs.getString("pr.Prezime"),
                    rs.getString("Email"),
                    rs.getString("Telefon"),
                    drz
            );
            Dobavljac d = new Dobavljac(
                    rs.getInt("DobavljacID"),
                    rs.getString("d.Ime"),
                    rs.getString("d.Prezime"),
                    rs.getString("KorisnickoIme"),
                    rs.getString("Lozinka")
            );
            Narudzbenica n = new Narudzbenica(
                    rs.getInt("NarudzbenicaID"),
                    rs.getTimestamp("DatumVremeFormiranja"),
                    rs.getDate("DatumNarudzbine"),
                    rs.getDouble("UkupnaCena"),
                    d, pr, new ArrayList<>()
            );
            lista.add(n);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (DatumVremeFormiranja, DatumNarudzbine, UkupnaCena, DobavljacID, ProdavacID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new java.sql.Timestamp(datumVremeFormiranja.getTime()) + "', "
                + "'" + new java.sql.Date(datumNarudzbine.getTime()) + "', "
                + ukupnaCena + ", "
                + dobavljac.getDobavljacID() + ", "
                + prodavac.getProdavacID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " DatumNarudzbine = '" + new java.sql.Date(datumNarudzbine.getTime()) + "', "
                + "UkupnaCena = " + ukupnaCena + " ";
    }

    @Override
    public String uslov() {
        return " NarudzbenicaID = " + narudzbenicaID;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE LOWER(PR.IME) LIKE '%" + prodavac.getIme() + "%' "
                + "AND LOWER(PR.PREZIME) LIKE '%" + prodavac.getPrezime() + "%' ";
    }

    @Override
    public String orderBy() {
        return " ORDER BY NARUDZBENICAID ASC ";
    }

    public int getNarudzbenicaID() {
        return narudzbenicaID;
    }

    public void setNarudzbenicaID(int narudzbenicaID) {
        this.narudzbenicaID = narudzbenicaID;
    }

    public Date getDatumVremeFormiranja() {
        return datumVremeFormiranja;
    }

    public void setDatumVremeFormiranja(Date datumVremeFormiranja) {
        this.datumVremeFormiranja = datumVremeFormiranja;
    }

    public Date getDatumNarudzbine() {
        return datumNarudzbine;
    }

    public void setDatumNarudzbine(Date datumNarudzbine) {
        this.datumNarudzbine = datumNarudzbine;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public ArrayList<StavkaNarudzbenice> getStavkeNarudzbenice() {
        return stavkeNarudzbenice;
    }

    public void setStavkeNarudzbenice(ArrayList<StavkaNarudzbenice> stavkeNarudzbenice) {
        this.stavkeNarudzbenice = (stavkeNarudzbenice != null) ? stavkeNarudzbenice : new ArrayList<>();
    }
}
