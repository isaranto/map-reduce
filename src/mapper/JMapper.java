package mapper;

import java.util.ArrayList;
import java.util.Random;

import com.google.gson.Gson;

import model.Message;
import model.Profile;
import model.Tuple;
import server.Scheduler;

public class JMapper implements Mapper {

	public static void main(String args[]) {

		// System.out.println(test.handleMessageFromNetwork(new
		// Gson().toJson(msg).toString()));
	}

	public ArrayList<Profile> dataset;

	@Override
	public void handleMessageFromNetwork(String msg, Scheduler s) {
		ArrayList<Tuple<Profile, Integer>> results = new ArrayList<>();
		Message m = new Gson().fromJson(msg, Message.class);
		// TODO extracts query from message
		String query = m.getAction();
		setup(query);
		for (int i = 0; i < dataset.size(); i++) {
			results.add(map(dataset.get(i)));
		}
		m.setProfile_scores(results);
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.handleMessageFromMRNetwork(new Gson().toJson(m));

	}

	@Override
	public Tuple<Profile, Integer> map(Profile p) {
		Tuple<Profile, Integer> tuple = new Tuple<Profile, Integer>(p, new Random().nextInt(100));
		// TODO Auto-generated method stub
		return tuple;
	}

	public ArrayList<Profile> mockup() {
		ArrayList<Profile> profile = new ArrayList<>();

		profile.add(new Gson().fromJson("{name:George,country:Greece,email:test@yahoo.com,language:English}",
				Profile.class));
		profile.add(new Gson().fromJson("{name:George,country:Greece,email:test@yahoo.com,language:English}",
				Profile.class));
		profile.add(new Gson().fromJson("{name:George,country:Greece,email:test@yahoo.com,language:English}",
				Profile.class));
		profile.add(new Gson().fromJson("{name:George,country:Greece,email:test@yahoo.com,language:English}",
				Profile.class));
		profile.add(new Gson().fromJson("{name:George,country:Greece,email:test@yahoo.com,language:English}",
				Profile.class));
		return profile;

	}

	@Override
	public void setup(String query) {
		// TODO Executes the query, and creates the dataset
		dataset = mockup();

	}

}
