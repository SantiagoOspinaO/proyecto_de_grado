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

import static co.com.crud.requirement.web.constants.Constants.FUNCIONAL_TYPE;
import static co.com.crud.requirement.web.constants.Constants.NO_FUNCIONAL_TYPE;

@Service
public class OperationService {

    private final OperationDomainRepository operationsDomainRepository;

    private final CharacteristicService characteristicService;

    private static final int ZERO_SCORE = 0;


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

        altoAlto = (countNumberScoreByProjectIdOrTypeRequirement.getAltoAlto() != ZERO_SCORE) ? countAllScoreByProjectIdOrTypeRequirement.getAltoAlto() / countNumberScoreByProjectIdOrTypeRequirement.getAltoAlto() : altoAlto;
        altoMedio = (countNumberScoreByProjectIdOrTypeRequirement.getAltoMedio() != ZERO_SCORE) ? countAllScoreByProjectIdOrTypeRequirement.getAltoMedio() / countNumberScoreByProjectIdOrTypeRequirement.getAltoMedio() : altoMedio;
        altoBajo = (countNumberScoreByProjectIdOrTypeRequirement.getAltoBajo() != ZERO_SCORE) ? countAllScoreByProjectIdOrTypeRequirement.getAltoBajo() / countNumberScoreByProjectIdOrTypeRequirement.getAltoBajo() : altoBajo;
        medioAlto = (countNumberScoreByProjectIdOrTypeRequirement.getMedioAlto() != ZERO_SCORE) ? countAllScoreByProjectIdOrTypeRequirement.getMedioAlto() / countNumberScoreByProjectIdOrTypeRequirement.getMedioAlto() : medioAlto;
        medioMedio = (countNumberScoreByProjectIdOrTypeRequirement.getMedioMEdio() != ZERO_SCORE) ? countAllScoreByProjectIdOrTypeRequirement.getMedioMEdio() / countNumberScoreByProjectIdOrTypeRequirement.getMedioMEdio() : medioMedio;
        medioBajo = (countNumberScoreByProjectIdOrTypeRequirement.getMedioBajo() != ZERO_SCORE) ? countAllScoreByProjectIdOrTypeRequirement.getMedioBajo() / countNumberScoreByProjectIdOrTypeRequirement.getMedioBajo() : medioBajo;
        bajoAlto = (countNumberScoreByProjectIdOrTypeRequirement.getBajoAlto() != ZERO_SCORE) ? countAllScoreByProjectIdOrTypeRequirement.getBajoAlto() / countNumberScoreByProjectIdOrTypeRequirement.getBajoAlto() : bajoAlto;
        bajoMedio = (countNumberScoreByProjectIdOrTypeRequirement.getBajoMedio() != ZERO_SCORE) ? countAllScoreByProjectIdOrTypeRequirement.getBajoMedio() / countNumberScoreByProjectIdOrTypeRequirement.getBajoMedio() : bajoMedio;
        bajoBajo = (countNumberScoreByProjectIdOrTypeRequirement.getBajoBajo() != ZERO_SCORE) ? countAllScoreByProjectIdOrTypeRequirement.getBajoBajo() / countNumberScoreByProjectIdOrTypeRequirement.getBajoBajo() : bajoBajo;

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

    public AverageScore calculateWeightedMedian(Integer projectId) {
        AverageScore averageScore = new AverageScore();
        AverageScore funcional = averageScoreByProjectIdOrTypeRequirement(FUNCIONAL_TYPE, projectId);
        AverageScore noFuncional = averageScoreByProjectIdOrTypeRequirement(NO_FUNCIONAL_TYPE, projectId);

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
        int index = 0;
        double medianaAltoAlto, medianaAltoMedio, medianaAltoBajo;
        double medianMedioAlto, medianMedioMedio, medianMedioBajo;
        double medianBajoAlto, medianBajoMedio, medianBajoBajo;

        if (funcional.getAltoAlto() == ZERO_SCORE && funcional.getAltoMedio() == ZERO_SCORE && funcional.getAltoBajo() == ZERO_SCORE &&
                funcional.getMedioAlto() == ZERO_SCORE && funcional.getMedioMedio() == ZERO_SCORE && funcional.getMedioBajo() == ZERO_SCORE &&
                funcional.getBajoAlto() == ZERO_SCORE && funcional.getBajoMedio() == ZERO_SCORE && funcional.getBajoBajo() == ZERO_SCORE) {

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

        } else if (noFuncional.getAltoAlto() == ZERO_SCORE && noFuncional.getAltoMedio() == ZERO_SCORE && noFuncional.getAltoBajo() == ZERO_SCORE &&
                noFuncional.getMedioAlto() == ZERO_SCORE && noFuncional.getMedioMedio() == ZERO_SCORE && noFuncional.getMedioBajo() == ZERO_SCORE &&
                noFuncional.getBajoAlto() == ZERO_SCORE && noFuncional.getBajoMedio() == ZERO_SCORE && noFuncional.getBajoBajo() == ZERO_SCORE) {

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

        medianaAltoAlto = weightedMedianFormula(altoAlto, pair, index);
        medianaAltoMedio = weightedMedianFormula(altoMedio, pair, index);
        medianaAltoBajo = weightedMedianFormula(altoBajo, pair, index);

        medianMedioAlto = weightedMedianFormula(medioAlto, pair, index);
        medianMedioMedio = weightedMedianFormula(medioMedio, pair, index);
        medianMedioBajo = weightedMedianFormula(medioBajo, pair, index);

        medianBajoAlto = weightedMedianFormula(bajoAlto, pair, index);
        medianBajoMedio = weightedMedianFormula(bajoMedio, pair, index);
        medianBajoBajo = weightedMedianFormula(bajoBajo, pair, index);

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

    private double weightedMedianFormula(List<Double> list, boolean pair, int index) {
        double median;
        if (pair) {
            double sumaMedios = list.get(list.size() / 2) + list.get((list.size() / 2) - 1);
            median = sumaMedios / 2;
        } else {
            median = list.get(index);
        }
        return median;
    }

    public double calculateSortedWeightedMedian(AverageScore averageScore) {
        List<Double> list = new ArrayList<>();
        boolean pair = false;
        int index = 4;

        list.add(averageScore.getAltoAlto());
        list.add(averageScore.getAltoMedio());
        list.add(averageScore.getAltoBajo());

        list.add(averageScore.getMedioAlto());
        list.add(averageScore.getMedioMedio());
        list.add(averageScore.getMedioBajo());

        list.add(averageScore.getBajoAlto());
        list.add(averageScore.getBajoMedio());
        list.add(averageScore.getBajoBajo());

        Arrays.sort(list.toArray());

        return weightedMedianFormula(list, pair, index);
    }

    public AverageScore weightedAverageOfCumulativeScore(Integer projectId, double maximumCumulativeScore) {
        AverageScore averageScore = calculateWeightedMedian(projectId);

        averageScore.setAltoAlto(characteristicService.calculatePercentage(averageScore.getAltoAlto(), maximumCumulativeScore));
        averageScore.setAltoMedio(characteristicService.calculatePercentage(averageScore.getAltoMedio(), maximumCumulativeScore));
        averageScore.setAltoBajo(characteristicService.calculatePercentage(averageScore.getAltoBajo(), maximumCumulativeScore));

        averageScore.setMedioAlto(characteristicService.calculatePercentage(averageScore.getMedioAlto(), maximumCumulativeScore));
        averageScore.setMedioMedio(characteristicService.calculatePercentage(averageScore.getMedioMedio(), maximumCumulativeScore));
        averageScore.setMedioBajo(characteristicService.calculatePercentage(averageScore.getMedioBajo(), maximumCumulativeScore));

        averageScore.setBajoAlto(characteristicService.calculatePercentage(averageScore.getBajoAlto(), maximumCumulativeScore));
        averageScore.setBajoMedio(characteristicService.calculatePercentage(averageScore.getBajoMedio(), maximumCumulativeScore));
        averageScore.setBajoBajo(characteristicService.calculatePercentage(averageScore.getBajoBajo(), maximumCumulativeScore));

        return averageScore;
    }

}
