package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.AverageScore;
import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.domain.model.queryresult.ITotalMaxScore;
import co.com.crud.requirement.domain.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/operations")
public class OperationController {

    private final OperationService operationService;

    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Operation> updateOperation(@RequestBody Operation operation, @PathVariable("id") Integer id) {
        operation.setOperationId(id);
        operation.setRequirementId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.operationService.saveOperation(operation));
    }

    @GetMapping()
    public ResponseEntity<List<Operation>> getAllOperations() {
        return ResponseEntity.ok(operationService.getAllOperations());
    }

    @GetMapping(path = "/count-number-score-by-project-id-or-type-requirement")
    public ITotalMaxScore countNumberScoreByProjectIdOrTypeRequirement(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return operationService.countNumberScoreByProjectIdOrTypeRequirement(typeRequirement, projectId);
    }

    @GetMapping(path = "/count-all-score-by-project-id-or-type-requirement")
    public ITotalMaxScore countAllScoreByProjectIdOrTypeRequirement(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId) {
        return operationService.countAllScoreByProjectIdOrTypeRequirement(typeRequirement, projectId);
    }

    @GetMapping(path = "/average-score-by-project-id-or-type-requirement")
    public AverageScore averageScoreByProjectIdOrTypeRequirement(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId) {
        return operationService.averageScoreByProjectIdOrTypeRequirement(typeRequirement, projectId);
    }

    @GetMapping(path = "/calculate-median")
    public AverageScore calculateMedian(@RequestParam Integer projectId) {
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
