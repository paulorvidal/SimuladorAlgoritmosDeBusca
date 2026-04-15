public class NoDeBusca {
    int linha;
    int coluna;
    int custoAcumulado;
    int estimativa;
    NoDeBusca pai;

    public NoDeBusca(int linha, int coluna, int custoAcumulado, int estimativa, NoDeBusca pai) {
        this.linha = linha;
        this.coluna = coluna;
        this.custoAcumulado = custoAcumulado;
        this.estimativa = estimativa;
        this.pai = pai;
    }

    public int compareTo(NoDeBusca outroNo) {
        return Integer.compare(this.custoAcumulado, outroNo.custoAcumulado);
    }
}