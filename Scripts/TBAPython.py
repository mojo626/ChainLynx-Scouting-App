import tbapy
import openpyxl
from openpyxl import load_workbook
from openpyxl import Workbook
import qrcode
from openpyxl.drawing.image import Image
from PIL import Image
import tkinter as tk
from tkinter import filedialog

deviceSerials = {"3100584a286fb200", "310058722270b200", "2", "3", "4", "5"}
tba = tbapy.TBA('MEiPlwvcrXkXUwmp10JVF3gUYfLErgIpYn4XPe885gDr5oyUfN7TCb4fHPNOTr5j')

matches = tba.event_matches('2022wagg') #Need to change this or ask for it

root = tk.Tk()
root.withdraw()

file_path = filedialog.askdirectory();

for input in range(0, 5):
    data = ""
    f = open(file_path + "/TabletMatchData/tabletDataTeam" + deviceSerials[input] + ".txt", "w+")

    for matchNum in range(0, len(matches)):
        match = matches[matchNum]
        data += str(matchNum) + "/"
        if int(input) < 3:
            data += "red" + "/" + matches[matchNum]['alliances']['red']['team_keys'][input] + "/"
        else:
            data += "blue" + "/" + matches[matchNum]['alliances']['blue']['team_keys'][input - 3] + "/"
    print(data)
    f.write(data)
    



