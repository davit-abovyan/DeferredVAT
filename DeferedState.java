package am.vector.VATReoprt.core;

public class DeferedState {
	private Period period;
	private InvState state;
	public DeferedState(Period period, InvState state){
		this.period = period;
		this.state = state;
	}
	public Period getPeriod(){
		return period;
	}
	public InvState getState(){
		return state;
	}
}
