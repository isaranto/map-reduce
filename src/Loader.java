import com.google.gson.Gson;

import mapper.JMapper;
import mapper.Mapper;
import model.Message;
import reducer.JReducer;
import reducer.Reducer;
import server.Node;
import server.Scheduler;

public class Loader {

	public static void main(String[] args) {
		JMapper test = new JMapper();
		JMapper test2 = new JMapper();
		JReducer test3 = new JReducer();
		Message msg = new Message("getEvents", true);
		Scheduler s = new Scheduler();
		s.mappers.addNode(new Node<Mapper>(true, test));
		s.mappers.addNode(new Node<Mapper>(true, test2));
		s.reducers.addNode(new Node<Reducer>(true, test3));
		s.handleMessageFromTaskManager(new Gson().toJson(msg));
	}

}
