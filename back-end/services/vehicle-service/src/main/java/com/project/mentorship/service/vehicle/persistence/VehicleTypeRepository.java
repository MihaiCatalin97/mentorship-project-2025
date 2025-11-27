package com.project.mentorship.service.vehicle.persistence;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.service.vehicle.domain.VehicleType;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleTypeRepository implements BaseRepository<VehicleType> {
	private final List<VehicleType> vehicleTypes = new ArrayList<>();

	@Override
	public VehicleType save(VehicleType vehicleType) {
		vehicleTypes.add(vehicleType);
		return vehicleType;
	}
}
