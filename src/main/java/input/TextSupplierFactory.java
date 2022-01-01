package input;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;


public class TextSupplierFactory{
	
	private static TextSupplierFactory instance;
	
	
	
	ArrayList<PrimitivCharConsumer> textSupplierQueue = new ArrayList<PrimitivCharConsumer>();
	
	
	
	private TextSupplierFactory() {}
	
	
	
	public static TextSupplierFactory getInstance () {
		if(instance == null) {
			instance = new TextSupplierFactory();
		}
		return TextSupplierFactory.instance;
	}
	
	
	
	
	
	
	public void addSupplier(KeyPredicate predicate, Consumer<Character> supplier) {
		if(supplier != null) {
			textSupplierQueue.add((char chr) -> {if(predicate.test((short) chr)) supplier.accept((chr));});
		}
	}
	
	
	
	
	public void addSupplier(BooleanSupplier prePredicate, KeyPredicate predicate, Consumer<Character> supplier) {
		if(supplier != null) {
			textSupplierQueue.add((char chr) -> {if(prePredicate.getAsBoolean() && predicate.test((short) chr)) supplier.accept((chr));});
		}
	}
	
	
	
	
	
	
	public void keyPressed(short keyCode) {
		for(PrimitivCharConsumer consumer : textSupplierQueue) {
			consumer.accept((char) keyCode);
		}
	}
	
	
	
	private interface PrimitivCharConsumer{
		
		public void accept(char chr);
		
	}
}
