package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.LinkedList;

public class EchoClient1 {
    private LinkedList<ByteBuffer> outq;

    public EchoClient1(LinkedList<ByteBuffer> outq) {
        this.outq = outq;
    }

    public LinkedList<ByteBuffer> getOutputQueue() {
        return outq;
    }

    public void enqueue(ByteBuffer bb) {
        outq.addFirst(bb);
    }

    public static void main(String[] args) throws IOException {
        Socket client = null;
        PrintWriter writer = null;
        BufferedReader reader = null;
        try {
            client = new Socket();
            client.connect(new InetSocketAddress("localhost", 8000));
            writer = new PrintWriter(client.getOutputStream(),true);
            writer.println("Hello!");
            writer.flush();
            
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("from server:" + reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(reader != null)reader.close();
            if(writer != null)writer.close();
            if(client != null)client.close();
        }
    }

}
