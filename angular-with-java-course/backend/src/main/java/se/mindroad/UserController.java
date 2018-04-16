package se.mindroad;
import static spark.Spark.*;

import com.google.gson.Gson;

import static se.mindroad.JsonUtil.*;

import spark.Request;
import spark.Response;
import spark.Route;

public class UserController {
	Gson g = new Gson();
	public UserController(final UserService userService) {
		
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
			res.header("Access-Control-Allow-Methods", "POST,PUT,GET,DELETE,OPTIONS");
			res.type("application/json");
		});
		
		get("/users", (req, res) -> userService.getAllUsers(), json());
		get("/users/:id", (req,res) -> {
			String id = req.params("id");
			User User = userService.getUser(Integer.parseInt(id));
			if(User != null) {
				return User;
			}
			res.status(400);
			return new ResponseError("No User with id '%s' found",id);
		}, json());
		
		
		post("/users",(req,res) -> {
			User jsonUser = g.fromJson(req.body(), User.class);
			if(userService.existsName(jsonUser.getName())) {
				res.status(400);
				return new ResponseError("User with name '%s' already exists",jsonUser.getName());
			}
			jsonUser = userService.createUser(jsonUser.getName(), jsonUser.getEmail());
			return jsonUser;
		}, json());
		
		put("/users/:id",(req,res) -> {
			User jsonUser = g.fromJson(req.body(), User.class);
			userService.updateUser(Integer.parseInt(req.params("id")), jsonUser.getName(), jsonUser.getEmail());
			return jsonUser;
		}, json());
		
		delete("/users/:id", (req,res) -> {
			int id = Integer.parseInt(req.params("id"));
			User temp = userService.getUser(id);
			if(!userService.existsID(id)) {
				res.status(400);
				return new ResponseError("User with id '%s' does not exist",Integer.toString(id));
			}
			userService.deleteUser(id);
			return g.toJson(temp);
		});
			
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
