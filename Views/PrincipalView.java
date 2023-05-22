package Views;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.BancoDadosController;
import Controller.CampeonatoController;
import Controller.TimeController;
import Model.Time;

import java.util.Scanner;


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
        TimeController timc = new TimeController();


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
        verTabelaButton = new JButton("DADOS DO CAMPEONATO");

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


        //Cria instancia padrao para passar para as outras views
        PrincipalView instanciaPrincipal = this;

        //INSTANCIA PADRAO SINGLETON DE CAMPEONATO
        CampeonatoController campC = CampeonatoController.getInstance();


        //FECHA JANELA PRINCIPAL E ABRE JANELA DE ADICIONAR JOGADOR
        adicionarJogadorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                TimeView time = new TimeView(timc, instanciaPrincipal);
            }
            
        });

        //FECHA JANELA PRINCIPAL E ABRE A JANELA DE ADICIONAR CONFRONTO
        marcarPartidaButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                AdicionarConfrontoView add = new AdicionarConfrontoView(timc, instanciaPrincipal);
            }
            
        });

        //Instancia do scanner
        Scanner scanner = new Scanner(System.in);

        BancoDadosController bd = new BancoDadosController();

        //MOSTRA OS DADOS DO CAMPEONATO
        verTabelaButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                bd.mostrarTimes();
                bd.mostrarConfrontos();
                /*System.out.println("------------------------LISTA DE TODOS OS TIMES ADICIONADOS------------------------");
                campC.listarTimes();
                System.out.println("------------------------LISTA DE TODOS OS CONFRONTOS DO CAMPEONATO------------------------");
                campC.listarConfrontos();

                //OPÇÃO DE MOSTRAR OS NOMES DOS JOGADORES DO TIME
                System.out.println("Voce tem uma opção de visualizar todos os jogadores de um time, você deseja? [S/N]");
                String aceita = scanner.next();

                while (aceita.equals("S") || aceita.equals("s")){
                    System.out.println("Qual time voce deseja ver os jogadores?: ");
                    String qualTime = scanner.next();
                    for (Time times: campC.getTimes()){
                        if (qualTime.equals(times.getNome())){
                            times.getJogadores();
                        }
                    }
                    System.out.println("Voce tem uma opção de visualizar todos os jogadores de um time, você deseja? [S/N]");
                    aceita = scanner.next();
                }
                System.out.println("Programa finalziado");*/
            }
            
        });
    }

    public static void main(String[] args) {
        PrincipalView mainView = new PrincipalView();
    }
}
