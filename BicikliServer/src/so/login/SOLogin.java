/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.login;

import controller.ServerController;
import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Dobavljac;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Mladja
 */
public class SOLogin extends AbstractSO {

    Dobavljac ulogovani;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Dobavljac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Dobavljac!");
        }

        Dobavljac d = (Dobavljac) ado;

        for (Dobavljac dobavljac : ServerController.getInstance().getUlogovaniDobavljaci()) {
            if (dobavljac.getKorisnickoIme().equals(d.getKorisnickoIme())) {
                throw new Exception("Ovaj dobavljac je vec ulogovan na sistem!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Dobavljac d = (Dobavljac) ado;

        ArrayList<Dobavljac> dobavljaci
                = (ArrayList<Dobavljac>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Dobavljac dobavljac : dobavljaci) {
            if (dobavljac.getKorisnickoIme().equals(d.getKorisnickoIme())
                    && dobavljac.getLozinka().equals(d.getLozinka())) {
                ulogovani = dobavljac;
                ServerController.getInstance().getUlogovaniDobavljaci().add(dobavljac);
                return;
            }
        }

        throw new Exception("Ne postoji dobavljac sa tim kredencijalima.");

    }

    public Dobavljac getUlogovani() {
        return ulogovani;
    }

}
