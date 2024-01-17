package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.exception.TypeErrorNotFoundException;
import co.com.crud.requirement.domain.model.TypeErrorCharacteristic;
import co.com.crud.requirement.domain.model.queryresult.IErrorDistributionAllRequirements;
import co.com.crud.requirement.domain.model.queryresult.IRequirementsByTypeAndCauseError;
import co.com.crud.requirement.domain.repository.TypeErrorCharacteristicDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TypeErrorCharacteristicService {

    private final TypeErrorCharacteristicDomainRepository typeErrorCharacteristicDomainRepository;

    @Autowired
    public TypeErrorCharacteristicService(TypeErrorCharacteristicDomainRepository typeErrorCharacteristicDomainRepository) {
        this.typeErrorCharacteristicDomainRepository = typeErrorCharacteristicDomainRepository;
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
        return (causeErrorByCause / allCauseErrors) * 100;
    }

    public Double percentageOfTypeErrorEIEById(Integer requirementId) {
        double allRequirements = typeErrorCharacteristicDomainRepository.countAllTypeErrorsByRequirement(requirementId);
        double requirementEIE = typeErrorCharacteristicDomainRepository.countTypeErrorEIEByRequirement(requirementId);
        return (requirementEIE / allRequirements) * 100;
    }

    public Double percentageOfTypeErrorMCCById(Integer requirementId) {
        double allRequirements = typeErrorCharacteristicDomainRepository.countAllTypeErrorsByRequirement(requirementId);
        double requirementMCC = typeErrorCharacteristicDomainRepository.countTypeErrorMCCByRequirement(requirementId);
        return (requirementMCC / allRequirements) * 100;
    }

    public Double percentageOfTypeErrosById(Integer typeErrorID, Integer requirementId) {
        Integer allRequirements = typeErrorCharacteristicDomainRepository.countAllTypeErrorsByRequirement(requirementId);
        Integer requirements = typeErrorCharacteristicDomainRepository.countTypeErrorsByRequirements(typeErrorID, requirementId);
        double result = ((double)requirements / (double)allRequirements) * 100 ;
        return result;
    }

    public Double percentageOffAllTypeErrorById(Integer requirementId) {
        Double MCC = percentageOfTypeErrorMCCById(requirementId);
        Double EIE = percentageOfTypeErrorEIEById(requirementId);
        Double result = (MCC + EIE);
        if( result > 99.9){
            result = 100.0;
        }
        return result;
    }

    public List<Double> allNumbersAndPercentageOperations(Integer requirementId){
        List<Double> result = new ArrayList<>();
        double countTypeErrorEIEByRequirement = countTypeErrorEIEByRequirement(requirementId);
        double countTypeErrorMCCByRequirement = countTypeErrorMCCByRequirement(requirementId);
        double countAllTypeErrorById = countTypeErrorEIEByRequirement + countTypeErrorMCCByRequirement  ;
        double percentageOfTypeErrorEIEById = (countTypeErrorEIEByRequirement / countAllTypeErrorById ) * 100 ;
        double percentageOfTypeErrorMCCById = (countTypeErrorMCCByRequirement / countAllTypeErrorById ) * 100 ;
        double percentageAllTypeErrorById = percentageOfTypeErrorEIEById+ percentageOfTypeErrorMCCById;

        double countTypeErrorEIEAndCauseErrorDDEByRequirement = countTypeErrorEIEAndCauseErrorDDEByRequirement(requirementId);
        double countTypeErrorEIEAndCauseErrorDIIByRequirement = countTypeErrorEIEAndCauseErrorDIIByRequirement(requirementId);
        double countTypeErrorEIEAndCauseErrorVARByRequirement = countTypeErrorEIEAndCauseErrorVARByRequirement(requirementId);
        double totalTypeErrorEIEWithCauseError = countTypeErrorEIEAndCauseErrorDDEByRequirement+ countTypeErrorEIEAndCauseErrorDIIByRequirement + countTypeErrorEIEAndCauseErrorVARByRequirement;
        double percentageOfTypeErrorEIEAndCauseErrorDDEById = (countTypeErrorEIEAndCauseErrorDDEByRequirement / totalTypeErrorEIEWithCauseError) * 100;
        double percentageOfTypeErrorEIEAndCauseErrorDIIById = (countTypeErrorEIEAndCauseErrorDIIByRequirement / totalTypeErrorEIEWithCauseError) * 100;
        double percentageOfTypeErrorEIEAndCauseErrorVARById = (countTypeErrorEIEAndCauseErrorVARByRequirement / totalTypeErrorEIEWithCauseError) * 100;
        double percentageOfTypeErrorEIEAndCauseError= percentageOfTypeErrorEIEAndCauseErrorDDEById + percentageOfTypeErrorEIEAndCauseErrorDIIById + percentageOfTypeErrorEIEAndCauseErrorVARById;

        double countTypeErrorMCCAndCauseErrorDDEByRequirement = countTypeErrorMCCAndCauseErrorDDEByRequirement(requirementId);
        double countTypeErrorMCCAndCauseErrorDIIByRequirement = countTypeErrorMCCAndCauseErrorDIIByRequirement(requirementId);
        double countTypeErrorMCCAndCauseErrorVARByRequirement = countTypeErrorMCCAndCauseErrorVARByRequirement(requirementId);
        double totalTypeErrorMCCWithCauseError = countTypeErrorMCCAndCauseErrorDDEByRequirement+ countTypeErrorMCCAndCauseErrorDIIByRequirement + countTypeErrorMCCAndCauseErrorVARByRequirement;
        double percentageOfTypeErrorMCCAndCauseErrorDDEById = (countTypeErrorMCCAndCauseErrorDDEByRequirement / totalTypeErrorMCCWithCauseError) * 100;
        double percentageOfTypeErrorMCCAndCauseErrorDIIById = (countTypeErrorMCCAndCauseErrorDIIByRequirement / totalTypeErrorMCCWithCauseError) * 100;
        double percentageOfTypeErrorMCCAndCauseErrorVARById = (countTypeErrorMCCAndCauseErrorVARByRequirement / totalTypeErrorMCCWithCauseError) * 100;
        double percentageOfTypeErrorMCCAndCauseError= percentageOfTypeErrorMCCAndCauseErrorDDEById + percentageOfTypeErrorMCCAndCauseErrorDIIById + percentageOfTypeErrorMCCAndCauseErrorVARById ;

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

    public IRequirementsByTypeAndCauseError causeErrorByCharacteristicForRequirements(String typeRequirement,Integer projectId){
        return typeErrorCharacteristicDomainRepository.causeErrorByCharacteristicForRequirements(typeRequirement,projectId);
    }

    public IErrorDistributionAllRequirements errorDistributionAllRequirements(String typeRequirement,Integer projectId){
        return typeErrorCharacteristicDomainRepository.errorDistributionAllRequirements(typeRequirement,projectId);
    }

}
