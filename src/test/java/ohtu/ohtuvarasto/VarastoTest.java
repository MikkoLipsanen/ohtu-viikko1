package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    static final double VERTAILUTARKKUUS = 0.0001;
    static final int K = 10;
    static final int V = 5;

    @Before
    public void setUp() {
        varasto = new Varasto(K);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), VERTAILUTARKKUUS);
    }

    @Test
    public void negatiivinenTilavuusKonstruktorille() {
        varasto = new Varasto(-K);
        assertEquals(0.0, varasto.getTilavuus(), VERTAILUTARKKUUS);
    }

    @Test
    public void kuormitettuKonstruktoriPositiivisellaTilavuudella() {
        varasto = new Varasto(K, V);
        assertEquals(K, varasto.getTilavuus(), VERTAILUTARKKUUS);
    }

    @Test
    public void kuormitettuKonstruktoriPositiivisellaAlkusaldolla() {
        varasto = new Varasto(K, V);
        assertEquals(V, varasto.getSaldo(), VERTAILUTARKKUUS);
    }

    @Test
    public void negatiivinenTilavuusKuormitetulleKonstruktorille() {
        varasto = new Varasto(-K, V);
        assertEquals(0.0, varasto.getTilavuus(), VERTAILUTARKKUUS);
    }

    @Test
    public void negatiivinenAlkusaldoKuormitetulleKonstruktorille() {
        varasto = new Varasto(K, -V);
        assertEquals(0.0, varasto.getSaldo(), VERTAILUTARKKUUS);
    }

    @Test
    public void alkusaldoSuurempiKuinTilavuusKuormitetulleKonstruktorille() {
        varasto = new Varasto(K, 15);
        assertEquals(K, varasto.getSaldo(), VERTAILUTARKKUUS);
    }


    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(K, varasto.getTilavuus(), VERTAILUTARKKUUS);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), VERTAILUTARKKUUS);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), VERTAILUTARKKUUS);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, VERTAILUTARKKUUS);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), VERTAILUTARKKUUS);
    }

    @Test
    public void liianSuuriLisäysPalauttaaTilavuuden() {
        varasto.lisaaVarastoon(15);

        assertEquals(10, varasto.getSaldo(), VERTAILUTARKKUUS);
    }

    @Test
    public void negatiivinenLisäysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-15);

        assertEquals(0.0, varasto.getSaldo(), VERTAILUTARKKUUS);
    }

    @Test
    public void liianSuuriOttoPalauttaaNollan() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(15);

        assertEquals(0.0, varasto.getSaldo(), VERTAILUTARKKUUS);
    }

    @Test
    public void negatiivinenOttoPalauttaaNollan() {
        varasto.lisaaVarastoon(8);

        assertEquals(0.0, varasto.otaVarastosta(-15), VERTAILUTARKKUUS);
    }

    @Test
    public void toStringPalauttaaOikeatArvot() {
        varasto = new Varasto(K, V);

         assertEquals("saldo = 5.0, vielä tilaa 5.0", varasto.toString());
    }

}
