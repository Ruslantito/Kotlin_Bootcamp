## Exercise 1: List of companies
A simple example of such object transformations is a preview list containing partial information about an object.  

Create a collection of companies, each is described as follows:
- Name
- Field of activity (IT, Banking, Public services)
- A list of vacancies, each contains information about the profession (Developer, QA, Project Manager, Analyst, Designer), level (junior, middle, senior) and the proposed salary level
- Contacts

You can take a list of companies from [file](data-samples/listOfCompanies.json)

**Advice!** This `json` file can help you to figure out some types of company fields. But it can only be strings and numbers - `json` doesn't supply complicated types and won't help you with objects, enums and others! For example, the "salary" field is obviously of number type, but you should think what to use with the "field of activity" (remember type of incidents). 

**Input:** in the console, a user selects the following filters sequentially: 
- field of activity
- profession
- level for the candidate
- salary level (there must be several forks)

At each stage there is an option "All". For example, if you click "All" on the first stage, you'll choose all fields of activity and go to the next stage.  
The user selects a number of chosen option. On an incorrect input, the program prints "It doesn't look like a correct input." and repeats an option.  

**Output:** when all stages passed, the program prints to the console a list of vacancies, filtered according to the selected options.

_Example:_
```
Select a field of activity:
1. IT
2. Banking
3. Public services
4. All

2

Banking. Select a profession:
1. Developer
2. QA
3. Project Manager
4. Analyst
5. Designer
6. All

4

Banking. Analyst. Select the level of a candidate:
1. Junior 
2. Middle
3. Senior
4. All

1

Banking. Analyst. Junior. Select a salary level:
1. < 100000
2. 100000 - 150000
3. > 150000
4. All

1

Banking. Analyst. Junior. < 100000
The list of suitable vacancies:

1.
Junior Analyst     ---      80000
  OOO SuperPay
  Banking
---------------------------------------

2. 
Junior Analyst     ---      70000
  MMM
  Banking
---------------------------------------

3.
Junior Analyst     ---      100000
  CryptoSuperGo
  Banking
---------------------------------------
...
...
...
```

## Exercise 2: Parse a resume file
Let's write a mechanism that allows you to import an information from a resume file

A resume has the following template:
- Candidate information block
  - Full name
  - Profession (the list of professions is same with one from the previous exercise)
  - Sex
  - Date of Birth
  - Contacts (phone, e-mail)
  - Willingness of relocation: preferable/possible/impossible
- Block with information about education. For each educational institution:
    - Type of education (for example: higher, secondary special, secondary)
    - Years of education
    - Description
- Block with job experience. For each:
    - Dates of work
    - Company name
    - Description
- Block for some words in free form

Check the ready resume [input file](data-samples/resume.json).

Create a data model (classes) corresponding to the resume template. Our program loads data from the file, recognizes template blocks and fill the corresponding objects with data

**Input:** import data from the resume file (put it to `src/files`).

**Output:** output the data from the created classes to the console. The data in the console and in the input file must be the same. 

**Advice!** For example, you can keep data in program using `data class` and use its `toString()` method. Read about `toString()` and its advantages in `data class`, if you didn't.

**Requirement!:** Think about data types of your fields in classes. For example, the "name" is obviously a string, the "date of birth" should be of a date type, the "contacts" has come fields inside - it should be another class. 

_Example:_
```
Block 1
CandidateInfo(name=Vasiliev Sergei Petrovich, profession=QA, contacts=Contacts(phone=72938572843, email=vspetrovich@pochta.ru) ... )
...

Block 2
[Education(type=higher, ...), Education(type=secondary, ...)]
...
```
## Bonus exercise 3: Seniority
- The program is a combination of the ex1 and the ex2.
- The program takes as input file a list with companies
- To the candidate levels in [file](datasamples/listOfCompanies.json) add seniority (in years): for junior - 0, for middle - 3, for senior - 6
- The program takes an input file with a resume and parse
- The program prints a list of vacancies, suitable for the resume data (by the following two parameters): 
  - match a profession from resume with professions in vacancies
  - match an experience in years - count years and months from all places of work and match with a candidate seniority

_Example:_
```
The candidate:
Name: Vasiliev Sergei Petrovich
Profession: QA
Experience: 1 year 5 months (junior)
Suitable vacancies:
OOO SoftForHomies
Field of activity: IT
Candidate level: junior
Salary: 60000
Contacts: 89785455654
...
```
