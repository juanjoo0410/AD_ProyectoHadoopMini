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
        frmMain frm = new frmMain();
        frm.setTitle("Hadoop Mini");
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
//        MapReduceTasks.executeTask1("result_task_1.txt", 5);
    }
}
