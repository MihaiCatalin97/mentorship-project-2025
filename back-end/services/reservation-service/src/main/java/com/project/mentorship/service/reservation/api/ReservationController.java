package com.project.mentorship.service.reservation.api;

import com.project.mentorship.service.reservation.api.dto.ReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reservations")
@RequiredArgsConstructor
public class ReservationController {

    @PostMapping
    public ResponseEntity<ReservationDto> create(@RequestBody ReservationDto request) {
        return ResponseEntity.ok(request);
    }
}
