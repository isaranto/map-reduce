package reducer;

import java.util.ArrayList;

import com.google.gson.Gson;

import model.Message;
import model.Profile;
import model.Tuple;
import server.Scheduler;

public class JReducer implements Reducer {

	@Override
	public void handleMessageFromNetwork(String msg, Scheduler s) {
		Message message = new Gson().fromJson(msg, Message.class);
		message.setProfiles(reduce(message.getProfile_scores()));
		s.handleMessageFromMRNetwork(new Gson().toJson(message));

	}

	@Override
	public ArrayList<Profile> reduce(ArrayList<Tuple<Profile, Integer>> ar) {
		ArrayList<Profile> s = new ArrayList<Profile>();
		for (int i = 0; i < ar.size(); i++) {
			s.add(ar.get(i).profile);
		}
		return s;
	}

}
