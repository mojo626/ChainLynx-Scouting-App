import pandas as pd
import numpy as np
import openpyxl
from openpyxl import load_workbook

#Device serial numbers so that we can loop through all of the devices data
deviceSerials = {"3100584a286fb200", "310058722270b200"} #ADD OTHER DEVICE SERIALS HERE

#Load a workbook, or create one if there isn't one here
wb = load_workbook(filename = '/Users/ben/Desktop/Coding/Documents/ChainLynxScoutingData/output.xlsx') #CHANGE THIS FILE PATH TO WHERE YOU WANT THE EXCEL SHEET TO BE
ws = wb.active

for serial in deviceSerials:
    #Get JSON data
    df_json = pd.read_json('/Users/ben/Documents/ChainLynxScoutingData/teamData' + serial + '.txt', orient='records', lines=True) #CHANGE THIS FILE PATH TO WHERE THE SCOUTING DATA IS BEING STORED

    for (index, row) in df_json.iterrows():
        #Check if the data is already in the excel sheet
        inSheet = False
        for row in ws.iter_rows():
            if row[11].value == df_json.get('teamNumber').values[index]:
                if  row[10].value == df_json.get('matchNumber').values[index]:
                    print("Duplicate data, skipping (match number: " + str(df_json.get('matchNumber').values[index]) + ", team number: " + str(df_json.get('teamNumber').values[index]) + ")")
                    inSheet = True
        
        #If the data isn't already in the sheet, add it
        if inSheet == False:
            ws.append(df_json.values.tolist()[index])

#Save the workbook
wb.save('/Users/ben/Desktop/Coding/Documents/ChainLynxScoutingData/output.xlsx') #CHANGE THIS FILE PATH TO WHERE YOU WANT THE EXCEL SHEET TO BE