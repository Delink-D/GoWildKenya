/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import java.util.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
		public static void main(String[] args) {
			ProcessBuilder process = new ProcessBuilder();
			Integer port;
			if (process.environment().get("PORT") != null) {
				port = Integer.parseInt(process.environment().get("PORT"));
			} else {
				port = 4567;
			}
			setPort(port);
			
		staticFileLocation("/public");
		String layout       = "templates/layout.vtl";
		String layout_index = "templates/layout_index.vtl";
		String layout_admin = "templates/layout_admin.vtl";
		String layout_login = "templates/layout_login.vtl";

		// landing page route
		get("/", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("cats", Category.all());
			model.put("template", "templates/index.vtl");
			return new ModelAndView(model, layout_index);
		},new VelocityTemplateEngine());

		// route Places
		get("/places/:id", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			int place = Integer.parseInt(request.params(":id"));
			OurList lists = OurList.find(place);
			model.put("cats", Category.all());
			model.put("list", lists);
			model.put("template", "templates/places.vtl");
			return new ModelAndView(model, layout);
		},new VelocityTemplateEngine());

		// a single place page route
		get("/places/place/:id", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			// OurList lists = OurList.find(place);
			model.put("cats", Category.all());
			// model.put("list", lists);
			model.put("place", Places.find(Integer.parseInt(request.params(":id"))));
			model.put("template", "templates/place.vtl");
			return new ModelAndView(model, layout);
		},new VelocityTemplateEngine());

		// login to admin panel route
		get("/admin", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("template", "templates/admin-login.vtl");
			return new ModelAndView(model, layout_login);
		}, new VelocityTemplateEngine());

		post("/admin",(request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			String email = request.queryParams("email");
			String password = request.queryParams("password");
			Login newAdmin = new Login(email, password);
			if(newAdmin.login() == true){
				String url = String.format("/admin/dashboard");
				response.redirect(url);
			}else {
				String url = String.format("/admin");
				response.redirect(url);
			}
			return new ModelAndView(model, layout_admin);
		}, new VelocityTemplateEngine());

		// admin panel dashboard-page route
		get("admin/dashboard", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("template", "templates/dashboard.vtl");
			return new ModelAndView(model, layout_admin);
		}, new VelocityTemplateEngine());

		// add category route
		get("/admin/add-cat", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("cats", Category.all());
			model.put("template", "templates/form-addcat.vtl");
			return new ModelAndView(model, layout_admin);
		}, new VelocityTemplateEngine());

		post("/admin/add-cat", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			String catName = request.queryParams("catname");
			Category newCategory = new Category(catName);
			newCategory.save();
			String url = String.format("/admin/add-cat");
			response.redirect(url);
			return new ModelAndView(model, layout_admin);
		}, new VelocityTemplateEngine());

		
		// add list route
		get("/admin/add-list", (request,response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("lists", OurList.all());
			model.put("cats", Category.all());
			model.put("template", "templates/form-addlist.vtl");
			return new ModelAndView(model, layout_admin);
		}, new VelocityTemplateEngine());

        post("/admin/add-list", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			int catId = Integer.parseInt(request.queryParams("cat"));
			String listName = request.queryParams("listname");
			String listImage = request.queryParams("image");
			OurList newList = new OurList(listName, catId, listImage);
			newList.save();
			String url = String.format("/admin/add-list");
			response.redirect(url);
			return new ModelAndView(model, layout_admin);
		}, new VelocityTemplateEngine());


        // add place route
        get("/admin/add-place", (request,response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			// model.put("lists", Places.all());
			model.put("lists", OurList.all());
			model.put("places", Places.getAll());
			model.put("template", "templates/form-addplace.vtl");
			return new ModelAndView(model, layout_admin);
		}, new VelocityTemplateEngine());

        post("/admin/add-place", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			// int catId = Integer.parseInt(request.queryParams("cat"));
			int listId = Integer.parseInt(request.queryParams("list"));
			String placeName = request.queryParams("placename");
			int placeCost = Integer.parseInt(request.queryParams("cost"));
			String placeImg1 = request.queryParams("img1");
			String placeImg2 = request.queryParams("img2");
			double placeLat = Double.parseDouble(request.queryParams("lat"));
			double placeLng = Double.parseDouble(request.queryParams("lng"));
			String placeDesc = request.queryParams("desc");
			Places newPlace = new Places(listId,placeName,placeCost,placeLat,placeLng,placeImg1,placeImg2,placeDesc);
			newPlace.save();
			String url = String.format("/admin/add-list");
			response.redirect(url);
			return new ModelAndView(model, layout_admin);
		}, new VelocityTemplateEngine());


		// add admin route
		get("/admin/add-admin", (request,response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("admins", Admin.getAll());
			model.put("template", "templates/form-addadmin.vtl");
			return new ModelAndView(model, layout_admin);
		}, new VelocityTemplateEngine());

		post("/admin/add-admin", (request, response) -> {
			Map<String, Object> model = new HashMap<String, Object>();
			String admiNname 	= request.queryParams("adminname");
			String admiEmail 	= request.queryParams("adminemail");
			String adminPassword = request.queryParams("adminpass");
			Admin newAdmin = new Admin(admiNname, admiEmail, adminPassword);
			newAdmin.save();
			String url = String.format("/admin/add-admin");
			response.redirect(url);
			return new ModelAndView(model, layout_admin);
		}, new VelocityTemplateEngine());
	}
}
