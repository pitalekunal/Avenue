Feature: Create Task

Scenario: The user should always see the My Tasks link on the NavBar and clicking it will take user to a page with all the created tasks so far
	Given User is logged in with Valid Credentials
	| username   		| password 		|
	| ksp48@njit.edu 	| AveCode2017qa |
	When User observes My Tasks menu in the navbar
	And User clicks on My Tasks menu
	Then User lands on a page with all the created tasks so far

Scenario: The user should see a message on the top part saying that list belongs to the logged user: 
	Given User is logged in with Valid Credentials
	| username   		| password 		|
    | ksp48@njit.edu 	| AveCode2017qa |
	When User clicks on My Tasks menu
	Then User should see a message on the top part Hey Username, this is your todo list for today:

Scenario: The user should be able to enter a new task by hitting enter or clicking on the add task button
	Given User is logged in with Valid Credentials
	| username   		| password 		|
	| ksp48@njit.edu 	| AveCode2017qa |
	And User clicks on My Tasks menu
	When User enters Task into the Task field and clicks plus button
	Then A new task is entered in the ToDo list


Scenario: The task should require at least three characters so the user can enter it
	Given User is logged in with Valid Credentials
	| username   		| password 		|
	| ksp48@njit.edu 	| AveCode2017qa |
	And User clicks on My Tasks menu
	When User enters Task into the Task field and clicks plus button
	And Task has less than 3 characters 
	| Task |
	| Te   |
	Then Task should not be entered in the ToDo list
	| Task |
	| Te   |

Scenario: The task can’t have more than 250 characters
	Given User is logged in with Valid Credentials
	| username   		| password 		|
	| ksp48@njit.edu 	| AveCode2017qa |
	And User clicks on My Tasks menu
	When User enters Task into the Task field and clicks plus button
	And Task has more than 250 characters
	| Task |
	| aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa |
	Then Task should not be entered in the ToDo list
	| Task |
	| aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa |

Scenario: The task should be appended on the list of created tasks

	Given User is logged in with Valid Credentials
	| username   		| password 		|
	| ksp48@njit.edu 	| AveCode2017qa |
	And User clicks on My Tasks menu
	When User enters Task into the Task field and clicks plus button
	Then The task should be appended on the list of created tasks.
