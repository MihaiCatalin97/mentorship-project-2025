package com.project.mentorship.service.vehicle.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.project.mentorship.service.vehicle.api.dto.VehicleTypeDto;
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

		VehicleTypeDto dto = new VehicleTypeDto(id,
                "Toyota Corolla 2020",
                25.5,
                5,
                now,
                now);

		// When
		VehicleType result = vehicleTypeMapper.map(dto);

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
		VehicleTypeDto dto = null;

		// When
		VehicleType result = vehicleTypeMapper.map(dto);

		// Then
		assertNull(result);
	}
    @Test
    void map_shouldHandleNullFields_whenDtoHasNullValues() {
        // Given: DTO with some null fields to cover ternary branches
        VehicleTypeDto dto = new VehicleTypeDto(
                null,         // id
                null,         // name
                null,         // hourlyRate
                null,         // capacity
                null,         // createdAt
                null          // updatedAt
        );

        // When
        VehicleType result = vehicleTypeMapper.map(dto);

        // Then
        assertNotNull(result);
        assertNull(result.getId());
        assertNull(result.getName());
        assertNull(result.getHourlyRate());
        assertNull(result.getCapacity());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
    }


	@Test
	void map_shouldMapAllFieldsCorrectlyFromDomainToDto_whenDomainIsValid() {
		// Given
		UUID id = UUID.randomUUID();

        OffsetDateTime created = OffsetDateTime.now().minusHours(1);
        OffsetDateTime updated = OffsetDateTime.now();

        VehicleType vehicleType = VehicleType.builder()
                .id(id)
                .name("Honda Civic 2021")
                .hourlyRate(30.0)
                .capacity(5)
                .createdAt(created)
                .updatedAt(updated)
                .build();
		// When
		VehicleTypeDto dto = vehicleTypeMapper.map(vehicleType);

		// Then
        assertNotNull(dto);
        assertEquals(id, dto.id());
        assertEquals("Honda Civic 2021", dto.name());
        assertEquals(Double.valueOf(30.0), dto.hourlyRate());
        assertEquals(Integer.valueOf(5), dto.capacity());
        assertEquals(created, dto.createdAt());
        assertEquals(updated, dto.updatedAt());
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
        VehicleType vehicleType = VehicleType.builder()
                .id(null)
                .name(null)
                .hourlyRate(null)
                .capacity(null)
                .createdAt(null)
                .updatedAt(null)
                .build();

        // When
        VehicleTypeDto dto = vehicleTypeMapper.map(vehicleType);

        // Then
        assertNotNull(dto);
        assertNull(dto.id());
        assertNull(dto.name());
        assertNull(dto.hourlyRate());
        assertNull(dto.capacity());
        assertNull(dto.createdAt());
        assertNull(dto.updatedAt());
    }
}
