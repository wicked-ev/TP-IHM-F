package logic;

import com.example.demo3.MainTimer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
    private int idTask;
    private String name;
    private Date deadline;
    private int numberOfPomodoro;
    private Status status;
    private final List<TimeSession> timeSessions;
    public static int inFocusTimes = 2;
    //jibhom ml database
    public static int shortBreakTime = 1;
    public static int longBreakTime = 1;
    public static int inFocusTime = 1;
    public static boolean autoStartBreak = false;
    public static boolean disableBreaks = false;
    public static boolean autoStartPomodoro = false;
    public static boolean autoStart = false;
    // id-time-session_genrator
    int id;

    // Constructor
    public Task(int idTask, String name,Date deadline, int numberOfPomodoro) {
        this.idTask = idTask;
        this.name = name;
        this.deadline = deadline;
        this.numberOfPomodoro = numberOfPomodoro;
        this.status = Status.TODO;
        this.timeSessions = new ArrayList<>(); // Initialize the list
        initList(inFocusTimes);
        if (autoStartPomodoro || autoStart){
            startTask();
        }
    }

    // Other methods...
    // Start the task with timers starting one after another
    public void startTask() {
        if (status == Status.TODO) {
            status = Status.IN_PROGRESS;
            for (TimeSession timeSession : timeSessions) {
                        timeSession.start();
                        // Wait for the current time session to complete before starting the next one
                        while (!timeSession.isTimeUp()) {
                            System.out.println("time: " + timeSession.getCurrent().toMinutes() + ":" + timeSession.getCurrent().toSecondsPart());
//                            MainTimer.mainTimer.setTimerText(timeSession.getCurrent().toMinutes() + ":" + timeSession.getCurrent().toSecondsPart());
                            try {
                                Thread.sleep(1000); // You can adjust the delay as needed (in milliseconds)
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
            }
            status = Status.DONE;
        } else {

            System.out.println("Task is already in progress or completed. check the status :" + status);
        }
    }


    // Mark the task as complete
    public void markAsComplete() {
        status = Status.DONE;
        pauseAllTimeSessions();
    }

    // Rename the task
    public void rename(String newName) {
        this.name = newName;
    }

    // Reset time sessions with a new number of pomodoros
    public void resetTimeSessions(int newPomodoro) {
        this.numberOfPomodoro = newPomodoro;
        resetAllTimeSessions();
    }


    // Reschedule task with a new deadline
    public void reschedule(Date newDeadline) {
        this.deadline = newDeadline;
    }


    // Create a new TimeSession
    private TimeSession createInFocusSession(int id, int duration) {
        return new InFocusS(id, duration);
    }
    private TimeSession createLongBreakSession(int id, int duration) {
        return new LongBreakS(id, duration);
    }
    private TimeSession createShortBreakSession(int id, int duration) {return new ShortBreakS(id, duration);}

    // Initialize the list of TimeSessions with different types
    private void initList(int inFocusTimes) {
        for (int i = 1; i <= numberOfPomodoro; i++) {
//            System.out.println(i % inFocusTimes);
            if (i % inFocusTimes == 0) {
//                System.out.println("long + in F");
                // Add an InFocusSession
                timeSessions.add(createInFocusSession(id, inFocusTime ));
                // Add a LongBreakSession after every 'inFocusTimes' InFocusSessions
                timeSessions.add(createLongBreakSession(id, longBreakTime ));
            }else if ( i  == numberOfPomodoro ) {
//                System.out.println("in F");

                // Add an InFocusSession
                timeSessions.add(createInFocusSession(id, inFocusTime ));
            } else {
//                System.out.println("in F + short");
                // Add an InFocusSession
                timeSessions.add(createInFocusSession(id, inFocusTime ));
                // After each InFocusSession, add a ShortBreakSession
                timeSessions.add(createShortBreakSession(id, shortBreakTime ));
            }
        }
        for (TimeSession timeSession : timeSessions) {
            System.out.println(timeSession.getLabel());
        }
    }

    // Add a new TimeSession to the list
    public void addSession() {
//        timeSessions.add(createTimer());
        numberOfPomodoro ++;
        if (numberOfPomodoro % inFocusTimes == 1) {
            // Add an InFocusSession
            timeSessions.add(new InFocusS(id, inFocusTime));
            // Add a LongBreakSession after every 'inFocusTimes' InFocusSessions
            timeSessions.add(createLongBreakSession(id, longBreakTime ));

        } else {
            // Add an InFocusSession
            timeSessions.add(new InFocusS(id, inFocusTime));
            // After each InFocusSession, add a ShortBreakSession
            timeSessions.add(new ShortBreakS(id, shortBreakTime));
        }
    }

    // Remove a TimeSession from the list
    public void removeTimer() {
        if (!timeSessions.isEmpty()) {
            timeSessions.remove(timeSessions.size() - 1); // Remove the last added TimeSession
        }
    }

    // Pause all time sessions
    private void pauseAllTimeSessions() {
        for (TimeSession timeSession : timeSessions) {
            timeSession.pause();
        }
    }

    // Reset all time sessions
    private void resetAllTimeSessions() {
        for (TimeSession timeSession : timeSessions) {
            timeSession.reset();
        }
    }

    public Status getStatus() {
        return status;
    }

    public int getNumberOfPomodoro() {
        return numberOfPomodoro;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setInFocusTime(int inFocusTime) {
        Task.inFocusTime = inFocusTime;
    }

    public void setInFocusTimes(int inFocusTimes) {
        this.inFocusTimes = inFocusTimes;
    }

    public void setLongBreakTime(int longBreakTime) {
        Task.longBreakTime = longBreakTime;
    }

    public void setShortBreakTime(int shortBreakTime) {
        Task.shortBreakTime = shortBreakTime;
    }

    public void setDisableBreaks(boolean disableBreaks) {
        Task.disableBreaks = disableBreaks;
    }

    public void setAutoStartBreak(boolean autoStartBreak) {
        Task.autoStartBreak = autoStartBreak;
    }

    public void setAutoStartPomodoro(boolean autoStartPomodoro) {
        Task.autoStartPomodoro = autoStartPomodoro;
    }
}
