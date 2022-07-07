# Deadline Tracker

## Keep track of your work deadlines

This *Deadline Tracker* will be useful for **Students** 
with multiple work tasks and who need a single list to log all the work deadlines.
With all works and corresponding deadlines display in a single list,
this clear visualization will help students decide which work to prioritize. Student
 can choose to only view the unfinished work which shorten the time in finding which work
needs to be done.

## User Stories
- Users can
  - Add a work with its name and its due date in the work list
  - See all the unfinished work
  - See all the work logged
  - Delete a selected work from the list
  - Mark a selected work as done on the list
  - Choose to save the work list when quit
  - Reload previously saved work list when reopen the file

## Phase 4: Task 2
Wed Nov 24 13:38:51 PST 2021

Work named "Phase1" is added.

Wed Nov 24 13:39:01 PST 2021

Work named "Phase2" is added.

Wed Nov 24 13:39:10 PST 2021

Work named "Phase4" is added.

Wed Nov 24 13:39:24 PST 2021

Work named "Math" is added.

Wed Nov 24 13:39:33 PST 2021

Work named "Math" is marked done.

Wed Nov 24 13:39:41 PST 2021

Work named "Phase1" is deleted.

## Phase 4: Task 3
- **optionIcon()** and **regularIcon()** method
  - Since there are duplicate code between the **optionIcon()**
and **regularIcon()** method inside the IconImage class, it would be better
if I can have an abstract method that captures the duplicate code so
that they can be less repetitive.
- refactor **setUpButtons()** method
  - Similarly, if there is an abstract method for creating a button including 
  making a JButton, setActionCommand, setActionListener and add to panel, 
  whenever a button is needed, this abstract method can be called, thus can remove 
  duplicate code.