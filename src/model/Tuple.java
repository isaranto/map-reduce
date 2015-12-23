package model;

public class Tuple<X, Y> {
	public final X profile;

	public final Y score;

	public Tuple(X profile, Y score) {
		this.profile = profile;
		this.score = score;
	}

	@Override
	public String toString() {
		return "Tuple [profile=" + profile + ", score=" + score + "]";
	}
}
