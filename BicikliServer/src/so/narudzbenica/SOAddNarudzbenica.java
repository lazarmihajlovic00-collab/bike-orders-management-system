/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.narudzbenica;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Narudzbenica;
import domain.StavkaNarudzbenice;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author Mladja
 */
public class SOAddNarudzbenica extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Narudzbenica)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Narudzbenica!");
        }

        Narudzbenica n = (Narudzbenica) ado;

        if (!n.getDatumNarudzbine().after(new Date())) {
            throw new Exception("Datum isporuke mora biti posle danasnjeg datuma!");
        }

        if (n.getStavkeNarudzbenice().isEmpty()) {
            throw new Exception("Narudzbenica mora imati barem jednu stavku!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        int noviID = tableKeys.getInt(1);

        Narudzbenica novaNarudzbenica = (Narudzbenica) ado;
        novaNarudzbenica.setNarudzbenicaID(noviID);

        for (StavkaNarudzbenice stavkaNarudzbenice : novaNarudzbenica.getStavkeNarudzbenice()) {
            stavkaNarudzbenice.setNarudzbenica(novaNarudzbenica);
            DBBroker.getInstance().insert(stavkaNarudzbenice);
        }
    }

}
