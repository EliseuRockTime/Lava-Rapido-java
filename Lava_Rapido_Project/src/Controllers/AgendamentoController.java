/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Agendamento;
import Models.Cliente;
import Models.TipoServico;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoController {

    private final List<Agendamento> agendamentos = new ArrayList<>();
    private int proximoId = 1;

    public AgendamentoController(ClienteController clienteController, TipoServicoController tipoServicoController) {
        // Inicialização, se necessário
    }

    public List<Agendamento> listarAgendamentos() {
        return agendamentos;
    }

    public void agendarServico(Cliente cliente, TipoServico tipoServico, LocalDateTime dataHora) {
        Agendamento novoAgendamento = new Agendamento(proximoId++, cliente, tipoServico, dataHora, "Agendado");
        agendamentos.add(novoAgendamento);
    }

    public void editarAgendamento(int id, Cliente cliente, TipoServico tipoServico, LocalDateTime dataHora, String status) {
        for (Agendamento ag : agendamentos) {
            if (ag.getId() == id) {
                ag.setCliente(cliente);
                ag.setTipoServico(tipoServico);
                ag.setDataHora(dataHora);
                ag.setStatus(status);
                break;
            }
        }
    }

    public void cancelarAgendamento(int id) {
        agendamentos.removeIf(ag -> ag.getId() == id);
    }

    public Agendamento buscarAgendamentoPorId(int id) {
        for (Agendamento ag : agendamentos) {
            if (ag.getId() == id) {
                return ag;
            }
        }
        return null;
    }
}
