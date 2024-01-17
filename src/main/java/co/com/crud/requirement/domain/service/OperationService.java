package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.model.AverageScore;
import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.domain.model.queryresult.ITotalMaxScore;
import co.com.crud.requirement.domain.repository.OperationDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService {

    private final OperationDomainRepository operationsDomainRepository;

    private final CharacteristicService characteristicService;

    @Autowired
    public OperationService(OperationDomainRepository operationsDomainRepository, CharacteristicService characteristicService) {
        this.operationsDomainRepository = operationsDomainRepository;
        this.characteristicService = characteristicService;
    }

    public Operation saveOperation(Operation operation) {
        Operation operationReturn = new Operation();
        operationReturn = characteristicService.allOperations(operation.getOperationId(), operation.getRequirementId());
        return operationsDomainRepository.saveOperation(operationReturn);
    }

    public List<Operation> getAllOperations() {
        return operationsDomainRepository.getAllOperations();
    }

    public ITotalMaxScore countNumberScoreByProjectIdOrTypeRequirement(String typeRequirement, Integer projectId) {
        return operationsDomainRepository.countNumberScoreByProjectIdOrTypeRequirement(typeRequirement, projectId);
    }

    public ITotalMaxScore countAllScoreByProjectIdOrTypeRequirement(String typeRequirement, Integer projectId) {
        return operationsDomainRepository.countAllScoreByProjectIdOrTypeRequirement(typeRequirement, projectId);
    }

    public AverageScore averageScoreByProjectIdOrTypeRequirement(String typeRequirement, Integer projectId) {
        AverageScore averageScore = new AverageScore();
        ITotalMaxScore countNumberScoreByProjectIdOrTypeRequirement = countNumberScoreByProjectIdOrTypeRequirement(typeRequirement, projectId);
        ITotalMaxScore countAllScoreByProjectIdOrTypeRequirement = countAllScoreByProjectIdOrTypeRequirement(typeRequirement, projectId);
        var altoAlto = countNumberScoreByProjectIdOrTypeRequirement.getAltoAlto();
        var altoMedio = countNumberScoreByProjectIdOrTypeRequirement.getAltoMedio();
        var altoBajo = countNumberScoreByProjectIdOrTypeRequirement.getAltoBajo();
        var medioAlto = countNumberScoreByProjectIdOrTypeRequirement.getMedioAlto();
        var medioMedio = countNumberScoreByProjectIdOrTypeRequirement.getMedioMEdio();
        var medioBajo = countNumberScoreByProjectIdOrTypeRequirement.getMedioBajo();
        var bajoAlto = countNumberScoreByProjectIdOrTypeRequirement.getBajoAlto();
        var bajoMedio = countNumberScoreByProjectIdOrTypeRequirement.getBajoMedio();
        var bajoBajo = countNumberScoreByProjectIdOrTypeRequirement.getBajoBajo();

        altoAlto = (countNumberScoreByProjectIdOrTypeRequirement.getAltoAlto() != 0) ? countAllScoreByProjectIdOrTypeRequirement.getAltoAlto() / countNumberScoreByProjectIdOrTypeRequirement.getAltoAlto() : altoAlto;
        altoMedio = (countNumberScoreByProjectIdOrTypeRequirement.getAltoMedio() != 0) ? countAllScoreByProjectIdOrTypeRequirement.getAltoMedio() / countNumberScoreByProjectIdOrTypeRequirement.getAltoMedio() : altoMedio;
        altoBajo = (countNumberScoreByProjectIdOrTypeRequirement.getAltoBajo() != 0) ? countAllScoreByProjectIdOrTypeRequirement.getAltoBajo() / countNumberScoreByProjectIdOrTypeRequirement.getAltoBajo() : altoBajo;
        medioAlto = (countNumberScoreByProjectIdOrTypeRequirement.getMedioAlto() != 0) ? countAllScoreByProjectIdOrTypeRequirement.getMedioAlto() / countNumberScoreByProjectIdOrTypeRequirement.getMedioAlto() : medioAlto;
        medioMedio = (countNumberScoreByProjectIdOrTypeRequirement.getMedioMEdio() != 0) ? countAllScoreByProjectIdOrTypeRequirement.getMedioMEdio() / countNumberScoreByProjectIdOrTypeRequirement.getMedioMEdio() : medioMedio;
        medioBajo = (countNumberScoreByProjectIdOrTypeRequirement.getMedioBajo() != 0) ? countAllScoreByProjectIdOrTypeRequirement.getMedioBajo() / countNumberScoreByProjectIdOrTypeRequirement.getMedioBajo() : medioBajo;
        bajoAlto = (countNumberScoreByProjectIdOrTypeRequirement.getBajoAlto() != 0) ? countAllScoreByProjectIdOrTypeRequirement.getBajoAlto() / countNumberScoreByProjectIdOrTypeRequirement.getBajoAlto() : bajoAlto;
        bajoMedio = (countNumberScoreByProjectIdOrTypeRequirement.getBajoMedio() != 0) ? countAllScoreByProjectIdOrTypeRequirement.getBajoMedio() / countNumberScoreByProjectIdOrTypeRequirement.getBajoMedio() : bajoMedio;
        bajoBajo = (countNumberScoreByProjectIdOrTypeRequirement.getBajoBajo() != 0) ? countAllScoreByProjectIdOrTypeRequirement.getBajoBajo() / countNumberScoreByProjectIdOrTypeRequirement.getBajoBajo() : bajoBajo;

        averageScore.setAltoAlto(altoAlto);
        averageScore.setAltoMedio(altoMedio);
        averageScore.setAltoBajo(altoBajo);
        averageScore.setMedioAlto(medioAlto);
        averageScore.setMedioMedio(medioMedio);
        averageScore.setMedioBajo(medioBajo);
        averageScore.setBajoAlto(bajoAlto);
        averageScore.setBajoMedio(bajoMedio);
        averageScore.setBajoBajo(bajoBajo);

        return averageScore;
    }
}
