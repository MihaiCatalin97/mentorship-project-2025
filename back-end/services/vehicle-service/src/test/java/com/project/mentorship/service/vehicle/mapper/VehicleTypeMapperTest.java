package com.project.mentorship.service.vehicle.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.project.mentorship.service.vehicle.api.dto.VehicleTypeDto;
import com.project.mentorship.service.vehicle.domain.VehicleStatus;
import com.project.mentorship.service.vehicle.domain.VehicleType;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class VehicleTypeMapperTest {

	private final VehicleTypeMapper vehicleTypeMapper = new VehicleTypeMapper();

	@Test
	void map_ShouldMapAllFieldsCorrectly_FromDtoToDomain() {
		// Given
		UUID id = UUID.randomUUID();
		OffsetDateTime now = OffsetDateTime.now();

		VehicleTypeDto dto = new VehicleTypeDto(id, "ABC-123", "Toyota", "Corolla", 2020, VehicleStatus.AVAILABLE,
				now.toString(), now, now);

		// When
		VehicleType result = vehicleTypeMapper.map(dto);

		// Then
		assertNotNull(result);
		assertEquals(id, result.getId());
		assertEquals("ABC-123", result.getLicensePlate());
		assertEquals("Toyota", result.getBrand());
		assertEquals("Corolla", result.getModel());
		assertEquals(2020, result.getYear());
		assertEquals(VehicleStatus.AVAILABLE, result.getStatus());
		assertEquals(now, result.getCreatedAt());
		assertEquals(now, result.getUpdatedAt());
	}

	@Test
	void map_ShouldReturnNull_WhenDtoIsNull() {
		// Given
		VehicleTypeDto dto = null;

		// When
		VehicleType result = vehicleTypeMapper.map(dto);

		// Then
		assertNull(result);
	}

	@Test
	void map_ShouldMapAllFieldsCorrectly_FromDomainToDto() {
		// Given
		UUID id = UUID.randomUUID();

		VehicleType vehicleType = VehicleType.builder().id(id).licensePlate("XYZ-789").brand("Honda").model("Civic")
				.year(2021).status(VehicleStatus.AVAILABLE).createdAt(OffsetDateTime.now())
				.updatedAt(OffsetDateTime.now()).build();
		// When
		VehicleTypeDto dto = vehicleTypeMapper.map(vehicleType);

		// Then
		assertNotNull(dto);
		assertEquals(id, dto.id());
		assertEquals("XYZ-789", dto.licensePlate());
		assertEquals("Honda", dto.brand());
		assertEquals("Civic", dto.model());
		assertEquals(2021, dto.year());
		assertEquals(VehicleStatus.AVAILABLE, dto.status());
	}

	@Test
	void map_ShouldReturnNull_WhenDomainIsNull() {
		// Given
		VehicleType vehicleType = null;

		// When
		VehicleTypeDto dto = vehicleTypeMapper.map(vehicleType);

		// Then
		assertNull(dto);
	}
}
