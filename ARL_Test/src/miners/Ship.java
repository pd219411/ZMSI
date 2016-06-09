package miners;

public class Ship {
	public float max_capacity = 10.0f;
	public float capacity = 10.0f;

	public int location = 0;

	public int turns_to_destination = 0;
	public int turns_to_order_deadline = Integer.MAX_VALUE;
	
	public String toString() {
		return "Ship (" + capacity + " / " + max_capacity + ") @{" + location + "} " + turns_to_destination + " deadline " + turns_to_order_deadline;
	}

	public void AddDeadline(int order_deadline) {
		turns_to_order_deadline = Math.min(turns_to_order_deadline, order_deadline);
	}

	public void AddStop(int new_destination, int travel_time) {
		location = new_destination;
		turns_to_destination += travel_time;
	}

	void Turn() {
		// stay where it is if dealine allows
		// go back home if needed
		// this logic probably should stay in ARL
	}

	public boolean Traveling() {
		return turns_to_destination > 0;
	}
	//TRAVEL TIME LEFT
}
