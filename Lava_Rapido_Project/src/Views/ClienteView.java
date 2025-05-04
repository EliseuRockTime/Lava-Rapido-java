/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import Controllers.ClienteController;
import Models.Cliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Elise
 */

public class ClienteView extends JFrame {
    private ClienteController controller;
    private JTable tabelaClientes;
    private DefaultTableModel tableModel;

    public ClienteView(ClienteController controller) {
        this.controller = controller;
        initComponents();
        carregarClientes();
    }

    private void initComponents() {
        setTitle("Gestão de Clientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel(new BorderLayout());

        // Tabela de clientes
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "CPF", "Telefone", "E-mail", "Placa Veículo"}, 0);
        tabelaClientes = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaClientes);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        btnAdicionar.addActionListener(e -> abrirFormularioCliente(null));
        btnEditar.addActionListener(e -> editarCliente());
        btnExcluir.addActionListener(e -> excluirCliente());

        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnExcluir);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void carregarClientes() {
        tableModel.setRowCount(0);
        List<Cliente> clientes = controller.listarClientes();
        for (Cliente cliente : clientes) {
            tableModel.addRow(new Object[]{
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getPlacaVeiculo()
            });
        }
    }

    private void abrirFormularioCliente(Cliente cliente) {
        JDialog dialog = new JDialog(this, "Formulário de Cliente", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(6, 2));

        JTextField txtNome = new JTextField();
        JTextField txtCpf = new JTextField();
        JTextField txtTelefone = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtPlaca = new JTextField();

        if (cliente != null) {
            txtNome.setText(cliente.getNome());
            txtCpf.setText(cliente.getCpf());
            txtTelefone.setText(cliente.getTelefone());
            txtEmail.setText(cliente.getEmail());
            txtPlaca.setText(cliente.getPlacaVeiculo());
        }

        dialog.add(new JLabel("Nome:"));
        dialog.add(txtNome);
        dialog.add(new JLabel("CPF:"));
        dialog.add(txtCpf);
        dialog.add(new JLabel("Telefone:"));
        dialog.add(txtTelefone);
        dialog.add(new JLabel("E-mail:"));
        dialog.add(txtEmail);
        dialog.add(new JLabel("Placa Veículo:"));
        dialog.add(txtPlaca);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            if (cliente == null) {
                controller.cadastrarCliente(
                    txtNome.getText(),
                    txtCpf.getText(),
                    txtTelefone.getText(),
                    txtEmail.getText(),
                    txtPlaca.getText()
                );
            } else {
                controller.editarCliente(
                    cliente.getId(),
                    txtNome.getText(),
                    txtCpf.getText(),
                    txtTelefone.getText(),
                    txtEmail.getText(),
                    txtPlaca.getText()
                );
            }
            carregarClientes();
            dialog.dispose();
        });

        dialog.add(new JLabel());
        dialog.add(btnSalvar);
        dialog.setVisible(true);
    }

    private void editarCliente() {
        int selectedRow = tabelaClientes.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Cliente cliente = controller.buscarClientePorId(id);
            abrirFormularioCliente(cliente);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para editar.");
        }
    }

    private void excluirCliente() {
        int selectedRow = tabelaClientes.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(
                this, 
                "Tem certeza que deseja excluir este cliente?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                controller.excluirCliente(id);
                carregarClientes();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
        }
    }
}