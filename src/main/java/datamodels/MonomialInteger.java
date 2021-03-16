package datamodels;

public class MonomialInteger extends Monomial{
    private Integer coeff;

    public MonomialInteger() {}

    public MonomialInteger(Integer coeff, Integer degree) {
        this.coeff = coeff;
        this.setDegree(degree);
        this.setProcessed(false);
    }

    public Integer getCoeff() {
        return coeff;
    }
    public void setCoeff(Integer coeff) {
        this.coeff = coeff;
    }

}