package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Leilao {
    private String descricao;
    private List<Lance> lances;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<Lance>();
    }

    public void propoe(Lance lance) {
        if (lances.isEmpty() || podeDarLance(lance.getUsuario()))
            lances.add(lance);
    }

    public void dobraLance(Usuario usuario) {
        Lance lance = lances.stream().filter(lanceN -> lanceN.getUsuario().equals(usuario)).collect(Collectors.toList()).get(lances.size() - 1);
        lance.setValor(lance.getValor()*2);
        propoe(lance);
    }

    private boolean podeDarLance(Usuario usuario) {
        return !ultimoLance().getUsuario().equals(usuario) && qtdDeLancesDo(usuario) < 5;
    }

    private Lance ultimoLance() {
        return lances.get(lances.size() - 1);
    }

    private int qtdDeLancesDo(Usuario usuario) {
        return (int) lances.stream().filter(lanceN -> lanceN.getUsuario().equals(usuario)).count();
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Lance> getLances() {
        return Collections.unmodifiableList(lances);
    }


}
