# Call Center Documentation

- [Call Center Documentation](#callcenterdocumentation)
  - [Prerequisites](#prerequisites)
  - [Description](#description)
  - [Source code folder structure](#sourcecodefolderstructure)
  - [Classes](#classes)
    - [CallCenter](#callcenter)
    - [Call](#call)
    - [Employee](#employee)
  - [CLI](#cli)
    - [Usage](#usage)
    - [Manual](#manual)
   
## Prerequisites
- Java version 20.0.1

  
## Description
Call center consists of 3 types of employees: Respondents, Manager and Director.
Each type of employee has an attribute of specific experience level: Respondents: lvl 3, Manager: lvl 5, Director: lvl 10.
The number of respondents is not limited, but there is only one manager and director by default.
Incoming calls to call center have attribute of required experience level from 1 (lowest) to 10 (max).
The incoming calls need to be dispatched using method called dispatchCall(Call call) (calls wont be processed if not dispatched).
These calls are first assigned to available respondent (the one which is currently not in call).
If there is no available respondent, the call is being put into waiting queue for respondents, and will be assigned to a respondent they are available.
If respondents experience level is lower that the required experience level of call, the call is being escalated to manager.
If manager is not available or their level is also below required experience level of call, the call is being escalated to director.
If director is not available, the call is being put into waiting queue.
There is a waiting queue for both manager and director (if both director and manager were not available but their level of experience satisfies the required level of experience).
There is also a waiting queue only for director. Only he satisfies the required level of experiences but was not available (this queue has higher priority for him).
After finishing call, the call is stored in list of finished calls.

## Source code folder structure
``` bash
├── cli - package for console interface
│   ├── ConsoleInterface.java 
│   └── Keyboardinput.java
├── exception - package for all exceptions
│   ├── DuplicateIdException.java
│   ├── NullException.java
│   └── OutOfScopeException.java
├── factory - package for factory design pattern
│   ├── CallCenterFactory.java
│   ├── CallFactory.java
│   ├── DirectorFactory.java
│   ├── EmployeeFactory.java
│   ├── ManagerFactory.java
│   └── RespondentFactory.java
├── position - package for Employee.java subclasses
│   ├── Director.java
│   ├── Manager.java
│   └── Respondent.java
├── Call.java
├── CallCenter.java
├── CallCenterApp.java - Main file
└── Employee.java - Superclass from which subclasses in package position inherit
```

## Classes

### CallCenter
The CallCenter class is implemented as a Singleton, ensuring that there is only one instance of the call center in the entire application. 

**Attributes:**
```java
private static CallCenter instance;
private List<Employee> employees;
private List<Call> allCalls;
private List<Call> finishedCalls;
private Queue<Call> callQueueForRespondents;
private Queue<Call> callQueueForManagerOrDirector;
private Queue<Call> callQueueForDirector;
```

**Features:**
- Processes and dispatches incoming calls to available employees.
- Maintains a list of employees, including Respondents, Managers, and Directors.
- Maintains a list of all and finished calls.
- Implements a queue system logic for handling scenarios where all employees are not available.
- Provides a centralized point for managing the state of the call center.

**Important methods:**
```java
// Create call center using callCenterFactory
CallCenterFactory callCenterFactory = new CallCenterFactory();
CallCenter callCenter = callCenterFactory.createCallCenter();

CallCenter callCenter = CallCenter.getInstance(); // Access the singleton instance of call center
callCenter.dispatchCall(call); // Dispatch a call
callCenter.hireEmployee(employee); // Add employee to call center ("hire them")
call.Center.receiveCall(call); // Process the incoming call
```

### Call
The Call class represents an incoming telephone call within the Call Center system. 
When a call is created, it can be dispatched to the Call Center, which then assigns an available employee to handle the call or set the call in queue.

**Attributes:**
```java
private UUID id = UUID.randomUUID(); // Assign random ID to call
private Integer requiredExperienceLevel = 1; // Default is the lowest experience level
private UUID callHandler = null; // ID of employee that is handling the call
private boolean inQueue = false; // Call is or is not currently in queue
private boolean callEnded = false; // Call is or is not finished 
```

**Important methods:**
```java
// Create call using callFactory
CallFactory callFactory = new CallFactory();
Call call = callFactory.createCall();

call.setRequiredExperienceLevel(int); // Set required experience level to handle the call
```

### Employee
The Employee class represents a superclass for subclasses Respondents, Managers, and Directors. 
Each employee is equipped with common attributes and methods, facilitating consistent behavior across the system. 
The Employee class leverages polymorphism, allowing instances of its subclasses to be treated as instances of the superclass.
There is a hierarchy between employees based on thier experience level.

**Common Attributes:**
```java
private UUID id = UUID.randomUUID(); // Assign random ID to employee
private String position; // Position of employee (Respondent/Manager/Director)
private Integer experienceLevel; // Level of experience to be able to handle a call
private UUID assignedCall = null; // ID of assigned call
```

**Common methods:**
```java
// Create Singleton instance of director
EmployeeFactory directorFactory = new DirectorFactory();
Employee director = directorFactory.createEmployee();

// Create Singleton instance of manager
EmployeeFactory managerFactory = new ManagerFactory();
Employee manager = managerFactory.createEmployee();

// Create respondents
EmployeeFactory respondentFactory = new RespondentFactory();
Employee respondent1 = respondentFactory.createEmployee();
Employee respondent2 = respondentFactory.createEmployee();

employee.assignCallToEmployee(call, employee, callAssigned) // Specific call is assigned to specific employee
employee.receiveCall(call); // Employee is processing the call (call might end up in queue or escalate)
employee.escalateCall(call); // Employee is either not available or cannot handle the call (low experience level) - this method applies only to respondent and manager
employee.finishCall(); // Employee ends call
```

## CLI
The Call Center Management System includes a Command Line Interface (CLI) for easy control and interaction.
Through the CLI, users can initiate calls, manage employees, and monitor the overall state of the call center.

### Usage
1. Run the application from the command line.
2. Follow the on-screen prompts to navigate through the available options.
3. Interact with the CLI to hire employees, create, dispatch and finish calls, and perform other commands.

### Manual
- Press 0 to end session.
- Press 1 for this manual.
- Press 2 to view calls waiting to dispatch.
- Press 3 to view calls in queue for respondents.
- Press 4 to view calls in queue for both manager and director.
- Press 5 to view calls in queue for director.
- Press 6 to view finished calls.
- Press 7 to view employees of call center.
- Press 8 to create a new call.
- Press 9 to dispatch all calls.
- Press 10 to finish a call of specific employee.

