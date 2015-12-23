package server;

import java.util.ArrayList;

import com.google.gson.Gson;

import mapper.Mapper;
import model.Message;
import model.Tuple;

public class MapperJob extends Job {
	private ArrayList<Tuple> cache;

	private ArrayList<Node<Mapper>> allocated_mappers;

	public MapperJob(int numOfNodes, Message msg) {
		super(numOfNodes, msg);
		cache = new ArrayList<Tuple>();

	}

	public void addToCache(ArrayList<Tuple> ar) {
		this.cache.addAll(ar);
	}

	public ArrayList<Node<Mapper>> getAllocated_mappers() {
		return allocated_mappers;
	}

	public ArrayList<Tuple> getCache() {
		return cache;
	}

	public void runJob(Scheduler s) {
		for (int i = 0; i < allocated_mappers.size(); i++) {
			allocated_mappers.get(i).setAvailable(false);
			this.msg.setJobId(this.jobId);
			String message = new Gson().toJson(this.msg);
			allocated_mappers.get(i).getNetworkNode().handleMessageFromNetwork(message, s);
		}
	}

	public void setAllocated_mappers(ArrayList<Node<Mapper>> allocated_mappers) {
		this.allocated_mappers = allocated_mappers;
	}

	public void setCache(ArrayList<Tuple> cache) {
		this.cache = cache;
	}

}
