package server;

import java.util.UUID;

import model.Message;

public abstract class Job {

	protected int numOfNodes;
	protected String jobId;
	protected Message msg;
	protected boolean pending;

	public Job(int numOfNodes, Message msg) {
		this.setNumOfNodes(numOfNodes);
		this.setMsg(msg);
		this.setJobId(UUID.randomUUID().toString());
	}

	public void decreaseNumOfNodes() {
		this.numOfNodes -= 1;
	}

	public String getJobId() {
		return jobId;
	}

	/**
	 * @return the msg
	 */
	public Message getMsg() {
		return msg;
	}

	public int getNumOfNodes() {
		return numOfNodes;
	}

	public boolean isDone() {
		return numOfNodes == 0;
	}

	/**
	 * @return the pending
	 */
	public boolean isPending() {
		return pending;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(Message msg) {
		this.msg = msg;
	}

	public void setNumOfNodes(int numOfNodes) {
		this.numOfNodes = numOfNodes;
	}

	/**
	 * @param pending
	 *            the pending to set
	 */
	public void setPending(boolean pending) {
		this.pending = pending;
	}

}
