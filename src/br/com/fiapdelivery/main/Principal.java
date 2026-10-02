package br.com.fiapdelivery.main;

import br.com.fiapdelivery.model.Caminhao;
import br.com.fiapdelivery.model.Moto;
import br.com.fiapdelivery.model.Pacote;
import br.com.fiapdelivery.model.Rota;
import br.com.fiapdelivery.model.StatusPacote;

/**
 * Ponto de entrada do FiapDelivery.
 * <p>
 * Simula entregas com veículos diferentes e mostra que os dados inválidos
 * aceitos pelo código legado agora são bloqueados.
 */
public class Principal {

    private Principal() {
    }

    /**
     * Executa a simulação de entregas.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        Caminhao caminhao = new Caminhao("ABC1234", 5000.0, 3);
        Moto moto = new Moto("BRA2E19", 25.0, true);

        System.out.println("=== Entregas ===");

        Rota rotaDeCaminhao = new Rota(new Pacote("BR999", 10.5), caminhao);
        rotaDeCaminhao.iniciarEntrega();
        rotaDeCaminhao.concluirEntrega();

        Rota rotaDeMoto = new Rota(new Pacote("BR1000", 2.0), moto);
        rotaDeMoto.iniciarEntrega();
        System.out.println("Situação atual: " + rotaDeMoto.getPacote());

        System.out.println();
        System.out.println("=== Dados inválidos bloqueados ===");

        tentar("Caminhão com capacidade negativa", () -> new Caminhao("ABC1234", -500.0, 3));
        tentar("Pacote mais pesado do que a moto suporta", () -> new Rota(new Pacote("BR2000", 80.0), moto));
        tentar("Concluir entrega que nem começou", () -> new Rota(new Pacote("BR3000", 1.0), moto).concluirEntrega());
        tentar("Voltar pacote entregue para pendente",
                () -> rotaDeCaminhao.getPacote().atualizarStatus(StatusPacote.PENDENTE));
    }

    private static void tentar(String cenario, Runnable acao) {
        try {
            acao.run();
            System.out.println("[ACEITO]    " + cenario);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("[BLOQUEADO] " + cenario + " -> " + e.getMessage());
        }
    }
}
