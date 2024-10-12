# Water For The World(W4TW)  - Missing Seven
* [Product Details](https://github.com/csc301-fall-2022/team-project-4-engineers-without-boarders-canada-t/blob/main/deliverable-1/planning.md#product-details)
   - [Q1: What are you planning to build?](https://github.com/csc301-fall-2022/team-project-4-engineers-without-boarders-canada-t/blob/main/deliverable-1/planning.md#q1-what-are-you-planning-to-build)
   - [Q2: Who are your target users?](https://github.com/csc301-fall-2022/team-project-4-engineers-without-boarders-canada-t/blob/main/deliverable-1/planning.md#q2-who-are-your-target-users)
   - [Q3: Why would your users choose your product? What are they using today to solve their problem/need?](https://github.com/csc301-fall-2022/team-project-4-engineers-without-boarders-canada-t/blob/main/deliverable-1/planning.md#q3-why-would-your-users-choose-your-product-what-are-they-using-today-to-solve-their-problemneed)
   - [Q4: How will you build it?](https://github.com/csc301-fall-2022/team-project-4-engineers-without-boarders-canada-t/blob/main/deliverable-1/planning.md#q4-how-will-you-build-it)
   - [Q5: What are the user stories that make up the MVP?](https://github.com/csc301-fall-2022/team-project-4-engineers-without-boarders-canada-t/blob/main/deliverable-1/planning.md#q5-what-are-the-user-stories-that-make-up-the-mvp)
   
* [Process Details](https://github.com/csc301-fall-2022/team-project-4-engineers-without-boarders-canada-t/blob/main/deliverable-1/planning.md#process-details)
  - [Q6: What are the roles & responsibilities on the team?](https://github.com/csc301-fall-2022/team-project-4-engineers-without-boarders-canada-t/blob/main/deliverable-1/planning.md#q6-what-are-the-roles--responsibilities-on-the-team)
  - [Q7: What operational events will you have as a team?](https://github.com/csc301-fall-2022/team-project-4-engineers-without-boarders-canada-t/blob/main/deliverable-1/planning.md#q7-what-operational-events-will-you-have-as-a-team)
  - [Q8: What artifacts will you use to self-organize?](https://github.com/csc301-fall-2022/team-project-4-engineers-without-boarders-canada-t/blob/main/deliverable-1/planning.md#q8-what-artifacts-will-you-use-to-self-organize)
  - [Q9: What are the rules regarding how your team works?](https://github.com/csc301-fall-2022/team-project-4-engineers-without-boarders-canada-t/blob/main/deliverable-1/planning.md#q9-what-are-the-rules-regarding-how-your-team-works)

* [Highlights](https://github.com/csc301-fall-2022/team-project-4-engineers-without-boarders-canada-t/blob/main/deliverable-1/planning.md#highlights)

## Product Details
## **Q1: What are you planning to build?**
The project is to develop an Android App to simulate the Engineers Without Boarders workshop on water filter building practice. Many people in this world do not have access to clean water, the workshop is aimed at improving peoples' awareness on difficulties accessing clean water in different countries, and the target users are mostly high school students and teachers. The onsite workshop generally have small capacity, there are no enough public locations to hold the workshop, and it is difficult to get all equipments needed in some countries. This app is an replacement to the onsite workshop as people can download the app and start the workshop, more people are involved.
The diagram below is our mockup diagrams
<p align="center">
  <img src=./ImageRes/Missing%20Seven%20Screens.drawio.png alt="plot" width="1000"/>
</p>


## **Q2: Who are your target users?**

https://workspace5276499.xtensio.com/pzg7libj

## **Q3: Why would your users choose your product? What are they using today to solve their problem/need?**

We believe the users would choose our Water For The World (W4TW) mobile app not only because of its customizable user interface with other interesting and useful functionalities but also because this app comes in many different forms and is easy to get started.

Firstly, our W4TW mobile app can definitely save users’ time, since the original format of W4TW is happening during an in-person workshop, so normally it will take the W4TW members a long time on the road to meet at the same place. By using our app, they can skip the process of going to the meeting, instead, they can just stay at home and use our app to connect online which saves the users tons of time.

Secondly, the users have many different ways to discover and learn new information and technologies from our W4TW mobile app. W4TW workshop is designed to teach volunteers about water filter building knowledge, so definitely there are tons of knowledge to learn and we will make sure to include those in our app even with specific video. We decided to divide that information and pair them with related game functionalities, so the users can play and learn at the same time. All the information and knowledge will directly come from the official W4TW website and summarize from the previous hundreds of in-person workshops, so of course, the information is accurate and reliable. 

Thirdly, our W4TW mobile app has many forms and can be used even under difficult circumstances.  As the partner mentioned during our weekly meeting, some of the W4TW users might not have the access to the internet, so our app will be including an offline mode which allows the users to ignore the limitations of the internet and access and use the app, so once the user gets our app installed, they can use it even without internet. When the phone connects to the internet, our app will automatically upload the data online. 

## **Q4: How will you build it?**

 * **Technology stack**\
   We will be using Android Studio to build an Android App based on Jetpack Compose, we use Jetpack Compose as our UI framework because it does not need xml files and the code will be maintained better. It also makes the app fragment free.
   
 * **Language**\
   We will use Kotlin. Google android team is now focusing on supporting Kotlin supports, so Kotlin is better than Java in android Development
   
 * **libraries**\
   Jetpack Compose related libraries

 * **Deploy the application**\
  We will use the Android Studio tools to Build APK, and upload the APK to partner's website and/or Google Play Store

 * **Third party applications or APIs**\
   We did not find any APIs needed yet, the data will be directed provided by our partner by email/messages, will update if we get some later.
 
 * **Testing strategy**\
   We will write unittests on every functions in the model classes and viewModel classes, for the UI classes, we will test them on the Android emulator/physical devices
     
 * **Architecture**\
   MVVM (Model - View - ViewModel) + clean architecture pattern, we will use Jetpack Compose to make the UI of the app, use view models to handle data from our local database. We use MVVM because this is the most common android architecture, also androidx library has support in viewModel and MVVM is recommended for Android development
<p align="center">
  <img src=./ImageRes/Missing%20Seven%20Architecture.drawio.png alt="plot" width="600"/>
</p>

## **Q5: What are the user stories that make up the MVP?**

https://drive.google.com/file/d/1FtkFjvEuns3qunmjAE3l3A_F8x8FFtKy/view?usp=sharing

----
## Intellectual Property Confidentiality Agreement 
We have sent an email about confidentiality to our partner but have not received the response yet, but from the previous meeting since this project is non-profit and is for public welfare we believe it can be shared.
We will update this part once we get response from our partner

----

## Process Details


#### Q6: What are the roles & responsibilities on the team?

 - Xuhui Chen：
   - Role: Project Manager/Full Stack Developer - Architect
   - Responsibilities:
   1. Design the overall architecture of the app
   2. Jump into and develop any part of the project 
   3. Offer help to teammates better understand Android development
   - Strength: Kotlin, Android, MVVM
   - Weakness: dependency injection, Jetpack Compose, Database design

 - Haonan Gao:
   - Role: Back-End Developer
   - Responsibility: Design data classes needed to be passed into different screens; Generate data class instances from the database; Provide use cases for data classes;
   - Strength: JAVA, C, SQL
   - Weakness: All front-end related
   
 - Ziyuan Gu:
     - Role: Front End Developer
     - Responsibilities: Will mainly work with Android front end including the page design and implementation; and also responsible for scenario tests.
     - Strength: Java, Spring, .NET
     - Weakness: Infra, system code, DevOps

 - Yuelin Jiang:
   - Role: Scrum Master/Front End Developer
   - Responsibilities: Will be working on the front-end part of the project, and dealing with front-end features like UI components and Screen Design. 
   - Strength: Python, C, SQL
   - Weakness: Java, IOS, React

 - Wenzhi Lin:
   - Role: Local Database Designer
   - Responsibilities:
    1. Design a database(Room or SQLite) to save complex data.
    2. Implement database gateways.
    3. Use SharedPreferences to save simple key-value pairs.
   - Strength: Python, C/C++, MySQL, Java
   - Weakness: No experience in any frontend and backend coding, mobile application development
   
 - Yaowei(Jason) Liu:
   - Role: Backend developer
   - Responsibilities:
    1. Use data from the database to genrate data provider model.
    2. Use the data provider model to impelement data usecases.
   - Strength: Python, Java, SQL
   - Weakness: limited experience with developing as a team, limited knowledge about andriod sepecific technologies

 - Tong Su：
    - Role: Product Owner/Backend Developer - ViewModel Designer
    - Responsibilities: 
    1. Design ViewModels for different activities
    2. Integrate use cases
    3. Manage UI state changes
    - Strength: Java, SQL, C
    - Weakness: Kotlin, Jetpack, Android

## Q7: What operational events will you have as a team?

- We plan to have regular meetings among the group member twice a week, both online via Zoom / Google Meet. And some additional discussion if we need them. And we plan to meet the project partner once a week.
- We have held 2 meetings with our project partner (Engineers Without Borders Canada). For the first online meeting, we introduce ourselves separately then the partner introduces the organization's background. we have a preliminary understanding of the software content. The second meeting, actually, was an in-person workshop, the collaborator showed us the specific principle and process of the water filtration system, which is also part of the software content, which is helpful for us to simulate this process in our application.


  
## Q8: What artifacts will you use to self-organize?

   * **How do you keep track of what needs to get done?**\
   Have lists of tasks with proirities and deadlines to make sure we stay on top of our schedule.

   * **How do you prioritize tasks?**\
   Tasks are prioritized based on dependency and urgency. For example, if a file is needed for the whole team to move on. This file is on top of our priority and is addressed as soon as possible. Or if we need to have a feature to be completed at a deadline, we would proitize to work on this feature and delay other unnecessary features.
   
   * **How do tasks get assigned to team members?**\
   Tasks are assigned to each team member according to there role/experience, so everyone can work on the tasks they are good at.
   
   * **How do you determine the status of work from inception to completion?**\
   We plan to have four status for each task in our team. Which are unassigned, assigned, need help, and completed. This way, we can keep track of the status of each task, and get help when we need.
   
   
## Q9: What are the rules regarding how your team works?

Describe your team's working culture.

**Communications:**
 * What is the expected frequency? What methods/channels are appropriate? 
   - We expect to hold team meetings at least three times a week, and hold Partner meeting weekly at Monday night. And these meetings will be hold on Zoom. 
   - Besides these meetings, we have a private group chat that is used for real-time, quick communication.
 * If you have a partner project, what is your process (in detail) for communicating with your partner?
   - Our partner has decided to meet with us once a week on Zoom or Google Meeting. For short communication and announcement we will be sending emails.
 
**Meetings:**
 * How are people held accountable for attending meetings, completing action items? Is there a moderator or process?
   - We post group announcement in our group chat to remind everyone about the action items. Our team leader will be the moderator and be responsible for communicating with each individual regarding the attendace and work completion problem.

----
## Highlights

1. During our discussion with our partner, we noticed that the target audience of the application may have difficult access to the internet. Thus we made the decision to make a majority of our application run offline. The only part of the app that requires internet is sending the workshop result to the teacher/insturctor. This design choice will allow people without stable access to the internet to utilize the application without compromising the user experience and help us to reach more target audiences.

2. During our team meeting, we made an agreement on the team structure. Our team will be separated into three small groups, which are front end group, back end group and database group. Each group will contain two to three person. But in the future, group size could change, people could be moved to a different group and the group work scope could also be modified. Therefore we can be flexible enough to maximize our productivity.

3. One of the key decisions that came up during our meeting with the project owner is to put down what we have in hand aside to attend the Water For The World Workshop. In the first meeting with the project owner, we found a serious problem. Our task is to design mobile phone software used to simulate the in-person workshop. Although the partner has done his best to describe the workshop, some details are still not very clear. In order to make our project closer to the real workshop, our group temporarily decided to participate in the most recent in-person workshop to understand the real situation. We believe that this can help us understand more about the project and to achieve better results.

4. Another decision we have made during our meeting is to use survey form to share results. During our meeting with John, he mentioned that he would like to receieve feedback from student about what do they learn and how the workshop could be improved. He suggested that the result could be sent through email. After the meeting, our group thought this app is not intended to collect any sensitive information from the user. If we use email, the users will need to type in their email account and password which is not ideal. Another issue may arise is that students may not have their email address and thus could not share their thoughts wuith the teacher. In that case, our team decided to use the survey form to replace the email for the feedback.  Students could write their feedback inside the app and the teacher will receive it in his/her side.

5. In the beginning, we planned to make an account registration function to allow users to choose their roles, like student or teacher. However, this feature is only used for sending students’ reports of virtual interactive water filter building exercises, which means this feature has pretty limited functionality. Thus, we decide to let students fill out their teachers’ emails before participating in the activities or exercises, where the reports can be sent to teachers’ emails after completing the activities, without creating role-identified accounts. Moreover, the account registration system can also be added back if more related features are implemented later.
