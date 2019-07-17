package be.vdab.fietsacademy.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.fietsacademy.domain.Docent;
import be.vdab.fietsacademy.exceptions.DocentNietGevondenException;
import be.vdab.fietsacademy.repositories.DocentRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
class DefaultDocentService implements DocentService {
	private final DocentRepository docentRepository;

	DefaultDocentService(DocentRepository docentRepository) {
		this.docentRepository = docentRepository;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void opslag(long id, BigDecimal percentage) {
		Optional<Docent> optionalDocent = docentRepository.findById(id);
		if (optionalDocent.isPresent()) {
			optionalDocent.get().opslag(percentage);
		} else {
			throw new DocentNietGevondenException();
		}
	}
}
