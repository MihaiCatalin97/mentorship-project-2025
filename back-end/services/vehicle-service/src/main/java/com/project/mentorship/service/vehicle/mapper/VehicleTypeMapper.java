package com.project.mentorship.service.vehicle.mapper;

import com.project.mentorship.contract.vehicle.model.VehicleTypeDto;
import com.project.mentorship.service.vehicle.domain.VehicleType;
import org.springframework.stereotype.Component;

@Component
public class VehicleTypeMapper {
	public VehicleType map(VehicleTypeDto dto) {
		if (dto == null) {
			return null;
		}

		return VehicleType.builder().id(dto.getId() != null ? dto.getId() : null)
				.name(dto.getName() != null ? dto.getName() : null)
				.hourlyRate(dto.getHourlyRate() != null ? dto.getHourlyRate() : null)
				.capacity(dto.getCapacity() != null ? dto.getCapacity() : null)
				.createdAt(dto.getCreatedAt() != null ? dto.getCreatedAt() : null)
				.updatedAt(dto.getUpdatedAt() != null ? dto.getUpdatedAt() : null).build();
	}

	public VehicleTypeDto map(VehicleType vehicleType) {
		if (vehicleType == null) {
			return null;
		}

		VehicleTypeDto vehicleTypeDto = new VehicleTypeDto();
		vehicleTypeDto.setId(vehicleType.getId());
		vehicleTypeDto.setName(vehicleType.getName());
		vehicleTypeDto.setHourlyRate(vehicleType.getHourlyRate());
		vehicleTypeDto.setCapacity(vehicleType.getCapacity());
		vehicleTypeDto.setCreatedAt(vehicleType.getCreatedAt());
		vehicleTypeDto.setUpdatedAt(vehicleType.getUpdatedAt());

		return vehicleTypeDto;
	}
}
