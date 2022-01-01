package taEngine.styleComponents.materials;

import audio.SoundClip;

public class MusicMaterial extends AudioMaterial{
	
	private boolean loop;
	
	public MusicMaterial(int id, SoundClip soundClip, boolean loop) {
		super(id, soundClip);
		
		this.loop = loop;
	}
	
	

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}
