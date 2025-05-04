/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import Controllers.TipoServicoController;
import Models.TipoServico;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Elise
 */

public class TipoServicoView extends JFrame {
    private TipoServicoController controller;
    private JTable tabelaTiposServico;
    private DefaultTableModel tableModel;

    public TipoServicoView(TipoServicoController controller) {
        this.controller = controller;
        initComponents();
        carregarTiposServico();
    }

    private void initComponents() {
        setTitle("Gestão de Tipos de Serviço");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel(new BorderLayout());

        // Tabela de tipos de serviço
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Descrição", "Preço"}, 0);
        tabelaTiposServico = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaTiposServico);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(e -> abrirFormularioTipoServico(null));
        buttonPanel.add(btnAdicionar);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void carregarTiposServico() {
        tableModel.setRowCount(0);
        List<TipoServico> tipos = controller.listarTiposServico();
        for (TipoServico tipo : tipos) {
            tableModel.addRow(new Object[]{
                tipo.getId(),
                tipo.getNome(),
                tipo.getDescricao(),
                String.format("R$ %.2f", tipo.getPreco())
            });
        }
    }

    private void abrirFormularioTipoServico(TipoServico tipoServico) {
        JDialog dialog = new JDialog(this, "Formulário de Tipo de Serviço", true);
        dialog.setSize(400, 250);
        dialog.setLayout(new GridLayout(4, 2));

        JTextField txtNome = new JTextField();
        JTextField txtDescricao = new JTextField();
        JTextField txtPreco = new JTextField();

        if (tipoServico != null) {
            txtNome.setText(tipoServico.getNome());
            txtDescricao.setText(tipoServico.getDescricao());
            txtPreco.setText(String.valueOf(tipoServico.getPreco()));
        }

        dialog.add(new JLabel("Nome:"));
        dialog.add(txtNome);
        dialog.add(new JLabel("Descrição:"));
        dialog.add(txtDescricao);
        dialog.add(new JLabel("Preço:"));
        dialog.add(txtPreco);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            try {
                double preco = Double.parseDouble(txtPreco.getText());
                controller.cadastrarTipoServico(
                    txtNome.getText(),
                    txtDescricao.getText(),
                    preco
                );
                carregarTiposServico();
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Preço inválido. Use números com ponto decimal.");
            }
        });

        dialog.add(new JLabel());
        dialog.add(btnSalvar);
        dialog.setVisible(true);
    }
}