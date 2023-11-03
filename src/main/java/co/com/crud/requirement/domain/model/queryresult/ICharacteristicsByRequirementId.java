package co.com.crud.requirement.domain.model.queryresult;

public interface ICharacteristicsByRequirementId {

    String getNameCharacteristic();

    String getDescriptionCharacteristic();

    String getOppositeName();

    String getOppositeDescription();

    String getGradeCharacteristic();

    String getTypeError();

    String getDescriptionTypeError();

    Boolean getDde();

    Boolean getDii();

    Boolean getVar();
}
