package mode.factory;

public class MailSender implements Sender{

	@Override
	public void send() {
		System.out.println("this is mailsender!");
		
	}

}
class SmsSender implements Sender{

	@Override
	public void send() {
		 System.out.println("this is sms sender!");  
		
	}
	
}