/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package so.prodavac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Drzava;
import domain.Prodavac;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mladja
 */
public class SODeleteProdavacTest {

    private SODeleteProdavac so;
    private Prodavac prodavac;

    @Before
    public void setUp() throws SQLException {
        so = new SODeleteProdavac();

        // Kreiram test prodavca koji bi postojao u bazi
        prodavac = new Prodavac();

        prodavac.setIme("Test");
        prodavac.setPrezime("Prodavac");
        prodavac.setEmail("test@example.com");
        prodavac.setTelefon("061123456");
        prodavac.setDrzava(new Drzava(1, "Kina"));
    }

    @Test(expected = Exception.class)
    public void testPredusloviProdavacNePostoji() throws Exception {
        prodavac.setProdavacID(-1);
        so.validate(prodavac);
    }

    @Test
    public void testIzvrsiOperacijuUspesno() throws Exception {
        PreparedStatement ps = DBBroker.getInstance().insert(prodavac);
        // Dohvati ID koji je baza generisala
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            int noviID = rs.getInt(1);
            prodavac.setProdavacID(noviID);
        }
        // Sada možemo da brišemo
        so.templateExecute(prodavac);
        // Provera da li je prodavac obrisan
        ArrayList<AbstractDomainObject> lista = DBBroker.getInstance().select(prodavac);
        assertEquals("Prodavac bi trebao biti obrisan iz baze", 0, lista.size());
    }

    @Test(expected = Exception.class)
    public void testIzvrsiOperacijuNeuspesno() throws Exception {
        prodavac.setProdavacID(2);
        so.templateExecute(prodavac);
    }

}
