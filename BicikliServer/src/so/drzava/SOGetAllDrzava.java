/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.drzava;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Drzava;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Mladja
 */
public class SOGetAllDrzava extends AbstractSO {

    private ArrayList<Drzava> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Drzava)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Drzava!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> drzave = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Drzava>) (ArrayList<?>) drzave;
    }

    public ArrayList<Drzava> getLista() {
        return lista;
    }

}
