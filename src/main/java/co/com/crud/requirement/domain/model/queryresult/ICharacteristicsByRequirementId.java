package co.com.crud.requirement.domain.model.queryresult;

public interface ICharacteristicsByRequirementId {

    Integer getIdCharacteristic();

    String getNameCharacteristic();

    String getDescriptionCharacteristic();

    String getOppositeName();

    String getOppositeDescription();

    Double getGradeCharacteristic();

    String getTypeError();

    String getDescriptionTypeError();

    Boolean getDde();

    Boolean getDii();

    Boolean getVar();

}
