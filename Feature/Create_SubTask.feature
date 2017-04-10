Feature: Create SubTask

Scenario: The user should see a button labeled as ‘Manage Subtasks’

	Given User is logged in with Valid Credentials
	| username   		| password 		|
	| ksp48@njit.edu 	| AveCode2017qa |
	And User clicks on My Tasks menu
	When User lands on a page with all the created tasks so far
	Then User observes a button labelled as Manage Subtasks
	
Scenario: This button should have the number of subtasks created for that tasks

	Given User is logged in with Valid Credentials
	| username   		| password 		|
	| ksp48@njit.edu 	| AveCode2017qa |
	And User clicks on My Tasks menu
	When User observes a button labelled as Manage Subtasks
	Then User clicks on Manage Subtasks button
	And Manage Subtasks button has the number of SubTasks created for that task 

Scenario: The popup should have a read only Task ID and Task Description

	Given User is logged in with Valid Credentials
	| username   		| password 		|
	| ksp48@njit.edu 	| AveCode2017qa |
	And User clicks on My Tasks menu
	When User observes a button labelled as Manage Subtasks
	And User clicks on Manage Subtasks button
	Then A popup oens up which has a read only field with the task ID and the task description
	
Scenario: The form should have a SubTask Description and SubTask due date which should allow the user to enter Subtask
		Given User is logged in with Valid Credentials
	| username   		| password 		|
	| ksp48@njit.edu 	| AveCode2017qa |
	And User clicks on My Tasks menu
	When User observes a button labelled as Manage Subtasks
	And User clicks on Manage Subtasks button
	Then There is a form  where user can enter the SubTask Description (250 characters) and SubTask due date (MM/dd/yyyy format)
	| Date 	     | SubTask |
	| 04/09/2017 |aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa |

Scenario: The Task Description and Due Date are required fields

	Given User is logged in with Valid Credentials
	| username   		| password 		|
	| ksp48@njit.edu 	| AveCode2017qa |
	And User clicks on My Tasks menu
	When User clicks on Manage Subtasks button
	Then User enters Subtask and date fields
	| Date | SubTask |
	|  | Full | 
	And User clicks add button to add a SubTask
	
Scenario: The Task Description and Due Date are required fields

	Given User is logged in with Valid Credentials
	| username   		| password 		|
	| ksp48@njit.edu 	| AveCode2017qa |
	And User clicks on My Tasks menu
	When User clicks on Manage Subtasks button
	Then User enters Subtask and date fields
	| Date 	     | SubTask   |
	| 04/09/2017 | SubTask 2 | 
	And User clicks add button to add a SubTask
	And User checks whether SubTask is appended on the bottom part of the modal dialog 
	
