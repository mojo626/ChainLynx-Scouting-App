#!/bin/sh

mkdir -p /Users/ben/Documents/ChainLynxScoutingData #Change this for file path you want the data to be stored in
devices = $(adb devices)
echo $(devices)
for device in devices
do
    echo $(deivce)
done
cd /Users/ben/Documents/ChainLynxScoutingData
adb pull sdcard/Notes/teamData.txt /Users/ben/Documents/ChainLynxScoutingData
python3 /Users/ben/Desktop/Coding/updateScoutingDatabase.py