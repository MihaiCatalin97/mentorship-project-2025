package com.project.mentorship.service.vehicle.api;

import com.project.mentorship.service.vehicle.api.dto.VehicleTypeDto;
import com.project.mentorship.service.vehicle.mapper.VehicleTypeMapper;
import com.project.mentorship.service.vehicle.service.VehicleTypeService;
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
	private final VehicleTypeService vehicleTypeService;
	private final VehicleTypeMapper vehicleTypeMapper;

	@PostMapping
	public ResponseEntity<VehicleTypeDto> create(@RequestBody VehicleTypeDto request) {
		var vehicleType = vehicleTypeMapper.map(request);
		var createdVehicleType = vehicleTypeService.create(vehicleType);
		var response = vehicleTypeMapper.map(createdVehicleType);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
