package GUI;

import datamodels.Model;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Font;

public class View extends JFrame {

    private Model model;
    private JButton addBtn = new JButton("Add");
    private JButton subBtn = new JButton("Subtract");
    private JButton mulBtn = new JButton("Multiply");
    private JButton divBtn = new JButton("Devide");
    private JButton derivativeBtn = new JButton("Derivate");
    private JButton integrateBtn = new JButton("Integrate");
    private JTextField op1;
    private JTextField op2;
    private JTextArea result;
    private JButton resetBtn = new JButton("Reset");

    public View(Model model) {
        this.model = model;
        getContentPane().setBackground(new Color(143, 188, 143));
        initialize();
    }

    private void initialize() {

        this.setBounds(100, 100, 626, 458);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        addBtn.setFont(new Font("Tahoma", Font.BOLD, 10));

        addBtn.setBounds(31, 179, 85, 21);
        this.getContentPane().add(addBtn);

        subBtn.setFont(new Font("Tahoma", Font.BOLD, 10));

        subBtn.setBounds(126, 179, 85, 21);
        this.getContentPane().add(subBtn);
        mulBtn.setFont(new Font("Tahoma", Font.BOLD, 10));

        mulBtn.setBounds(221, 179, 85, 21);
        this.getContentPane().add(mulBtn);
        divBtn.setFont(new Font("Tahoma", Font.BOLD, 10));

        divBtn.setBounds(316, 179, 85, 21);
        this.getContentPane().add(divBtn);
        derivativeBtn.setFont(new Font("Tahoma", Font.BOLD, 10));

        derivativeBtn.setBounds(411, 179, 85, 21);
        this.getContentPane().add(derivativeBtn);
        integrateBtn.setFont(new Font("Tahoma", Font.BOLD, 10));

        integrateBtn.setBounds(506, 179, 85, 21);
        this.getContentPane().add(integrateBtn);

        op1 = new JTextField();
        op1.setBounds(55, 121, 219, 19);
        getContentPane().add(op1);
        op1.setColumns(10);

        op2 = new JTextField();
        op2.setColumns(10);
        op2.setBounds(345, 121, 219, 19);
        getContentPane().add(op2);

        JLabel lblOperand1 = new JLabel("Insert polynomial");
        lblOperand1.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblOperand1.setBounds(126, 98, 100, 13);
        getContentPane().add(lblOperand1);

        JLabel lblOperand2 = new JLabel("Insert polynomial");
        lblOperand2.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblOperand2.setBounds(425, 98, 100, 13);
        getContentPane().add(lblOperand2);

        JLabel lblResult = new JLabel("Result");
        lblResult.setHorizontalAlignment(SwingConstants.CENTER);
        lblResult.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblResult.setBounds(272, 263, 85, 13);
        getContentPane().add(lblResult);

        result = new JTextArea();
        result.setBackground(Color.WHITE);
        result.setEditable(false);
        result.setBounds(126, 286, 370, 34);
        getContentPane().add(result);
        result.setColumns(10);

        JLabel lblTitle = new JLabel("POLYNOMIAL CALCULATOR");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTitle.setBounds(193, 28, 259, 13);
        getContentPane().add(lblTitle);
        resetBtn.setFont(new Font("Tahoma", Font.BOLD, 10));
        resetBtn.setBounds(272, 213, 85, 21);

        getContentPane().add(resetBtn);
    }

    public String getOp1() {
        return op1.getText();
    }


    public String getOp2() {
        return op2.getText();
    }


    public String getResult() {
        return result.getText();
    }

    public void setResult(String s) {
        this.result.setText(s);
    }

    public void setOp1(String s) {
        this.op1.setText(s);
    }

    public void setOp2(String s) {
        this.op2.setText(s);
    }

    void addBtnListener(ActionListener a) { addBtn.addActionListener(a); }

    void subBtnListener(ActionListener a) {
        subBtn.addActionListener(a);
    }

    void mulBtnListener(ActionListener a) {
        mulBtn.addActionListener(a);
    }

    void divBtnListener(ActionListener a) {
        divBtn.addActionListener(a);
    }

    void derivativeBtnListener(ActionListener a) {
        derivativeBtn.addActionListener(a);
    }

    void integrateBtnListener(ActionListener a) {
        integrateBtn.addActionListener(a);
    }

    void resetBtnListener(ActionListener a) {
        resetBtn.addActionListener(a);
    }

    void documentListenerForOp1(DocumentListener d){ op1.getDocument().addDocumentListener(d);}

    void documentListenerForOp2(DocumentListener d){ op2.getDocument().addDocumentListener(d);}

    void reset() {
        this.op1.setText("");
        this.op2.setText("");
        this.result.setText("");
    }

}
