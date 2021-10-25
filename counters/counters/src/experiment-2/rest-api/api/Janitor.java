package api;

import com.google.gson.Gson;

import java.util.ArrayList;

import java.util.List;

import static spark.Spark.*;

public class Janitor {
    static List<Todo> todosList = null;

    public static void main(String[] args) {
        port(8088);

        todosList = new ArrayList<>();
        after((req, res) -> {
            res.type("applications/json");
        });

        path("/todos", () -> {
            get("", (request, response) ->
                    "No to-dos found. Create one now."
            );

            get("/alltodos", (request, response) -> {
                String all = "";
                int counter = 0;
                for (Todo todo : todosList) {
                    counter++;
                    all += "Todo #" + counter + "\n";
                    all += todo.jSon() + "\n\n";
                }
                return all;
            });

            get("/:Id", (request, response) -> {
                Integer Id = Integer.parseInt(request.params(":Id"));
                if (Id != 0 && Id < todosList.size()) {
                    return todosList.get(Id).jSon();
                }
                else {
                    return("Invalid ID");
                }
            });

            post("", (request, response) -> {
                Todo todo = new Gson().fromJson(request.body(), Todo.class);
                todosList.add(todo);
                return todosList.get(todosList.size() - 1);
            });

            put("/:Id", (request, response) -> {
                Integer Id = Integer.parseInt(request.params(":Id"));
                if (Id != 0 && Id < todosList.size()) {
                    Todo updated = new Gson().fromJson(request.body(), Todo.class);
                    todosList.remove(Id.intValue());
                    todosList.add(Id, updated);
                    return("Update was a success!");
                }
                else {
                    return("Invalid ID");
                }
            });

            delete("/delete/:Id", (request, response) -> {
                Integer Id = Integer.parseInt(request.params(":Id"));
                if (Id != 0 && Id < todosList.size()) {
                    todosList.remove(Id.intValue());
                    return("Delete successful!");
                }
                else {
                    return("Invalid ID");
                }
            });
        });

    }
}
