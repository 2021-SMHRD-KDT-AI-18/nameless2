package 개발자괴롭히기;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javazoom.jl.player.MP3Player;

public class DeveloperMainSystem {

   private static final Scanner Scanner = null;

   public static void main(String[] args) {

      // MVC Pattern (Design Pattern 일종 )
      // Model View Controller
      // Model : 데이터를 묶고 데이터베이스에 접근하는 역할(DAO)
      // View : Client 화면에 보여주고 안내하고 입력받는 역할(Main System)
      // Controller : View 와 Model 을 이어주는 역할, 조종역할

      Scanner sc = new Scanner(System.in);
      DeveloperController controller = new DeveloperController();
      // 모든 기능에서 사용할 수 있도록 전역변수 dto, dao 생성

      DeveloperDTO dto = null;
      CharacterDTO player = null;
      CharacterDTO enemy = null;
      String id = "0";

      MP3Player mp3 = new MP3Player();
      mp3.play("C:\\\\Users\\\\smhrd\\\\Downloads\\\\BGM\\\\포캣몬스터 레드그린 무지개 게임 코너 BGM.mp3");

      while (true) {
         // CRUD (create / read / update / delete)
    	 System.out.println("\n"
    			 
    			 
    	 		+ "⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⡉⢧⣉⠳\n"
    	 		+ "⡙⢆⡙⢦⠙⣆⡙⢦⠙⣆⡙⢦⠙⣆⡙⢦⠙⣆⡙⢦⠙⣆⡙⢦⠙⣆⡙⢦⠙⣆⡙⢦⠙⣆⡙⢦⡙⢆⡙⢦⢹⣢⠙⢦⡙⢆⡙⢦⠙⣆⡙⢦⠙⣆⡙⢦⠙⣆⡙⢦⡙⠦⡙⢆⡙⢦⠙⣆⡙⢦⠙⣆⡙⢦⠙⣆⡙⢦⠙⣆⡙⢦⠙⣆⡙⢦⠙⠦⣌⠳\n"
    	 		+ "⡘⢦⡙⠦⡙⢤⡙⠦⡙⢤⡙⢦⡙⢤⡙⢢⡙⢤⡙⢢⡙⢤⡙⠦⡙⢤⡙⢢⡙⢤⡙⢢⡙⢤⡙⢦⡘⢦⣙⣦⢻⣿⣽⡢⢜⢢⡙⢦⡙⣴⣯⣆⠹⢤⡙⢢⡙⢤⡙⢦⡘⢥⡙⢦⡙⠦⡙⢤⡙⢢⡙⢤⡙⢦⡙⢤⡙⠦⡙⢤⡙⢢⡙⢤⡙⢢⡙⠲⣌⠳\n"
    	 		+ "⡙⠦⣑⢣⡙⠦⡙⢦⡙⢦⡉⢖⡘⢦⡙⢦⡙⠦⡙⢦⠙⢦⡙⢦⡙⠦⡙⢦⠙⢦⡙⠦⡙⢦⡉⢦⡙⣶⣾⣿⣿⣿⣿⣿⣾⣦⡙⣶⣯⣿⣿⣯⡞⢤⡙⢦⡙⢦⡉⢦⡙⢢⢍⠲⣌⢣⡙⠦⡙⢦⠙⢦⡉⢖⡘⢦⡙⢦⡙⠦⡙⢦⡙⠦⡙⢦⣉⠳⣌⠲\n"
    	 		+ "⡙⢦⡑⢦⡙⢦⡙⠦⡑⢦⡙⢦⡙⠦⡑⢦⡙⢦⡙⠦⡙⢦⡘⢦⡑⢣⡙⠦⡙⢦⡘⢥⣹⣾⣷⡢⣑⢻⣿⣿⣿⣿⣿⣿⣿⣿⣷⣹⣿⣿⣿⣿⣿⠤⡙⢦⡘⢦⢙⢢⡙⣶⡌⠳⣄⠣⡜⣡⠙⢦⡙⠦⣙⢢⡙⢦⡘⢦⡑⢣⡙⢦⡘⢥⡙⠦⣌⠳⣨⠱\n"
    	 		+ "⡙⢤⡙⢦⡘⢦⡑⢣⡙⠦⡑⢦⡙⢦⡙⢦⡑⢦⡙⢦⡙⢆⡍⢦⡙⢦⠙⢦⡙⠦⡙⢦⣹⣿⣿⡿⢄⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⡰⣉⠦⣙⠢⣍⣶⣿⣿⣿⢣⢌⠳⡘⡤⢛⠤⡙⢦⡑⢦⡙⢤⡙⢢⡙⢦⢙⢢⡙⠦⣙⠲⣌⠱⣂⠳\n"
    	 		+ "⣙⠢⡍⢦⡙⢤⡙⢦⠙⢦⡙⠦⡑⢦⡙⠴⡘⢦⡑⢦⡉⢖⡘⢦⡑⢎⡙⠦⣑⢣⡙⣦⣿⣿⢯⡑⢎⡹⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡯⢔⡱⢌⠲⣡⣳⣾⣿⣿⡿⣫⡑⢎⡱⠱⣌⠣⢎⡱⢢⡙⢆⡙⢦⠙⢦⡙⢢⠍⢦⡙⢦⢡⠓⣌⠳⢌⡓\n"
    	 		+ "⡌⢳⡘⢦⡉⢦⡙⢢⡙⢦⠙⢦⡙⠦⡙⢦⡙⢆⡍⢦⡙⢦⡙⢢⠍⢦⡙⢦⣵⣲⣏⣿⣿⣯⢆⡙⢦⠱⡩⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⡑⢎⡔⣂⣶⣿⣿⣿⣿⣿⣿⣿⡑⢎⡔⢫⠔⡩⢦⡑⢣⠜⣢⠙⢦⡙⠦⡙⢦⡙⢦⡑⢎⠦⡙⡤⢋⠦⡙\n"
    	 		+ "⣌⠣⡜⢢⡙⢆⡙⢦⡙⢢⡙⢦⡑⢣⡙⢆⡍⢲⡘⢦⡑⢦⡙⢦⡙⢦⠹⡟⣻⢿⣿⣿⣿⣿⣿⣞⣦⣣⠑⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⡴⣉⢖⢬⣿⣿⣿⣿⣿⣿⣿⡿⢋⡕⢪⠜⣡⢚⡱⢢⡙⢦⡙⢤⢋⠦⡙⢦⡙⢦⡘⢦⡙⣌⠲⣡⠣⣍⠲⣉\n"
    	 		+ "⢤⠓⣌⠣⡜⢢⡙⢆⡙⢦⢙⢢⡙⠦⡙⢦⡘⢥⠚⡤⡙⢦⡘⢦⡉⢆⠳⡘⡔⢪⡑⢻⡙⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⣙⢣⣚⣴⣟⡈⢄⣻⣿⣿⣿⣿⣿⣿⣿⠟⣌⢣⠚⡤⢣⠜⣡⠚⡤⣉⠄⣋⠖⡩⢆⡱⢢⡙⢦⡘⢤⠓⡔⢣⡌⠳⣌\n"
    	 		+ "⢦⡙⢤⢋⡜⣡⠚⣤⠙⢦⡉⢦⡙⢦⡙⢆⡙⢦⡙⠴⣉⠦⡙⢤⡙⣌⠣⡕⢪⠱⡘⡥⢊⡕⢢⢍⡛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⣿⣾⣥⣯⣿⣿⣿⣿⣿⣶⣯⡔⢣⡙⡔⢣⠎⡥⢋⠴⣡⠚⡤⢋⡴⢣⠜⣡⠚⡤⡙⢦⡙⣌⠣⡜⡱⢌\n"
    	 		+ "⣢⠙⣆⠣⡜⢤⢋⠴⣉⠦⡙⢆⡍⢲⡘⠬⡜⢢⡙⢦⡑⢎⡱⢊⠴⣊⠵⡘⢥⢃⠳⣌⠣⡜⡡⢎⡜⡡⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡌⢣⠜⣌⢣⠚⡴⠉⠄⢡⢋⠴⣉⠖⣡⠞⣡⢋⠴⣉⠦⡱⢌⠳⣌⠱⣊\n"
    	 		+ "⢤⠋⡔⢣⠜⡢⢍⠲⡡⢎⠱⣊⠜⡡⢎⡱⢌⠣⡜⢢⡉⢦⠱⣉⠖⡡⢎⠱⣊⢌⡓⠬⡱⢌⡱⢊⠴⡑⠦⡙⠿⡿⣿⣿⣿⣿⣿⡿⣿⣜⡰⢡⠎⢭⠹⡛⢟⢻⡿⣿⣿⣿⣧⠜⣡⠚⢤⠣⣙⠰⣩⠐⡌⢎⠲⣡⠚⢤⢋⠤⢋⡒⠥⣊⠕⡪⡑⡌⡓⣌\n"
    	 		+ "⠆⣙⠘⡤⢃⠱⢊⡑⢆⣉⠒⣡⢊⡑⢢⠑⣊⠱⢨⠑⡸⢀⡓⢌⡸⢐⠣⠱⣈⠒⡌⣑⢂⢃⡒⡉⠦⡑⢣⠉⣆⠱⡉⠬⡑⣩⣿⣷⣿⡿⣿⡃⠜⢢⠑⡩⢌⠒⡔⢢⠩⢌⡉⠞⡠⢍⢂⡓⢌⠱⢠⠋⡜⢨⠑⢢⣉⠒⡌⢊⡅⢚⠰⡁⢎⢡⠱⣈⠱⢠\n"
    	 		+ "⡘⢄⠣⡐⡉⢆⠣⠘⡄⢢⠉⡔⠢⡘⢄⠣⢌⠒⡡⢊⠱⡈⠔⡊⠔⡡⢊⠱⣀⠣⡘⢄⠊⡔⠰⡑⢢⠑⢢⠑⡄⠣⢌⠱⡐⣿⡟⣨⢿⣧⡝⢠⠋⠤⡉⠔⡌⡘⢄⠣⡘⠤⡑⢊⠔⡌⢢⠘⠤⡉⢆⠱⡈⢆⡉⠆⡄⠣⢌⠡⡘⢌⠢⡑⢌⠢⡑⠤⡉⠆\n"
    	 		+ "⠡⠌⡐⠡⢘⠠⢊⠡⠌⢂⡑⠤⠑⠐⠈⠂⠈⠢⢁⠆⡑⠌⡰⢁⠊⠤⢁⠒⠄⡑⠰⠈⠂⠈⠡⠐⠡⡘⠄⢊⠄⠣⢈⠔⡐⠙⡛⠛⡈⠩⠐⠁⠘⠠⠑⡨⠐⠌⢂⠆⡑⠤⠑⡨⢐⠨⠐⠌⠂⠑⠀⠃⠌⠤⢈⠒⣈⠑⠌⡐⠡⢂⠡⠌⠂⠔⠁⠂⠑⢈\n"
    	 		+ "⠀⢂⠡⠘⠀⢂⠂⠔⡈⠄⡐⠀⠀⠀⠀⢀⠀⣁⠂⠐⠄⠒⠀⠂⠌⡐⠂⠌⡐⠀⠀⠀⠀⢀⢀⠈⡄⠐⠌⡠⠌⢂⠁⠢⢈⠔⠠⠁⠀⠀⠀⢀⠀⠄⡁⠠⢁⠊⠄⠂⠰⢀⠃⡐⠄⠂⠁⠀⠀⠀⡀⠠⠌⡐⠠⠒⠠⠈⢂⠌⡐⢂⠡⠈⠀⠀⠀⠀⡀⠠\n"
    	 		+ "⠈⠀⠄⠀⢀⠀⠠⠐⠀⠄⢀⠀⠄⢀⠰⢀⡁⠠⠀⢁⠈⠀⠠⠀⡀⠄⠈⡀⠀⠄⢀⠀⡐⡀⢂⠀⠐⠀⢂⠀⠀⡀⢀⠁⠠⠈⠀⠄⡀⢀⢀⠂⠌⠐⠀⠁⠠⠀⠀⡀⠄⠠⠀⠄⠀⢂⠀⡀⢀⢂⠐⠁⠠⠀⢂⠁⠀⢀⠀⠠⠀⠄⠀⢂⠀⡀⢀⠂⠤⠁\n"
    	 		+ "⠌⠰⢈⡐⠂⠈⠀⠠⠈⠀⠄⠊⡐⠠⢂⠂⡐⠤⠈⡄⠊⠄⠡⠐⠀⠀⠂⠀⠌⠐⠂⠌⡐⠠⢁⠂⢌⠐⠂⠤⠁⠄⠀⠀⠂⠈⠀⠒⡀⢂⠂⠌⢂⠡⠌⢂⠡⠌⡐⠀⠠⠁⠀⠈⠀⠂⠰⠀⣁⠢⢈⢂⠁⠆⠂⢌⠐⠠⠈⠀⠐⠀⠁⠀⠒⠠⠁⠌⡄⠡\n"
    	 		+ "⠌⠐⠀⠀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⢂⢁⠂⠡⠀⡁⠈⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⡁⠢⠌⢀⠊⠈⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⢈⠐⡈⡐⠈⠄⠂⠀⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠐⠂⡄⠊⠠⠉⠀⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠂⠤⢁\n"
    	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠐⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠐⠀⠂\n"
    	 		+ "⠀⠀⠀⠀⠀⠀⡀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⢀⠀⠀⠀⠀⠀⠤⣃⠄⠁\n"
    	 		+ "⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⢀⠁⠀⠀\n"
    	 		+ "");
         System.out.println("======1.회원등록======");
         System.out.println("======2.로그인=======");
         System.out.println("======3.회원탈퇴======");
         System.out.print(" >>> ");
         int menu = sc.nextInt();

         if (menu == 1) {
            System.out.println("======회원등록======");
            System.out.print("======ID입력 : ");
            id = sc.next();
            System.out.print("======PW입력 : ");
            String pw = sc.next();

            dto = new DeveloperDTO(id, pw);
            int cnt = controller.join(dto);

            if (cnt > 0) {
               System.out.println("======회원 등록 성공======");
            } else {
               System.out.println("======회원 등록 실패======");
            }

         } else if (menu == 2) {
            System.out.println("======로그인======");
            System.out.print("======ID 입력 : ");
            id = sc.next();
            System.out.print("======PW 입력 : ");
            String pw = sc.next();

//            한번에 로그인 하려면 새로운 메소드를 하나 묶어서 만들면 됨
//            new MemberDTO(id, pw);

            DeveloperDTO info = controller.login(id, pw);

            if (info != null) {
               System.out.println("======" + info.getId() + "님 환영합니다.======");
               break;
            } else {
               System.out.println("======정확한 정보를 입력해주세요.======");

            }
         } else if (menu == 3) {
            System.out.println("======회원탈퇴======");
            System.out.print("======삭제할 아이디를 입력해주세요 : ");
            id = sc.next();
            int cnt = controller.delete(id);

            if (cnt > 0) {
               System.out.println("======탈퇴성공======");
            } else {
               System.out.println("======탈퇴실패======");

            }
         }
      }

      while (true) {
         mp3.stop();
         mp3.play("C:\\\\Users\\\\smhrd\\\\Downloads\\\\BGM\\\\포켓몬스터 레드그린 3번도로 BGM.mp3");
         System.out.println("======1.게임시작======");
         System.out.println("======2.랭킹확인======");
         System.out.println("======3.캐릭터 삭제======");
         int menu = sc.nextInt();

         // 1. 게임시작
         if (menu == 1) {
            break;
         }
         // 2. 랭킹

         else if (menu == 2) {
            System.out.println("======랭킹 조회======");
            ArrayList<String> list = controller.CharacterList();// 나중에 characterDTO로 변경
            ArrayList<Integer> salarylist = controller.salaryList();

            System.out.println("닉네임\t연봉");
            for (int j = 1; j <= list.size(); j++) {
               for (int i = 0; i < list.size(); i++, j++) {
                  System.out.print(j + "위\t");
                  System.out.print(list.get(i) + "\t");
                  System.out.print(salarylist.get(i) * 100 + "만원\t");
                  System.out.println();
               }
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
         }
         // 3 . 캐릭터 삭제
         else if (menu == 3) {
            System.out.println("======캐릭터 삭제======");
            System.out.print("======삭제할 닉네임을 입력해주세요 : ");
            String name = sc.next();
            int cnt = controller.cDelete(name);

            if (cnt > 0) {
               System.out.println("======삭제 성공======");
            } else {
               System.out.println("======삭제 실패======");

            }
         }

      }
      mp3.stop();
      mp3.play("C:\\\\Users\\\\smhrd\\\\Downloads\\\\BGM\\\\포켓몬스터 레드그린 오박사연구소 BGM.mp3");
      System.out.print("======난이도를 선택하세요 (1: 쉬움, 2: 중간, 3: 어려움): ");
      int difficulty = sc.nextInt();
      int statTotal = 0;
      switch (difficulty) {
      case 1:
         statTotal = 120;
         break;
      case 2:
         statTotal = 100;
         break;
      case 3:
         statTotal = 80;
         break;
      case 9:
         statTotal = 5000;
         break;
      }

      // 캐릭터 생성 및 능력치 분배

      System.out.println("======캐릭터를 생성합니다.======");
      System.out.print("======이름을 입력해주세요 :");
      String name = sc.next();
      System.out.println("======능력치를 분배해주세요======");
      System.out.println("======1.체력 2.지능 3.신앙력 4.마력 5.정신력======");
      System.out.println("======체력 : 코딩을 위한 기초 능력======");
      int health = 50 + CharacterDTO.getStatInput(sc, "체력", statTotal);
      statTotal -= (health - 50);
      System.out.println("======지능 : 코딩 공격력======");
      int intelligence = CharacterDTO.getStatInput(sc, "지능", statTotal);
      statTotal -= intelligence;
      System.out.println("======신앙력 : 코딩 공격을 방어하는 능력======");
      int faithPower = CharacterDTO.getStatInput(sc, "신앙력", statTotal);
      statTotal -= faithPower;
      System.out.println("======마력 : 특수 공격력======");
      int magicPower = CharacterDTO.getStatInput(sc, "마력", statTotal);
      statTotal -= magicPower;
      System.out.println("======신앙력 : 치명타 확률 + 데미지======");
      int mentality = CharacterDTO.getStatInput(sc, "정신력", statTotal);
      statTotal -= mentality;
      int salary = 0;

      player = new CharacterDTO(name, health, intelligence, faithPower, magicPower, mentality, salary);
      mp3.stop();

      // 배틀 시스템

      // 적의 스텟 설정
      for (int level = 1; level <= 3 && player.getHp() > 0; level++) {
    	  System.out.println("\nLevel " + level + " 적이 코딩 승부를 걸어왔다"); // 상대 스텟 분배
         if (level == 1) {
        	 System.out.println("\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⣤⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⢤⡚⡝⠈⠉⠉⠙⠳⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠰⣏⣸⠁⣀⣀⡠⠌⠀⠙⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀  ⠀⠀⠀⠀⠀⠀⠀⢘⣿⣽⣏⡀⠀⠈⠋⠱⣟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⣀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢴⣿⢠⠉⣏⠙⣻⣟⣦⣶⡴⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⣠⠞⡶⣂⢀⡼⠶⢦⡀⣠⠞⡳⣄⡀⡸⠶⢇⡀⣠⠞⡶⣄⠀⡤⠷⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠓⡳⣀⡣⠴⣌⡴⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⢻⡐⢁⠟⠸⣃⢊⡱⠇⠻⡐⡑⡼⠑⢇⡙⡸⠇⢹⢂⠑⡾⠀⢟⡘⡘⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣶⠁⣍⠳⢻⣏⣽⡦⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠉⠉⠀⠀⠈⠉⠀⠀⠀⠉⠉⠀⠀⠈⠉⠁⠀⠀⠈⠉⠀⠀⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⢰⢩⣿⢷⣶⣿⣿⣿⣯⣗⡂⡙⡦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⢿⣿⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠓⠀⣹⡾⣽⣿⣿⣿⣷⣻⣷⠎⠀⢛⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠈⠛⠛⠛⠒⠓⠒⠓⠒⠓⠒⠓⠒⠓⠒⠓⠒⠓⠒⠓⠒⠓⠒⠓⠒⠓⠒⠓⠒⠓⠒⠓⠒⠓⠒⠓⠒⠓⠒⠓⠛⠛⠛⠓⠂⠀⠀⠀⠀⠀⠀⠀⠀⠘⣷⠦⣼⡷⣏⣿⣿⣿⣿⡷⢿⣡⡤⠎⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡟⠰⣿⣛⣉⣻⣛⣿⣿⣴⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣷⣦⣿⣻⢷⣯⣟⡾⣽⠖⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠹⠤⠋⡿⢧⢟⡏⣾⡽⣻⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢼⡟⡜⣮⠃⠿⣧⠽⣃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠺⣏⣷⡟⠀⢸⣯⣟⣯⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣴⣂⣒⣒⠒⠂⠤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣲⣿⡽⣧⠀⢠⣼⡞⣥⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠿⣭⡿⣿⣿⣿⣿⣶⣶⡌⠵⣢⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠨⢷⣉⡽⠛⠀⠈⠻⢟⣩⡵⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣺⢯⣛⣿⣽⣿⣿⣿⣿⣿⣿⣿⡱⣂⡗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣏⢿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣧⢹⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣼⣷⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡷⡜⣿⡿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⠿⠽⢿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢛⡿⢏⠴⠱⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣿⣿⣿⣿⣿⣿⣿⣿⠀⠃⠀⠀⠈⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠙⣿⣿⣿⣿⣿⠟⣋⠧⠀⠀⣀⡴⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⠖⢩⡙⢾⣿⡿⠋⡜⡸⢄⣫⢦⡐⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠂⠁⠀⠀⡘⠩⢿⡐⠀⠀⣉⣾⣧⡀⣀⡀⢀⠶⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⠀⠀⢀⣀⣀⠀⠀⣀⣀⡀⠀⢀⣀⣀⠀⠀⣀⣀⠀⠀⢀⣀⡀⠀⠀⠀⠀⠀⠀    ⠀⣶⣦⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡿⣶⢶⣴⣌⡤⠑⠠⡙⠶⣆⣼⣿⡔⣻⡀⠙⣿⢶⣄⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⢉⡝⣳⢰⡏⣩⠝⡦⢼⣉⢭⢳⢰⣏⡩⡝⡆⣾⣉⢭⢻⡄⣎⡹⣙⡆⠀⠀⠀⠀  ⠀⠀⣿⣿⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣨⡗⣼⡿⣾⣝⡞⣟⢻⣧⣴⢃⢎⡿⣿⠟⢱⣿⣿⣳⠒⢯⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⣤⣤⠊⠈⢢⣤⡔⠁⠉⢦⣤⠎⠈⢢⣤⠔⠃⠑⢢⣤⠊⠁⢣⣤⠖⠉⠀⠀⠀ ⠀ ⠀⠀⣿⣿⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡰⢟⡹⣜⢲⡽⣺⣽⣹⢆⡳⢎⠿⣟⡿⣁⠀⣁⠟⠛⠳⢢⠜⡻⡀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⠀  ⣿⣿⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⢃⢇⡲⡭⢞⣳⡽⣚⣯⣿⣼⢏⡾⣼⠃⠈⣱⠞⣳⣦⡴⠰⠞⠡⠃⠀⠀⠀⠀⢠⣤⣦⣷⣿⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣴⣿⣿⠟⠀⠀⠀⠀⠀\n"
        	 		+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠈⠈⠉⠀⠉⠁⠉⠉⠉⠉⠉⠉⠉⠁⠉⠉⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        	 		+ "");
            mp3.play("C:\\\\Users\\\\smhrd\\\\Downloads\\\\BGM\\\\포켓몬스터 레드그린 야생포켓몬 배틀 BGM.mp3");
            int hp = 150;
            int intell = 10;
            int fp = 15;
            int mp = 15;
            int ment = 15;
            enemy = new CharacterDTO("pbk mk1", hp, intell, fp, mp, ment, 0);
         } else if (level == 2) {
            mp3.stop();
            mp3.play("C:\\\\Users\\\\smhrd\\\\Downloads\\\\BGM\\\\포켓몬스터 레드그린 챔피언 배틀 BGM.mp3");
            int hp = 200;
            int intell = 40;
            int fp = 20;
            int mp = 200;
            int ment = 30;
            enemy = new CharacterDTO("pbk mk2", hp, intell, fp, mp, ment, 0);
         } else if (level == 3) {
            mp3.stop();
            mp3.play("C:\\\\Users\\\\smhrd\\\\Downloads\\\\BGM\\\\포켓몬스터 레드그린 체육관 관장 배틀 BGM.mp3");
            int hp = 250;
            int intell = 20;
            int fp = 20;
            int mp = 30;
            int ment = 20;
            enemy = new CharacterDTO("pbk mk3", hp, intell, fp, mp, ment, 0);
         }
         // 적의 공격행동 설정
         while (player.getHp() > 0 && enemy.getHp() > 0) {
            Random rand = new Random();
            int enemyAttackType = rand.nextInt(3) + 1;
            int playerAttack = 0;
            int enemyAttack = 0;
            if (enemyAttackType == 1) {
               enemyAttack = enemy.normalAttack();
               if (enemyAttack > 0) {
                  System.out
                        .println(enemy.getName() + "(이)가 코딩테스트 공격을 할 것 같습니다. 예상 수치: " + Math.abs(enemyAttack));
               } else {
                  System.out.println("상대 공격을 피할 수 있을것 같습니다.");
               }
            } else if (enemyAttackType == 2) {
               enemyAttack = enemy.specialAttack();
               if (enemyAttack > 0) {
                  System.out.println(
                        enemy.getName() + "(이)가 변수 이름 바꾸기 공격을 할 것 같습니다. 예상 수치: " + Math.abs(enemyAttack));
               } else {
                  System.out.println("상대 공격을 피할 수 있을것 같습니다.");
               }

            } else if (enemyAttackType == 3) {
               enemy.setHp(enemy.getHp() + enemy.defend());
               System.out.println(enemy.getName() + "(이)가 챗GPT를 사용하여 " + enemy.defend() + " 만큼 코드를 복구합니다.");
            }
            // 플레이어의 공격 선택
            System.out.print("\n1. 맥북 사기 2. 이직하기 3. 방어 선택: ");
            int actionChoice = sc.nextInt();
            if (actionChoice == 1) {
               playerAttack = player.normalAttack();
               if (playerAttack > 0) {
                  System.out.println(player.getName() + "(이)가 할부로 맥북을 구매하여 " + playerAttack + "데미지를 입혔습니다.");
               } else {
                  System.out.println("공격이 빗나갔습니다.");
               }
            } else if (actionChoice == 2) {
               playerAttack = player.specialAttack();
               if (playerAttack > 0) {
                  System.out.println(player.getName() + "(이)가 이직을 성공하여 " + playerAttack + "데미지를 입혔습니다.");
               } else {
                  System.out.println("공격이 빗나갔습니다.");
               }
            } else if (actionChoice == 3) {
               enemy.setHp(enemy.getHp() + player.defend());
               System.out.println(
                     player.getName() + "(이)가 디도스를 시전하여 " + player.defend() + " 만큼 충격을 상쇄하고 남은 수치만큼 행복해합니다.");
            }

            // 플레이어의 공격
            enemy.setHp(enemy.getHp() - playerAttack);

            System.out.print(enemy.getName() + "의 체력: " + enemy.getHp());

            if (enemy.getHp() <= 0) {
               System.out.println("\n" + enemy.getName() + "(이)가 패배하였습니다. ");

               break;
            }
            // 적의 공격
            player.setHp(player.getHp() - enemyAttack);
            System.out.print("\t" + player.getName() + "의 체력: " + player.getHp() + "\n");
            if (player.getHp() <= 0) {
               System.out.println("\n" + player.getName() + "(이)가 패배하였습니다. ");
               mp3.stop();
               mp3.play("C:\\\\Users\\\\smhrd\\\\Downloads\\\\BGM\\\\포켓몬스터 레드그린 체육관 관장 승리 BGM.mp3");
               break;
            }

            // 남은 체력 출력

         }
         // 승리

      }
      if (player.getHp() > 0) {
         mp3.stop();
         mp3.play("C:\\\\Users\\\\smhrd\\\\Downloads\\\\BGM\\\\포켓몬스터 레드그린 야생포켓몬 승리 BGM.mp3");
         System.out.println("\n축하합니다! 모든 적을 이기고 게임에서 승리하였습니다!");
         System.out.println("랭킹에 등록합니다");
         player.setSalary(player.getHp());
         System.err.println("당신의 연봉은 " + player.getSalary() * 100 + "만원 입니다");

         int cnt = controller.inputStats(player);
         if (cnt > 0) {
            System.out.println("랭킹 등록이 완료되었습니다");
         } else {
            System.out.println("랭킹 등록에 실패했습니다");
         }
      }

}
}
// 배틀 시스템