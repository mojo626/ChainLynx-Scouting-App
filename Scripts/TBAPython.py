import tbapy
import openpyxl
from openpyxl import load_workbook
from openpyxl import Workbook
from openpyxl.drawing.image import Image
import tkinter as tk
from tkinter import filedialog

#Device serial numbers for creating data for each device
deviceSerials = ["3100584a286fb200", "3100586a446ab200", "310058722270b200", "310058ba1c73b200", "310058aa2273b200", "310066705149b200"] #TODO ADD OTHER DEVICE SERIALS HERE
tba = tbapy.TBA('MEiPlwvcrXkXUwmp10JVF3gUYfLErgIpYn4XPe885gDr5oyUfN7TCb4fHPNOTr5j')

#Class to hold all of the matches, so that we can sort them by match number
class Match:
    def __init__(self, match, number):
        self.match = match
        self.number = number
matchList = []

#Ask for event code
matches = tba.event_matches(input('What event code?')) 

#Loop through all of the matches
for match in matches:
    #Only add it if the match is a qualifier
    if (match['comp_level'] != 'qm'):
        continue
    #Add teh match to the list
    matchList.append(Match(match, match['match_number']))

#sort the match list by match number
#we do this becuase for some reason, the api gives us the matches in the wrong order...
matchList.sort(key=lambda x: x.number)


root = tk.Tk()
root.withdraw()

#Ask for where the text files should be stored
file_path = filedialog.askdirectory();



#Loop through all teams in match
for input in range(0, 6):
    data = ""
    f = open(file_path + "/tabletDataTeam" + deviceSerials[input] + ".txt", 'w+')

    for matchNum in range(0, len(matchList)):
        match = matchList[matchNum].match
        data += str(matchNum) + "/"
        if int(input) < 3:
            data += "red" + "/" + matchList[matchNum].match['alliances']['red']['team_keys'][input] + "/"
        else:
            data += "blue" + "/" + matchList[matchNum].match['alliances']['blue']['team_keys'][input - 3] + "/"
    print(data)
    f.write(data)
    



