package be.vdab.fietsacademy.domain;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "docenten") // kan je weg laten indien de naam van de tabel dezelfde is als de class.
public class Docent implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// databases met sequence --> dit is enkel als voorbeeld.
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="myGenerator") 
	//@SequenceGenerator(name="myGenerator", sequenceName="klantleverancierid") 

	private long id;
	private String voornaam;
	private String familienaam;
	@NumberFormat private BigDecimal wedde;
	private String emailAdres;
	@Enumerated(EnumType.STRING) private Geslacht geslacht;
	
	public Docent() {
		
	}

	public Docent(String voornaam, String familienaam, BigDecimal wedde, String emailAdres, Geslacht geslacht) {
		
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.wedde = wedde;
		this.emailAdres = emailAdres;
		this.geslacht = geslacht;
	}

	public long getId() {
		return id;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public BigDecimal getWedde() {
		return wedde;
	}

	public String getEmailAdres() {
		return emailAdres;
	}

	public Geslacht getGeslacht() {
		return geslacht;
	}
	
	public void opslag(BigDecimal percentage) {
		if (percentage.compareTo(BigDecimal.ZERO) <=0 ) {
		throw new IllegalArgumentException();
		}
		BigDecimal factor =
		BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
		wedde = wedde.multiply(factor, new MathContext(2, RoundingMode.HALF_UP));
		}


}
