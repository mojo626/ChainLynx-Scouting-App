import tbapy
import openpyxl
from openpyxl import load_workbook
from openpyxl import Workbook
import qrcode
from openpyxl.drawing.image import Image
import tkinter as tk
from tkinter import filedialog

#Device serial numbers for creating data for each device
deviceSerials = ["3100584a286fb200", "310058722270b200", "G0K0H40455030SQV", "emulator-5554", "4", "5"] #TODO ADD OTHER DEVICE SERIALS HERE
tba = tbapy.TBA('MEiPlwvcrXkXUwmp10JVF3gUYfLErgIpYn4XPe885gDr5oyUfN7TCb4fHPNOTr5j')

#Ask for event code
matches = tba.event_matches(input('What event code?')) 

root = tk.Tk()
root.withdraw()

#Ask for where the text files should be stored
file_path = filedialog.askdirectory();

#Loop through all teams in match
for input in range(0, 5):
    data = ""
    f = open(file_path + "/tabletDataTeam" + deviceSerials[input] + ".txt", 'w+')

    for matchNum in range(0, len(matches)):
        match = matches[matchNum]
        data += str(matchNum) + "/"
        if int(input) < 3:
            data += "red" + "/" + matches[matchNum]['alliances']['red']['team_keys'][input] + "/"
        else:
            data += "blue" + "/" + matches[matchNum]['alliances']['blue']['team_keys'][input - 3] + "/"
    print(data)
    f.write(data)
    



