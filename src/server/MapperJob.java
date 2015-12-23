package server;

import java.util.ArrayList;

import com.google.gson.Gson;

import mapper.Mapper;
import model.Message;
import model.Profile;
import model.Tuple;

public class MapperJob extends Job {
	private ArrayList<Tuple<Profile, Integer>> cache;

	private ArrayList<Node<Mapper>> allocated_mappers;

	public MapperJob(int numOfNodes, Message msg) {
		super(numOfNodes, msg);
		cache = new ArrayList<Tuple<Profile, Integer>>();

	}

	public void addToCache(ArrayList<Tuple<Profile, Integer>> ar) {
		this.cache.addAll(ar);
	}

	public void freeResources() {
		for (int i = 0; i < allocated_mappers.size(); i++) {
			allocated_mappers.get(i).setAvailable(true);
		}
	}

	public ArrayList<Node<Mapper>> getAllocated_mappers() {
		return allocated_mappers;
	}

	public ArrayList<Tuple<Profile, Integer>> getCache() {
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

	public void setCache(ArrayList<Tuple<Profile, Integer>> cache) {
		this.cache = cache;
	}

}
