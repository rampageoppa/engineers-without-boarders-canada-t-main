# Water For The World(W4TW) - Missing Seven

## Description
This is an android mobile application that develops for Engineers Without Borders Canada. This application is for people who are in areas lacking water or lack knowledge about water environment protection, it contains some questions about water purifying and a water filter experiment. The user will be required to finish these questions and build a virtual water filter. Then, the user will get a score to evaluate the performance of the water filter they create. The final goal of this application is to help people raise their awareness of water protection and learn some knowledge about water purifying.




## Key Features
- the introduction page of _the Engineers Without Boarders_ and _the Water For The World_(W4TW) workshop, it will give the users(mostly students) on some imformation of current world water situation and some stats.
- some multiple question pages to let the user choose the answer(not for mark, only for introduction and stats collection), the answers will be stored for later use, as well as a pre- and post-activity assessments to assess users’ knowledge or misconceptions on poverty, literacy and accessibility issues.
- the app will have a IP-based geolocation capability, such that it can provide imformation including: Country population, Gross Domestic Product (GDP) and Literacy Rate, according to location, as a reference to users.
- Virtual water filter building exercise which highlights the differences in accessing clean water in various locations in the world. This feature will be displayed as an interactive drag-and-drop system to enable filter building and experimentation, as well as utilize probability since outcomes should not be guaranteed from playthrough to playthrough. Also it will contains a shop page to let the user buy the materials they need for their water filter.
- Ability to share results of the differing filter building experiences between users to highlight the issues around accessing clean water. Also, when users(students) is firstly using the software, they will be asked to provide their teachers'/parents' email addresses, such that teachers or others who may wish to view the results of multiple users.


## Instructions
On an Android smartphone, the user can install our application using an APK package. Download the APK package from our repository.
Following installation, the user can begin a new workshop session, and from there, instructions will be provided to walk them through the app's workflow.

The program opens to a page with two options: create new session or resume current session. You can only select the create new session option when using the app for the first time and if you quit while a workshop session was in progress. You can pick up where you left off by selecting "Resume Session."

Other than the create new workshop/resume workshop page, We have 5 types of pages in our app, and these pages are ordered in sequential order, In every page, there will be a back and next button for navigating between pages. 

The text information page has the following logic:
At the bottom of the page, there will be a checkbox to confirm you have read the text above.  You will only be able to navigate to the next page after checking the checkbox.

The multiple choice page has the following logic:
There will be options on the screen, and you can choose any of them, but the next page button only becomes available after you selected the right answer.

The logic behind the sliding scale page is as follows:
A question with a sliding bar asking the user to select the right response will be presented. The next page button will become available as soon as the user slides to the correct portion, at which point a text explanation describing why this is the correct area will be presented.

The logic of short answer page is as follows:
User will see a question and a text input box to write their answer, they will only be able to proceed to the next page once they have filled in the text input.

The final type of page is one used to construct a water filer. There will be 7 spaces on this page where the user can select the material to use for this layer. When a user clicks on an empty layer, they are taken to a page showing all the available items. After selecting an item, they are taken back to the page for creating the water filter, where the view has been modified to reflect the new additions. The page also display the amount of money the user have and the country the user representing. There will also be a store button, instruction button, and a evaluate button.

The store button to purchase the item needed to assemble the water filter, the user can navigate to a store.
The user's available money will be shown on the store page, along with a variety of items. Each item will have an add/minus button so that the user may choose the quantity they want to buy.
After choosing the things to purchase and completing the payment process, the app will return to water filter building page. When we click on a layer in the water filter, we can see the newly purchased items are now available for use. Additionally, the amount of money will also be updated.

Insturction button will navigate to a page displaying a guide about how to build the water filter.(i.e. what material to use in which order)
The evaluate button will give the user a score on the water filter they have built.

## Development requirements
- There's no requirement regarding OS. As long as Android Studio is installed, you can clone our codes and build the project locally. The download link of
Android Studio can be found [here](https://developer.android.com/studio?gclid=CjwKCAjwtp2bBhAGEiwAOZZTuHFTHsdXGF5dyEeAVvV1wCzRoQovlRF91SkEpikS3aeretue1yp5GxoC_VYQAvD_BwE&gclsrc=aw.ds)
- If you want to run the actual application, you need to go to "Build" > "Build Bundle(s) / APK (s)" > "Build APK(s)". 
<img width="431" alt="Screen Shot 2022-11-06 at 23 24 05" src="https://user-images.githubusercontent.com/90353234/200226219-9bd3fdf2-87f7-4106-8b3b-b97f5c82855c.png">

After an APK is built, you could find the apk file by clicking "locate" on the buttom right-hand corner.
<img width="375" alt="Screen Shot 2022-11-06 at 23 32 07" src="https://user-images.githubusercontent.com/90353234/200227246-c02a3ed2-d563-444a-b992-14a966611355.png">

Then you could install the application by copying the file to any Android machine. 

We have also uploaded an APK under this folder, the user can download it here to install it on an Android device

## Deployment and Github Workflow
- We cut a branch called D2 off the main branch. We are entirely working on D2 branch. For each feature, we will cut off a branch from D2. e.g. The integration of multiple choice tasks, we cut off a branch called MultipleChoiceTaskIntegration and all the tasks related to multiple choice task is done on that branch. Everytime a member wish to push the code onto the sub-brach, we will first git pull to and resolve conflicts using android studio's github GUI, then push the code onto the sub-branch. When the last person finishes the tasks, first rebase the sub-branch onto D2 and then the person will create a pull request, another teammate will review the code. When the pull request is approved, either the person requests the pull request or the reviewer will merge the code onto D2. This flow can resolve conflicts on both stages of development - pushing changes to sub-branch and merging into D2. When everything is done, we make an pull request and merge it into main, this time no review is required because we have already reviewed everything in D2.
- To deploy the application, when we run the code on Android Studio, a debug version of the app will be installed on the Android device selected, or on an Android emulator provided by android studio, and then we can use and debug our app. After the development of the entire app(for D2) is finished, we generated a product release 
APK. We have uploaded an APK under this folder.
## Licenses

We are using the MIT license. We choose this license because our project is non-profit and is for social welfare. We have discussed with our partner before about confidentiality. They hope others can update this project later to better serve the society. The MIT license allow others to make copy and modify our codebase which serves our partner's purpose well.

## Link to our deployed application:
https://github.com/csc301-fall-2022/team-project-4-engineers-without-boarders-canada-t/blob/D2/deliverables/Deliverable2/W4TW_D2.apk
## Link to our demo video:
https://github.com/csc301-fall-2022/team-project-4-engineers-without-boarders-canada-t/blob/D2/deliverables/Deliverable2/demo.mp4
