package com.matchy.app.focusarea.repository.model;

import com.matchy.app.focusarea.domain.entity.FocusAreaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("focus_areas")
public class FocusAreaModel {

    private @Id UUID id;
    private String name;
    private String description;
    private FocusAreaType type;
    private int weightage;
    private List<FocusAreaModel> relatedFocusAreas;
}
