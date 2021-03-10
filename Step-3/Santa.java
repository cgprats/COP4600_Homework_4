//import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;



public class Santa implements Runnable {

	enum SantaState {SLEEPING, READY_FOR_CHRISTMAS, WOKEN_UP_BY_ELVES, WOKEN_UP_BY_REINDEER};
	private SantaState state;
	private boolean terminate = false;
	private Elf elfAtDoor;
	
	public Santa(SantaScenario scenario) {
		this.state = SantaState.SLEEPING;
	}

	public void deferredTerminate() {
		terminate = true;
	}

	public void wakeByElf(Elf wakingElf) {
		elfAtDoor = wakingElf;
		state = SantaState.WOKEN_UP_BY_ELVES;
	}
	
	
	@Override
	public void run() {
		//while(true) {
		while (!terminate) {
			// wait a day...
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch(state) {
			case SLEEPING: // if sleeping, continue to sleep
				break;
			case WOKEN_UP_BY_ELVES: 
				// FIXME: help the elves who are at the door and go back to sleep
				elfAtDoor.setState(Elf.ElfState.WORKING);
				state = SantaState.SLEEPING;
				break;
			case WOKEN_UP_BY_REINDEER: 
				// FIXME: assemble the reindeer to the sleigh then change state to ready 
				break;
			case READY_FOR_CHRISTMAS: // nothing more to be done
				break;
			}
		}
	}

	
	/**
	 * Report about my state
	 */
	public void report() {
		System.out.println("Santa : " + state);
	}
	
	
}
