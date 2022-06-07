package paint;

import controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    private final MainFrame frame;
    private String[] towns;
    private ClientController clientController = new ClientController();
    private JLabel label;
    private JButton createBtn;
    private JComboBox<String> jComboBox;
    private String selectedTown;
    private boolean firstDraw = true;


    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        this.towns = clientController.getTowns().toArray(new String[0]);
        init();
    }

    private void init() {
        label = new JLabel("Choose town:");
        label.setBackground(Color.CYAN);
        jComboBox = new JComboBox<>(towns);
        jComboBox.setBounds(80, 50, 300, 20);
        createBtn = new JButton("Choose");
        add(label);
        add(jComboBox);
        add(createBtn);
         createBtn.addActionListener(e -> {
             if (Boolean.TRUE.equals(firstDraw)) {
                 frame.remove(frame.label);
                 frame.add(frame.canvas, BorderLayout.CENTER);
                 firstDraw =false;
             }
             String selectedTownMsg = "You selected " + jComboBox.getItemAt(jComboBox.getSelectedIndex());
             label.setText(selectedTownMsg);
             selectedTown = jComboBox.getItemAt(jComboBox.getSelectedIndex());
             frame.canvas.init();
             frame.canvas.drawRoute(selectedTown);
         });
    }
}