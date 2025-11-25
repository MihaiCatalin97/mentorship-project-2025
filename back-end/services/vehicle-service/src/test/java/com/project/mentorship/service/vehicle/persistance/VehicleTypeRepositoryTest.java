package com.project.mentorship.service.vehicle.persistance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.project.mentorship.service.vehicle.domain.VehicleType;
import com.project.mentorship.service.vehicle.persistence.VehicleTypeRepository;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehicleTypeRepositoryTest {

	private VehicleTypeRepository vehicleTypeRepository = new VehicleTypeRepository();

	@Test
	void save_shouldAddVehicleType_whenEntityIsValid() {
		// Given
		VehicleType vehicleType = new VehicleType();
		vehicleType.setId(UUID.randomUUID());

		// When
		VehicleType result = vehicleTypeRepository.save(vehicleType);

		// Then
		assertNotNull(result);
		assertEquals(vehicleType, result, "The saved vehicleType should be the same as the input vehicleType");
	}
}
