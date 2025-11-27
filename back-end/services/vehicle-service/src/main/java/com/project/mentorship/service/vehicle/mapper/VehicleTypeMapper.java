package com.project.mentorship.service.vehicle.mapper;

import com.project.mentorship.service.vehicle.api.dto.VehicleTypeDto;
import com.project.mentorship.service.vehicle.domain.VehicleType;
import org.springframework.stereotype.Component;

@Component
public class VehicleTypeMapper {
	public VehicleType map(VehicleTypeDto dto) {
		if (dto == null) {
			return null;
		}

		return VehicleType.builder().id(dto.id() != null ? dto.id() : null)
                .name(dto.name() != null ? dto.name() : null)
                .hourlyRate(dto.hourlyRate() != null ? dto.hourlyRate() : null)
                .capacity(dto.capacity() != null ? dto.capacity() : null)
				.createdAt(dto.createdAt() != null ? dto.createdAt() : null)
				.updatedAt(dto.updatedAt() != null ? dto.updatedAt() : null).build();
	}

	public VehicleTypeDto map(VehicleType vehicleType) {
		if (vehicleType == null) {
			return null;
		}

		return new VehicleTypeDto(vehicleType.getId() != null ? vehicleType.getId() : null,
				vehicleType.getName() != null ? vehicleType.getName() : null,
                vehicleType.getHourlyRate() != null ? vehicleType.getHourlyRate() : null,
                vehicleType.getCapacity() != null ? vehicleType.getCapacity() : null,
				vehicleType.getCreatedAt() != null ? vehicleType.getCreatedAt() : null,
				vehicleType.getUpdatedAt() != null ? vehicleType.getUpdatedAt() : null);
	}
}
