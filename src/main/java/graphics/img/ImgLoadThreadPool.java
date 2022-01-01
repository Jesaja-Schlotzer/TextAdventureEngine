package graphics.img;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ImgLoadThreadPool {
	
	private static ImgLoadThreadPool instance;
	
	public static ImgLoadThreadPool getInstance() {
		if(instance == null) {
			instance = new ImgLoadThreadPool();
		}
		return instance;
	}
	
	
	
	
	
	private ExecutorService threadPool = new ThreadPoolExecutor(3, 20, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

	private Set<Img> currentTasksList = new HashSet<Img>();
	

	
	
	
	public void addLoadTask(Img img) {
		if(currentTasksList.add(img)) {
			threadPool.execute(img::loadImg);
		}
	}
	
	
	public void removeFromCurrentTasksList(Img img) {
		currentTasksList.remove(img);
	}
	
	
}
