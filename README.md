# ChainLynx-Scouting-App

This is the scouting app for the ChainLynx robotics team.

This app is meant to run on android SM-T280 tablets, and with an API level of 22

There are also scripts for getting and processing data from the devices on a centralized computer


# To Use:

1. Run TBAPython.py to generate match data using the blue alliance API (hopefully in the future this will also transfer the data to the devices, but currently you have to do that manually with adb)

2. Scout on the devices

3. When you want the data, plug the devices that you want the data from in, and run getScoutingData.sh in the terminal (right now, it just gets the text files, but in the future it will also add the data into an excel sheet or a database)
