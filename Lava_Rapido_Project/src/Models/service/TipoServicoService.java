/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models.service;

import Models.TipoServico;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Elise
 */

public class TipoServicoService {
    private List<TipoServico> tiposServico = new ArrayList<>();
    private int nextId = 1;

    public void cadastrarTipoServico(String nome, String descricao, double preco) {
        TipoServico tipo = new TipoServico(nextId++, nome, descricao, preco);
        tiposServico.add(tipo);
    }

    public List<TipoServico> listarTiposServico() {
        return new ArrayList<>(tiposServico);
    }

    public TipoServico buscarTipoServicoPorId(int id) {
        for (TipoServico tipo : tiposServico) {
            if (tipo.getId() == id) {
                return tipo;
            }
        }
        return null;
    }
}