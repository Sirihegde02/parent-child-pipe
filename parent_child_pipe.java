// NAME: SIRI HEGDE
// HOMEWORK-2 COMP512

// Question-1 statement:
// Write code to create a program of parent and child thread. The parent will send a message "COMP 512 pipe programming parent" via a pipe 
// to the child, then the child changes the case of each character and adds "CHILD" to the end of the message and send it back to the parent 
// via a pipe. The parent thread will then print the message receipt from the child. 

import java.io.*;

class PipeCommunication{
    public static void main(String[] args){
        try{
            //Piped streams facilitating inter-thread communication:
            final PipedOutputStream parentOut = new PipedOutputStream();
            final PipedInputStream childIn = new PipedInputStream(parentOut);

            final PipedOutputStream childOut = new PipedOutputStream();
            final PipedInputStream parentIn = new PipedInputStream(childOut);

            //The parent thread sends a message to the child thread and receives the modified response:
            Thread parentThread = new Thread(() -> {
                try{
                    String message = "COMP 512 pipe programming parent";
                    parentOut.write(message.getBytes());
                    parentOut.close();

                    byte[] buffer = new byte[1024];
                    int length = parentIn.read(buffer);
                    String receivedMessage = new String(buffer, 0, length);
                    System.out.println("Parent received: " + receivedMessage);
                }catch (IOException e){
                    e.printStackTrace();
                }
            });

            //The child thread reads the message from the parent, modifies it and sends it back:
            Thread childThread = new Thread(() -> {
                try{
                    byte[] buffer = new byte[1024];
                    int length = childIn.read(buffer);
                    String message = new String(buffer, 0, length);

                    String modifiedMessage = modifyMessage(message);
                    childOut.write(modifiedMessage.getBytes());
                    childOut.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            });

            parentThread.start();
            childThread.start();

            //Ensures both threads complete execution before the main program exits:
            parentThread.join();
            childThread.join();
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    //Method to chnage the case of each character and append "CHILD" to the message:
    private static String modifyMessage(String message) {
        StringBuilder modified = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isUpperCase(c)) {
                modified.append(Character.toLowerCase(c));
            } else if (Character.isLowerCase(c)) {
                modified.append(Character.toUpperCase(c));
            } else {
                modified.append(c);
            }
        }
        modified.append(" CHILD");
        return modified.toString();
    }
}