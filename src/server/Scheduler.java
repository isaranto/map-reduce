package server;

import java.util.ArrayList;

import com.google.gson.Gson;

import mapper.Mapper;
import model.Message;

public class Scheduler {
	public ArrayList<MapperJob> mapper_jobs = new ArrayList<MapperJob>();
	public MapperList mappers = new MapperList();
	// public NodeList reducers = new ArrayList<Node<Reducer>>();

	public void handleMessageFromMRNetwork(String msg) {
		Message m = new Gson().fromJson(msg, Message.class);
		for (int i = 0; i < mapper_jobs.size(); i++) {
			MapperJob current = mapper_jobs.get(i);
			if (m.getJobId().equals(current.jobId)) {
				current.addToCache(m.getProfile_scores());
				current.decreaseNumOfNodes();
				if (current.isDone()) {
					System.out.println("MapperJob done");
					System.out.println(mappers.getAvailables().size());
					System.out.println(current.getCache().size());
				}
			}
		}
	}

	public void handleMessageFromTaskManager(String msg) {
		ArrayList<Node<Mapper>> avail = mappers.getAvailables();
		Message m = new Gson().fromJson(msg, Message.class);

		if (avail.size() > 0) {
			MapperJob job = new MapperJob(avail.size(), m);
			mapper_jobs.add(job);
			job.setAllocated_mappers(avail);
			job.runJob(this);
		} else {
			MapperJob job = new MapperJob(0, m);
			job.setPending(true);
		}
	}

}
