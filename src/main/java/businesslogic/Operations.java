package businesslogic;

import java.util.ArrayList;
import java.util.Collections;

import datamodels.*;

public class Operations {
	public static Polynomial add(Polynomial a, Polynomial b) {
		Polynomial res = new Polynomial();
		Integer coeff;
		for (MonomialInteger m1 : a.getMonomials()) {
			for (MonomialInteger m2 : b.getMonomials()) {
				if (m1.getDegree().equals(m2.getDegree())) {
					coeff = m1.getCoeff() + m2.getCoeff();
					m1.setProcessed();
					m2.setProcessed();
					res.getMonomials().add(new MonomialInteger(coeff, m1.getDegree()));
				}
			}
			if (!m1.isProcessed())
				res.getMonomials().add(m1);
		}
		for (MonomialInteger m2 : b.getMonomials()) {
			if (!m2.isProcessed())
				res.getMonomials().add(m2);
		}
		return res;
	}

	public static Polynomial sub(Polynomial a, Polynomial b) {
		Polynomial res = new Polynomial();
		Integer coeff;
		for (MonomialInteger m1 : a.getMonomials()) {
			for (MonomialInteger m2 : b.getMonomials()) {
				if (m1.getDegree().equals(m2.getDegree())) {
					coeff = m1.getCoeff() - m2.getCoeff();
					m1.setProcessed();
					m2.setProcessed();
					if (coeff != 0)
						res.getMonomials().add(new MonomialInteger(coeff, m1.getDegree()));
				}
			}
			if (!m1.isProcessed())
				res.getMonomials().add(m1);
		}
		for (MonomialInteger m2 : b.getMonomials()) {
			if (!m2.isProcessed())
				res.getMonomials().add(new MonomialInteger(-m2.getCoeff(), m2.getDegree()));
		}
		return res;
	}

	public static Polynomial multiply(Polynomial a, Polynomial b) {
		Polynomial res = new Polynomial();
		Integer coef, pow;
		for (MonomialInteger m1 : a.getMonomials()) {
			for (MonomialInteger m2 : b.getMonomials()) {
				coef = m1.getCoeff() * m2.getCoeff();
				pow = m1.getDegree() + m2.getDegree();
				res.getMonomials().add(new MonomialInteger(coef, pow));
			}
		}
		return res;
	}

	public static Polynomial deriv(Polynomial a) {
		Polynomial res = new Polynomial();
		res.getMonomials().addAll(a.getMonomials());
		for (MonomialInteger m1 : res.getMonomials()) {
			m1.setCoeff(m1.getCoeff() * m1.getDegree());
			if (m1.getDegree() != 0)
				m1.setDegree(m1.getDegree() - 1);
		}
		return res;
	}
	 public static Polynomial integrate(Polynomial a) {
	        ArrayList<MonomialDouble> doublePolynomial = new ArrayList<MonomialDouble>();
	        Polynomial res = new Polynomial(doublePolynomial);
	        for(MonomialInteger monom: a.getMonomials()){
	            res.getDoubleMonomials().add(new MonomialDouble(Double.valueOf(monom.getCoeff()), monom.getDegree()));
	        }
	        for(MonomialDouble m: res.getDoubleMonomials()){
	            m.setDegree(m.getDegree()+1);
	            Double integralCoeff = m.getCoeff() / m.getDegree();
	            m.setCoeff(integralCoeff);
	        }
	        return res;
	    }

	 public static  Polynomial devide(Polynomial a, Polynomial b) {
	        Collections.sort(a.getMonomials(), Collections.reverseOrder()); // ordonam numitorul si numaratorul
	        Collections.sort(b.getMonomials(), Collections.reverseOrder());
	        Polynomial P, Q, quotient = new Polynomial() , res = new Polynomial(); // P - numarator, Q - numitor, quotient - catul impartirii
	        if(b.getMonomials().get(0).getDegree() > a.getMonomials().get(0).getDegree()){
	            P = b;
	            Q = a;
	        }
	        else {
	            P = a;
	            Q = b;
	        }
	        Integer coef, pow;
	        while (P.getMonomials().size()>0 && Q.getMonomials().size()>0 && P.getMonomials().get(0).getDegree() >= Q.getMonomials().get(0).getDegree()){
	            Collections.sort(P.getMonomials(), Collections.reverseOrder());
	            Collections.sort(Q.getMonomials(), Collections.reverseOrder());
	            MonomialInteger m1 = P.getMonomials().get(0);
	            MonomialInteger m2 = Q.getMonomials().get(0);
	            coef = m1.getCoeff() / m2.getCoeff();
	            pow = m1.getDegree() - m2.getDegree();
	            if(coef != 0)
	                quotient.getMonomials().add(new MonomialInteger(coef,pow));
	            res.getMonomials().add(new MonomialInteger(coef,pow));
	            P = Operations.sub(P,Operations.multiply(quotient,Q));
	            quotient.getMonomials().removeAll(quotient.getMonomials());
	        }
	        return res;
	    }


}
