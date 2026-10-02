package br.com.fiapdelivery.model;

import java.util.Objects;

/**
 * Rota de entrega que liga um {@link Pacote} ao {@link Veiculo} que vai
 * transportá-lo.
 * <p>
 * A rota depende da abstração {@code Veiculo}, e não de um tipo específico.
 * Assim, qualquer veículo da frota (caminhão, moto ou um tipo criado no
 * futuro) pode fazer a entrega sem que esta classe precise mudar.
 */
public class Rota {

    private final Pacote pacote;
    private final Veiculo veiculo;

    /**
     * Cria uma rota de entrega.
     *
     * @param pacote  pacote a ser entregue
     * @param veiculo veículo responsável pela entrega
     * @throws NullPointerException     se o pacote ou o veículo forem nulos
     * @throws IllegalArgumentException se o pacote for mais pesado do que o veículo suporta
     */
    public Rota(Pacote pacote, Veiculo veiculo) {
        this.pacote = Objects.requireNonNull(pacote, "A rota precisa de um pacote.");
        this.veiculo = Objects.requireNonNull(veiculo, "A rota precisa de um veículo.");
        if (!veiculo.suportaPeso(pacote.getPesoKg())) {
            throw new IllegalArgumentException("O veículo " + veiculo + " suporta até " + veiculo.getCapacidadeCargaKg()
                    + " kg e não pode levar o pacote " + pacote.getCodigo() + " (" + pacote.getPesoKg() + " kg).");
        }
    }

    /** {@return pacote transportado nesta rota} */
    public Pacote getPacote() {
        return pacote;
    }

    /** {@return veículo responsável por esta rota} */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * Coloca o pacote em trânsito no veículo da rota.
     *
     * @throws IllegalStateException se o pacote não estiver pendente
     */
    public void iniciarEntrega() {
        pacote.atualizarStatus(StatusPacote.EM_TRANSITO);
        System.out.println("Levando pacote " + pacote.getCodigo() + " no veículo " + veiculo);
    }

    /**
     * Registra que o pacote chegou ao destino.
     *
     * @throws IllegalStateException se o pacote ainda não estiver em trânsito
     */
    public void concluirEntrega() {
        pacote.atualizarStatus(StatusPacote.ENTREGUE);
        System.out.println("Pacote " + pacote.getCodigo() + " entregue pelo veículo " + veiculo);
    }
}
