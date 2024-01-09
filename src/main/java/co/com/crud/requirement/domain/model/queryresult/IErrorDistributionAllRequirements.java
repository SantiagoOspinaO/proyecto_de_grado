package co.com.crud.requirement.domain.model.queryresult;

public interface IErrorDistributionAllRequirements {

    Integer getIncorrectoEIE();

    Integer getAmbiguoEIE();

    Integer getIncompletoEIE();

    Integer getDebilEIE();

    Integer getIntrascendenteMCC();

    Integer getInestableMCC();

    Integer getNoComprobableMCC();

    Integer getNoIdentificableEIE();

    Integer getNoTrazableEIE();

}
