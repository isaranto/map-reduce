package mapper;

import model.Profile;
import model.Tuple;
import server.Scheduler;

public interface Mapper {
	public void handleMessageFromNetwork(String msg, Scheduler s);

	public Tuple<Profile, Integer> map(Profile p);

	public void setup(String query);

}
