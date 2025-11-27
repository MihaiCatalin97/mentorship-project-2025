package com.project.mentorship.service.vehicle.api;

import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.vehicle.api.dto.VehicleTypeDto;
import com.project.mentorship.service.vehicle.domain.VehicleType;
import com.project.mentorship.service.vehicle.mapper.VehicleTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles/types")
@RequiredArgsConstructor
public class VehicleTypeController {
	private final BaseService<VehicleType> vehicleTypeService;
	private final VehicleTypeMapper vehicleTypeMapper;

	@PostMapping
	public ResponseEntity<VehicleTypeDto> create(@RequestBody VehicleTypeDto request) {
		VehicleType vehicleType = vehicleTypeMapper.map(request);
        VehicleType createdVehicleType = vehicleTypeService.create(vehicleType);
        VehicleTypeDto response = vehicleTypeMapper.map(createdVehicleType);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
