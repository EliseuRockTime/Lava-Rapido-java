/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lava_rapido_project;

import Controllers.AgendamentoController;
import Controllers.ClienteController;
import Controllers.TipoServicoController;
import Views.TelaPrincipalView;
import javax.swing.SwingUtilities;


/**
 *
 * @author Elise
 */
public class Lava_Rapido_Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Inicializa controllers
        ClienteController clienteController = new ClienteController();
        TipoServicoController tipoServicoController = new TipoServicoController();
        AgendamentoController agendamentoController = new AgendamentoController(
            clienteController, 
            tipoServicoController
        );
        
         // Cria e exibe a tela principal
        SwingUtilities.invokeLater(() -> {
            TelaPrincipalView tela = new TelaPrincipalView(
                clienteController,
                tipoServicoController,
                agendamentoController
            );
            tela.setVisible(true);
        });
    }
}
