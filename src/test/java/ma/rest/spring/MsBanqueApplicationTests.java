package ma.rest.spring;

import ma.rest.spring.entities.Compte;
import ma.rest.spring.entities.TypeCompte;
import ma.rest.spring.repositories.CompteRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsBanqueApplicationTests {

    @Autowired
    private CompteRepository compteRepository;

    @Test
    public void contextLoads() {
        List<Compte> comptes = compteRepository.findAll();
        Assert.assertNotNull(comptes);
        Assert.assertTrue(comptes.size() >= 3);
    }

    @Test
    public void testCreateAndFindCompte() {
        Compte compte = new Compte(null, 7500.0, new Date(), TypeCompte.COURANT);
        Compte saved = compteRepository.save(compte);
        Assert.assertNotNull(saved.getId());
        Assert.assertEquals(7500.0, saved.getSolde(), 0.001);
    }
}
