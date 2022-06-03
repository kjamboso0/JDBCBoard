public class Member {
	private String memberId;
	private String name;
	private String phoneNumber;
	
	public Member() {
		super();
	}	
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", name=" + name + ", phoneNumber=" + phoneNumber + "]";
	}		
}












