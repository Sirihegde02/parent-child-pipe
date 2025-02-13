import java.io.*;

class PipeCommunication{
    public static void main(String[] args){
        try{
            final PipedOutputStream parentOut = new PipedOutputStream();
            final PipedInputStream childIn = new PipedInputStream(parentOut);

            final PipedOutputStream childOut = new PipedOutputStream();
            final PipedInputStream parentIn = new PipedInputStream(childOut);

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

            parentThread.join();
            childThread.join();
        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

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