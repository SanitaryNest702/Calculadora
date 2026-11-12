package vista;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import modelo.CalcModel;

public class CalcView extends JFrame {
    //... Constants
    private static final String INITIAL_VALUE = "0"; //Correccion: antes era "0", Con 0 siempre daba un resultado 0

    //... Components
    private JTextField m_userInputTf = new JTextField(5);
    private JTextField m_totalTf     = new JTextField(20);
    private JButton   m_sumaBtn     = new JButton("Sumar");
    private JButton    m_multiplyBtn = new JButton("Multiply");
    private JButton    m_clearBtn    = new JButton("Clear");
    private CalcModel m_model;


    //============================================================== constructor
    /** Constructor */
    public CalcView(CalcModel model) {
        //... Set up the logic
        m_model = model;
        //... Initialize components
        m_totalTf.setText(INITIAL_VALUE);
        m_totalTf.setEditable(false);

        //... Layout the components.
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(new JLabel("Input"));
        content.add(m_userInputTf);
        content.add(m_multiplyBtn);
        //Boton para sumar
        content.add(m_sumaBtn);
        content.add(new JLabel("Total"));
        content.add(m_totalTf);
        content.add(m_clearBtn);
        //... finalize layout
        this.setContentPane(content);
        this.pack();

        this.setTitle("Simple Calc - MVC");
        // The window closing event should probably be passed to the
        // Controller in a real program, but this is a short example.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void reset() {
        m_totalTf.setText(INITIAL_VALUE);
    }

    public String getUserInput() {
        return m_userInputTf.getText();
    }

    public void setTotal(String newTotal) {
        m_totalTf.setText(newTotal);
    }

    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }

    public void addMultiplyListener(ActionListener mal) {
        m_multiplyBtn.addActionListener(mal);
    }

    public void addClearListener(ActionListener cal) {
        m_clearBtn.addActionListener(cal);
    }

    public void addSumaListener(ActionListener sal){
        m_sumaBtn.addActionListener(sal);
    }
}
