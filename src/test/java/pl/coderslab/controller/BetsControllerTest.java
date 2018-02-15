package pl.coderslab.controller;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import pl.coderslab.bookie.controllers.BankingController;
import pl.coderslab.bookie.controllers.BetController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = pl.coderslab.bookie.BookieApplication.class)
@DataJpaTest
public class BetsControllerTest {
	private static BetController betController;

	@BeforeClass
	public static void setUp() {
		betController = new BetController();
	}

	@Test
	public void testBankingPage() throws Exception {
		betController = new BetController();
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(betController).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/bets/all"))
				.andExpect(MockMvcResultMatchers.view().name("bets/all"));
	}
}
