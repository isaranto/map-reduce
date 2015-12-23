package reducer;

import java.util.ArrayList;

import model.Profile;
import model.Tuple;
import server.Scheduler;

public interface Reducer {
	public void handleMessageFromNetwork(String msg, Scheduler s);

	public ArrayList<Profile> reduce(ArrayList<Tuple<Profile, Integer>> ar);

}
