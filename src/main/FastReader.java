package main;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class FastReader {

    private int BUFFER_SIZE = 1 << 21;
    private int bufferPointer, bytesRead;
    private byte[] buffer = new byte[BUFFER_SIZE];
    private DataInputStream stream;

    public FastReader(String path) throws IOException {
        stream = new DataInputStream(new FileInputStream(path));
    }

    public WordObject readLine() throws IOException {
        byte[] charBuffer = new byte[32];
        int length = 0, character;
        while ((character = read()) != -1) {
            if (character == '\n' || character == 13)
                break;
            charBuffer[length++] = (byte) character;
        }
        return character == -1 ? null : new WordObject(charBuffer, length);
    }

    private void fillBuffer() throws IOException {
        bytesRead = stream.read(buffer, bufferPointer = 0, BUFFER_SIZE);
        if (bytesRead == -1){
            buffer[bufferPointer] = -1;
        }
    }

    private byte read() throws IOException {
        if (bufferPointer == bytesRead)
            fillBuffer();
        return buffer[bufferPointer++];
    }

    public void close() throws IOException {
        if (stream == null)
            return;
        stream.close();
    }

}
