package br.com.fiapdelivery.model;

import java.util.regex.Pattern;

/**
 * Veículo da frota do FiapDelivery.
 * <p>
 * Reúne o que é comum a todo veículo (placa e capacidade de carga). Cada tipo
 * de veículo, como {@link Caminhao} e {@link Moto}, herda desta classe e
 * acrescenta apenas o que é próprio dele. Por ser abstrata, garante que só
 * existam veículos de um tipo concreto.
 */
public abstract class Veiculo {

    /** Aceita o padrão antigo (ABC1234) e o padrão Mercosul (ABC1D23). */
    private static final Pattern FORMATO_PLACA = Pattern.compile("[A-Z]{3}[0-9][A-Z0-9][0-9]{2}");

    private final String placa;
    private final double capacidadeCargaKg;

    /**
     * Cria um veículo com placa e capacidade de carga validadas.
     *
     * @param placa             placa no padrão antigo ou Mercosul
     * @param capacidadeCargaKg carga máxima suportada, em quilogramas; deve ser maior que zero
     * @throws IllegalArgumentException se a placa ou a capacidade forem inválidas
     */
    protected Veiculo(String placa, double capacidadeCargaKg) {
        this.placa = validarPlaca(placa);
        this.capacidadeCargaKg = validarCapacidadeCarga(capacidadeCargaKg);
    }

    /** {@return placa do veículo, sempre em letras maiúsculas} */
    public String getPlaca() {
        return placa;
    }

    /** {@return carga máxima suportada, em quilogramas} */
    public double getCapacidadeCargaKg() {
        return capacidadeCargaKg;
    }

    /**
     * Informa se o veículo consegue transportar a carga informada.
     *
     * @param pesoKg peso da carga, em quilogramas
     * @return {@code true} se o peso não ultrapassa a capacidade do veículo
     */
    public boolean suportaPeso(double pesoKg) {
        return pesoKg <= capacidadeCargaKg;
    }

    /** {@return nome do tipo de veículo, usado nas mensagens do sistema (ex.: "Caminhão")} */
    public abstract String getTipo();

    @Override
    public String toString() {
        return getTipo() + " " + placa;
    }

    private static String validarPlaca(String placa) {
        if (placa == null) {
            throw new IllegalArgumentException("A placa do veículo é obrigatória.");
        }
        String placaNormalizada = placa.trim().toUpperCase();
        if (!FORMATO_PLACA.matcher(placaNormalizada).matches()) {
            throw new IllegalArgumentException("Placa inválida: " + placa);
        }
        return placaNormalizada;
    }

    private static double validarCapacidadeCarga(double capacidadeCargaKg) {
        if (capacidadeCargaKg <= 0) {
            throw new IllegalArgumentException(
                    "A capacidade de carga deve ser maior que zero. Valor recebido: " + capacidadeCargaKg);
        }
        return capacidadeCargaKg;
    }
}
