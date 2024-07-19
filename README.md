<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
# WhenTakenSlackBot 
<!-- TABLE OF CONTENTS 
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>
-->


<!-- ABOUT THE PROJECT -->
## About The Project
The WhenTakenSlackBot is a Kotlin-based application that interacts with the Slack API to retrieve messages from a specified channel, filter these messages for user scores, sort the scores, and post the top scores back to the Slack channel. The app is scheduled to run at specific intervals using Quartz Scheduler.



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these steps.

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/Vikl5/WhenTakenSlackBot.git
   ```
2. Configure Slack API Token and Channel Id
    If developing locally the class is setup to use environment variables for the slack token and channel id.
    In your terminal execute the following:
    ```sh
    export CHANNEL_ID=YOUR_CHANNEL_ID_HERE
    export SLACK_BOT_TOKEN=YOUR_TOKEN_HERE
    ```
    You can find your slack bot token here https://api.slack.com/apps/ and inside under OAuth & Permissions is your token.
3. Run the project
   ```sh
   gradle run
   ```
     

   




<!-- USAGE EXAMPLES -->
## Usage

<!--Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.-->
To change the time for the Quartz job to begin, you can change the ```.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(14, 10))``` with your desired timestamp.
If you want to change the time for when the bot should start/stop reading messages and start posting messages edit the Int values in the DateTimeToUnixConverter class.

The main functionality of the app is encapsulated in the following classes:
ChannelHistory

    readBetweenTimeStamps(): Reads messages between specified timestamps.
    filterMessages(): Filters and processes messages to extract scores.
    getSortedScores(): Retrieves the sorted scores.

HighScoreSorting

    sortHighScore(): Sorts the scores in descending order.

ScheduleMessage

    postOnSchedule(): Posts the sorted scores back to the Slack channel.
    buildMessageText(): Constructs the message text with top scores and tags the user with the highest score.

MessageJob

    Implements Quartz's Job interface to execute the scheduled task.



<!-- CONTRIBUTING -->
## Contributing

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!
Feel free to send me a DM if anything is unclear :) 

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.




<!-- CONTACT -->
## Contact

E-mail: Vikl5@protonmail.com






<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=for-the-badge
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=for-the-badge
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=for-the-badge
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=for-the-badge
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/screenshot.png
[Java.com]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.java.com/en/
[Spring.io]: https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=Spring&logoColor=white
[Spring-url]: https://spring.io/
