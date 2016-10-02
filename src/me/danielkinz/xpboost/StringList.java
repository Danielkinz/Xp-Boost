package me.danielkinz.xpboost;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A special implementation of ArrayList for strings
 * 
 * @author Danielkinz
 */
public class StringList extends ArrayList<String> {

	private static final long serialVersionUID = 8431981736166835630L;

	public StringList() {
		super();
	}

	public <U extends Collection<String>> StringList(U collection) {
		super(collection);
	}

	/**
	 * Replaces the substrings with new substrings in all the strings in the
	 * list
	 * 
	 * @param oldChars
	 * @param newChars
	 * @return
	 */
	public StringList replace(CharSequence oldChars, CharSequence newChars) {
		for (int i = 0; i < this.size(); i++) {
			this.set(i, this.get(i).replace(oldChars, newChars));
		}
		return this;
	}

	/**
	 * Removes all the formats that are left
	 * 
	 * @return
	 */
	public StringList stripFormats() {
		for (int i = 0; i < this.size(); i++) {
			this.set(i, this.get(i).replaceAll("%.*?%", ""));
		}
		return this;
	}

	/**
	 * Same as contains but ignores case
	 * 
	 * @param str
	 * @return
	 */
	public boolean containIgnoreCase(String str) {
		for (String s : this) {
			if (s.equalsIgnoreCase(str)) {
				return true;
			}
		}
		return false;
	}

}
