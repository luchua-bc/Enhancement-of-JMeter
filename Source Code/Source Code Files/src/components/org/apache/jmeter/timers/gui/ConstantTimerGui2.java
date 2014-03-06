package org.apache.jmeter.timers.gui;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.timers.ConstantTimer2;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.gui.layout.VerticalLayout;

/**
 * The GUI for ConstantTimer.
 *
 */
public class ConstantTimerGui2 extends AbstractTimerGui {
    private static final long serialVersionUID = 240L;

    /**
     * The default value for the delay.
     */
    private static final String DEFAULT_DELAY = "100";

    private static final String DELAY_FIELD = "Delay Field";

    private JTextField delayField;

    /**
     * No-arg constructor.
     */
    public ConstantTimerGui2() {
        init();
    }

    /**
     * Handle an error.
     *
     * @param e
     *            the Exception that was thrown.
     * @param thrower
     *            the JComponent that threw the Exception.
     */
    public static void error(Exception e, JComponent thrower) {
        JOptionPane.showMessageDialog(thrower, e, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public String getLabelResource() {
        return "constant_timer_title2"; // $NON-NLS-1$
    }

    /**
     * Create the test element underlying this GUI component.
     *
     * @see org.apache.jmeter.gui.JMeterGUIComponent#createTestElement()
     */
    @Override
    public TestElement createTestElement() {
        ConstantTimer2 timer = new ConstantTimer2();
        modifyTestElement(timer);
        return timer;
    }

    /**
     * Modifies a given TestElement to mirror the data in the gui components.
     *
     * @see org.apache.jmeter.gui.JMeterGUIComponent#modifyTestElement(TestElement)
     */
    @Override
    public void modifyTestElement(TestElement timer) {
        this.configureTestElement(timer);
        ((ConstantTimer2) timer).setDelay(delayField.getText());
    }

    /**
     * Configure this GUI component from the underlying TestElement.
     *
     * @see org.apache.jmeter.gui.JMeterGUIComponent#configure(TestElement)
     */
    @Override
    public void configure(TestElement el) {
        super.configure(el);
        delayField.setText(((ConstantTimer2) el).getDelay());
    }

    /**
     * Initialize this component.
     */
    private void init() {
        setLayout(new VerticalLayout(5, VerticalLayout.BOTH, VerticalLayout.TOP));

        setBorder(makeBorder());
        add(makeTitlePanel());

        Box delayPanel = Box.createHorizontalBox();
        JLabel delayLabel = new JLabel(JMeterUtils.getResString("constant_timer_delay"));//$NON-NLS-1$
        delayPanel.add(delayLabel);

        delayField = new JTextField(6);
        delayField.setText(DEFAULT_DELAY);
        delayField.setName(DELAY_FIELD);
        delayPanel.add(delayField);
        add(delayPanel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearGui() {
        delayField.setText(DEFAULT_DELAY);
        super.clearGui();
    }
}