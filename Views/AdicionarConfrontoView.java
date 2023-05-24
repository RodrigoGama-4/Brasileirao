package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import Controller.TimeController;
import Dao.BancoDadosController;

public class AdicionarConfrontoView extends JFrame {

    private JLabel labelTime1;
    private JLabel labelTime2;
    private JTextField inputTime1;
    private JTextField inputTime2;
    private JButton addConfrontoButton;
    private JButton voltarButton;

    public AdicionarConfrontoView(TimeController timec, PrincipalView princi) {
        // Configurações básicas da janela
        setTitle("Time View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLayout(null);
        setResizable(false);
        setVisible(true);

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
        addConfrontoButton.setBounds(150, 150, 150, 20);
        add(addConfrontoButton);

        voltarButton = new JButton("VOLTAR");
        voltarButton.setBounds(10, 150, 100, 20);
        add(voltarButton);



        //Ações AdiconarConfronto
        addConfrontoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MEU BANCO DE DADOS
                BancoDadosController bd = new BancoDadosController();
                bd.marcarConfronto(inputTime1.getText(), inputTime2.getText(), bd.obterProximoIdConfronto());

                //Apagando texto anterior
                inputTime1.setText("");
                inputTime2.setText("");
            }
            
        });

        //Botao voltar
        voltarButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
                princi.setVisible(true);
			}
            
        });

    }
}
