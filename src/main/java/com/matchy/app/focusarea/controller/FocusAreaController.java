package com.matchy.app.focusarea.controller;

import com.matchy.app.focusarea.controller.dto.CreateFocusAreaRequestBody;
import com.matchy.app.focusarea.controller.dto.FocusAreaResponse;
import com.matchy.app.focusarea.controller.dto.UpdateFocusAreaRequestBody;
import com.matchy.app.focusarea.domain.FocusAreaService;
import com.matchy.app.focusarea.domain.entity.FocusAreaEntity;
import com.matchy.app.focusarea.mapper.FocusAreaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/focus-areas")
public class FocusAreaController {

    private final FocusAreaService focusAreaService;
    private final FocusAreaMapper focusAreaMapper;

    @GetMapping
    public ResponseEntity<List<FocusAreaResponse>> getFocusAreas() {
        List<FocusAreaResponse> focusAreaResponse = focusAreaService.getFocusAreas()
            .stream()
            .map(focusAreaMapper::toResponse)
            .toList();
        return ResponseEntity.ok(focusAreaResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FocusAreaResponse> getFocusArea(@PathVariable("id") UUID id) {
        FocusAreaEntity focusAreaEntity = focusAreaService.getFocusAreaById(id);
        FocusAreaResponse focusAreaResponse = focusAreaMapper.toResponse(focusAreaEntity);
        return ResponseEntity.ok(focusAreaResponse);
    }

    @PostMapping
    public ResponseEntity<UUID> createFocusArea(@RequestBody CreateFocusAreaRequestBody body) {
        UUID newFocusAreaId = focusAreaService.createFocusArea(
            body.name(),
            body.description(),
            body.type(),
            body.weightage(),
            body.relatedFocusAreaIds()
        );
        return ResponseEntity.ok(newFocusAreaId);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UUID> updateFocusAreaById(
        @PathVariable("id") UUID id,
        @RequestBody UpdateFocusAreaRequestBody body
    ) {
        UUID updatedFocusAreaId = focusAreaService.updateFocusArea(
            id,
            body.name(),
            body.description(),
            body.type(),
            body.weightage(),
            body.relatedFocusAreaIds()
        );
        return ResponseEntity.ok(updatedFocusAreaId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void deleteFocusAreaById(@PathVariable("id") UUID id) {
        focusAreaService.deleteFocusAreaById(id);
    }
}
