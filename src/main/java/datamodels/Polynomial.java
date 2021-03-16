package datamodels;

import java.util.ArrayList;
import java.util.Collections;

public class Polynomial {
    private ArrayList<MonomialInteger> polinom;
    private ArrayList<MonomialDouble> polinomDouble;

    public Polynomial() {
        this.polinom = new ArrayList<MonomialInteger>();
    }

    public Polynomial(ArrayList<MonomialDouble> doublePolynomial){
        this.polinomDouble = doublePolynomial;
    }

    public ArrayList<MonomialInteger> getMonomials() {
        return polinom;
    }

    public ArrayList<MonomialDouble> getDoubleMonomials() {
        return polinomDouble;
    }

    public void setMonomials(ArrayList<MonomialInteger> monomials) {
        this.polinom = monomials;
    }
}
