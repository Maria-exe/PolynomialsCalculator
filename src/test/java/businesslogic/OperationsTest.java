package businesslogic;

import static org.junit.jupiter.api.Assertions.*;

import datamodels.Model;
import datamodels.MonomialDouble;
import datamodels.MonomialInteger;
import datamodels.Polynomial;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

class OperationsTest {

    @Test
    void add() {
        Polynomial a = new Polynomial();
        a.getMonomials().add(new MonomialInteger(3, 5));
        a.getMonomials().add(new MonomialInteger(2, 2));
        a.getMonomials().add(new MonomialInteger(4, 1));
        a.getMonomials().add(new MonomialInteger(3, 0));
        Polynomial b = new Polynomial();
        b.getMonomials().add(new MonomialInteger(1, 5));
        b.getMonomials().add(new MonomialInteger(2, 2));
        b.getMonomials().add(new MonomialInteger(2, 1));
        b.getMonomials().add(new MonomialInteger(3, 0));
        Polynomial res = new Polynomial();
        res.getMonomials().add(new MonomialInteger(4,5));
        res.getMonomials().add(new MonomialInteger(4,2));
        res.getMonomials().add(new MonomialInteger(6,1));
        res.getMonomials().add(new MonomialInteger(6,0));
        assertEquals(res.getMonomials().size(),Operations.add(a, b).getMonomials().size());
        for(int i=0;i<res.getMonomials().size();i++) {
            assertEquals(Operations.add(a, b).getMonomials().get(i).getCoeff(), res.getMonomials().get(i).getCoeff());
            assertEquals(Operations.add(a, b).getMonomials().get(i).getDegree(), res.getMonomials().get(i).getDegree());
        }
    }

    @Test
    void sub() {
        Polynomial a = new Polynomial();
        a.getMonomials().add(new MonomialInteger(4, 1));
        a.getMonomials().add(new MonomialInteger(7, 0));

        Polynomial b = new Polynomial();
        b.getMonomials().add(new MonomialInteger(2, 1));
        b.getMonomials().add(new MonomialInteger(2, 2));
        b.getMonomials().add(new MonomialInteger(2, 0));

        Polynomial res = new Polynomial();
        res.getMonomials().add(new MonomialInteger(2,1));
        res.getMonomials().add(new MonomialInteger(5,0));
        res.getMonomials().add(new MonomialInteger(-2,2));
        assertEquals(res.getMonomials().size(),Operations.sub(a, b).getMonomials().size());

        for(int i=0;i<res.getMonomials().size();i++) {
            assertEquals(Operations.sub(a, b).getMonomials().get(i).getCoeff(), res.getMonomials().get(i).getCoeff());
            assertEquals(Operations.sub(a, b).getMonomials().get(i).getDegree(), res.getMonomials().get(i).getDegree());
        }
    }

    @Test
    void multiply() {
        Polynomial a = new Polynomial();
        a.getMonomials().add(new MonomialInteger(4, 1));
        a.getMonomials().add(new MonomialInteger(7, 0));
        Polynomial b = new Polynomial();
        b.getMonomials().add(new MonomialInteger(3, 1));
        b.getMonomials().add(new MonomialInteger(3, 0));
        Polynomial res = new Polynomial();
        res.getMonomials().add(new MonomialInteger(12,2));
        res.getMonomials().add(new MonomialInteger(33,1));
        res.getMonomials().add(new MonomialInteger(21,0));
        res = Model.simplifyPolynomial(res);
        Polynomial opres = Operations.multiply(a, b);
        opres = Model.simplifyPolynomial(opres);
        assertEquals(res.getMonomials().size(),opres.getMonomials().size());
        for(int i=0;i<res.getMonomials().size();i++) {
            assertEquals(opres.getMonomials().get(i).getCoeff(), res.getMonomials().get(i).getCoeff());
            assertEquals(opres.getMonomials().get(i).getDegree(), res.getMonomials().get(i).getDegree());
        }
    }

    @Test
    void deriv() {
        Polynomial b = new Polynomial();
        b.getMonomials().add(new MonomialInteger(2, 1));
        b.getMonomials().add(new MonomialInteger(2, 2));
        b.getMonomials().add(new MonomialInteger(2, 3));

        Polynomial res = new Polynomial();
        res.getMonomials().add(new MonomialInteger(2,0));
        res.getMonomials().add(new MonomialInteger(4,1));
        res.getMonomials().add(new MonomialInteger(6,2));
        Polynomial opres = Operations.deriv(b);
        assertEquals(res.getMonomials().size(),opres.getMonomials().size());
        for(int i=0;i<res.getMonomials().size();i++) {
            assertEquals(opres.getMonomials().get(i).getCoeff(), res.getMonomials().get(i).getCoeff());
            assertEquals(opres.getMonomials().get(i).getDegree(), res.getMonomials().get(i).getDegree());
        }
    }

    @Test
    void integrate() {
        Polynomial b = new Polynomial();
        b.getMonomials().add(new MonomialInteger(2, 1));
        b.getMonomials().add(new MonomialInteger(6, 2));
        ArrayList<MonomialDouble> doublePolynomial = new ArrayList<MonomialDouble>();
        Polynomial res = new Polynomial(doublePolynomial);
        res.getDoubleMonomials().add(new MonomialDouble(1.0,2));
        res.getDoubleMonomials().add(new MonomialDouble(2.0,3));
        Polynomial opres = Operations.integrate(b);
        assertEquals(res.getDoubleMonomials().size(),opres.getDoubleMonomials().size());
        for(int i=0;i<res.getDoubleMonomials().size();i++) {
            assertEquals(opres.getDoubleMonomials().get(i).getCoeff(), res.getDoubleMonomials().get(i).getCoeff());
            assertEquals(opres.getDoubleMonomials().get(i).getDegree(), res.getDoubleMonomials().get(i).getDegree());
        }
    }

    @Test
    void devide() {
        Polynomial P = new Polynomial(), Q = new Polynomial();
        P.getMonomials().add(new MonomialInteger(1,3));
        P.getMonomials().add(new MonomialInteger(-2,2));
        P.getMonomials().add(new MonomialInteger(6,1));
        P.getMonomials().add(new MonomialInteger(-5,0));

        Q.getMonomials().add(new MonomialInteger(1,2));
        Q.getMonomials().add(new MonomialInteger(-1,0));

        Polynomial opres = Operations.devide(P,Q);
        Polynomial res = new Polynomial();
        res.getMonomials().add(new MonomialInteger(1,1));
        res.getMonomials().add(new MonomialInteger(-2,0));

        assertEquals(res.getMonomials().size(),opres.getMonomials().size());
        for(int i=0;i<res.getMonomials().size();i++) {
            assertEquals(opres.getMonomials().get(i).getCoeff(), res.getMonomials().get(i).getCoeff());
            assertEquals(opres.getMonomials().get(i).getDegree(), res.getMonomials().get(i).getDegree());
        }
    }
}