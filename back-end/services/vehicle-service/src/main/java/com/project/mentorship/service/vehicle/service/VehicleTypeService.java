package com.project.mentorship.service.vehicle.service;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.vehicle.domain.VehicleType;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleTypeService implements BaseService<VehicleType> {
	private final BaseRepository<VehicleType> vehicleTypeRepository;

	@Override
	public VehicleType create(VehicleType vehicleType) {
		vehicleType.setId(UUID.randomUUID());
		vehicleType.setCreatedAt(OffsetDateTime.now(java.time.ZoneOffset.UTC));
		vehicleType.setUpdatedAt(null);

		return vehicleTypeRepository.save(vehicleType);
	}
}
