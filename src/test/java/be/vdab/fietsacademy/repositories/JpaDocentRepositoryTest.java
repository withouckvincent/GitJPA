package be.vdab.fietsacademy.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.fietsacademy.domain.Docent;
import be.vdab.fietsacademy.domain.Geslacht;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Sql("/insertDocent.sql")
@Import(JpaDocentRepository.class)
public class JpaDocentRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private JpaDocentRepository repository;
	private static final String DOCENTEN = "docenten";
	private Docent docent;

	@Before
	public void before() {
		docent = new Docent("test", "test", BigDecimal.TEN, "test@fietsacademy.be", Geslacht.MAN);
	}

	@Test
	public void create() {
		int aantalDocenten = super.countRowsInTable(DOCENTEN);
		repository.create(docent);
		assertEquals(aantalDocenten + 1, super.countRowsInTable("docenten"));
		assertNotEquals(0, docent.getId());
		assertEquals(1, super.countRowsInTableWhere(DOCENTEN, "id=" + docent.getId()));
	}

	private long idVanTestMan() {
		return super.jdbcTemplate.queryForObject("select id from docenten where voornaam = 'testM'", Long.class);
	}

	private long idVanTestVrouw() {
		return super.jdbcTemplate.queryForObject("select id from docenten where voornaam='testV'", Long.class);
	}

	@Test
	public void findById() {
		Docent docent = repository.findById(idVanTestMan()).get();
		assertEquals("testM", docent.getVoornaam());
	}

	@Test
	public void findByOnbestaandeId() {
		assertFalse(repository.findById(-1).isPresent());
	}

	@Test
	public void man() {
		assertEquals(Geslacht.MAN, repository.findById(idVanTestMan()).get().getGeslacht());
	}

	@Test
	public void vrouw() {
		assertEquals(Geslacht.VROUW, repository.findById(idVanTestVrouw()).get().getGeslacht());
	}

}
