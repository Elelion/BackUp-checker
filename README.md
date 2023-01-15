# BackUp checker

## Description
A program that will check backups and send notifications if suddenly backups is not correct. The program will be really applied at my work, and will check backups:
+ > 1C
+ > Shara
+ > Profiles

The program will be written in several steps, where at each step, the program will be run-in.

---

### Step-1:
+ Checks for an archive
+ If there is NO archive, it sends a message to the specified email.
  - If there is an archive, it does not send anything.

### Step-2:
+ Make a settings file where the email for notification will be registered, it will be loaded into the program and a newsletter will be sent to this eMail.
  - If the email address is not specified correctly, the program should notify about it immediately (those implement address validity check)

### Step-3:
+ Make a check on the size of the archive, based on the calculation:
  - for 1C:
    - The size of ALL databases divided by 3 = a number that should be LESS than the size of the archive with 1c && the size of ALL databases (SQL) should be greater than the size of the archive

  - for Shara / Profiles:
    - make a check on the size of the archive, based on (for Shara / Profiles):
    <br>
    `test`
    <br>
    `min = nominal - (nominal * 20 / 100) // 119 - 24 = 95`<br>
    `max = nominal + (nominal * 20 / 100) // 119 + 24 = 143`<br>
    `nominal = Size of Shara in its original form // for example 119 GB`<br>
    `archive = Archive size // for example 100 GB`<br>
    - Condition:<br>
    `Archive > main && Archive < max && archive < nominal`
    - if the condition is NOT met, we send a letter in the required format to the post office

### Step-4:
  - checks archives for old age, deletes old archives, leaves new ones (the algorithm for deleting old ones is to think over)
  - after deletion, sends a letter, a list of which archives remained and which were deleted.
