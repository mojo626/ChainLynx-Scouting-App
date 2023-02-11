import sqlite3
import csv
import json
import pandas as pd
import numpy as np

deviceSerials = {'emulator-5554'}
    

def txtToJson(filename):
    
    # dictionary where the lines from
    # text will be stored
    dict1 = {}
    
    # creating dictionary
    with open(filename) as fh:
    
        for line in fh:
    
            # reads each line and trims of extra the spaces
            # and gives only the valid words
            command, description = line.strip().split(None, 1)
    
            dict1[command] = description.strip()
    
    # creating json file
    # the JSON file is named as test1
    out_file = open("test1.json", "w")
    json.dump(dict1, out_file, indent = 4, sort_keys = False)
    out_file.close()



for serial in deviceSerials:
    #data = open('/Users/ben/Documents/ChainLynxScoutingData/teamData.txt', 'r')
    #jsonObj = json.loads(data.read())
    #print(type(jsonObj))
    #do something with the data
    #df_json = pd.read_json('/Users/ben/Documents/ChainLynxScoutingData/teamData.json', orient='records', lines=True)
    #df_json.to_excel('DATAFILE.xlsx')

    data.close()




