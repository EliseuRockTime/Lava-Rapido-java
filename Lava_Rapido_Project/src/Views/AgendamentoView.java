/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import Controllers.AgendamentoController;
import Controllers.ClienteController;
import Controllers.TipoServicoController;
import Models.Agendamento;
import Models.Cliente;
import Models.TipoServico;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Elise
 */

public class AgendamentoView extends JFrame {
    private AgendamentoController agendamentoController;
    private ClienteController clienteController;
    private TipoServicoController tipoServicoController;
    private JTable tabelaAgendamentos;
    private DefaultTableModel tableModel;

    public AgendamentoView(AgendamentoController agendamentoController, 
                         ClienteController clienteController, 
                         TipoServicoController tipoServicoController) {
        this.agendamentoController = agendamentoController;
        this.clienteController = clienteController;
        this.tipoServicoController = tipoServicoController;
        initComponents();
        carregarAgendamentos();
    }

    private void initComponents() {
        setTitle("Agenda de Serviços");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel(new BorderLayout());

        // Tabela de agendamentos
        tableModel = new DefaultTableModel(new Object[]{"ID", "Cliente", "Serviço", "Data/Hora", "Status"}, 0);
        tabelaAgendamentos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaAgendamentos);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnAgendar = new JButton("Agendar");
        JButton btnEditar = new JButton("Editar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAgendar.addActionListener(e -> abrirFormularioAgendamento(null));
        btnEditar.addActionListener(e -> editarAgendamento());
        btnCancelar.addActionListener(e -> cancelarAgendamento());

        buttonPanel.add(btnAgendar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnCancelar);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void carregarAgendamentos() {
        tableModel.setRowCount(0);
        List<Agendamento> agendamentos = agendamentoController.listarAgendamentos();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        for (Agendamento agendamento : agendamentos) {
            tableModel.addRow(new Object[]{
                agendamento.getId(),
                agendamento.getCliente().getNome(),
                agendamento.getTipoServico().getNome(),
                agendamento.getDataHora().format(formatter),
                agendamento.getStatus()
            });
        }
    }

    private void abrirFormularioAgendamento(Agendamento agendamento) {
        JDialog dialog = new JDialog(this, "Formulário de Agendamento", true);
        dialog.setSize(500, 350);
        dialog.setLayout(new GridLayout(5, 2));

        // Componentes do formulário
        JComboBox<Cliente> comboClientes = new JComboBox<>();
        JComboBox<TipoServico> comboServicos = new JComboBox<>();
        JTextField txtData = new JTextField();
        JTextField txtHora = new JTextField();

        // Carregar combos
        for (Cliente cliente : clienteController.listarClientes()) {
            comboClientes.addItem(cliente);
        }
        for (TipoServico servico : tipoServicoController.listarTiposServico()) {
            comboServicos.addItem(servico);
        }

        // Preencher campos se for edição
        if (agendamento != null) {
            comboClientes.setSelectedItem(agendamento.getCliente());
            comboServicos.setSelectedItem(agendamento.getTipoServico());
            txtData.setText(agendamento.getDataHora().toLocalDate().toString());
            txtHora.setText(agendamento.getDataHora().toLocalTime().toString());
        }

        dialog.add(new JLabel("Cliente:"));
        dialog.add(comboClientes);
        dialog.add(new JLabel("Serviço:"));
        dialog.add(comboServicos);
        dialog.add(new JLabel("Data (yyyy-MM-dd):"));
        dialog.add(txtData);
        dialog.add(new JLabel("Hora (HH:mm):"));
        dialog.add(txtHora);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            try {
                LocalDateTime dataHora = LocalDateTime.parse(
                    txtData.getText() + "T" + txtHora.getText()
                );
                
                if (agendamento == null) {
                    agendamentoController.agendarServico(
                        (Cliente) comboClientes.getSelectedItem(),
                        (TipoServico) comboServicos.getSelectedItem(),
                        dataHora
                    );
                } else {
                    agendamentoController.editarAgendamento(
                        agendamento.getId(),
                        (Cliente) comboClientes.getSelectedItem(),
                        (TipoServico) comboServicos.getSelectedItem(),
                        dataHora,
                        agendamento.getStatus()
                    );
                }
                carregarAgendamentos();
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Formato de data/hora inválido.");
            }
        });

        dialog.add(new JLabel());
        dialog.add(btnSalvar);
        dialog.setVisible(true);
    }

    private void editarAgendamento() {
        int selectedRow = tabelaAgendamentos.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Agendamento agendamento = agendamentoController.buscarAgendamentoPorId(id);
            abrirFormularioAgendamento(agendamento);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um agendamento para editar.");
        }
    }

    private void cancelarAgendamento() {
        int selectedRow = tabelaAgendamentos.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(
                this, 
                "Tem certeza que deseja cancelar este agendamento?",
                "Confirmar Cancelamento",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                agendamentoController.cancelarAgendamento(id);
                carregarAgendamentos();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um agendamento para cancelar.");
        }
    }
}