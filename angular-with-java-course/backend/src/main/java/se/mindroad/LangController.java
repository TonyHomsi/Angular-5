package se.mindroad;
import static spark.Spark.*;

import com.google.gson.Gson;

import static se.mindroad.JsonUtil.*;

import spark.Request;
import spark.Response;
import spark.Route;

public class LangController {
	Gson g = new Gson();
	public LangController(final LangService langService) {
		
		options("/*", (req,res) -> {
			String accessControlRequestHeaders = req.headers("Access-Control-Request-Headers");
			if (accessControlRequestHeaders != null) {
				res.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
			}

			String accessControlRequestMethod = req.headers("Access-Control-Request-Method");
			if (accessControlRequestMethod != null) {
				res.header("Access-Control-Allow-Method", accessControlRequestMethod);
			}

			return "OK";
		});

		before((req, res) -> {
			res.header("Access-Control-Allow-Origin", "*");
			res.header("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS");
			res.type("application/json");
		});
		
		get("/langs", (req, res) -> langService.getAllLangs(), json());
		get("/langs/:id", (req,res) -> {
			String id = req.params("id");
			Lang lang = langService.getLang(Integer.parseInt(id));
			if(lang != null) {
				return lang;
			}
			res.status(400);
			return new ResponseError("No lang with id '%s' found",id);
		}, json());
		
		
		post("/langs",(req,res) -> {
			Lang jsonLang = g.fromJson(req.body(), Lang.class);
			if(langService.existsName(jsonLang.getName())) {
				res.status(400);
				return new ResponseError("lang with name '%s' already exists",jsonLang.getName());
			}
			jsonLang = langService.createLang(jsonLang.getName());
			return jsonLang;
		}, json());
		
		put("/langs/:id",(req,res) -> {
			Lang jsonLang = g.fromJson(req.body(), Lang.class);
			langService.updateLang(Integer.parseInt(req.params("id")), jsonLang.getName());
			return jsonLang;
		}, json());
			
		after((req, res) -> {
			res.type("application/json");
		});
		
		//Adds a message to the response body
		exception(IllegalArgumentException.class,(e,req,res) -> {
			res.status(400);
			res.body(toJson(new ResponseError(e)));
		});
		
	}
}
