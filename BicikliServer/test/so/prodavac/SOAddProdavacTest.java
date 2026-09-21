/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package so.prodavac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Drzava;
import domain.Prodavac;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mladja
 */
public class SOAddProdavacTest {

    private SOAddProdavac so;
    private Prodavac prodavac;

    @Before
    public void setUp() throws SQLException {
        so = new SOAddProdavac();
        prodavac = new Prodavac();
    }

    @After
    public void tearDown() throws SQLException {
        // Brišem test prodavce posle testa
        DBBroker.getInstance().getConnection()
                .createStatement()
                .executeUpdate("DELETE FROM prodavac WHERE email LIKE 'test%'");
        DBBroker.getInstance().getConnection().commit();

        so = null;
        prodavac = null;
    }

    @Test
    public void testPredusloviIspravni() throws Exception {
        prodavac.setIme("Lazar");
        prodavac.setPrezime("Mihajlovic");
        prodavac.setEmail("test@gmail.com");
        prodavac.setTelefon("061234567");
        prodavac.setDrzava(new Drzava(2, "kina"));

        so.validate(prodavac);
    }

    @Test(expected = Exception.class)
    public void testPredusloviNeispravanObjekat() throws Exception {
        so.validate((AbstractDomainObject) new Object());
    }

    @Test(expected = Exception.class)
    public void testPredusloviPogresanEmail() throws Exception {
        prodavac.setIme("Lazar");
        prodavac.setPrezime("Mihajlovic");
        prodavac.setEmail("lazar.com");
        prodavac.setTelefon("061234567");
        prodavac.setDrzava(new Drzava(2, "Kina"));

        so.validate(prodavac);
    }

    @Test(expected = Exception.class)
    public void testPredusloviPogresanTelefon() throws Exception {
        prodavac.setIme("Lazar");
        prodavac.setPrezime("Mihajlovic");
        prodavac.setEmail("test1@test.com");
        prodavac.setTelefon("12345678");
        prodavac.setDrzava(new Drzava(2, "Kina"));

        so.validate(prodavac);
    }

    @Test(expected = Exception.class)
    public void testPredusloviDuplikatEmail() throws Exception {
        prodavac.setIme("Marija");
        prodavac.setPrezime("Ilic");
        prodavac.setEmail("marija@bike.rs");
        prodavac.setTelefon("063555666");
        prodavac.setDrzava(new Drzava(2, "Kina"));

        so.templateExecute(prodavac);
    }

    @Test(expected = Exception.class)
    public void testPredusloviDuplikatTelefon() throws Exception {
        prodavac.setIme("Vladimir");
        prodavac.setPrezime("Nikolic");
        prodavac.setEmail("vladimir@bike.rs");
        prodavac.setTelefon("063777888");
        prodavac.setDrzava(new Drzava(3, "Bosna"));

        so.templateExecute(prodavac);
    }

    @Test
    public void testIzvrsiOperacijuUspesno() throws Exception {
        prodavac.setIme("Kristijan");
        prodavac.setPrezime("Mihajlovic");
        prodavac.setEmail("testuspesno@gmail.com");
        prodavac.setTelefon("061888777");
        prodavac.setDrzava(new Drzava(2, "Kina"));

        so.templateExecute(prodavac);
        assertEquals("testuspesno@gmail.com", prodavac.getEmail());
    }

    @Test(expected = Exception.class)
    public void testIzvrsiOperacijuNeuspesno() throws Exception {
        prodavac.setIme("Kristijan");
        prodavac.setPrezime("Mihajlovic");
        prodavac.setEmail("pogresanemail");
        prodavac.setTelefon("0618887777");
        prodavac.setDrzava(new Drzava(2, "Kina"));

        so.templateExecute(prodavac);
    }
}
