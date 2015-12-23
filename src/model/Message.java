package model;

import java.util.ArrayList;

public class Message {

	private String action;

	private ArrayList<Event> events;
	private ArrayList<Profile> profiles;
	private ArrayList<Category> categories;
	private String JobId;
	private boolean ack;

	private ArrayList<Tuple<Profile, Integer>> profile_scores;

	public Message(String action, boolean ack) {
		this.action = action;
		this.ack = ack;
	}

	public boolean getAck() {
		return this.ack;
	}

	public String getAction() {
		return this.action;
	}

	public ArrayList<Category> getCategories() {
		return this.categories;
	}

	public ArrayList<Event> getEvents() {
		return this.events;
	}

	public String getJobId() {
		return JobId;
	}

	public ArrayList<Tuple<Profile, Integer>> getProfile_scores() {
		return profile_scores;
	}

	public ArrayList<Profile> getProfiles() {
		return this.profiles;
	}

	public void setAck(boolean ack) {
		this.ack = ack;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}

	public void setJobId(String jobId) {
		JobId = jobId;
	}

	public void setProfile_scores(ArrayList<Tuple<Profile, Integer>> profile_scores) {
		this.profile_scores = profile_scores;
	}

	public void setProfiles(ArrayList<Profile> profiles) {
		this.profiles = profiles;
	}

	@Override
	public String toString() {
		return "Message [action=" + action + ", events=" + events + ", profiles=" + profiles + ", categories="
				+ categories + ", ack=" + ack + "]";
	}
}
