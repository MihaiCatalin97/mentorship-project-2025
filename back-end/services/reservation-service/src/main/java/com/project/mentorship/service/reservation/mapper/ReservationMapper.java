package com.project.mentorship.service.reservation.mapper;

import com.project.mentorship.service.reservation.api.dto.ReservationDto;
import com.project.mentorship.service.reservation.domain.Reservation;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class ReservationMapper {

    public Reservation toDomain(ReservationDto dto) {
        if (dto == null) {
            return null;
        }

        return Reservation.builder()
                .id(dto.getId())
                .customerId(dto.getCustomerId())
                .vehicleId(dto.getVehicleId())
                .startTime(dto.getStartTime() != null ? LocalDateTime.parse(dto.getStartTime()) : null)
                .endTime(dto.getEndTime() != null ? LocalDateTime.parse(dto.getEndTime()) : null)
                .status(dto.getStatus())
                .createdAt(dto.getCreatedAt() != null ? LocalDateTime.parse(dto.getCreatedAt()) : null)
                .updatedAt(dto.getUpdatedAt() != null ? LocalDateTime.parse(dto.getUpdatedAt()) : null)
                .build();
    }

    public ReservationDto toDto(Reservation reservation) {
        if (reservation == null) {
            return null;
        }

        return ReservationDto.builder()
                .id(reservation.getId())
                .customerId(reservation.getCustomerId())
                .vehicleId(reservation.getVehicleId())
                .startTime(reservation.getStartTime() != null ? reservation.getStartTime().toString() : null)
                .endTime(reservation.getStartTime() != null ? reservation.getStartTime().toString() : null)
                .status(reservation.getStatus())
                .createdAt(reservation.getCreatedAt() != null ? reservation.getCreatedAt().toString() : null)
                .updatedAt(reservation.getUpdatedAt() != null ? reservation.getUpdatedAt().toString() : null)
                .build();
    }
}
