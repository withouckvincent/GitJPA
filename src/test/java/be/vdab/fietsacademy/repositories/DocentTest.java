package be.vdab.fietsacademy.repositories;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import be.vdab.fietsacademy.domain.Docent;
import be.vdab.fietsacademy.domain.Geslacht;

public class DocentTest {
	private final static BigDecimal ORIGINELE_WEDDE = BigDecimal.valueOf(200);
	private Docent docent1;

	@Before
	public void before() {
		docent1 = new Docent("test", "test", ORIGINELE_WEDDE, "test@fietsacademy.be", Geslacht.MAN);
	}

	@Test
	public void opslag() {
		docent1.opslag(BigDecimal.TEN);
		assertEquals(0, BigDecimal.valueOf(220).compareTo(docent1.getWedde()));
	}

	@Test(expected = NullPointerException.class)
	public void opslagMetNullMislukt() {
		docent1.opslag(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void opslagMet0Mislukt() {
		docent1.opslag(BigDecimal.ZERO);
		assertEquals(0, ORIGINELE_WEDDE.compareTo(docent1.getWedde()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void negatieveOpslagMislukt() {
		docent1.opslag(BigDecimal.valueOf(-1));
		assertEquals(0, ORIGINELE_WEDDE.compareTo(docent1.getWedde()));
	}
}
