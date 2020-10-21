package com.example.demo.controller;

import com.example.demo.db.DBManager;
import com.example.demo.db.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;

@Controller
public class HomeController {

    @GetMapping(value="/")
    public String index(Model model,
                        @RequestParam(name = "task_name", defaultValue = "", required = false) String name,
                        @RequestParam(name="deadline_from", defaultValue = "1000-10-10", required = false) Date date_from,
                        @RequestParam(name="deadline_to", defaultValue = "2021-10-10", required = false) Date date_to,
                        @RequestParam(name="task_completed", defaultValue = "false", required = false) boolean complete){
        ArrayList<Task> tasks = DBManager.getAllTasks();

        System.out.println(false + "compare with " + complete + " = " + Boolean.compare(false,complete) );
        for(Task task: tasks){
            if(task.getDeadlineDate().compareTo(date_from) >= 0 && task.getDeadlineDate().compareTo(date_to) <= 0 && Boolean.compare(task.isCompleted(),complete) == 0) {
                System.out.println(task.getId() + ": " + task.isCompleted() + "compare with " + complete + " = " + Boolean.compare(false, complete));
                tasks.remove(task);
            }
        }

//        tasks.removeIf(task -> task.getDeadlineDate().compareTo(date_from) >= 0 && task.getDeadlineDate().compareTo(date_to) <= 0 && Boolean.compare(task.isCompleted(),complete) == 0);



        if(! name.equals("")) {
            tasks.removeIf(task -> task.getName().contains(name));
            System.out.println("I am here");
        }


        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping(value="/about")
    public String about(){
        return "about";
    }

    @PostMapping(value="/add_task")
    public String addTask(@RequestParam(name = "task_name", defaultValue = "No Name") String name,
                          @RequestParam(name="task_description", defaultValue = "No Description") String description,
                          @RequestParam(name="task_deadline", defaultValue = "1000-01-01") Date date,
                          @RequestParam(name="task_completed", defaultValue = "false") boolean complete){
        DBManager.addTask(new Task(name,description,date,complete));
        return "redirect:/";
    }


    @PostMapping(value="/update_task")
    public String updateTask(@RequestParam(name = "task_id", defaultValue = "-1") Long id,
                          @RequestParam(name = "task_name", defaultValue = "No Name") String name,
                          @RequestParam(name="task_description", defaultValue = "No Description") String description,
                          @RequestParam(name="task_deadline", defaultValue = "1000-01-01") Date date,
                          @RequestParam(name="task_completed", defaultValue = "false") boolean complete){

        if(DBManager.updateTask(new Task(id,name,description,date,complete)))
            return "redirect:/details/"+id;
        else
            return "redirect:/";

    }

    @PostMapping(value="/delete_task")
    public String deleteTask(@RequestParam(name = "task_id", defaultValue = "-1") Long id){
        if(DBManager.removeTask(id))
            return "redirect:/";
        else
            return "redirect:/details/"+id;
    }

    @GetMapping(value="/details/{id}")
    public String taskDetails(Model model,@PathVariable(name="id") Long id){
        Task task = DBManager.getTask(id);
        if(task != null) {
            model.addAttribute("task", task);
            return "details";
        } else {
            return "error";
        }
    }
}
