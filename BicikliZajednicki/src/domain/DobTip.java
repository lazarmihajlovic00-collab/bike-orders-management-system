package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DobTip extends AbstractDomainObject {

    private Dobavljac dobavljac;
    private TipProizvoda tipProizvoda;
    private Date datumNabavke;

    public DobTip() {
    }

    public DobTip(Dobavljac dobavljac, TipProizvoda tipProizvoda, Date datumNabavke) {
        this.dobavljac = dobavljac;
        this.tipProizvoda = tipProizvoda;
        this.datumNabavke = datumNabavke;
    }

    @Override
    public String nazivTabele() {
        return " DobTip ";
    }

    @Override
    public String alijas() {
        return " dt ";
    }

    @Override
    public String join() {
        return " JOIN Dobavljac d ON (d.DobavljacID = dt.DobavljacID) "
                + " JOIN TipProizvoda tp ON (tp.TipProizvodaID = dt.TipProizvodaID) ";
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
                    rs.getString("Lozinka")
            );
            TipProizvoda tp = new TipProizvoda(
                    rs.getInt("TipProizvodaID"),
                    rs.getString("Naziv"),
                    rs.getString("Opis")
            );
            DobTip dt = new DobTip(d, tp, rs.getDate("DatumNabavke"));
            lista.add(dt);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (DobavljacID, TipProizvodaID, DatumNabavke) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return dobavljac.getDobavljacID() + ", " + tipProizvoda.getTipProizvodaID() + ", '"
                + new java.sql.Date(datumNabavke.getTime()) + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " DobavljacID = " + dobavljac.getDobavljacID()
                + " AND TipProizvodaID = " + tipProizvoda.getTipProizvodaID()
                + " AND DatumNabavke = '" + new java.sql.Date(datumNabavke.getTime()) + "' ";
    }

    @Override
    public String dodatniUslov() {
        return "";
    }

    @Override
    public String orderBy() {
        return "";
    }

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }

    public TipProizvoda getTipProizvoda() {
        return tipProizvoda;
    }

    public void setTipProizvoda(TipProizvoda tipProizvoda) {
        this.tipProizvoda = tipProizvoda;
    }

    public Date getDatumNabavke() {
        return datumNabavke;
    }

    public void setDatumNabavke(Date datumNabavke) {
        this.datumNabavke = datumNabavke;
    }
}
