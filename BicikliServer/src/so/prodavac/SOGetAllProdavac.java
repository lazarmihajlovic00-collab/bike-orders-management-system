/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.prodavac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Prodavac;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Mladja
 */
public class SOGetAllProdavac extends AbstractSO {

    private ArrayList<Prodavac> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Prodavac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Prodavac!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> prodavci = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Prodavac>) (ArrayList<?>) prodavci;
    }

    public ArrayList<Prodavac> getLista() {
        return lista;
    }

}
