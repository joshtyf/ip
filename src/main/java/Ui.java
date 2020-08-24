class Ui {
    final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    final String LINE = "------------------------";

    public void showStartScreen() {
        System.out.println(LINE);
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println(LINE + "\n");
    }

    public void showErrorScreen(String errorMessage) {
        System.out.println(LINE);
        System.out.println("Oh no, something went wrong!");
        System.out.println(errorMessage);
        System.out.println(LINE);
    }
}