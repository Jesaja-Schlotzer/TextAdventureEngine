package screen.fragmentRecorder;

import graphics.colors.Color;
import graphics.img.Img;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class FragmentRecorderPool {
	
	
	private static FragmentRecorderPool instance;
	
	
	
	private FragmentRecorderPool() {
		fragmentRecorderPool.add(new FragmentRecorder());
		fragmentRecorderPool.add(new FragmentRecorder());
	}
	
	
	
	public static FragmentRecorderPool getInstance () {
		if(instance == null) {
			instance = new FragmentRecorderPool();
		}
		return FragmentRecorderPool.instance;
	}
	
	
	
	
	

	private LinkedList<FragmentRecorder> fragmentRecorderPool = new LinkedList<FragmentRecorder>();
	
	private Map<String, Img> finishedRecordings = new HashMap<String, Img>();
	
	
	
	
	
	
	
	
	public void begin(String id) {
		for(FragmentRecorder recorder : fragmentRecorderPool) {
			if(!recorder.isRecording()) {
				recorder.begin(id);
				return;
			}
		}
		
		FragmentRecorder newRecorder = new FragmentRecorder();
		newRecorder.begin(id);
		
		fragmentRecorderPool.add(newRecorder);
	}
	
	
	
	
	
	public void begin(String id, int recordMode) {
		for(FragmentRecorder recorder : fragmentRecorderPool) {
			if(!recorder.isRecording()) {
				recorder.begin(id, recordMode);
				return;
			}
		}
		
		FragmentRecorder newRecorder = new FragmentRecorder();
		newRecorder.begin(id, recordMode);
		
		fragmentRecorderPool.add(newRecorder);
	}
	
	
	
	
	public void end(String id) {
		for(FragmentRecorder recorder : fragmentRecorderPool) {
			if(recorder.isRecording() && recorder.id().equals(id)) {
				recorder.end();
				
				finishedRecordings.put(recorder.id(), recorder.asImg());
			}
		}
		
		
		if(fragmentRecorderPool.size() > 2) {
			fragmentRecorderPool.removeIf(recorder -> finishedRecordings.containsKey(recorder.id()));
		}
		
		// Momentan nicht möglich, wenn id nicht existiert wird sie einfach ignoriert
		//Debugger.addLogEntry("FEHLER - screen/FragmentRecorderPool/end(String) - ID existiert nicht");
	}
	
	
	
	
	
	
	
	public boolean isRecording() {
		return fragmentRecorderPool.stream().filter(FragmentRecorder::isRecording).count() > 0;
	}
	
	
	
	
	public boolean getRecordModeState(int recordModeIndex) {
		return fragmentRecorderPool.stream().filter(recorder -> recorder.isRecording() && recorder.getRecordModeState(recordModeIndex)).count() > 0;
	}
	
	
	
	
	
	public void record(int x, int y, Color value) {
		if(value == null || value.isTransparent()) {
			return;
		}
		
		for(FragmentRecorder recorder : fragmentRecorderPool) {
			if(recorder.isRecording()) {
				recorder.record(x, y, value);
			}
		}
	}
	
	
	
	
	public Img getRecord(String id) {
		Img img = finishedRecordings.get(id);
		if(img == null) {
			return Img.EMPTY_IMG;
		}else {
			return img;
		}
	}



	
	
	public void remove(String id) {
		finishedRecordings.remove(id);
	}


	
	
	public Img getRecordAndRemove(String id) {
		Img temp = getRecord(id);
		remove(id);
		return temp;
	}
	
	
	
	
	@Deprecated
	public FragmentRecorder getRecorder(String id) {
		if(fragmentRecorderPool.stream().filter(rec -> rec.id() == id).count() == 0) {
			return null;
		}
		
		return fragmentRecorderPool.stream().filter(rec -> rec.id() == id).findFirst().get();
	}


	
}
