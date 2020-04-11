package view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GateView extends JPanel implements ActionListener {
    private final Gate gate;

    private final JCheckBox inputA;
    private final JCheckBox inputB;
    private final JCheckBox output;
    private final Switch sinalA;
    private final Switch sinalB;

    public GateView(Gate gate) {
        this.gate = gate;

        inputA = new JCheckBox("A");
        inputB = new JCheckBox("B");
        output = new JCheckBox("Output");

        sinalA = new Switch();
        sinalB = new Switch();

        JLabel entrada = new JLabel("Entrada: ");
        JLabel saida = new JLabel("Saída: ");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        if (gate.getInputSize() == 1) {
            add(entrada);
            add(inputA);
            add(saida);
            add(output);
        } else {
            add(entrada);
            add(inputA);
            add(inputB);
            add(saida);
            add(output);
        }

        inputA.addActionListener(this);
        inputB.addActionListener(this);

        output.setEnabled(false);

        update();
    }

    private void update() {

        if (inputA.isSelected()) {
            sinalA.turnOn();
        } else {
            sinalA.turnOff();
        }

        if (inputB.isSelected()) {
            sinalB.turnOn();
        } else {
            sinalB.turnOff();
        }

        if (gate.getInputSize() == 1) {
            gate.connect(0, sinalA);
        } else {
            gate.connect(0, sinalA);
            gate.connect(1, sinalB);
        }

        boolean result = gate.read();

        output.setSelected(result);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }
}
