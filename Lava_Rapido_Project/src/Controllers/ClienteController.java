package Controllers;

import Models.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    private List<Cliente> clientes = new ArrayList<>();
    private int proximoId = 1;

    public ClienteController() {
        // Exemplo: cliente inicial para teste
        clientes.add(new Cliente(proximoId++, "João da Silva", "123.456.789-00", "11999999999", "joao@email.com", "ABC1234"));
    }

    public void cadastrarCliente(String nome, String cpf, String telefone, String email, String placaVeiculo) {
        Cliente novoCliente = new Cliente(proximoId++, nome, cpf, telefone, email, placaVeiculo);
        clientes.add(novoCliente);
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public void editarCliente(int id, String nome, String cpf, String telefone, String email, String placaVeiculo) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                cliente.setNome(nome);
                cliente.setCpf(cpf);
                cliente.setTelefone(telefone);
                cliente.setEmail(email);
                cliente.setPlacaVeiculo(placaVeiculo);
                return;
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
