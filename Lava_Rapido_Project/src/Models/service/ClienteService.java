/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.service;

import Models.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elise
 */

public class ClienteService {
    private List<Cliente> clientes = new ArrayList<>();
    private int nextId = 1;

    public void cadastrarCliente(String nome, String cpf, String telefone, String email, String placaVeiculo) {
        Cliente cliente = new Cliente(nextId++, nome, cpf, telefone, email, placaVeiculo);
        clientes.add(cliente);
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    public void editarCliente(int id, String nome, String cpf, String telefone, String email, String placaVeiculo) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                cliente.setNome(nome);
                cliente.setCpf(cpf);
                cliente.setTelefone(telefone);
                cliente.setEmail(email);
                cliente.setPlacaVeiculo(placaVeiculo);
                break;
            }
        }
    }

    public void excluirCliente(int id) {
        clientes.removeIf(cliente -> cliente.getId() == id);
    }

    public Cliente buscarClientePorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }
}
