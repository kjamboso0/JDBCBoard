import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MemberManager {
	private MemberDAO memberDAO;
	private Scanner keyboard;
	
	public MemberManager() {
		memberDAO = new MemberDAO();	
		keyboard = new Scanner(System.in);
	}
	
	public void startProgram() throws IOException {	
		
		while (true) {			
			printMenu();
			String buttonNumber = keyboard.nextLine();
			if(buttonNumber.equals("0")) {
				break;
			} else {
				if(buttonNumber.equals("1")) {
					getMemberList();
				} else if(buttonNumber.equals("2")) {
					insertMember();
				} else if(buttonNumber.equals("3")) {
					updateMember();
				} else if(buttonNumber.equals("4")) {
					deleteMember();
				} else {
					System.out.println(buttonNumber + "는 유효하지 않은 번호입니다.");
				}
			}
			
		}

		keyboard.close();
	}

	private void insertMember() {
		System.out.print("아이디를 입력하세요 (형식 M-00001): " );		
		String memberId = keyboard.nextLine();
		if(memberDAO.getMember(memberId)) {
			System.out.println(memberId + "가 이미 존재합니다.");
			return;
		}
		if(idVarify(memberId)) {
			return;
		}
		
		System.out.print("이름을 입력하세요 : " );	
		String name = keyboard.nextLine();
		if(nameVarify(name)) {
			return;
		}
		
		System.out.print("전화번호를 입력하세요 : " );
		String phoneNumber = keyboard.nextLine();
		if(phoneNumberVarify(phoneNumber)) {
			return;
		}

		memberDAO.insertMember(memberId, name, phoneNumber);
		System.out.println("회원가입에 성공하셨습니다.");
		memberDAO.getMemberList();
	}

	private void updateMember() {
		System.out.print("수정할 아이디를 입력하세요 (형식 M-00001): " );		
		String memberId = keyboard.nextLine();
		if(idVarify(memberId)) {
			return;
		}
		if(!memberDAO.getMember(memberId)) {
			System.out.println("수정할" + memberId + "회원 정보가 존재하지 않습니다.");
			return;
		}
		System.out.print("수정할 전화번호를 입력하세요 : " );
		String phoneNumber = keyboard.nextLine();
		if(phoneNumberVarify(phoneNumber)) {
			return;
		}

		memberDAO.updateMember(phoneNumber, memberId);
		System.out.println("회원수정에 성공하셨습니다.");		
	}

	private void deleteMember() {
		System.out.print("삭제할 아이디를 입력하세요 : " );
		String memberId = keyboard.nextLine();
		if(idVarify(memberId)) {
			return;
		}
		if(!memberDAO.getMember(memberId)) {
			System.out.println("삭제할" + memberId + "회원 정보가 존재하지 않습니다.");
			return;
		}
		memberDAO.deleteMember(memberId);
		System.out.println(memberId + "회원 삭제에 성공하셨습니다.");
		
	}
	
	private void getMemberList() {
		List<Member> memberList = memberDAO.getMemberList();
		if(memberList.size() == 0) {
			System.out.println("등록된 회원이 없습니다.");
		} else {
			System.out.println("현재 등록된 회원 목록입니다.");					
			for (Member member : memberList) {
				System.out.println("---> " + member.toString());
			}
		}
	}

	private void printMenu() {
		System.out.println("목록을 원하시면 1번을 입력하세요.");
		System.out.println("등록을 원하시면 2번을 입력하세요.");
		System.out.println("수정을 원하시면 3번을 입력하세요.");
		System.out.println("삭제를 원하시면 4번을 입력하세요.");
		System.out.println("종료를 원하시면 0번을 입력하세요.");
	}
	
	private boolean idVarify(String memberId) {
		if(memberId == null || memberId.length() == 0) {
			System.out.println("아이디는 필수 입력항목입니다.");
			return true;
		}
		if(!memberId.startsWith("M-") || !(memberId.length() == 7)) {
			System.out.println("아이디는 'M-'로 시작해야 하며, M-를 포함하여 7개의 문자로 구성해야 합니다.");
			return true;
		}		
		return false;
	}
	
	private boolean nameVarify(String name) {
		if(name == null || name.length() == 0) {
			System.out.println("이름은 필수 입력항목입니다.");
			return true;
		}
		return false;
	}
	
	private boolean phoneNumberVarify(String phoneNumber) {
		if(phoneNumber == null || phoneNumber.length() == 0) {
			System.out.println("전화번호는 필수 입력항목입니다.");
			return true;
		}
		if(phoneNumber.charAt(3) != '-'  || !(phoneNumber.length() == 13)) {
			System.out.println("전화번호는 두 개의 '-'를 포함하여 총 13개의 문자로 구성해야 합니다.");
			return true;
		}	
		return false;
	}

}
