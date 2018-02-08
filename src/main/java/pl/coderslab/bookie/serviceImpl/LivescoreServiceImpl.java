package pl.coderslab.bookie.serviceImpl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import pl.coderslab.bookie.entities.Livescore;
import pl.coderslab.bookie.entities.Match;
import pl.coderslab.bookie.service.LivescoreService;
@Service
public class LivescoreServiceImpl implements LivescoreService {

	@Override
	public List<Match> getAllMatches() {
		Gson gson = new Gson();
		URL url;
		List<Match> gamesList =new ArrayList<Match>();
		try {
			url = new URL(
					"http://livescore-api.com/api-client/scores/live.json?key=W7h9X9ho7KSLVkuR&secret=APMi8nA60wvEm9jihArfJJEQtxCoS7gt");
			InputStreamReader reader = new InputStreamReader(url.openStream());
			Livescore livescore = gson.fromJson(reader, Livescore.class);
			gamesList = livescore.getData().getMatch();
			

		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return gamesList;
	}

}
