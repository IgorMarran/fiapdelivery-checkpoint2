package br.com.fiapdelivery.model;

/**
 * Pacote a ser entregue pelo FiapDelivery.
 * <p>
 * Todo pacote nasce com status {@link StatusPacote#PENDENTE}. O status só pode
 * ser alterado por {@link #atualizarStatus(StatusPacote)}, que garante que o
 * fluxo de entrega seja respeitado.
 */
public class Pacote {

    private final String codigo;
    private final double pesoKg;
    private StatusPacote status;

    /**
     * Cria um pacote pendente de entrega.
     *
     * @param codigo código de rastreio; não pode ser nulo ou vazio
     * @param pesoKg peso do pacote, em quilogramas; deve ser maior que zero
     * @throws IllegalArgumentException se o código ou o peso forem inválidos
     */
    public Pacote(String codigo, double pesoKg) {
        this.codigo = validarCodigo(codigo);
        this.pesoKg = validarPeso(pesoKg);
        this.status = StatusPacote.PENDENTE;
    }

    /** {@return código de rastreio do pacote} */
    public String getCodigo() {
        return codigo;
    }

    /** {@return peso do pacote, em quilogramas} */
    public double getPesoKg() {
        return pesoKg;
    }

    /** {@return status atual da entrega} */
    public StatusPacote getStatus() {
        return status;
    }

    /**
     * Avança o pacote para a próxima etapa da entrega.
     *
     * @param novoStatus próximo status do fluxo de entrega
     * @throws IllegalStateException se a mudança pular ou voltar etapas
     */
    public void atualizarStatus(StatusPacote novoStatus) {
        if (!status.podeMudarPara(novoStatus)) {
            throw new IllegalStateException("O pacote " + codigo + " não pode passar de \"" + status
                    + "\" para \"" + novoStatus + "\".");
        }
        this.status = novoStatus;
    }

    @Override
    public String toString() {
        return "Pacote " + codigo + " (" + pesoKg + " kg, " + status + ")";
    }

    private static String validarCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("O código do pacote é obrigatório.");
        }
        return codigo.trim();
    }

    private static double validarPeso(double pesoKg) {
        if (pesoKg <= 0) {
            throw new IllegalArgumentException("O peso do pacote deve ser maior que zero. Valor recebido: " + pesoKg);
        }
        return pesoKg;
    }
}
