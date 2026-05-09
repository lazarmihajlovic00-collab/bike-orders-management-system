package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StavkaNarudzbenice extends AbstractDomainObject {

    private Narudzbenica narudzbenica;
    private int rb;
    private int kolicina;
    private double cenaPoJedinici;
    private double iznos;
    private Bicikl bicikl;

    public StavkaNarudzbenice() {
    }

    public StavkaNarudzbenice(Narudzbenica narudzbenica, int rb, int kolicina, double cenaPoJedinici, double iznos, Bicikl bicikl) {
        this.narudzbenica = narudzbenica;
        this.rb = rb;
        this.kolicina = kolicina;
        this.cenaPoJedinici = cenaPoJedinici;
        this.iznos = iznos;
        this.bicikl = bicikl;
    }

    @Override
    public String nazivTabele() {
        return " StavkaNarudzbenice ";
    }

    @Override
    public String alijas() {
        return " sn ";
    }

    @Override
    public String join() {
        return " JOIN Narudzbenica n ON (n.NarudzbenicaID = sn.NarudzbenicaID) "
                + " JOIN BICIKL B ON (B.biciklID = sn.biciklID) "
                + " JOIN Dobavljac d ON (d.DobavljacID = n.DobavljacID) "
                + " JOIN Prodavac pr ON (pr.ProdavacID = n.ProdavacID) "
                + " JOIN Drzava dr ON (dr.DrzavaID = pr.DrzavaID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Drzava drz = new Drzava(rs.getInt("DrzavaID"), rs.getString("dr.Naziv"));
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
            Bicikl b = new Bicikl(
                    rs.getInt("biciklID"),
                    rs.getString("b.Naziv"),
                    rs.getString("Opis"),
                    rs.getDouble("b.cenaPoJedinici")
            );
            StavkaNarudzbenice sn = new StavkaNarudzbenice(
                    n,
                    rs.getInt("Rb"),
                    rs.getInt("Kolicina"),
                    rs.getDouble("sn.cenaPoJedinici"),
                    rs.getDouble("Iznos"),
                    b
            );
            lista.add(sn);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (NarudzbenicaID, Rb, Kolicina, cenaPoJedinici, Iznos, biciklID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return narudzbenica.getNarudzbenicaID() + ", " + rb + ", "
                + kolicina + ", " + cenaPoJedinici + ", " + iznos + ", " + bicikl.getBiciklID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " kolicina = " + kolicina
                + ", cenaPoJedinici = " + cenaPoJedinici
                + ", iznos = " + iznos
                + ", biciklID = " + bicikl.getBiciklID();
    }

    @Override
    public String uslov() {
        return " narudzbenicaID = " + narudzbenica.getNarudzbenicaID() + " AND rb = " + rb;
    }

    @Override
    public String dodatniUslov() {
        return " WHERE N.NARUDZBENICAID = " + narudzbenica.getNarudzbenicaID();
    }

    @Override
    public String orderBy() {
        return " ORDER BY RB ASC ";
    }

    public Narudzbenica getNarudzbenica() {
        return narudzbenica;
    }

    public void setNarudzbenica(Narudzbenica narudzbenica) {
        this.narudzbenica = narudzbenica;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Bicikl getBicikl() {
        return bicikl;
    }

    public void setBicikl(Bicikl bicikl) {
        this.bicikl = bicikl;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCenaPoJedinici() {
        return cenaPoJedinici;
    }

    public void setCenaPoJedinici(double cenaPoJedinici) {
        this.cenaPoJedinici = cenaPoJedinici;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }
}
