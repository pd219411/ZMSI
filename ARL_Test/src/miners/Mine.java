package miners;

public class Mine {
	public Mine(int location) {
		this.location = location;
	}

	public String toString() {
		return "Mine @" + location;
	}

	public int location;
}
