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
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Mladja
 */
public class SOGetAllNarudzbenica extends AbstractSO {

    private ArrayList<Narudzbenica> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Narudzbenica)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Narudzbenica!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> narudzbenice = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Narudzbenica>) (ArrayList<?>) narudzbenice;

        for (Narudzbenica trenutnaNarudzbenica : lista) {

            StavkaNarudzbenice stavkaNarudzbenice = new StavkaNarudzbenice();
            stavkaNarudzbenice.setNarudzbenica(trenutnaNarudzbenica);

            ArrayList<StavkaNarudzbenice> stavkeTrenutneNarudzbenice
                    = (ArrayList<StavkaNarudzbenice>) (ArrayList<?>) DBBroker.getInstance().select(stavkaNarudzbenice);

            trenutnaNarudzbenica.setStavkeNarudzbenice(stavkeTrenutneNarudzbenice);
        }

    }

    public ArrayList<Narudzbenica> getLista() {
        return lista;
    }

}
