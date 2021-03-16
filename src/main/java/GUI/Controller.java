package GUI;
import businesslogic.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import datamodels.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Controller {
    private Model m;
    private View v;

    public Controller(View v, Model m) {
        this.m = m;
        this.v = v;
        v.addBtnListener(new AddListener());
        v.subBtnListener(new SubListener());
        v.mulBtnListener(new MultiplyListener());
        v.derivativeBtnListener(new DerivListener());
        v.resetBtnListener(new ResetListener());
        v.integrateBtnListener(new InegrateListener());
        v.divBtnListener(new DivideListener());
        v.documentListenerForOp1(new InputPolynomialListener());
        v.documentListenerForOp2(new InputPolynomialListener());
    }

    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            m.setOp1(Model.stringToPoly(v.getOp1())); // v.getOp1() gets the string polynomial operand inserted by the user from the view's text field
            m.setOp2(Model.stringToPoly(v.getOp2()));
            Polynomial additionResult = Operations.add(m.getOp1(),m.getOp2());
            String res = Model.polyToString(additionResult);
            m.setResult(res);
            v.setResult(res);
        }
    }

    class SubListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            m.setOp1(Model.stringToPoly(v.getOp1())); // v.getOp1() gets the string polynomial operand inserted by the user from the view's text field
            m.setOp2(Model.stringToPoly(v.getOp2()));
            Polynomial subResult = Operations.sub(m.getOp1(),m.getOp2());
            String res = Model.polyToString(subResult);
            m.setResult(res);
            v.setResult(res);
        }
    }

    class MultiplyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            m.setOp1(Model.stringToPoly(v.getOp1())); // v.getOp1() gets the string polynomial operand inserted by the user, from the view's text field
            m.setOp2(Model.stringToPoly(v.getOp2()));
            Polynomial mulResult =  Model.simplifyPolynomial(Operations.multiply(m.getOp1(),m.getOp2())); // the static method declared in the datamodels.Model class simplifies the addition result
            String res = Model.polyToString(mulResult);
            m.setResult(res);
            v.setResult(res);
        }
    }

    class DerivListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            m.setOp1(Model.stringToPoly(v.getOp1())); // v.getOp1() gets the string polynomial operand inserted by the user, from the view's text field
            m.setOp2(Model.stringToPoly(v.getOp2()));
            Polynomial derivResultOp1 =  Model.simplifyPolynomial(Operations.deriv(m.getOp1())); // the static method declared in the datamodels.Model class simplifies the addition result
            Polynomial derivResultOp2 =  Model.simplifyPolynomial(Operations.deriv(m.getOp2()));
            String res1 = Model.polyToString(derivResultOp1);
            String res2 = Model.polyToString(derivResultOp2);
            m.setResult(res1 +"\r\n" + res2);
            v.setResult(res1 +"\r\n" + res2);
        }
    }

    class InegrateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            m.setOp1(Model.stringToPoly(v.getOp1())); // v.getOp1() gets the string polynomial operand inserted by the user, from the view's text field
            m.setOp2(Model.stringToPoly(v.getOp2()));
            Polynomial derivResultOp1 =  Model.simplifyDoublePolynomial(Operations.integrate(m.getOp1())); // the static method declared in the datamodels.Model class simplifies the addition result
            Polynomial derivResultOp2 =  Model.simplifyDoublePolynomial(Operations.integrate(m.getOp2()));
            String res1 = Model.doublePolyToString(derivResultOp1);
            String res2 = Model.doublePolyToString(derivResultOp2);
            m.setResult(res1 +"\r\n" + res2);
            v.setResult(res1 +"\r\n" + res2);
        }
    }

    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           v.reset();
        }
    }

    class DivideListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            m.setOp1(Model.stringToPoly(v.getOp1())); // v.getOp1() gets the string polynomial operand inserted by the user, from the view's text field
            m.setOp2(Model.stringToPoly(v.getOp2()));
            Polynomial divResult =  Operations.devide(m.getOp1(),m.getOp2()); // the static method declared in the datamodels.Model class simplifies the addition result
            String res = Model.polyToString(divResult);
            m.setResult(res);
            v.setResult(res);
        }
    }

    class InputPolynomialListener implements DocumentListener {
        @Override
        public void changedUpdate(DocumentEvent e) {
            warn();
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
            warn();
        }
        @Override
        public void insertUpdate(DocumentEvent e) {
            warn();
        }
        public void warn() {
            if (!Model.checkInputString(v.getOp1()) || !Model.checkInputString(v.getOp2())){
                JOptionPane.showMessageDialog(null,
                        "Error: Please enter a polynomial of variable x, with no white spaces.", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    };
}
