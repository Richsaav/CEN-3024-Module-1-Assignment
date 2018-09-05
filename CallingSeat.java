package Module1;

/**
 * @author Rich
 *
 */
public class CallingSeat {
	
	private double seatNumber;
	
	public CallingSeat(double seatNumber) throws IllegalArgumentException 
	{
		this.seatNumber = seatNumber;
	}

	public double getSeatNumber() throws IllegalArgumentException 
	{
		return seatNumber;
	}

	public void setSeatNumber(double seatNumber) throws IllegalArgumentException
	{
		this.seatNumber = seatNumber;
	}
	
	public String toString() {
		return getClass() + " [Seat Number=" + seatNumber + "] ";
	}
	
}
