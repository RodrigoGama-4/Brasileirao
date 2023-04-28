import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Views.AdicionarConfrontoView;
import Views.TimeView;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class PrincipalView extends JFrame {
    private JPanel mainPanel;
    private JButton adicionarJogadorButton;
    private JButton marcarPartidaButton;
    private JButton verTabelaButton;

    public PrincipalView() {
        // Configurações da janela
        setTitle("Minha Aplicação");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 200);
        setResizable(false);

        // Criação do painel principal
        mainPanel = new JPanel(new BorderLayout());

        // Criação dos botões
        adicionarJogadorButton = new JButton("Adicionar Jogador");
        marcarPartidaButton = new JButton("Marcar Partida");
        verTabelaButton = new JButton("Consultar BD");

        // Configuração do layout dos botões
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        buttonPanel.setPreferredSize(new Dimension(200, 120)); // Define tamanho do painel
        buttonPanel.add(adicionarJogadorButton);
        buttonPanel.add(marcarPartidaButton);
        buttonPanel.add(verTabelaButton);

        // Adiciona o painel de botões ao painel principal
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Adiciona o painel principal à janela
        add(mainPanel);

        // Torna a janela visível
        setVisible(true);



        //FECHA JANELA PRINCIPAL E ABRE JANELA DE ADICIONAR JOGADOR
        adicionarJogadorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                TimeView time = new TimeView();
            }
            
        });

        //FECHA JANELA PRINCIPAL E ABRE A JANELA DE ADICIONAR CONFRONTO
        marcarPartidaButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdicionarConfrontoView add = new AdicionarConfrontoView();
                add.setVisible(true);
            }
            
        });

        /*verTabelaButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //FAZER VIEW 
            }
            
        });*/
    }

    public static void main(String[] args) {
        PrincipalView mainView = new PrincipalView();
    }
}
