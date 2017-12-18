package br.com.caelum.leilao.dominio;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lucas on 15/12/2017.
 */
public class LeilaoTest {

    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() throws Exception {
        // cenario: usuario tenta fazer 2 lances em sequencia
        Usuario joao = new Usuario("Joao");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        // executando a acao
        leilao.propoe(new Lance(joao, 1300.0));
        leilao.propoe(new Lance(joao, 1330.0));

        assertEquals(1, leilao.getLances().size());
    }

    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() throws Exception {
        // cenario: usuario tenta fazer 2 lances em sequencia
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("Robson");

        Leilao leilao = new Leilao("Playstation 4 Novo");

        // executando a acao
        leilao.propoe(new Lance(joao, 800.0));
        leilao.propoe(new Lance(jose, 810.0));

        leilao.propoe(new Lance(joao, 900.0));
        leilao.propoe(new Lance(jose, 930.0));

        leilao.propoe(new Lance(joao, 1000.0));
        leilao.propoe(new Lance(jose, 1040.0));

        leilao.propoe(new Lance(joao, 1100.0));
        leilao.propoe(new Lance(jose, 1150.0));

        leilao.propoe(new Lance(joao, 1200.0));
        leilao.propoe(new Lance(jose, 1260.0));

        leilao.propoe(new Lance(joao, 1300.0));

        assertEquals(10, leilao.getLances().size());
        int ultimo = leilao.getLances().size() - 1;
        assertEquals(1260.0, leilao.getLances().get(ultimo).getValor(), 0.00001);
    }

    @Test
    public void deveDobrarUltimoLanceDoUsuario() throws Exception {
        // cenario: usuario tenta fazer 2 lances em sequencia
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("Robson");

        Leilao leilao = new Leilao("Playstation 4 Novo");

        // executando a acao
        leilao.propoe(new Lance(joao, 1300.0));
        leilao.propoe(new Lance(jose, 1330.0));

        leilao.dobraLance(joao);

        assertEquals(3, leilao.getLances().size());
        assertEquals(1300.0 * 2, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.00001);

    }
}