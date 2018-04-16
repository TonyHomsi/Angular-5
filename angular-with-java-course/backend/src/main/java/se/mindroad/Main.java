package se.mindroad;

import static spark.Spark.get;
import static spark.Spark.post;
import spark.Request;
import spark.Response;
import spark.Route;

public class Main {
	
	public static void main(String[] args) {
		LangService langService = new LangService();
		langService.createLang("Java");
		langService.createLang("C++");
		langService.createLang("Go");
		langService.createLang("C");
		langService.createLang("Python");
		langService.createLang("Ruby");
		langService.createLang("Kotlin");
		langService.createLang("TypeScript");
		new LangController(langService);
		new UserController(new UserService());
	}
}


