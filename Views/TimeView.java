package Views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.TimeController;
import Dao.JogadorDao;
import Dao.JogadorDaoInterface;
import Dao.TimeDao;
import Dao.TimeDaoInterface;
//import Dao.BancoDadosController;
import Model.Jogador;
import Model.Time;
import Model.TimeFactory;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeView extends JFrame {
    private JPanel panel;
    private JLabel nameLabel;
    private JLabel jogadorLabel;
    private JLabel posicaoLabel;
    private JLabel idadeLabel;
    private JTextField nomeField;
    private JTextField jogadorField;
    private JTextField posicaoField;
    private JTextField numeroField;
    private JButton adicionarButton;
    private JButton voltarButton;

    public TimeView(TimeController timec, PrincipalView princi) {
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
        idadeLabel = new JLabel("Numero:");
        constraints.gridx = 0;
        panel.add(idadeLabel, constraints);

        numeroField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(numeroField, constraints);

        //BOTAO ADICIONAR
        adicionarButton = new JButton("Adicionar Jogador");
        constraints.gridy = 4;
        panel.add(adicionarButton, constraints);

        //BOTAO VOLTAR
        voltarButton = new JButton("VOLTAR");
        constraints.gridy = 4;
        constraints.gridx = 0;
        panel.add(voltarButton, constraints);

        // Adiciona o painel à janela
        add(panel);

        // Torna a janela visível
        setVisible(true);


        //INSTANCIO O TIMECONTROLLER    
        //TimeController timec = new TimeController();

        //BANCO DE DADOS
        //BancoDadosController bd = new BancoDadosController();
        JogadorDaoInterface bd = new JogadorDao();
        TimeDaoInterface bdTime = new TimeDao();

        //AÇOES BOTAO ADICIONAR
        adicionarButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Time time =  TimeFactory.createTime(nomeField.getText(), timec.times);
                Jogador jogador = new Jogador(jogadorField.getText(), posicaoField.getText(), Integer.parseInt(numeroField.getText()), time);
                timec.adicionarJogador(time, jogador);
                System.out.println("O jogador " + jogador.getNome() + " foi adicionado ao time " + time.getNome() + " com sucesso!");
                bd.adicionarJogador(jogador, bd.obterProximoIdJogador() ,bdTime.obterIdOuCriarTime(time));
             


                //Apagando texto anterior
                nomeField.setText("");
                jogadorField.setText("");
                posicaoField.setText("");
                numeroField.setText("");

                
            }
        });

        //AÇÕES VOLTAR BOTAO
        voltarButton.addActionListener(new ActionListener() {
            
			@Override
			public void actionPerformed(ActionEvent e) {
                setVisible(false);
                princi.setVisible(true);
                
			}
        });
        
    }
}
