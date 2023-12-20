package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.exception.TypeErrorNotFoundException;
import co.com.crud.requirement.domain.model.TypeErrorCharacteristic;
import co.com.crud.requirement.domain.repository.TypeErrorCharacteristicDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public int countRequirementsByCauseErrorAndRequirementId(Integer requirementId, String causeError) {
        return typeErrorCharacteristicDomainRepository.countRequirementsByCauseErrorAndRequirementId(requirementId,causeError);
    }

    public int countTypeErrorEIEByRequirement(Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countTypeErrorEIEByRequirement(requirementId);
    }

    public int countTypeErrorMCCByRequirement(Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countTypeErrorMCCByRequirement(requirementId);
    }

    public int countTypeErrorsByRequirements(Integer typeErrorId, Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countTypeErrorsByRequirements(typeErrorId, requirementId);
    }

    public int countTypeErrorsByRequirement(Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countTypeErrorsByRequirement(requirementId);
    }

    public int countCauseErrorsByRequirement(Integer requirementId) {
        return typeErrorCharacteristicDomainRepository.countCauseErrorsByRequirement(requirementId);
    }

    public double percentageOfTypeErrorEIEById(Integer requirementId) {
        Integer allRequirements = typeErrorCharacteristicDomainRepository.countTypeErrorsByRequirement(requirementId);
        double requirementEIE = typeErrorCharacteristicDomainRepository.countTypeErrorEIEByRequirement(requirementId);
        double result = (requirementEIE / allRequirements) * 100;
        return result;
    }

    public double percentageOfTypeErrorMCCById(Integer requirementId) {
        Integer allRequirements = typeErrorCharacteristicDomainRepository.countTypeErrorsByRequirement(requirementId);
        double requirementMCC = typeErrorCharacteristicDomainRepository.countTypeErrorMCCByRequirement(requirementId);
        double result = (requirementMCC / allRequirements) * 100;
        return result;
    }

    public double percentageOfTypeErrosById(Integer typeErrorID, Integer requirementId) {
        Integer allRequirements = typeErrorCharacteristicDomainRepository.countTypeErrorsByRequirement(requirementId);
        double requirements = typeErrorCharacteristicDomainRepository.countTypeErrorsByRequirements(typeErrorID, requirementId);
        double result = (requirements / allRequirements) * 100;
        return result;
    }

    public double percentageOffAllTypeErrorById(Integer requirementId) {
        double MCC = percentageOfTypeErrorMCCById(requirementId);
        double EIE = percentageOfTypeErrorEIEById(requirementId);
        return (MCC + EIE);
    }

    public double percentageOffAllCauseErrorById(Integer requirementId) {
        double VAR = percentageOfTypeErrorMCCById(requirementId);
        double DDE = percentageOfTypeErrorEIEById(requirementId);
        double DII = percentageOfTypeErrorEIEById(requirementId);
        return (VAR + DDE + DII);
    }
/*
    public double percentageOffCauseErrorDEE(){
        Integer allCauseErrors = typeErrorCharacteristicDomainRepository.countCauseErrorsByRequirement()

    }

    public double percentageOffCauseErrorDII(){

    }

    public double percentageOffCauseErrorVAR(){

    }
*/
}
