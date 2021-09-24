package Person;

public class PersonExe {
	private static PersonExe singleton = new PersonExe();
	private Person[] persons;

	private PersonExe() {
		persons = new Person[100];
//		persons[0] = new Person("ȫ�浿",Gender.MAN,"111-222");
//		persons[1] = new Person("�谡��",Gender.MAN,"121-222");
//		persons[2] = new Student("��ö��",Gender.MAN,"133-442","��ϴ�");
//		persons[3] = new Student("�迵��",Gender.WOMAN,"144-222","������");
//		persons[4] = new Worker("��浿",Gender.WOMAN,"141-222","�Ｚ");
//		persons[5] = new Worker("�̰���",Gender.WOMAN,"121-222","����");
		
	}

	public static PersonExe getInstance() {
		return singleton;
	}

	public void execute() {
		while (true) {
			
			System.out.println("--------------------------------");
			System.out.println("1.�߰� 2.��ȸ 3.���� 4.���� 5.����");
			System.out.println("--------------------------------");

			int menu = ScanUtil.readInt("�޴��� �����ϼ���.");
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

		System.out.println("��.");

	}

	public void addPerson() { // ���
		System.out.println("====��ϸ޴��Դϴ�.====");
		System.out.println("1.�Ϲݵ�� 2. �л���� 3. ���嵿����");
		int choice = ScanUtil.readInt("�߰��� ����� �����ϼ���>>");

		if (choice == 1 || choice == 2 || choice == 3) {
			String name = ScanUtil.readStr("�̸��� �Է��ϼ���>>");
			String gender = ScanUtil.readStr("������ �Է��ϼ���(�� or ��)>>");
			while(true) {
				if(gender.equals("��") || gender.equals("��")) {
					break;
				}else
					System.out.println("[�� / ��] �� �� �ϳ��� �Է����ּ���!!");
					gender = ScanUtil.readStr("������ �Է��ϼ���(�� or ��)>>");
			}
			
			String phone = ScanUtil.readStr("����ó�� �Է��ϼ���>>");
			Person person = null;
			
			Gender gender1 = selGender(gender);

			if (choice == 1) {
				person = new Person(name, gender1, phone);
			} else if (choice == 2) {
				String school = ScanUtil.readStr("�б��� �Է��ϼ���>>");
				person = new Student(name, gender1, phone, school);
			} else if (choice == 3) {
				String company = ScanUtil.readStr("������ �Է��ϼ���>>");
				person = new Worker(name, gender1, phone, company);
			}

			for (int i = 0; i < persons.length; i++) {
				if (persons[i] == null) {
					persons[i] = person;
					break;
				}
			}
		} else {
			System.out.println("�߸� �Է��ϼ̽��ϴ�!!!");
		}

	}

	public void showList() {  // ��ȸ
		System.out.println("==��ȸ�޴��Դϴ�.==");
		String search = ScanUtil.readStr("��ȸ�� �̸��� �Է��ϼ���[�𸦽� Enter.]>>");
		String search2 = ScanUtil.readStr("��ȸ�� ������ �Է��ϼ���(�� or ��)[�𸦽� Enter.]>>");
		while(true) {
			if(search2.equals("��") || search2.equals("��")|| search2.equals("")) {
				break;
			}else
				System.out.println("�� or ���� �Է����ּ���!!");
			search2 = ScanUtil.readStr("������ �Է��ϼ���(�� or ��)>>");
		}
		Gender gender1 = selGender(search2);

		for (int i = 0; i < persons.length; i++) {
			if (!search.equals("") && search2 != "") {
				if (persons[i] != null && persons[i].getName().indexOf(search) != -1
						&& persons[i].getGender() == gender1) {
					System.out.println(persons[i].toString());
				}
			} else if (!search.equals("")) { // �̸��� �Է�
				if (persons[i] != null && persons[i].getName().indexOf(search) != -1) {
					System.out.println(persons[i].toString());
				}
			} else if (!search2.equals("")) { // ������ �Է�
				if (persons[i] != null && persons[i].getGender() == gender1) {
					System.out.println(persons[i].toString());
				}
			} else {
				if (persons[i] != null)
					System.out.println(persons[i].toString());
			}
		}

	}

	public void modify() {  // ����
		System.out.println("==�����޴��Դϴ�.==");

		String name = ScanUtil.readStr("������ ģ�� �̸��� �Է�>>");
		String gender = ScanUtil.readStr("������ ������ �Է�(�� or ��)[��������� Enter.]>>");
		while(true) {
			if(gender.equals("��") || gender.equals("��")|| gender.equals("")) {
				break;
			}else
				System.out.println("�� or ���� �Է����ּ���!!");
				gender = ScanUtil.readStr("������ �Է��ϼ���(�� or ��)>>");
		}
		String phone = ScanUtil.readStr("������ ����ó�� �Է�[��������� Enter.]>>");
		
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
					String school = ScanUtil.readStr("������ �б��� �Է�[��������� Enter.]>>");
					if (!school.equals("")) {
						((Student) persons[i]).setSchool(school);
					}
				} else if (persons[i] instanceof Worker) {
					String company = ScanUtil.readStr("������ ������ �Է�[��������� Enter.]>>");
					if (!company.equals("")) {
						((Worker) persons[i]).setCompany(company);
					}
				}
				isExist = true;
			}
		}
		if (isExist)
			System.out.println("���������� �����߽��ϴ�!!!");
		else
			System.out.println("�������� �ʽ��ϴ�!!!");

	}

	public void remove() {  // ����
		System.out.println("==�����޴��Դϴ�.==");
		System.out.println("[������]");
		
		for (int i = 0; i < persons.length; i++) {
			if (persons[i] != null) {
				System.out.println("[" + i + "]" + persons[i].toString());
			}
		}

		int num = ScanUtil.readInt("������ ����� �����ϼ���.");

		if (persons[num] != null) {
			persons[num] = null;
			System.out.println("�����Ϸ�.");
		} else
			System.out.println("������ ������ �����ϴ�.");
	}

	public Gender selGender(String gender) {
		if (gender.equals("��"))
			return Gender.MAN;
		else if(gender.equals("��"))
			return Gender.WOMAN;
		else
			return null;
	}

}
