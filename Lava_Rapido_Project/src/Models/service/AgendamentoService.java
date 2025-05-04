/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.service;

import Models.Agendamento;
import Models.Cliente;
import Models.TipoServico;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Elise
 */

public class AgendamentoService {
    private List<Agendamento> agendamentos = new ArrayList<>();
    private int nextId = 1;

    public void agendarServico(Cliente cliente, TipoServico tipoServico, LocalDateTime dataHora) {
        Agendamento agendamento = new Agendamento(nextId++, cliente, tipoServico, dataHora, "Agendado");
        agendamentos.add(agendamento);
    }

    public List<Agendamento> listarAgendamentos() {
        return new ArrayList<>(agendamentos);
    }

    public void editarAgendamento(int id, Cliente cliente, TipoServico tipoServico, LocalDateTime dataHora, String status) {
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getId() == id) {
                agendamento.setCliente(cliente);
                agendamento.setTipoServico(tipoServico);
                agendamento.setDataHora(dataHora);
                agendamento.setStatus(status);
                break;
            }
        }
    }

    public void cancelarAgendamento(int id) {
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getId() == id) {
                agendamento.setStatus("Cancelado");
                break;
            }
        }
    }

    public List<Agendamento> listarAgendamentosPorData(LocalDateTime data) {
        List<Agendamento> agendamentosDoDia = new ArrayList<>();
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getDataHora().toLocalDate().equals(data.toLocalDate())) {
                agendamentosDoDia.add(agendamento);
            }
        }
        return agendamentosDoDia;
    }
}