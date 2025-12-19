package com.project.mentorship.service.vehicle.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.project.mentorship.contract.vehicle.model.VehicleTypeDto;
import com.project.mentorship.service.vehicle.domain.VehicleType;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class VehicleTypeMapperTest {

	private final VehicleTypeMapper vehicleTypeMapper = new VehicleTypeMapper();

	@Test
	void map_shouldMapAllFieldsCorrectly_whenDtoIsValid() {
		// Given
		UUID id = UUID.randomUUID();
		OffsetDateTime now = OffsetDateTime.now();

		VehicleTypeDto vehicleTypeDto = new VehicleTypeDto("Toyota Corolla 2020", 25.5, 5);
		vehicleTypeDto.setId(id);
		vehicleTypeDto.setCreatedAt(now);
		vehicleTypeDto.setUpdatedAt(now);

		// When
		VehicleType result = vehicleTypeMapper.map(vehicleTypeDto);

		// Then
		assertEquals(id, result.getId());
		assertEquals("Toyota Corolla 2020", result.getName());
		assertEquals(Double.valueOf(25.5), result.getHourlyRate());
		assertEquals(Integer.valueOf(5), result.getCapacity());
		assertEquals(now, result.getCreatedAt());
		assertEquals(now, result.getUpdatedAt());
	}

	@Test
	void map_shouldReturnNull_whenDtoIsNull() {
		// Given
		VehicleTypeDto vehicleTypeDto = null;

		// When
		VehicleType result = vehicleTypeMapper.map(vehicleTypeDto);

		// Then
		assertNull(result);
	}
	@Test
	void map_shouldHandleNullFields_whenDtoHasNullValues() {
		// Given: DTO with some null fields to cover ternary branches
		VehicleTypeDto vehicleTypeDto = new VehicleTypeDto(null, // name
				null, // hourlyRate
				null // capacity
		);

		// When
		VehicleType result = vehicleTypeMapper.map(vehicleTypeDto);

		// Then
		assertNotNull(result);
		assertNull(result.getName());
		assertNull(result.getHourlyRate());
		assertNull(result.getCapacity());
	}

	@Test
	void map_shouldMapAllFieldsCorrectlyFromDomainToDto_whenDomainIsValid() {
		// Given
		UUID id = UUID.randomUUID();

		OffsetDateTime created = OffsetDateTime.now().minusHours(1);
		OffsetDateTime updated = OffsetDateTime.now();

		VehicleType vehicleType = VehicleType.builder().id(id).name("Honda Civic 2021").hourlyRate(30.0).capacity(5)
				.createdAt(created).updatedAt(updated).build();
		// When
		VehicleTypeDto vehicleTypeDto = vehicleTypeMapper.map(vehicleType);

		// Then
		assertNotNull(vehicleTypeDto);
		assertEquals(id, vehicleTypeDto.getId());
		assertEquals("Honda Civic 2021", vehicleTypeDto.getName());
		assertEquals(Double.valueOf(30.0), vehicleTypeDto.getHourlyRate());
		assertEquals(Integer.valueOf(5), vehicleTypeDto.getCapacity());
		assertEquals(created, vehicleTypeDto.getCreatedAt());
		assertEquals(updated, vehicleTypeDto.getUpdatedAt());
	}

	@Test
	void map_shouldReturnNull_whenDomainIsNull() {
		// Given
		VehicleType vehicleType = null;

		// When
		VehicleTypeDto dto = vehicleTypeMapper.map(vehicleType);

		// Then
		assertNull(dto);
	}

	@Test
	void map_shouldHandleNullFields_whenDomainHasNullValues() {
		// Given: domain object with null fields to cover ternary branches
		VehicleType vehicleType = VehicleType.builder().id(null).name(null).hourlyRate(null).capacity(null)
				.createdAt(null).updatedAt(null).build();

		// When
		VehicleTypeDto vehicleTypeDto = vehicleTypeMapper.map(vehicleType);

		// Then
		assertNotNull(vehicleTypeDto);
		assertNull(vehicleTypeDto.getId());
		assertNull(vehicleTypeDto.getName());
		assertNull(vehicleTypeDto.getHourlyRate());
		assertNull(vehicleTypeDto.getCapacity());
		assertNull(vehicleTypeDto.getCreatedAt());
		assertNull(vehicleTypeDto.getUpdatedAt());
	}
}
