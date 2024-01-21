package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.exception.TypeErrorNotFoundException;
import co.com.crud.requirement.domain.model.TypeErrorCharacteristic;
import co.com.crud.requirement.domain.model.queryresult.IErrorDistributionAllRequirements;
import co.com.crud.requirement.domain.model.queryresult.IRequirementsByTypeAndCauseError;
import co.com.crud.requirement.domain.repository.TypeErrorCharacteristicDomainRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TypeErrorCharacteristicService {

    private final TypeErrorCharacteristicDomainRepository typeErrorCharacteristicDomainRepository;

    private final CharacteristicService characteristicService;

    @Autowired
    public TypeErrorCharacteristicService(TypeErrorCharacteristicDomainRepository typeErrorCharacteristicDomainRepository, CharacteristicService characteristicService) {
        this.typeErrorCharacteristicDomainRepository = typeErrorCharacteristicDomainRepository;
        this.characteristicService = characteristicService;
    }

    public List<TypeErrorCharacteristic> getAllTypesErrors() {
        return typeErrorCharacteristicDomainRepository.getAllTypesErrors();
    }

    public Optional<TypeErrorCharacteristic> getTypeErrorById(Integer id) {
        Optional<TypeErrorCharacteristic> typeError = typeErrorCharacteristicDomainRepository.getTypeErrorById(id);
        if (typeError.isEmpty()) {
            throw new TypeErrorNotFoundException(id);
        }
        return typeError;
    }

    public int countRequirementsByTypeAndCauseError(String typeRequirement, String causeError) {
        return typeErrorCharacteristicDomainRepository.countRequirementsByTypeAndCauseError(typeRequirement, causeError);
    }

    public int countRequirementsByCauseErrorDDE() {
        return typeErrorCharacteristicDomainRepository.countRequirementByCauseErrorDDE();
    }

    public int countRequirementsByCauseErrorDII() {
        return typeErrorCharacteristicDomainRepository.countRequirementByCauseErrorDII();
    }

    public int countRequirementsByCauseErrorVAR() {
        return typeErrorCharacteristicDomainRepository.countRequirementByCauseErrorVAR();
    }

    public int countRequirementsByCauseErrorAndRequirementId(Integer requirementId, Integer typeErrorId, String causeError) {
        return typeErrorCharacteristicDomainRepository.countRequirementsByCauseErrorAndRequirementId(requirementId, typeErrorId, causeError);
    }

    public int countTypeErrorEIEByRequirement(Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countTypeErrorEIEByRequirement(requirementId);
    }

    public int countTypeErrorEIEAndCauseErrorDDEByRequirement(Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countTypeErrorEIEAndCauseErrorDDEByRequirement(requirementId);
    }

    public int countTypeErrorEIEAndCauseErrorDIIByRequirement(Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countTypeErrorEIEAndCauseErrorDIIByRequirement(requirementId);
    }

    public int countTypeErrorEIEAndCauseErrorVARByRequirement(Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countTypeErrorEIEAndCauseErrorVARByRequirement(requirementId);
    }

    public int countTypeErrorMCCByRequirement(Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countTypeErrorMCCByRequirement(requirementId);
    }

    public int countTypeErrorMCCAndCauseErrorDDEByRequirement(Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countTypeErrorMCCAndCauseErrorDDEByRequirement(requirementId);
    }

    public int countTypeErrorMCCAndCauseErrorDIIByRequirement(Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countTypeErrorMCCAndCauseErrorDIIByRequirement(requirementId);
    }

    public int countTypeErrorMCCAndCauseErrorVARByRequirement(Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countTypeErrorMCCAndCauseErrorVARByRequirement(requirementId);
    }

    public int countTypeErrorsMCCByRequirement(Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countTypeErrorEIEByRequirement(requirementId);
    }


    public int countTypeErrorsByRequirements(Integer typeErrorId, Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countTypeErrorsByRequirements(typeErrorId, requirementId);
    }

    public int countAllTypeErrorsByRequirement(Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countAllTypeErrorsByRequirement(requirementId);
    }

    public int countAllCauseErrorsByRequirement(Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countAllCauseErrorsByRequirement(requirementId);
    }

    public Integer numberOfCauseErrorById(Integer requirementId, Integer typeError, String causeError) {
        return typeErrorCharacteristicDomainRepository.countRequirementsByCauseErrorAndRequirementId(requirementId, typeError, causeError);
    }

    public double percentageOfCauseErrorById(Integer requirementId, Integer typeErrorId, String causeError) {
        Integer allCauseErrors = typeErrorCharacteristicDomainRepository.countAllCauseErrorsByRequirement(requirementId);
        double causeErrorByCause = typeErrorCharacteristicDomainRepository.countRequirementsByCauseErrorAndRequirementId(requirementId, typeErrorId, causeError);
        return characteristicService.calculatePercentage(causeErrorByCause, allCauseErrors);
    }

    public Double percentageOfTypeErrorEIEById(Integer requirementId) {
        double allRequirements = typeErrorCharacteristicDomainRepository.countAllTypeErrorsByRequirement(requirementId);
        double requirementEIE = typeErrorCharacteristicDomainRepository.countTypeErrorEIEByRequirement(requirementId);
        return characteristicService.calculatePercentage(requirementEIE, allRequirements);
    }

    public Double percentageOfTypeErrorMCCById(Integer requirementId) {
        double allRequirements = typeErrorCharacteristicDomainRepository.countAllTypeErrorsByRequirement(requirementId);
        double requirementMCC = typeErrorCharacteristicDomainRepository.countTypeErrorMCCByRequirement(requirementId);
        return characteristicService.calculatePercentage(requirementMCC, allRequirements);
    }

    public Double percentageOfTypeErrosById(Integer typeErrorID, Integer requirementId) {
        Integer allRequirements = typeErrorCharacteristicDomainRepository.countAllTypeErrorsByRequirement(requirementId);
        Integer requirements = typeErrorCharacteristicDomainRepository.countTypeErrorsByRequirements(typeErrorID, requirementId);
        double result = characteristicService.calculatePercentage(requirements, allRequirements);
        return result;
    }

    public Double percentageOffAllTypeErrorById(Integer requirementId) {
        Double MCC = percentageOfTypeErrorMCCById(requirementId);
        Double EIE = percentageOfTypeErrorEIEById(requirementId);
        Double result = (MCC + EIE);
        if (result > 99.9) {
            result = 100.0;
        }
        return result;
    }

    public List<Double> allNumbersAndPercentageOperations(Integer requirementId) {
        List<Double> result = new ArrayList<>();
        double countTypeErrorEIEByRequirement = countTypeErrorEIEByRequirement(requirementId);
        double countTypeErrorMCCByRequirement = countTypeErrorMCCByRequirement(requirementId);
        double countAllTypeErrorById = countTypeErrorEIEByRequirement + countTypeErrorMCCByRequirement;
        double percentageOfTypeErrorEIEById = characteristicService.calculatePercentage(countTypeErrorEIEByRequirement, countAllTypeErrorById);
        double percentageOfTypeErrorMCCById = characteristicService.calculatePercentage(countTypeErrorMCCByRequirement, countAllTypeErrorById);
        double percentageAllTypeErrorById = percentageOfTypeErrorEIEById + percentageOfTypeErrorMCCById;

        double countTypeErrorEIEAndCauseErrorDDEByRequirement = countTypeErrorEIEAndCauseErrorDDEByRequirement(requirementId);
        double countTypeErrorEIEAndCauseErrorDIIByRequirement = countTypeErrorEIEAndCauseErrorDIIByRequirement(requirementId);
        double countTypeErrorEIEAndCauseErrorVARByRequirement = countTypeErrorEIEAndCauseErrorVARByRequirement(requirementId);
        double totalTypeErrorEIEWithCauseError = countTypeErrorEIEAndCauseErrorDDEByRequirement + countTypeErrorEIEAndCauseErrorDIIByRequirement + countTypeErrorEIEAndCauseErrorVARByRequirement;
        double percentageOfTypeErrorEIEAndCauseErrorDDEById = characteristicService.calculatePercentage(countTypeErrorEIEAndCauseErrorDDEByRequirement, totalTypeErrorEIEWithCauseError);
        double percentageOfTypeErrorEIEAndCauseErrorDIIById = characteristicService.calculatePercentage(countTypeErrorEIEAndCauseErrorDIIByRequirement, totalTypeErrorEIEWithCauseError);
        double percentageOfTypeErrorEIEAndCauseErrorVARById = characteristicService.calculatePercentage(countTypeErrorEIEAndCauseErrorVARByRequirement, totalTypeErrorEIEWithCauseError);
        double percentageOfTypeErrorEIEAndCauseError = percentageOfTypeErrorEIEAndCauseErrorDDEById + percentageOfTypeErrorEIEAndCauseErrorDIIById + percentageOfTypeErrorEIEAndCauseErrorVARById;

        double countTypeErrorMCCAndCauseErrorDDEByRequirement = countTypeErrorMCCAndCauseErrorDDEByRequirement(requirementId);
        double countTypeErrorMCCAndCauseErrorDIIByRequirement = countTypeErrorMCCAndCauseErrorDIIByRequirement(requirementId);
        double countTypeErrorMCCAndCauseErrorVARByRequirement = countTypeErrorMCCAndCauseErrorVARByRequirement(requirementId);
        double totalTypeErrorMCCWithCauseError = countTypeErrorMCCAndCauseErrorDDEByRequirement + countTypeErrorMCCAndCauseErrorDIIByRequirement + countTypeErrorMCCAndCauseErrorVARByRequirement;
        double percentageOfTypeErrorMCCAndCauseErrorDDEById = characteristicService.calculatePercentage(countTypeErrorMCCAndCauseErrorDDEByRequirement, totalTypeErrorMCCWithCauseError);
        double percentageOfTypeErrorMCCAndCauseErrorDIIById = characteristicService.calculatePercentage(countTypeErrorMCCAndCauseErrorDIIByRequirement, totalTypeErrorMCCWithCauseError);
        double percentageOfTypeErrorMCCAndCauseErrorVARById = characteristicService.calculatePercentage(countTypeErrorMCCAndCauseErrorVARByRequirement, totalTypeErrorMCCWithCauseError);
        double percentageOfTypeErrorMCCAndCauseError = percentageOfTypeErrorMCCAndCauseErrorDDEById + percentageOfTypeErrorMCCAndCauseErrorDIIById + percentageOfTypeErrorMCCAndCauseErrorVARById;

        result.add(countTypeErrorEIEByRequirement);
        result.add(percentageOfTypeErrorEIEById);
        result.add(countTypeErrorMCCByRequirement);
        result.add(percentageOfTypeErrorMCCById);
        result.add(countAllTypeErrorById);
        result.add(percentageAllTypeErrorById);

        result.add(countTypeErrorEIEAndCauseErrorDDEByRequirement);
        result.add(percentageOfTypeErrorEIEAndCauseErrorDDEById);
        result.add(countTypeErrorEIEAndCauseErrorDIIByRequirement);
        result.add(percentageOfTypeErrorEIEAndCauseErrorDIIById);
        result.add(countTypeErrorEIEAndCauseErrorVARByRequirement);
        result.add(percentageOfTypeErrorEIEAndCauseErrorVARById);
        result.add(totalTypeErrorEIEWithCauseError);
        result.add(percentageOfTypeErrorEIEAndCauseError);

        result.add(countTypeErrorMCCAndCauseErrorDDEByRequirement);
        result.add(percentageOfTypeErrorMCCAndCauseErrorDDEById);
        result.add(countTypeErrorMCCAndCauseErrorDIIByRequirement);
        result.add(percentageOfTypeErrorMCCAndCauseErrorDIIById);
        result.add(countTypeErrorMCCAndCauseErrorVARByRequirement);
        result.add(percentageOfTypeErrorMCCAndCauseErrorVARById);
        result.add(totalTypeErrorMCCWithCauseError);
        result.add(percentageOfTypeErrorMCCAndCauseError);

        return result;
    }

    public IRequirementsByTypeAndCauseError causeErrorByCharacteristicForRequirements(String typeRequirement, Integer projectId) {
        return typeErrorCharacteristicDomainRepository.causeErrorByCharacteristicForRequirements(typeRequirement, projectId);
    }

    public IErrorDistributionAllRequirements errorDistributionAllRequirements(String typeRequirement, Integer projectId) {
        return typeErrorCharacteristicDomainRepository.errorDistributionAllRequirements(typeRequirement, projectId);
    }

    @NotNull
    public Map<String, Double> getPercentageCauseErrorByCharacteristicInterface(IRequirementsByTypeAndCauseError requirements) {
        double incorrectoDDE = requirements.getIncorrectoDDE();
        double ambiguoDDE = requirements.getAmbiguoDDE();
        double incompletoDDE = requirements.getIncompletoDDE();
        double debilDDE = requirements.getDebilDDE();
        double intrascendenteDDE = requirements.getIntrascendenteDDE();
        double inestableDDE = requirements.getInestableDDE();
        double noComprobableDDE = requirements.getNoComprobableDDE();
        double noIdentificableDDE = requirements.getNoIdentificableDDE();
        double noTrazableDDE = requirements.getNoTrazableDDE();

        double incorrectoDII = requirements.getIncorrectoDII();
        double ambiguoDII = requirements.getAmbiguoDII();
        double incompletoDII = requirements.getIncompletoDII();
        double debilDII = requirements.getDebilDII();
        double intrascendenteDII = requirements.getIntrascendenteDII();
        double inestableDII = requirements.getInestableDII();
        double noComprobableDII = requirements.getNoComprobableDII();
        double noIdentificableDII = requirements.getNoIdentificableDII();
        double noTrazableDII = requirements.getNoTrazableDII();

        double incorrectoVAR = requirements.getIncorrectoVAR();
        double ambiguoVAR = requirements.getAmbiguoVAR();
        double incompletoVAR = requirements.getIncompletoVAR();
        double debilVAR = requirements.getDebilVAR();
        double intrascendenteVAR = requirements.getIntrascendenteVAR();
        double inestableVAR = requirements.getInestableVAR();
        double noComprobableVAR = requirements.getNoComprobableVAR();
        double noIdentificableVAR = requirements.getNoIdentificableVAR();
        double noTrazableVAR = requirements.getNoTrazableVAR();

        double totalEIEDDE = incorrectoDDE + ambiguoDDE + incompletoDDE + debilDDE + noIdentificableDDE + noTrazableDDE;

        double totalMCCDDE = intrascendenteDDE + inestableDDE + noComprobableDDE;

        double totalEIEDII = incorrectoDII + ambiguoDII + incompletoDII + debilDII + noIdentificableDII + noTrazableDII;

        double totalMCCDII = intrascendenteDII + inestableDII + noComprobableDII;

        double totalEIEVAR = incorrectoVAR + ambiguoVAR + incompletoVAR + debilVAR + noIdentificableVAR + noTrazableVAR;

        double totalMCCVAR = intrascendenteVAR + inestableVAR + noComprobableVAR;

        Map<String, Double> result = new HashMap<>();
        result.put("incorrectoEIEDDE", characteristicService.calculatePercentage(incorrectoDDE, totalEIEDDE));
        result.put("ambiguoEIEDDE", characteristicService.calculatePercentage(ambiguoDDE, totalEIEDDE));
        result.put("incompletoEIEDDE", characteristicService.calculatePercentage(incompletoDDE, totalEIEDDE));
        result.put("debilEIEDDE", characteristicService.calculatePercentage(debilDDE, totalEIEDDE));
        result.put("noIdentificableEIEDDE", characteristicService.calculatePercentage(noIdentificableDDE, totalEIEDDE));
        result.put("noTrazableEIEDDE", characteristicService.calculatePercentage(noTrazableDDE, totalEIEDDE));

        result.put("incorrectoEIEDII", characteristicService.calculatePercentage(incorrectoDDE, totalEIEDII));
        result.put("ambiguoEIEDII", characteristicService.calculatePercentage(ambiguoDDE, totalEIEDII));
        result.put("incompletoEIEDII", characteristicService.calculatePercentage(incompletoDDE, totalEIEDII));
        result.put("debilEIEDII", characteristicService.calculatePercentage(debilDDE, totalEIEDII));
        result.put("noIdentificableEIEDII", characteristicService.calculatePercentage(noIdentificableDDE, totalEIEDII));
        result.put("noTrazableEIEDII", characteristicService.calculatePercentage(noTrazableDDE, totalEIEDII));

        result.put("incorrectoEIEVAR", characteristicService.calculatePercentage(incorrectoDII, totalEIEVAR));
        result.put("ambiguoEIEVAR", characteristicService.calculatePercentage(ambiguoDII, totalEIEVAR));
        result.put("incompletoEIEVAR", characteristicService.calculatePercentage(incompletoDII, totalEIEVAR));
        result.put("debilEIEVAR", characteristicService.calculatePercentage(debilDII, totalEIEVAR));
        result.put("noIdentificableEIEVAR", characteristicService.calculatePercentage(noIdentificableDII, totalEIEVAR));
        result.put("noTrazableEIEVAR", characteristicService.calculatePercentage(noTrazableDII, totalEIEVAR));

        result.put("intrascendenteMCCDDE", characteristicService.calculatePercentage(intrascendenteVAR, totalMCCDDE));
        result.put("inestableMCCDDE", characteristicService.calculatePercentage(inestableVAR, totalMCCDDE));
        result.put("noComprobableMCCDDE", characteristicService.calculatePercentage(noComprobableVAR, totalMCCDDE));

        result.put("intrascendenteMCCDII", characteristicService.calculatePercentage(intrascendenteVAR, totalMCCDII));
        result.put("inestableMCCDII", characteristicService.calculatePercentage(inestableVAR, totalMCCDII));
        result.put("noComprobableMCCDII", characteristicService.calculatePercentage(noComprobableVAR, totalMCCDII));

        result.put("intrascendenteMCCVAR", characteristicService.calculatePercentage(intrascendenteVAR, totalMCCVAR));
        result.put("inestableMCCVAR", characteristicService.calculatePercentage(inestableVAR, totalMCCVAR));
        result.put("noComprobableMCCVAR", characteristicService.calculatePercentage(noComprobableVAR, totalMCCVAR));

        return result;
    }

    @NotNull
    public Map<String, Double> getPercentageErrorDistributionRequirementsInterface(IErrorDistributionAllRequirements requirements) {
        double incorrecto = requirements.getIncorrectoEIE() != null ? requirements.getIncorrectoEIE() : 0.0;
        double ambiguo = requirements.getAmbiguoEIE() != null ? requirements.getAmbiguoEIE() : 0.0;
        double incompleto = requirements.getIncompletoEIE() != null ? requirements.getIncompletoEIE() : 0.0;
        double debil = requirements.getDebilEIE() != null ? requirements.getDebilEIE() : 0.0;
        double noIdentificable = requirements.getNoIdentificableEIE() != null ? requirements.getNoIdentificableEIE() : 0.0;
        double noTrazable = requirements.getNoTrazableEIE() != null ? requirements.getNoTrazableEIE() : 0.0;
        double intrascendente = requirements.getIntrascendenteMCC() != null ? requirements.getIntrascendenteMCC() : 0.0;
        double inestable = requirements.getInestableMCC() != null ? requirements.getInestableMCC() : 0.0;
        double noComprobable = requirements.getNoComprobableMCC() != null ? requirements.getNoComprobableMCC() : 0.0;

        double totalEIE = incorrecto + ambiguo + incompleto + debil + noIdentificable + noTrazable;
        double totalMCC = intrascendente + inestable + noComprobable;

        Map<String, Double> result = new HashMap<>();
        result.put("incorrecto", characteristicService.calculatePercentage(incorrecto, totalEIE));
        result.put("ambiguo", characteristicService.calculatePercentage(ambiguo, totalEIE));
        result.put("incompleto", characteristicService.calculatePercentage(incompleto, totalEIE));
        result.put("debil", characteristicService.calculatePercentage(debil, totalEIE));
        result.put("intrascendente", characteristicService.calculatePercentage(intrascendente, totalMCC));
        result.put("inestable", characteristicService.calculatePercentage(inestable, totalMCC));
        result.put("noComprobable", characteristicService.calculatePercentage(noComprobable, totalMCC));
        result.put("noIdentificable", characteristicService.calculatePercentage(noIdentificable, totalEIE));
        result.put("noTrazable", characteristicService.calculatePercentage(noTrazable, totalEIE));

        return result;
    }

}
