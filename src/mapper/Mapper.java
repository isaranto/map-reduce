package mapper;

import model.Profile;
import model.Tuple;

public interface Mapper {
	public Tuple<Profile, Integer> map(Profile p);

	public void setup(String query);

}
