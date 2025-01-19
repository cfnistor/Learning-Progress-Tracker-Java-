package tracker;

import java.util.Scanner;

public enum Command {
    ADD_STUDENT("add students") {
        @Override
        public void execute() {
            Menu.addStudents();
        }
    },
    LIST("list") {
      @Override
      public void execute() {
          Menu.listStudents();
      }
    },
    ADD_POINTS("add points") {
        @Override
        public void execute() {
            Menu.addPoints(ScannerUtil.getScanner());
        }
    },
    FIND("find") {
        @Override
        public void execute() {
            Menu.find(ScannerUtil.getScanner());
        }
    },
    BACK("back") {
        @Override
        public void execute() {
            System.out.println("Enter 'exit' to exit the program");
        }
    },
    UNKNOWN("unknown") {
        @Override
        public void execute() {
            System.out.println("Error: unknown command!");
        }
    };

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public abstract void execute();

    public static Command fromString(String input) {
        for (Command cmd : Command.values()) {
            if (cmd.command.equals(input)) {
                return cmd;
            }
        }
        return UNKNOWN;
    }
}
