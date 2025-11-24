package com.project.mentorship.service.vehicle.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.mentorship.service.vehicle.domain.VehicleType;
import com.project.mentorship.service.vehicle.persistence.VehicleTypeRepository;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VehicleTypeServiceTest {

	@Mock
	private VehicleTypeRepository vehicleTypeRepository;

	@InjectMocks
	private VehicleTypeService vehicleTypeService;

	private final VehicleType vehicleType = new VehicleType().setId(UUID.randomUUID());

	@Test
	void create_ShouldSaveAndReturnVehicleType() {
		// Given
		when(vehicleTypeRepository.save(any(VehicleType.class))).thenReturn(vehicleType);

		// When
		VehicleType result = vehicleTypeService.create(vehicleType);

		// Then
		assertNotNull(result);
		assertEquals(vehicleType.getId(), result.getId());
		verify(vehicleTypeRepository, times(1)).save(vehicleType);
	}
}
