package tests;

public class Add_to_cart 
{
	public static void main(String[] args) throws InterruptedException 
	{
		BaseClass base = new BaseClass();
	
		base.Handel_Notification();
		base.Enetr_Url();
		base.User_SignUp();
		base.Verify_Signup_Status();
		
		base.Add_to_cart();
		base.Payment();
		base.Logout();
		base.Quit_application();
		
		
		
	}
}



