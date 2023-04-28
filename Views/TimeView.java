package Views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class TimeView extends JFrame {
    private JPanel panel;
    private JLabel nameLabel;
    private JLabel jogadorLabel;
    private JLabel posicaoLabel;
    private JLabel idadeLabel;
    private JTextField nomeField;
    private JTextField jogadorField;
    private JTextField posicaoField;
    private JTextField idadeField;

    public TimeView() {
        // Configurações da janela
        setTitle("Cadastro de Time");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        // Criação do painel
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets.bottom = 5;

        // Label e campo de texto para o nome do time
        nameLabel = new JLabel("Nome do Time:");
        constraints.gridx = 0;
        panel.add(nameLabel, constraints);

        nomeField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(nomeField, constraints);

        // Label e campo de texto para o jogador
        jogadorLabel = new JLabel("Jogador:");
        constraints.gridx = 0;
        panel.add(jogadorLabel, constraints);

        jogadorField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(jogadorField, constraints);

        // Label e campo de texto para a posição
        posicaoLabel = new JLabel("Posição:");
        constraints.gridx = 0;
        panel.add(posicaoLabel, constraints);

        posicaoField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(posicaoField, constraints);

        // Label e campo de texto para a idade
        idadeLabel = new JLabel("Idade:");
        constraints.gridx = 0;
        panel.add(idadeLabel, constraints);

        idadeField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(idadeField, constraints);

        // Adiciona o painel à janela
        add(panel);

        // Torna a janela visível
        setVisible(true);
    }

    public static void main(String[] args) {
        TimeView timeView = new TimeView();
    }
}
