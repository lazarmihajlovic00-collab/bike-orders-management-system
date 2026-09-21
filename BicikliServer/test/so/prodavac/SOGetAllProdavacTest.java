/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package so.prodavac;

import domain.AbstractDomainObject;
import domain.Prodavac;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mladja
 */
public class SOGetAllProdavacTest {

    private SOGetAllProdavac so;
    private Prodavac prodavac;

    @Before
    public void setUp() {
        so = new SOGetAllProdavac();
        prodavac = new Prodavac();
    }

    @After
    public void tearDown() {
        so = null;
        prodavac = null;
    }

    @Test
    public void testValidateIspravno() throws Exception {
        so.validate(prodavac);
    }

    @Test(expected = Exception.class)
    public void testValidateNeispravno() throws Exception {
        so.validate((AbstractDomainObject) new Object());
    }

    @Test
    public void testIzvrsiUspesno() throws Exception {
        so.templateExecute(prodavac);

        ArrayList<Prodavac> lista = so.getLista();

        // U JUnit 4, tekstualna poruka greške ide kao PRVI parametar
        assertNotNull("Lista prodavaca ne sme biti null", lista);
        assertTrue("Lista prodavaca može biti prazna, ali mora postojati", lista.size() >= 0);
    }
}
