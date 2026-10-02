package br.com.fiapdelivery.model;

/**
 * Etapas pelas quais um {@link Pacote} passa, na ordem em que acontecem.
 * <p>
 * Substitui o status em texto livre do código legado, que aceitava qualquer
 * valor (inclusive erros de digitação).
 */
public enum StatusPacote {

    /** Pacote cadastrado, aguardando saída para entrega. */
    PENDENTE("Pendente"),
    /** Pacote a caminho do destino. */
    EM_TRANSITO("Em trânsito"),
    /** Pacote entregue ao destinatário. */
    ENTREGUE("Entregue");

    private final String descricao;

    StatusPacote(String descricao) {
        this.descricao = descricao;
    }

    /** {@return texto amigável do status, para exibição} */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Informa se o pacote pode sair deste status para o status informado.
     * O fluxo só avança, uma etapa por vez, na ordem em que as constantes
     * foram declaradas.
     *
     * @param proximoStatus status de destino
     * @return {@code true} se a mudança respeita o fluxo de entrega
     */
    public boolean podeMudarPara(StatusPacote proximoStatus) {
        return proximoStatus != null && proximoStatus.ordinal() == ordinal() + 1;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
