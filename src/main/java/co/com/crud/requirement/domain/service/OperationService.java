package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.model.AverageScore;
import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.domain.model.queryresult.ITotalMaxScore;
import co.com.crud.requirement.domain.repository.OperationDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    public AverageScore calculateMedian(Integer projectId){
        AverageScore averageScore = new AverageScore();
        AverageScore funcional = averageScoreByProjectIdOrTypeRequirement("funcional", projectId);
        AverageScore noFuncional = averageScoreByProjectIdOrTypeRequirement("no funcional", projectId);

        List<Double> altoAlto = new ArrayList<>();
        altoAlto.add(funcional.getAltoAlto());
        altoAlto.add(noFuncional.getAltoAlto());
        List<Double> altoMedio = new ArrayList<>();
        altoMedio.add(funcional.getAltoMedio());
        altoMedio.add(noFuncional.getAltoMedio());
        List<Double> altoBajo = new ArrayList<>();
        altoBajo.add(funcional.getAltoBajo());
        altoBajo.add(noFuncional.getAltoBajo());

        List<Double> medioAlto = new ArrayList<>();
        medioAlto.add(funcional.getMedioAlto());
        medioAlto.add(noFuncional.getMedioAlto());
        List<Double> medioMedio = new ArrayList<>();
        medioMedio.add(funcional.getMedioMedio());
        medioMedio.add(noFuncional.getMedioMedio());
        List<Double> medioBajo = new ArrayList<>();
        medioBajo.add(funcional.getMedioBajo());
        medioBajo.add(noFuncional.getMedioBajo());

        List<Double> bajoAlto = new ArrayList<>();
        bajoAlto.add(funcional.getBajoAlto());
        bajoAlto.add(noFuncional.getBajoAlto());
        List<Double> bajoMedio = new ArrayList<>();
        bajoMedio.add(funcional.getBajoMedio());
        bajoMedio.add(noFuncional.getBajoMedio());
        List<Double> bajoBajo = new ArrayList<>();
        bajoBajo.add(funcional.getBajoBajo());
        bajoBajo.add(noFuncional.getBajoBajo());

        Arrays.sort(altoAlto.toArray());
        Arrays.sort(altoMedio.toArray());
        Arrays.sort(altoBajo.toArray());

        Arrays.sort(medioAlto.toArray());
        Arrays.sort(medioMedio.toArray());
        Arrays.sort(medioBajo.toArray());

        Arrays.sort(bajoAlto.toArray());
        Arrays.sort(bajoMedio.toArray());
        Arrays.sort(bajoBajo.toArray());
        boolean pair = true;
        double medianaAltoAlto, medianaAltoMedio, medianaAltoBajo;
        double medianMedioAlto, medianMedioMedio, medianMedioBajo;
        double medianBajoAlto, medianBajoMedio, medianBajoBajo;
        if (funcional.getAltoAlto() == 0 && funcional.getAltoMedio() == 0 && funcional.getAltoBajo() == 0 && funcional.getMedioAlto() == 0 && funcional.getMedioMedio() == 0 && funcional.getMedioBajo() == 0 && funcional.getBajoAlto() == 0 && funcional.getBajoMedio() == 0 && funcional.getBajoBajo() == 0) {
            pair = false;
            altoAlto.remove(funcional.getAltoAlto());
            altoMedio.remove(funcional.getAltoMedio());
            altoBajo.remove(funcional.getAltoBajo());
            medioAlto.remove(funcional.getMedioAlto());
            medioMedio.remove(funcional.getMedioMedio());
            medioBajo.remove(funcional.getMedioBajo());
            bajoAlto.remove(funcional.getBajoAlto());
            bajoMedio.remove(funcional.getBajoMedio());
            bajoBajo.remove(funcional.getBajoBajo());
        } else if (noFuncional.getAltoAlto() == 0 && noFuncional.getAltoMedio() == 0 && noFuncional.getAltoBajo() == 0 && noFuncional.getMedioAlto() == 0 && noFuncional.getMedioMedio() == 0 && noFuncional.getMedioBajo() == 0 && noFuncional.getBajoAlto() == 0 && noFuncional.getBajoMedio() == 0 && noFuncional.getBajoBajo() == 0) {
            pair = false;
            altoAlto.remove(noFuncional.getAltoAlto());
            altoMedio.remove(noFuncional.getAltoMedio());
            altoBajo.remove(noFuncional.getAltoBajo());
            medioAlto.remove(noFuncional.getMedioAlto());
            medioMedio.remove(noFuncional.getMedioMedio());
            medioBajo.remove(noFuncional.getMedioBajo());
            bajoAlto.remove(noFuncional.getBajoAlto());
            bajoMedio.remove(noFuncional.getBajoMedio());
            bajoBajo.remove(noFuncional.getBajoBajo());
        }
        medianaAltoAlto = calculateMedian(altoAlto, pair);
        medianaAltoMedio = calculateMedian(altoMedio, pair);
        medianaAltoBajo = calculateMedian(altoBajo, pair);

        medianMedioAlto = calculateMedian(medioAlto, pair);
        medianMedioMedio = calculateMedian(medioMedio, pair);
        medianMedioBajo = calculateMedian(medioBajo, pair);

        medianBajoAlto = calculateMedian(bajoAlto, pair);
        medianBajoMedio = calculateMedian(bajoMedio, pair);
        medianBajoBajo = calculateMedian(bajoBajo, pair);

        averageScore.setAltoAlto(medianaAltoAlto);
        averageScore.setAltoMedio(medianaAltoMedio);
        averageScore.setAltoBajo(medianaAltoBajo);

        averageScore.setMedioAlto(medianMedioAlto);
        averageScore.setMedioMedio(medianMedioMedio);
        averageScore.setMedioBajo(medianMedioBajo);

        averageScore.setBajoAlto(medianBajoAlto);
        averageScore.setBajoMedio(medianBajoMedio);
        averageScore.setBajoBajo(medianBajoBajo);
        return averageScore;
    }

    private double calculateMedian(List<Double> list, boolean par) {
        double median;
        if (par) {
            double sumaMedios = list.get(list.size() / 2) + list.get((list.size() / 2) - 1);
            median = sumaMedios / 2;
        } else {
            median = list.get(0);
        }
        return median;
    }
}
