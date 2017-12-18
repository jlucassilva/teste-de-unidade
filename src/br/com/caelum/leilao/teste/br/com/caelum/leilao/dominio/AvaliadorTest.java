package br.com.caelum.leilao.dominio;

import br.com.caelum.leilao.dominio.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lucas on 14/12/2017.
 */
public class AvaliadorTest {

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {
        // cenario: 3 lances em ordem crescente
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(maria, 250.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(jose, 400.0));

        // executando a acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        // comparando a saida com o esperado
        double menorEsperado = 250;
        double maiorEsperado = 400;

        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
    }


    @Test
    public void deveEntenderLancesEmOrdemDecrescente() {
        // cenario: 3 lances em ordem decrescente
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario mary = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(mary, 3250.0));
        leilao.propoe(new Lance(joao, 2300.0));
        leilao.propoe(new Lance(jose, 1400.0));

        // executando a acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        // comparando a saida com o esperado
        double menorEsperado = 1400;
        double maiorEsperado = 3250;

        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
    }

    @Test
    public void deveCalcularMediaDosLances() throws Exception {
        // cenario: 3 lances em ordem decrescente
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario mary = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(mary, 300.0));
        leilao.propoe(new Lance(joao, 200.0));
        leilao.propoe(new Lance(jose, 100.0));

        // executando a acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.calculaMediaDosLances(leilao);

        // comparando a saida com o esperado
        double mediaEsperada = 200;

        assertEquals(mediaEsperada, leiloeiro.getMediaDosLances(), 0.0001);
    }

    @Test
    public void deveFuncionarComApenasUmLance() throws Exception {

        // cenario: 3 lances em ordem decrescente
        Usuario joao = new Usuario("Joao");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 200.0));

        // executando a acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        // comparando a saida com o esperado
        double menorEsperado = 200;
        double maiorEsperado = 200;

        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
    }

    @Test
    public void deveEntenderLancesEmOrdemAleatoria() {
        // cenario: 3 lances em ordem decrescente
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario mary = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(mary, 3250.0));
        leilao.propoe(new Lance(joao, 1300.0));
        leilao.propoe(new Lance(jose, 1400.0));
        leilao.propoe(new Lance(jose, 5400.0));
        leilao.propoe(new Lance(jose, 1230.0));

        // executando a acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        // comparando a saida com o esperado
        double menorEsperado = 1230.0;
        double maiorEsperado = 5400.0;

        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
    }

    @Test
    public void deveEncontrarOsTresMaiores() throws Exception {

        // cenario: 3 lances em ordem decrescente
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario mary = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(jose, 1230.0));
        leilao.propoe(new Lance(joao, 1300.0));
        leilao.propoe(new Lance(jose, 1400.0));
        leilao.propoe(new Lance(mary, 3250.0));
        leilao.propoe(new Lance(jose, 5400.0));

        // executando a acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        // comparando a saida com o esperado
        double maiorEsperado = 5400.0;
        double segundoMaiorEsperado = 3250.0;
        double terceiroMaiorEsperado = 1400.0;

        assertEquals(3, leiloeiro.getTresMaiores().size());
        assertEquals(maiorEsperado, leiloeiro.getTresMaiores().get(0).getValor(), 0.0001);
        assertEquals(segundoMaiorEsperado, leiloeiro.getTresMaiores().get(1).getValor(), 0.0001);
        assertEquals(terceiroMaiorEsperado, leiloeiro.getTresMaiores().get(2).getValor(), 0.0001);
    }

    @Test
    public void deveDevolverApenasOsDoisLancesQueEncontrou() throws Exception {

        // cenario: 3 lances em ordem decrescente
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");


        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(jose, 1230.0));
        leilao.propoe(new Lance(joao, 1300.0));

        // executando a acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        // comparando a saida com o esperado
        double maiorEsperado = 1300.0;
        double segundoMaiorEsperado = 1230.0;

        assertEquals(2, leiloeiro.getTresMaiores().size());
        assertEquals(maiorEsperado, leiloeiro.getTresMaiores().get(0).getValor(), 0.0001);
        assertEquals(segundoMaiorEsperado, leiloeiro.getTresMaiores().get(1).getValor(), 0.0001);

    }

    @Test
    public void deveDevolverListaVazia() throws Exception {
        Leilao leilao = new Leilao("Playstation 3 Novo");
        // executando a acao
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(leiloeiro.getTresMaiores().size(), 0);

    }

 }

