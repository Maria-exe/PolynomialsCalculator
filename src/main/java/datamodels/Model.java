package datamodels;

import java.util.Collections;
import java.util.regex.*;

public class Model {
    private static final String INITIAL_VALUE = "";
    private static final String monomialPtrn = "([+-]?[^-+]+)";  // regex for splitting input string into monomials
    private static final String polynomialPtrn = "^\\d{0,}(?:x(?:\\^(?:\\d+)?)?)?(?:(?:x?(\\d+)?)[+-]\\d{0,}(?:x(?:\\^(?:\\d+)?)?)?)*$";
    private Polynomial op1;
    private Polynomial op2;
    private String result;

    public Model(){
        reset();
    }

    public void reset() {
        result = INITIAL_VALUE;
        op1 = new Polynomial();
        op2 = new Polynomial();
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  // match a number with optional '-' and decimal.
    }

    public static  Polynomial stringToPoly(String inp){
        Pattern pattern = Pattern.compile(monomialPtrn);
        Matcher matcher = pattern.matcher(inp);
        MonomialInteger monom = new MonomialInteger();
        Polynomial polynomial = new Polynomial();
        while (matcher.find()) {
            String m = matcher.group(1);
            String [] s = m.split("x\\^?\\b"); // split monomial into coefficient and degree
            for (int i = 0 ;i<s.length;i++) {
                if (s[i].length()>0 && s[i].charAt(0) == '+') s[i] = s[i].substring(1); }
            if(s.length == 2) { // the monomial has both coefficient and degree
                if(Model.isNumeric(s[0])) monom.setCoeff(Integer.parseInt(s[0]));
                else if(s[0].equals("-")) monom.setCoeff(-1);
                else monom.setCoeff(1);
                if(Model.isNumeric(s[1])) monom.setDegree(Integer.parseInt(s[1]));
                else  monom.setDegree(1); }
            else { //the monomial has either a degree, either a coefficient
                if(matcher.group(1).contains("^")) { //the monomial has no coefficient
                    monom.setCoeff(1);
                    monom.setDegree(Integer.parseInt(s[0])); }
                else { //the monomial has no degree or the degree is 1
                    if(s.length>0 && Model.isNumeric(s[0])) // the monomial has an integer as coefficient
                        monom.setCoeff(Integer.parseInt(s[0]));
                    else if(s.length>0 && s[0].equals("-")) // the minomial has -1 as coefficient
                        monom.setCoeff(-1);
                    else  monom.setCoeff(1); // the monomial has 1 as coefficient
                    if(matcher.group(1).contains("x")) monom.setDegree(1); // the monomial has 1 as degree
                    else monom.setDegree(0); } }
            polynomial.getMonomials().add(new MonomialInteger(monom.getCoeff(),monom.getDegree())); }
        return polynomial;
    }

    public static String polyToString(Polynomial pol){
        StringBuilder sb = new StringBuilder();
        Collections.sort(pol.getMonomials());
        for(MonomialInteger monom: pol.getMonomials()){
             if(monom.getCoeff() == -1 ) // to avoid outputting -1x, this test appends only the '-' operator if coefficient of the monomial equals -1
                 sb.append('-');
             else if(monom.getCoeff() != -1 ) {
                 if(monom.getCoeff() >= 1 && pol.getMonomials().indexOf(monom) != 0) // if its the first monomial we don't append a '+' operator before coeff
                     sb.append('+');                                                // if the coefficient is positive than we append '+' operator before it
                 if(monom.getCoeff() != 0 && (monom.getCoeff() != 1 || (monom.getCoeff() == 1 && monom.getDegree() == 0))){
                    sb.append(monom.getCoeff());
                 }
             }
             if(monom.getDegree() != 0 && monom.getCoeff() != 0)
                 sb.append('x');
             if(monom.getDegree() > 1)
                 sb.append('^');
             if(monom.getDegree() != 1 && monom.getDegree() != 0)
                 sb.append(monom.getDegree());
        }
        System.out.println(sb.toString());
        return String.valueOf(sb);
    }

    public static String doublePolyToString(Polynomial pol){
        StringBuilder sb = new StringBuilder();
        Collections.sort(pol.getDoubleMonomials());
        for(MonomialDouble monom: pol.getDoubleMonomials()){
            if(monom.getCoeff() == -1) // to avoid outputting -1x, this test appends only the '-' operator if coefficient of the monomial equals -1
                sb.append('-');
            else if(monom.getCoeff() != -1 ) {
                if(monom.getCoeff() > 0 && pol.getDoubleMonomials().indexOf(monom) != 0) // if its the first monomial we don't append a '+' operator before coeff
                    sb.append('+');                                                // if the coefficient is positive than we append '+' operator before it
                if(monom.getCoeff() != 0 && (monom.getCoeff() != 1 || (monom.getCoeff() == 1 && monom.getDegree() == 0))){
                    sb.append(monom.getCoeff());
                }
            }
            if(monom.getDegree() != 0 && monom.getCoeff() != 0)
                sb.append('x');
            if(monom.getDegree() > 1)
                sb.append('^');
            if(monom.getDegree() != 1 && monom.getDegree() != 0)
                sb.append(monom.getDegree());
        }
        System.out.println(sb.toString());
        return String.valueOf(sb);
    }

    public static Polynomial simplifyPolynomial(Polynomial p){
        Polynomial res = p;
        Integer coeff;
        for(int i=0; i < p.getMonomials().size(); i++) {
            for (int j=i+1; j < p.getMonomials().size(); j++) {
                if (p.getMonomials().get(i).getDegree().equals(p.getMonomials().get(j).getDegree())) {
                    coeff = p.getMonomials().get(i).getCoeff() + p.getMonomials().get(j).getCoeff();
                    res.getMonomials().get(i).setCoeff(coeff);
                    res.getMonomials().remove(j);
                }
            }
        }
        return res;
    }

    public static Polynomial simplifyDoublePolynomial(Polynomial p){
        Polynomial res = p;
        Double coeff;
        for(int i=0; i < p.getDoubleMonomials().size(); i++) {
            for (int j=i+1; j < p.getDoubleMonomials().size(); j++) {
                if (p.getDoubleMonomials().get(i).getDegree().equals(p.getDoubleMonomials().get(j).getDegree())) {
                    coeff = p.getDoubleMonomials().get(i).getCoeff() + p.getDoubleMonomials().get(j).getCoeff();
                    res.getDoubleMonomials().get(i).setCoeff(coeff);
                    res.getDoubleMonomials().remove(j);
                }
            }
        }
        return res;
    }

    public static boolean checkInputString(String userParsedPolynomial){
        if(userParsedPolynomial.matches(polynomialPtrn)){
            return true;}
        return false;
    }

    public Polynomial getOp1() {
        return op1;
    }

    public void setOp1(Polynomial op1) {
        this.op1 = op1;
    }

    public Polynomial getOp2() {
        return op2;
    }

    public void setOp2(Polynomial op2) {
        this.op2 = op2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
