/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.narudzbenica;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Narudzbenica;
import so.AbstractSO;

/**
 *
 * @author Mladja
 */
public class SODeleteNarudzbenica extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Narudzbenica)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Narudzbenica!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }

}
