package com.daisukeclinic.structure;

import com.daisukeclinic.model.Appointment;
public class AppointmentQueue {
    Appointment[] data = new Appointment[20];
    int size = 0;

    public void enqueue(Appointment a) {
        if (size >= data.length) {
            System.out.println("Queue is full. Cannot add more appointments.");
            return;
        }

        int i = size - 1;
        while (i >= 0 && data[i].getTimeInMinutes() > a.getTimeInMinutes()) {
            data[i + 1] = data[i];
            i--;
        }
        data[i + 1] = a;
        size++;
    }

    public Appointment dequeue() {
        if (size == 0) return null;
        Appointment a = data[0];
        for (int i = 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        return a;
    }

    public void display() {
        if (size == 0) {
            System.out.println("No upcoming appointments.");
            return;
        }
        System.out.println("____________________________________________________________\n");
        for (int i = 0; i < size; i++) {
            System.out.println(data[i]);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
}
