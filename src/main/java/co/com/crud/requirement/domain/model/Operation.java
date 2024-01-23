package co.com.crud.requirement.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Operation {

    private Integer operationId;

    private Integer requirementId;

    private Double levelAdecuacy;

    private Double evaluatedCharacteristics;

    private Double levelWeightScore;

    private Double maximumScore;

    private Double calculatedWeightAverage;

}
