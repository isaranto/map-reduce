package server;

import java.util.ArrayList;

import com.google.gson.Gson;

import model.Message;
import model.Profile;
import reducer.Reducer;

public class ReducerJob extends Job {
	private ArrayList<Profile> cache;

	private ArrayList<Node<Reducer>> allocated_reducers;

	public ReducerJob(int numOfNodes, Message msg) {
		super(numOfNodes, msg);
		cache = new ArrayList<Profile>();

	}

	public void addToCache(ArrayList<Profile> ar) {
		this.cache.addAll(ar);
	}

	public void freeResources() {
		for (int i = 0; i < allocated_reducers.size(); i++) {
			allocated_reducers.get(i).setAvailable(true);
		}
	}

	public ArrayList<Node<Reducer>> getAllocated_reducers() {
		return allocated_reducers;
	}

	public ArrayList<Profile> getCache() {
		return cache;
	}

	public void runJob(Scheduler s) {
		for (int i = 0; i < allocated_reducers.size(); i++) {
			allocated_reducers.get(i).setAvailable(false);
			this.msg.setJobId(this.jobId);
			System.out.println(this.msg.getProfile_scores().size());
			String message = new Gson().toJson(this.msg);
			allocated_reducers.get(i).getNetworkNode().handleMessageFromNetwork(message, s);
		}
	}

	public void setAllocated_reducers(ArrayList<Node<Reducer>> allocated_reducers) {
		this.allocated_reducers = allocated_reducers;
	}

	public void setCache(ArrayList<Profile> cache) {
		this.cache = cache;
	}

}
