# ChainLynx-Scouting-App

This is the scouting app for the ChainLynx robotics team.

This app is meant to run on android SM-T280 tablets, and with an API level of 22

There are also scripts for getting and processing data from the devices on a centralized computer

**Make sure that before using, you go through and change the local file paths to match where you want the data to be stored and pulled from your computer**


# To Use:

1. Run TBAPython.py to generate match data using the blue alliance API (hopefully in the future this will also transfer the data to the devices, but currently you have to do that manually with adb push)

2. Scout on the devices

3. When you want the data, plug the devices that you want the data from in, and run getScoutingData.sh in the terminal (right now, it adds the data to an excel sheet, but in the future it will also hopefully add the data to a database)

# To-do

1. Find a way to automate file paths for saving an getting data so that the python and shell script can be used on any device with no modifications

2. Save data to a database instead of an excel file, and make some sort of user interface for querying data

3. Incorparate data processing like an OPR calculator
