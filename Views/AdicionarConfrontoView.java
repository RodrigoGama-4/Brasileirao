package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AdicionarConfrontoView extends JFrame {

    private JLabel labelTime1;
    private JLabel labelTime2;
    private JTextField inputTime1;
    private JTextField inputTime2;
    private JButton addConfrontoButton;

    public AdicionarConfrontoView() {
        // Configurações básicas da janela
        setTitle("Time View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(null);

        // Criação dos componentes
        labelTime1 = new JLabel("Time 1:");
        labelTime1.setBounds(50, 50, 50, 20);
        add(labelTime1);

        inputTime1 = new JTextField();
        inputTime1.setBounds(100, 50, 200, 20);
        add(inputTime1);

        JLabel labelX = new JLabel("X");
        labelX.setBounds(150, 80, 20, 20);
        add(labelX);

        labelTime2 = new JLabel("Time 2:");
        labelTime2.setBounds(50, 110, 50, 20);
        add(labelTime2);

        inputTime2 = new JTextField();
        inputTime2.setBounds(100, 110, 200, 20);
        add(inputTime2);

        addConfrontoButton = new JButton("Adiconar Confronto");
        addConfrontoButton.setBounds(100, 150, 200, 20);
        add(addConfrontoButton);



        addConfrontoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
            
        });

    }

    

    public static void main(String[] args) {
        AdicionarConfrontoView timeView = new AdicionarConfrontoView();
        timeView.setVisible(true);
    }
}
