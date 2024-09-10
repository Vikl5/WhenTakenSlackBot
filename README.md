# WhenTakenSlackBot 


<!-- ABOUT THE PROJECT -->
## About The Project
The WhenTakenSlackBot is a Kotlin-based application that interacts with the Slack API to retrieve messages from a specified channel, filter these messages for user scores, sort the scores, and post the top scores back to the Slack channel. The app is scheduled to run at specific intervals using Quartz Scheduler with a Cron job.



<!-- GETTING STARTED -->
## Getting Started
There are two ways to run the application. You can either clone the repo locally and run it, or pull the image from Dockerhub and run it in a container.

To get a local copy up and running follow these steps:

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/Vikl5/WhenTakenSlackBot.git
   cd WhenTakenSlackBot
   ```
2. Configure Slack API Token and Channel Id
    If developing locally the class is setup to use environment variables for the slack token and channel id.
    In your terminal execute the following:
    ```sh
    export CHANNEL_ID=YOUR_CHANNEL_ID_HERE
    export SLACK_BOT_TOKEN=YOUR_TOKEN_HERE
    ```
    You can find your slack bot token [here](https://api.slack.com/apps/) and inside under OAuth & Permissions is your token.
3. Build and run the project
   ```sh
   gradle build
   gradle run
   ```

To run the the image wih Docker container you can pull it from here: 
https://hub.docker.com/r/vikl5/whentaken-slack-bot
The docker image has two mandatory environment variables for it to be able to run, SLACK_BOT_TOKEN and CHANNEL_ID, and the following environment variables are optional to provide. Meaning they will use default values from the code if they are not provided.

- POSTING_TIME //Uses 24 hour format and not AM or PM.
- START_READING //Uses 24 hour format and not AM or PM.
- END_READING //Uses 24 hour format and not AM or PM.
- TIMEZONE //Your timezone, e.g. Europe/Paris. Uses the Kotlin TimeZone library
- CRON_SCHEDULE //Only valid Cron jobs, see [CronGuru](https://crontab.guru/)
```sh
docker run \
  -e SLACK_BOT_TOKEN="YOUR_TOKEN_HERE" \
  -e CHANNEL_ID="YOUR_CHANNEL_ID_HERE" \
  -e TIMEZONE="your/timezone" \
  -e POSTING_TIME="10:00" \
  -e START_READING_TIME="06:00" \
  -e END_READING_TIME="10:00" \
  -e CRON_SCHEDULE="0 0 10 ? * MON-FRI" \
  whentaken-slack-bot
```
   

<!-- USAGE EXAMPLES -->
## Usage

Before the application can be run you need to create a new app in your Slack workspace [here](https://api.slack.com/apps/).
Go to OAuth and Permissions and add the following scopes:

      - channels:history
      - channels:read
      - chat:write
      - chat:write.customize
      - groups:history
      - users:read
Then click on Install App in the sidebar and install it to your workspace.
Next go to your Slack channel and copy the channel ID and add it to the env variable.
Now all you need is to invite the bot to your channel and after that give it a test run!

<!--Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.-->


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

