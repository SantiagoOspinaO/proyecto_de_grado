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

    public int countRequirementsByTypeAndError(String typeRequirement, String typeError) {
        return typeErrorCharacteristicDomainRepository.countRequirementsByTypeAndError(typeRequirement, typeError);
    }

    public int countRequirementsByErrorDDE(){
        return typeErrorCharacteristicDomainRepository.countRequirementByErrorDDE();
    }

    public int countRequirementsByErrorDII(){
        return typeErrorCharacteristicDomainRepository.countRequirementByErrorDII();
    }

    public int countRequirementsByErrorVAR(){
        return typeErrorCharacteristicDomainRepository.countRequirementByErrorVAR();
    }

    public int countTypeErrorEIEByRequirement(Integer requirementId){
        return typeErrorCharacteristicDomainRepository.countTypeErrorEIEByRequirement(requirementId);
    }

    public int countTypeErrorMCCByRequirement(Integer requirementId){
        return typeErrorCharacteristicDomainRepository.countTypeErrorMCCByRequirement(requirementId);
    }

    public int countTypeErrorsByRequirement(Integer requirementId){
        return typeErrorCharacteristicDomainRepository.countTypeErrorsByRequirement(requirementId);
    }

    public double percentageOfTypeErrorEIEById(Integer requirementId){
        Integer allRequirements = typeErrorCharacteristicDomainRepository.countTypeErrorsByRequirement(requirementId);
        double requirementEIE = typeErrorCharacteristicDomainRepository.countTypeErrorEIEByRequirement(requirementId);
        double result = (requirementEIE / allRequirements) * 100;
        return result;
    }

    public double percentageOfTypeErrorMCCById(Integer requirementId){
        Integer allRequirements = typeErrorCharacteristicDomainRepository.countTypeErrorsByRequirement(requirementId);
        double requirementMCC = typeErrorCharacteristicDomainRepository.countTypeErrorMCCByRequirement(requirementId);
        double result = (requirementMCC / allRequirements) * 100;
        return result;
    }

    public double percentageOffAllErrorById(Integer requirementId){
        double MCC = percentageOfTypeErrorMCCById(requirementId);
        double EIE = percentageOfTypeErrorEIEById(requirementId);
        return (MCC + EIE);
    }

}
