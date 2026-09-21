package so.login;

import controller.ServerController;
import domain.AbstractDomainObject;
import domain.Dobavljac;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mladja
 */
public class SOLoginTest {
    
    SOLogin so;
    Dobavljac dobavljac;
    
    @Before
    public void setUp() {
        so = new SOLogin();
        dobavljac = new Dobavljac();
        
        dobavljac.setIme("");
        dobavljac.setPrezime("");
        
        ServerController.getInstance().getUlogovaniDobavljaci().clear();
    }
    
    @After
    public void tearDown() {
        so = null;
        dobavljac = null;
    }

    @Test 
    public void testValidateIspravanObjekat() throws Exception {
        dobavljac.setKorisnickoIme("lazar");
        dobavljac.setLozinka("lazar");
        so.validate(dobavljac);
    }
    
    @Test(expected = Exception.class)
    public void testValidateNeispravanObjekat() throws Exception {
        so.validate((AbstractDomainObject) new Object());
    }
    
    @Test(expected = Exception.class)
    public void testDobavljacVecUlogovan() throws Exception {
        ServerController.getInstance().getUlogovaniDobavljaci().clear();
        
        Dobavljac ulogovan = new Dobavljac();
        ulogovan.setKorisnickoIme("lazar");
        ulogovan.setLozinka("lazar");
        ServerController.getInstance().getUlogovaniDobavljaci().add(ulogovan);
        
        dobavljac.setIme("");
        dobavljac.setPrezime("");
        dobavljac.setKorisnickoIme("lazar");
        dobavljac.setLozinka("lazar");
        
        so.templateExecute(dobavljac);
    }
    
    @Test(expected = Exception.class)
    public void testIzvrsiOperacijuUspesno() throws Exception {
        ServerController.getInstance().getUlogovaniDobavljaci().clear();
        
        Dobavljac ulogovan = new Dobavljac();
        ulogovan.setKorisnickoIme("lazar");
        ulogovan.setLozinka("lazar");
        ServerController.getInstance().getUlogovaniDobavljaci().add(ulogovan);
        
        so.templateExecute(dobavljac);
        
        assertNotNull(so.getUlogovani());
        assertEquals("lazar", so.getUlogovani().getKorisnickoIme());
    }
    
    @Test(expected = Exception.class)
    public void testIzvrsiOperacijuNeuspesno() throws Exception {
        dobavljac.setKorisnickoIme("lazar");
        dobavljac.setLozinka("pogresna");
        
        so.templateExecute(dobavljac);
        
        assertNull(so.getUlogovani());
    }
    
    @Test(expected = Exception.class)
    public void testLoginNePostoji() throws Exception {
        dobavljac.setKorisnickoIme("nepoznat");
        dobavljac.setLozinka("123");
        dobavljac.setIme("");
        dobavljac.setPrezime("");
        
        so.templateExecute(dobavljac);
    }
    
    @Test(expected = Exception.class)
    public void testPraznoKorisnickoIme() throws Exception {
        dobavljac.setKorisnickoIme("");
        dobavljac.setLozinka("lazar");
        dobavljac.setIme("");
        dobavljac.setPrezime("");
        
        so.templateExecute(dobavljac);
    }
    
    @Test(expected = Exception.class)
    public void testPraznaLozinka() throws Exception {
        dobavljac.setKorisnickoIme("lazar");
        dobavljac.setLozinka("");
        dobavljac.setIme("");
        dobavljac.setPrezime("");
        
        so.templateExecute(dobavljac);
    }
}
