package br.com.fiapdelivery.model;

/**
 * Moto da frota, indicada para entregas pequenas e rápidas.
 * <p>
 * Herda placa e capacidade de carga de {@link Veiculo} e acrescenta a
 * informação de baú.
 */
public class Moto extends Veiculo {

    private final boolean possuiBau;

    /**
     * Cria uma moto.
     *
     * @param placa             placa no padrão antigo ou Mercosul
     * @param capacidadeCargaKg carga máxima suportada, em quilogramas; deve ser maior que zero
     * @param possuiBau         {@code true} se a moto tem baú de carga
     * @throws IllegalArgumentException se a placa ou a capacidade forem inválidas
     */
    public Moto(String placa, double capacidadeCargaKg, boolean possuiBau) {
        super(placa, capacidadeCargaKg);
        this.possuiBau = possuiBau;
    }

    /** {@return {@code true} se a moto tem baú de carga} */
    public boolean possuiBau() {
        return possuiBau;
    }

    @Override
    public String getTipo() {
        return "Moto";
    }
}
