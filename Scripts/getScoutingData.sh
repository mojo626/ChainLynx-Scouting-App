#!/bin/sh

#Make a directory for scouting data if there isn't one already
mkdir -p /Users/ben/Documents/ChainLynxScoutingData #Change this for file path you want the data to be stored in

#Loop through all devices and pull data from each
for DEVICE in $(adb devices | grep -v "List" | awk '{print $1}')
do
  adb -s $DEVICE pull sdcard/Notes/teamData$DEVICE.txt /Users/ben/Documents/ChainLynxScoutingData
done


#Go to directory, and then run python script for updating excel sheet with new data
cd /Users/ben/Documents/ChainLynxScoutingData
python3 /Users/ben/AndroidStudioProjects/ChainLynxScoutingApp/Scripts/updateScoutingDatabase.py