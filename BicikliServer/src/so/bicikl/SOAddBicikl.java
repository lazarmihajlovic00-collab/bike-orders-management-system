/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.bicikl;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Bicikl;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Mladja
 */
public class SOAddBicikl extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Bicikl)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Bicikl!");
        }

        Bicikl b = (Bicikl) ado;

        if (b.getCenaPoJedinici() <= 0) {
            throw new Exception("Cena mora biti veca od 0!");
        }

        ArrayList<Bicikl> bicikli = (ArrayList<Bicikl>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Bicikl bicikl : bicikli) {
            if (bicikl.getNaziv().equals(b.getNaziv())) {
                throw new Exception("Bicikl sa tim nazivom vec postoji!");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
