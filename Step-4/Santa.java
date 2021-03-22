//import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;


import java.util.ArrayList;
import java.util.List;

public class Santa implements Runnable {

	enum SantaState {SLEEPING, READY_FOR_CHRISTMAS, WOKEN_UP_BY_ELVES, WOKEN_UP_BY_REINDEER};
	private SantaState state;
	private boolean terminate = false;
	private List<Elf> elvesAtDoor = new ArrayList<>();

	public Santa(SantaScenario scenario) {
		this.state = SantaState.SLEEPING;
	}

	public void deferredTerminate() {
		terminate = true;
	}

	public void wakeByElf(Elf wakingElf) {
		elvesAtDoor.add(wakingElf);
		if (elvesAtDoor.size() == 3) {
			for (int i = 0; i < elvesAtDoor.size(); i++) {
				elvesAtDoor.get(i).setState(Elf.ElfState.AT_SANTAS_DOOR);
			}
			state = SantaState.WOKEN_UP_BY_ELVES;
		}
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
				for (int i = 0; i < elvesAtDoor.size(); i++) {
					elvesAtDoor.get(0).setState(Elf.ElfState.WORKING);
					elvesAtDoor.remove(0);
				}
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
