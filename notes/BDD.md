
BDD
Behaviour-Driven Development
is an approach that collaboratively specifies the system's desired behavior. 
Each time a piece of behavior is agreed, we use that specification to "drive" the development of the code 
that will implement that behavior. 

Doing Bdd involves doing the following things over and over again
1. Discovery
   2. discovering the scope of the behavior required by the user story by 
   exploring, discovering, and agreeing on the details of 
   how we want the system to behave using concrete examples
2. Formulation
   3. Documenting those examples in a way that could be automated and check for agreement
   In other words, formulate the specification in business-readable language. 
3. Automation
   4. Implementing the behavior described by each documented example
      4. strating with an automated test to guide the development of the code

    
- The documentation composed in the step of formulation is called "living document"
  because it reflects the team's shared understanding and the code reflects documentation. 


What does Cucmber do with BDD?
- Cucumber is a tool that understands the documentation and turns it into automaged tests. 
- BDD is a collaborative approach, made up of three practices. BDD practitioners may use Cucumber to automate their documenation. 


Prerequisit for doing BDD is to have an agile process in place. 
BDD needs to be done just in time at the last responsible moment
because we place a lot of emphasis on conversations rather than trying to write every thing down. 
If you try to have those conversations too far ahead of doing the work, you will forgotton most of what you talked about. 
so you need to havey our work already broken down into user stories. 
Idealy, you will have to also started to define acceptance criteria or business rules. 
but these get enriched doring the bdd process. 
User stories were created to be "placeholders for conversation". They allow us to defer
detailed analysis until we're confident that the behaviour they describe actually needs to be developed. 

The goal of a three amigos meeting is to ensure that the team fully understand the scope of the story being discussed. 
For this to be effective, we need to have at least three different perspectives represented at the meeting. 
The essential three perspectives required are : 
    - customer / business perspective - usually provided by the product owner
    - development perspective - usually provided by a developer
    - test perspective - usually provided by a tester
It;s a really useful way to discover assumptions and potential misunderstandings.
- the outcome is to have clear picture of
  - what you know
  - what you need to find out

We can refine as many example as we need into this form
to create an executabe spectification for the software
![cucumber_gherkin_example.png](images%2Fcucumber_gherkin_example.png)

- gherkin is a simple syntax that allows teams to write buisness readable, executable specfications. 
- some of the gherkin keywords are given, when, and then, but not al text that usese these words in gherkin. 
- gherkin is understood by a large number of tools, notably cucumber and specflow, and effectively turn the specification into automated tests. 

The concrete examples came out of the discussion are fomulated into gherkin scenarios. 
so each scenario is an example and both scenario and example are both keywords in gherkin.