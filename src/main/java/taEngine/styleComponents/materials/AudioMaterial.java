package taEngine.styleComponents.materials;

import audio.SoundClip;

public abstract class AudioMaterial extends Material{
	
	private SoundClip soundClip;
	
	public AudioMaterial(int id, SoundClip soundClip) {
		super(id);
		
		this.soundClip = soundClip;
	}
	
	
	
	public abstract void play();
	
	public abstract void pause();
	
	public abstract void stop();
	
}
