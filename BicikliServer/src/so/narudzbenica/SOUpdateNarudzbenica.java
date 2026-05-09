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
import java.util.Date;
import java.util.HashMap;
import so.AbstractSO;

/**
 *
 * @author Mladja
 */
public class SOUpdateNarudzbenica extends AbstractSO {

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
        Narudzbenica n = (Narudzbenica) ado;

        DBBroker.getInstance().update(n);

        ArrayList<StavkaNarudzbenice> stareStavke
                = (ArrayList<StavkaNarudzbenice>) (ArrayList<?>) DBBroker.getInstance()
                        .select(new StavkaNarudzbenice(n, 0, 0, 0, 0, null));

        HashMap<Integer, StavkaNarudzbenice> mapaStarih = new HashMap<>();
        for (StavkaNarudzbenice sn : stareStavke) {
            mapaStarih.put(sn.getRb(), sn);
        }

        HashMap<Integer, StavkaNarudzbenice> mapaNovih = new HashMap<>();
        for (StavkaNarudzbenice nova : n.getStavkeNarudzbenice()) {
            mapaNovih.put(nova.getRb(), nova);
        }

        for (StavkaNarudzbenice stara : stareStavke) {
            if (!mapaNovih.containsKey(stara.getRb())) {
                DBBroker.getInstance().delete(stara);
            }
        }

        for (StavkaNarudzbenice nova : n.getStavkeNarudzbenice()) {
            if (mapaStarih.containsKey(nova.getRb())) {
                DBBroker.getInstance().update(nova);
            }
        }

        for (StavkaNarudzbenice nova : n.getStavkeNarudzbenice()) {
            if (!mapaStarih.containsKey(nova.getRb())) {
                DBBroker.getInstance().insert(nova);
            }
        }

    }

}
