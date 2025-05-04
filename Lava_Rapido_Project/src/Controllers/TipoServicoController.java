/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.TipoServico;
import java.util.ArrayList;
import java.util.List;

public class TipoServicoController {

    private List<TipoServico> tiposServico = new ArrayList<>();
    private int proximoId = 1;

    public TipoServicoController() {
        // Dados iniciais opcionais
        tiposServico.add(new TipoServico(proximoId++, "Lavagem Simples", "Lavagem externa rápida", 30.0));
        tiposServico.add(new TipoServico(proximoId++, "Lavagem Completa", "Lavagem externa e interna", 50.0));
    }

    public List<TipoServico> listarTiposServico() {
        return tiposServico;
    }

    public void cadastrarTipoServico(String nome, String descricao, double preco) {
        TipoServico novoTipo = new TipoServico(proximoId++, nome, descricao, preco);
        tiposServico.add(novoTipo);
    }

    public void editarTipoServico(int id, String nome, String descricao, double preco) {
        for (TipoServico tipo : tiposServico) {
            if (tipo.getId() == id) {
                tipo.setNome(nome);
                tipo.setDescricao(descricao);
                tipo.setPreco(preco);
                break;
            }
        }
    }

    public void excluirTipoServico(int id) {
        tiposServico.removeIf(tipo -> tipo.getId() == id);
    }

    public TipoServico buscarPorId(int id) {
        for (TipoServico tipo : tiposServico) {
            if (tipo.getId() == id) {
                return tipo;
            }
        }
        return null;
    }
}
