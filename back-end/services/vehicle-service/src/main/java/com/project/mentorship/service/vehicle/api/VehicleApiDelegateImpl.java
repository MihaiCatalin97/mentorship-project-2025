package com.project.mentorship.service.vehicle.api;

import com.project.mentorship.contract.vehicle.api.VehiclesApiDelegate;
import com.project.mentorship.contract.vehicle.model.VehicleTypeDto;
import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.vehicle.domain.VehicleType;
import com.project.mentorship.service.vehicle.mapper.VehicleTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleApiDelegateImpl implements VehiclesApiDelegate {

	private final BaseService<VehicleType> vehicleTypeService;
	private final VehicleTypeMapper vehicleTypeMapper;

	@Override
	public ResponseEntity<VehicleTypeDto> createVehicleType(VehicleTypeDto request) {
		VehicleType vehicleType = vehicleTypeMapper.map(request);
		VehicleType createdVehicleType = vehicleTypeService.create(vehicleType);
		VehicleTypeDto response = vehicleTypeMapper.map(createdVehicleType);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
