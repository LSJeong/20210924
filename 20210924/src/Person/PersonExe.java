package Person;

public class PersonExe {
	private static PersonExe singleton = new PersonExe();
	private Person[] persons;

	private PersonExe() {
		persons = new Person[100];
//		persons[0] = new Person("홍길동",Gender.MAN,"111-222");
//		persons[1] = new Person("김가나",Gender.MAN,"121-222");
//		persons[2] = new Student("최철수",Gender.MAN,"133-442","경북대");
//		persons[3] = new Student("김영희",Gender.WOMAN,"144-222","영남대");
//		persons[4] = new Worker("김길동",Gender.WOMAN,"141-222","삼성");
//		persons[5] = new Worker("이가나",Gender.WOMAN,"121-222","엘지");
		
	}

	public static PersonExe getInstance() {
		return singleton;
	}

	public void execute() {
		while (true) {
			
			System.out.println("--------------------------------");
			System.out.println("1.추가 2.조회 3.수정 4.삭제 5.종료");
			System.out.println("--------------------------------");

			int menu = ScanUtil.readInt("메뉴를 선택하세요.");
			if (menu == 1) {
				addPerson();
			} else if (menu == 2) {
				showList();
			} else if (menu == 3) {
				modify();
			} else if (menu == 4) {
				remove();
			} else if (menu == 5) {
				break;
			}
		}

		System.out.println("끝.");

	}

	public void addPerson() { // 등록
		System.out.println("====등록메뉴입니다.====");
		System.out.println("1.일반등록 2. 학생등록 3. 직장동료등록");
		int choice = ScanUtil.readInt("추가할 사람을 선택하세요>>");

		if (choice == 1 || choice == 2 || choice == 3) {
			String name = ScanUtil.readStr("이름을 입력하세요>>");
			String gender = ScanUtil.readStr("성별을 입력하세요(남 or 여)>>");
			while(true) {
				if(gender.equals("남") || gender.equals("여")) {
					break;
				}else
					System.out.println("[남 / 여] 둘 중 하나를 입력해주세요!!");
					gender = ScanUtil.readStr("성별을 입력하세요(남 or 여)>>");
			}
			
			String phone = ScanUtil.readStr("연락처를 입력하세요>>");
			Person person = null;
			
			Gender gender1 = selGender(gender);

			if (choice == 1) {
				person = new Person(name, gender1, phone);
			} else if (choice == 2) {
				String school = ScanUtil.readStr("학교를 입력하세요>>");
				person = new Student(name, gender1, phone, school);
			} else if (choice == 3) {
				String company = ScanUtil.readStr("직장을 입력하세요>>");
				person = new Worker(name, gender1, phone, company);
			}

			for (int i = 0; i < persons.length; i++) {
				if (persons[i] == null) {
					persons[i] = person;
					break;
				}
			}
		} else {
			System.out.println("잘못 입력하셨습니다!!!");
		}

	}

	public void showList() {  // 조회
		System.out.println("==조회메뉴입니다.==");
		String search = ScanUtil.readStr("조회할 이름을 입력하세요[모를시 Enter.]>>");
		String search2 = ScanUtil.readStr("조회할 성별을 입력하세요(남 or 여)[모를시 Enter.]>>");
		while(true) {
			if(search2.equals("남") || search2.equals("여")|| search2.equals("")) {
				break;
			}else
				System.out.println("남 or 여를 입력해주세요!!");
			search2 = ScanUtil.readStr("성별을 입력하세요(남 or 여)>>");
		}
		Gender gender1 = selGender(search2);

		for (int i = 0; i < persons.length; i++) {
			if (!search.equals("") && search2 != "") {
				if (persons[i] != null && persons[i].getName().indexOf(search) != -1
						&& persons[i].getGender() == gender1) {
					System.out.println(persons[i].toString());
				}
			} else if (!search.equals("")) { // 이름만 입력
				if (persons[i] != null && persons[i].getName().indexOf(search) != -1) {
					System.out.println(persons[i].toString());
				}
			} else if (!search2.equals("")) { // 성별만 입력
				if (persons[i] != null && persons[i].getGender() == gender1) {
					System.out.println(persons[i].toString());
				}
			} else {
				if (persons[i] != null)
					System.out.println(persons[i].toString());
			}
		}

	}

	public void modify() {  // 수정
		System.out.println("==수정메뉴입니다.==");

		String name = ScanUtil.readStr("수정할 친구 이름을 입력>>");
		String gender = ScanUtil.readStr("변경할 성별을 입력(남 or 여)[변경없을시 Enter.]>>");
		while(true) {
			if(gender.equals("남") || gender.equals("여")|| gender.equals("")) {
				break;
			}else
				System.out.println("남 or 여를 입력해주세요!!");
				gender = ScanUtil.readStr("성별을 입력하세요(남 or 여)>>");
		}
		String phone = ScanUtil.readStr("변경할 연락처를 입력[변경없을시 Enter.]>>");
		
		Gender gender1 = selGender(gender);

		boolean isExist = false;

		for (int i = 0; i < persons.length; i++) {
			if (persons[i] != null && persons[i].getName().equals(name)) {
				if (!gender.equals("")) {
					persons[i].setGender(gender1);
				}
				if (!phone.equals("")) {
					persons[i].setPhone(phone);
				}

				if (persons[i] instanceof Student) {
					String school = ScanUtil.readStr("변경할 학교를 입력[변경없을시 Enter.]>>");
					if (!school.equals("")) {
						((Student) persons[i]).setSchool(school);
					}
				} else if (persons[i] instanceof Worker) {
					String company = ScanUtil.readStr("변경할 직장을 입력[변경없을시 Enter.]>>");
					if (!company.equals("")) {
						((Worker) persons[i]).setCompany(company);
					}
				}
				isExist = true;
			}
		}
		if (isExist)
			System.out.println("정상적으로 수정했습니다!!!");
		else
			System.out.println("존재하지 않습니다!!!");

	}

	public void remove() {  // 삭제
		System.out.println("==삭제메뉴입니다.==");
		System.out.println("[사람목록]");
		
		for (int i = 0; i < persons.length; i++) {
			if (persons[i] != null) {
				System.out.println("[" + i + "]" + persons[i].toString());
			}
		}

		int num = ScanUtil.readInt("삭제할 사람을 선택하세요.");

		if (persons[num] != null) {
			persons[num] = null;
			System.out.println("삭제완료.");
		} else
			System.out.println("삭제할 정보가 없습니다.");
	}

	public Gender selGender(String gender) {
		if (gender.equals("남"))
			return Gender.MAN;
		else if(gender.equals("여"))
			return Gender.WOMAN;
		else
			return null;
	}

}
