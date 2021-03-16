package datamodels;

public class MonomialDouble extends Monomial{
    private Double coeff;
    public MonomialDouble(Double coeff, Integer degree) {
        this.coeff = coeff;
        this.setDegree(degree);
        this.setProcessed(false);
    }

    public Double getCoeff() {
        return coeff;
    }
    public void setCoeff(Double coeff) {
        this.coeff = coeff;
    }
}