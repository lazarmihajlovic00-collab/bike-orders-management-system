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
import java.util.regex.Pattern;
import so.AbstractSO;

/**
 *
 * @author Mladja
 */
public class SOAddProdavac extends AbstractSO {

    private static final Pattern EMAIL_PATTERN
            = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern TELEFON_PATTERN
            = Pattern.compile("^06[0-9]{7}$");

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Prodavac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Prodavac!");
        }

        Prodavac p = (Prodavac) ado;

        if (!EMAIL_PATTERN.matcher(p.getEmail()).matches()) {
            throw new Exception("Email nije u ispravnom formatu!");
        }

        if (!TELEFON_PATTERN.matcher(p.getTelefon()).matches()) {
            throw new Exception("Telefon mora biti u formatu 06XXXXXXX!");
        }

        ArrayList<Prodavac> prodavci = (ArrayList<Prodavac>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Prodavac prodavac : prodavci) {
            if (prodavac.getEmail().equals(p.getEmail())) {
                throw new Exception("Prodavac sa tim emailom vec postoji!");
            }
            if (prodavac.getTelefon().equals(p.getTelefon())) {
                throw new Exception("Prodavac sa tim telefonom vec postoji!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
