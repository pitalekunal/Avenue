package stepDefinition;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver; 
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import javafx.concurrent.Task;
import junit.framework.Assert;
import pageMethods.Login;

	@SuppressWarnings({ "unused", "deprecation" })
	public class Test_Steps {
		
		public String taskCount;
		public String SubTask;
		public int SubtaskField = 1;
		public int DateField = 1;
		
		public static WebDriver driver;
		
		@Given("^User is logged in with Valid Credentials$")
		
		public void user_is_logged_in_with_Valid_Credentials(DataTable usercredentials) throws Throwable{
			
			System.setProperty("webdriver.chrome.driver","C:/Selenium/chrome/chromedriver.exe");
			driver = new ChromeDriver();			
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
		    Login.UserLogin(driver, usercredentials);
	        if (driver.findElement(By.xpath("//div[@class='alert alert-info']")).getText().equals("Signed in successfully.")){
	        	System.out.println("Successfully signed in");
	        }else{
	        	System.out.println("Sorry where are you!");
	        }
		}
		
		@When("^User observes My Tasks menu in the navbar$")
		public void user_observes_My_Tasks_menu_in_the_navbar() throws Throwable {
			
			if(driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[2]/ul[1]/li[2]/a")).getText().equals("My Tasks")){
				System.out.println("Yes 'My Tasks' is present in the nav bar");
			}			
		}
		
		@When("^User clicks on My Tasks menu$")
		public void user_clicks_on_My_Tasks_menu() throws Throwable {
		    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[2]/ul[1]/li[2]/a")).click();
		}

		@Then("^User lands on a page with all the created tasks so far$")
		public void user_lands_on_a_page_with_all_the_created_tasks_so_far() throws Throwable {
			if(driver.findElement(By.xpath("//input[@id='new_task']")).isDisplayed()){
				System.out.println("User landed on page with all created tasks so far");
			}
			else{
				System.out.println("User did not land on page with all created tasks so far");
			}
		}

		@Then("^User should see a message on the top part Hey Username, this is your todo list for today:$")
		public void user_should_see_a_message_on_the_top_part_Hey_Username_this_is_your_todo_list_for_today() throws Throwable {
			//get User's name from menu besides Logout
			String Username = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[2]/ul[2]/li[1]/a")).getText();
			String [] message = Username.split(",");
			message[1] = message[1].replace("!", "");
			System.out.println("User's name is = " + message[1]);
			String finalMessage = String.format("Hey %s, this is your todo list for today:", message[1]);
		    if(driver.findElement(By.xpath("/html/body/div[1]/h1")).getText().equals(finalMessage)){
		    	System.out.println("Right Message displayed at top");
		    }else{
		    	System.out.println("Wrong Message displayed at top");
		    }   
		}	

		@When("^User enters Task into the Task field and clicks plus button$")
		public void user_enters_Task_into_the_Task_field_and_clicks_plus_button() throws Throwable {
		    driver.findElement(By.id("new_task")).click();
		    driver.findElement(By.id("new_task")).sendKeys("Demo Task 1");
		    driver.findElement(By.xpath("//span[@ng-click='addTask()']")).click();
		}

		@Then("^A new task is entered in the ToDo list$")
		public void a_new_task_is_entered_in_the_ToDo_list() throws Throwable {
			if(driver.findElement(By.xpath("//a[contains(.,'Demo Task 1')]")).isDisplayed()){
			System.out.println("Task is added to the ToDo List");
			int added = 1;
			}else{
			System.out.println("Task is not added to the ToDo List");	
			int added = 0;
			}
		}

		@When("^Task has less than (\\d+) characters$")
		public void task_has_less_than_characters(int min, DataTable Task) throws Throwable {
			List<Map<String,String>> data = Task.asMaps(String.class,String.class);
			    driver.findElement(By.id("new_task")).click();
			    driver.findElement(By.id("new_task")).sendKeys(data.get(0).get("Task"));
			    driver.findElement(By.xpath("//span[@ng-click='addTask()']")).click();			
		}

		@Then("^Task should not be entered in the ToDo list$")
		public void task_should_not_be_entered_in_the_ToDo_list(DataTable task) throws Throwable {
			List<Map<String,String>> data = task.asMaps(String.class,String.class);
				String addedTask = data.get(0).get("Task");
				if(driver.findElement(By.xpath("//a[contains(.,'"+addedTask+"')]")).isDisplayed()){
				System.out.println("Failed ---- Task should not have been entered in the List");
				}else{
				System.out.println("Passed ---- Task has been entered in the List");	
				}
		}
		
		@When("^Task has more than (\\d+) characters$")
		public void task_has_more_than_characters(int max, DataTable Task) throws Throwable {
			List<Map<String,String>> data = Task.asMaps(String.class,String.class);
		    driver.findElement(By.id("new_task")).click();
		    driver.findElement(By.id("new_task")).sendKeys(data.get(0).get("Task"));
		    driver.findElement(By.xpath("//span[@ng-click='addTask()']")).click();	
		}

		@Then("^The task should be appended on the list of created tasks\\.$")
		public void the_task_should_be_appended_on_the_list_of_created_tasks() throws Throwable {
			
			if (driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/table/tbody/tr[1]/td[2]/a")).getText().equals("Demo Task 1")){
				System.out.println("Task Appended");
			}else{
				System.out.println("Task Not Appended");
			}
		}
		
		@Then("^User observes a button labelled as Manage Subtasks$")
		public void user_observes_a_button_labelled_as_Manage_Subtasks() throws Throwable {
			
			if (driver.findElement(By.xpath("//button[contains(.,'Manage Subtasks')]")).isDisplayed()){
				System.out.println("Manage Task Button VISIBLE");
			}else{
				System.out.println("Manage Task Button INVISIBLE");
			}
		}

		@Then("^User clicks on Manage Subtasks button$")
		public void user_clicks_on_Manage_Subtasks_button() throws Throwable {
			
			driver.findElement(By.xpath("//button[contains(.,'Manage Subtasks')]")).click();
			taskCount = driver.findElement(By.xpath("//button[contains(.,'Manage Subtasks')]")).getText();
			taskCount = taskCount.replaceAll("[^\\d.]", "");
			System.out.println("Button with Tasks " + taskCount + " Clicked");
		}

		@When("^Manage Subtasks button has the number of SubTasks created for that task$")
		public void manage_Subtasks_button_has_the_number_of_SubTasks_created_for_that_task() throws Throwable {
			
			List<WebElement> rows = driver.findElements(By.xpath("html/body/div[4]/div/div/div[2]/div[2]/table/tbody/tr"));
			System.out.println("Number of Tasks = " + rows.size());
			if(Integer.valueOf(taskCount).equals(rows.size())){
				System.out.println("Button displays equal number of subtasks created for repsective task");
			}else{
				System.out.println("Button DOES NOT display equal number of subtasks created for repsective task");
			}
		}
		
		@Then("^A popup oens up which has a read only field with the task ID and the task description$")
		public void a_popup_oens_up_which_has_a_read_only_field_with_the_task_ID_and_the_task_description() throws Throwable {
			
			if(driver.findElement(By.xpath("//h3[@class='modal-title ng-binding']")).isDisplayed()){
				System.out.println("Task id is a Read only field");
			}else{
				System.out.println("Task id is maynot be a Read only field");
			}
			
			if(driver.findElement(By.xpath("//textarea[@id='edit_task']")).isEnabled()){
				System.out.println("Task Description is not Read only field");
			}else{
				System.out.println("Task Description is a Read only field");
			}
		}

		@Then("^There is a form  where user can enter the SubTask Description \\((\\d+) characters\\) and SubTask due date \\(MM/dd/yyyy format\\)$")
		public void there_is_a_form_where_user_can_enter_the_SubTask_Description_characters_and_SubTask_due_date_MM_dd_yyyy_format(int SubTaskLimit, DataTable SubTaskValues) throws Throwable {
		    
			int DateValidation;
			
			List<Map<String,String>> data = SubTaskValues.asMaps(String.class,String.class);
			String SubTask = data.get(0).get("SubTask");
			driver.findElement(By.xpath("//input[@id='new_sub_task']")).sendKeys(SubTask);
			System.out.println("The number of characters in Subtask are = " + SubTask.length());
			
			driver.findElement(By.xpath("//input[@id='dueDate']")).clear();
			driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys(data.get(0).get("Date"));
			System.out.println("The Date entered is = " + data.get(0).get("Date"));
			
			driver.findElement(By.xpath("//button[@id='add-subtask']")).click();
			
			String[] date = data.get(0).get("Date").split("/");
			
			if(date[0].length() == 2 && date[1].length() == 2 && date[2].length() == 4){
				System.out.println("Date Format is CORRECT");
				DateValidation = 1;
				
			}else{
				System.out.println("Date Format is INCORRECT");
				DateValidation = 0;
			}
			 
			if (SubTask.length() > SubTaskLimit && driver.findElement(By.xpath("//a[contains(.,'" + SubTask + "')]")).isDisplayed()){
				System.out.println("The SubTask should not have been added, Sub Task character length is above given limit");
			}
			
			if (SubTask.length() <= SubTaskLimit && driver.findElement(By.xpath("//a[contains(.," + SubTask + ")]")).isDisplayed()){
				if(DateValidation == 1){
					System.out.println("The SubTask is added correctly");
				}else{
					System.out.println("The SubTask should not have been added, Sub Task date has INCORRECT date fromat");
				}
			}			
			
		}
		
		@Then("^User enters Subtask and date fields$")
		public void user_enters_Subtask_and_date_fields(DataTable SubTaskFields) throws Throwable {
			
			List<Map<String,String>> data = SubTaskFields.asMaps(String.class,String.class);
			
			SubTask = data.get(0).get("SubTask");
			
			driver.findElement(By.xpath("//input[@id='new_sub_task']")).clear();
			driver.findElement(By.xpath("//input[@id='new_sub_task']")).sendKeys(SubTask);
			
			if(data.get(0).get("SubTask").isEmpty()){
				SubtaskField = 0;
			}
			
			driver.findElement(By.xpath("//input[@id='dueDate']")).clear();
			driver.findElement(By.xpath("//input[@id='dueDate']")).sendKeys(data.get(0).get("Date"));
			
			if(data.get(0).get("Date").isEmpty()){				
				DateField = 0;
			}
		}

		@Then("^User clicks add button to add a SubTask$")
		public void user_clicks_add_button_to_add_a_SubTask() throws Throwable {


			driver.findElement(By.xpath("//button[@id='add-subtask']")).click();
			
			if(SubtaskField == 0 || DateField == 0){
				if(SubtaskField == 1 && driver.findElement(By.xpath("//a[contains(.,"+SubTask+")]")).isDisplayed()){						
				System.out.println("Scenario FAILED ---- Validation failed Subtask added without Date");
			}else{
				if(driver.findElement(By.xpath("//a[@class='ng-scope ng-binding editable editable-click editable-empty']")).isDisplayed()){
					System.out.println("Scenario FAILED ---- Validation failed Empty Subtask added without Date");
				}				
			}
			}else{
				System.out.println("Scenario PASSED ---- Subtask is added with Date");
			}
		}	
		
		@Then("^User checks whether SubTask is appended on the bottom part of the modal dialog$")

		public void user_checks_whether_SubTask_is_appended_on_the_bottom_part_of_the_modal_dialog() throws Throwable {
			
			List<WebElement> rows = driver.findElements(By.xpath("html/body/div[4]/div/div/div[2]/div[2]/table/tbody/tr"));
			System.out.println("Number of Sub Tasks = " + rows.size());
			if(driver.findElement(By.xpath("html/body/div[4]/div/div/div[2]/div[2]/table/tbody/tr[" + rows.size() + "]/td[2]/a")).getText().equals(SubTask)){
				System.out.println("PASSED ---- SubTask is appended on the bottom part of the modal dialog");
			}else{
				System.out.println("FAILED ---- SubTask is not appended on the bottom part of the modal dialog");
			}
		}
	}

	