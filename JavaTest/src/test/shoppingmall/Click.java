package test.shoppingmall;

public class Click {
	Click() {
	}

	public void Clickerror1(int num) throws ClickErrorException {
		if (num != 1 && num != 2) {
			throw new ClickErrorException("�߸�������!!!!�ٽ� ��������~~!!!");
		}
	}

	public void Clickerror2(int num) throws ClickErrorException {
		if (num != 1 && num != 2 && num != 3 && num != 4 && num != 5 && num != 6) {
			throw new ClickErrorException("�߸�������!!!!!!!!!");
		}
	}
}
