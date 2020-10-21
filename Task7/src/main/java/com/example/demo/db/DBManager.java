package com.example.demo.db;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.util.ArrayList;

public class DBManager {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Long id = 6L;
    private static String desc = "Uneasy barton seeing remark happen his has. Am possible offering at contempt mr distance stronger an. Attachment excellence announcing or reasonable am on if indulgence. Exeter talked in agreed spirit no he unable do. Betrayed shutters in vicinity it unpacked in. In so impossible appearance considered mr. Mrs him left find are good. \n" +
            "\n" +
            "Is at purse tried jokes china ready decay an. Small its shy way had woody downs power. To denoting admitted speaking learning my exercise so in. Procured shutters mr it feelings. To or three offer house begin taken am at. As dissuade cheerful overcame so of friendly he indulged unpacked. Alteration connection to so as collecting me. Difficult in delivered extensive at direction allowance. Alteration put use diminution can considered sentiments interested discretion. An seeing feebly stairs am branch income me unable. \n" +
            "\n" +
            "Be at miss or each good play home they. It leave taste mr in it fancy. She son lose does fond bred gave lady get. Sir her company conduct expense bed any. Sister depend change off piqued one. Contented continued any happiness instantly objection yet her allowance. Use correct day new brought tedious. By come this been in. Kept easy or sons my it done. \n" +
            "\n" +
            "Guest it he tears aware as. Make my no cold of need. He been past in by my hard. Warmly thrown oh he common future. Otherwise concealed favourite frankness on be at dashwoods defective at. Sympathize interested simplicity at do projecting increasing terminated. As edward settle limits at in. \n" +
            "\n" +
            "Procuring education on consulted assurance in do. Is sympathize he expression mr no travelling. Preference he he at travelling in resolution. So striking at of to welcomed resolved. Northward by described up household therefore attention. Excellence decisively nay man yet impression for contrasted remarkably. There spoke happy for you are out. Fertile how old address did showing because sitting replied six. Had arose guest visit going off child she new. \n" +
            "\n" +
            "Mr oh winding it enjoyed by between. The servants securing material goodness her. Saw principles themselves ten are possession. So endeavor to continue cheerful doubtful we to. Turned advice the set vanity why mutual. Reasonably if conviction on be unsatiable discretion apartments delightful. Are melancholy appearance stimulated occasional entreaties end. Shy ham had esteem happen active county. Winding morning am shyness evident to. Garrets because elderly new manners however one village she. \n" +
            "\n" +
            "Both rest of know draw fond post as. It agreement defective to excellent. Feebly do engage of narrow. Extensive repulsive belonging depending if promotion be zealously as. Preference inquietude ask now are dispatched led appearance. Small meant in so doubt hopes. Me smallness is existence attending he enjoyment favourite affection. Delivered is to ye belonging enjoyment preferred. Astonished and acceptance men two discretion. Law education recommend did objection how old. \n" +
            "\n" +
            "Betrayed cheerful declared end and. Questions we additions is extremely incommode. Next half add call them eat face. Age lived smile six defer bed their few. Had admitting concluded too behaviour him she. Of death to or to being other. \n" +
            "\n" +
            "An country demesne message it. Bachelor domestic extended doubtful as concerns at. Morning prudent removal an letters by. On could my in order never it. Or excited certain sixteen it to parties colonel. Depending conveying direction has led immediate. Law gate her well bed life feet seen rent. On nature or no except it sussex. \n" +
            "\n" +
            "In show dull give need so held. One order all scale sense her gay style wrote. Incommode our not one ourselves residence. Shall there whose those stand she end. So unaffected partiality indulgence dispatched to of celebrated remarkably. Unfeeling are had allowance own perceived abilities. \n" +
            "\n";
    static{
        tasks.add(new Task(1L, "Complete Task 7", desc, Date.valueOf("2020-02-12"), false));
        tasks.add(new Task(2L, "Create Django website", desc, Date.valueOf("2019-01-12"), false));
        tasks.add(new Task(3L, "Create Bootstrap", desc, Date.valueOf("2018-10-12"), false));
        tasks.add(new Task(4L, "Complete Thymeleaf", desc, Date.valueOf("2017-02-12"), false));
        tasks.add(new Task(5L, "Complete .Net", desc, Date.valueOf("2016-02-12"), true));
    }

    public static void addTask(Task task){
        task.setId(id);
        tasks.add(task);
        id++;
    }


    public static boolean updateTask(Task task){
        for(int i = 0; i < tasks.size(); i++){
            if(task.getId() == tasks.get(i).getId()){
                tasks.get(i).setName(task.getName());
                tasks.get(i).setDescription(task.getDescription());
                tasks.get(i).setDeadlineDate(task.getDeadlineDate());
                tasks.get(i).setCompleted(task.isCompleted());
                return true;
            }
        }

        return false;
    }


    public static boolean removeTask(Long id){
        for(int i = 0; i < tasks.size(); i++){
            if(id == tasks.get(i).getId()){
                tasks.remove(i);
                return true;
            }
        }

        return false;
    }

    public static Task getTask(Long id){
        for(Task task: tasks){
            if(task.getId().equals(id)){
                return task;
            }
        }
        return null;
    }

    public static ArrayList<Task> getAllTasks(){
        return tasks;
    }
}
