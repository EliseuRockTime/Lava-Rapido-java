/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import Controllers.AgendamentoController;
import Controllers.ClienteController;
import Controllers.TipoServicoController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author Elise
 */

public class TelaPrincipalView extends JFrame {
    public TelaPrincipalView(ClienteController clienteController, 
                           TipoServicoController tipoServicoController,
                           AgendamentoController agendamentoController) {
        
        setTitle("Sistema de Gestão - Lava Rápido");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Botões
        JButton btnClientes = new JButton("Clientes");
        JButton btnServicos = new JButton("Tipos de Serviço");
        JButton btnAgendamentos = new JButton("Agendamentos");
        JButton btnSair = new JButton("Sair");

        // Ações dos botões
        btnClientes.addActionListener(e -> {
            new ClienteView(clienteController).setVisible(true);
        });

        btnServicos.addActionListener(e -> {
            new TipoServicoView(tipoServicoController).setVisible(true);
        });

        btnAgendamentos.addActionListener(e -> {
            new AgendamentoView(agendamentoController, clienteController, tipoServicoController).setVisible(true);
        });

        btnSair.addActionListener(e -> System.exit(0));

        // Adicionando componentes
        panel.add(btnClientes);
        panel.add(btnServicos);
        panel.add(btnAgendamentos);
        panel.add(btnSair);

        add(panel);
    }
}