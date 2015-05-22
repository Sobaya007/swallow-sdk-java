package org.tokyotech.trap.swallow;

public class TEST {

	public static void main(String[] args) throws SwallowException {
		SwallowSecurity sec = new SwallowSecurity();
		sec.login("testuser", "gY7n44hqfo");
		
		Swallow swa = sec.getSwallow();
		swa.createMessage("TEST MESSAGE", null, new Integer[]{2}, null, null, null, null);
		
		Swallow.FullMessage[] mes = swa.findMessage(0, 1, null, null, null, null, new Integer[]{2}, null, null, null, null, null, null);
		System.out.println(mes[0].getMessage());
		
		sec.logout("testuser", "gY7n44hqfo");
	}
	

}
