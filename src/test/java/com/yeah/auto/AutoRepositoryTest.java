package com.yeah.auto;

import com.yeah.auto.entity.Auto;
import com.yeah.auto.entity.AutoGroup;
import com.yeah.auto.projections.AutoProjection;
import com.yeah.auto.repository.AutoGroupRepository;
import com.yeah.auto.repository.AutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@DataJpaTest
class AutoRepositoryTest {
	@Autowired
	private AutoRepository autoRepository;
	@Autowired
	private AutoGroupRepository autoGroupRepository;
	private UUID autoGroupId;

	@Test
	@Rollback(value = false)
	void contextLoads() {
		assertNotNull(autoRepository);
		Auto auto = new Auto();
		auto.setPlate("123");
		auto.setModel("octavia");
		auto.setMark("skoda");
		autoRepository.save(auto);

	}

//	@Test
//	@Rollback(value = false)
	@PostConstruct
	@Transactional
	void addAutoGroup() {
		AutoGroup autoGroup = new AutoGroup();
		autoGroup.setMark("skoda");

		autoGroupRepository.save(autoGroup);

		System.out.println("group saved");

		{
			Auto auto = new Auto();
			auto.setPlate("123");
			auto.setModel("octavia");
			auto.setMark("skoda");
			autoGroup.addAuto(auto);
			autoRepository.save(auto);
			System.out.println("auto1 added");
		}

		{
			Auto auto = new Auto();
			auto.setPlate("456");
			auto.setModel("a-klasse");
			auto.setMark("mercedes");
			autoGroup.addAuto(auto);
			autoRepository.save(auto);
			System.out.println("auto2 added");
		}

	//	autoGroupRepository.save(autoGroup);
		autoGroupId = autoGroup.getId();
		System.out.println("auto group id saved");
	}

	@Test
	void showSelect() {
		AutoGroup autoGroup = autoGroupRepository.findById(autoGroupId).orElseThrow();
		List<Auto> autos = autoGroup.getAutos();
		System.out.println("getAutos loaded");
		autos.size();
		System.out.println("size requested");
	}

	@Test
	@Rollback(value = false)
	void addAutoGroupInOneSetting() {
		AutoGroup autoGroup = autoGroupRepository.findById(autoGroupId).orElseThrow();
		System.out.println("autoGroup loaded");
		System.out.println("autoGroup.getAutos().size() = " + autoGroup.getAutos().size());
		assertEquals(2, autoGroup.getAutos().size());

	}
}
