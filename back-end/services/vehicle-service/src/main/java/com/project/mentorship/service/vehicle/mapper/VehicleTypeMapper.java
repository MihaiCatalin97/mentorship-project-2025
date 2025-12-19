package com.project.mentorship.service.vehicle.mapper;

import com.project.mentorship.contract.vehicle.model.VehicleTypeDto;
import com.project.mentorship.service.vehicle.domain.VehicleType;
import org.springframework.stereotype.Component;

@Component
public class VehicleTypeMapper {
	public VehicleType map(VehicleTypeDto vehicleTypeDto) {
		if (vehicleTypeDto == null) {
			return null;
		}

		return VehicleType.builder().id(vehicleTypeDto.getId() != null ? vehicleTypeDto.getId() : null)
				.name(vehicleTypeDto.getName() != null ? vehicleTypeDto.getName() : null)
				.hourlyRate(vehicleTypeDto.getHourlyRate() != null ? vehicleTypeDto.getHourlyRate() : null)
				.capacity(vehicleTypeDto.getCapacity() != null ? vehicleTypeDto.getCapacity() : null)
				.createdAt(vehicleTypeDto.getCreatedAt() != null ? vehicleTypeDto.getCreatedAt() : null)
				.updatedAt(vehicleTypeDto.getUpdatedAt() != null ? vehicleTypeDto.getUpdatedAt() : null).build();
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
