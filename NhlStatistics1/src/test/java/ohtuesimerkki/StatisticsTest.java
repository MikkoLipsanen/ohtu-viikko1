/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class StatisticsTest {
    
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void returnsRightPlayer(){
        assertEquals(readerStub.getPlayers().get(3), stats.search("Yzerman"));
    }
    
    @Test
    public void returnsNullWhenPlayerNotOnList(){
        assertEquals(null, stats.search("Koivu"));
    }
    
    @Test
    public void returnsRightPlayersOfTeam(){
        ArrayList<Player> testList = new ArrayList<Player>();
        testList.add(readerStub.getPlayers().get(0));
        testList.add(readerStub.getPlayers().get(2));
        testList.add(readerStub.getPlayers().get(4));
        assertEquals(testList, stats.team("EDM"));
    }
    
    @Test
    public void returnsEmptyListWhenTeamNotOnList(){
        ArrayList<Player> testList = new ArrayList<Player>();
        assertEquals(testList, stats.team("CAL"));
    }
    
    @Test
    public void returnsRightTopScorers(){
        ArrayList<Player> testList = new ArrayList<Player>();
        testList.add(readerStub.getPlayers().get(4));
        testList.add(readerStub.getPlayers().get(1));
        assertEquals(testList, stats.topScorers(1));
    }

}
