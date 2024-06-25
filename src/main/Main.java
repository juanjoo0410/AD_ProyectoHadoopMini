package main;

import framework.model.Pair;
import framework.model.Tarea;
import gui.frmMain;
import java.util.Collections;
import tasks.MapReduceTasks;

/**
 *
 * @author JuanJoo
 */
public class Main {

    public static void main(String[] args) {
//        frmMain frm = new frmMain();
//        frm.setTitle("Hadoop Mini");
//        frm.setLocationRelativeTo(null);
//        frm.setVisible(true);
        MapReduceTasks.executeTask6("result_task_6.txt", 4);
    }
}
