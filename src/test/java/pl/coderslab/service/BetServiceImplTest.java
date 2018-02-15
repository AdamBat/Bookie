package pl.coderslab.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.coderslab.bookie.entities.Bet;
import pl.coderslab.bookie.entities.Game;
import pl.coderslab.bookie.repositories.BetRepository;
import pl.coderslab.bookie.repositories.GameRepository;
import pl.coderslab.bookie.service.BetService;
import pl.coderslab.bookie.service.GameService;
import pl.coderslab.bookie.serviceImpl.BetServiceImpl;
import pl.coderslab.bookie.serviceImpl.GameServiceImpl;

@SpringBootTest(classes = pl.coderslab.bookie.BookieApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest
public class BetServiceImplTest {

	BetRepository betRepo;
	BetService betservice;
	GameService gameService;
	GameRepository gameRepo;
	List<Bet> bets;

	@Before
	public void setup() {
		betRepo = mock(BetRepository.class);
		betservice = new BetServiceImpl(betRepo);
		gameRepo = mock(GameRepository.class);
		gameService = new GameServiceImpl();
		bets = new ArrayList<>();
		
	}

	@Test
	public void checkIfMarginCountedCorrectly() {
		double homeOdds = 2.0;
		double awayOdds = 1.98;
		double margin = ((1 / homeOdds) * 100 + (1 / awayOdds) * 100) - 100;
		assertEquals(false, betservice.checkIfMarginIsOkTwoWay(homeOdds, awayOdds));
	}

	@Test
	public void checkIfReturnCorrectBetsForFor() {
		bets = betservice.easyBet();
		assertArrayEquals(bets.toArray(), betservice.easyBet().toArray());
	}
	

}
