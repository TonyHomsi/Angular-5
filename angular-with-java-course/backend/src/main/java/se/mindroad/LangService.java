package se.mindroad;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LangService {
	private Map<Integer,Lang> Langs = new HashMap<Integer,Lang>();
	private int id = 0;
	
	public Collection<Lang> getAllLangs() {
		return Langs.values();
	}
	
	public Lang getLang(int id) {
	
		for(Lang lang : Langs.values()) {
			if(lang.getId() == id) {
				return lang;
			}
		}
		return null;
	}
	
	public Lang createLang(String name) {
		Lang lang = new Lang();
		lang.setId(id);
		lang.setName(name);
		Langs.put(id, lang);
		id++;
		return lang;
	}
	
	public Lang updateLang(int id,String name) {
		Lang temp = getLang(id);
		temp.setName(name);
		return temp; 
	}
	
	public boolean existsName(String name) {
		for(Lang lang : Langs.values()) {
			if(lang.getName() == name) {
				return true;
			}
		}
		return false;
	}
}
