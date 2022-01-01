package input;

import java.awt.event.KeyEvent;

public interface KeyPredicate {

	public boolean test(short keyCode);
	
	
	
	
	public default KeyPredicate andThen(KeyPredicate keyPredicate) {
		return chr -> test(chr) || keyPredicate.test(chr);
	}
	
	
	
	
	
	
	public static KeyPredicate KEY_PREDICATE_BACKSPACE = chr -> chr == KeyEvent.VK_BACK_SPACE;
	
	public static KeyPredicate KEY_PREDICATE_SPACEBAR = chr -> chr == KeyEvent.VK_SPACE;
	
	public static KeyPredicate KEY_PREDICATE_ENTER = chr -> chr == KeyEvent.VK_ENTER;
	
	
	public static KeyPredicate KEY_PREDICATE_ARROW_BUTTONS = chr -> chr == KeyEvent.VK_LEFT || chr == KeyEvent.VK_RIGHT || chr == KeyEvent.VK_UP || chr == KeyEvent.VK_DOWN;
	
	
	
	
	
	
	public static KeyPredicate KEY_PREDICATE_ONLY_NUMBERS = chr -> chr >= 48 && chr <= 57;
	
		
		
	public static KeyPredicate KEY_PREDICATE_NUMBERS_WITH_SIGN = chr -> (chr >= 48 && chr <= 57) || chr == 45 || chr == 521;
	
	
	
	
	public static KeyPredicate KEY_PREDICATE_ONLY_ALPHABET = chr -> (chr >= 65 && chr <= 90) || (chr >= 97 && chr <= 122);
	
	
	
	public static KeyPredicate KEY_PREDICATE_ONLY_UMLAUTS = chr -> chr == 196 || chr == 220 || chr == 214 || chr == 228 || chr == 246 || chr == 252;
	
	
	
	public static KeyPredicate KEY_PREDICATE_SYMBOLS = chr -> (chr >= 33 && chr <= 47) || (chr >= 58 && chr <= 64) || (chr >= 91 && chr <= 96) || (chr >= 123 && chr <= 126) || chr == 128;
	
	
	
	
	
	public static KeyPredicate KEY_PREDICATE_ALL_PRINTABLE_CHARS = chr -> KEY_PREDICATE_ONLY_ALPHABET
																 .andThen(KEY_PREDICATE_ONLY_NUMBERS)
																 .andThen(KEY_PREDICATE_SYMBOLS)
																 .andThen(KEY_PREDICATE_SPACEBAR)
																 .andThen(KEY_PREDICATE_ONLY_UMLAUTS)
																 .andThen(KEY_PREDICATE_SPACEBAR)
																 .andThen(KEY_PREDICATE_ENTER)
																 .test(chr);
	
		
		
		
	public static KeyPredicate KEY_PREDICATE_ALL = chr -> true;
}
