package server;

import java.util.ArrayList;

import com.google.gson.Gson;

import mapper.Mapper;
import model.Message;
import reducer.Reducer;

public class Scheduler {
	public ArrayList<MapperJob> mapper_jobs = new ArrayList<MapperJob>();
	public ArrayList<ReducerJob> reducer_jobs = new ArrayList<ReducerJob>();
	public MapperList mappers = new MapperList();
	public ReducerList reducers = new ReducerList();

	public void createReducerJob(MapperJob m) {
		ArrayList<Node<Reducer>> avail = reducers.getAvailables();
		if (avail.size() > 0) {
			ReducerJob job = new ReducerJob(avail.size(), m.getMsg());
			reducer_jobs.add(job);
			job.setAllocated_reducers(avail);
			job.runJob(this);
		} else {
			ReducerJob job = new ReducerJob(0, m.getMsg());
			job.setPending(true);
		}
	}

	public void handleMessageFromMRNetwork(String msg) {
		System.out.println(msg);
		Message m = new Gson().fromJson(msg, Message.class);
		for (int i = 0; i < mapper_jobs.size(); i++) {
			MapperJob current = mapper_jobs.get(i);
			if (m.getJobId().equals(current.jobId)) {
				current.addToCache(m.getProfile_scores());
				current.decreaseNumOfNodes();
				if (current.isDone()) {
					current.getMsg().setProfile_scores(current.getCache());
					createReducerJob(current);
					current.freeResources();
					/*
					 * TODO: start next pending mapper job
					 */
					mapper_jobs.remove(current);
				}
			}
		}

		for (int i = 0; i < reducer_jobs.size(); i++) {
			ReducerJob current = reducer_jobs.get(i);
			if (m.getJobId().equals(current.getJobId())) {
				current.addToCache(m.getProfiles());
				current.decreaseNumOfNodes();
				if (current.isDone()) {
					/*
					 * TODO: return result to network
					 */
					System.out.println(current.getCache());
					current.freeResources();
					/*
					 * TODO: start next pending reducer job
					 */
					reducer_jobs.remove(current);
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
