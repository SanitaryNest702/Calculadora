package modelo;

import java.math.BigDecimal;

public class CalcModel {
    //... Constants
    // CORRECCION: antes era "0". Con 0, despues de pulsar Clear el total quedaba en 0
    // y cualquier multiplicacion daba 0. El 1 es el elemento neutro de la multiplicacion.
    private static final String INITIAL_VALUE = "1";

    //... Member variable defining state of calculator.
    private BigDecimal m_total;

    private static final String INITIAL_SUMA= "0"; //0 es el valor neutro de la suma
    private boolean m_primeraOperacion;  // The total current value state.

    //============================================================== constructor
    /** Constructor */
    public CalcModel() {
        reset();
    }

    //==================================================================== reset
    /** Reset to initial value. */
    public void reset() {
        m_total = new BigDecimal(INITIAL_VALUE);
        //Agregar el booleano
        m_primeraOperacion=true;

    }

    //=============================================================== multiplyBy
    /** Multiply current total by a number.
     *@param operand Number (as string) to multiply total by.
     */
    public void multiplyBy(String operand) {
        m_total = m_total.multiply(new BigDecimal(operand));
        m_primeraOperacion=false;

    }



    //================================================================== setValue
    /** Set the total value.
     *@param value New value that should be used for the calculator total.
     */
    public void setValue(String value) {
        m_total = new BigDecimal(value);
    }

    //================================================================== getValue
    /** Return current calculator total. */
    public String getValue() {
        return m_total.toString();
    }
        public void addBy(String operand){
        BigDecimal base= m_primeraOperacion ? new BigDecimal(INITIAL_SUMA): m_total;
        m_total=base.add(new BigDecimal(operand));
        m_primeraOperacion=false;
    }

}
