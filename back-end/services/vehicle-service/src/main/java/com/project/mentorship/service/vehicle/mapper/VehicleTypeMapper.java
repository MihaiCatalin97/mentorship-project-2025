package com.project.mentorship.service.vehicle.mapper;

import com.project.mentorship.service.vehicle.api.dto.VehicleTypeDto;
import com.project.mentorship.service.vehicle.domain.VehicleStatus;
import com.project.mentorship.service.vehicle.domain.VehicleType;
import org.springframework.stereotype.Component;

@Component
public class VehicleTypeMapper {
	public VehicleType map(VehicleTypeDto dto) {
		if (dto == null) {
			return null;
		}

		return VehicleType.builder().id(dto.id() != null ? dto.id() : null)
				.licensePlate(dto.licensePlate() != null ? dto.licensePlate() : null)
				.brand(dto.brand() != null ? dto.brand() : null).model(dto.model() != null ? dto.model() : null)
				.year(dto.year() != null ? dto.year() : null)
				.status(dto.status() != null ? VehicleStatus.valueOf(dto.status().toString()) : null)
				.location(dto.location() != null ? dto.location() : null)
				.createdAt(dto.createdAt() != null ? dto.createdAt() : null)
				.updatedAt(dto.updatedAt() != null ? dto.updatedAt() : null).build();
	}

	public VehicleTypeDto map(VehicleType vehicleType) {
		if (vehicleType == null) {
			return null;
		}

		return new VehicleTypeDto(vehicleType.getId() != null ? vehicleType.getId() : null,
				vehicleType.getLicensePlate() != null ? vehicleType.getLicensePlate() : null,
				vehicleType.getBrand() != null ? vehicleType.getBrand() : null,
				vehicleType.getModel() != null ? vehicleType.getModel() : null,
				vehicleType.getYear() != null ? vehicleType.getYear() : null,
				vehicleType.getStatus() != null ? VehicleStatus.valueOf(vehicleType.getStatus().toString()) : null,
				vehicleType.getLocation() != null ? vehicleType.getLocation() : null,
				vehicleType.getCreatedAt() != null ? vehicleType.getCreatedAt() : null,
				vehicleType.getUpdatedAt() != null ? vehicleType.getUpdatedAt() : null);
	}
}
