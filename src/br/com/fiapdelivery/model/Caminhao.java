package br.com.fiapdelivery.model;

/**
 * Caminhão da frota, indicado para cargas grandes e pesadas.
 * <p>
 * Herda placa e capacidade de carga de {@link Veiculo} e acrescenta a
 * quantidade de eixos.
 */
public class Caminhao extends Veiculo {

    private static final int QUANTIDADE_MINIMA_EIXOS = 2;

    private final int quantidadeEixos;

    /**
     * Cria um caminhão.
     *
     * @param placa             placa no padrão antigo ou Mercosul
     * @param capacidadeCargaKg carga máxima suportada, em quilogramas; deve ser maior que zero
     * @param quantidadeEixos   quantidade de eixos; mínimo de 2
     * @throws IllegalArgumentException se algum dos valores for inválido
     */
    public Caminhao(String placa, double capacidadeCargaKg, int quantidadeEixos) {
        super(placa, capacidadeCargaKg);
        if (quantidadeEixos < QUANTIDADE_MINIMA_EIXOS) {
            throw new IllegalArgumentException(
                    "Um caminhão precisa de pelo menos " + QUANTIDADE_MINIMA_EIXOS + " eixos. Valor recebido: "
                            + quantidadeEixos);
        }
        this.quantidadeEixos = quantidadeEixos;
    }

    /** {@return quantidade de eixos do caminhão} */
    public int getQuantidadeEixos() {
        return quantidadeEixos;
    }

    @Override
    public String getTipo() {
        return "Caminhão";
    }
}
