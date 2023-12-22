package com.luniun.luniun.time_taf;

import java.io.Serializable;

public class WorkEntry implements Serializable {
    private int hoursWorked;
    // Vous pouvez ajouter d'autres informations comme la date si nécessaire

    WorkEntry(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    int getHoursWorked() {
        return hoursWorked;
    }
}
