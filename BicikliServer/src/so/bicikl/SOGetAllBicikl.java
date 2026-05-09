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
public class SOGetAllBicikl extends AbstractSO {

    private ArrayList<Bicikl> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Bicikl)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Bicikl!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> bicikli = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Bicikl>) (ArrayList<?>) bicikli;
    }

    public ArrayList<Bicikl> getLista() {
        return lista;
    }

}
