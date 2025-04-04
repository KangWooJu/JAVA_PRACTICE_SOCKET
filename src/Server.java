import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    public static void main(String[] args) {

        BufferedReader in = null;
        BufferedWriter out = null;
        ServerSocket listener = null; // 클라이언트로부터 연결 요청을 기다리는 목적
        Socket socket = null; // 소켓 : 클라이언트와 데이터를 주고받는다.
        Scanner scanner = new Scanner(System.in); // 입력받을 Scanner 객체

        try {
            listener = new ServerSocket(9999); // 서버 소켓 생성 : 클라이언트의 접속을 기다릴 자신의 포트번호
            System.out.println("연결을 기다리고 있습니다......");
            socket = listener.accept(); // 클라이언트로부터 연결 요청 대기
            System.out.println("연결되었습니다.");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 소켓 스트림을 입력 스트림에 연결
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // 소켓 스트림을 출력 스트림에 연결
            while(true){
                String inputMessage = in.readLine();

                if(inputMessage.equalsIgnoreCase("bye")){
                    System.out.println("클라이언트에서 bye로 연결을 종료합니다.");
                    break;
                }

                System.out.println("클라이언트: "+inputMessage);
                System.out.print("보내기>>");
                String outputMessage = scanner.nextLine();
                out.write(outputMessage+"\n");
                out.flush();
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                scanner.close(); // Scanner 닫기
                socket.close(); // Socket 닫기
                listener.close(); // listener 닫기
            } catch (IOException e){
                System.out.println("클라이언트와 채팅 중 오류가 발생하였습니다.");
            }
        }
    }
}